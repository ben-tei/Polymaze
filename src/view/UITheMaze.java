package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.business.Maze;

public class UITheMaze extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;

	private UIView myUIView;
	private MyTabbedPane myTabs;
	private Maze myMaze;

	private JLabel nameLbl;

	private JButton backBtn;

	private JButton resolveBtn;

	public UITheMaze(UIView uiView, MyTabbedPane tabs, Maze maze)
	{
		this.myUIView = uiView;
		this.myTabs = tabs;
		this.myMaze = maze;

		this.myUIView.setTitle("Polymaze - The Maze");

		this.nameLbl = new JLabel(this.myMaze.getName());
		this.nameLbl.setBounds(325, 10, 200, 25);
		this.nameLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.add(nameLbl);

		int px = 0;

		if(this.myMaze.getWidth() > 50 || this.myMaze.getLength() > 50)
		{
			px = 5;
		}
		else
		{
			px = 10;
		}

		File[] files = new File("sprites/" + px + "px/").listFiles();

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
				jl.setBounds(((this.myUIView.getWidth() - this.myMaze.getWidth() * img.getWidth()) / 2)
						+ (j * img.getWidth()), 100 + (i * img.getHeight()), img.getWidth(), img.getHeight());
				this.add(jl);
			}
		}

		this.resolveBtn = new JButton("Resolve");
		this.resolveBtn.setBounds(510, 425, 100, 25);
		this.resolveBtn.addActionListener(this);
		this.resolveBtn.setActionCommand("resolve");
		this.resolveBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.add(this.resolveBtn);

		this.backBtn = new JButton("Back");
		this.backBtn.setBounds(200, 425, 100, 25);
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

		}
		else if(cmd.equals("back"))
		{
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
