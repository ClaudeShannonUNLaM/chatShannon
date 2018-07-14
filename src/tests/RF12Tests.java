package tests;

import java.io.IOException;
import java.text.ParseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bot.asistente.Asistente;

public class RF12Tests {	
	
	public final static String USUARIO = "delucas";

	Asistente jenkins;

	@Before
	public void setup() {
		jenkins = new Asistente("jenkins");
	}
	
	@Test
	public void leyesDeLaRobotica() throws ParseException, IOException{


		try {
			Assert.assertEquals(
			
			"Las Leyes de la Robotica son: " + "1) Un robot no hará daño a un ser humano o, por inacción, permitir que un ser humano sufra daño." 
			+ "2) Un robot debe obedecer las órdenes dadas por los seres humanos, excepto si estas órdenes entrasen en conflicto con la 1º Ley."  
			+ "3) Un robot debe proteger su propia existencia en la medida en que esta protección no entre en conflicto con la 1º o la 2º Ley.",
			jenkins.escuchar("Cuales son las leyes de la robotica, @jenkins",USUARIO)
			);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Assert.assertEquals(

					"Las Leyes de la Robotica son: " + "1) Un robot no hará daño a un ser humano o, por inacción, permitir que un ser humano sufra daño." 
					+ "2) Un robot debe obedecer las órdenes dadas por los seres humanos, excepto si estas órdenes entrasen en conflicto con la 1º Ley."  
					+ "3) Un robot debe proteger su propia existencia en la medida en que esta protección no entre en conflicto con la 1º o la 2º Ley.",
			jenkins.escuchar("Me dirias cuales son las LEYES DE LA ROBOTICA, @jenkins",USUARIO)
			);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}