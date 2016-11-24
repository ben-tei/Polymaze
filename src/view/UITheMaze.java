package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
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

		this.px = 0;

		if(this.myMaze.getWidth() > 50 || this.myMaze.getLength() > 50)
		{
			this.px = 5;
		}
		else
		{
			this.px = 10;
		}

		this.startX = (this.myUIView.getWidth() - this.myMaze.getWidth() * px) / 2;
		this.startY = 100;

		File[] files;

		try
		{
			files = new File(getClass().getResource("/assets/" + this.px + "px/").toURI()).listFiles();

			HashMap<String, BufferedImage> hm = new HashMap<String, BufferedImage>();

			for(File file : files)
			{

				try
				{
					hm.put(file.getName(), ImageIO.read(file));
				}
				catch(IOException e)
				{
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

				}
			}

			File file = new File(getClass().getResource("/assets/" + this.px + "px/green.png").toURI());
			BufferedImage img;

			img = ImageIO.read(file);

			JLabel start = new JLabel(new ImageIcon(img));
			start.setBounds(this.startX + this.myMaze.getStartX() * img.getWidth(),
					this.startY + this.myMaze.getStartY() * img.getHeight(), img.getWidth(), img.getHeight());
			this.add(start);
			this.setComponentZOrder(start, 1);

			JLabel end = new JLabel(new ImageIcon(img));
			end.setBounds(this.startX + this.myMaze.getEndX() * img.getWidth(),
					this.startY + this.myMaze.getEndY() * img.getHeight(), img.getWidth(), img.getHeight());
			this.add(end);
			this.setComponentZOrder(end, 1);

		}
		catch(IOException | URISyntaxException e)
		{
			e.printStackTrace();
		}

		this.backBtn = new JButton("Back");
		this.backBtn.setBounds((int) (this.nameLbl.getX() + this.nameLbl.getSize().getWidth() + 50), 30, 100, 25);
		this.backBtn.addActionListener(this);
		this.backBtn.setActionCommand("back");
		this.backBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.add(this.backBtn);

		this.resolveBtn = new JButton("Resolve");
		this.resolveBtn.setBounds(this.backBtn.getX() + 250, 30, 100, 25);
		this.resolveBtn.addActionListener(this);
		this.resolveBtn.setActionCommand("resolve");
		this.resolveBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.add(this.resolveBtn);

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

				int i = 1;

				File file = new File(getClass().getResource("/assets/" + this.px + "px/red.png").toURI());
				BufferedImage img = ImageIO.read(file);

				while(i < sol.size() - 1)
				{

					JLabel jl = new JLabel(new ImageIcon(img));
					jl.setBounds(this.startX + (int) sol.get(i).getX() * img.getWidth(),
							this.startY + (int) sol.get(i).getY() * img.getHeight(), img.getWidth(), img.getHeight());
					this.add(jl);
					this.setComponentZOrder(jl, 1);

					i++;
				}

				this.revalidate();
				this.repaint();

			}
			catch(PolymazeException | IOException | URISyntaxException e)
			{
				e.printStackTrace();
			}
		}
		else if(cmd.equals("back"))
		{
			this.myUIView.setResizable(false);
			this.myUIView.setSize(new Dimension(800, 720));
			this.myUIView.setLocationRelativeTo(null);

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
