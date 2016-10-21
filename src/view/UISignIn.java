package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UISignIn extends JPanel implements KeyListener, ActionListener
{

	private static final long serialVersionUID = 1L;

	private JLabel connectionLbl;

	private JLabel loginLbl;

	private JLabel passwordLbl;

	private JPasswordField passwordTextField;

	private JTextField loginTextField;

	private JButton loginBtn;

	private JButton registerBtn;

	private UIView myUIView;

	public UISignIn(UIView uiView)
	{
		this.myUIView = uiView;
		this.myUIView.setTitle("Polymaze - Sign In");

		this.connectionLbl = new JLabel("Connection");
		this.connectionLbl.setBounds(10, 10, 100, 25);
		this.connectionLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.add(connectionLbl);

		this.loginLbl = new JLabel("Login");
		this.loginLbl.setBounds(200, 150, 100, 25);
		this.loginLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(loginLbl);

		this.passwordLbl = new JLabel("Password");
		this.passwordLbl.setBounds(200, 250, 100, 25);
		this.passwordLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(passwordLbl);

		this.loginTextField = new JTextField();
		this.loginTextField.setBounds(this.loginLbl.getX() + 200, this.loginLbl.getY(), 210, 25);
		this.add(this.loginTextField);
		this.loginTextField.addKeyListener(this);
		this.loginTextField.setColumns(10);

		this.passwordTextField = new JPasswordField();
		this.passwordTextField.setBounds(this.passwordLbl.getX() + 200, this.passwordLbl.getY(), 210, 25);
		this.passwordTextField.addKeyListener(this);
		this.add(this.passwordTextField);
		this.passwordTextField.setColumns(10);

		this.loginBtn = new JButton("Login");
		this.loginBtn.setBounds(this.loginTextField.getX() + this.loginTextField.getWidth() - 100,
				this.passwordLbl.getY() + 100, 100, 25);
		this.loginBtn.addActionListener(this);
		this.loginBtn.setActionCommand("login");
		this.loginBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.add(this.loginBtn);

		this.registerBtn = new JButton("Register");
		this.registerBtn.setBounds(this.loginLbl.getX(), this.passwordLbl.getY() + 100, 100, 25);
		this.registerBtn.addActionListener(this);
		this.registerBtn.setActionCommand("register");
		this.registerBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
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
			if(this.loginTextField.getText().length() > 0 && this.passwordTextField.getPassword().length > 0)
			{
				if(this.myUIView.getUIController().signIn(this.loginTextField.getText(),
						new String(this.passwordTextField.getPassword())))
				{
					JOptionPane.showMessageDialog(null, "Welcome on Polymaze, " + this.loginTextField.getText() + " !",
							"Success", JOptionPane.INFORMATION_MESSAGE);

					this.myUIView.updatePanel(new UIHome(this.myUIView));

				}
				else
				{
					JOptionPane.showMessageDialog(null, "Invalid username or password", "Failed to login",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		}
		else if(cmd.equals("register"))
		{
			this.myUIView.updatePanel(new UISignUp(this.myUIView));
		}
	}

}
