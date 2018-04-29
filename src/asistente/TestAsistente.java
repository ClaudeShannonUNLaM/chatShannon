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
	public void noTeEntiendo(){
		String mensaje = "chacha @shannon";
		
		Assert.assertEquals("Disculpa... no entiendo el pedido, @pepe ¿podrías repetirlo?", shannon.escuchar(mensaje));
	}
	
	@Test
	public void saludo(){
		String mensaje = "Hola @shannon";
		
		Assert.assertEquals("¡Hola, @pepe!", shannon.escuchar(mensaje));
	}
}
