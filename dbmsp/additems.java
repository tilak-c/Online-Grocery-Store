package dbmsp;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class additems extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	String cat[]= {"Select","Vegetables","Water","Milk","Beverages","Solid","Fruits","Seasonal Fruits","Cereals"};
	public static int n,b,c;
	DefaultTableModel model;
	/**
	 * Launch the application.
	 */
	Component frame=null;
	private JTextField textField_3;
	public static void screen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					additems frame = new additems();
					frame.setTitle("Items Info");
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
	public additems() {
		dispose();
		setBounds(100, 100, 575, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		model=new DefaultTableModel();
		
		textField = new JTextField();
		textField.setBounds(158, 45, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(30, 43, 86, 20);
		contentPane.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(158, 91, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Quantity");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(30, 92, 86, 14);
		contentPane.add(lblNewLabel_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(158, 145, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Price");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(30, 146, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox comboBox = new JComboBox(cat);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String d=(String) comboBox.getSelectedItem();
				if(d.equals("Water")||d.equals("Beverages")||d.equals("Milk")){
					textField_3.setEditable(true);
				}
				else {
					textField_3.setEditable(false);
				}
			}
		});
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s=comboBox.getSelectedItem().toString();
				if(s.equals("Beverages")||s.equals("Milk")||s.equals("Water")) {
					textField_3.setEditable(true);
				}
			}
		});
		comboBox.setBounds(158, 256, 114, 22);
		contentPane.add(comboBox);
		comboBox.getSelectedItem().toString();
		
		
		JLabel lblNewLabel_3 = new JLabel("Select Category");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(30, 255, 125, 21);
		contentPane.add(lblNewLabel_3);
		if(comboBox.getSelectedItem().toString().equals("Beverages")) {
			textField_3.setEditable(true);
		}
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				
				if(textField.getText().length()<2 || textField_1.getText().length()<1 || textField_2.getText().length()<1) {
					JOptionPane.showMessageDialog(frame, "Please fill the boxes");
				}
				else if(comboBox.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(frame, "Please select the cateogry");
				}
				else {
				int a=JOptionPane.showConfirmDialog(frame,"Are you sure you want to save");
				if(a==JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(frame,"Saved");
				//	textField.setText("");
				//	textField_1.setText("");
				//	textField_2.setText("");
				//	textField_3.setText("");
				//	comboBox.setSelectedIndex(0);
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","tilakavati","tilak");
						String d=comboBox.getSelectedItem().toString();
						if(d.equals("Vegetables")) {
							textField_3.setEditable(false);
						String query1="insert into vegetable values(seq_veg.nextval,?,?,?,?)";
						PreparedStatement ps1=con.prepareStatement(query1);
						ps1.setInt(1, 1);
						ps1.setString(2, textField.getText());
						ps1.setInt(3,n);
						ps1.setInt(4, b);
						ps1.executeUpdate();
						}
						if(d.equals("Water")) {
							try {
							n=Integer.parseInt(textField_1.getText());
							b=Integer.parseInt(textField_2.getText());
							c=Integer.parseInt(textField_3.getText());
							}
							catch(NumberFormatException e1) {
								JOptionPane.showMessageDialog(frame, "Quantity and price should be numbers");
							}
							String query5="select * from water where brand=?";
							PreparedStatement ps5=con.prepareStatement(query5);
							ps5.setString(1, textField.getText());
							int u=ps5.executeUpdate();
							ResultSet rs=ps5.executeQuery();
							if(u==0) {
								String query2="insert into water values(seq_wat.nextval,?,?)";
								PreparedStatement ps2=con.prepareStatement(query2);
								ps2.setInt(1,2);
								ps2.setString(2,textField.getText());
								ps2.executeUpdate();
								String query4="select max(wid) from water";
								Statement ps3=con.createStatement();
								ResultSet rs1=ps3.executeQuery(query4);
								rs1.next();
								int y=rs1.getInt(1);
								String query3="insert into waterap values(?,?,?,?)";
								PreparedStatement ps4=con.prepareStatement(query3);
								ps4.setInt(1, y);
								ps4.setInt(2, c);
								ps4.setInt(3, b);
								ps4.setInt(4,n);
								ps4.execute();
							}
							if(u>=1) {
								rs.next();
								int wid=rs.getInt(1);
								//System.out.println("YES EXISTS");
							String query3="insert into waterap values(?,?,?,?)";
							PreparedStatement ps4=con.prepareStatement(query3);
							ps4.setInt(1, wid);
							ps4.setInt(2, c);
							ps4.setInt(3, b);
							ps4.setInt(4,n);
							ps4.execute();
						}
						}
						if(d.equals("Milk")) {
							try {
								n=Integer.parseInt(textField_1.getText());
								b=Integer.parseInt(textField_2.getText());
								c=Integer.parseInt(textField_3.getText());
							
								}
								catch(NumberFormatException e1) {
									JOptionPane.showMessageDialog(frame, "Quantity and price should be numbers");
								}
							String query5="select * from milk where brand=?";
							PreparedStatement ps5=con.prepareStatement(query5);
							ps5.setString(1, textField.getText());
							int u=ps5.executeUpdate();
							ResultSet rs=ps5.executeQuery();
							if(u==0) {
							String query2="insert into milk values(seq_milk.nextval,?,?)";
							PreparedStatement ps2=con.prepareStatement(query2);
							ps2.setInt(1,7);
							ps2.setString(2,textField.getText());
							ps2.executeUpdate();
							ps2.close();
							String query4="select max(milk_id) from milk";
							Statement ps3=con.createStatement();
							ResultSet rs1=ps3.executeQuery(query4);
							rs1.next();
							int y=rs1.getInt(1);
							String query3="insert into milkap values(?,?,?,?)";
							PreparedStatement ps4=con.prepareStatement(query3);
							ps4.setInt(1, y);
							ps4.setInt(2, c);
							ps4.setInt(3, b);
							ps4.setInt(4,n);
							ps4.execute();
							}
							else {
								rs.next();
								int milkid=rs.getInt(1);
								String query3="insert into milkap values(?,?,?,?)";
								PreparedStatement ps4=con.prepareStatement(query3);
								ps4.setInt(1, milkid);
								ps4.setInt(2, c);
								ps4.setInt(3, b);
								ps4.setInt(4,n);
								ps4.execute();
								
							}
						}
						if(d.equals("Beverages")) {
							try {
								n=Integer.parseInt(textField_1.getText());
								b=Integer.parseInt(textField_2.getText());
								c=Integer.parseInt(textField_3.getText());
							
								}
								catch(NumberFormatException e1) {
									JOptionPane.showMessageDialog(frame, "Quantity and price should be numbers");
								}
							String query5="select * from beverages where brand=?";
							PreparedStatement ps5=con.prepareStatement(query5);
							ps5.setString(1, textField.getText());
							int u=ps5.executeUpdate();
							ResultSet rs=ps5.executeQuery();
							if(u==0) {
							String query2="insert into beverages values(seq_bevr.nextval,?,?)";
							PreparedStatement ps2=con.prepareStatement(query2);
							ps2.setInt(1,8);
							ps2.setString(2,textField.getText());
							ps2.executeUpdate();
							ps2.close();
							String query4="select max(beverages_id) from beverages";
							Statement ps3=con.createStatement();
							ResultSet rs1=ps3.executeQuery(query4);
							rs1.next();
							int y=rs1.getInt(1);
							String query3="insert into beveragesap values(?,?,?,?)";
							PreparedStatement ps4=con.prepareStatement(query3);
							ps4.setInt(1, y);
							ps4.setInt(2, c);
							ps4.setInt(3, b);
							ps4.setInt(4,n);
							ps4.execute();
							ps2.close();
							ps3.close();
							ps4.close();
							ps5.close();
							}else {
								rs.next();
								int bevid=rs.getInt(1);
								String query3="insert into beveragesap values(?,?,?,?)";
								PreparedStatement ps4=con.prepareStatement(query3);
								ps4.setInt(1, bevid);
								ps4.setInt(2, c);
								ps4.setInt(3, b);
								ps4.setInt(4,n);
								ps4.execute();
								ps4.close();
								
							}
						}
						if(d.equals("Solid")) {
							textField_3.setEditable(false);
							try {
								n=Integer.parseInt(textField_1.getText());
								b=Integer.parseInt(textField_2.getText());
								//if(d.equals("Water")||d.equals("Milk")||d.equals("Beverages")){
								//c=Integer.parseInt(textField_3.getText());
								//}
								}
								catch(NumberFormatException e1) {
									JOptionPane.showMessageDialog(frame, "Quantity and price should be numbers");
								}
							String query1="insert into solid values(seq_solid.nextval,?,?,?,?)";
							PreparedStatement ps1=con.prepareStatement(query1);
							ps1.setInt(1, 3);
							ps1.setString(2, textField.getText());
							ps1.setInt(3,n);
							ps1.setInt(4, b);
							ps1.executeUpdate();
						}
						if(d.equals("Fruits")) {
							textField_3.setEditable(false);
							try {
								n=Integer.parseInt(textField_1.getText());
								b=Integer.parseInt(textField_2.getText());
								//if(d.equals("Water")||d.equals("Milk")||d.equals("Beverages")){
								//c=Integer.parseInt(textField_3.getText());
								//}
								}
								catch(NumberFormatException e1) {
									JOptionPane.showMessageDialog(frame, "Quantity and price should be numbers");
								}
							String query1="insert into fruit values(seq_fruit.nextval,?,?,?,?)";
							PreparedStatement ps1=con.prepareStatement(query1);
							ps1.setInt(1, 4);
							ps1.setString(2, textField.getText());
							ps1.setInt(3,n);
							ps1.setInt(4, b);
							ps1.executeUpdate();
						}
						if(d.equals("Seasonal Fruits")) {
							textField_3.setEditable(false);
							try {
								n=Integer.parseInt(textField_1.getText());
								b=Integer.parseInt(textField_2.getText());
								//if(d.equals("Water")||d.equals("Milk")||d.equals("Beverages")){
								//c=Integer.parseInt(textField_3.getText());
								//}
								}
								catch(NumberFormatException e1) {
									JOptionPane.showMessageDialog(frame, "Quantity and price should be numbers");
								}
							java.util.Date date1 = new java.util.Date();
							long t = date1.getTime();
						      java.sql.Date sqlDate = new java.sql.Date(t);
							String query1="insert into seasonalfruit values(seq_sfruit.nextval,?,?,?,?,?)";
							PreparedStatement ps1=con.prepareStatement(query1);
							ps1.setInt(1, 5);
							ps1.setString(2, textField.getText());
							ps1.setInt(3,n);
							ps1.setInt(4, b);
							ps1.setDate(5, sqlDate);
							ps1.executeUpdate();
						}
						if(d.equals("Cereals")) {
							textField_3.setEditable(false);
							try {
								n=Integer.parseInt(textField_1.getText());
								b=Integer.parseInt(textField_2.getText());
								//if(d.equals("Water")||d.equals("Milk")||d.equals("Beverages")){
								//c=Integer.parseInt(textField_3.getText());
								//}
								}
								catch(NumberFormatException e1) {
									JOptionPane.showMessageDialog(frame, "Quantity and price should be numbers");
								}
							String query1="insert into cereal values(seq_cereal.nextval,?,?,?,?)";
							PreparedStatement ps1=con.prepareStatement(query1);
							ps1.setInt(1, 6);
							ps1.setString(2, textField.getText());
							ps1.setInt(3,n);
							ps1.setInt(4, b);
							ps1.executeUpdate();
						}
						
						}
					catch(ClassNotFoundException|SQLException e1 ) {
						e1.printStackTrace();
					}
					
					
					textField_3.setEditable(false);
					textField_3.setText("");
				}
				}
			}
		});
		btnNewButton.setBounds(354, 44, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Clear");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_3.setEditable(false);
				comboBox.setSelectedIndex(0);
				
			}
		});
		btnNewButton_1.setBounds(354, 103, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_1.setEditable(false);
				textField_2.setEditable(false);
				textField_3.setEditable(false);
				
				if(textField.getText().length()>2 && comboBox.getSelectedItem()!="Select") {
					int a=JOptionPane.showConfirmDialog(frame,"Are you sure you want to delete");
					if(a==JOptionPane.YES_OPTION) {
						try {
						String a1=textField.getText();
						String f=comboBox.getSelectedItem().toString();
						if(f.equals("Vegetables")) {
							String query="delete from vegetable where vegetable_name=?";
							Class.forName("oracle.jdbc.driver.OracleDriver");
							Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","tilakavati","tilak");
							PreparedStatement ps=con.prepareStatement(query);
							ps.setString(1,a1);
							int y=ps.executeUpdate();
							if(y==0) {
								JOptionPane.showMessageDialog(frame, "Item name doesnt exist");
							}
							else {
								JOptionPane.showMessageDialog(frame,"Deleted");
							}
							System.out.println(y);
						}
						if(f.equals("Water")) {
							String query="delete from water where brand=?";
							String query1="select wid from water where brand=?";
							Class.forName("oracle.jdbc.driver.OracleDriver");
							Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","tilakavati","tilak");
							PreparedStatement ps=con.prepareStatement(query);
							PreparedStatement ps1=con.prepareStatement(query1);
							ps.setString(1,a1);
							ps1.setString(1,a1);
							ResultSet rs=ps1.executeQuery();
							if(rs==null) {
								JOptionPane.showMessageDialog(frame, "Item name doesnt exist");
							}
							else {

								rs.next();
								int s=rs.getInt(1);
							String query2="delete from waterap where wid=?";
							PreparedStatement ps2=con.prepareStatement(query2);
							ps2.setInt(1, s);
							int h=ps2.executeUpdate();
							System.out.println(h);
							if(h==1) {
								JOptionPane.showMessageDialog(frame,"Deleted");
							}
							

						}
						}
						if(f.equals("Milk")) {
							String query="delete from milk where brand=?";
							Class.forName("oracle.jdbc.driver.OracleDriver");
							Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","tilakavati","tilak");
							PreparedStatement ps=con.prepareStatement(query);
							ps.setString(1,a1);
							int y=ps.executeUpdate();
							if(y==0) {
								JOptionPane.showMessageDialog(frame, "Item name doesnt exist");
							}
							else {
								JOptionPane.showMessageDialog(frame,"Deleted");
							}

						}
						if(f.equals("Beverages")) {
							String query="delete from beverages where brand=?";
							Class.forName("oracle.jdbc.driver.OracleDriver");
							Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","tilakavati","tilak");
							PreparedStatement ps=con.prepareStatement(query);
							ps.setString(1,a1);
							int y=ps.executeUpdate();
							if(y==0) {
								JOptionPane.showMessageDialog(frame, "Item name doesnt exist");
							}else {
								JOptionPane.showMessageDialog(frame,"Deleted");
							}

						}
						if(f.equals("Solid")) {
							String query="delete from solid where sname=?";
							Class.forName("oracle.jdbc.driver.OracleDriver");
							Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","tilakavati","tilak");
							PreparedStatement ps=con.prepareStatement(query);
							ps.setString(1,a1);
							int y=ps.executeUpdate();
							if(y==0) {
								JOptionPane.showMessageDialog(frame, "Item name doesnt exist");
							}else {
								JOptionPane.showMessageDialog(frame,"Deleted");
							}

						}
						if(f.equals("Fruits")) {
							String query="delete from fruit where name=?";
							Class.forName("oracle.jdbc.driver.OracleDriver");
							Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","tilakavati","tilak");
							PreparedStatement ps=con.prepareStatement(query);
							ps.setString(1,a1);
							int y=ps.executeUpdate();
							if(y==0) {
								JOptionPane.showMessageDialog(frame, "Item name doesnt exist");
							}else {
								JOptionPane.showMessageDialog(frame,"Deleted");
							}

						}
						if(f.equals("Seasonal Fruits")){
							String query="delete from seasonalfruit where name=?";
							Class.forName("oracle.jdbc.driver.OracleDriver");
							Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","tilakavati","tilak");
							PreparedStatement ps=con.prepareStatement(query);
							ps.setString(1,a1);
							int y=ps.executeUpdate();
							if(y==0) {
								JOptionPane.showMessageDialog(frame, "Item name doesnt exist");
							}else {
								JOptionPane.showMessageDialog(frame,"Deleted");
							}

						}
						if(f.equals("Cereals")) {
							String query="delete from cereal where name=?";
							Class.forName("oracle.jdbc.driver.OracleDriver");
							Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","tilakavati","tilak");
							PreparedStatement ps=con.prepareStatement(query);
							ps.setString(1,a1);
							int y=ps.executeUpdate();
							if(y==0) {
								JOptionPane.showMessageDialog(frame, "Item name doesnt exist");
							}else {
								JOptionPane.showMessageDialog(frame,"Deleted");
							}

						}
						}
						catch(ClassNotFoundException |SQLException e2) {
							e2.printStackTrace();
						}
						textField.setText("");
						textField_1.setEditable(true);
						textField_2.setEditable(true);
						comboBox.setSelectedIndex(0);
					}
					else {
						textField.setText("");
						textField_1.setEditable(true);
						textField_2.setEditable(true);
						comboBox.setSelectedIndex(0);
					}
				}
				
				else {
					JOptionPane.showMessageDialog(frame,"Write the name and select the category you want to delete");
				}
				
			}
		});
		btnNewButton_2.setBounds(354, 166, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Back");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_3.setBounds(354, 228, 89, 23);
		contentPane.add(btnNewButton_3);
		
		JLabel lblAvailability = new JLabel("Availability");
		lblAvailability.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAvailability.setBounds(30, 205, 86, 20);
		contentPane.add(lblAvailability);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(158, 207, 86, 20);
		contentPane.add(textField_3);
		textField_3.setEditable(false);
		
		
	}
}
