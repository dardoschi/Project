package main;

import java.sql.Connection;

public class ItemDao {
	
	private Controller ctrl;
	Connection connection = ConnectionFactory.getConnection();
	
	public ItemDao (Controller Contrl) {
		ctrl = Contrl;
		}
	
}
 