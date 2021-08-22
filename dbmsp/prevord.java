package dbmsp;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class prevord extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	DefaultTableModel model,model1;
	private JTable table_1;
	/**
	 * Launch the application.
	 */
	public static void screen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					prevord frame = new prevord();
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
	public prevord() {
		dispose();
		setBounds(100, 100, 607, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		model=new DefaultTableModel();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 11, 531, 126);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		Object column[]= {"sno","name","price","quantity","total"};
		Object row[]=new Object[5];
		table.setModel(model);
		scrollPane.setViewportView(table);
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		model.setColumnIdentifiers(column);
		btnNewButton.setBounds(229, 294, 103, 39);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(42, 167, 480, 87);
		contentPane.add(scrollPane_1);
		
		Object column1[]= {"date of purchase","time of purchase","total Price"};
		Object row1[]=new Object[3];
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		model1=new DefaultTableModel();
		table_1.setModel(model1);
		scrollPane_1.setViewportView(table_1);
		model1.setColumnIdentifiers(column1);
		
		try {
				String query="select * from prevorder where customer_id=?";
				String query1="select * from sales where customer_id=?";
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","tilakavati","tilak");
				PreparedStatement ps=con.prepareStatement(query);
				PreparedStatement ps1=con.prepareStatement(query1);
				
				ps.setInt(1, login.cid);
				ps1.setInt(1, login.cid);
				ResultSet rs=ps.executeQuery();
				ResultSet rs1=ps1.executeQuery();
				while(rs.next() ) {
					if(rs.getInt(2)==1) {
						 model.insertRow(table.getRowCount(),new Object[]{"","","","",""});
						}
				row[0]=rs.getInt(2);
				row[1]=rs.getString(3);
				row[2]=rs.getInt(4);
				row[3]=rs.getInt(5);
				row[4]=rs.getInt(6);
				model.addRow(row);
				}
				while(rs1.next()) {
					row1[0]=rs1.getDate(3);
			    	row1[1]=rs1.getString(5);
					row1[2]=String.valueOf(rs1.getInt(4));
					model1.addRow(row1);
					
				}
	}
		catch(SQLException|ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
