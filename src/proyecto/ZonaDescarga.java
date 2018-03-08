package proyecto;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ZonaDescarga {
	
	int cont;
	int tipo;
	int cajasQuitadas = 0;
	

	public void setCajasQuitadas(int cajasQuitadas) {
		this.cajasQuitadas = cajasQuitadas;
	}

	/* Para la exclusión mutua */
	final Lock lock = new ReentrantLock(true);
	/* Variables de condición */
	final Condition lleno = lock.newCondition();
	final Condition vacio = lock.newCondition();
	
	public void desempacar(Mercante barco) throws InterruptedException{
		lock.lock();
		try {
			while(cont>=1){
				lleno.await();//Hay una caja, no se puede poner
			}		
			
			//coloco caja 
			if(barco.getAzucar()>0) {
				tipo = 1;
				barco.setAzucar(barco.getAzucar()-1);
			}
			else {
				if(barco.getHarina()>0) {
					tipo = 2;
					barco.setHarina(barco.getHarina()-1);
				}else {
					if(barco.getSal()>0) {
						tipo = 3;
						barco.setSal(barco.getSal()-1);
					}
				}
			}
			
			cont++;
			System.out.println("El barco mercante ha colocado una caja de tipo "+tipo+".");

			vacio.signal();//Hay una caja, se puede recoger

		} finally {
			lock.unlock();
		}
	}

	/*
	 * Metodo que gestiona si una grua puede o no quitar la caja de la plataforma.
	 */
	public void recogerCaja(Grua grua) throws InterruptedException{
		lock.lock();
		try {
			while(cont==0){
				vacio.await();//No hay una caja, no se puede recoger
			}
			cont--;
			cajasQuitadas++;
			System.out.println(cajasQuitadas+"."+grua.toString()+" de la plataforma ha quitado una caja de tipo "+tipo+".");
			lleno.signal();//No hay una caja, se debe poner
	
		} finally {
			lock.unlock();
		}
		
	}
	public int getCajasQuitadas() {
		return cajasQuitadas;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	/*
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
	}*/
}
