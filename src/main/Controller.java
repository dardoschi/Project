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

import Item.Item;
import TableModels.ItemInDBTableModel;
import dao.ConnectionFactory;
import frames.AddNewItemFrame;
import frames.CartFrame;
import frames.EditSelectedItemFrame;
import frames.LoginFrame;
import frames.MainFrameEmployee;
import frames.RegisterNewUserFrame;
import frames.MainFrameAdmin;
import dao.ItemDao;
import dao.EmployeesDao;

public class Controller {
	
	private static ConnectionFactory conn;
	private ItemDao IDao;
	private EmployeesDao EDao;
	private LoginFrame LoginFrame;
	private MainFrameAdmin MAdminFrame;
	private MainFrameEmployee MEmployeeFrame;
	private RegisterNewUserFrame RegisterFrame;
	private EditSelectedItemFrame EditFrame;
	private Item selecteditem;
	private Item CartItem;
	private CartFrame CFrame;
	public AddNewItemFrame AddFrame;
//	public JTable ctrlItemTable = new JTable();   OLD IMPLEMENTATION
	public ArrayList<Item> Warehouse;
	public ArrayList<Item> Cart;
	
	//Constructor
	public Controller(){
		Warehouse = new ArrayList<Item>();
		Cart = new ArrayList<Item>();
		conn = new ConnectionFactory(this);
		IDao = new ItemDao(this);
		EDao = new EmployeesDao(this);
		LoginFrame = new LoginFrame(this);
		LoadWarehouseArray();
		MAdminFrame = new MainFrameAdmin(this);
		MEmployeeFrame = new MainFrameEmployee(this);
		RegisterFrame = new RegisterNewUserFrame(this);
		AddFrame = new AddNewItemFrame(this);
		CFrame = new CartFrame(this);
//		LoginFrame.setVisible(true);
//		MAdminFrame.setVisible(true);
		MEmployeeFrame.setVisible(true);
	}
	
	
	//FUNCTIONS FOR ARRAYLIST
	public void add(Item i) {
		Warehouse.add(i);
	}
	public ArrayList<Item> getWarehouse(){
		return Warehouse;
	}
	public ArrayList<Item> deleteWarehouseArray(){
		Warehouse.clear();
		return Warehouse;
	}
	//retrieves data from database (data will be loaded into table in mainframe)
	public void LoadWarehouseArray() {
		IDao.getWarehousefromDB();
	}
	//reloads the Warehouse arrayList (updates it with current DB values)(use it after insert/delete/update querys)
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
	
	//open EditSelectedItemFrame
	public void EditSelectedFrameOpen() {
		EditFrame = new EditSelectedItemFrame(this);
		EditFrame.setVisible(true);
	}
	
	//open CartFrame
	public void CartFrameOpen() {
		CFrame = new CartFrame(this);
		CFrame.setVisible(true);
	}
	
	//login check
	public void LoginCheck(String Username, String Password) {
		if(EDao.CheckLoginProps(Username, Password)==true){
				MAdminFrame.setVisible(true);
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
	
//	//loads the MainFrameAdmin JTable  OLD IMPLEMENTATION
//	public JTable LoadTable() {
////		ctrlItemTable = IDao.LoadTable();
//		IDao.LoadTable();
//		return ctrlItemTable;
//	}
	
	//reloads the JTable in MainFrameAdmin (use after every change to the Database)
	public void ReloadDBTable() {
		reloadWarehouseArray();
		MAdminFrame.TModel.fireTableDataChanged();
		MEmployeeFrame.TModel.fireTableDataChanged();
	} 
	
	//add new item
	public void AddNewItem(int Id, String Size, double Price, String Type, int InStock, String Colour) {
		IDao.AddNewItemToDB(Id, Size, Price, Type, InStock, Colour);
		ReloadDBTable();
		}
	
	//updates the SelectedItemFromDB  (from the editselectedframe)
	public void updateItemInDB(int Id, String Size, double Price, String Type, int InStock, String Colour, int OldId) {
		IDao.updateItem(Id, Size, Price, Type, InStock, Colour, OldId);
		ReloadDBTable();
	}

	//check if an item id already exists
	public boolean CheckItemId(int Id){
		if((IDao.CheckItemId(Id))==true) {
			return true;
		}
		else return false;
	}
	
	//get the item fetched from DB with its Id
	public Item getItem(int Id) {
		selecteditem = IDao.getSelectedItemFromDB(Id);
		return selecteditem;
	}
	
	public Item fetchSelectedItem() {
		return selecteditem;
	}

//	transforms the selecteditemfromDb into an ItemInCart and adds it to the cart
	public void addItemToCart(Item selected) {
		if(selected.getInStock()==0) {
			JOptionPane.showMessageDialog(new JFrame(), "Currently not available","ERROR", JOptionPane.ERROR_MESSAGE);
		}else {
//			CartItem = new Item(selected);
			if(selected.checkInCart(Cart)==false) {
				selected.setInCart(1);
				selected.inStockMinusOne();
				Cart.add(selected);   //elimina questa chiusura di parentesi
				}else {
					//get the selected index in cart
					int index = selected.getSelectedItemIndex(Cart);
					Cart.get(index).inCartPlusOne();
					Cart.get(index).inStockMinusOne();
					}
			CFrame.TModel.fireTableDataChanged();
			MEmployeeFrame.TModel.fireTableDataChanged();
		}
	}
	
	//
	public void BuyandUpdate(){
		for(Item i : Cart) {
			int id = i.getId();
			int sold = i.getInCart();
			IDao.updateOnSaleInDB(sold, id);
		}
		Cart.clear();
		MEmployeeFrame.TModel.fireTableDataChanged();
	}
	
	
}	




