package tests;
import java.io.IOException;
import java.text.ParseException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bot.asistente.Asistente;

public class RF11Tests {

	public final static String USUARIO = "delucas";

	Asistente jenkins;

	@Before
	public void setup() {
		jenkins = new Asistente("jenkins");
	}
	
	@Test
	public void unidadesDeMasa() throws ParseException, IOException, IOException {
		Assert.assertEquals(
				"@delucas 1 kilo equivalen a 1000 gramos",
				jenkins.escuchar("@jenkins cuántos gramos son 1 kilo",USUARIO).getMensaje()
			);
		
		Assert.assertEquals(
				"@delucas 1000 gramos equivalen a 1 kilos",
				jenkins.escuchar("@jenkins cuántos kilos son 1000 gramos",USUARIO).getMensaje()
			);
		
		Assert.assertEquals(
				"@delucas 1000 gramos equivalen a 35,27 onzas",
				jenkins.escuchar("@jenkins cuántas onzas son 1000 gramos",USUARIO).getMensaje()
			);		
	}
	@Test
	public void unidadesDeTiempo() throws ParseException, IOException {
		Assert.assertEquals(
				"@delucas 60 minutos equivalen a 1 horas",
				jenkins.escuchar("@jenkins cuántas horas son 60 minutos",USUARIO).getMensaje()
			);
		
		Assert.assertEquals(
				"@delucas 2 horas equivalen a 7200 segundos",
				jenkins.escuchar("@jenkins cuántos segundos son 2 horas",USUARIO).getMensaje()
			);
		
		Assert.assertEquals(
				"@delucas 1 minuto equivalen a 60 segundos",
				jenkins.escuchar("@jenkins cuántos segundos hay en 1 minuto",USUARIO).getMensaje()
			);		
	}	
	
	@Test
	public void unidadesDeCapacidad() throws ParseException, IOException {
		Assert.assertEquals(
				"@delucas 2 litros equivalen a 2000 mililitros",
				jenkins.escuchar("@jenkins cuántos mililitros son 2 litros",USUARIO).getMensaje()
			);
		
		Assert.assertEquals(
				"@delucas 3 litros equivalen a 300 centilitros",
				jenkins.escuchar("@jenkins cuántos centilitros son 3 litros",USUARIO).getMensaje()
			);	
				
	}
	
	@Test
	public void unidadesDeLongitud() throws ParseException, IOException {
	
		Assert.assertEquals(
				"@delucas 500 metros equivalen a 0,5 kilometros",
				jenkins.escuchar("@jenkins cuántos kilometros son 500 metros",USUARIO).getMensaje()
			);
		
		Assert.assertEquals(
				"@delucas 2 metros equivalen a 200 centimetros",
				jenkins.escuchar("@jenkins cuántos centimetros son 2 metros",USUARIO).getMensaje()
			);		
	}
	
	@Test
	public void MedidasIncorrectas() throws ParseException, IOException {
		Assert.assertEquals(
				"@delucas las unidades especificadas no se pueden transformar",
				jenkins.escuchar("@jenkins cuántos gramos son 1 metro",USUARIO).getMensaje()
			);
		
		Assert.assertEquals(
				"@delucas las unidades especificadas no se pueden transformar",
				jenkins.escuchar("@jenkins cuántos kilos son 1000 litros",USUARIO).getMensaje()
			);
		
		Assert.assertEquals(
				"@delucas las unidades especificadas no se pueden transformar",
				jenkins.escuchar("@jenkins cuántas onzas son 1000 horas",USUARIO).getMensaje()
			);		
	}	
}