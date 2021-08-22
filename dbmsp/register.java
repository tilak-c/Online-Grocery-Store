package dbmsp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
public class register extends JFrame {

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
	private JButton btnNewButton;
	private JButton btnClear;
	private JLabel lblNewLabel_7;
	ButtonGroup g1;
	Component frame=null;
	public static String fn="";
	public static int id;
	/**
	 * Launch the application.
	 */
	public static void registerscreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					register frame = new register();
					frame.setTitle("User Registration Form");
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
	public register() {
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
		lblNewLabel_6.setBounds(35, 295, 85, 25);
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
		textField_4.setBounds(187, 295, 115, 25);
		contentPane.add(textField_4);
		
		btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().length()<3) {
					JOptionPane.showMessageDialog(frame, "Please fill the first name field");
				}
				else if(textField_1.getText().length()<3) {
					JOptionPane.showMessageDialog(frame, "Please fill the last name field");
				}
				else if(textField_2.getText().length()<3) {
					JOptionPane.showMessageDialog(frame, "Please enter the emaild");
				}
				else if(g1.getSelection()==null) {
					JOptionPane.showMessageDialog(frame, "Please select the gender");
				}
				else if(textArea.getText().length()<1) {
					JOptionPane.showMessageDialog(frame, "Please fill the address field");
				}
				else if(textField_3.getText().length()<1) {
					JOptionPane.showMessageDialog(frame, "Please fill age field");
				}
				else if(textField_4.getText().length()<3) {
					JOptionPane.showMessageDialog(frame, "Please fill phone number field");
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
				dispose();
					String query="insert into customer values(seq_cust.nextval,?,?,?,?,?,?,?)";
					try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","tilakavati","tilak");
					PreparedStatement ps=con.prepareStatement(query);
					ps.setString(1, textField.getText());
					ps.setString(2, textField_1.getText());
					ps.setString(3, textField_2.getText());
					ps.setString(4, gender);
					ps.setInt(5, Integer.parseInt(textField_3.getText()));
					ps.setString(6, textField_4.getText());
					ps.setString(7, textArea.getText());
					ps.execute();
					
					ps.close();
					con.close();
					fn=textField.getText();
					
					String query1="select max(customer_id) from customer";
					Connection con1=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","tilakavati","tilak");
					PreparedStatement ps1=con1.prepareStatement(query1);
					ResultSet rs=ps1.executeQuery();
					rs.next();
					id=rs.getInt(1);
					logsc.screen();
					
				}
					catch(ClassNotFoundException|SQLException e1 ) {
						e1.printStackTrace();
					}
			}
			}
		});
		btnNewButton.setBounds(133, 343, 95, 36);
		contentPane.add(btnNewButton);
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				g1.clearSelection();
				textArea.setText("");
			}
		});
		btnClear.setBounds(334, 343, 102, 36);
		contentPane.add(btnClear);
		
		lblNewLabel_7 = new JLabel("Register Form");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_7.setBackground(new Color(240, 240, 240));
		lblNewLabel_7.setBounds(58, 8, 157, 34);
		contentPane.add(lblNewLabel_7);
	}

}
