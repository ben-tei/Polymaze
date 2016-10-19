package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class UIHome extends JPanel implements KeyListener, ActionListener
{
	private static final long serialVersionUID = 1L;

	private UIView myUIView;

	private JLabel homeLbl;

	private JTabbedPane tabs;

	private UIMyLabyrinths myLabyrinths;

	private UIAllLabyrinths allLabyrinths;

	public UIHome(UIView uiView)
	{
		this.myUIView = uiView;
		this.myUIView.setTitle("Polymaze - Maze Generator");

		this.homeLbl = new JLabel("Polymaze");
		this.homeLbl.setBounds(350, 10, 100, 25);
		this.homeLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.add(homeLbl);

		this.myLabyrinths = new UIMyLabyrinths();
		this.allLabyrinths = new UIAllLabyrinths();
		this.tabs = new JTabbedPane();

		this.tabs.add("My Labyrinths", this.myLabyrinths);
		this.tabs.add("All Labyrinths", this.allLabyrinths);
		this.tabs.setBounds(0, 50, this.myUIView.getWidth(), this.myUIView.getHeight());

		this.add(this.tabs);
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
