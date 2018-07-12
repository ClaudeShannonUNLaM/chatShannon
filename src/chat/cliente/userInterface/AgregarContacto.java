package chat.cliente.userInterface;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hibernate.contacto.ContactoController;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AgregarContacto extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	
	public AgregarContacto(String nombreUsuarioIngresado) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de usuario");
		lblNombreDeUsuario.setFont(new Font("Arial", Font.BOLD, 13));
		lblNombreDeUsuario.setBounds(10, 95, 135, 14);
		contentPane.add(lblNombreDeUsuario);
		
		textField = new JTextField();
		textField.setBounds(155, 93, 269, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean exito = ContactoController.agregarNuevoContacto(nombreUsuarioIngresado.toLowerCase(),textField.getText().toLowerCase());
				
				if(exito) {
					new MensajeInterfaz("Se agregó el contacto con éxito");
					dispose();
				}					
				else
					new MensajeInterfaz("No se encontró el usuario indicado");
			}
		});
		btnAgregar.setFont(new Font("Arial", Font.BOLD, 11));
		btnAgregar.setBounds(155, 179, 89, 23);
		contentPane.add(btnAgregar);
		setVisible(true);
	}
}
