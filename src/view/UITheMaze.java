package view;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.business.Maze;
import model.business.MazeSolver;
import util.exception.PolymazeException;

public class UITheMaze extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;

	private UIView myUIView;
	private MyTabbedPane myTabs;
	private Maze myMaze;

	private JLabel nameLbl;

	private JButton backBtn;

	private JButton resolveBtn;

	private int startX;

	private int startY;

	private int px;

	public UITheMaze(UIView uiView, MyTabbedPane tabs, Maze maze)
	{
		this.myUIView = uiView;
		this.myTabs = tabs;
		this.myMaze = maze;

		this.myUIView.setResizable(true);

		this.myUIView.setTitle("Polymaze - The Maze");

		this.nameLbl = new JLabel(this.myMaze.getName());
		this.nameLbl.setBounds(50, 30, 200, 25);
		this.nameLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.add(nameLbl);

		px = 0;

		if(this.myMaze.getWidth() > 50 || this.myMaze.getLength() > 50)
		{
			px = 5;
		}
		else
		{
			px = 10;
		}

		this.startX = (this.myUIView.getWidth() - this.myMaze.getWidth() * px) / 2;
		this.startY = 100;

		File[] files = new File("sprites/" + this.px + "px/").listFiles();

		HashMap<String, BufferedImage> hm = new HashMap<String, BufferedImage>();

		for(File file : files)
		{

			try
			{
				hm.put(file.getName(), ImageIO.read(file));
			}
			catch(IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for(int i = 0; i < this.myMaze.getLength(); i++)
		{
			for(int j = 0; j < this.myMaze.getWidth(); j++)
			{

				BufferedImage img = hm.get(this.myMaze.getContent()[j][i].wallToString() + ".png");
				JLabel jl = new JLabel(new ImageIcon(img));
				jl.setBounds(this.startX + j * img.getWidth(), this.startY + i * img.getHeight(), img.getWidth(),
						img.getHeight());
				this.add(jl);
				this.setComponentZOrder(jl, 0);
			}
		}

		this.resolveBtn = new JButton("Resolve");
		this.resolveBtn.setBounds(510, 30, 100, 25);
		this.resolveBtn.addActionListener(this);
		this.resolveBtn.setActionCommand("resolve");
		this.resolveBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.add(this.resolveBtn);

		this.backBtn = new JButton("Back");
		this.backBtn.setBounds(200, 30, 100, 25);
		this.backBtn.addActionListener(this);
		this.backBtn.setActionCommand("back");
		this.backBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.add(this.backBtn);

	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String cmd = arg0.getActionCommand();

		if(cmd.equals("resolve"))
		{
			try
			{
				ArrayList<Point> sol = MazeSolver.solveMaze(this.myMaze);

				int i = 0;

				while(i < sol.size())
				{
					File file = new File("sprites/" + this.px + "px/sol.png");
					BufferedImage img;
					try
					{
						img = ImageIO.read(file);
						JLabel jl = new JLabel(new ImageIcon(img));
						jl.setBounds(this.startX + (int) sol.get(i).getX() * img.getWidth(),
								this.startY + (int) sol.get(i).getY() * img.getHeight(), img.getWidth(),
								img.getHeight());
						this.add(jl);
						this.setComponentZOrder(jl, 1);
					}
					catch(IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					i++;
				}

				this.revalidate();
				this.repaint();

			}
			catch(PolymazeException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(cmd.equals("back"))
		{
			this.myUIView.setResizable(false);

			if(this.myTabs.getSelectedIndex() == 0)
			{
				this.myTabs.updateTab(0, new UIMyMazes(this.myUIView, this.myTabs));
			}
			else
			{
				this.myTabs.updateTab(1, new UIAllMazes(this.myUIView, this.myTabs));
			}

		}

	}

}