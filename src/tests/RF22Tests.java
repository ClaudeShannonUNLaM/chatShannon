package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;
import org.junit.Before;
import org.junit.Test;

import asistente.Asistente;
import meme.GeneradorMeme;

public class RF22Tests {
	public final static String USUARIO = "delucas"; 
	Asistente shannon;
	GeneradorMeme meme;
	
	
	@Before
	public void setup() {
		shannon = new Asistente("shannon");
	
	}
	
	
	@Test
	public void memeRegexTest() throws ParseException, IOException{
		assertEquals("img\\memes\\its trap.jpg",shannon.escuchar("quiero ver el meme its trap, @shannon"));
	}
	
	@Test 
	public void memejFrameTest() throws ParseException, IOException, InterruptedException {
		
		String img=shannon.escuchar("quiero ver el meme take my money, @shannon");
		if(img!=null){
		
			meme =new GeneradorMeme(img,"MEME: "+img.substring(img.indexOf("memes")+6,img.length()-4));
			meme.mostrarMeme();
			meme.setVisible(true);
			Thread.sleep(10000);
			
			}
		}
}

