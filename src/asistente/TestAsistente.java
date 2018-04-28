package asistente;
import org.junit.*;


public class TestAsistente{
	
	
	public final static String USUARIO = "pepe";
	
	Asistente shannon;
	
	@Before
	public void setup(){
		shannon = new Asistente("shannon");
	}
	
	@Test
	public void saludo(){
		String mensaje = "Hola @shannon";
		
		Assert.assertEquals("¡Hola, @pepe!", shannon.escuchar(mensaje));
	}
}
