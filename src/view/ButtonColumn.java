package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractCellEditor;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 * The ButtonColumn class provides a renderer and an editor that looks like a
 * JButton. The renderer and editor will then be used for a specified column in
 * the table. The TableModel will contain the String to be displayed on the
 * button.
 *
 * The button can be invoked by a mouse click or by pressing the space bar when
 * the cell has focus. Optionally a mnemonic can be set to invoke the button.
 * When the button is invoked the provided Action is invoked. The source of the
 * Action will be the table. The action command will contain the model row
 * number of the button that was clicked.
 *
 */
public class ButtonColumn extends AbstractCellEditor
		implements TableCellRenderer, TableCellEditor, ActionListener, MouseListener
{

	private static final long serialVersionUID = 1L;

	private JTable table;
	private Action action;
	private int mnemonic;
	private Border originalBorder;
	private Border focusBorder;

	private JButton renderButton;
	private JButton editButton;
	private Object editorValue;
	private boolean isButtonColumnEditor;

	/**
	 * Create the ButtonColumn to be used as a renderer and editor. The renderer
	 * and editor will automatically be installed on the TableColumn of the
	 * specified column.
	 *
	 * @param table
	 *            the table containing the button renderer/editor
	 * @param action
	 *            the Action to be invoked when the button is invoked
	 * @param column
	 *            the column to which the button renderer/editor is added
	 */
	public ButtonColumn(JTable table, Action action, int column)
	{
		this.table = table;
		this.action = action;

		this.renderButton = new JButton();
		this.editButton = new JButton();
		this.editButton.setFocusPainted(false);
		this.editButton.addActionListener(this);
		this.originalBorder = editButton.getBorder();
		this.setFocusBorder(new LineBorder(Color.BLUE));

		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(column).setCellRenderer(this);
		columnModel.getColumn(column).setCellEditor(this);
		this.table.addMouseListener(this);
	}

	/**
	 * Get foreground color of the button when the cell has focus
	 *
	 * @return the foreground color
	 */
	public Border getFocusBorder()
	{
		return this.focusBorder;
	}

	/**
	 * The foreground color of the button when the cell has focus
	 *
	 * @param focusBorder
	 *            the foreground color
	 */
	public void setFocusBorder(Border focusBorder)
	{
		this.focusBorder = focusBorder;
		this.editButton.setBorder(focusBorder);
	}

	public int getMnemonic()
	{
		return this.mnemonic;
	}

	/**
	 * The mnemonic to activate the button when the cell has focus
	 *
	 * @param mnemonic
	 *            the mnemonic
	 */
	public void setMnemonic(int mnemonic)
	{
		this.mnemonic = mnemonic;
		this.renderButton.setMnemonic(mnemonic);
		this.editButton.setMnemonic(mnemonic);
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
	{
		if(value == null)
		{
			this.editButton.setText("");
			this.editButton.setIcon(null);
		}
		else if(value instanceof Icon)
		{
			this.editButton.setText("");
			this.editButton.setIcon((Icon) value);
		}
		else
		{
			this.editButton.setText(value.toString());
			this.editButton.setIcon(null);
		}

		this.editorValue = value;
		return this.editButton;
	}

	@Override
	public Object getCellEditorValue()
	{
		return this.editorValue;
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column)
	{
		if(isSelected)
		{
			this.renderButton.setForeground(table.getSelectionForeground());
			this.renderButton.setBackground(table.getSelectionBackground());
		}
		else
		{
			this.renderButton.setForeground(table.getForeground());
			this.renderButton.setBackground(UIManager.getColor("Button.background"));
		}

		if(hasFocus)
		{
			this.renderButton.setBorder(focusBorder);
		}
		else
		{
			this.renderButton.setBorder(originalBorder);
		}

		if(value == null)
		{
			this.renderButton.setText("");
			this.renderButton.setIcon(null);
		}
		else if(value instanceof Icon)
		{
			this.renderButton.setText("");
			this.renderButton.setIcon((Icon) value);
		}
		else
		{
			this.renderButton.setText(value.toString());
			this.renderButton.setIcon(null);
		}

		return this.renderButton;
	}

	public void actionPerformed(ActionEvent e)
	{
		int row = this.table.convertRowIndexToModel(this.table.getEditingRow());
		this.fireEditingStopped();

		//  Invoke the Action

		ActionEvent event = new ActionEvent(this.table, ActionEvent.ACTION_PERFORMED, "" + row);
		this.action.actionPerformed(event);
	}

	/*
	 * When the mouse is pressed the editor is invoked. If you then then drag
	 * the mouse to another cell before releasing it, the editor is still
	 * active. Make sure editing is stopped when the mouse is released.
	 */
	public void mousePressed(MouseEvent e)
	{
		if(this.table.isEditing() && this.table.getCellEditor() == this)
			this.isButtonColumnEditor = true;
	}

	public void mouseReleased(MouseEvent e)
	{
		if(this.isButtonColumnEditor && this.table.isEditing())
			this.table.getCellEditor().stopCellEditing();

		this.isButtonColumnEditor = false;
	}

	public void mouseClicked(MouseEvent e)
	{

	}

	public void mouseEntered(MouseEvent e)
	{

	}

	public void mouseExited(MouseEvent e)
	{

	}
}
