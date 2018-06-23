package hibernate.usuario;

public class Usuario {
	
	private int id;
	private String nombre;
	private String password;
	private boolean online;
	
	public Usuario() {
		
	}

	public Usuario(String nombre, String password) {
		this.nombre = nombre;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}
	

	
}
