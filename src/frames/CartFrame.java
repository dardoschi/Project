package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
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

public class CartFrame extends JFrame {

	private JPanel contentPane;
	private Controller ctrl;
	private JTable CartTable;
	public ItemInCartTableModel TModel;
	private Item SelectedItem;
	private int incart;
	
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
		
		JButton CancelBtn = new JButton("Cancel");
		CancelBtn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		CancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.ReloadDBTable();
				ctrl.Cart.clear();
				setVisible(false);
			}
		});
		CancelBtn.setBounds(51, 417, 124, 54);
		contentPane.add(CancelBtn);
		
		JButton BuyBtn = new JButton("Buy");
		BuyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					ctrl.BuyandUpdate();
				}
			});
		BuyBtn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		BuyBtn.setBounds(536, 417, 124, 54);
		contentPane.add(BuyBtn);
		CartTable.getRowSorter().toggleSortOrder(0);
	}
}
