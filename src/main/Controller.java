package main;

public class Controller {
	
	private ConnectionFactory conn;
	
	//Constructor
	public Controller() {
		conn = new ConnectionFactory(this);
	}
}
