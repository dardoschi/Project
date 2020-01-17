package main;

import dao.ConnectionFactory;
import dao.ItemDao;

public class Controller {
	
	private ConnectionFactory conn;
	private ItemDao IDao;
	
	
	//Constructor
	public Controller() {
		IDao = new ItemDao(this);
		conn = new ConnectionFactory(this);
	}
}
