package chat.serverUtils;

import java.io.File;

import hibernate.sala.Sala;
import hibernate.usuario.Usuario;

public class Mensaje {
	private Usuario emisor;
	private Sala sala;
	private Usuario usuarioDestinatario;
	private File video;
	private File imagen;
	private String mensaje;
	private String descripcion;
	private String link;
	
	public Mensaje(Usuario emisor, Sala sala, String mensaje){
		this.emisor = emisor;
		this.sala = sala;
		this.mensaje = mensaje;
	}

	
	public Usuario getEmisor() {
		return emisor;
	}

	public void setEmisor(Usuario emisor) {
		this.emisor = emisor;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Usuario getUsuarioDestinatario() {
		return usuarioDestinatario;
	}

	public void setUsuarioDestinatario(Usuario usuarioDestinatario) {
		this.usuarioDestinatario = usuarioDestinatario;
	}

	public File getVideo() {
		return video;
	}

	public void setVideo(File video) {
		this.video = video;
	}

	public File getImagen() {
		return imagen;
	}

	public void setImagen(File imagen) {
		this.imagen = imagen;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	

}
