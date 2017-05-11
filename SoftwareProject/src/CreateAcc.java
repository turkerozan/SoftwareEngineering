import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
public class CreateAcc extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateAcc frame = new CreateAcc();
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
	Connection connection = null;
	private JTextField cName;
	private JTextField username;
	private JPasswordField password;
	public CreateAcc() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 458, 147);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		connection = sqlConnection.dbConnector();
		
		
		JLabel lblCompanyName = new JLabel("Company Name :");
		lblCompanyName.setBounds(10, 11, 82, 14);
		contentPane.add(lblCompanyName);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setBounds(10, 36, 82, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(219, 36, 58, 14);
		contentPane.add(lblPassword);
		
		cName = new JTextField();
		cName.setBounds(102, 8, 294, 20);
		contentPane.add(cName);
		cName.setColumns(10);
		
		username = new JTextField();
		username.setBounds(102, 33, 107, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		JButton iptal = new JButton("Cancel");
		iptal.setBounds(289, 74, 89, 23);
		contentPane.add(iptal);
		
		JButton olustur = new JButton("Create Account");
		olustur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				String query= "insert into Company (cName,username,password) values (?,?,?)";
				PreparedStatement pst = connection.prepareStatement(query);
				pst.setString(1, cName.getText());
				pst.setString(2, username.getText());
				pst.setString(3, password.getText());
				pst.execute();
				JOptionPane.showMessageDialog(null,  "Account created. Please login to add employer informations");
				pst.close();
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
		});
		olustur.setBounds(102, 74, 107, 23);
		contentPane.add(olustur);
		
		password = new JPasswordField();
		password.setBounds(289, 33, 107, 20);
		contentPane.add(password);
		
	}
}
