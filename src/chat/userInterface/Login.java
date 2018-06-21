package chat.userInterface;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;

@SuppressWarnings("serial")
public class Login extends JFrame {

	private JPanel contentPane;	
	private JTextField lblNombreUsuario;
	private JTextField lblPassUsuario;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Login(){
		this.setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 687, 422);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de usuario:");
		lblNombreDeUsuario.setBounds(272, 143, 123, 14);
		contentPane.add(lblNombreDeUsuario);
		
		JLabel lblContraseaDeUsuario = new JLabel("Contrase\u00F1a de usuario:");
		lblContraseaDeUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContraseaDeUsuario.setBounds(246, 234, 138, 14);
		contentPane.add(lblContraseaDeUsuario);
		
		JLabel lblImg = new JLabel("");
		lblImg.setBounds(10, 76, 196, 224);		
		lblImg.setIcon(new ImageIcon("img\\loginCheck.png"));
		contentPane.add(lblImg);
		
		lblNombreUsuario = new JTextField();
		lblNombreUsuario.setColumns(10);
		lblNombreUsuario.setBounds(402, 140, 259, 20);
		contentPane.add(lblNombreUsuario);
		
		lblPassUsuario = new JTextField();
		lblPassUsuario.setColumns(10);
		lblPassUsuario.setBounds(402, 231, 259, 20);
		contentPane.add(lblPassUsuario);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Index(lblNombreUsuario.getText());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnIngresar.setFont(new Font("Arial", Font.BOLD, 11));
		btnIngresar.setBounds(538, 281, 123, 23);
		contentPane.add(btnIngresar);
		
		JButton btnNuevoUsuarioButton = new JButton("Nuevo usuario");
		btnNuevoUsuarioButton.setFont(new Font("Arial", Font.BOLD, 11));
		btnNuevoUsuarioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new NuevoUsuario();				
			}
		});
		btnNuevoUsuarioButton.setBounds(182, 329, 286, 23);
		contentPane.add(btnNuevoUsuarioButton);
		
		JLabel lblBienvenido = new JLabel("Bienvenido");
		lblBienvenido.setFont(new Font("Arial", Font.BOLD, 18));
		lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenido.setBounds(88, 11, 467, 23);
		contentPane.add(lblBienvenido);
		
		setVisible(true);
	}
}
