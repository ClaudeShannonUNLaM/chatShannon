package hibernate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import hibernate.AdivinarMayorMenorMappingClass;

public class AdivinarMayorMenorHibernateManager {
	 
//	public void inicializarBD(){
//		Configuration cfg = new Configuration();
//		cfg.configure("hibernate.cfg.xml");
//		SessionFactory factory = cfg.buildSessionFactory();
//		Session session = factory.openSession();
//	}
	
	public int insertar(int techo, int piso, int ultimo,int idUsuario){
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		AdivinarMayorMenorMappingClass ammh = new AdivinarMayorMenorMappingClass();
		ammh.setPiso(piso);
		ammh.setTecho(techo);
		ammh.setUltimoNumeroRespondido(ultimo);
		ammh.setIdUsuario(idUsuario);
		Transaction tx = session.beginTransaction();
		try {
			session.save(ammh);
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
		return ammh.getIdUsuario();
	}
	
	public boolean actualizar (AdivinarMayorMenorMappingClass ammh) {
//		System.out.println("aaaaa");
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction trns = session.beginTransaction();
//		System.out.println("bbbbbb");
		try {
//		    System.out.println("ccccc");
			session.update(ammh);
			System.out.println("Se actualizo el registro con exito.....!!");
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
			System.out.println("error en la actualizacion -.-");
        } finally {
            session.close();
			factory.close();

        }
        return true;
    }
	public AdivinarMayorMenorMappingClass consultar(int id)/*ID DE USUARIO*/ {
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
			cq.select(rp).where(cb.equal(rp.get("idUsuario"),id));
			ammh = session.createQuery(cq).getSingleResult();
		} catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
        if(id==0)System.out.println("ERROR EN LA CNSULTA");
        
        return ammh;
    }
	
}
