package dataBaseConection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public abstract class DataBaseHelper {	//Esta clase será un generics de guardado y actualizado
	
	public final static Session crearSession() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();	
		return session;
	}
}
