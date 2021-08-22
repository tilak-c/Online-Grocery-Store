package dbmsp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class frame {

	private JFrame frame;
	private JTextField textField;
	private ButtonGroup g1;
	int sum=0;
	JRadioButton rdbtnNewRadioButton_1;
	/**
	 * Launch the application.
	 */
	public static void framescreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame window = new frame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public frame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		g1=new ButtonGroup();
		frame = new JFrame();
		frame.getContentPane().setBackground(UIManager.getColor("CheckBox.light"));
		frame.getContentPane().setLayout(null);
		frame.setTitle("Sales Info");
		
		JLabel lblNewLabel = new JLabel("Choose the date for sales,which you want to generate");
		lblNewLabel.setBounds(51, 11, 359, 32);
		frame.getContentPane().add(lblNewLabel);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(243, 72, 142, 32);
		frame.getContentPane().add(dateChooser);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Date");
		
		rdbtnNewRadioButton.setBounds(66, 100, 109, 23);
		frame.getContentPane().add(rdbtnNewRadioButton);
		g1.add(rdbtnNewRadioButton);
		JButton btnNewButton = new JButton("Get date");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				try {
				    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			    	String date =sdf.format(dateChooser.getDate().getTime());
		 java.util.Date dt_1=null;
			    	try {
			 dt_1=sdf.parse(date);
		        } catch (ParseException e1) {
		            e1.printStackTrace();
		        }
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","tilakavati","tilak");
					if(rdbtnNewRadioButton.isSelected()) {
						textField.setText("");
						PreparedStatement ps=con.prepareStatement("select sum(final_price) from sales where dop like ?");
						ps.setDate(1,new java.sql.Date(dt_1.getTime()));
						ResultSet rs=ps.executeQuery();
						rs.next();
						textField.setText(rs.getInt(1)+"");
					}
					if(rdbtnNewRadioButton_1.isSelected()) {
						textField.setText("");
						PreparedStatement ps=con.prepareStatement("select sum(final_price) from sales where dop like ?");
						//ps.setDate(1,new java.sql.Date(dt_1.getTime()));
						SimpleDateFormat df=new SimpleDateFormat("dd-MMM-yyyy");
						String s=df.format(dateChooser.getDate()).toUpperCase();
						//String u="'"+"%"+s.substring(3,6)+"%"+"'";
						
						ps.setString(1,"'%"+s.substring(3,6)+"%'");
						ResultSet rs=ps.executeQuery();
						rs.next();
						textField.setText(rs.getInt(1)+"");
						//System.out.println(u);
						System.out.println(s);
						System.out.println(s.substring(3,6));
					}
					else {
						JOptionPane.showMessageDialog(null, "click on date and then select the date");
					}
					
					
				}	catch(SQLException|ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			
			}
		
				
		});
		btnNewButton.setBounds(51, 204, 109, 23);
		frame.getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(243, 115, 126, 47);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setEditable(false);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(191, 204, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Clear");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g1.clearSelection();
				dateChooser.setCalendar(null);
				textField.setText("");
				
			}
		});
		btnNewButton_2.setBounds(308, 204, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		rdbtnNewRadioButton_1= new JRadioButton("Month");
		rdbtnNewRadioButton_1.setBounds(66, 139, 109, 23);
		frame.getContentPane().add(rdbtnNewRadioButton_1);
		frame.setBounds(100, 100, 450, 300);
		frame.dispose();
	}
}
