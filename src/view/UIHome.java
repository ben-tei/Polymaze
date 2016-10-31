package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class UIHome extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;

	private UIView myUIView;

	private JLabel homeLbl;

	private MyTabbedPane tabs;

	private UIMyMazes myLabyrinths;

	private UIAllMazes allLabyrinths;

	public UIHome(UIView uiView)
	{
		this.myUIView = uiView;
		this.myUIView.setTitle("Polymaze");

		this.homeLbl = new JLabel("Polymaze");
		this.homeLbl.setBounds(350, 10, 100, 25);
		this.homeLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.add(homeLbl);

		this.myUIView.getUIController().getMazeManager()
				.setMazesByCreator(this.myUIView.getUIController().getUserManager().getCurrentPerson());

		this.myUIView.getUIController().getMazeManager().setAllMazes();

		this.tabs = new MyTabbedPane();
		this.myLabyrinths = new UIMyMazes(uiView, this.tabs);
		this.allLabyrinths = new UIAllMazes(uiView, this.tabs);

		this.myLabyrinths.setLayout(null);
		this.allLabyrinths.setLayout(null);

		this.tabs.add("My Mazes", this.myLabyrinths);
		this.tabs.add("All Mazes", this.allLabyrinths);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		this.tabs.setBounds(0, this.homeLbl.getY() + 50, (int) screenSize.getWidth(), (int) screenSize.getHeight());

		this.add(this.tabs);
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub

	}

}
