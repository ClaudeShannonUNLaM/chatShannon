package tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import bot.asistente.Asistente;
import tests.ventanaDeVideo.ReproductorVideo;

public class RF21Tests {
	
	public final static String USUARIO = "delucas"; 
	Asistente jenkins;
	ReproductorVideo video;
	
	
	@Before
	public void setup() {
		jenkins = new Asistente("jenkins");
	
	}
	
	@Test
	public void youtubeTest() throws ParseException, IOException{
		assertEquals("Aqui tienes.",jenkins.escuchar("Quiero ver videos donde argentina sale campeon, @jenkins",USUARIO).getMensaje());
	}
	
	@Test 
	public void jFrameYoutubetest() throws ParseException, IOException, InterruptedException {
		
		if(jenkins.escuchar("Quiero ver videos donde argentina sale campeon, @jenkins",USUARIO).getMensaje().equals("Aqui tienes.")){
			
			video = new ReproductorVideo();
			Thread.sleep(200000);
		}
	}
}
