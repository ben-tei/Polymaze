package view;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MyTabbedPane extends JTabbedPane
{

	private static final long serialVersionUID = 1L;

	public MyTabbedPane()
	{
		super();
	}

	public void updateTab(int index, JPanel jpanel)
	{
		jpanel.setLayout(null);
		this.setComponentAt(index, jpanel);

		this.revalidate();
		this.repaint();

	}
}
