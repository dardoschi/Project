package frames;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
//import javax.swing.table.DefaultTableModel;

import main.Controller;
import main.Item;
//**//to reload table, reload controller arraylist and call firetabledatachanged on the ItemTableModel variable//**//
	public class ItemTableModel extends AbstractTableModel {
			private Controller c;
	
		    private String[] columnNames = { "Id","Size","Price","Type","In Stock","Colour"}; 
		    private Class<?>[] types = new Class[]{Integer.class, String.class, Double.class, String.class, Integer.class, String.class};
		    
		    //needed as dummy for the constructor(Warehouse arrayList of the controller copied here)
			public ArrayList<Item> Warehouse ;
			
			public ItemTableModel(Controller ctrl) {
				c = ctrl;
			}
			
		    public ItemTableModel (ArrayList<Item> CWarehouse){
		        this.Warehouse = CWarehouse;
		        fireTableDataChanged();
		    }
		    
		    @Override
		    public String getColumnName(int columnIndex){
		        return columnNames[columnIndex];
		    }

		    @Override
		    public Class<?> getColumnClass(int columnIndex){
		        return types[columnIndex];
		    }

		    @Override
		    public boolean isCellEditable(int row, int columnIndex){
		        return false;
		    }
		    	
		    @Override
		    public int getRowCount(){
		    	return Warehouse.size();
//		    	int size;
//		    	if(Warehouse == null) {
//		    		size = 0;
//		    	}else {
//		    		size = Warehouse.size();
//		    	}
//		        return size;
		    }
		    
		    @Override
		    public int getColumnCount(){
		        return columnNames.length;
		    }
		    
		    @Override  //Returns the value for the cell at columnIndex and rowIndex.
		    public Object getValueAt(int row, int column){
		        if(row < 0 || row >= Warehouse.size()) return null;
		        Item obj = Warehouse.get(row);
		        switch(column){
		            case 0: return obj.getId();
		            case 1: return obj.getSize();
		            case 2: return obj.getPrice();
		            case 3: return obj.getType();
		            case 4: return obj.getInStock();
		            case 5: return obj.getColour();
		            default: return null;
		        }
		    }  
		    
}
