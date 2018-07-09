package chat.userInterface;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import hibernate.usuario.UsuarioController;

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
	private JPasswordField lblPassUsuario;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("unused")
					Login frame = new Login();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Login(){
		this.setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 603, 392);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de usuario:");
		lblNombreDeUsuario.setFont(new Font("Arial", Font.BOLD, 13));
		lblNombreDeUsuario.setBounds(25, 142, 150, 14);
		contentPane.add(lblNombreDeUsuario);
		
		JLabel lblContraseaDeUsuario = new JLabel("Contrase\u00F1a de usuario:");
		lblContraseaDeUsuario.setFont(new Font("Arial", Font.BOLD, 13));
		lblContraseaDeUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContraseaDeUsuario.setBounds(0, 195, 150, 14);
		contentPane.add(lblContraseaDeUsuario);
		
		lblNombreUsuario = new JTextField();
		lblNombreUsuario.setColumns(10);
		lblNombreUsuario.setBounds(170, 140, 385, 20);
		contentPane.add(lblNombreUsuario);
		
		lblPassUsuario = new JPasswordField();
		lblPassUsuario.setColumns(10);
		lblPassUsuario.setBounds(170, 193, 385, 20);
		contentPane.add(lblPassUsuario);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					if(lblNombreUsuario.getText().equals("")|| lblPassUsuario.getPassword().toString().equals("")) {
						new Mensaje("Debe ingresar la información necesaria");
						return;
					}
					
					if(usuarioExiste()) {
						new Index(lblNombreUsuario.getText());
						dispose();
					}						
					else
						new Mensaje("El usuario no está dentro del sistema");
					
				} catch (IOException e1) {					
					e1.printStackTrace();
				}
			}
		});
		btnIngresar.setFont(new Font("Arial", Font.BOLD, 11));
		btnIngresar.setBounds(432, 236, 123, 23);
		contentPane.add(btnIngresar);
		
		JButton btnNuevoUsuarioButton = new JButton("Nuevo usuario");
		btnNuevoUsuarioButton.setFont(new Font("Arial", Font.BOLD, 11));
		btnNuevoUsuarioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new NuevoUsuario();				
			}
		});
		btnNuevoUsuarioButton.setBounds(148, 281, 286, 23);
		contentPane.add(btnNuevoUsuarioButton);
		
		JLabel lblBienvenido = new JLabel("Bienvenido");
		lblBienvenido.setFont(new Font("Arial", Font.BOLD, 18));
		lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenido.setBounds(70, 11, 467, 23);
		contentPane.add(lblBienvenido);
		
		setVisible(true);
	}
	
	private boolean usuarioExiste() {		
		return UsuarioController.usuarioYaCreado(lblNombreUsuario.getText().toLowerCase(),new String(lblPassUsuario.getPassword()).toLowerCase(),false); 
	}
}
