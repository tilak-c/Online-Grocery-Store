package dbmsp;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.UIManager;

public class login extends JFrame {

	/**
	 * 
	 */
	public static boolean flag=false;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	protected Component frame;
	String a,b;
	public static int cid;
	public static int di;
	static login frame1;
	static String name="";
//	public static int 
	/**
	 * Launch the application.
	 */
	public static void adminloginscreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setTitle("Grocery Store");
					frame.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				} 
		});
	}

	/**
	 * Create the frame.
	 */
	public login() {
		dispose();
		setBounds(100, 100, 601, 353);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("InternalFrame.resizeIconHighlight"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		if(usersc.s==1) {
			dispose();
		}
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(77, 59, 102, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(77, 146, 102, 34);
		contentPane.add(lblNewLabel_1);
		
		
		textField = new JTextField();
		textField.setBounds(263, 63, 161, 34);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(263, 146, 161, 34);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBackground(UIManager.getColor("Button.disabledShadow"));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s=textField.getText();
				char[] p=passwordField.getPassword();
				String pass=String.valueOf(p);
				if(admin.admin==true) {
					if(pass.equals("") && s.trim().equals("")) {
						JOptionPane.showMessageDialog(frame,"Please fill out the username and password fields");
					}
					else if(pass.equals("") && s.length()>0) {
						JOptionPane.showMessageDialog(frame, "Please fill out the password field");
					}
					else if(pass.length()>0 && s.length()==0) {
						JOptionPane.showMessageDialog(frame, "Please fill out the user field");
					}
					else if(s.trim().equals("admin")&& pass.trim().equals("admin@123")) {
					setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					
					dispose();
					flag=true;
					insadmin.adminscreen();
					admin.admin=false;
					flag=false;
				}
				else {
					JOptionPane.showMessageDialog(frame,"incorrect username or password");
				}
				}
				else {
				try {
						String query="select * from grlogin where username=?";
						Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","tilakavati","tilak");
						PreparedStatement ps=con.prepareStatement(query);
						ps.setString(1, textField.getText());
						//ps.setInt(1,);
						ResultSet rs=ps.executeQuery();
						rs.next();
						a=rs.getString(1);
						b=rs.getString(2);
						cid=rs.getInt(3);
						name=a;
					}
					catch(ClassNotFoundException|SQLException e1) {
						e1.printStackTrace();
					}
					if(pass.equals("") && s.trim().equals("")) {
						JOptionPane.showMessageDialog(frame,"Please fill out the username and password fields");
					}
					else if(pass.equals("") && s.length()>0) {
						JOptionPane.showMessageDialog(frame, "Please fill out the password field");
					}
					else if(pass.length()>0 && s.length()==0) {
						JOptionPane.showMessageDialog(frame, "Please fill out the user field");
					}
					else if(s.equals(a) && pass.equals(b)) {
						JOptionPane.showMessageDialog(frame,"Hurray! You have made it");
						
						admin.user1=false;
						usersc.screen();
						dispose();
						setVisible(false);
						di=cid;
					}
					else {
					JOptionPane.showMessageDialog(frame,"incorrect username or password");
				}
				}
					
			}
		});
		btnNewButton.setBounds(107, 235, 134, 40);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Clear");
		btnNewButton_1.setBackground(UIManager.getColor("Button.disabledShadow"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				passwordField.setText("");
				
			}
		});
		btnNewButton_1.setBounds(337, 235, 134, 40);
		contentPane.add(btnNewButton_1);
	}
}
