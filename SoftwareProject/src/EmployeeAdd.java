import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
public class EmployeeAdd extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeAdd frame = new EmployeeAdd(157);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection = null;
	private JTextField ad;
	private JTextField soyad;
	private JTextField tc;
	private JTextField saglik;
	private JTextField cinsiyet;
	/**
	 * Create the frame.
	 */
	public EmployeeAdd(int x) {
		if(x == -1){
			//lol
			JOptionPane.showMessageDialog(null, "Error while loading dataset");
		}
		connection = sqlConnection.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 741, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton loadTable = new JButton("Show ");
		loadTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					
					String query="select ad,soyad,tc,cinsiyet,saglik,guvenlik from Employee where cId = ?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setInt(1, x);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		loadTable.setBounds(10, 11, 89, 23);
		contentPane.add(loadTable);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 70, 512, 295);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Ad");
		lblNewLabel.setBounds(532, 71, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Soyad");
		lblNewLabel_1.setBounds(532, 96, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Tc");
		lblNewLabel_2.setBounds(532, 121, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Sa\u011Fl\u0131k");
		lblNewLabel_3.setBounds(532, 146, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel Cinslb = new JLabel("Cinsiyet");
		Cinslb.setBounds(532, 171, 46, 14);
		contentPane.add(Cinslb);
		
		ad = new JTextField();
		ad.setBounds(629, 68, 86, 20);
		contentPane.add(ad);
		ad.setColumns(10);
		
		soyad = new JTextField();
		soyad.setColumns(10);
		soyad.setBounds(629, 93, 86, 20);
		contentPane.add(soyad);
		
		tc = new JTextField();
		tc.setColumns(10);
		tc.setBounds(629, 118, 86, 20);
		contentPane.add(tc);
		
		saglik = new JTextField();
		saglik.setColumns(10);
		saglik.setBounds(629, 143, 86, 20);
		contentPane.add(saglik);
		
		JDateChooser guvenlik = new JDateChooser();
		guvenlik.setBounds(629, 196, 86, 20);
		contentPane.add(guvenlik);
		
		JButton btnEkle = new JButton("Ekle");
		btnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query= "insert into Employee (cId,ad,soyad,TC,cinsiyet,saglik,guvenlik) values (?,?,?,?,?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setInt(1, x);
					pst.setString(2, ad.getText());
					pst.setString(3, soyad.getText());
					pst.setString(4, tc.getText());
					pst.setString(5, cinsiyet.getText());
					pst.setString(6, saglik.getText());
					pst.setString(7, ((JTextField)guvenlik.getDateEditor().getUiComponent()).getText());
					pst.execute();
					JOptionPane.showMessageDialog(null, ad.getText() + " added successfully");
					pst.close();
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		btnEkle.setBounds(626, 234, 89, 23);
		contentPane.add(btnEkle);
		
		
		JLabel labellote = new JLabel("G\u00FCvenlik");
		labellote.setBounds(532, 202, 46, 14);
		contentPane.add(labellote);
		
		cinsiyet = new JTextField();
		cinsiyet.setColumns(10);
		cinsiyet.setBounds(629, 168, 86, 20);
		contentPane.add(cinsiyet);
	}
}
