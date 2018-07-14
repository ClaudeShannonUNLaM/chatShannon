package chat.cliente.userInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import chat.cliente.Cliente;
import chat.serverUtils.FuncionalidadServerEnum;
import chat.serverUtils.ServerRequest;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class Invitacion extends JFrame {

	private JPanel contentPane;
	private Cliente cliente;
	private JTextField lblNombreUsuario;
	private JTextField lblNombreSala;
	

	/**
	 * Create the frame.
	 */
	public Invitacion(Cliente cliente) {
		this.cliente = cliente;	
		this.cliente.getThreadLectura().addPantalla("invitacion", this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreDelUsuario = new JLabel("Nombre del usuario");
		lblNombreDelUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreDelUsuario.setFont(new Font("Arial", Font.BOLD, 13));
		lblNombreDelUsuario.setBounds(10, 82, 160, 14);
		contentPane.add(lblNombreDelUsuario);
		
		JLabel lblNombreDeLa = new JLabel("Nombre de la sala");
		lblNombreDeLa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreDeLa.setFont(new Font("Arial", Font.BOLD, 13));
		lblNombreDeLa.setBounds(10, 156, 160, 14);
		contentPane.add(lblNombreDeLa);
		
		lblNombreUsuario = new JTextField();
		lblNombreUsuario.setBounds(180, 80, 244, 20);
		contentPane.add(lblNombreUsuario);
		lblNombreUsuario.setColumns(10);
		
		lblNombreSala = new JTextField();
		lblNombreSala.setBounds(180, 154, 244, 20);
		contentPane.add(lblNombreSala);
		lblNombreSala.setColumns(10);
		
		JButton btnInvitar = new JButton("Invitar");
		btnInvitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lblNombreSala.getText().equals("") ||lblNombreUsuario.getText().equals("") ) {
					new MensajeInterfaz("Debe completar los campos obligatorios");
					return;
				}				
				
				HashMap<String, Object> map = new HashMap<String,Object>();
				map.put("nombreUsuario", lblNombreUsuario.getText().toLowerCase());
				map.put("nombreSala", lblNombreSala.getText().toLowerCase());				
		        ServerRequest request = new ServerRequest(map,FuncionalidadServerEnum.INVITARCONTACTO);
				Gson gson = new Gson();					
				String requestJson = gson.toJson(request);
				cliente.getThreadEscritura().AddRequest(requestJson);
			}
		});
		btnInvitar.setFont(new Font("Arial", Font.BOLD, 13));
		btnInvitar.setBounds(128, 212, 89, 23);
		contentPane.add(btnInvitar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Arial", Font.BOLD, 13));
		btnCancelar.setBounds(245, 212, 89, 23);
		contentPane.add(btnCancelar);
		setVisible(true);
	}
	
	public void informarFinalizacionInvitacion(boolean exito) {
		if(exito) {
			new MensajeInterfaz("Se invito al usuario con éxito");
			dispose();
		}
		else {
			new MensajeInterfaz("El usuario o la sala no existen, intentelo de nuevo.");			
		}
			
	}

}
