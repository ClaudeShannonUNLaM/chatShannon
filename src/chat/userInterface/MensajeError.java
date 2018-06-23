package chat.userInterface;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class MensajeError extends JFrame {

	private JPanel contentPane;

	
	public MensajeError(String mensaje) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton.setBounds(126, 158, 176, 28);
		contentPane.add(btnNewButton);
		
		JLabel lblErrorDisplay = new JLabel(mensaje);
		lblErrorDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorDisplay.setBounds(38, 40, 362, 61);
		contentPane.add(lblErrorDisplay);
		setVisible(true);
	}
}
