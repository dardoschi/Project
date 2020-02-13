package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import frames.MainFrame;
import main.Controller;
import main.Item;

public class ItemDao {
	
	private Controller ctrl;
	private Connection connection = ConnectionFactory.getConnection();
	private MainFrame MFrame;
	
	//constructor
	public ItemDao (Controller Contrl) {
		ctrl = Contrl;
		};
	
		
//	//load jtable in main frame OLD IMPLEMENTATION
//	public void LoadTable() {
//		try {
//			Statement st = connection.createStatement();
//			ResultSet rs = st.executeQuery("select * from item");
//			ctrl.ctrlItemTable.setModel(DbUtils.resultSetToTableModel(rs));
////			return daoItemTable;
//		}catch (SQLException e2) {
//				e2.printStackTrace();		
////				return null;
//		}
//	}
		
		
		//Loads the ArrayList used for the jtable in mainframe
		public void getWarehousefromDB() {
			try {
				Statement st = connection.createStatement();
				String query ="select * from Item";
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					Item s = new Item();
					s.setId(rs.getInt("id"));
					s.setSize(rs.getString("size"));
					s.setPrice(rs.getDouble("price"));
					s.setType(rs.getString("type"));
					s.setInStock(rs.getInt("instock"));
					s.setColour(rs.getString("colour"));
					ctrl.Warehouse.add(s);
				}
			}
			catch (SQLException e ) {
				e.printStackTrace();
			}		
			
		}

	//Add new item to Database, after this reload the arrayList
	public void AddNewItemToDB(int Id, String Size, double Price, String Type, int InStock, String Colour) {
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
	
	
	
	//get selected item from JTable (fetch its data from DB)
	public Item getSelectedItemFromDB(int Id) {
		PreparedStatement st;
		try {
			st = connection.prepareStatement("SELECT * from item WHERE id = ? ");
			st.setInt(1,  Id);
			ResultSet rs = st.executeQuery();
			Item selected = new Item();
			while (rs.next()) {
				selected.setId(rs.getInt("id"));
				selected.setSize(rs.getString("size"));
				selected.setPrice(rs.getDouble("price"));
				selected.setType(rs.getString("type"));
				selected.setInStock(rs.getInt("instock"));
				selected.setColour(rs.getString("colour"));
			}
			return selected;
		}	
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void updateItem(int Id, String Size, double Price, String Type, int InStock, String Colour, int OldId) {
		PreparedStatement st;
		try {
			st = connection.prepareStatement("UPDATE item SET id = ?, size = ?, price = ?, type = ?, instock = ?, colour = ? WHERE id = ?");
			st.setInt(1, Id);
			st.setString(2, Size);
			st.setDouble(3, Price);
			st.setString(4, Type);
			st.setInt(5, InStock);
			st.setString(6, Colour);
			st.setInt(7,OldId);
			ResultSet rs = st.executeQuery();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}