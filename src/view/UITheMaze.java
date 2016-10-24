package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		this.nameLbl.setBounds(300, 10, 200, 25);
		this.nameLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.add(nameLbl);

		this.resolveBtn = new JButton("Resolve");
		this.resolveBtn.setBounds(400, 300, 100, 25);
		this.resolveBtn.addActionListener(this);
		this.resolveBtn.setActionCommand("resolve");
		this.resolveBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.add(this.resolveBtn);

		this.backBtn = new JButton("Back");
		this.backBtn.setBounds(100, 300, 100, 25);
		this.backBtn.addActionListener(this);
		this.backBtn.setActionCommand("back");
		this.backBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.add(this.backBtn);
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String cmd = arg0.getActionCommand();

		if(cmd.equals("generate"))
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
