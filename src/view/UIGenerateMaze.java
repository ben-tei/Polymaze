package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import model.MazeManager;
import model.business.Person;
import model.factory.MazeFactoryStrategyName;
import util.exception.PolymazeException;

public class UIGenerateMaze extends JPanel implements ActionListener
{

	private static final long serialVersionUID = 1L;

	private UIView myUIView;
	private MyTabbedPane myTabs;

	private JLabel generateLbl;

	private JLabel nameLbl;

	private JLabel lengthLbl;

	private JLabel witdhLbl;

	private JLabel strategyLbl;

	private JTextField nameTextField;

	private JSpinner lengthSpinner;

	private JSpinner widthSpinner;

	private JComboBox<String> comboBox;

	private JButton backBtn;

	private JButton generateBtn;

	public UIGenerateMaze(UIView uiView, MyTabbedPane tabs)
	{
		this.myUIView = uiView;
		this.myTabs = tabs;
		this.myUIView.setTitle("Polymaze - Generate Maze");

		// Labels

		this.generateLbl = new JLabel("Generate New Maze");
		this.generateLbl.setBounds(300, 10, 200, 25);
		this.generateLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.add(generateLbl);

		this.nameLbl = new JLabel("Name");
		this.nameLbl.setBounds(200, 100, 100, 25);
		this.nameLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(nameLbl);

		this.lengthLbl = new JLabel("Length");
		this.lengthLbl.setBounds(200, 175, 100, 25);
		this.lengthLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lengthLbl);

		this.witdhLbl = new JLabel("Width");
		this.witdhLbl.setBounds(200, 250, 100, 25);
		this.witdhLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(witdhLbl);

		this.strategyLbl = new JLabel("Strategy");
		this.strategyLbl.setBounds(200, 325, 100, 25);
		this.strategyLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(strategyLbl);

		// Textfield - Maze's name

		this.nameTextField = new JTextField();
		this.nameTextField.setBounds(this.nameLbl.getX() + 200, this.nameLbl.getY(), 210, 25);
		this.add(this.nameTextField);
		this.nameTextField.setColumns(10);

		// Spinner - Width and Height

		SpinnerModel spinnerModel = new SpinnerNumberModel(3, 3, 100, 1);
		SpinnerModel spinnerModel2 = new SpinnerNumberModel(3, 3, 100, 1);
		// I add a new spinner to avoid edition of both fields.

		this.lengthSpinner = new JSpinner(spinnerModel);
		this.lengthSpinner.setBounds(this.lengthLbl.getX() + 200, this.lengthLbl.getY(), 210, 25);
		this.add(this.lengthSpinner);

		this.widthSpinner = new JSpinner(spinnerModel2);
		this.widthSpinner.setBounds(this.witdhLbl.getX() + 200, this.witdhLbl.getY(), 210, 25);
		this.add(this.widthSpinner);

		// ComboBox - algorithm box

		String[] elements = { "Backtrack", "Eller's Algorithm", "Kruskal's Algorithm", "Prim's Algorithm" };

		this.comboBox = new JComboBox<String>(elements);
		this.comboBox.setBounds(this.strategyLbl.getX() + 200, this.strategyLbl.getY(), 210, 25);
		this.add(this.comboBox);

		// Button - Generate and Back

		this.generateBtn = new JButton("Generate");
		this.generateBtn.setBounds(this.nameTextField.getX() + this.nameTextField.getWidth() - 100,
				this.strategyLbl.getY() + 100, 100, 25);
		this.generateBtn.addActionListener(this);
		this.generateBtn.setActionCommand("generate");
		this.generateBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.add(this.generateBtn);

		this.backBtn = new JButton("Back");
		this.backBtn.setBounds(this.nameLbl.getX(), this.strategyLbl.getY() + 100, 100, 25);
		this.backBtn.addActionListener(this);
		this.backBtn.setActionCommand("back");
		this.backBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.add(this.backBtn);

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String cmd = e.getActionCommand();

		if(cmd.equals("generate"))
		{
			Person creator = this.myUIView.getUIController().getUserManager().getCurrentPerson();
			String name = this.nameTextField.getText();
			int length = (int) this.lengthSpinner.getValue();
			int width = (int) this.widthSpinner.getValue();
			String strategy = this.comboBox.getSelectedItem().toString().replaceAll("'s Algorithm", "");

			if(!name.isEmpty() && length > 2 && width > 2)
			{

				try
				{
					MazeManager mm = this.myUIView.getUIController().getMazeManager();

					mm.getMazeFactory().setStrategy(MazeFactoryStrategyName.valueOf(strategy));

					mm.generateMaze(name, length, width, creator);

					this.myTabs.updateTab(0, new UIMyMazes(this.myUIView, this.myTabs));

					this.myTabs.updateTab(1, new UIAllMazes(this.myUIView, this.myTabs));
				}
				catch(PolymazeException e1)
				{
					// TODO Auto-generated catch block

					JOptionPane.showMessageDialog(null, "The Maze \"" + name + "\" already exists !", "Failure",
							JOptionPane.ERROR_MESSAGE);

				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Some fields are incorrect !", "Failure",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(cmd.equals("back"))
		{
			this.myTabs.updateTab(0, new UIMyMazes(this.myUIView, this.myTabs));
		}

	}

}
