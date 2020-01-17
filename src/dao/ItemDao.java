package dao;

import java.sql.Connection;
import main.Controller;
public class ItemDao {
	
	private Controller ctrl;
	Connection connection = ConnectionFactory.getConnection();
	
	public ItemDao (Controller Contrl) {
		ctrl = Contrl;
		}
	
}
 