package bot.handlers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import chat.serverUtils.Mensaje;

public class Meme9GagHandler extends AsistantSentenceHandler{
	//***********IMPORTANTE***********************************************************************
	/*CHICOS*********************************************************************************************************/
//el giveAnswer de 9gag te puede dar, o la ruta al archivo o puede tambien mostrar la imagen en una nueva ventana, avisenme como lo quieran y lo arreglo de ultima
	public Meme9GagHandler(){
		patron = Pattern.compile(".*(gag|random)");
	}
	
	@Override
	public Mensaje giveAnswer(String mensaje, String nombreUsuario) {
		Mensaje msj = new Mensaje (mensaje);
		
		Matcher matcher = patron.matcher(mensaje);		
		String rutaALaImagen;
	    if (matcher.find()) {	    	
	    	rutaALaImagen=responderMeme();
	    	msj.setImagen(new File(rutaALaImagen));
	    	msj.setDescripcion("Tomá¡, @" + nombreUsuario);
	    	return msj;
	    } else 
	    	return this.nextHandler.giveAnswer(mensaje, nombreUsuario);			
	}	
//	public static void main (String []args){
//		Meme9GagHandler m = new Meme9GagHandler();
//		System.out.println(m.giveAnswer("quiero gag", "aaa"));
//		
//	}
	private static String IMAGE_DESTINATION_FOLDER = "gag";
	private static String responderMeme(){
	    
	        
	        //replace it with your URL 
	        String strURL = "http://9gag.com/random";
	        
	        //connect to the website and get the document
	        Document document=null;
			try {
				document = Jsoup
				        .connect(strURL)
				        .userAgent("Mozilla/5.0")
				        .timeout(10 * 1000)
				        .get();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
	        //select all img tags
//	        Elements imageElements = document.select("img");
//	        
//	        //iterate over each image
//	        for(Element imageElement : imageElements){
//	            
//	            //make sure to get the absolute URL using abs: prefix
//	            String strImageURL = imageElement.attr("abs:src");
//	            
//	            //download image one by one
//	            downloadImage(strImageURL);
//	            
//	        }
	        String strImageURL=obtenerImagen(document);
	        String imagePath=downloadImage(strImageURL);
	        //System.out.println("strImageURL = "+strImageURL);
	        try {
	            BufferedImage img = ImageIO.read(new File("gag/"+imagePath));
	            ImageIcon icon = new ImageIcon(img);
	            JLabel label = new JLabel(icon);
	            JOptionPane.showMessageDialog(null, label);
	         } catch (IOException e) {
	            e.printStackTrace();
	         }
	 
	    return "gag/"+imagePath;
	   
	}
	 private static String obtenerImagen(Document document) {
	        Element image = document.select("meta[property=og:image]").first();
	        if (image != null) {
	            return image.attr("abs:content");
	        }
	        Element secureImage = document.select("meta[property=og:image:secure]").first();
	        if (secureImage != null) {
	            return secureImage.attr("abs:content");
	        }
	        return null;
	    } 
	    private static String downloadImage(String strImageURL){
	        
	        //get file name from image path
	        String strImageName = 
	                strImageURL.substring( strImageURL.lastIndexOf("/") + 1 );
	        
	      // System.out.println("Saving: " + strImageName + ", from: " + strImageURL);
	        
	        try {
	            
	            //open the stream from URL
	            URL urlImage = new URL(strImageURL);
	            InputStream in = urlImage.openStream();
	            
	            byte[] buffer = new byte[4096];
	            int n = -1;
	            
	            OutputStream os = 
	                new FileOutputStream( IMAGE_DESTINATION_FOLDER + "/" + strImageName );
	            
	            //write bytes to the output stream
	            while ( (n = in.read(buffer)) != -1 ){
	                os.write(buffer, 0, n);
	            }
	            
	            //close the stream
	            os.close();
	            
	          //  System.out.println("Image saved");
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return strImageName;
	        
	    }
}
