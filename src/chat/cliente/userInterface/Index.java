package chat.cliente.userInterface;


import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.google.gson.Gson;

import chat.cliente.Cliente;
import chat.serverUtils.FuncionalidadServerEnum;
import chat.serverUtils.Mensaje;
import chat.serverUtils.ServerRequest;
import hibernate.contacto.Contacto;
import hibernate.sala.Sala;
import hibernate.usuario.Usuario;

import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.CompoundBorder;
import javax.swing.JMenuBar;
import javax.swing.JList;
import java.awt.Font;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;
import javax.swing.JTextPane;

public class Index extends JFrame {
	
	private JPanel contentPane;	
	
	private List<Sala> salas; //Mantengo las salas 
	private List<Usuario> contactos;
	
	private ArrayList<String> mensajesSalas; 		
	private ArrayList<String> mensajesContactos; 
	
	private JList listContactos; 
	private JList listSalasPrivadas;
	private JList listSalasPublicas;	
	
	private Object conversacionSeleccionada;
	private JTextField mensajeTxT;
	private Cliente cliente;
	
	public Index(Cliente cliente) throws IOException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 872, 526);
		contentPane = new JPanel();
		contentPane.setBorder(new CompoundBorder());
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelListas = new JPanel();
		panelListas.setBackground(Color.WHITE);
		panelListas.setBounds(0, 54, 252, 433);
		contentPane.add(panelListas);	
		
		
		JPanel panelMensajes = new JPanel();
		panelMensajes.setBounds(251, 54, 605, 397);
		contentPane.add(panelMensajes);
		
		JPopupMenu popupMenu_1 = new JPopupMenu();
		addPopup(panelMensajes, popupMenu_1);
		
		JMenuItem mntmVerIntegrantes_1 = new JMenuItem("Ver integrantes");
		mntmVerIntegrantes_1.setIcon(new ImageIcon("img\\wach.png"));
		popupMenu_1.add(mntmVerIntegrantes_1);
		panelMensajes.setLayout(null);
		
		JTextPane textArea = new JTextPane();
		textArea.setBackground(SystemColor.menu);
		textArea.setFont(new Font("Arial", Font.PLAIN, 14));
		textArea.setEditable(false);
		textArea.setBounds(0, 0, 605, 397);		
		textArea.setForeground(Color.BLACK);
		panelMensajes.add(textArea);
		
		Component verticalGlue = Box.createVerticalGlue();
		verticalGlue.setBounds(84, 42, 0, 14);
		contentPane.add(verticalGlue);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalGlue.setBounds(251, 194, 12, 6);
		contentPane.add(horizontalGlue);
		
		JPanel panelDatosUsuario = new JPanel();
		panelDatosUsuario.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDatosUsuario.setBounds(0, 0, 252, 56);
		contentPane.add(panelDatosUsuario);
		panelDatosUsuario.setLayout(null);
		
		JLabel lblFotoUsuario = new JLabel("");
		Image imagenUsuario = ImageIO.read(new File(("img\\user.png" )));
		Image imagenUsuarioResized = imagenUsuario.getScaledInstance(46, 51, Image.SCALE_DEFAULT);		
		lblFotoUsuario.setIcon(new ImageIcon(imagenUsuarioResized));
		lblFotoUsuario.setBounds(2, 2, 46, 51);
		panelDatosUsuario.add(lblFotoUsuario);
		
		JLabel lblNombreUsuario = new JLabel(cliente.getUsuario().getNombre());
		lblNombreUsuario.setBounds(58, 20, 184, 14);
		panelDatosUsuario.add(lblNombreUsuario);
		
		JPanel panelOpciones = new JPanel();
		panelOpciones.setBounds(251, 0, 605, 56);
		contentPane.add(panelOpciones);
		panelOpciones.setLayout(null);
		
		JPopupMenu popupMenu = new JPopupMenu();
		popupMenu.setBounds(544, 29, 250, 16);
		addPopup(panelOpciones, popupMenu);
		
		JMenuItem mntmVerIntegrantes = new JMenuItem("Ver integrantes");
		mntmVerIntegrantes.setIcon(new ImageIcon("img\\wach.png"));
		popupMenu.add(mntmVerIntegrantes);
		
		JLabel labelNombreSalaSeleccionada = new JLabel("");
		labelNombreSalaSeleccionada.setFont(new Font("Arial", Font.BOLD, 26));
		labelNombreSalaSeleccionada.setBounds(31, 11, 320, 34);
		panelOpciones.add(labelNombreSalaSeleccionada);
		
		JPanel panelEscritura = new JPanel();
		panelEscritura.setBounds(251, 452, 605, 35);
		contentPane.add(panelEscritura);
		panelEscritura.setLayout(null);
		
		mensajeTxT = new JTextField();
		mensajeTxT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(mensajeTxT.getText() == "")
					return;
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					
					//textArea.setText("  " + lblNombreUsuario.getText() + ": " +   mensajeTxT.getText() + "\n");
					HashMap<String, Object> map = new HashMap<String,Object>();
					
					if(contactos.contains(conversacionSeleccionada) ) {
						map.put("usuarioDestino", ((Usuario)conversacionSeleccionada));	
					}
					else {
						map.put("usuarioDestino", ((Usuario)conversacionSeleccionada));
					}
					
					Mensaje mensaje = new Mensaje();
			        map.put("mensaje",mensaje);
			        
			        ServerRequest request = new ServerRequest(map,FuncionalidadServerEnum.ENVIARMENSAJE);
					Gson gson = new Gson();					
					String requestJson = gson.toJson(request);
					cliente.getThreadEscritura().AddRequest(requestJson);
					
					mensajeTxT.setText("");
				}				
			}
		});
		mensajeTxT.setHorizontalAlignment(SwingConstants.RIGHT);
		mensajeTxT.setBounds(10, 7, 585, 20);
		panelEscritura.add(mensajeTxT);
		mensajeTxT.setColumns(10);		
		
		
		JScrollPane contactosPane = new JScrollPane((Component) null);
		contactosPane.setBounds(0, 324, 252, 109);
		panelListas.add(contactosPane);		
		
		JScrollPane salasPrivadasPane = new JScrollPane((Component) null);
		salasPrivadasPane.setBounds(0, 181, 252, 109);
		panelListas.add(salasPrivadasPane);		
		
		
		listContactos = new JList();	
		contactosPane.setViewportView(listContactos);
		
		listSalasPrivadas = new JList();
		salasPrivadasPane.setViewportView(listSalasPrivadas);	
		
		listSalasPublicas = new JList();
		
		listSalasPublicas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSalasPublicas.setFont(new Font("Arial", Font.BOLD, 16));
		
		listSalasPublicas.addListSelectionListener(new ListSelectionListener() {     
			@Override
			public void valueChanged(ListSelectionEvent e) {
			      if (!e.getValueIsAdjusting()) {			    	  
			    	  textArea.setText("  " + lblNombreUsuario.getText() + ": " +   mensajeTxT.getText() + "\n"); //Se cambia el mensaje siendo mostrado
			    	  labelNombreSalaSeleccionada.setText(listSalasPublicas.getSelectedValue().toString());
	                }				
			}
        });
		panelListas.setLayout(null);
		
		JScrollPane panelScroll = new JScrollPane(listSalasPublicas);
		panelScroll.setBounds(0, 36, 252, 109);
		panelListas.add(panelScroll);
		
		JLabel lblSalasPublicas = new JLabel("Salas publicas");
		lblSalasPublicas.setFont(new Font("Arial", Font.BOLD, 13));
		lblSalasPublicas.setBounds(10, 11, 232, 14);
		panelListas.add(lblSalasPublicas);
		
		JLabel lblSalasPrivadas = new JLabel("Salas privadas");
		lblSalasPrivadas.setFont(new Font("Arial", Font.BOLD, 13));
		lblSalasPrivadas.setBounds(10, 156, 232, 14);
		panelListas.add(lblSalasPrivadas);		
		
		JLabel lblContactos = new JLabel("Contactos");
		lblContactos.setFont(new Font("Arial", Font.BOLD, 13));
		lblContactos.setBounds(10, 302, 124, 14);
		panelListas.add(lblContactos);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new AgregarContacto(cliente);
			}
		});
		btnAgregar.setFont(new Font("Arial", Font.BOLD, 11));
		btnAgregar.setBounds(153, 295, 89, 23);
		panelListas.add(btnAgregar);
		
		JButton btnCrearNuevaSala = new JButton("Nueva");
		btnCrearNuevaSala.setBounds(138, 8, 104, 23);
		panelListas.add(btnCrearNuevaSala);
		btnCrearNuevaSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new NuevaSala(cliente);
			}
		});
		btnCrearNuevaSala.setFont(new Font("Arial", Font.BOLD, 11));
		
		
		mensajesSalas= new ArrayList<String>();
		mensajesContactos = new ArrayList<String>();
		
		setVisible(true);
	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	public void cargarDatosIndex(List<Sala> salasPublicas, List<Sala> salasPrivadas,List<Usuario> contactos ) {
		
		salas = salasPublicas;		
		for (Sala sala : salasPrivadas) { //Cargo todas las salas
			salas.add(sala);	
		}
		
		this.contactos = contactos;
		
		cargarSalasPublicas(salasPublicas);
		cargarSalasPrivadas(salasPrivadas);
		cargarContactos(contactos);
	
	}
	
	private void cargarSalasPublicas(List<Sala> salasPublicas) {
		DefaultListModel listModel = new DefaultListModel();		
		
		for (Sala sala : salasPublicas) {
			listModel.addElement(sala.getNombre());
			mensajesSalas.add("");			
		}
		
		listSalasPublicas.setModel(listModel);
	}
	
	private void cargarSalasPrivadas(List<Sala> salasPrivadas) {
		DefaultListModel listModel = new DefaultListModel();		
		
		for (Sala sala : salasPrivadas) {
			listModel.addElement(sala.getNombre());
			mensajesSalas.add("");			
		}
		
		listSalasPrivadas.setModel(listModel);
	}	
	
	public void cargarContactos(List<Usuario> contactos ){				
		DefaultListModel listModel = new DefaultListModel();		
		
		for (Usuario contacto : contactos) {
			listModel.addElement(contacto.getNombre());
			mensajesContactos.add("");
		}
		
		listContactos.setModel(listModel);	
		
	}
	
	
	/*
	 * 
	
	private List<Mensaje> mensajesPrivados;
	private List<Mensaje> mensajesContactos;
	 * 
	 * */
	public void agregarMensajePublico(Mensaje mensaje) {		

	}
	public void agregarMensajePrivado(Mensaje mensaje) {
		
	}
	
	public void agregarMensajeContacto(Mensaje mensaje) {
		
	}
	
}
