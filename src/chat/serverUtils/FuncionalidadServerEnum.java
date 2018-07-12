package chat.serverUtils;

public enum FuncionalidadServerEnum {
	
	LOGIN(1),
	CARGARDATOSINICIALES(2),
	NUEVOUSUARIO(3),	
	ENVIARMENSAJE(4),
	NUEVASALA(5),	
	NUEVOCONTACTO(6),
	LOGOFF(7);
	
	private int valor;
    private FuncionalidadServerEnum(int valor) {
        this.valor = valor;
    }
	
}
