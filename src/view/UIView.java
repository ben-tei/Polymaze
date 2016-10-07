package view;

import java.awt.Dimension;

import javax.swing.JFrame;

public class UIView extends JFrame
{
	private static final long serialVersionUID = 1L;

	public UIView()
	{
		this.setTitle("Polymaze - Welcome");

		this.setContentPane(new UILogin());

		this.getContentPane().setPreferredSize(new Dimension(800, 600));
		this.getContentPane().setLayout(null);
		this.pack();

		this.setLocationRelativeTo(null);

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.setResizable(false);

		this.setVisible(true);
	}

}
