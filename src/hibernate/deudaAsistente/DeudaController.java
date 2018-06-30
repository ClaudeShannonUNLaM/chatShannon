package hibernate.deudaAsistente;


import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import dataBaseConection.DataBaseHelper;

public class DeudaController extends DataBaseHelper {
	
	static public DeudaAsistente buscarDeuda(String prestamista, String deudor) {
		DeudaAsistente deuda = null;
		Session sesion = crearSession();
		try {			

			String condition = "";
			if(!prestamista.isEmpty())
				condition += " AND d.prestamista = '" + prestamista+"'";
			if(!deudor.isEmpty())
				condition += " AND d.deudor = '" + deudor+"'";
			Query q = sesion.createQuery("from DeudaAsistente as d where 1 = 1 " + condition);
			
			try{
				deuda = (DeudaAsistente)q.getSingleResult();
			}	
			catch (NoResultException nre){
				//Se evita que termine la ejecución si no se encuentra el registro
			}
			
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            sesion.close();
        }				
		
		return deuda;
	}
	
	public static void agregarDeuda(String prestamista, String deudor, int valor) {
		DeudaAsistente deuda = DeudaController.buscarDeuda(prestamista, deudor);

		if(deuda == null)
			deuda = new DeudaAsistente(prestamista,deudor,valor);
		else
			deuda.sumarValor(valor);
		
		//DeudaAsistente deudaPrestamista = DeudaController.buscar(deudor, prestamista);
		/*if(deudaPrestamista != null) {
			if(deudaPrestamista.getValor() > 0)
				deudaPrestamista.sumarValor(-valor);
		}*/

		Session s = DataBaseHelper.crearSession();
		Transaction tx = s.beginTransaction();
		try {
			s.saveOrUpdate(deuda);
			tx.commit();
		} catch (Exception e) {
			System.out.println("ERROR GUARDANDO");
			tx.rollback();
		}finally {
			s.close();
		}
	}

	public static void pagoDeuda(String prestamista, String deudor, int pago) {
		int valor;
		DeudaAsistente deuda = DeudaController.buscarDeuda(prestamista, deudor);
		if(deuda == null) {
			DeudaController.agregarDeuda(deudor, prestamista, pago);
			return;
		} else {
			valor = deuda.getValor();
		}

		valor -= pago;

		if(valor < 0) {
			DeudaController.agregarDeuda(deudor, prestamista, -valor);
			valor = 0;
		}
		
		deuda.setValor(valor);
		
		Session s = DataBaseHelper.crearSession();
		Transaction tx = s.beginTransaction();
		try {
			s.saveOrUpdate(deuda);
			tx.commit();
		} catch (Exception e) {
			System.out.println("ERROR GUARDANDO");
			tx.rollback();
		}finally {
			s.close();
		}
	}
	
	public static List buscarDeudas(String prestamista, String deudor) {
		List deudas = null;
		Session sesion = crearSession();
		try {			

			String condition = "";
			if(!prestamista.isEmpty())
				condition += " AND d.prestamista = '" + prestamista+"'";
			if(!deudor.isEmpty())
				condition += " AND d.deudor = '" + deudor+"'";
			condition += " AND d.valor > 0";
			Query q = sesion.createQuery("from DeudaAsistente as d where 1 = 1 " + condition);
			
			try{
				deudas = q.list();
			}	
			catch (NoResultException nre){
				//Se evita que termine la ejecución si no se encuentra el registro
			}
			
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            sesion.close();
        }				
		
		return deudas;
	}
	
	public static void limpiarDeudas() {
		
		Session s = DataBaseHelper.crearSession();
		Transaction tx = s.beginTransaction();
		try {
			Query q = s.createQuery("update DeudaAsistente set valor = 0");
			q.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			System.out.println("ERROR GUARDANDO");
			tx.rollback();
		}finally {
			s.close();
		}
	}
}
