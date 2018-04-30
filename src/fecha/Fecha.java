package fecha;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Fecha {

	private Calendar hoy;
	
	public Fecha (){
        this.hoy = Calendar.getInstance();

	}
	
	public Calendar getHoy() {
		return hoy;
	}

	public void setHoy(Calendar hoy) {
		this.hoy = hoy;
	}

	public Fecha (Calendar fecha){
		this.hoy = fecha;
	}
	
	public Calendar diaDentro(int aSumar, String caso) /*throws ParseException*/{
		if (caso=="mes"){
			this.hoy.add(Calendar.MONTH,aSumar);
		}
		else if(caso=="año"){
			this.hoy.add(Calendar.YEAR,aSumar);
		}
		else
			this.hoy.add(Calendar.DAY_OF_MONTH,aSumar);
		return this.hoy;
	}
	public Calendar diaHace(int aRestar, String caso) /*throws ParseException*/{
		if (caso=="mes"){
			this.hoy.add(Calendar.MONTH,-aRestar);
		}
		else if(caso=="año"){
			this.hoy.add(Calendar.YEAR,-aRestar);
		}
		else
			this.hoy.add(Calendar.DAY_OF_MONTH,-aRestar);
		return this.hoy;
	}
	public long restarFechas(Calendar f2, String caso) /*throws ParseException*/{
		Date d1=this.getHoy().getTime();
		Date d2=f2.getTime();
		
		long diffInMillies= d1.getTime()-d2.getTime();
		return Math.abs(TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS))+1;

	
	}
}
