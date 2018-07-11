package hibernate.adivinado;

public class MayorMenorAdivinadoMappingClass {
	private int cantidadInt,idAdivinar,idUsuario, respuesta;

	
	

	public int getRespuesta() {
		return respuesta;
	}


	public void setRespuesta(int respuesta) {
		this.respuesta = respuesta;
	}


	public int getCantidadInt() {
		return cantidadInt;
	}


	public void setCantidadInt(int cantidadInt) {
		this.cantidadInt = cantidadInt;
	}


	public int getIdAdivinar() {
		return idAdivinar;
	}


	public void setIdAdivinar(int idAdivinar) {
		this.idAdivinar = idAdivinar;
	}


	public int getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}


	public MayorMenorAdivinadoMappingClass(int id,int idU) {
		this.cantidadInt = 0;
		this.idAdivinar = id;
		this.idUsuario=idU;
	}

	
	public MayorMenorAdivinadoMappingClass() {
		
	}

	

}
