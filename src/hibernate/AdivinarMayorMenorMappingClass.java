package hibernate;

public class AdivinarMayorMenorMappingClass {
	private int techo,piso,ultimoNumeroRespondido,idAdivinar,idUsuario;

	
	public int getTecho() {
		return techo;
	}


	public void setTecho(int techo) {
		this.techo = techo;
	}


	public int getPiso() {
		return piso;
	}


	public void setPiso(int piso) {
		this.piso = piso;
	}


	public int getUltimoNumeroRespondido() {
		return ultimoNumeroRespondido;
	}


	public void setUltimoNumeroRespondido(int ultimoNumeroRespondido) {
		this.ultimoNumeroRespondido = ultimoNumeroRespondido;
	}


	public int getIdAdivinar() {
		return idAdivinar;
	}


	public void setIdAdivinar(int id) {
		this.idAdivinar = id;
	}

	public int getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(int id) {
		this.idUsuario = id;
	}

	public AdivinarMayorMenorMappingClass(int techo, int piso, int ultimoNumeroRespondido, int id,int idU) {
		this.techo = techo;
		this.piso = piso;
		this.ultimoNumeroRespondido = ultimoNumeroRespondido;
		this.idAdivinar = id;
		this.idUsuario=idU;
	}

	
	public AdivinarMayorMenorMappingClass() {
		
	}

	

}
