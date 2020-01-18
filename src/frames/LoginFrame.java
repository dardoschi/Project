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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.ImageIcon;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField UsernameTextField;
	private JPasswordField PasswordField;
	private Controller ctrl;
	private RegisterNewUserFrame regFrame;

	/**
	 * Create the frame.
	 */
	public LoginFrame(Controller control) {
		ctrl = control;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 802, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
//		setResizable(false);
		//Frame becomes visible at the center of the screen
		setLocationRelativeTo(null);
		
		JPanel TitlePanel = new JPanel();
		TitlePanel.setBackground(Color.WHITE);
		
		JLabel LoginTitleLable = new JLabel("Login Page");
		LoginTitleLable.setToolTipText("");
		LoginTitleLable.setForeground(Color.BLACK);
		LoginTitleLable.setBackground(Color.WHITE);
		LoginTitleLable.setHorizontalAlignment(SwingConstants.CENTER);
		LoginTitleLable.setFont(new Font("Tahoma", Font.PLAIN, 54));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		JLabel UsernameLabel = new JLabel("Username");
		UsernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		
		JLabel PasswordLabel = new JLabel("Password");
		PasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		
		UsernameTextField = new JTextField();
		UsernameTextField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		UsernameTextField.setColumns(10);
		
		PasswordField = new JPasswordField();
		PasswordField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JButton LoginButton = new JButton("Login");
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CheckValidProps();
			}
		});
		LoginButton.setFont(new Font("Tahoma", Font.PLAIN, 28));
		
		JButton RegisterButton = new JButton("Register \r\nnew user");
		RegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.RegisterUserOpen();
			}
		});
		RegisterButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(110)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(UsernameLabel, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
							.addGap(41)
							.addComponent(UsernameTextField, GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(PasswordLabel, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
							.addGap(41)
							.addComponent(PasswordField, GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(RegisterButton, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
							.addGap(99)
							.addComponent(LoginButton, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)))
					.addGap(132))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(63)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(UsernameLabel, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
							.addGap(6))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(8)
							.addComponent(UsernameTextField)))
					.addGap(16)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(PasswordLabel, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(PasswordField)
							.addGap(2)))
					.addGap(30)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(2)
							.addComponent(RegisterButton, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(LoginButton, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
							.addGap(1)))
					.addGap(8))
		);
		panel.setLayout(gl_panel);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(TitlePanel, GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE))
					.addGap(6))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(105)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(TitlePanel, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
					.addGap(245))
		);
		GroupLayout gl_TitlePanel = new GroupLayout(TitlePanel);
		gl_TitlePanel.setHorizontalGroup(
			gl_TitlePanel.createParallelGroup(Alignment.LEADING)
				.addComponent(LoginTitleLable, GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE)
		);
		gl_TitlePanel.setVerticalGroup(
			gl_TitlePanel.createParallelGroup(Alignment.LEADING)
				.addComponent(LoginTitleLable, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
		);
		TitlePanel.setLayout(gl_TitlePanel);
		contentPane.setLayout(gl_contentPane);
		
		
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
