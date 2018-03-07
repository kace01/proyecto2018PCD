package proyecto;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ZonaDescarga {
	
	int cont;
	/* Para la exclusión mutua */
	final Lock lock = new ReentrantLock(true);
	/* Variables de condición */
	final Condition lleno = lock.newCondition();
	final Condition vacio = lock.newCondition();
	
	
	public void descargar(int contenedor) throws InterruptedException {
		lock.lock();
		try {
			while (contenedor > 0 ) 
				vacio.await(); 
			
			/* s.c. */
			switch(contenedor) {
			
			case 1:
				System.out.println("Descargado azúcar");
				break;
			case 2:
				System.out.println("Descargado harina");
				break;
			case 3:
				System.out.println("Descargado sal");
				break;
			}	
			
			
		}
		/* Salida de la s.c. */
		finally  {
			lock.unlock();
		}
	}
	
	public void coger (int contenedor) throws InterruptedException {
		lock.lock();
		try {
			
			/* s.c. */
			switch(contenedor) {
			
			case 1:
				System.out.println("Cargado azúcar");
				break;
			case 2:
				System.out.println("Cargado  harina");
				break;
			case 3:
				System.out.println("Cargado  sal");
				break;
			}	
			contenedor = 0;
			vacio.signalAll();
			
		}
		/* Salida de la s.c. */
		finally  {
			lock.unlock();
		}
	}
	
	public void vaciarContenedores(Mercante m) throws InterruptedException {
		while(m.getAzucar() > 0 || m.getHarina() > 0 ||m.getSal() > 0) {
			if(m.getAzucar() > 0) {
				this.descargar(1);//azucar
				m.setAzucar(m.getAzucar()-1);
				
			}
			if(m.getHarina() > 0) {
				this.descargar(2);//harina
				m.setHarina(m.getHarina()-1);
			}
			if(m.getSal() > 0) {
				this.descargar(3);//sal
				m.setSal(m.getSal()-1);
				}
		}
	}
}
