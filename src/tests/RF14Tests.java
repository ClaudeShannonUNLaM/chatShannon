package tests;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bot.asistente.Asistente;
import hibernate.deudaAsistente.DeudaController;

public class RF14Tests {
	public final static String USUARIO = "delucas";


	Asistente shannon;
	
	@Before
	public void setup(){
		shannon = new Asistente("shannon");
		DeudaController.limpiarDeudas();
	}
	
	@Test
	public void transferenciaDeDeudas() throws ParseException, IOException{
		Assert.assertEquals(
				"@delucas anotado.",
				shannon.escuchar("@shannon @juan me debe $50",USUARIO).getMensaje()
			);
		Assert.assertEquals(
				"@delucas @juan te debe $50",
				shannon.escuchar("@shannon cuánto me debe @juan?",USUARIO).getMensaje()
			);
		Assert.assertEquals(
				"@delucas anotado.",
				shannon.escuchar("@shannon le debo $60 a @maria",USUARIO).getMensaje()
			);
		
		Assert.assertEquals(
				"@delucas le debés $60 a @maria. @juan te debe $50.",
				shannon.escuchar("@shannon cual es mi estado de deudas?",USUARIO).getMensaje()
			);
		
		Assert.assertEquals(
				"@delucas bueno.",
				shannon.escuchar("@shannon simplificar deudas con @juan y @maria",USUARIO).getMensaje()
			);
		
		Assert.assertEquals(
				"@delucas le debés $10 a @maria.",
				shannon.escuchar("@shannon cual es mi estado de deudas?",USUARIO).getMensaje()
			);
		// por detrás, ahora @juan le debe $50 a @maria. Podría probarse,
		// cambiando el interlocutor del asistente
	}

	@Test
	public void deudasGrupalesCasoUno() throws ParseException, IOException{
		
		Assert.assertEquals(
				"@delucas anotado.",
				shannon.escuchar("@shannon con @juan y @maria gastamos $300 y pagó @juan",USUARIO).getMensaje()
			);
		
		Assert.assertEquals(
				"@delucas le debés $100 a @juan.",
				shannon.escuchar("@shannon cual es mi estado de deudas?",USUARIO).getMensaje()
			);
		// @maria le debe otros $100 a @juan
	
	}
	
	@Test
	public void deudasGrupalesCasoDos() throws ParseException, IOException{
		Assert.assertEquals(
				"@delucas anotado.",
				shannon.escuchar("@shannon con @juan y @maria gastamos $300 y pagué yo",USUARIO).getMensaje()
			);
		
		Assert.assertEquals(
				"@delucas @juan te debe $100. @maria te debe $100.",
				shannon.escuchar("@shannon cual es mi estado de deudas?",USUARIO).getMensaje()
			);
	}
	
	@Test
	public void deudasSimples() throws ParseException, IOException{
		Assert.assertEquals(
				"@delucas anotado.",
				shannon.escuchar("@shannon @juan me debe $500",USUARIO).getMensaje()
			);
		Assert.assertEquals(
				"@delucas @juan te debe $500",
				shannon.escuchar("@shannon cuánto me debe @juan?",USUARIO).getMensaje()
			);
		
		Assert.assertEquals(
				"@delucas anotado.",
				shannon.escuchar("@shannon @juan me pagó $501",USUARIO).getMensaje()
			);
		Assert.assertEquals(
				"@delucas @juan no te debe nada. Vos le debés $1",
				shannon.escuchar("@shannon cuánto me debe @juan?",USUARIO).getMensaje()
			);
		Assert.assertEquals(
				"@delucas debés $1 a @juan",
				shannon.escuchar("@shannon cuánto le debo a @juan?",USUARIO).getMensaje()
			);

		Assert.assertEquals(
				"@delucas anotado.",
				shannon.escuchar("@shannon le pagué a @juan $10",USUARIO).getMensaje()
			);
		Assert.assertEquals(
				"@delucas @juan te debe $9",
				shannon.escuchar("@shannon cuánto me debe @juan?",USUARIO).getMensaje()
			);
		Assert.assertEquals(
				"@delucas no le debés nada. @juan te debe $9",
				shannon.escuchar("@shannon cuánto le debo a @juan?",USUARIO).getMensaje()
			);
	}
}
