package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UILogin extends JPanel implements KeyListener, ActionListener
{

	private static final long serialVersionUID = 1L;

	private final JPasswordField passwordTextField;

	private final JTextField loginTextField;

	private final JButton loginBtn;

	private final JButton registerBtn;

	private UIView myUIView;

	public UILogin(UIView uiView)
	{
		this.myUIView = uiView;
		this.myUIView.setTitle("Polymaze - Login");

		this.loginTextField = new JTextField();
		this.loginTextField.setBounds(120, 10, 210, 30);
		this.add(this.loginTextField);
		this.loginTextField.addKeyListener(this);
		this.loginTextField.setColumns(10);

		this.passwordTextField = new JPasswordField();
		this.passwordTextField.setBounds(120, 50, 210, 30);
		this.passwordTextField.addKeyListener(this);
		this.add(this.passwordTextField);
		this.passwordTextField.setColumns(10);

		this.loginBtn = new JButton("Login");
		this.loginBtn.setBounds(120, 90, 100, 25);
		this.loginBtn.addActionListener(this);
		this.loginBtn.setActionCommand("login");
		this.add(this.loginBtn);

		this.registerBtn = new JButton("Register");
		this.registerBtn.setBounds(230, 90, 100, 25);
		this.registerBtn.addActionListener(this);
		this.registerBtn.setActionCommand("register");
		this.add(this.registerBtn);
	}

	@Override
	public void keyPressed(KeyEvent arg0)
	{
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
		{
			this.loginBtn.doClick();
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

		if(cmd.equals("login"))
		{

		}
		else if(cmd.equals("register"))
		{
			this.myUIView.updatePanel(new UIRegister(this.myUIView));
		}
	}

}
