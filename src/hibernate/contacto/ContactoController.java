package hibernate.contacto;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dataBaseConection.DataBaseHelper;
import hibernate.usuario.Usuario;
import hibernate.usuario.UsuarioController;

public class ContactoController extends DataBaseHelper{

	
	public static boolean agregarNuevoContacto(String nombreUsuarioIngresado, String nombreNuevoContacto) {
		
		Usuario usuarioContacto = UsuarioController.BuscarUsuario(nombreNuevoContacto);
		
		if(usuarioContacto == null) //No se encontro al usuario buscado para agregar a contactos.
			return false;
		
		
		Usuario usuarioIngresado = UsuarioController.BuscarUsuario(nombreUsuarioIngresado);
		
		Contacto contacto = new Contacto(usuarioIngresado.getId(),usuarioContacto.getId());
		
		Session session = crearSession();	
		Transaction tx = session.beginTransaction();
		try {			
			session.save(contacto);			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
				session.close();	
				return false;
			}							
			
		}
		
		session.close();
		return true;
		
	}

	public static ArrayList<Contacto> buscarContactos(String nombreUsuario) {
		return null;
	}
	
}
