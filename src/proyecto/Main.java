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
		
		ArrayList <Grua> gruas;
		gruas = new ArrayList<Grua> ();
		
		ArrayList <Thread> gruasT;
		gruasT = new ArrayList<Thread> ();
		
		ZonaDescarga zona = new ZonaDescarga();
		Thread t;
		for (int i = 0; i <9; i++) {
			barcos.add(new Barco(puerta,true,i));
			t = new Thread(barcos.get(i));
			//barcosT.add(new Thread(barcos.get(i));
			t.start();
		}
		Mercante m = new Mercante(puerta, true, 9, 12, 20, 5, zona);
		barcos.add(m);
		t = new Thread(m);
		t.start();
		
		for (int i = 10; i < 20; i++) {
			barcos.add(new Barco(puerta,false,i));
			t = new Thread(barcos.get(i));
			t.start();
		}
	
		
		for(int i = 1; i <4; i++) {
			gruas.add(new Grua(i, zona));
			t = new Thread(gruas.get(i-1));
			t.start();
		}
		
	/*	try {
			for (int i = 0; i < barcosT.size(); i++) {
				barcosT.get(i).join();
			
		} catch (Exception e) {e.printStackTrace();}
		*/
		System.out.println("FIN");
		
	}
}
