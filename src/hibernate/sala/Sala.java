package hibernate.sala;

public class Sala {

	private int id;
	private String nombre;
	private boolean privada;
	
	public Sala() {
		
	}
	
	public Sala(String nombre, boolean privada) {
		this.nombre = nombre;
		this.privada = privada;
	}
	
	public Sala(int id, String nombre, boolean privada) {
		this.id = id;
		this.nombre = nombre;
		this.privada = privada;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean isPrivada() {
		return privada;
	}
	public void setPrivada(boolean privada) {
		this.privada = privada;
	}
	
}
