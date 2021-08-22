package dbmsp;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class cust extends JFrame {
//to be chckehed
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTable table;
	DefaultTableModel model;
	

	/**
	 * Launch the application.
	 */
	public static void custscreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cust frame = new cust();
					frame.setTitle("Customer Details");
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
	public cust() {
		dispose();
		setBounds(100, 100, 1170, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(241, 39, 903, 434);
		contentPane.add(scrollPane);
		table = new JTable();
		model=new DefaultTableModel();
		Object column[]= {"customer_id","first name","last name","email_id","gender","age","Phone no","address"};
		Object row[]=new Object[8];
		model.setColumnIdentifiers(column);
		
		table.setModel(model);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		table.getColumnModel().getColumn(5).setPreferredWidth(3);
		table.getColumnModel().getColumn(7).setPreferredWidth(90);
		scrollPane.setViewportView(table);
		
		
		JButton btnNewButton = new JButton("View Customer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				String y=JOptionPane.showInputDialog(null,"Enter the customer id");
				String query="select * from customer where customer_id=?";
				//if(y.equals("10")) {
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","tilakavati","tilak");
						PreparedStatement ps=con.prepareStatement(query);
						ps.setInt(1,Integer.parseInt(y));
						ResultSet rs=ps.executeQuery();
						if(rs.next()==false) {
							JOptionPane.showMessageDialog(null, "No customer_id");
						}
						else {
							row[0]=rs.getInt(1);
							row[1]=rs.getString(2);
							row[2]=rs.getString(3);
							row[3]=rs.getString(4);
							row[4]=rs.getString(5);
							row[5]=rs.getInt(6);
							row[6]=rs.getString(7);
							row[7]=rs.getString(8);
							model.addRow(row);
							ps.close();
						}
						}
					catch(SQLException|ClassNotFoundException e11) {
						e11.printStackTrace();
						
					}
					
					
			//	}
			//	else {
			//		JOptionPane.showMessageDialog(null, "No customer_id");
			//	}
			}
		});
		
		btnNewButton.setBounds(32, 39, 125, 35);
		contentPane.add(btnNewButton);
		table = new JTable();
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setViewportView(table);

		
		JButton btnNewButton_1 = new JButton("View all customers");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				String query="select * from customer";
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
					row[4]=rs1.getString(5);
					row[5]=rs1.getInt(6);
					row[6]=rs1.getString(7);
					row[7]=rs1.getString(8);
					model.addRow(row);
				}
			}
			catch(SQLException|ClassNotFoundException e11) {
				e11.printStackTrace();
			}}
		});
		btnNewButton_1.setBounds(32, 85, 148, 35);
		contentPane.add(btnNewButton_1);
		
		lblNewLabel = new JLabel("Select to view the details of customer ");
		lblNewLabel.setBounds(47, 11, 315, 33);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_1_1 = new JButton("Back");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1_1.setBounds(32, 131, 104, 35);
		contentPane.add(btnNewButton_1_1);
	}
}