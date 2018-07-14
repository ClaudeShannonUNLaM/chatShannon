package hibernate.adivinado;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import hibernate.AdivinarMayorMenorMappingClass;

public class MayorMenorAdivinadoHibernateManager {
	 
//	public void inicializarBD(){
//		Configuration cfg = new Configuration();
//		cfg.configure("hibernate.cfg.xml");
//		SessionFactory factory = cfg.buildSessionFactory();
//		Session session = factory.openSession();
//	}
	
	public int insertar(int idUsuario, int respuesta){
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		MayorMenorAdivinadoMappingClass ammh = new MayorMenorAdivinadoMappingClass();
		ammh.setCantidadInt(0);
		ammh.setIdUsuario(idUsuario);
		ammh.setRespuesta(respuesta);
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
	
	public boolean actualizar (MayorMenorAdivinadoMappingClass ammh) {
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
	public MayorMenorAdivinadoMappingClass consultar(int id)/*ID DE USUARIO*/ {
        MayorMenorAdivinadoMappingClass ammh = null;/**/
        Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction trns = session.beginTransaction();
        try {
        	CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<MayorMenorAdivinadoMappingClass> cq = cb.createQuery(MayorMenorAdivinadoMappingClass.class);
			Root<MayorMenorAdivinadoMappingClass> rp = cq.from(MayorMenorAdivinadoMappingClass.class);
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
