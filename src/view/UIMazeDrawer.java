package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class UIMazeDrawer extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private UIView myUIView;
	
	public UIMazeDrawer(UIView uiView){
		this.myUIView = uiView;
		this.myUIView.setTitle("Polymaze - Maze Generator");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
