package tests.ventanaDeVideo;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.io.File;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class ReproductorVideo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JFXPanel jfxPanel = new JFXPanel();   

	
	public ReproductorVideo() {
		createScene();
		setTitle("Never Gonna Give You Up");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 399);
		setResizable(false);
        setLocationRelativeTo(null);
        
        
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
        contentPane.add(jfxPanel,BorderLayout.CENTER);//agrego el video al pane
		setContentPane(contentPane);
		setVisible(true);
		
		
	}

	private void createScene(){
        Platform.runLater(new Runnable() {
             @Override
             public void run() {                 
                File file = new File("video\\nevergonnagiveyouup.mp4");                                  
                MediaPlayer oracleVid = new MediaPlayer(new Media(file.toURI().toString()));
                    //se añade video al jfxPanel
                jfxPanel.setScene(new Scene(new Group(new MediaView(oracleVid))));                    
                oracleVid.setVolume(0.7);//volumen
                oracleVid.setCycleCount(MediaPlayer.INDEFINITE);//repite video
                oracleVid.play();//play video
             }
        });
	}
}
