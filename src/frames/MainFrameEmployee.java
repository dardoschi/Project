package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import Item.Item;
import TableModels.ItemInDBTableModel;
import main.Controller;
import java.awt.Color;

public class MainFrameEmployee extends JFrame {

	private JPanel contentPane;
	private Controller ctrl;
	public ItemInDBTableModel TModel;
	private JTable ItemTable;
	private JScrollPane ItemscrollPane;
	private JButton OpenCartBtn;
	private Item SelectedItem;
	private Item CartItem;            //non so se serve, per ora no

	public MainFrameEmployee(Controller c) {
		ctrl = c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1083, 805);
		setLocationRelativeTo(null);
		JPanel MainPanel = new JPanel();
		MainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(MainPanel);
		
		//creates an istance of the custom table model with the controller arraylist(warehouse)
		TModel = new ItemInDBTableModel(c.Warehouse);
		ItemTable = new JTable(TModel);
		ItemTable.setBackground(Color.WHITE);
		
		
		//inserts the table into the scrollpanel
		ItemscrollPane = new JScrollPane(ItemTable);
	
		
		//text centered in table
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		ItemTable.setDefaultRenderer(Integer.class, centerRenderer);
		ItemTable.setDefaultRenderer(String.class, centerRenderer);
		ItemTable.setDefaultRenderer(Double.class, centerRenderer);
		//column sorting (automatic on id ascending)
		ItemTable.setAutoCreateRowSorter(true);
		ItemTable.getRowSorter().toggleSortOrder(0);
		ItemTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		
		JButton AddToCartBtn = new JButton("Add To Cart");
		AddToCartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = ItemTable.getSelectedRow();
				if(i == -1) {
					JOptionPane.showMessageDialog(new JFrame(), "Please select a line from table","ERROR", JOptionPane.ERROR_MESSAGE);	
				}else {
					SelectedItem = ctrl.getItem((int) ItemTable.getValueAt(ItemTable.getSelectedRow(), 0));
					int itemindex = SelectedItem.getSelectedItemIndex(c.Warehouse);
					CartItem = c.Warehouse.get(itemindex);
					ctrl.addItemToCart(CartItem);
				}
			}
		});
		AddToCartBtn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		OpenCartBtn = new JButton("Open Cart");
		OpenCartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.CartFrameOpen();
			}
		});
		OpenCartBtn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JButton LogOutBtn = new JButton("Log Out");
		LogOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.LogOut();
			}
		});
		LogOutBtn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GroupLayout gl_MainPanel = new GroupLayout(MainPanel);
		gl_MainPanel.setHorizontalGroup(
			gl_MainPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_MainPanel.createSequentialGroup()
					.addGap(29)
					.addComponent(ItemscrollPane, GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)
					.addGap(70)
					.addGroup(gl_MainPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(AddToCartBtn, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
						.addComponent(OpenCartBtn, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addComponent(LogOutBtn, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_MainPanel.setVerticalGroup(
			gl_MainPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_MainPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_MainPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_MainPanel.createSequentialGroup()
							.addComponent(AddToCartBtn, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(OpenCartBtn, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 545, Short.MAX_VALUE)
							.addComponent(LogOutBtn, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
						.addComponent(ItemscrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 719, Short.MAX_VALUE))
					.addGap(28))
		);
		

		
		ItemscrollPane.setViewportView(ItemTable);
		MainPanel.setLayout(gl_MainPanel);
	}
}
