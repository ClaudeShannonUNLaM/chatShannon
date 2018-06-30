package chat.userInterface;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hibernate.sala.Sala;
import hibernate.sala.SalaController;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class NuevaSala extends JFrame {

	private JPanel contentPane;
	private JTextField lblNombreNuevaSala;	
	private JCheckBox chckbxPrivada;
	

	public NuevaSala() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("img\\AddNew.png"));
		lblNewLabel.setBounds(10, 11, 123, 70);
		contentPane.add(lblNewLabel);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de la sala:");
		lblNombreDeUsuario.setBounds(10, 124, 123, 14);
		contentPane.add(lblNombreDeUsuario);		
		
		lblNombreNuevaSala = new JTextField();
		lblNombreNuevaSala.setBounds(146, 121, 278, 20);
		contentPane.add(lblNombreNuevaSala);
		lblNombreNuevaSala.setColumns(10);		
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if(!lblNombreNuevaSala.getText().isEmpty()) {
					Sala sala = new Sala(lblNombreNuevaSala.getText(),chckbxPrivada.isSelected());					
					boolean resultado = SalaController.CrearSala(sala);
					
					if(resultado) {
						new Mensaje("Se creo la sala con exito");
						dispose();
					}
					else {
						new Mensaje("Ya existe una sala con ese nombre");
					}
				}
				else
					new Mensaje("Debe ingresar el nombre de la sala");
					
			}
		});
		btnCrear.setFont(new Font("Arial", Font.BOLD, 11));
		btnCrear.setBounds(97, 205, 116, 20);
		contentPane.add(btnCrear);
		
		JButton btnCancelar = new JButton("Cancelar");		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();				
			}
		});
		btnCancelar.setFont(new Font("Arial", Font.BOLD, 11));
		btnCancelar.setBounds(236, 205, 116, 20);
		contentPane.add(btnCancelar);
		
		chckbxPrivada = new JCheckBox("Privada");
		chckbxPrivada.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxPrivada.setBounds(167, 148, 97, 23);
		contentPane.add(chckbxPrivada);
		setVisible(true);
	}
}
