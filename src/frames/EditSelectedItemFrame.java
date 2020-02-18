package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import Item.Item;
import main.Controller;

public class EditSelectedItemFrame extends JFrame {
	
	private Controller ctrl;
	private JPanel contentPane;
	private JFormattedTextField NewIdTF;
	private JComboBox<String> NewSizeCB;
	private JFormattedTextField NewPriceTF;
	private JComboBox<String> NewTypeCB;
	private JComboBox<String> NewColorCB;
	private JLabel lblTitle;
	private JButton EditItemBtn;
	private JButton CancelBtn;
	private JLabel lblInStock;
	private JFormattedTextField NewInStockTF;
	private Item SelectedItem;

	public EditSelectedItemFrame(Controller c) {
		ctrl = c;
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 571, 547);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		//formatter for Id and InStock TF that only accept Integers, with max in range
		NumberFormat Intformat = NumberFormat.getInstance();
		Intformat.setGroupingUsed(false);//Remove comma from number greater than 4 digit
		NumberFormatter IntFormatter = new NumberFormatter(Intformat);
		IntFormatter.setValueClass(Integer.class);
		IntFormatter.setMinimum(0);
		IntFormatter.setMaximum(2147483647);
		IntFormatter.setAllowsInvalid(false);
		IntFormatter.setCommitsOnValidEdit(true);// committ value on each keystroke instead of focus lost
		
		JFormattedTextField NewIdTF = new JFormattedTextField(IntFormatter);
		NewIdTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
		NewIdTF.setHorizontalAlignment(SwingConstants.CENTER);
		NewIdTF.setColumns(10);
		
