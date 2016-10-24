package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.business.Maze;

public class UIAllMazes extends JPanel implements ActionListener
{

	private static final long serialVersionUID = 1L;

	private UIView myUIView;
	private MyTabbedPane myTabs;

	private JTable table;
	private JScrollPane scrollPane;

	private List<Maze> myMazes;

	public UIAllMazes(UIView uiView, MyTabbedPane tabs)
	{
		this.myUIView = uiView;
		this.myTabs = tabs;

		String[] titles = { "Name", "Creator", "" };

		this.myUIView.getUIController().getMazeManager().setAllMazes();

		this.myMazes = this.myUIView.getUIController().getMazeManager().getMazeList();

		List<String[]> data = new ArrayList<String[]>();

		for(int i = 0; i < myMazes.size(); i++)
		{
			data.add(new String[] { myMazes.get(i).getName(), myMazes.get(i).getCreator().getName(), "See" });
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
		this.scrollPane.setBounds(50, 50, 700, 250);
		this.add(this.scrollPane);

		Action see = new AbstractAction()
		{

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e)
			{
				int modelRow = Integer.valueOf(e.getActionCommand());

				myTabs.updateTab(1, new UITheMaze(myUIView, myTabs, myMazes.get(modelRow)));
			}
		};

		ButtonColumn seeButton = new ButtonColumn(this.table, see, 2);
		seeButton.setMnemonic(KeyEvent.VK_D);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub

	}

}
