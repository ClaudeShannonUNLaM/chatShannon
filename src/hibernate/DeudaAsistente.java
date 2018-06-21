package hibernate;

public class DeudaAsistente {
	protected int id;
	protected String prestamista;
	protected String deudor;
	protected int valor;

	public String getPrestamista() {
		return prestamista;
	}

	public int getId() {
		return id;
	}

	public void setPrestamista(String prestamista) {
		this.prestamista = prestamista;
	}

	public String getDeudor() {
		return deudor;
	}

	public void setDeudor(String deudor) {
		this.deudor = deudor;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	
	
}
