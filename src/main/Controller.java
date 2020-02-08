package main;

import java.awt.Component;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dao.ConnectionFactory;
import frames.AddNewItemFrame;
import frames.ItemTableModel;
import frames.LoginFrame;
import frames.RegisterNewUserFrame;
import frames.MainFrame;
import dao.ItemDao;
import dao.EmployeesDao;

public class Controller {
	
	private ConnectionFactory conn;
	private ItemDao IDao;
	private EmployeesDao EDao;
	private LoginFrame LoginFrame;
	private MainFrame MFrame;
	private RegisterNewUserFrame RegisterFrame;
	public AddNewItemFrame AddFrame;
//	public JTable ctrlItemTable = new JTable();   OLD IMPLEMENTATION
	public ArrayList<Item> Warehouse;
	

	
	
	//Constructor
	public Controller(){
		Warehouse = new ArrayList<Item>();
		conn = new ConnectionFactory(this);
		IDao = new ItemDao(this);
		EDao = new EmployeesDao(this);
//		CModel = new ItemTableModel(this);
		LoginFrame = new LoginFrame(this);
		LoadWarehouseArray();
		MFrame = new MainFrame(this);
		RegisterFrame = new RegisterNewUserFrame(this);
		AddFrame = new AddNewItemFrame(this);
//		LoginFrame.setVisible(true);
		MFrame.setVisible(true);
	}
	
	
	//FUNCTIONS FOR ARRAYLIST
	public void add(Item i) {
		Warehouse.add(i);
	}
	public ArrayList<Item>getWarehouse(){
		return Warehouse;
	}
	public ArrayList<Item>deleteWarehouseArray(){
		Warehouse.clear();
		return Warehouse;
	}
	//retrieves data from database (data will be loaded into table in mainframe)
	public void LoadWarehouseArray() {
		IDao.getWarehousefromDB();
	}
	public ArrayList<Item> reloadWarehouseArray(){
		deleteWarehouseArray();
		LoadWarehouseArray();
		return Warehouse;
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
	
//	//loads the MainFrame JTable  OLD IMPLEMENTATION
//	public JTable LoadTable() {
////		ctrlItemTable = IDao.LoadTable();
//		IDao.LoadTable();
//		return ctrlItemTable;
//	}
	
	
	//add new item
	public void AddNewItem(int Id, String Size, double Price, String Type, int InStock, String Colour) {
		IDao.AddNewItemToDB(Id, Size, Price, Type, InStock, Colour);
		//since the table data is based on the arraylist, it need to be emptied and then refilled
		reloadWarehouseArray();
		//reloads table
		MFrame.TModel.fireTableDataChanged();
		
		}

	//check if an item id already exists
	public boolean CheckItemId(int Id){
		if((IDao.CheckItemId(Id))==true) {
			return true;
		}
		else return false;
	}	
}
