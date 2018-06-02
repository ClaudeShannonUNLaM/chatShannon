package tests;

import java.text.ParseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import asistente.Asistente;

public class RF12Tests {	
	
	public final static String USUARIO = "delucas";

	Asistente jenkins;

	@Before
	public void setup() {
		jenkins = new Asistente("jenkins");
	}
	
	@Test
	public void leyesDeLaRobotica() throws ParseException{


		try {
			Assert.assertEquals(
			
			"Las Leyes de la Robotica son: " + "1) Un robot no har� da�o a un ser humano o, por inacci�n, permitir que un ser humano sufra da�o." 
			+ "2) Un robot debe obedecer las �rdenes dadas por los seres humanos, excepto si estas �rdenes entrasen en conflicto con la 1� Ley."  
			+ "3) Un robot debe proteger su propia existencia en la medida en que esta protecci�n no entre en conflicto con la 1� o la 2� Ley.",
			jenkins.escuchar("Cuales son las leyes de la robotica, @jenkins")
			);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Assert.assertEquals(
			
			"Las Leyes de la Robotica son: " + "1) Un robot no har� da�o a un ser humano o, por inacci�n, permitir que un ser humano sufra da�o." 
			+ "2) Un robot debe obedecer las �rdenes dadas por los seres humanos, excepto si estas �rdenes entrasen en conflicto con la 1� Ley."  
			+ "3) Un robot debe proteger su propia existencia en la medida en que esta protecci�n no entre en conflicto con la 1� o la 2� Ley.",
			jenkins.escuchar("Me dirias cuales son las LEYES DE LA ROBOTICA, @jenkins")
			);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}