package handlers;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import hibernate.AdivinarMayorMenorHibernateManager;
import hibernate.AdivinarMayorMenorMappingClass;
public class AdivinarMayorMenorHandler extends AsistantSentenceHandler {
	
	private int techo,piso,ultimoNumeroRespondido,id,idUsuario=1;//idUsuario=getUsuarioLlamador!!!!!!	
	///////////////////////////////////////////////////////////HARCODEADO ESTO SACARLO
	public AdivinarMayorMenorHandler () {
		patron = Pattern.compile(".*(jugamos\\?$|más grande|más chico|listo|si\\!$).*");
	}
	
	@Override
	public String giveAnswer(String mensaje, String nombreUsuario) {
		Matcher matcher = patron.matcher(mensaje);		
		if (matcher.matches()) {			
			switch (matcher.group(1)) { 
				case "jugamos?" :
				{
					return "@"+nombreUsuario+" ¡sale y vale! Pensá un numero del 1 al 100";
				}
				case "listo" :
				{
					techo=100;
					piso=0;
					ultimoNumeroRespondido=50;
					AdivinarMayorMenorHibernateManager ammhm = new AdivinarMayorMenorHibernateManager();
					ammhm.insertar(techo, piso, ultimoNumeroRespondido,idUsuario);
					return "@"+nombreUsuario+" ¿es el "+ultimoNumeroRespondido+"?";
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
					return "@"+nombreUsuario+" ¿es el "+ultimoNumeroRespondido+"?";
		    	}
				case "si!":
				{
					return "@"+nombreUsuario+" fue divertido :)";
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
					return "@"+nombreUsuario+" ¿es el "+ultimoNumeroRespondido+"?";
		    	}
			}
		}
	    else
	    	return this.nextHandler.giveAnswer(mensaje, nombreUsuario);
		return null;
	}
}