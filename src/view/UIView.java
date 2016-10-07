package view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class UIView extends JFrame
{
	private static final long serialVersionUID = 1L;

	public UIView()
	{

		this.updatePanel(new UILogin(this));

		this.setLocationRelativeTo(null);

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.setResizable(false);

		this.setVisible(true);
	}

	public void updatePanel(JPanel jpanel)
	{

		jpanel.setPreferredSize(new Dimension(800, 600));
		jpanel.setLayout(null);
		this.setContentPane(jpanel);

		this.pack();
		this.getContentPane().revalidate();
		this.getContentPane().repaint();

	}

}
