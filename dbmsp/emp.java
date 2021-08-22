package dbmsp;
import java.sql.*;
import java.sql.Date;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.awt.Component;
import java.awt.EventQueue;
import java.text.ParseException;  
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class emp extends JFrame  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblJobTitle;
	private JLabel lblLastName_2;
	private JLabel lblLastName_3;
	private JLabel lblLastName_4;
	private JLabel lblLastName_5;
	private JLabel lblLastName_6;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	Component frame=null;
	String jt[]= {"Manager","Senior Analyst","Analyst","Operator"};
	private JTextField textField_5;
	private static final String regex = "^(.+)@(.+)$";
	private JTable table;
	DefaultTableModel model;
	private JScrollPane scrollPane;
	private JButton btnNewButton_3;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox ;
	private JDateChooser dateChooser;
	private JDateChooser dateChooser_1;
	/**
	 * Launch the application.
	 */
	public static void empscreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					emp frame = new emp();
					frame.setTitle("Employees");
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
	public emp() {
		dispose();
		setBounds(100, 100, 1223, 625);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=table.getSelectedRow();
				textField.setText(model.getValueAt(i, 1).toString());
				textField_1.setText(model.getValueAt(i, 2).toString());
				comboBox.setSelectedItem(model.getValueAt(i, 3));
				dateChooser.setDate((java.util.Date) model.getValueAt(i, 4));
				dateChooser_1.setDate((java.util.Date) model.getValueAt(i, 5));
				textField_4.setText(model.getValueAt(i, 6).toString());
				textField_2.setText(model.getValueAt(i, 7).toString());
				textField_5.setText(model.getValueAt(i, 8).toString());
				textField_3.setText(model.getValueAt(i, 9).toString());
			}
		});
		scrollPane.setBounds(324, 21, 873, 554);
		contentPane.add(scrollPane);
		
		
		table = new JTable();
		model=new DefaultTableModel();
		table.setDefaultEditor(Object.class, null);
		Object column[]= {"id","first name","last name","designation","dob","doj","salary","address","email-id","phone no"};
		Object row[]=new Object[10];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setBounds(32, 38, 77, 23);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(159, 33, 124, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(32, 78, 77, 23);
		contentPane.add(lblLastName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(159, 79, 124, 28);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		lblJobTitle = new JLabel("Job Title");
		lblJobTitle.setBounds(32, 138, 77, 23);
		contentPane.add(lblJobTitle);
		
		lblLastName_2 = new JLabel("Date of Birth");
		lblLastName_2.setBounds(32, 185, 77, 23);
		contentPane.add(lblLastName_2);
		
		lblLastName_3 = new JLabel("Date of Joining");
		lblLastName_3.setBounds(32, 219, 94, 23);
		contentPane.add(lblLastName_3);
		
		lblLastName_4 = new JLabel("Salary");
		lblLastName_4.setBounds(32, 268, 77, 23);
		contentPane.add(lblLastName_4);
		
		lblLastName_5 = new JLabel("Address");
		lblLastName_5.setBounds(32, 316, 77, 23);
		contentPane.add(lblLastName_5);
		
		lblLastName_6 = new JLabel("Mobile Number");
		lblLastName_6.setBounds(32, 417, 89, 23);
		contentPane.add(lblLastName_6);
		
		comboBox= new JComboBox(jt);
		comboBox.setBounds(159, 138, 124, 22);
		contentPane.add(comboBox);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(159, 180, 124, 28);
		contentPane.add(dateChooser);
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(159, 219, 124, 28);
		contentPane.add(dateChooser_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(159, 313, 124, 28);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(159, 414, 124, 28);
		contentPane.add(textField_3);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().trim().length()<2  ) {
					JOptionPane.showMessageDialog(frame, "Please fill the first name field");
				}
				else if(textField_1.getText().trim().length()<2) {
					JOptionPane.showMessageDialog(frame, "Please fill the last name field");
				}
				else if(comboBox.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(frame, "Please select the role");
				}
				else if(dateChooser.getCalendar()==null || dateChooser_1.getCalendar()==null) {
					JOptionPane.showMessageDialog(frame, "Please select the dates");
				}
				else if(textField_4.getText().trim().length()<2) {
					JOptionPane.showMessageDialog(frame, "Please fill the salary field");
				}else if(textField_2.getText().trim().length()<2) {
					JOptionPane.showMessageDialog(frame, "Please fill the address field");
				}
				else if(textField_5.getText().trim().length()<2) {
					JOptionPane.showMessageDialog(frame, "Please enter valid email");
				}
				else if(textField_5.getText().trim().length()<2) {
					boolean f=false;
					Pattern pattern=Pattern.compile(regex);
					Matcher matcher = pattern.matcher(textField_5.getText().trim());
					
					if(matcher.matches()? f=true:false) {
						if(f==false) {
							JOptionPane.showMessageDialog(frame,"Emaild ID is invalid");
						}
					}
				}
				else if(textField_3.getText().trim().length()<2) {
					JOptionPane.showMessageDialog(frame, "Please fill the phone number");
				}
				else if(textField_3.getText().trim().length()!=10) {
					JOptionPane.showMessageDialog(frame, "Phone number must contain 10 digits");
				}
				else {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
				String date = sdf.format(dateChooser.getDate().getTime());
				String date_1=sdf.format(dateChooser_1.getDate().getTime());
				java.util.Date dt_1=null;
				try {
					dt_1 = sdf.parse(date);
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				java.util.Date dt_2 = null;
				try {
					dt_2 = sdf.parse(date_1);
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
					try {
						String query="insert into employee values(seq_emp.nextval,?,?,?,?,?,?,?,?,?)";
						Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","tilakavati","tilak");
						PreparedStatement ps=con.prepareStatement(query);
						ps.setString(1,textField.getText());
						ps.setString(2,textField_1.getText());
						ps.setString(3,(String) comboBox.getSelectedItem());
						String g=textField_3.getText();
						ps.setDate(4,new java.sql.Date(dt_1.getTime()));
						ps.setDate(5,new java.sql.Date(dt_2.getTime()));
						ps.setInt(6,Integer.parseInt(textField_4.getText().trim()));
						ps.setString(7,textField_2.getText());
						ps.setString(8,textField_5.getText());
						ps.setString(9,textField_3.getText());

						int y=ps.executeUpdate();
							System.out.println(y);
						PreparedStatement ps1=con.prepareStatement("select eid from employee where empphone_no=?");
						ps1.setString(1,g);
						ResultSet rs=ps1.executeQuery();
						rs.next();
						int sds=rs.getInt(1);
						
						row[0]=sds;
						row[1]=textField.getText();
						row[2]=textField_1.getText();
						row[3]=(String) comboBox.getSelectedItem();
						row[4]=new java.sql.Date(dt_1.getTime());
						row[5]=new java.sql.Date(dt_2.getTime());
						row[6]=textField_4.getText().trim();
						row[7]=textField_2.getText();
						row[8]=textField_5.getText();
						row[9]=textField_3.getText();
						model.addRow(row);
						
						
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
						textField_3.setText("");
						textField_4.setText("");
						textField_5.setText("");
						dateChooser.setCalendar(null);
						dateChooser_1.setCalendar(null);
						comboBox.setSelectedIndex(0); 
						ps.close();
						con.close();
						
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(frame, "Saved");
				}
				}
		});
		btnNewButton.setBounds(20, 463, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Clear");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				dateChooser.setCalendar(null);
				dateChooser_1.setCalendar(null);
				comboBox.setSelectedIndex(0); 
				
			}
		});
		btnNewButton_1.setBounds(20, 509, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectionModel().isSelectionEmpty()) {
				JOptionPane.showMessageDialog(null,"To delete click on view all button and then select a row to delete");
				}
				else{
					int i=table.getSelectedRow();
				if(i>=0) {
					int y=(int) table.getModel().getValueAt(0, 0);
					String query="delete from employee where eid=?";
					try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","tilakavati","tilak");
					PreparedStatement ps=con.prepareStatement(query);
					ps.setInt(1, y);
					ps.execute();
					ps.close();
					}
					catch(SQLException|ClassNotFoundException e1) {
						e1.printStackTrace();
						
					}
					model.removeRow(i);
					JOptionPane.showMessageDialog(null, "successfully deleted");
				}
				else {
					JOptionPane.showMessageDialog(null, "Please select a row");
				}
				}
			}
		});
		btnNewButton_2.setBounds(179, 509, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("Back");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				//frame.setVisible(false);
			}
		});
		btnNewButton_2_1.setBounds(179, 552, 89, 23);
		contentPane.add(btnNewButton_2_1);
		
		JButton btnNewButton_1_1 = new JButton("Update");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			java.sql.Date date=null;
			if(table.getSelectionModel().isSelectionEmpty()) {
			JOptionPane.showMessageDialog(null,"To update click on view all button and then select a row to update");
			}
			else {
				java.sql.Date date1=null;
				int i=table.getSelectedRow();
				model.setValueAt(textField.getText(),i,1);
				model.setValueAt(textField_1.getText(),i,2);
				model.setValueAt((String) comboBox.getSelectedItem(),i,3);
				model.setValueAt(textField_4.getText(),i,6);
				model.setValueAt(textField_2.getText(),i,7);
				model.setValueAt(textField_5.getText(),i,8);
				model.setValueAt(textField_3.getText(),i,9);
				System.out.println(i);
				System.out.println(model.getValueAt(i,1));
				try {
				date=(Date) model.getValueAt(i,4);
				date1=(Date)model.getValueAt(i, 5);
					model.setValueAt(new java.sql.Date(date.getTime()), i, 4);
				model.setValueAt(new java.sql.Date(date1.getTime()), i, 5);
					String query="update employee set efname=?,elname=?,job_title=?,dob=?,doj=?,salary=?,address=?,email_id=?,empphone_no=? where empphone_no=?";
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","tilakavati","tilak");
					PreparedStatement ps=con.prepareStatement(query);
					ps.setString(1,(String)model.getValueAt(i, 1));
					ps.setString(2,(String)model.getValueAt(i, 2));
					ps.setString(3,(String) comboBox.getSelectedItem());
					textField_3.getText();
					ps.setDate(4,new java.sql.Date(date.getTime()));
					ps.setDate(5,new java.sql.Date(date1.getTime()));
					ps.setInt(6,Integer.parseInt(textField_4.getText().trim()));
					ps.setString(7,textField_2.getText());
					ps.setString(8,(String)table.getModel().getValueAt(i, 7));
					ps.setString(9,textField_3.getText());
					ps.setString(10,textField_3.getText());
					int u=ps.executeUpdate();
					System.out.println(u);
				}
				catch(SQLException|ClassNotFoundException  e1) {
					e1.printStackTrace();
				}
			}
			}
		});
		
		btnNewButton_1_1.setBounds(179, 463, 89, 23);
		contentPane.add(btnNewButton_1_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(159, 265, 124, 28);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(159, 366, 124, 28);
		contentPane.add(textField_5);
		
		JLabel lblLastName_6_1 = new JLabel("Email ID");
		lblLastName_6_1.setBounds(32, 366, 77, 23);
		contentPane.add(lblLastName_6_1);
		
		btnNewButton_3 = new JButton("View all");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				String query="select * from employee";
		try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","tilakavati","tilak");
				PreparedStatement ps=con.prepareStatement(query);
				ResultSet rs1=ps.executeQuery();
				while(rs1.next()) {
					row[0]=rs1.getInt(1);
					row[1]=rs1.getString(2);
					row[2]=rs1.getString(3);
					row[3]=rs1.getString(4);
					row[4]=rs1.getDate(5);
					row[5]=rs1.getDate(6);
					row[6]=rs1.getInt(7);
					row[7]=rs1.getString(8);
					row[8]=rs1.getString(9);
					row[9]=rs1.getString(10);
					model.addRow(row);
				}
			}
			
			catch(SQLException|ClassNotFoundException e11) {
				e11.printStackTrace();
			}
			}
		});
		btnNewButton_3.setBounds(20, 552, 89, 23);
		contentPane.add(btnNewButton_3);
	}
}

