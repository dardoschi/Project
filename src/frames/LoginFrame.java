package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import main.Controller;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField UsernameTextField;
	private JPasswordField PasswordField;
	private Controller ctrl;
	private RegisterNewUserFrame regFrame;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					LoginFrame frame = new LoginFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public LoginFrame(Controller control) {
		ctrl = control;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 802, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		JPanel TitlePanel = new JPanel();
		TitlePanel.setBounds(10, 0, 767, 108);
		contentPane.add(TitlePanel);
		TitlePanel.setLayout(new BorderLayout(0, 0));
		
		JLabel LoginTitleLable = new JLabel("Login Page");
		LoginTitleLable.setHorizontalAlignment(SwingConstants.CENTER);
		LoginTitleLable.setFont(new Font("Tahoma", Font.PLAIN, 54));
		TitlePanel.add(LoginTitleLable, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 105, 767, 249);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel UsernameLabel = new JLabel("Username");
		UsernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		UsernameLabel.setBounds(110, 63, 161, 37);
		panel.add(UsernameLabel);
		
		JLabel PasswordLabel = new JLabel("Password");
		PasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		PasswordLabel.setBounds(110, 122, 161, 37);
		panel.add(PasswordLabel);
		
		UsernameTextField = new JTextField();
		UsernameTextField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		UsernameTextField.setBounds(312, 71, 323, 35);
		panel.add(UsernameTextField);
		UsernameTextField.setColumns(10);
		
		PasswordField = new JPasswordField();
		PasswordField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		PasswordField.setBounds(312, 122, 323, 35);
		panel.add(PasswordField);
		
		JButton LoginButton = new JButton("Login");
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CheckValidProps();
			}
		});
		LoginButton.setBounds(422, 189, 213, 50);
		panel.add(LoginButton);
		LoginButton.setFont(new Font("Tahoma", Font.PLAIN, 28));
		
		JButton RegisterButton = new JButton("Register \r\nnew user");
		RegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.RegisterUserOpen();
			}
		});
		RegisterButton.setBounds(110, 191, 213, 50);
		panel.add(RegisterButton);
		RegisterButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		
	}
	

	
	//checks if the username and password fields are empty
	private void CheckValidProps() {
		if((UsernameTextField.getText().length()<=0 || PasswordField.getText().length()<=0)){
			JOptionPane.showMessageDialog(new JFrame(), "Please insert valid Username and Password","WRONG VALUES", JOptionPane.ERROR_MESSAGE);
			UsernameTextField.setText("");
			PasswordField.setText("");
		}else
			if((UsernameTextField.getText().length()>0 || PasswordField.getText().length()>0)) {
				ctrl.LoginCheck(UsernameTextField.getText(),PasswordField.getText());
			}
	}
	
	
	//user not registered
	public void UnregisteredUser() {
		JOptionPane.showMessageDialog(new JFrame(), "User not registered","", JOptionPane.ERROR_MESSAGE);
		UsernameTextField.setText("");
		PasswordField.setText("");
	}
}
