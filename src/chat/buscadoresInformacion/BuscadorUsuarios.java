package chat.buscadoresInformacion;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dataBaseConection.DataBaseHelper;
import hibernate.AdivinarMayorMenorMappingClass;
import hibernate.usuario.Usuario;

public class BuscadorUsuarios extends DataBaseHelper {

	public boolean usuarioYaCreado(String nombreUsuario, String passWord, boolean importaSoloElNombre) {
		Session sesion = crearSession();		
		Usuario usuario = null;
		try {			
        	CriteriaBuilder cb = sesion.getCriteriaBuilder();
			CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
			Root<Usuario> rp = cq.from(Usuario.class);
			
			if(importaSoloElNombre)
				cq.select(rp).where(cb.equal(rp.get("nombre"),nombreUsuario));
			else
				cq.select(rp).where(cb.equal(rp.get("nombre"),nombreUsuario), cb.equal(rp.get("password"),passWord));			
			
			try{
				usuario = sesion.createQuery(cq).getSingleResult();
			}	
			catch (NoResultException nre){
				//Se evita que termine la ejecución si no se encuentra el registro
			}						
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            sesion.close();            
        }		
		
		if(usuario == null)
			return false;
		
		return true;
	}
	
	
	public Usuario BuscarUsuario(String nombreUsuario) {
		Session sesion = crearSession();		
		Usuario usuario = null;
		try {			
        	CriteriaBuilder cb = sesion.getCriteriaBuilder();
			CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
			Root<Usuario> rp = cq.from(Usuario.class);
			cq.select(rp).where(cb.equal(rp.get("nombre"),nombreUsuario));						
			
			try{
				usuario = sesion.createQuery(cq).getSingleResult();
			}	
			catch (NoResultException nre){
				//Se evita que termine la ejecución si no se encuentra el registro
			}						
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            sesion.close();            
        }				
		
		return usuario;		
	}
	
	
	public boolean crearNuevoUsuario(String nombreUsuario, String passWord) {
	
		if(usuarioYaCreado(nombreUsuario,passWord, true))
			return false;
		
		boolean insercionConExito = false;
		Session session = crearSession();		
		Usuario usuario = new Usuario(nombreUsuario,passWord);		
		Transaction tx = session.beginTransaction();
		try {
			session.save(usuario);			
			tx.commit();
			insercionConExito = true;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();			
			e.printStackTrace();
		} finally {
			session.close();	
		}		
		
		return insercionConExito;
	}
}
