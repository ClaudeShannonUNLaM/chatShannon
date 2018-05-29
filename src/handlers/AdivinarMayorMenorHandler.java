package handlers;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdivinarMayorMenorHandler extends AsistantSentenceHandler {
private int techo,piso,ultimoNumeroRespondido;
private String RF10Path = "C://Users//Tomi//Desktop//RF10.txt";//C:\Users\Tomi\Desktop\Progra avanzada
	public AdivinarMayorMenorHandler () throws IOException {
		patron = Pattern.compile(".*(jugamos\\?$|más grande|más chico|listo|si\\!$).*");
		}
	
	@Override
	public String giveAnswer(String mensaje, String nombreUsuario) {
		Matcher matcher = patron.matcher(mensaje);		
		if (matcher.matches()) {
			
			switch (matcher.group(1)) { 
				case "jugamos?" :
				{
					return "@"+nombreUsuario+" ¡sale y vale! Pensá un número del 1 al 100";
				}
				case "listo" :
				{
					techo=100;
					piso=0;
					ultimoNumeroRespondido=50;
					try{
					PrintWriter salida3 = new PrintWriter(new FileWriter(RF10Path));
					salida3.println(piso);
					salida3.println(" ");
					salida3.println(techo);
					salida3.println(" ");
					salida3.println(ultimoNumeroRespondido);
					salida3.close(); 
					}//  preparo el arch de salida
				    catch (IOException i){
				    	
				    }
					
					return "@"+nombreUsuario+" ¿es el "+ultimoNumeroRespondido+"?";
				}
				case "más chico":
				{

					try{
						Scanner sc = new Scanner(new File (RF10Path));
						piso= sc.nextInt(); // vector para almacenar la lectura
						techo= sc.nextInt();
						ultimoNumeroRespondido=sc.nextInt();
						sc.close();
						techo=ultimoNumeroRespondido;
						ultimoNumeroRespondido=(piso+techo)/2;
						PrintWriter salida = new PrintWriter(new FileWriter(RF10Path));
						salida.println(piso);
						salida.println(" ");
						salida.println(techo);
						salida.println(" ");
						salida.println(ultimoNumeroRespondido);
						salida.close(); 
						}//  preparo el arch de salida
					    catch (IOException f){
					    	
					    }
					return "@"+nombreUsuario+" ¿es el "+ultimoNumeroRespondido+"?";
		    	}
				case "si!":
				{
					return "@"+nombreUsuario+" fue divertido :)";
				}
				case "más grande":
				{

					try{
						Scanner sc = new Scanner(new File (RF10Path));
						piso= sc.nextInt(); // vector para almacenar la lectura
						techo= sc.nextInt();
						ultimoNumeroRespondido=sc.nextInt();
						sc.close();
						piso=ultimoNumeroRespondido;
						ultimoNumeroRespondido=(piso+techo)/2;

						PrintWriter salida2 = new PrintWriter(new FileWriter(RF10Path));
						salida2.println(piso);
						salida2.println(" ");
						salida2.println(techo);
						salida2.println(" ");
						salida2.println(ultimoNumeroRespondido);
//						salida.println("||"+techo);
						salida2.close(); 
						}//  preparo el arch de salida
					    catch (IOException f){
					    	
					    }
					return "@"+nombreUsuario+" ¿es el "+ultimoNumeroRespondido+"?";
		    	}
			}
		}
	    else
	    	return this.nextHandler.giveAnswer(mensaje, nombreUsuario);
		return null;
	}
}