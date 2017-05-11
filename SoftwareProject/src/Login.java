import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.sql.*;
import javax.swing.*;
import java.awt.Font;

public class Login {

	private JFrame frame;
	private JTextField uField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	Connection connection = null;
	private JPasswordField pField;
	public Login() {
		initialize();
		connection = sqlConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 310, 214);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					String query = "select * from Company where username=? and password=? ";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1, uField.getText());
					pst.setString(2, pField.getText());
					int x = -1;
					ResultSet rs = pst.executeQuery();
					int count = 0;
					while(rs.next()){
						count++;
						x = rs.getInt(1);
					}
					if(count == 1){
						JOptionPane.showMessageDialog(null, "Connection Established");
						frame.dispose();
						
						Company cInfo = new Company(x);
						cInfo.setVisible(true);
					}
					else{
						JOptionPane.showMessageDialog(null, "Username or Password is not correct");
					}
					rs.close();
					pst.close();
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnLogin.setBackground(Color.LIGHT_GRAY);
		btnLogin.setBounds(32, 107, 107, 23);
		frame.getContentPane().add(btnLogin);
		
		uField = new JTextField();
		uField.setBounds(120, 32, 86, 20);
		frame.getContentPane().add(uField);
		uField.setColumns(10);
		
		JLabel username = new JLabel("Username");
		username.setBounds(50, 35, 60, 14);
		frame.getContentPane().add(username);
		
		JLabel password = new JLabel("Password");
		password.setBounds(50, 60, 46, 14);
		frame.getContentPane().add(password);
		
		pField = new JPasswordField();
		pField.setBounds(120, 57, 86, 20);
		frame.getContentPane().add(pField);
		
		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.setBackground(Color.LIGHT_GRAY);
		btnCreateAccount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCreateAccount.setBounds(149, 107, 107, 23);
		frame.getContentPane().add(btnCreateAccount);
	}
}
