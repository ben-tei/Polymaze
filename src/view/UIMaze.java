package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class UIMaze extends JPanel implements KeyListener, ActionListener
{
	private static final long serialVersionUID = 1L;

	private UIView myUIView;
	private MyTabbedPane myTabs;

	public UIMaze(UIView uiView, MyTabbedPane tabs)
	{
		this.myUIView = uiView;
		this.myTabs = tabs;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub

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
