package bot.handlers;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import chat.serverUtils.Mensaje;
import hibernate.AdivinarMayorMenorHibernateManager;
import hibernate.AdivinarMayorMenorMappingClass;
import hibernate.usuario.UsuarioController;
public class AdivinarMayorMenorHandler extends AsistantSentenceHandler {
	
	private int techo,piso,ultimoNumeroRespondido,id,idUsuario;//idUsuario=getUsuarioLlamador!!!!!!	
	///////////////////////////////////////////////////////////HARCODEADO ESTO SACARLO
	public AdivinarMayorMenorHandler () {
		patron = Pattern.compile(".*(jugamos\\?$|más grande|más chico|listo|si\\!$).*");
	}
//	public static void main (String []args){
//		AdivinarMayorMenorHandler a = new AdivinarMayorMenorHandler();
//		System.out.println(a.giveAnswer("jugamos?", "marinolautaro"));
//		System.out.println(a.giveAnswer("¡listo!", "marinolautaro"));
//
//		System.out.println(a.giveAnswer("más grande", "marinolautaro"));
//
//		System.out.println(a.giveAnswer("más chico", "marinolautaro"));
//
//	}
	@Override
	public Mensaje giveAnswer(String mensaje, String nombreUsuario) {
		Mensaje msj =new Mensaje(mensaje);

		Matcher matcher = patron.matcher(mensaje);	
		UsuarioController u = new UsuarioController();
		idUsuario=u.BuscarUsuario(nombreUsuario).getId();
	
		if (matcher.matches()) {			
			switch (matcher.group(1)) { 
				case "jugamos?" :
				{
					msj.setDescripcion("@"+nombreUsuario+" ¡sale y vale! Pensá un numero del 1 al 100");
					msj=new Mensaje("@"+nombreUsuario+" ¡sale y vale! Pensá un numero del 1 al 100");
					return msj;
				}
				case "listo" :
				{
					techo=100;
					piso=0;
					ultimoNumeroRespondido=50;
					AdivinarMayorMenorHibernateManager ammhm = new AdivinarMayorMenorHibernateManager();
					ammhm.insertar(techo, piso, ultimoNumeroRespondido,idUsuario);
					msj.setDescripcion("@"+nombreUsuario+" ¿es el "+ultimoNumeroRespondido+"?");
					msj=new Mensaje("@"+nombreUsuario+" ¿es el "+ultimoNumeroRespondido+"?");
					return msj;
				}
				case "más chico":
				{
					AdivinarMayorMenorHibernateManager ammhm = new AdivinarMayorMenorHibernateManager();
					AdivinarMayorMenorMappingClass ammmc1 = ammhm.consultar(idUsuario);
					piso=ammmc1.getPiso();
					techo=ammmc1.getTecho();
					ultimoNumeroRespondido=ammmc1.getUltimoNumeroRespondido();
					//System.out.println("piso:"+piso+"techo:"+techo);
					techo=ultimoNumeroRespondido;
					ultimoNumeroRespondido=(piso+techo)/2;
					//System.out.println("piso:"+piso+"techo:"+techo);
					AdivinarMayorMenorMappingClass ammmc12 = new AdivinarMayorMenorMappingClass();
					ammmc12.setPiso(piso);
					ammmc12.setTecho(techo);
					ammmc12.setUltimoNumeroRespondido(ultimoNumeroRespondido);
					ammmc12.setIdAdivinar(idUsuario);
					ammmc12.setIdUsuario(idUsuario);
					//System.out.println("AMMMC ID "+ammmc1.getIdUsuario()+"AMMMC TECHO"+ammmc1.getTecho());
					ammhm.actualizar(ammmc12);
					msj.setDescripcion("@"+nombreUsuario+" ¿es el "+ultimoNumeroRespondido+"?");
					msj=new Mensaje("@"+nombreUsuario+" ¿es el "+ultimoNumeroRespondido+"?");
					return msj;
		    	}
				case "si!":
				{
					msj.setDescripcion("@"+nombreUsuario+" fue divertido :)");
					msj=new Mensaje("@"+nombreUsuario+" fue divertido :)");
					return msj;
				}
				case "más grande":
				{

					AdivinarMayorMenorHibernateManager ammhm = new AdivinarMayorMenorHibernateManager();
					AdivinarMayorMenorMappingClass ammmc = ammhm.consultar(idUsuario);
					piso=ammmc.getPiso();
					techo=ammmc.getTecho();
					ultimoNumeroRespondido=ammmc.getUltimoNumeroRespondido();
					//System.out.println("piso:"+piso+"techo:"+techo);
					piso=ultimoNumeroRespondido;
					ultimoNumeroRespondido=(piso+techo)/2;
					//	System.out.println("piso:"+piso+"techo:"+techo);
					AdivinarMayorMenorMappingClass ammmc22 = new AdivinarMayorMenorMappingClass();
					ammmc22.setPiso(piso);
					ammmc22.setTecho(techo);
					ammmc22.setUltimoNumeroRespondido(ultimoNumeroRespondido);
					ammmc22.setIdAdivinar(idUsuario);
					ammmc22.setIdUsuario(idUsuario);
//					System.out.println("AMMMC ID "+ammmc.getIdUsuario()+"AMMMC TECHO"+ammmc.getTecho());
					ammhm.actualizar(ammmc22);
					msj.setDescripcion("@"+nombreUsuario+" ¿es el "+ultimoNumeroRespondido+"?");
					msj=new Mensaje("@"+nombreUsuario+" ¿es el "+ultimoNumeroRespondido+"?");
					return msj;
		    	}
			}
		}
	    else
	    	return this.nextHandler.giveAnswer(mensaje, nombreUsuario);
		return null;
	}
}