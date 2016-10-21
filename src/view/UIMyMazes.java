package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.business.Maze;

public class UIMyMazes extends JPanel implements KeyListener, ActionListener
{

	private static final long serialVersionUID = 1L;

	private UIView myUIView;
	private MyTabbedPane myTabs;

	private JButton generateBtn;
	private JTable table;
	private JScrollPane scrollPane;

	public UIMyMazes(UIView uiView, MyTabbedPane tabs)
	{
		this.myUIView = uiView;
		this.myTabs = tabs;

		String[] titles = { "Name", "", "" };

		this.myUIView.getUIController().getMazeManager()
				.setMazesByCreator(this.myUIView.getUIController().getUserManager().getCurrentPerson());

		List<Maze> myMazes = this.myUIView.getUIController().getMazeManager().getMazeList();

		List<String[]> data = new ArrayList<String[]>();

		for(int i = 0; i < myMazes.size(); i++)
		{
			data.add(new String[] { myMazes.get(i).getName() });
		}

		TableModel model = new DefaultTableModel(data.toArray(new Object[][] {}), titles)
		{

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};

		this.table = new JTable(model);
		this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.table.setRowHeight(40);

		this.table.getColumnModel().getColumn(0).setPreferredWidth(400);
		this.table.getColumnModel().getColumn(1).setPreferredWidth(149);
		this.table.getColumnModel().getColumn(2).setPreferredWidth(148);

		this.scrollPane = new JScrollPane(this.table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.scrollPane.setBounds(50, 50, 700, 250);
		this.add(this.scrollPane);

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
