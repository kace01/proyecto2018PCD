package proyecto;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Grua implements Runnable{
	int id;
	ZonaDescarga zona;
	final Lock lock = new ReentrantLock(true);
	public Grua(int id , ZonaDescarga zona) {
		this.id = id;
		this.zona = zona;
	}

	
	public Grua() {
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String var = "";
		switch (id) {
		case 1:
			var = "Grua de azúcar";
			break;
		case 2:
			var = "Grua de harina";
			break;
		case 3:
			var = "Grua de sal";
			break;
		}
		return var;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(zona.getCajasQuitadas() < 37){
			lock.lock();
			if((id == 1 && zona.getTipo()==1) || (id == 2 && zona.getTipo()==2) || (id == 3 && zona.getTipo()==3)){
				try {
					zona.recogerCaja(this);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
			lock.unlock();	
		}
	}
		
}
