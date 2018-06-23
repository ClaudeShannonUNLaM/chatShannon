package hibernate;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import hibernate.AdivinarMayorMenorMappingClass;

public class AdivinarMayorMenorHibernateManager {
	 
//	public void inicializarBD(){
//		Configuration cfg = new Configuration();
//		cfg.configure("hibernate.cfg.xml");
//		SessionFactory factory = cfg.buildSessionFactory();
//		Session session = factory.openSession();
//	}
	
	public int insertar(int techo, int piso, int ultimo){
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		AdivinarMayorMenorMappingClass ammh = new AdivinarMayorMenorMappingClass();
		ammh.setPiso(piso);
		ammh.setTecho(techo);
		ammh.setUltimoNumeroRespondido(ultimo);
		Transaction tx = session.beginTransaction();
		try {
			session.save(ammh);
			System.out.println("Se genero el registro con exito.....!!");
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			System.out.println("error hibernate");
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
		return ammh.getId();
	}
	
	public boolean actualizar (AdivinarMayorMenorMappingClass ammh) {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction trns = session.beginTransaction();
        try {
            session.update(ammh);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
			factory.close();

        }
        return true;
    }
	public AdivinarMayorMenorMappingClass consultar(int id) {
        AdivinarMayorMenorMappingClass ammh = null;/**/
        Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction trns = session.beginTransaction();
        try {
        	CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<AdivinarMayorMenorMappingClass> cq = cb.createQuery(AdivinarMayorMenorMappingClass.class);
			Root<AdivinarMayorMenorMappingClass> rp = cq.from(AdivinarMayorMenorMappingClass.class);
			cq.select(rp).where(cb.equal(rp.get("id"),id));
			ammh = session.createQuery(cq).getSingleResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
        return ammh;
    }
	
}
