package chat.serverUtils;

public enum FuncionalidadServerEnum {
	
	LOGIN(1),
	CARGARDATOSINICIALES(2),
	OBTENERSALASPUBLICA(3),
	OBTENERSALASPRIVADAS(4),
	OBTENERCONTACTOS(5),
	LLAMARBOT(6),
	ENVIARMENSAJE(7),
	LOGOFF(8);
	
	private int valor;
    private FuncionalidadServerEnum(int valor) {
        this.valor = valor;
    }
	
}
