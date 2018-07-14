package tests;
import org.junit.*;

import bot.asistente.Asistente;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;

public class RF04Tests{
	
	
	public final static String USUARIO = "delucas";
	public final static Calendar FECHA_MUNDIAL = Calendar.getInstance();

	Asistente shannon;
	
	@Before
	public void setup(){
		shannon = new Asistente("shannon");
		FECHA_MUNDIAL.set(Calendar.YEAR,2018);
		FECHA_MUNDIAL.set(Calendar.MONTH,5);
		FECHA_MUNDIAL.set(Calendar.DAY_OF_MONTH,14);
	}
	
	@Test
	public void diaDentroDe() throws ParseException, IOException {
		Assert.assertEquals(
				"@delucas será el sábado 2 de junio de 2018",
				shannon.escuchar("@shannon qué día será dentro de 2 días?",USUARIO).getMensaje()
			);
		Assert.assertEquals(
				"@delucas será el martes 31 de julio de 2018",
				shannon.escuchar("@shannon qué día será dentro de 2 meses?",USUARIO).getMensaje()
			);
		
		Assert.assertEquals(
				"@delucas será el domingo 31 de mayo de 2020",
				shannon.escuchar("@shannon qué día será dentro de 2 años?",USUARIO).getMensaje()
			);
			
	}
	
	@Test
	public void diaHace() throws ParseException, IOException {
		
	Assert.assertEquals(
				"@delucas fue el miércoles 30 de mayo de 2018",
				shannon.escuchar("@shannon qué día fue ayer?",USUARIO).getMensaje()
			);
		
		Assert.assertEquals(
				"@delucas fue el lunes 28 de mayo de 2018",
				shannon.escuchar("@shannon qué día fue hace 3 días?",USUARIO).getMensaje()
			);
		
		Assert.assertEquals(
				"@delucas fue el sábado 31 de marzo de 2018",
				shannon.escuchar("@shannon qué día fue hace 2 meses?",USUARIO).getMensaje()
			);
		
		Assert.assertEquals(
				"@delucas fue el martes 31 de mayo de 2016",
				shannon.escuchar("@shannon qué día fue hace 2 años?",USUARIO).getMensaje()
			);			
	}
	@Test
	public void tiempoDesde() throws ParseException, IOException {
		Assert.assertEquals(
				"@delucas entre el 31 de mayo de 2017 y el 31 de mayo de 2018 pasaron 365 días",
				shannon.escuchar("@shannon cuántos días pasaron desde el 31 de mayo de 2017?",USUARIO).getMensaje()
			);
		Assert.assertEquals(
				"@delucas entre el 30 de abril de 2018 y el 31 de mayo de 2018 pasaron 31 días",
				shannon.escuchar("@shannon cuántos días pasaron desde el 30 de abril?",USUARIO).getMensaje()
			);		 
	}
	
	@Test
	public void tiempoHasta() throws ParseException, IOException {
		Assert.assertEquals(
				"@delucas faltan 2 días",
				shannon.escuchar("@shannon cuántos días faltan para el 2 de junio?",USUARIO).getMensaje()
			);
		Assert.assertEquals(
				"@delucas faltan 365 días",
				shannon.escuchar("@shannon cuántos días faltan para el 31 de mayo de 2019?",USUARIO).getMensaje()
			);/*
		Assert.assertEquals(
				"@delucas faltan 42 d�as",
				shannon.escuchar("@shannon cu�ntos d�as faltan para el mundial?",USUARIO)
			);*/
	}
	
}
