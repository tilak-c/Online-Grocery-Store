package dbmsp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class usersc extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static int s=0;
	public static int us=0;
	public static int am=0;
	public static void screen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					usersc frame = new usersc();
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
	public usersc() {
		dispose();
		setBounds(100, 100, 520, 337);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Buy");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				items.itemscreen();
			}
		});
		btnNewButton.setBounds(162, 23, 133, 39);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Previous Orders");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prevord.screen();
			}
		});
		btnNewButton_1.setBounds(162, 92, 133, 39);
		contentPane.add(btnNewButton_1);
		
		
		JButton btnNewButton_1_2 = new JButton("Logout");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			dispose();
			}
		});
		btnNewButton_1_2.setBounds(162, 226, 133, 39);
		contentPane.add(btnNewButton_1_2);
		
		JButton btnNewButton_1_2_1 = new JButton("Edit");
		btnNewButton_1_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cedit.screen();
			}
		});
		btnNewButton_1_2_1.setBounds(162, 156, 133, 39);
		contentPane.add(btnNewButton_1_2_1);
	}
}
