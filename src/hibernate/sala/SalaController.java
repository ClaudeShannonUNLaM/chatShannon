package hibernate.sala;

import java.nio.file.attribute.AclEntry.Builder;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import dataBaseConection.DataBaseHelper;
import hibernate.AdivinarMayorMenorMappingClass;
import hibernate.usuario.Usuario;
import hibernate.usuario.UsuarioController;
import hibernate.usuarioSala.UsuarioSala;
import hibernate.usuarioSala.UsuarioSalaController;

public class SalaController extends DataBaseHelper {
	
	
	public static boolean CrearSala(int idCreador,Sala sala) {
		Session session = crearSession();
		
		Sala salaExistente = exists(sala.getNombre(), session);
		if(salaExistente != null)
			return false;
		
		Transaction tx = session.beginTransaction();
		try {			
			session.save(sala);			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
				session.close();	
				return false;
			}							
			
		}	
		
		UsuarioSalaController.agregarParticipanteSala(idCreador,sala);
		
		session.close();
		return true;
	}	
	
	
	public static boolean invitarSala(String nombreUsuario,String nombreSala) {
		Session session = crearSession();
		
		Sala salaExistente = exists(nombreSala, session);
		if(salaExistente == null)
			return false;
		
		
		Usuario usuarioExistente = UsuarioSalaController.obtenerPorNombre(nombreUsuario, session);
		
		if(usuarioExistente == null)
			return false;
		
		Transaction tx = session.beginTransaction();
		UsuarioSala invitacion= new UsuarioSala();
		invitacion.setIdSala(salaExistente.getId());
		invitacion.setIdUsuario(usuarioExistente.getId());
		try {			
			session.save(invitacion);			
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
	
	
	
	@SuppressWarnings("unchecked")
	public static Sala exists (String nombreSala,Session sesion) {	
		Sala sala = null;		
		try {
			
			Query q = sesion.createNativeQuery("select * from sala ua"
					+ " where nombre = '" + nombreSala + "'" , Sala.class);		  	
			
			try{ 
				sala  =(Sala) q.uniqueResult();
			}	
			catch (NoResultException nre){
				//Se evita que termine la ejecuci�n si no se encuentra el registro
			}						
        } catch (HibernateException e) {
            e.printStackTrace();
        } 
		
		return sala;		
	}
}
