package proyecto;

import java.util.LinkedList;


public class TorreControl {

	private int entrando;
	private int saliendo;
	private int quieroSalir;
	private LinkedList<Integer> quierenEntrar;
	private LinkedList<Integer> quierenSalir;

	public TorreControl() {
		entrando = 0;
		saliendo = 0;
		quieroSalir=0;
		quierenEntrar = new LinkedList<Integer>();
		quierenSalir = new LinkedList<Integer>();
	}

	public synchronized void permisoEntrada(Barco b) {
	
			quierenEntrar.add((int) b.getId());
			System.out.println("El barco "+b.getId()+" quiere Entrar");
			System.out.println(quierenEntrar.toString());
			while (saliendo > 0 || quieroSalir>0 || quierenEntrar.getFirst() != b.getId()) {
				try {
					System.out.println("El barco "+b.getId()+" debe Esperar");
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("El barco "+b.getId()+" va a Entrar");
			quierenEntrar.removeFirst();
			entrando++;
	
	}

	public synchronized void permisoSalida(Barco b) {
	
			quieroSalir++;
			quierenSalir.add((int) b.getId());
			System.out.println("El barco "+b.getId()+" quiere Salir");
			
			System.out.println(quierenSalir.toString());	
			
			while (entrando > 0 || quierenSalir.getFirst() != b.getId()) {
				try {
					System.out.println("El barco "+b.getId()+" debe Esperar");
					wait();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
			System.out.println("El barco "+b.getId()+" va a salir");
			quierenSalir.removeFirst();
			quieroSalir--;
			saliendo++;

	}

	public synchronized void finEntrada(Barco b) {
		
			entrando--;
			notifyAll();
		
	}

	public synchronized void finSalida(Barco b) {
		
			saliendo--;
			notifyAll();
		
	}
}

