package hibernate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class AdivinarMayorMenorMappingClass {
	private int techo,piso,ultimoNumeroRespondido,id;

	
	public int getTecho() {
		return techo;
	}


	public void setTecho(int techo) {
		this.techo = techo;
	}


	public int getPiso() {
		return piso;
	}


	public void setPiso(int piso) {
		this.piso = piso;
	}


	public int getUltimoNumeroRespondido() {
		return ultimoNumeroRespondido;
	}


	public void setUltimoNumeroRespondido(int ultimoNumeroRespondido) {
		this.ultimoNumeroRespondido = ultimoNumeroRespondido;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public AdivinarMayorMenorMappingClass(int techo, int piso, int ultimoNumeroRespondido, int id) {
		super();
		this.techo = techo;
		this.piso = piso;
		this.ultimoNumeroRespondido = ultimoNumeroRespondido;
		this.id = id;
	}

	public AdivinarMayorMenorMappingClass(int techo, int piso, int ultimoNumeroRespondido) {
		super();
		this.techo = techo;
		this.piso = piso;
		this.ultimoNumeroRespondido = ultimoNumeroRespondido;
	}
	public AdivinarMayorMenorMappingClass() {
		
	}

	

}
