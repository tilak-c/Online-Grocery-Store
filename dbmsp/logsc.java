package dbmsp;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class logsc extends JFrame {

	private static final long serialVersionUID = 1L;
	//reg rg=new reg();
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	public static int logintext;
	Component frame=null;
	public static boolean suc=false;

	/**
	 * Launch the application.
	 */
	public static void screen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					logsc frame = new logsc();
					frame.setTitle("Password Confirmation");
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
	public logsc() {
			//dispose();
			setBounds(100, 100, 599, 384);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("UserName");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel.setBounds(65, 60, 108, 36);
			contentPane.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Password");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_1.setBounds(65, 127, 108, 36);
			contentPane.add(lblNewLabel_1);
			
			textField = new JTextField();
			textField.setBounds(265, 60, 145, 36);
			contentPane.add(textField);
			textField.setColumns(10);
			
			JLabel lblNewLabel_2 = new JLabel("Confirm Password");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_2.setBounds(65, 199, 110, 36);
			contentPane.add(lblNewLabel_2);
			
			JButton btnNewButton = new JButton("Save");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
					String query1="select * from customer where cfname=?";
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con1=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","tilakavati","tilak");
					PreparedStatement ps1=con1.prepareStatement(query1);
					ps1.setString(1, textField.getText());
					ps1.executeUpdate();
					String p1=String.valueOf(passwordField.getPassword());
					String p2=String.valueOf(passwordField_1.getPassword());
					if(p1.length()<8) {
						Component frame = null;
						JOptionPane.showMessageDialog(frame, "passwords must be 8 character long");
						
					}
					else if(p1.compareTo(p2)!=0 || p1.length()<8) {
						Component frame = null;
						JOptionPane.showMessageDialog(frame, "passwords must match");
					}
					
					
					else if(p1.compareTo(p2)==0 && p1.length()>=8){
							String query="insert into grlogin values(?,?,?)";
							Class.forName("oracle.jdbc.driver.OracleDriver");
							Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","tilakavati","tilak");
							PreparedStatement ps=con.prepareStatement(query);
							
							ps.setString(1,textField.getText());
							ps.setString(2,String.valueOf(passwordField.getPassword()));
							ps.setInt(3, register.id);
							suc=true;
							ps.executeUpdate();
							dispose();
							setVisible(false);
							ps.close();
							con.close();
							
						}
				
					}
						 catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							//e1.printStackTrace();
					JOptionPane.showMessageDialog(null,"Username already exists","Error message" ,JOptionPane.ERROR_MESSAGE);
					
						}
						
				}
				
				
			});
			btnNewButton.setBounds(84, 274, 126, 36);
			contentPane.add(btnNewButton);
			
			JButton btnNewButton_1 = new JButton("Clear");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					passwordField.setText("");
					passwordField_1.setText("");
					
				}
			});
			btnNewButton_1.setBounds(251, 274, 116, 36);
			contentPane.add(btnNewButton_1);
			
			JButton btnNewButton_2 = new JButton("Back");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					setVisible(false);
				}
			});
			btnNewButton_2.setBounds(407, 274, 116, 36);
			contentPane.add(btnNewButton_2);
			
			passwordField = new JPasswordField();
			passwordField.setBounds(265, 129, 145, 36);
			contentPane.add(passwordField);
			
			passwordField_1 = new JPasswordField();
			passwordField_1.setBounds(265, 201, 145, 36);
			contentPane.add(passwordField_1);
			
		}
	
	

}
