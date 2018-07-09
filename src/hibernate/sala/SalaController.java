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

public class SalaController extends DataBaseHelper {

	public static List<Sala> BuscarSalas() { //Se buscan las salas no privadas
		Session sesion = crearSession();		
		List<Sala> salas = new ArrayList<Sala>();
		try {			
        	CriteriaBuilder cb = sesion.getCriteriaBuilder();
			CriteriaQuery<Sala> cq = cb.createQuery(Sala.class);
			Root<Sala> rp = cq.from(Sala.class);
			
			cq.where(cb.equal(rp.get("privada"), 0));
			cq.select(rp);
			
			try{
				salas = sesion.createQuery(cq).getResultList();
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
	
	
	public static boolean CrearSala(Sala sala) {
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
		
		session.close();
		return true;
	}	
	
	
	
	@SuppressWarnings("unchecked")
	public static Boolean exists (Sala sala,Session sesion) {		
		List<Sala> salas = (List<Sala>) sesion.createQuery("from Sala where nombre = '" + sala.getNombre() + "'").list();		
	    return (salas.size() != 0);
	}
}
