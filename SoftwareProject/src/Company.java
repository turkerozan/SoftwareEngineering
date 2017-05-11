import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Company extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Company frame = new Company(-1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection = null;
	
	/**
	 * Create the frame.
	 */
	public Company(int x) {
		int id = x;
		if(id == -1 ){
			JOptionPane.showMessageDialog(null, "Error while loading dataset");
		}
		connection = sqlConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton addE = new JButton("Employee Operations");
		addE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					EmployeeAdd ead = new EmployeeAdd(id);
					ead.setVisible(true);
					
					/*String query="select ad,soyad,tc,cinsiyet,saglik,guvenlik from Employee where cId = ?";
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.setInt(1, id);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					*/
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		addE.setBounds(354, 61, 135, 23);
		contentPane.add(addE);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 95, 452, 241);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
