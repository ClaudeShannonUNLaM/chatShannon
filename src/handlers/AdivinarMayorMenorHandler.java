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
	
	private int techo,piso,ultimoNumeroRespondido;	
	public AdivinarMayorMenorHandler () {
		patron = Pattern.compile(".*(jugamos\\?$|m�s grande|m�s chico|listo|si\\!$).*");
	}
	
	@Override
	public String giveAnswer(String mensaje, String nombreUsuario) {
		Matcher matcher = patron.matcher(mensaje);		
		int id=0;
		if (matcher.matches()) {			
			switch (matcher.group(1)) { 
				case "jugamos?" :
				{
					return "@"+nombreUsuario+" �sale y vale! Pens� un n�mero del 1 al 100";
				}
				case "listo" :
				{
					techo=100;
					piso=0;
					ultimoNumeroRespondido=50;
//					try{
//					PrintWriter salida3 = new PrintWriter(new FileWriter("RF10.txt"));
//					salida3.println(piso);
//					salida3.println(" ");
//					salida3.println(techo);
//					salida3.println(" ");
//					salida3.println(ultimoNumeroRespondido);
//					salida3.close(); 
//					}//  preparo el arch de salida
//				    catch (IOException i){
//				    	
//				    }
					AdivinarMayorMenorHibernateManager ammhm = new AdivinarMayorMenorHibernateManager();
					id=ammhm.insertar(techo, piso, ultimoNumeroRespondido);
					return "@"+nombreUsuario+" �es el "+ultimoNumeroRespondido+"?";
				}
				case "m�s chico":
				{
					AdivinarMayorMenorHibernateManager ammhm = new AdivinarMayorMenorHibernateManager();
					AdivinarMayorMenorMappingClass ammmc = ammhm.consultar(id);
					piso=ammmc.getPiso();
					techo=ammmc.getTecho();
					ultimoNumeroRespondido=ammmc.getUltimoNumeroRespondido();
//					try{
//						Scanner sc = new Scanner(new File ("RF10.txt"));
//						piso= sc.nextInt(); // vector para almacenar la lectura
//						techo= sc.nextInt();
//						ultimoNumeroRespondido=sc.nextInt();
//						sc.close();
						techo=ultimoNumeroRespondido;
						ultimoNumeroRespondido=(piso+techo)/2;
//						PrintWriter salida = new PrintWriter(new FileWriter("RF10.txt"));
//						salida.println(piso);
//						salida.println(" ");
//						salida.println(techo);
//						salida.println(" ");
//						salida.println(ultimoNumeroRespondido);
//						salida.close(); 
						ammmc.setPiso(piso);
						ammmc.setTecho(techo);
						ammmc.setUltimoNumeroRespondido(ultimoNumeroRespondido);
						ammhm.actualizar(ammmc);
						//}//  preparo el arch de salida
//					    catch (IOException f){
//					    	
//					    }
					return "@"+nombreUsuario+" �es el "+ultimoNumeroRespondido+"?";
		    	}
				case "si!":
				{
					return "@"+nombreUsuario+" fue divertido :)";
				}
				case "m�s grande":
				{

					AdivinarMayorMenorHibernateManager ammhm = new AdivinarMayorMenorHibernateManager();
					AdivinarMayorMenorMappingClass ammmc = ammhm.consultar(id);
					piso=ammmc.getPiso();
					techo=ammmc.getTecho();
					ultimoNumeroRespondido=ammmc.getUltimoNumeroRespondido();
//					try{
//						Scanner sc = new Scanner(new File ("RF10.txt"));
//						piso= sc.nextInt(); // vector para almacenar la lectura
//						techo= sc.nextInt();
//						ultimoNumeroRespondido=sc.nextInt();
//						sc.close();
						piso=ultimoNumeroRespondido;
						ultimoNumeroRespondido=(piso+techo)/2;

//						PrintWriter salida2 = new PrintWriter(new FileWriter("RF10.txt"));
//						salida2.println(piso);
//						salida2.println(" ");
//						salida2.println(techo);
//						salida2.println(" ");
//						salida2.println(ultimoNumeroRespondido);
////						salida.println("||"+techo);
//						salida2.close(); 
						ammmc.setPiso(piso);
						ammmc.setTecho(techo);
						ammmc.setUltimoNumeroRespondido(ultimoNumeroRespondido);
						ammhm.actualizar(ammmc);
						//}//  preparo el arch de salida
//					    catch (IOException f){
//					    	
//					    }
					return "@"+nombreUsuario+" �es el "+ultimoNumeroRespondido+"?";
		    	}
			}
		}
	    else
	    	return this.nextHandler.giveAnswer(mensaje, nombreUsuario);
		return null;
	}
}