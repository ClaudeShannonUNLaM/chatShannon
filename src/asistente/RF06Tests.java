package asistente;
import java.text.ParseException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RF06Tests {

	public final static String USUARIO = "delucas";

	Asistente jenkins;

	@Before
	public void setup() {
		jenkins = new Asistente("jenkins");
	}

	@Test
	public void calculos() throws ParseException {

 		Assert.assertEquals(
				"@delucas 3",
				jenkins.escuchar("@jenkins cuánto es 1 + 2")
			);
		
		Assert.assertEquals(
				"@delucas 1",
				jenkins.escuchar("@jenkins cuánto es 5 - 2 * 2")
			);
	
		Assert.assertEquals(
				"@delucas 10",
				jenkins.escuchar("@jenkins cuánto es el 10% de 100")
			);
	
		Assert.assertEquals(
				"@delucas 42",
				jenkins.escuchar("@jenkins cuánto es el 17 + 5 ^ 2")
			);
		
		// agregar otros casos
	}
		
	@Test
	public void calculosCompuestos() throws ParseException {
		Assert.assertEquals(
				"@delucas -6",
				jenkins.escuchar("@jenkins cuánto es (4-8)*2 + 4 / ( 1 + 1)")
			);

		
		// agregar otros casos
	}
	
	@Test
	public void testAdicionales() throws ParseException {
		Assert.assertEquals(
				"@delucas -2",
				jenkins.escuchar("@jenkins cuánto es ((4-8)+2)*2 + 4 / ( 1 + 1)")
			);
		
		Assert.assertEquals(
				"@delucas -3",
				jenkins.escuchar("@jenkins cuánto es ((4-8)+2)*2 + 4 / (( 1 + 1) * 2)")
			);
	}

}
	