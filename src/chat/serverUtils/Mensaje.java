package chat.serverUtils;

import java.io.File;

public class Mensaje {
	private File video;
	private File imagen;
	private String descripcion;
	private String Link;
	
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getLink() {
		return Link;
	}
	public void setLink(String link) {
		Link = link;
	}
}
