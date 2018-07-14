package hibernate.contacto;

import hibernate.usuario.UsuarioController;

public class Contacto {

	private int id;
	private int idUsuario;
	private int idContacto;
	
	public Contacto() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdContacto() {
		return idContacto;
	}

	public void setIdContacto(int idContacto) {
		this.idContacto = idContacto;
	}

	public Contacto(int idUsuairo, int idContacto) {
		this.idUsuario = idUsuairo;
		this.idContacto = idContacto;
	}
}
