package hibernate.usuarioSala;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import dataBaseConection.DataBaseHelper;
import hibernate.sala.Sala;
import hibernate.usuario.Usuario;
import hibernate.usuario.UsuarioController;

public class UsuarioSalaController extends DataBaseHelper {

	
	
	public static List<Sala> BuscarSalaUsuario(String nombreUsuario) {
		Session sesion = crearSession();		
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
