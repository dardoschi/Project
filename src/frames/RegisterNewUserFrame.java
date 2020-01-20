package frames;

import java.awt.BorderLayout;
import main.Controller;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterNewUserFrame extends JFrame {

	private JPanel contentPane;
	private Controller ctrl;
	private JTextField NUsernameTextField;
	private JTextField NPasswordTextField;


	/**
	 * Create the frame.
	 */
	public RegisterNewUserFrame(Controller control) {
		setFont(new Font("Tahoma", Font.PLAIN, 24));
		ctrl = control;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 863, 611);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel Titlepanel = new JPanel();
		Titlepanel.setBackground(Color.WHITE);
		
		JPanel MainPanel = new JPanel();
		MainPanel.setBackground(Color.WHITE);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(Titlepanel, GroupLayout.DEFAULT_SIZE, 847, Short.MAX_VALUE)
						.addComponent(MainPanel, GroupLayout.DEFAULT_SIZE, 847, Short.MAX_VALUE))
					.addGap(0))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(Titlepanel, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(MainPanel, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE))
		);
		
		JLabel InsertUsernameLabel = new JLabel("Insert Username");
		InsertUsernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JLabel InsertPasswordLabel = new JLabel("Insert Password");
		InsertPasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		NUsernameTextField = new JTextField();
		NUsernameTextField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		NUsernameTextField.setColumns(10);
		
		NPasswordTextField = new JTextField();
		NPasswordTextField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		NPasswordTextField.setColumns(10);
//		cancel button
		JButton CancelButton = new JButton("Cancel");
		CancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.LoginFrameOpen();
			}
		});
		CancelButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
//		confirm button
		JButton ConfirmButton = new JButton("Confirm");
		ConfirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register();
			}
		});
		ConfirmButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GroupLayout gl_MainPanel = new GroupLayout(MainPanel);
		gl_MainPanel.setHorizontalGroup(
			gl_MainPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_MainPanel.createSequentialGroup()
					.addGap(112)
					.addGroup(gl_MainPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(InsertUsernameLabel, GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
						.addComponent(InsertPasswordLabel, GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
						.addGroup(gl_MainPanel.createSequentialGroup()
							.addComponent(CancelButton, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
							.addGap(42)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_MainPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_MainPanel.createSequentialGroup()
							.addGap(79)
							.addComponent(ConfirmButton, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
						.addGroup(gl_MainPanel.createSequentialGroup()
							.addComponent(NUsernameTextField, GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
							.addGap(4))
						.addComponent(NPasswordTextField))
					.addGap(140))
		);
		gl_MainPanel.setVerticalGroup(
			gl_MainPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_MainPanel.createSequentialGroup()
					.addGap(94)
					.addGroup(gl_MainPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(InsertUsernameLabel, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
						.addComponent(NUsernameTextField, GroupLayout.PREFERRED_SIZE, 33, Short.MAX_VALUE))
					.addGap(82)
					.addGroup(gl_MainPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(InsertPasswordLabel, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
						.addComponent(NPasswordTextField, GroupLayout.PREFERRED_SIZE, 33, Short.MAX_VALUE))
					.addGap(88)
					.addGroup(gl_MainPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(ConfirmButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(CancelButton, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
					.addContainerGap(63, Short.MAX_VALUE))
		);
		MainPanel.setLayout(gl_MainPanel);
		
		JLabel TitleLabel = new JLabel("Register New User");
		TitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		TitleLabel.setBackground(Color.WHITE);
		TitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		GroupLayout gl_Titlepanel = new GroupLayout(Titlepanel);
		gl_Titlepanel.setHorizontalGroup(
			gl_Titlepanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_Titlepanel.createSequentialGroup()
					.addGap(211)
					.addComponent(TitleLabel, GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
					.addGap(195))
		);
		gl_Titlepanel.setVerticalGroup(
			gl_Titlepanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Titlepanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(TitleLabel, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
					.addGap(28))
		);
		Titlepanel.setLayout(gl_Titlepanel);
		contentPane.setLayout(gl_contentPane);
		
		//Frame becomes visible at the center of the screen
		setLocationRelativeTo(null);
		
		
	}
	
	//register user call
	private void Register(){
		if((NUsernameTextField.getText().length()<=0 || NPasswordTextField.getText().length()<=0)){
			JOptionPane.showMessageDialog(new JFrame(), "Please insert valid Username and Password","WRONG VALUES", JOptionPane.ERROR_MESSAGE);
			NUsernameTextField.setText("");
			NPasswordTextField.setText("");
		}else
			if((NUsernameTextField.getText().length()>0 || NPasswordTextField.getText().length()>0)) {
				ctrl.RegisterUser(NUsernameTextField.getText(),NPasswordTextField.getText());
			}
	}
	
	//user already registered
	public void UserAlreadyRegistered() {
		JOptionPane.showMessageDialog(new JFrame(), "User already registered","", JOptionPane.ERROR_MESSAGE);
		NUsernameTextField.setText("");
		NPasswordTextField.setText("");
	}
	
	//user has been registered
	public void UserHasBeenRegistered() {
		JOptionPane.showMessageDialog(new JFrame(), "User has been registered","", JOptionPane.INFORMATION_MESSAGE);
		NUsernameTextField.setText("");
		NPasswordTextField.setText("");
	}

}
