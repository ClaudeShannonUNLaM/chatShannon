package hibernate.rss;

import java.util.regex.Pattern;

public class RssClassManager {
	private int idRss,idUsuario;
	private String URL;
	
	public int getIdRss() {
		return idRss;
	}
	public void setIdRss(int idRss) {
		this.idRss = idRss;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public RssClassManager(int idRss, int idUsuario, String uRL) {
		super();
		this.idRss = idRss;
		this.idUsuario = idUsuario;
		URL = uRL;
	}public RssClassManager() {
		
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

		
}