		NewSizeCB = new JComboBox<String>();
		((JLabel)NewSizeCB.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		NewSizeCB.setFont(new Font("Tahoma", Font.PLAIN, 20));
		NewSizeCB.addItem("XS");
		NewSizeCB.addItem("S");
		NewSizeCB.addItem("M");
		NewSizeCB.addItem("L");
		NewSizeCB.addItem("XL");
		NewSizeCB.addItem("XXL");
		
		//formatter for the price (double) field
		NumberFormat Doubleformat = DecimalFormat.getInstance();
		Doubleformat.setGroupingUsed(false);
		NumberFormatter DoubleFormatter = new NumberFormatter(Doubleformat);
		DoubleFormatter.setValueClass(Double.class);
		Doubleformat.setMinimumFractionDigits(2);
		Doubleformat.setMaximumFractionDigits(2);
		DoubleFormatter.setAllowsInvalid(false);
		DoubleFormatter.setCommitsOnValidEdit(true);
		Doubleformat.setRoundingMode(RoundingMode.HALF_UP);
		
		NewPriceTF = new JFormattedTextField(DoubleFormatter);
		NewPriceTF.setHorizontalAlignment(SwingConstants.CENTER);
		NewPriceTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
		NewPriceTF.setColumns(10);
		
		NewTypeCB = new JComboBox<String>();
		((JLabel)NewTypeCB.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		NewTypeCB.setFont(new Font("Tahoma", Font.PLAIN, 20));
		NewTypeCB.addItem("Coat");
		NewTypeCB.addItem("Shoe");
		NewTypeCB.addItem("Dress");
		NewTypeCB.addItem("Jeans");
		NewTypeCB.addItem("T-shirt");
		NewTypeCB.addItem("Skirt");
		NewTypeCB.addItem("Bag");
		NewTypeCB.addItem("Briefcase");
		
		NewColorCB = new JComboBox<String>();
		((JLabel)NewColorCB.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		NewColorCB.setFont(new Font("Tahoma", Font.PLAIN, 20));
		NewColorCB.addItem("Red");
		NewColorCB.addItem("Blue");
		NewColorCB.addItem("Green");
		NewColorCB.addItem("Yellow");
		NewColorCB.addItem("Black");
		NewColorCB.addItem("White");
		NewColorCB.addItem("Purple");
		NewColorCB.addItem("Orange");
		NewColorCB.addItem("Brown");
		NewColorCB.addItem("Jeans");
		
		JLabel lblID = new JLabel("ID");
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		JLabel lblSize = new JLabel("Size");
		lblSize.setHorizontalAlignment(SwingConstants.CENTER);
		lblSize.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		JLabel lblType = new JLabel("Type");
		lblType.setHorizontalAlignment(SwingConstants.CENTER);
		lblType.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		JLabel lblColour = new JLabel("Colour");
		lblColour.setHorizontalAlignment(SwingConstants.CENTER);
		lblColour.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		lblTitle = new JLabel("Edit Item");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblTitle.setToolTipText("Edit the selected Item");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		EditItemBtn = new JButton("Edit Item");
		EditItemBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (check() == true) {
					editSelectedItem();
				}
			}

			private boolean check() {
				if(inputCheck() == true && NewIdCheck() == true) {
				  return true;
				}
				else
					return false;
			}
			
			//checks if Id already exist
			private boolean NewIdCheck() {
				int OldId = SelectedItem.getId();
				if(OldId!=(int) NewIdTF.getValue()) {
					if(ctrl.CheckItemId((int) NewIdTF.getValue())==false) {
						JOptionPane.showMessageDialog(new JFrame(), "Please change id","ERROR", JOptionPane.ERROR_MESSAGE);
						return false;
					}
				}return true;
			}
			//checks for blank spaces, pretty much useless
			private boolean inputCheck() {
				if(NewIdTF.getValue()==null || NewPriceTF.getValue()==null || NewInStockTF.getValue()==null) { 
					JOptionPane.showMessageDialog(new JFrame(), "Please insert valid values","ERROR", JOptionPane.ERROR_MESSAGE);
				}return true;
			}

			private void editSelectedItem() {
				int OldId = SelectedItem.getId();
				int Id = (int) NewIdTF.getValue();
				String Size = NewSizeCB.getSelectedItem().toString();
				Double Price = (Double) NewPriceTF.getValue();
				String Type = NewTypeCB.getSelectedItem().toString();
				int InStock = (int) NewInStockTF.getValue();
				String Color = NewColorCB.getSelectedItem().toString();
				ctrl.updateItemInDB(Id, Size, Price, Type, InStock, Color, OldId);
				dispose();
				SelectedItem = new Item();
			}
		});
		EditItemBtn.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		CancelBtn = new JButton("Cancel");
		CancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2) {
				dispose();
				SelectedItem = new Item();
			}
		});
		CancelBtn.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		lblInStock = new JLabel("In Stock");
		lblInStock.setHorizontalAlignment(SwingConstants.CENTER);
		lblInStock.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		NewInStockTF = new JFormattedTextField(IntFormatter);
		NewInStockTF.setHorizontalAlignment(SwingConstants.CENTER);
		NewInStockTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
		NewInStockTF.setColumns(10);
		
		//waits for the window to open to execute this code
		addWindowListener(new WindowAdapter() {
	          public void windowOpened(WindowEvent e) {
	        	  fieldSetter();
	          }
	        //fills the frame fields with the SelectedItem current values, so you can modify them
			private void fieldSetter() {
			  	  SelectedItem = ctrl.fetchSelectedItem();
			  	  NewIdTF.setValue(SelectedItem.getId());
			  	  NewPriceTF.setValue(SelectedItem.getPrice());
			  	  NewInStockTF.setValue(SelectedItem.getInStock());
			  	  NewSizeCB.setSelectedIndex(getNewSizeCB());
			  	  NewTypeCB.setSelectedIndex(getNewTypeCB());
			  	  NewColorCB.setSelectedIndex(getNewColorCB());
					
			}
			private int getNewSizeCB() {
				String size = SelectedItem.getSize();
				switch(size) {
					case "XS ":return 0;
					case "S  ":return 1;
					case "M  ":return 2;
					case "L  ":return 3;
					case "XL ":return 4;
					case "XXL":return 5;
				default:return 0;
				}
			}
			private int getNewTypeCB() {
				String type = SelectedItem.getType();
				switch(type) {
					case "Coat      ":return 0;
					case "Shoe      ":return 1;
					case "Dress     ":return 2;
					case "Jeans     ":return 3;
					case "T-shirt   ":return 4;
					case "Skirt     ":return 5;
					case "Bag       ":return 6;
					case "Briefcase ":return 7;
				default:return 0;
				}
			}
			private int getNewColorCB() {
				String type = SelectedItem.getColour();
				switch(type) {
					case "Red     ":return 0;
					case "Blue    ":return 1;
					case "Green   ":return 2;
					case "Yellow  ":return 3;
					case "Black   ":return 4;
					case "White   ":return 5;
					case "Purple  ":return 6;
					case "Orange  ":return 7;
					case "Brown   ":return 8;
					case "Jeans   ":return 9;
				default:return 0;
				}
			}
	        });
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(68)
							.addComponent(CancelBtn, GroupLayout.PREFERRED_SIZE, 147, Short.MAX_VALUE)
							.addGap(66)
							.addComponent(EditItemBtn, GroupLayout.PREFERRED_SIZE, 166, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(36)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblType, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
								.addComponent(lblPrice, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
								.addComponent(lblSize, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
								.addComponent(lblID, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
								.addComponent(lblColour, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
								.addComponent(lblInStock, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(NewColorCB, 0, 270, Short.MAX_VALUE)
									.addComponent(NewTypeCB, 0, 270, Short.MAX_VALUE)
									.addComponent(NewPriceTF, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
									.addComponent(NewSizeCB, Alignment.TRAILING, 0, 270, Short.MAX_VALUE)
									.addComponent(NewIdTF, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
								.addComponent(NewInStockTF, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))))
					.addGap(100))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(95)
					.addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
					.addGap(116))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 88, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(NewIdTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblID))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(NewSizeCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSize))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(NewPriceTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPrice))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblType)
						.addComponent(NewTypeCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblInStock, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(NewInStockTF, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblColour)
						.addComponent(NewColorCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(CancelBtn, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
						.addComponent(EditItemBtn, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);

	}

}
