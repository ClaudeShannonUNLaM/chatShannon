package bot.handlers;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import chat.serverUtils.Mensaje;
import hibernate.AdivinarMayorMenorHibernateManager;
import hibernate.AdivinarMayorMenorMappingClass;
import hibernate.adivinado.MayorMenorAdivinadoHibernateManager;
import hibernate.adivinado.MayorMenorAdivinadoMappingClass;
import hibernate.usuario.UsuarioController;
import tests.RF10Tests;

public class MayorMenorAdivinadoHandler  extends AsistantSentenceHandler{
	private int respuesta, id, idUsuario,conteoIntentos;Random rnd;

	public MayorMenorAdivinadoHandler () {
		patron = Pattern.compile(".*(del 1 al 100|es el|fue divertido).*");
	}
	
	@Override
	public Mensaje giveAnswer(String mensaje, String nombreUsuario) {
		Mensaje msj = new Mensaje(mensaje);
		Matcher matcher = patron.matcher(mensaje);
		//System.out.println(nombreUsuario);
		UsuarioController u = new UsuarioController();
		
		if (matcher.matches()) {
			idUsuario=u.BuscarUsuario(nombreUsuario).getId();

			switch (matcher.group(1)) { 
				case "del 1 al 100" :
				{
					 rnd = new Random();

//					this.respuesta = (int)(rnd.nextDouble() * 100 + 0);
					this.respuesta = RF10Tests.ELEGIDO;
					
					MayorMenorAdivinadoHibernateManager ammhm = new MayorMenorAdivinadoHibernateManager();
					ammhm.insertar(idUsuario, this.respuesta);
		    		msj.setMensaje("@"+nombreUsuario+" ¡listo!");
		    		return msj;
		    	}
				
				case "es el":
				{
					MayorMenorAdivinadoHibernateManager ammhm = new MayorMenorAdivinadoHibernateManager();
					MayorMenorAdivinadoMappingClass ammmc1 = ammhm.consultar(idUsuario);
					conteoIntentos=ammmc1.getCantidadInt();
					conteoIntentos++;
					ammmc1.setCantidadInt(conteoIntentos);
					ammhm.actualizar(ammmc1);
					respuesta=ammmc1.getRespuesta();
		    		String intento = mensaje.replaceAll("\\D", "");
		    		msj.setMensaje("@"+nombreUsuario+" "+evaluarIntento(Integer.parseInt(intento)));
					this.respuesta = (int)(rnd.nextDouble() * 100 + 0);
					msj=new Mensaje("@"+nombreUsuario+" "+evaluarIntento(Integer.parseInt(intento)));
		    		return msj;
		    	}
				
			}
		}
	    else
	    	return this.nextHandler.giveAnswer(mensaje, nombreUsuario);
		return null;
	}
	
	public String evaluarIntento (int intento) {
		if(intento<respuesta)
			return "más grande";
		else
		{
			if(intento>respuesta)
				return "más chico";
			else
				return "¡si! Adivinaste en "+conteoIntentos+" pasos...";
		}
	}
}