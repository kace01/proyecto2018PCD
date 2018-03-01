package proyecto;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		TorreControl control = new TorreControl();
		Puerta puerta = new Puerta(control);
		ArrayList <Barco> barcos;
		barcos = new ArrayList<Barco> ();
		
		ArrayList <Thread> barcosT;
		barcosT = new ArrayList<Thread> ();
		
		for (int i = 0; i < 10; i++) {
			barcos.add(new Barco(puerta,true,i));
			barcosT.add(new Thread(barcos.get(i)));
		}
		for (int i = 10; i < 20; i++) {
			barcos.add(new Barco(puerta,false,i));
			barcosT.add(new Thread(barcos.get(i)));
		}
		for (int i = 0; i < barcosT.size(); i++) {
			barcosT.get(i).start();
		}
		
		
		try {
			for (int i = 0; i < barcosT.size(); i++) {
				barcosT.get(i).join();
			}
		} catch (Exception e) {e.printStackTrace();}
		
		System.out.println("FIN");
		
	}
}
