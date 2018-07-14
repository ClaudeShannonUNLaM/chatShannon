package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;
import org.junit.Before;
import org.junit.Test;

import bot.asistente.Asistente;
import bot.meme.GeneradorMeme;
import chat.serverUtils.Mensaje;

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
		assertEquals("img\\memes\\its trap.jpg",shannon.escuchar("quiero ver el meme its trap, @shannon",USUARIO));
	}
	
	@Test 
	public void memejFrameTest() throws ParseException, IOException, InterruptedException {
		
		Mensaje img=shannon.escuchar("quiero ver el meme take my money, @shannon",USUARIO);
		if(img!=null){
		/*
			meme =new GeneradorMeme(img.getImagen(),img.getDescripcion());
			meme.mostrarMeme();
			meme.setVisible(true);
		*/	Thread.sleep(10000);
			
			}
		}
}

