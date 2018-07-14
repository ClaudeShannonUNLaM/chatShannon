package hibernate.rss;


import java.util.LinkedList;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.List;
import org.hibernate.query.Query;

import dataBaseConection.*;
import hibernate.AdivinarMayorMenorMappingClass;
import hibernate.rss.RssClassManager;

public class RssCreation extends DataBaseHelper {

	public int insertar(String URL,int idUsuario){
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		RssClassManager rss = new RssClassManager();
		rss.setIdUsuario(idUsuario);
		rss.setURL(URL);
		Transaction tx = session.beginTransaction();
		try {
			session.save(rss);
			System.out.println("Se genero el registro con exito.....!!");
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			System.out.println("error hibernate en el creado");
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
		return rss.getIdUsuario();
	}
//	public static void main(String []args){
//		insertar("http:",1);
//		System.out.println(devolverListaURLS(1)[2]);
//	}
	public String[] devolverListaURLS(int idUsuario){
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		RssClassManager rss = new RssClassManager();
		Transaction tx = session.beginTransaction();		String []vec =new String[10];

		/*try {			

			String condition = "";
			try{
				//java.util.List<RssClassManager> list= (java.util.List<RssClassManager> )sesion.createQuery("from Rss where Rss.idUsario="+idUsuario).list();
				CriteriaQuery<RssClassManager> cq = session.createNamedQuery(RssClassManager.class);
				Root<RssClassManager> rp = cq.from(RssClassManager.class);
				cq.select(rp).where(((CriteriaBuilder) session).equal(rp.get("idUsuario"),idUsuario));
				java.util.List <RssClassManager> list = session.createQuery(cq).getResultList();
				for (int i=0;i<list.size();i++){
					vec[i]=list.get(i).getURL();
				}
				
			}	
			catch (NoResultException nre){
				//Se evita que termine la ejecución si no se encuentra el registro
			}
			
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            sesion.close();
        }	*/			
		try {
        	CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<RssClassManager> cq = cb.createQuery(RssClassManager.class);
			Root<RssClassManager> rp = cq.from(RssClassManager.class);
			cq.select(rp).where(cb.equal(rp.get("idUsuario"),idUsuario));
			java.util.List <RssClassManager> list = session.createQuery(cq).getResultList();
			for (int i=0;i<list.size();i++){
				vec[i]=list.get(i).getURL();
			}
		} catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
		/**/
	
		return vec;
	}
}
	
	
	

