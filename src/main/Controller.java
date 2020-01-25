package main;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import dao.ConnectionFactory;
import frames.AddNewItemFrame;
import frames.LoginFrame;
import frames.RegisterNewUserFrame;
import net.proteanit.sql.DbUtils;
import frames.MainFrame;
import dao.ItemDao;
import dao.EmployeesDao;

public class Controller {
	
	private ConnectionFactory conn;
	private ItemDao IDao;
	private EmployeesDao EDao;
	private LoginFrame LoginFrame;
	public MainFrame MFrame;
	private RegisterNewUserFrame RegisterFrame;
	public AddNewItemFrame AddFrame;
	public JTable ctrlItemTable = new JTable();
;
	
	
	//Constructor
	public Controller(){
		conn = new ConnectionFactory(this);
		IDao = new ItemDao(this);
		EDao = new EmployeesDao(this);
		LoginFrame = new LoginFrame(this);
		MFrame = new MainFrame(this);
		RegisterFrame = new RegisterNewUserFrame(this);
		AddFrame = new AddNewItemFrame(this);
//		LoginFrame.setVisible(true);
		MFrame.setVisible(true);
	}
	
		
	
	//open registernewuser frame from login frame
	public void RegisterFrameOpen() {
		RegisterFrame.setVisible(true);	
		LoginFrame.setVisible(false);
	}
	
	
	//open login frame from register frame
	public void LoginFrameOpen() {
		RegisterFrame.setVisible(false);	
		LoginFrame.setVisible(true);
	}
	
    // open AddNewItemFrame
	public void AddNewItemFrameOpen() {
		AddFrame.setVisible(true);
	}
	
	
	
	//login check
	public void LoginCheck(String Username, String Password) {
		if(EDao.CheckLoginProps(Username, Password)==true){
				MFrame.setVisible(true);
				LoginFrame.setVisible(false);
		}else {
			LoginFrame.UnregisteredUser();
		}

	}
	
	//register a new user
	public void RegisterUser(String Username, String Password) {
		if(EDao.RegisterNewUser(Username, Password)==true){
			RegisterFrame.UserHasBeenRegistered();
			RegisterFrame.setVisible(false);
			LoginFrame.setVisible(true);
		}else {
			if (EDao.CheckLoginProps(Username, Password)==true)
				RegisterFrame.UserAlreadyRegistered();
		}						
	}
	
	//loads the MainFrame JTable
	public JTable LoadTable() {
		ctrlItemTable = IDao.LoadTable();
		return ctrlItemTable;
	}
	
	
	//add new item
	public void AddNewItem(int Id, String Size, double Price, String Type, int InStock, String Colour) {
		IDao.AddNewItem(Id, Size, Price, Type, InStock, Colour);
		LoadTable();
	}
	
	//check if an item id already exists
	public boolean CheckItemId(int Id){
		if((IDao.CheckItemId(Id))==true) {
			return true;
		}
		else return false;
	}




	
	
}
