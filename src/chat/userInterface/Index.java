package chat.userInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import entities.sala.Sala;

import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

public class Index extends JFrame {

	private JPanel contentPane;
	private ArrayList<Sala> Salas;
	private Sala SalaSeleccionada;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index frame = new Index("marinoLautaro");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public Index(String nombreUsuario) throws IOException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 872, 526);
		contentPane = new JPanel();
		contentPane.setBorder(new CompoundBorder());
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 54, 252, 433);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(251, 54, 605, 397);
		contentPane.add(panel_1);
		
		JPopupMenu popupMenu_1 = new JPopupMenu();
		addPopup(panel_1, popupMenu_1);
		
		JMenuItem mntmVerIntegrantes_1 = new JMenuItem("Ver integrantes");
		mntmVerIntegrantes_1.setIcon(new ImageIcon("img\\wach.png"));
		popupMenu_1.add(mntmVerIntegrantes_1);
		panel_1.setLayout(null);
		
		Component verticalGlue = Box.createVerticalGlue();
		verticalGlue.setBounds(84, 42, 0, 14);
		contentPane.add(verticalGlue);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalGlue.setBounds(251, 194, 12, 6);
		contentPane.add(horizontalGlue);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(0, 0, 252, 56);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblFotoUsuario = new JLabel("");
		Image imagenUsuario = ImageIO.read(new File(("img\\usuarios\\" + nombreUsuario + ".jpg" )));
		Image imagenUsuarioResized = imagenUsuario.getScaledInstance(46, 51, Image.SCALE_DEFAULT);		
		lblFotoUsuario.setIcon(new ImageIcon(imagenUsuarioResized));
		lblFotoUsuario.setBounds(2, 2, 46, 51);
		panel_2.add(lblFotoUsuario);
		
		JLabel lblNombreUsuario = new JLabel(nombreUsuario);
		lblNombreUsuario.setBounds(58, 20, 184, 14);
		panel_2.add(lblNombreUsuario);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(251, 0, 605, 56);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JPopupMenu popupMenu = new JPopupMenu();
		popupMenu.setBounds(544, 29, 250, 16);
		addPopup(panel_3, popupMenu);
		
		JMenuItem mntmVerIntegrantes = new JMenuItem("Ver integrantes");
		mntmVerIntegrantes.setIcon(new ImageIcon("img\\wach.png"));
		popupMenu.add(mntmVerIntegrantes);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(251, 452, 605, 35);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
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
	
}
