package hibernate.usuarioSala;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.query.internal.NativeQueryImpl;

import dataBaseConection.DataBaseHelper;
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
					+ "where u.nombre = " + nombreUsuario + " and s.privada = 0", Sala.class);		  	
			
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
		
		/*Session sesion = crearSession();		
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
		
		return salas;*/
	}
	
	public static List<Sala> buscarSalasPrivadas(String nombreUsuario) {
		List<Sala> salas = new ArrayList<Sala>();
		Session sesion = crearSession();		
		try {
			
			Query q = sesion.createNativeQuery("select s.* from usuario_sala ua"
					+ " inner join usuario u on u.id = ua.idUsuario "
					+ " inner join sala s on s.id = ua.idSala " 
					+ "where u.nombre = " + nombreUsuario + " and s.privada = 1", Sala.class);		  	
			
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
		
		/*Session sesion = crearSession();		
		List<Sala> salas = new ArrayList<Sala>();
		List<UsuarioSala> relacionUsuarioSalas = new ArrayList<UsuarioSala>();
		
		Usuario usuario = new Usuario(); //Busco el usuario
		
		UsuarioController.BuscarUsuario(nombreUsuario);
		
		try {			
        	CriteriaBuilder cb = sesion.getCriteriaBuilder();
			CriteriaQuery<UsuarioSala> cq = cb.createQuery(UsuarioSala.class);
			Root<UsuarioSala> rp = cq.from(UsuarioSala.class);
			cq.select(rp).where(cb.equal(rp.get("idUsuario"),usuario.getId()));
			
			try{
				relacionUsuarioSalas  = sesion.createQuery(cq).getResultList(); //Obtengo a que salas esta unido
				salas = BuscarSalasPorId(relacionUsuarioSalas); //Obtengo las salas privadas de las que forma parte
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
		*/
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
	
	
	private static List<Sala> BuscarSalasPorId(List<UsuarioSala> relaciones){
		Session sesion = crearSession();		
		List<Sala> salas = new ArrayList<Sala>();
		
		try {			
        	CriteriaBuilder cb = sesion.getCriteriaBuilder();
			CriteriaQuery<Sala> cq = cb.createQuery(Sala.class);
			Root<Sala> rp = cq.from(Sala.class);
			
			
			try{
				for (UsuarioSala relacion : relaciones) {
					cq.select(rp).where(cb.equal(rp.get("idSala"),relacion.getIdSala()));
					salas.add(sesion.createQuery(cq).getSingleResult()); //Obtengo a que salas esta unido	
				}
				
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

}
