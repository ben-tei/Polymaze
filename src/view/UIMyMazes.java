package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class UIMyMazes extends JPanel implements KeyListener, ActionListener
{

	private static final long serialVersionUID = 1L;

	private UIView myUIView;
	private MyTabbedPane myTabs;

	private JButton generateBtn;

	public UIMyMazes(UIView uiView, MyTabbedPane tabs)
	{
		this.myUIView = uiView;
		this.myTabs = tabs;

		this.generateBtn = new JButton("Generate new maze");
		this.generateBtn.setBounds(325, 450, 150, 25);
		this.generateBtn.addActionListener(this);
		this.generateBtn.setActionCommand("generate");
		this.generateBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.add(this.generateBtn);
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String cmd = arg0.getActionCommand();

		if(cmd.equals("generate"))
		{
			this.myTabs.updateTab(0, new UIGenerateMaze(this.myUIView, this.myTabs));

		}

	}

	@Override
	public void keyPressed(KeyEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0)
	{
		// TODO Auto-generated method stub

	}

}
