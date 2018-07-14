package chat.cliente.userInterface;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ServerSeter extends JFrame {

	private JPanel contentPane;
	private JTextField lblIpServidor;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerSeter frame = new ServerSeter();					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ServerSeter() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIpDelServer = new JLabel("Ip del server:");
		lblIpDelServer.setFont(new Font("Arial", Font.BOLD, 13));
		lblIpDelServer.setBounds(10, 74, 147, 14);
		contentPane.add(lblIpDelServer);
		
		lblIpServidor = new JTextField();
		lblIpServidor.setBounds(157, 72, 267, 20);
		contentPane.add(lblIpServidor);
		lblIpServidor.setColumns(10);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Login(lblIpServidor.getText(), textField.getText());
				dispose();
			}
		});
		btnIngresar.setFont(new Font("Arial", Font.BOLD, 13));
		btnIngresar.setBounds(175, 175, 89, 23);
		contentPane.add(btnIngresar);
		
		JLabel lblHost = new JLabel("Host:");
		lblHost.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHost.setFont(new Font("Arial", Font.BOLD, 13));
		lblHost.setBounds(0, 128, 89, 14);
		contentPane.add(lblHost);
		
		textField = new JTextField();
		textField.setBounds(157, 126, 267, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		setVisible(true);
	}
}
