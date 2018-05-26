package tests;
import java.text.ParseException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import asistente.Asistente;

public class RF11Tests {

	public final static String USUARIO = "delucas";

	Asistente jenkins;

	@Before
	public void setup() {
		jenkins = new Asistente("jenkins");
	}
	
	@Test
	public void unidadesDeMasa() throws ParseException {
		Assert.assertEquals(
				"@delucas 1 kilo equivalen a 1000 gramos",
				jenkins.escuchar("@jenkins cuántos gramos son 1 kilo")
			);
		
		Assert.assertEquals(
				"@delucas 1000 gramos equivalen a 1 kilos",
				jenkins.escuchar("@jenkins cuántos kilos son 1000 gramos")
			);
		
		Assert.assertEquals(
				"@delucas 1000 gramos equivalen a 35,27 onzas",
				jenkins.escuchar("@jenkins cuántas onzas son 1000 gramos")
			);		
	}
	@Test
	public void unidadesDeTiempo() throws ParseException {
		Assert.assertEquals(
				"@delucas 60 minutos equivalen a 1 horas",
				jenkins.escuchar("@jenkins cuántas horas son 60 minutos")
			);
		
		Assert.assertEquals(
				"@delucas 2 horas equivalen a 7200 segundos",
				jenkins.escuchar("@jenkins cuántos segundos son 2 horas")
			);
		
		Assert.assertEquals(
				"@delucas 1 minuto equivalen a 60 segundos",
				jenkins.escuchar("@jenkins cuántos segundos hay en 1 minuto")
			);		
	}	
	
	@Test
	public void unidadesDeCapacidad() throws ParseException {
		Assert.assertEquals(
				"@delucas 1 kilo equivalen a 1000 gramos",
				jenkins.escuchar("@jenkins cuántos litros son 1 kilo")
			);
		
		Assert.assertEquals(
				"@delucas 1000 gramos equivalen a 1 kilos",
				jenkins.escuchar("@jenkins cuántos kilos son 1000 gramos")
			);
		
		Assert.assertEquals(
				"@delucas 1000 gramos equivalen a 35,27 onzas",
				jenkins.escuchar("@jenkins cuántas onzas son 1000 gramos")
			);		
	}
	
	@Test
	public void unidadesDeLongitud() throws ParseException {
		Assert.assertEquals(
				"@delucas 1 kilometro equivalen a 1000 metros",
				jenkins.escuchar("@jenkins cuántos metros son 1 kilometro")
			);
		
		Assert.assertEquals(
				"@delucas 500 metros equivalen a 0.50 kilometros",
				jenkins.escuchar("@jenkins cuántos kilometros son 500 metros")
			);
		
		Assert.assertEquals(
				"@delucas 2 metros equivalen a 200 centimetros",
				jenkins.escuchar("@jenkins cuántos centimetros son 2 metros")
			);		
	}
	
	@Test
	public void MedidasIncorrectas() throws ParseException {
		Assert.assertEquals(
				"@delucas las unidades especificadas no se pueden transformar",
				jenkins.escuchar("@jenkins cuántos gramos son 1 metro")
			);
		
		Assert.assertEquals(
				"@delucas las unidades especificadas no se pueden transformar",
				jenkins.escuchar("@jenkins cuántos kilos son 1000 litros")
			);
		
		Assert.assertEquals(
				"@delucas las unidades especificadas no se pueden transformar",
				jenkins.escuchar("@jenkins cuántas onzas son 1000 horas")
			);		
	}	
}