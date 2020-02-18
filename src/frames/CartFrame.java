package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import Item.Item;
import TableModels.ItemInCartTableModel;
import TableModels.ItemInDBTableModel;
import main.Controller;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;

public class CartFrame extends JFrame {

	private JPanel contentPane;
	private Controller ctrl;
	private JTable CartTable;
	public ItemInCartTableModel TModel;
	private Item SelectedItem;
	private Item CartItem;
	private JLabel TotalLbl;
	
	public CartFrame(Controller c) {
		ctrl = c;
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 705, 554);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 691, 356);
		contentPane.add(scrollPane);
		TModel = new ItemInCartTableModel(ctrl.Cart);
		CartTable = new JTable(TModel);
		scrollPane.setViewportView(CartTable);
		
		//text centered in table
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		CartTable.setDefaultRenderer(Integer.class, centerRenderer);
		CartTable.setDefaultRenderer(String.class, centerRenderer);
		CartTable.setDefaultRenderer(Double.class, centerRenderer);
		//column sorting (automatic on id ascending)
		CartTable.setAutoCreateRowSorter(true);
		
		JButton EmptyCartBtn = new JButton("Empty Cart");
		EmptyCartBtn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		EmptyCartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null,"Do you want to proceed?", "Select an Option...",JOptionPane.YES_NO_OPTION)==0){
					ctrl.emptyCart();
					} else {
						//does nothing
					}
			}
		});
		EmptyCartBtn.setBounds(10, 417, 165, 54);
		contentPane.add(EmptyCartBtn);
		
		JButton BuyBtn = new JButton("Buy");
		BuyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null,"Do you want to proceed?", "Select an Option...",JOptionPane.YES_NO_OPTION)==0){
					ctrl.BuyandUpdate();
					} else {
						//does nothing
					}
				}
			});
		BuyBtn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		BuyBtn.setBounds(557, 417, 124, 54);
		contentPane.add(BuyBtn);
		
		JButton RemoveOneBtn = new JButton("Remove One");
		RemoveOneBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = CartTable.getSelectedRow();
				if(i == -1) {
					JOptionPane.showMessageDialog(new JFrame(), "Please select a line from table","ERROR", JOptionPane.ERROR_MESSAGE);	
				}else {
					SelectedItem = ctrl.getItem((int) CartTable.getValueAt(CartTable.getSelectedRow(), 0));
					int itemindex = SelectedItem.getSelectedItemIndex(c.Cart);
					CartItem = c.Cart.get(itemindex);
					ctrl.removeOneFromCart(CartItem);
				}
			}
		});
		RemoveOneBtn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		RemoveOneBtn.setBounds(185, 419, 181, 51);
		contentPane.add(RemoveOneBtn);
		
		JButton RemoveAllBtn = new JButton("Remove All");
		RemoveAllBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = CartTable.getSelectedRow();
				if(i == -1) {
					JOptionPane.showMessageDialog(new JFrame(), "Please select a line from table","ERROR", JOptionPane.ERROR_MESSAGE);	
				}else {
					SelectedItem = ctrl.getItem((int) CartTable.getValueAt(CartTable.getSelectedRow(), 0));
					int itemindex = SelectedItem.getSelectedItemIndex(c.Cart);
					CartItem = c.Cart.get(itemindex);
					ctrl.removeAllFromCart(CartItem);
				}
			}
		});
		RemoveAllBtn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		RemoveAllBtn.setBounds(376, 417, 165, 54);
		contentPane.add(RemoveAllBtn);
		
		TotalLbl = new JLabel("Total : "+ ctrl.getTotal() +" €");
		TotalLbl.setFont(new Font("Tahoma", Font.PLAIN, 34));
		TotalLbl.setBounds(376, 367, 305, 43);
		contentPane.add(TotalLbl);
		CartTable.getRowSorter().toggleSortOrder(0);
		
		
	}
	
	//updates the total calling the total calculator function in ctrl
	public void updateTotal() {
		TotalLbl.setText("Total : "+ ctrl.getTotal() +" €");
	}
	

}
