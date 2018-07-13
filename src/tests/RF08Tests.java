package tests;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bot.asistente.Asistente;

public class RF08Tests {

	public final static String USUARIO = "delucas";

	Asistente jenkins;

	@Before
	public void setup() {
		jenkins = new Asistente("jenkins");
	}
	@Test
	public void datosFinancieros() throws ParseException, IOException {
		Assert.assertEquals(
				"@delucas, Valor del Dolar: 27.9, "+
				"Variacion anual del Dolar: 56.65, "+
				"Variacion de cotizacion Dolar Billete y Dolar Oficial: 27.9, "+
				"Valor del Merval: 27611, Base Monetaria: 1110100, "+
				"Reservas Internacionales: 61085, Lebac: 954453.",
				jenkins.escuchar("@jenkins mostrame datos financieros")
			);
	}
}