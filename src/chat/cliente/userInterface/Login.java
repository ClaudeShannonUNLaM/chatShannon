package chat.cliente.userInterface;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import chat.cliente.Cliente;
import chat.serverUtils.FuncionalidadServerEnum;
import chat.serverUtils.ServerRequest;
import hibernate.usuario.UsuarioController;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.Font;

@SuppressWarnings("serial")
public class Login extends JFrame {

	private Cliente cliente;
	private JPanel contentPane;	
	private JTextField lblNombreUsuario;
	private JPasswordField lblPassUsuario;
	private JTextField lblIpServidor;
	
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
		setBounds(100, 100, 606, 351);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de usuario:");
		lblNombreDeUsuario.setFont(new Font("Arial", Font.BOLD, 13));
		lblNombreDeUsuario.setBounds(25, 100, 150, 14);
		contentPane.add(lblNombreDeUsuario);
		
		JLabel lblContraseaDeUsuario = new JLabel("Contrase\u00F1a de usuario:");
		lblContraseaDeUsuario.setFont(new Font("Arial", Font.BOLD, 13));
		lblContraseaDeUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContraseaDeUsuario.setBounds(0, 145, 150, 14);
		contentPane.add(lblContraseaDeUsuario);
		
		lblNombreUsuario = new JTextField();
		lblNombreUsuario.setColumns(10);
		lblNombreUsuario.setBounds(170, 100, 385, 20);
		contentPane.add(lblNombreUsuario);
		
		lblPassUsuario = new JPasswordField();
		lblPassUsuario.setColumns(10);
		lblPassUsuario.setBounds(170, 145, 385, 20);
		contentPane.add(lblPassUsuario);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {					
			        
					if(lblNombreUsuario.getText().equals("")|| lblPassUsuario.getPassword().toString().equals("") || lblIpServidor.getText().equals("")) {
						new Mensaje("Debe ingresar la información necesaria");
						return;
					}
					
					cliente = new Cliente(lblIpServidor.getText(), 10000);
			        cliente.run();
			        cliente.setNombreUsuario(lblNombreUsuario.getText());
					
			        HashMap<String, String> map = new HashMap<String,String>();			        
			        map.put("nombreUsuario", lblNombreUsuario.getText());
			        map.put("passUsuario", lblPassUsuario.getPassword().toString());
			        
			        ServerRequest request = new ServerRequest(map,FuncionalidadServerEnum.LOGIN);
					Gson gson = new Gson();					
					String requestJson = gson.toJson(request);
					
					if(true) {
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
		btnIngresar.setBounds(432, 200, 123, 23);
		contentPane.add(btnIngresar);
		
		JButton btnNuevoUsuarioButton = new JButton("Nuevo usuario");
		btnNuevoUsuarioButton.setFont(new Font("Arial", Font.BOLD, 11));
		btnNuevoUsuarioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new NuevoUsuario();				
			}
		});
		btnNuevoUsuarioButton.setBounds(148, 240, 286, 23);
		contentPane.add(btnNuevoUsuarioButton);
		
		JLabel lblBienvenido = new JLabel("Bienvenido");
		lblBienvenido.setFont(new Font("Arial", Font.BOLD, 18));
		lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenido.setBounds(70, 11, 467, 23);
		contentPane.add(lblBienvenido);
		
		JLabel lblIpDelServidor = new JLabel("Ip del servidor: ");
		lblIpDelServidor.setFont(new Font("Arial", Font.BOLD, 13));
		lblIpDelServidor.setBounds(50, 58, 125, 14);
		contentPane.add(lblIpDelServidor);
		
		lblIpServidor = new JTextField();
		lblIpServidor.setBounds(168, 56, 387, 20);
		contentPane.add(lblIpServidor);
		lblIpServidor.setColumns(10);
		
		setVisible(true);
	}
	
	private boolean usuarioExiste() {		
		return UsuarioController.usuarioYaCreado(lblNombreUsuario.getText().toLowerCase(),new String(lblPassUsuario.getPassword()).toLowerCase(),false); 
	}
}
