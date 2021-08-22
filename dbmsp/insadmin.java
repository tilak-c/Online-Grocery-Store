package dbmsp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class insadmin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void adminscreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					insadmin frame = new insadmin();
					frame.setTitle("admin login");
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
	public insadmin() {
		//setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		dispose();
		setBounds(100, 100, 529, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Add Items");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			additems.screen();
			}
		});
		btnNewButton.setBounds(164, 70, 140, 39);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Sales Report");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.framescreen();
			}
		});
		btnNewButton_1.setBounds(164, 132, 140, 39);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Customer Details");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cust.custscreen();
			}
		});
		btnNewButton_2.setBounds(164, 193, 140, 39);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Logout");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(usersc.am==1) {
					dispose();
				}
				dispose();
			}
		});
		btnNewButton_3.setBounds(164, 255, 140, 39);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Employees");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vemp.screen();
			}
		});
		btnNewButton_4.setBounds(164, 11, 140, 39);
		contentPane.add(btnNewButton_4);
	}
}

