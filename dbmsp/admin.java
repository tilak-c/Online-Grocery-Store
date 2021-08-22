package dbmsp;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;

public class admin extends JFrame {

	/**
	 * 
	 */
	public static boolean admin=false;
	public static boolean user1=false;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin frame = new admin();
					frame.setTitle("Grocery Store");
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
	public admin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(27, 11, 461, 281);
		contentPane.add(lblNewLabel);
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/supermarket-management-system.jpg"));
		lblNewLabel.setIcon(icon);
		
		JButton btnNewButton = new JButton("Admin");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				admin=true;
				login.adminloginscreen();
				if(login.flag==true) {
				insadmin.adminscreen();
				}
			}
		});
		btnNewButton.setBounds(190, 52, 111, 37);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("User");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user1=true;
				user.userscreen();
			}
		});
		btnNewButton_1.setBounds(190, 117, 111, 37);
		contentPane.add(btnNewButton_1);
		
	}
}
