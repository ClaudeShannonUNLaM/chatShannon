package hibernate.contacto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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

	public static List<Usuario> buscarContactos(String nombreUsuario) { //Devuelve los contactos del usuario
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Session sesion = crearSession();		
		try {			

			Query q = sesion.createNativeQuery("select u.* from usuario u"
					+ " inner join contacto c on u.id = c.idContacto "
					+ " where u.nombre = '" + nombreUsuario + "'" , Usuario.class);
		  	
			
			try{ 
				usuarios = q.getResultList();
			}	
			catch (NoResultException nre){
				//Se evita que termine la ejecuci�n si no se encuentra el registro
			}						
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            sesion.close();            
        }
		
		return usuarios;	  	
		
	}
	
}
