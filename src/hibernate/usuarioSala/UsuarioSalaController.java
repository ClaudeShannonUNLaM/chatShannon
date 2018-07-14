package hibernate.usuarioSala;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.query.internal.NativeQueryImpl;

import dataBaseConection.DataBaseHelper;
import hibernate.AdivinarMayorMenorMappingClass;
import hibernate.sala.Sala;
import hibernate.usuario.Usuario;
import hibernate.usuario.UsuarioController;

public class UsuarioSalaController extends DataBaseHelper {


	public static List<Sala> BuscarSalasPublicas(String nombreUsuario) { //Se buscan las salas publicas
		List<Sala> salas = new ArrayList<Sala>();
		Session sesion = crearSession();		
		try {
			
			Query q = sesion.createNativeQuery("select s.* from usuario_sala ua"
					+ " inner join usuario u on u.id = ua.idUsuario "
					+ " inner join sala s on s.id = ua.idSala " 
					+ "where u.nombre = '" + nombreUsuario + "' and s.privada = 0", Sala.class);		  	
			
			try{ 
				salas  = q.getResultList();
			}	
			catch (NoResultException nre){
				//Se evita que termine la ejecuci�n si no se encuentra el registro
			}						
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            sesion.close();            
        }
		
		return salas;	
	}
	
	public static List<Sala> buscarSalasPrivadas(String nombreUsuario) {
		List<Sala> salas = new ArrayList<Sala>();
		Session sesion = crearSession();		
		try {
			
			Query q = sesion.createNativeQuery("select s.* from usuario_sala ua"
					+ " inner join usuario u on u.id = ua.idUsuario "
					+ " inner join sala s on s.id = ua.idSala " 
					+ "where u.nombre = '" + nombreUsuario + "' and s.privada = 1", Sala.class);		  	
			
			try{ 
				salas  = q.getResultList();
			}	
			catch (NoResultException nre){
				//Se evita que termine la ejecuci�n si no se encuentra el registro
			}						
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            sesion.close();            
        }
		
		return salas;
	}
	
	public static Usuario obtenerPorNombre(String nombreUsuario,Session sesion) {
		Usuario usu; 
		usu = (Usuario) sesion.createQuery("from usuario where nombre = '" + nombreUsuario +"'");
		return usu;
	}
	
	public static List<Usuario> getUsuariosPorSala(int salaId) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Session sesion = crearSession();		
		try {
			
			Query q = sesion.createNativeQuery("SELECT u.* FROM Usuario as u"
					+ " INNER JOIN Usuario_Sala us on us.idUsuario = u.id "
					+ " WHERE us.idSala = " + salaId, Usuario.class);
		  	
			
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

	
	public static boolean agregarParticipanteSala(int idUsuario, Sala sala) {
		Session sesion = crearSession();		
		try {			
			UsuarioSala relacion = new UsuarioSala();
			relacion.setIdSala(sala.getId());
			relacion.setIdUsuario(idUsuario);
			
			Transaction tx = sesion.beginTransaction();
		try {
			sesion.save(relacion);			
			tx.commit();
		}catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			System.out.println("Error hibernate en el creado");
			e.printStackTrace();
			}			
        } catch (HibernateException e) {
            e.printStackTrace();            
            return false;
        } finally {
            sesion.close();            
        }
		return true;		 	
		
	}
}
