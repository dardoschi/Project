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
import javax.swing.JRadioButton;

public class RegisterNewUserFrame extends JFrame {

	private JPanel contentPane;
	private Controller ctrl;
	private JTextField NUsernameTextField;
	private JTextField NPasswordTextField;
	private JRadioButton AdminRdBtn;


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
		Titlepanel.setBounds(5, 5, 839, 88);
		Titlepanel.setBackground(Color.WHITE);
		
		JPanel MainPanel = new JPanel();
		MainPanel.setBounds(5, 99, 839, 470);
		MainPanel.setBackground(Color.WHITE);
		
		JLabel InsertUsernameLabel = new JLabel("Insert Username");
		InsertUsernameLabel.setBounds(112, 94, 291, 55);
		InsertUsernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JLabel InsertPasswordLabel = new JLabel("Insert Password");
		InsertPasswordLabel.setBounds(112, 179, 291, 38);
		InsertPasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		NUsernameTextField = new JTextField();
		NUsernameTextField.setBounds(407, 105, 288, 33);
		NUsernameTextField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		NUsernameTextField.setColumns(10);
		
		NPasswordTextField = new JTextField();
		NPasswordTextField.setBounds(407, 182, 292, 33);
		NPasswordTextField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		NPasswordTextField.setColumns(10);
//		cancel button
		JButton CancelButton = new JButton("Cancel");
		CancelButton.setBounds(112, 357, 249, 78);
		CancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.LoginFrameOpen();
			}
		});
		CancelButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
//		confirm button
		JButton ConfirmButton = new JButton("Confirm");
		ConfirmButton.setBounds(486, 357, 213, 78);
		ConfirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register();
			}
		});
		ConfirmButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		contentPane.setLayout(null);
		
		JLabel TitleLabel = new JLabel("Register New User");
		TitleLabel.setBounds(211, 11, 433, 49);
		TitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		TitleLabel.setBackground(Color.WHITE);
		TitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		contentPane.add(Titlepanel);
		Titlepanel.setLayout(null);
		Titlepanel.add(TitleLabel);
		contentPane.add(MainPanel);
		MainPanel.setLayout(null);
		MainPanel.add(InsertUsernameLabel);
		MainPanel.add(InsertPasswordLabel);
		MainPanel.add(CancelButton);
		MainPanel.add(ConfirmButton);
		MainPanel.add(NUsernameTextField);
		MainPanel.add(NPasswordTextField);
		
		AdminRdBtn = new JRadioButton("Admin");
		AdminRdBtn.setBackground(Color.WHITE);
		AdminRdBtn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		AdminRdBtn.setBounds(410, 250, 289, 38);
		MainPanel.add(AdminRdBtn);
		
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
				ctrl.RegisterUser(NUsernameTextField.getText(),NPasswordTextField.getText(),AdminRdBtn.isSelected());
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
