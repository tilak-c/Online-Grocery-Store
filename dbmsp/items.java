package dbmsp;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.sql.*;
import java.awt.Font;
public class items extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	String s1[]={"Select","Vegetables","Water","Milk","Beverages","Solid","Fruits","Seasonal Fruits","Cereals"};
	String veg[]={"Brinjal","Lady's finger","Tomato","Potato","Bottle Guard","Beans"};
	String sol[]={"Cheese","Jaggery","Paneer","Bread","Rice","Pasta","Maggi"};
	String fru[]={"Banana","Apple","Guava","Carrot","Kiwi","Plum"};
	String sesfru[]={"Mango","Orange","Custard Apple","Sweet Lime"};
	String cer[]={"Corn Flakes","Chocos","Oats","Honey Rings"};
	String units[]= {"kg","g","L","ml","dozen","none"};
	String cat[]={"SubCategory","niha"};
	String milkb[]={"Amul","Jersey","Arogya"};
	String waterb[]={"Oxygen","Bisleri","Kinley"};
	String bevr[]={"Red Bull","Coca Cola","Sprite","Maaza","Sting","Flavored Milk"};
	private JTextField textField;
	Component frame=null;
	private JTable table;
	DefaultTableModel model;
	DefaultTableModel model1;
	private JTextField textField_1;
	private JTextField textField_2;
	private JScrollPane scrollPane;
	@SuppressWarnings("rawtypes")
	JComboBox comboBox ;
	private JTable table_1;
	public static int id=0;
	public int sum=0;
	/**
	 * Launch the application.
	 */
	public static void itemscreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					items frame = new items();
					frame.setTitle(login.name);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public items() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 861, 707);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 345, 649, 312);
		contentPane.add(scrollPane);
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=table.getSelectedRow();
				textField_1.setText(model.getValueAt(i, 2).toString());
				textField_2.setText(model.getValueAt(i, 4).toString());
			}
		});
		

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(267, 41, 568, 226);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		model1=new DefaultTableModel();
		Object t1[]= {"id","Item","price","quantity","total"};
		Object r1[]=new Object[5];
		model1.setColumnIdentifiers(t1);
		table_1.setModel(model1);
		table_1.setDefaultEditor(Object.class,null);

		scrollPane_1.setViewportView(table_1);
		
		
		table = new JTable();
		model=new DefaultTableModel();
		table.setDefaultEditor(Object.class, null);
		Object column1[]= {"vid","item_id","vegetable_name","availability","price"};
		Object column2[]= {"wid","liquid_id","brand","availability","price","quantity"};
		Object column3[]= {"milk_id","liquid_id","brand","availability","price","quantity"};
		Object column4[]= {"beverages_id","liquid_id","brand","availability","price","quantity"};
		Object column5[]= {"solid_id","item_id","sname","availability","price"};
		Object column6[]= {"fruit_id","item_id","name","availability","price"};
		Object column7[]= {"sfruit_id","item_id","name","availability","price","dom"};
		Object column8[]= {"cereal_id","item_id","name","availability","price"};
		Object row1[]=new Object[5];
		Object row2[]=new Object[6];
		Object row3[]=new Object[6];
		Object row4[]=new Object[6];
		Object row5[]=new Object[5];
		Object row6[]=new Object[5];
		Object row7[]=new Object[6];
		Object row8[]=new Object[5];
		table.setModel(model);
		scrollPane.setViewportView(table);
		comboBox = new JComboBox(s1);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				String s=(String)comboBox.getSelectedItem();
				if(s.equals("Vegetables")) {
					model.setColumnIdentifiers(column1);
					model.setRowCount(0);
					String query="select * from vegetable";
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","tilakavati","tilak");
					PreparedStatement ps=con.prepareStatement(query);
					ResultSet rs=ps.executeQuery();
					while(rs.next()) {
						row1[0]=rs.getInt(1);
						row1[1]=rs.getInt(2);
						row1[2]=rs.getString(3);
						row1[3]=rs.getInt(4);
						row1[4]=rs.getInt(5);
						model.addRow(row1);
					}
						
					}
				if(s.equals("Water")) {
					model.setColumnIdentifiers(column2);
					model.setRowCount(0);
					String query=" select * from water natural join waterap order by wid,availability,quantity,price";
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","tilakavati","tilak");
					PreparedStatement ps=con.prepareStatement(query);
					ResultSet rs=ps.executeQuery();
					while(rs.next()) {
						row2[0]=rs.getInt(1);
						row2[1]=rs.getInt(2);
						row2[2]=rs.getString(3);
						row2[3]=rs.getInt(4);
						row2[4]=rs.getInt(5);
						row2[5]=rs.getInt(6);
						model.addRow(row2);
				}
				}
				if(s.equals("Milk")) {
					model.setColumnIdentifiers(column3);
					model.setRowCount(0);
					String query=" select * from milk natural join milkap order by milk_id,availability,quantity,price";
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","tilakavati","tilak");
					PreparedStatement ps=con.prepareStatement(query);
					ResultSet rs=ps.executeQuery();
					while(rs.next()) {
						row3[0]=rs.getInt(1);
						row3[1]=rs.getInt(2);
						row3[2]=rs.getString(3);
						row3[3]=rs.getInt(4);
						row3[4]=rs.getInt(5);
						row3[5]=rs.getInt(6);
						model.addRow(row3);
					}
				}
				if(s.equals("Beverages")) {
					model.setColumnIdentifiers(column4);
					model.setRowCount(0);
					String query=" select * from beverages natural join beveragesap order by beverages_id,availability,quantity,price";
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","tilakavati","tilak");
					PreparedStatement ps=con.prepareStatement(query);
					ResultSet rs=ps.executeQuery();
					while(rs.next()) {
						row4[0]=rs.getInt(1);
						row4[1]=rs.getInt(2);
						row4[2]=rs.getString(3);
						row4[3]=rs.getInt(4);
						row4[4]=rs.getInt(5);
						row4[5]=rs.getInt(6);
						model.addRow(row4);
					}
				}
				if(s.equals("Solid")) {
					model.setColumnIdentifiers(column5);
					model.setRowCount(0);
					String query="select * from solid";
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","tilakavati","tilak");
					PreparedStatement ps=con.prepareStatement(query);
					ResultSet rs=ps.executeQuery();
					while(rs.next()) {
						row5[0]=rs.getInt(1);
						row5[1]=rs.getInt(2);
						row5[2]=rs.getString(3);
						row5[3]=rs.getInt(4);
						row5[4]=rs.getInt(5);
						model.addRow(row5);
					}
				}
				if(s.equals("Fruits")) {
					model.setColumnIdentifiers(column6);
					model.setRowCount(0);
					String query="select * from fruit";
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","tilakavati","tilak");
					PreparedStatement ps=con.prepareStatement(query);
					ResultSet rs=ps.executeQuery();
					while(rs.next()) {
						row6[0]=rs.getInt(1);
						row6[1]=rs.getInt(2);
						row6[2]=rs.getString(3);
						row6[3]=rs.getInt(4);
						row6[4]=rs.getInt(5);
						model.addRow(row6);
					}
				}
				if(s.equals("Seasonal Fruits")) {
					model.setColumnIdentifiers(column7);
					model.setRowCount(0);
					String query="select * from seasonalfruit";
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","tilakavati","tilak");
					PreparedStatement ps=con.prepareStatement(query);
					ResultSet rs=ps.executeQuery();
					while(rs.next()) {
						row7[0]=rs.getInt(1);
						row7[1]=rs.getInt(2);
						row7[2]=rs.getString(3);
						row7[3]=rs.getInt(4);
						row7[4]=rs.getInt(5);
						row7[5]=rs.getDate(6);
						model.addRow(row7);
					}
				}
				if(s.equals("Cereals")) {
					model.setColumnIdentifiers(column8);
					model.setRowCount(0);
					String query="select * from cereal";
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","tilakavati","tilak");
					PreparedStatement ps=con.prepareStatement(query);
					ResultSet rs=ps.executeQuery();
					while(rs.next()) {
						row8[0]=rs.getInt(1);
						row8[1]=rs.getInt(2);
						row8[2]=rs.getString(3);
						row8[3]=rs.getInt(4);
						row8[4]=rs.getInt(5);
						model.addRow(row8);
					}
				}
				}
				catch(SQLException|ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			
			}
		});
		
		model1.setRowCount(0);
		comboBox.setToolTipText("Items");
		comboBox.setBounds(50, 32, 116, 33);
		contentPane.add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(116, 227, 122, 33);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Quantity");
		lblNewLabel.setBounds(35, 232, 73, 23);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Add To Bill");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedIndex()==0) {
				JOptionPane.showMessageDialog(frame, "Select any category");	
				}
				else if(textField.getText().equals("")) {
					JOptionPane.showMessageDialog(frame,"Please enter the quantity");
				}
				else if(textField.getText().length()!=0) {
					
				try {
					int y=++id;
					String name= textField_1.getText();
					int price=Integer.parseInt(textField_2.getText());
					int quan=Integer.parseInt(textField.getText());
					int tot=price*quan;
					try{
						
					String query="insert into prevorder values(?,?,?,?,?,?)";
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","tilakavati","tilak");
					PreparedStatement ps=con.prepareStatement(query);
					ps.setInt(1,login.cid );
					ps.setInt(2, y);
					ps.setString(3,name);
					ps.setInt(4, price);
					ps.setInt(5, quan);
					ps.setInt(6, tot);
					sum+=tot;
					ps.executeUpdate();
					}
					catch(SQLException|ClassNotFoundException e1) {
						e1.printStackTrace();
					}
					r1[0]=y;
					r1[1]=name;
					r1[2]=price;
					r1[3]=quan;
					r1[4]=tot;
					model1.addRow(r1);
					
					
				}
				catch(NumberFormatException e1) {
					JOptionPane.showMessageDialog(frame,"Please enter a number");
				}}
				
			}
		});
		btnNewButton.setBounds(35, 289, 116, 39);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Clear");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				comboBox.setSelectedIndex(0);
			}
		});
		btnNewButton_1.setBounds(187, 289, 116, 39);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Back");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1_1.setBounds(338, 289, 116, 39);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_2 = new JButton("Buy");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query="insert into sales values(seq_sales.nextval,?,sysdate,?,to_char(sysdate,'hh24:mi:ss'))";
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","tilakavati","tilak");
					PreparedStatement ps=con.prepareStatement(query);
					ps.setInt(1,login.cid);
					ps.setInt(2,sum);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null,"Your total is "+sum);
					dispose();
				}
				catch(SQLException|ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(496, 287, 116, 42);
		contentPane.add(btnNewButton_2);
		
		JLabel lblName = new JLabel("Item");
		lblName.setBounds(35, 94, 40, 23);
		contentPane.add(lblName);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(116, 89, 122, 33);
		contentPane.add(textField_1);
		
		JLabel lblName_1 = new JLabel("Price");
		lblName_1.setBounds(35, 164, 65, 23);
		contentPane.add(lblName_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(116, 159, 122, 33);
		contentPane.add(textField_2);
		
		textField_1.setEditable(false);
		textField_2.setEditable(false);
		JLabel lblBilling = new JLabel("Bill");
		lblBilling.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBilling.setBounds(550, 11, 139, 23);
		contentPane.add(lblBilling);
	}
}

