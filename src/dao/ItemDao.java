package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import frames.MainFrame;
import main.Controller;
import net.proteanit.sql.DbUtils;


public class ItemDao {
	
	private Controller ctrl;
	Connection connection = ConnectionFactory.getConnection();
	private MainFrame MFrame;
	public JTable daoItemTable = new JTable();
	
	public ItemDao (Controller Contrl) {
		ctrl = Contrl;
		};
	
		
	//load jtable in main frame
	public JTable LoadTable() {
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("select * from item");
			daoItemTable.setModel(DbUtils.resultSetToTableModel(rs));
			return daoItemTable;
		}catch (SQLException e2) {
				e2.printStackTrace();		
				return null;
		}
	}
	
	//Add new item
	public void AddNewItem(int Id, String Size, double Price, String Type, int InStock, String Colour) {
		PreparedStatement st;
		try {
				st = connection.prepareStatement("insert into item (values (?,?,?,?,?,?));");
				st.setInt(1, Id);
				st.setString(2, Size);
				st.setDouble(3, Price);
				st.setString(4, Type);
				st.setInt(5, InStock);
				st.setString(6, Colour);	
				ResultSet rs = st.executeQuery();
		}catch(SQLException e3) {
			e3.printStackTrace();
		}

	}
	
	//check id, false if there is already an item with that id, true otherwise
	public boolean CheckItemId(int Id){
		PreparedStatement st;
			try {
				st = connection.prepareStatement("SELECT * from item WHERE id = ?");
					 st.setInt(1, Id);
				     ResultSet rs = st.executeQuery(); 
				     // if this id exist returns true else returns false 
				     if ( rs.next() ) {    	
				    	 return false;
				    	}
				     else 
				    	 return true;
				}
				catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}