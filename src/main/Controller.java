package main;

public class Controller {
	
	private ConnectionFactory conn;
	private ItemDao IDao;
	//Constructor
	public Controller() {
		conn = new ConnectionFactory(this);
		IDao = new ItemDao(this);
	}
}
