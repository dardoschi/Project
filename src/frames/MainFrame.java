package frames;

import dao.ConnectionFactory;
import main.Controller;
import main.Item;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.naming.ldap.SortKey;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.*;
import java.awt.Color;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.TextArea;

import javax.swing.SwingConstants;

public class MainFrame extends JFrame {
	
	private Controller ctrl;
//	private Connection conn = ConnectionFactory.getConnection();    //unused
	private JScrollPane ItemscrollPane;
	public ItemTableModel TModel;
	public JTable ItemTable;

	public MainFrame(Controller c){
		ctrl = c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1083, 805);
		setLocationRelativeTo(null);

		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu EditWarehouseMenu = new JMenu("Edit Warehouse");
		menuBar.add(EditWarehouseMenu);
		
		JMenu AddItemMenu = new JMenu("Add Item");
		AddItemMenu.setHorizontalAlignment(SwingConstants.LEFT);
		EditWarehouseMenu.add(AddItemMenu);
		
		JMenuItem AddNewItemMenuItem = new JMenuItem("New");
		AddNewItemMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.AddNewItemFrameOpen();
			}
		});
		AddItemMenu.add(AddNewItemMenuItem);
		
		JMenuItem AddExistingItemMenuItem = new JMenuItem("Existing");
		AddItemMenu.add(AddExistingItemMenuItem);
		JPanel MainPanel = new JPanel();
		MainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(MainPanel);
		
		//creates an istance of the custom table model with the controller arraylist(warehouse)
		TModel = new ItemTableModel(c.Warehouse);
		ItemTable = new JTable(TModel);
		
		
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

		JButton SearchRefreshBtn = new JButton("Refresh");
		SearchRefreshBtn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		SearchRefreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.ReloadDBTable();							
			}
		});
		
		JButton EditSelectedBtn = new JButton("Edit Selected");
		EditSelectedBtn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		EditSelectedBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = ItemTable.getSelectedRow();
				if(i == -1) {
					JOptionPane.showMessageDialog(new JFrame(), "pigl na linea kitemmuo","ERROR", JOptionPane.ERROR_MESSAGE);	
				}else {
					ctrl.getItem((int) ItemTable.getValueAt(ItemTable.getSelectedRow(), 0));
					ctrl.EditSelectedFrameOpen();
				}
			}
		});
		GroupLayout gl_MainPanel = new GroupLayout(MainPanel);
		gl_MainPanel.setHorizontalGroup(
			gl_MainPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_MainPanel.createSequentialGroup()
					.addGap(29)
					.addComponent(ItemscrollPane, GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE)
					.addGap(77)
					.addGroup(gl_MainPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_MainPanel.createSequentialGroup()
							.addComponent(SearchRefreshBtn, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
							.addGap(52))
						.addGroup(gl_MainPanel.createSequentialGroup()
							.addComponent(EditSelectedBtn, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_MainPanel.setVerticalGroup(
			gl_MainPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_MainPanel.createSequentialGroup()
					.addGap(135)
					.addGroup(gl_MainPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_MainPanel.createSequentialGroup()
							.addComponent(SearchRefreshBtn, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(EditSelectedBtn, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addGap(498))
						.addGroup(gl_MainPanel.createSequentialGroup()
							.addComponent(ItemscrollPane, GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
							.addGap(28))))
		);
		

		
		ItemscrollPane.setViewportView(ItemTable);
		MainPanel.setLayout(gl_MainPanel);
	}
}
