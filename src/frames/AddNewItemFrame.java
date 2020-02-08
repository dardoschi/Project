package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import main.Controller;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;

public class AddNewItemFrame extends JFrame {

	private JPanel contentPane;
	private Controller ctrl;
	private JFormattedTextField NewIdTF;
	private JComboBox<String> NewSizeCB;
	private JFormattedTextField NewPriceTF;
	private JComboBox<String> NewTypeCB;
	private JComboBox<String> NewColorCB;
	private JLabel lblTitle;
	private JButton AddItemBtn;
	private JButton CancelBtn;
	private JLabel lblInStock;
	private JFormattedTextField NewInStockTF;

	public AddNewItemFrame(Controller c) {
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
		
		
		NumberFormat Doubleformat = DecimalFormat.getInstance();
		Doubleformat.setGroupingUsed(false);
		NumberFormatter DoubleFormatter = new NumberFormatter(Doubleformat);
		DoubleFormatter.setValueClass(Double.class);
		Doubleformat.setMinimumFractionDigits(2);
		Doubleformat.setMaximumFractionDigits(2);
		DoubleFormatter.setAllowsInvalid(true);
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
		
		lblTitle = new JLabel("Add New Item");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblTitle.setToolTipText("Add New Item");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		AddItemBtn = new JButton("Add Item");
		AddItemBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if((InputCheck(NewIdTF,NewPriceTF,NewInStockTF)==true)){
						int Id = (int)NewIdTF.getValue();
						if(CheckItemId(Id)==true) {
							// values of the JFormattedTextField
							double Price = (double)NewPriceTF.getValue();
							int InStock = (int)NewInStockTF.getValue();
							AddNewItem(Id, Price, InStock);
							NewIdTF.setValue(null);
							NewPriceTF.setValue(null);
							NewInStockTF.setValue(null);
							setVisible(false);
						}
						else 
							JOptionPane.showMessageDialog(new JFrame(), "Please change id","ERROR", JOptionPane.ERROR_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(new JFrame(), "Please insert valid values","ERROR", JOptionPane.ERROR_MESSAGE);
					}
			}
		});
		AddItemBtn.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		CancelBtn = new JButton("Cancel");
		CancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2) {
				setVisible(false);
				NewIdTF.setValue(null);
				NewPriceTF.setValue(null);
				NewInStockTF.setValue(null);

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
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(68)
							.addComponent(CancelBtn, GroupLayout.PREFERRED_SIZE, 147, Short.MAX_VALUE)
							.addGap(66)
							.addComponent(AddItemBtn, GroupLayout.PREFERRED_SIZE, 166, Short.MAX_VALUE))
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
						.addComponent(AddItemBtn, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	//Add new Item
	private void AddNewItem(int Id, double Price, int InStock) {
		ctrl.AddNewItem(Id, NewSizeCB.getSelectedItem().toString(),Price, NewTypeCB.getSelectedItem().toString() ,InStock , NewColorCB.getSelectedItem().toString());
	}
	
	//checks if id,price and instock are valid
	private boolean InputCheck(JFormattedTextField NewIdTF, JFormattedTextField NewPriceTF, JFormattedTextField NewInStockTF) {
		if(NewIdTF.getValue()==null || NewPriceTF.getValue()==null || NewInStockTF.getValue()==null) {
			return false;
		}else
			return true;
	}
	
	//check if an item id already exist
	private boolean CheckItemId(int Id) {
		if(ctrl.CheckItemId(Id)==true) {
			return true;
		}
		else return false;
	}
}
