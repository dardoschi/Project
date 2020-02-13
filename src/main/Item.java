package main;

	public class Item {

		int Id;
		String Size;
		double Price;
		String Type;
		int InStock;
		String Colour;
		
		//constructor
		public Item() {
		}
//		//constructor with all fields
//		public Item(int id, String size, double price, String type, int inStock, String colour) {
//			Id = id;
//			Size = size;
//			Price = price;
//			Type = type;
//			InStock = inStock;
//			Colour = colour;
//		}
		//getters and setters
		public int getId() {
			return Id;
		}

		public void setId(int id) {
			Id = id;
		}
		public String getSize() {
			return Size;
		}
		public void setSize(String size) {
			Size = size;
		}
		public double getPrice() {
			return Price;
		}
		public void setPrice(double price) {
			Price = price;
		}
		public String getType() {
			return Type;
		}
		public void setType(String type) {
			Type = type;
		}
		public int getInStock() {
			return InStock;
		}
		public void setInStock(int inStock) {
			InStock = inStock;
		}
		public String getColour() {
			return Colour;
		}
		public void setColour(String colour) {
			Colour = colour;
		}

	}
