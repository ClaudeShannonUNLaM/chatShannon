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
		
		if(exists(sala, session))
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
	
	
	
	@SuppressWarnings("unchecked")
	public static Boolean exists (Sala sala,Session sesion) {		
		List<Sala> salas = (List<Sala>) sesion.createQuery("from Sala where nombre = '" + sala.getNombre() + "'").list();		
	    return (salas.size() != 0);
	}
}
