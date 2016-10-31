package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.business.Maze;

public class UIMyMazes extends JPanel implements ActionListener
{

	private static final long serialVersionUID = 1L;

	private UIView myUIView;
	private MyTabbedPane myTabs;

	private JButton generateBtn;
	private JTable table;
	private JScrollPane scrollPane;

	private List<Maze> myMazes;

	public UIMyMazes(UIView uiView, MyTabbedPane tabs)
	{
		this.myUIView = uiView;
		this.myTabs = tabs;

		String[] titles = { "Name", "", "" };

		this.myMazes = this.myUIView.getUIController().getMazeManager().getCreatorMazesList();

		List<String[]> data = new ArrayList<String[]>();

		for(int i = 0; i < myMazes.size(); i++)
		{
			data.add(new String[] { myMazes.get(i).getName(), "See", "Delete" });
		}

		TableModel model = new DefaultTableModel(data.toArray(new Object[][] {}), titles);

		this.table = new JTable(model);
		this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.table.setRowHeight(40);

		this.table.getColumnModel().getColumn(0).setPreferredWidth(400);
		this.table.getColumnModel().getColumn(1).setPreferredWidth(149);
		this.table.getColumnModel().getColumn(2).setPreferredWidth(148);

		this.scrollPane = new JScrollPane(this.table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.scrollPane.setBounds(30, 50, 720, 300);
		this.add(this.scrollPane);

		this.generateBtn = new JButton("Generate new maze");
		this.generateBtn.setBounds(325, 450, 150, 25);
		this.generateBtn.addActionListener(this);
		this.generateBtn.setActionCommand("generate");
		this.generateBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.add(this.generateBtn);

		Action delete = new AbstractAction()
		{

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e)
			{
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				((DefaultTableModel) table.getModel()).removeRow(modelRow);
				myUIView.getUIController().getMazeManager().deleteMaze(myMazes.get(modelRow).getId());
				myTabs.updateTab(1, new UIAllMazes(myUIView, myTabs));
			}
		};

		Action see = new AbstractAction()
		{

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e)
			{
				int modelRow = Integer.valueOf(e.getActionCommand());

				myTabs.updateTab(0, new UITheMaze(myUIView, myTabs, myMazes.get(modelRow)));
			}
		};

		ButtonColumn seeButton = new ButtonColumn(this.table, see, 1);
		seeButton.setMnemonic(KeyEvent.VK_D);

		ButtonColumn deleteButton = new ButtonColumn(this.table, delete, 2);
		deleteButton.setMnemonic(KeyEvent.VK_D);

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

}
