package dbmsp;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class cedit extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JTextArea textArea;
	private JTextField textField_4;
	ButtonGroup g1;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	
	/**
	 * Launch the application.
	 */
	public static void screen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cedit frame = new cedit();
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
	public cedit() {
		g1=new ButtonGroup();
		dispose();
		setBounds(400, 400, 697, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setBounds(35, 53, 85, 25);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(187, 53, 115, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Last Name");
		lblNewLabel_1.setBounds(35, 89, 85, 25);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Email ID");
		lblNewLabel_2.setBounds(35, 120, 85, 25);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Gender");
		lblNewLabel_3.setBounds(35, 156, 85, 25);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Address");
		lblNewLabel_4.setBounds(35, 192, 85, 25);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Age");
		lblNewLabel_5.setBounds(35, 259, 85, 25);
		contentPane.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("Phone Number");
		lblNewLabel_6.setBounds(35, 306, 85, 25);
		contentPane.add(lblNewLabel_6);
		
		textField_1 = new JTextField();
		textField_1.setBounds(187, 89, 115, 25);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(187, 125, 115, 25);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(187, 259, 115, 25);
		contentPane.add(textField_3);
		
		rdbtnNewRadioButton = new JRadioButton("Male");
		rdbtnNewRadioButton.setBounds(193, 157, 109, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1 = new JRadioButton("Female");
		rdbtnNewRadioButton_1.setBounds(298, 157, 109, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		
		rdbtnNewRadioButton_2 = new JRadioButton("Others");
		rdbtnNewRadioButton_2.setBounds(409, 157, 109, 23);
		contentPane.add(rdbtnNewRadioButton_2);
		g1.add(rdbtnNewRadioButton);
		g1.add(rdbtnNewRadioButton_1);
		g1.add(rdbtnNewRadioButton_2);
		textArea = new JTextArea();
		textArea.setBounds(187, 192, 209, 51);
		contentPane.add(textArea);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(187, 306, 115, 25);
		contentPane.add(textField_4);
		try {
			String query="select * from customer where customer_id=?";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","tilakavati","tilak");
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1, login.cid);
			ResultSet rs=ps.executeQuery();
			rs.next();
			textField.setText(rs.getString(2));
			textField_1.setText(rs.getString(3));
			textField_2.setText(rs.getString(4));//fn,ln  email age phonen
			textField_3.setText(rs.getInt(6)+"");
			textField_4.setText(rs.getString(7));
			textArea.setText(rs.getString(8));
			String y=rs.getString(5);
			if(y.equals("female")) {
				rdbtnNewRadioButton_1.setSelected(true);
			}
			else if(y.equals("male")) {
				rdbtnNewRadioButton.setSelected(true);
			}
			else {
				rdbtnNewRadioButton_2.setSelected(true);
			}
			
		}catch(SQLException|ClassNotFoundException e) {
			e.printStackTrace();
		}
		btnNewButton_1 = new JButton("Save");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField.getText().length()<3) {
						JOptionPane.showMessageDialog(null, "Please fill the first name field");
					}
					else if(textField_1.getText().length()<3) {
						JOptionPane.showMessageDialog(null, "Please fill the last name field");
					}
					else if(textField_2.getText().length()<3) {
						JOptionPane.showMessageDialog(null, "Please enter the emaild");
					}
					else if(g1.getSelection()==null) {
						JOptionPane.showMessageDialog(null, "Please select the gender");
					}
					else if(textArea.getText().length()<1) {
						JOptionPane.showMessageDialog(null, "Please fill the address field");
					}
					else if(textField_3.getText().length()<1) {
						JOptionPane.showMessageDialog(null, "Please fill age field");
					}
					else if(textField_4.getText().length()<3) {
						JOptionPane.showMessageDialog(null, "Please fill phone number field");
					}
					else {
					String gender="";
					if(rdbtnNewRadioButton.isSelected()) {
						gender="male";
					}
					else if(rdbtnNewRadioButton_1.isSelected()) {
						gender="female";
					}
					else  {
						gender="others";
					}
					String query="update customer set cfname=?,clname=?,email_id=?,gender=?,age=?,phone_no=?,address=? where customer_id=?";
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","tilakavati","tilak");
					PreparedStatement ps=con.prepareStatement(query);
					ps.setString(1,textField.getText());
					ps.setString(2,textField_1.getText());
					ps.setString(3,textField_2.getText());
					ps.setString(4,gender);
					ps.setInt(5,Integer.parseInt(textField_3.getText()));
					ps.setString(6, textField_4.getText());
					ps.setString(7, textArea.getText());
					ps.setInt(8,login.cid);
					ps.execute();
					JOptionPane.showMessageDialog(null, "Successfully saved");
					}
					dispose();
				}catch(SQLException|ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(100, 356, 100, 31);
		contentPane.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Back");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_2.setBounds(322, 356, 100, 31);
		contentPane.add(btnNewButton_2);
		
	}

}
