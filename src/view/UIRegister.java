package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UIRegister extends JPanel implements KeyListener, ActionListener
{

	private static final long serialVersionUID = 1L;

	private JLabel connectionLbl;

	private JLabel loginLbl;

	private JLabel passwordLbl;

	private JLabel confirmPasswordLbl;

	private JPasswordField passwordTextField;

	private JPasswordField confirmPasswordTextField;

	private JTextField loginTextField;

	private JButton backBtn;

	private JButton registerBtn;

	private UIView myUIView;

	public UIRegister(UIView uiView)
	{
		this.myUIView = uiView;
		this.myUIView.setTitle("Polymaze - Register");

		this.connectionLbl = new JLabel("Register");
		connectionLbl.setBounds(10, 10, 100, 25);
		this.connectionLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.add(connectionLbl);

		this.loginLbl = new JLabel("Login");
		loginLbl.setBounds(200, 150, 100, 25);
		this.loginLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(loginLbl);

		this.passwordLbl = new JLabel("Password");
		passwordLbl.setBounds(200, 250, 100, 25);
		this.passwordLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(passwordLbl);

		this.confirmPasswordLbl = new JLabel("Confirm Password");
		confirmPasswordLbl.setBounds(200, 350, 150, 25);
		this.confirmPasswordLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(confirmPasswordLbl);

		this.loginTextField = new JTextField();
		this.loginTextField.setBounds(this.loginLbl.getX() + 200, this.loginLbl.getY(), 210, 30);
		this.add(this.loginTextField);
		this.loginTextField.addKeyListener(this);
		this.loginTextField.setColumns(10);

		this.passwordTextField = new JPasswordField();
		this.passwordTextField.setBounds(this.passwordLbl.getX() + 200, this.passwordLbl.getY(), 210, 30);
		this.passwordTextField.addKeyListener(this);
		this.add(this.passwordTextField);
		this.passwordTextField.setColumns(10);

		this.confirmPasswordTextField = new JPasswordField();
		this.confirmPasswordTextField.setBounds(this.confirmPasswordLbl.getX() + 200, this.confirmPasswordLbl.getY(),
				210, 30);
		this.confirmPasswordTextField.addKeyListener(this);
		this.add(this.confirmPasswordTextField);
		this.confirmPasswordTextField.setColumns(10);

		this.registerBtn = new JButton("Register");
		this.registerBtn.setBounds(this.loginTextField.getX() + this.loginTextField.getWidth() - 100,
				this.confirmPasswordLbl.getY() + 100, 100, 25);
		this.registerBtn.addActionListener(this);
		this.registerBtn.setActionCommand("register");
		this.registerBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.add(this.registerBtn);

		this.backBtn = new JButton("Back");
		this.backBtn.setBounds(this.loginLbl.getX(), this.confirmPasswordLbl.getY() + 100, 100, 25);
		this.backBtn.addActionListener(this);
		this.backBtn.setActionCommand("back");
		this.backBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.add(this.backBtn);
	}

	@Override
	public void keyPressed(KeyEvent arg0)
	{
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
		{
			this.registerBtn.doClick();
		}

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

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String cmd = arg0.getActionCommand();

		if(cmd.equals("register"))
		{

		}
		else if(cmd.equals("back"))
		{
			this.myUIView.updatePanel(new UILogin(this.myUIView));
		}
	}

}