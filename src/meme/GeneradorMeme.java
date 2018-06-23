package meme;


import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;



@SuppressWarnings("serial")
public class GeneradorMeme extends JFrame {

	//private JPanel contentPane;
	private String fullPathImage;
	private String textMeme;
	

	public GeneradorMeme(String img,String text) {
		
		this.fullPathImage=img;
		this.textMeme=text;
		
	}
	
	private void incializar()
	{   getContentPane().setLayout(null);
		setBounds(100, 100, 800,900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel Meme = new JLabel(new ImageIcon(this.fullPathImage));
		Meme.setBounds(150, 11, 500, 700);
		getContentPane().add(Meme);
		
		JLabel TextoMeme = new JLabel(this.textMeme);
		TextoMeme.setBounds(250, 230,400,960);
		TextoMeme.setFont(new Font("Arial",Font.BOLD,30));
		getContentPane().add(TextoMeme);
	}
	

	public void mostrarMeme()
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					GeneradorMeme frame = new GeneradorMeme(getFullPathImage(),getTextMeme());
					frame.incializar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	public String getFullPathImage() {
		return fullPathImage;
	}


	public void setFullPathImage(String fullPathImage) {
		this.fullPathImage = fullPathImage;
	}


	public String getTextMeme() {
		return textMeme;
	}


	public void setTextMeme(String textMeme) {
		this.textMeme = textMeme;
	}


	
	


}

