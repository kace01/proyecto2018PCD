package proyecto;
//
//import java.util.*;
//
//public class TorreControl {
//	int barcosEntrando = 0;
//	int barcosSaliendo = 0;
//	int quiereSalir = 0;
//	
//	 Queue<Barco> colaEntrada=new LinkedList<Barco>();
//	 Queue<Barco> colaSalida=new LinkedList<Barco>();
//	
//	
//	public synchronized void permisoEntrada(Barco barco) {
//		
//		if(!colaEntrada.contains(barco)) {
//			colaEntrada.add(barco);
//		}
//		if()
//		while(barcosSaliendo >0  || quiereSalir > 0 || colaEntrada.peek().getId() != barco.getId()) {
//			try {
//				System.out.println("El barco "+barco.getId()+" está bloqueado para entrar.");
//				wait();
//			} catch (Exception e) {
//				{e.printStackTrace();}
//			}
//		}
//		/*S.C*/
//		barcosEntrando++;
//		System.out.println("El barco "+barco.getId()+" está entrando");
//		System.out.println("----->Hay: "+barcosEntrando+" barcos entrando");
//		
//	}
//	public synchronized void permisoSalida(Barco barco) {
//		if(!colaSalida.contains(barco)) {
//			colaSalida.add(barco);
//		}
//		while(barcosEntrando >0 || colaSalida.peek().getId() != barco.getId() ) {
//			try {
//				System.out.println("El barco "+barco.getId()+" está bloqueado para salir.");
//				quiereSalir++;
//				System.out.println("*****Quieren salir -> "+quiereSalir+" barcos.");
//				wait();
//			} catch (Exception e) {
//				{e.printStackTrace();}
//			}
//		}
//		/*S.C*/
//		
//		barcosSaliendo++;
//		System.out.println("El barco "+barco.getId()+" saliendo");
//		System.out.println("----->Hay: "+barcosSaliendo+" barcos saliendo");
//		
//	}
//	public synchronized void finEntrada(Barco barco){
//		
//		colaEntrada.remove();
//		barcosEntrando--;
//		System.out.println("El barco "+barco.getId()+" ha entrado.");
//		notifyAll();
//		
//	}
//	
//	public synchronized void finSalida(Barco barco){
//		if(quiereSalir > 0) {
//			quiereSalir--;
//		}
//		colaSalida.remove();
//		barcosSaliendo--;
//		System.out.println("El barco "+barco.getId()+" ha salido.");
//		notifyAll();
//		
//	}
//}

import java.util.LinkedList;
import java.util.Queue;

public class TorreControl {

	
	int barcosEntrando = 0;
	int barcosSaliendo = 0;
	
	int barcosEsperandoParaSalir = 0;
	
	
		 Queue<Barco> colaEntrada=new LinkedList<Barco>();
		 Queue<Barco> colaSalida=new LinkedList<Barco>();
	 
	TorreControl(){
		
	}
	
	public synchronized void permisoEntrada(Barco barco){
		

		if(!colaEntrada.contains(barco)){
			colaEntrada.add(barco);
		}
		while(barcosSaliendo>0 || barcosEsperandoParaSalir >0 || colaEntrada.peek().getId() != barco.getId()){
			try {
				System.out.println("El barco "+barco.getId()+" bloqueado para entrar.");
				wait();
			} catch (Exception e) {
				{e.printStackTrace();}
			}
		}

		barcosEntrando++;
		System.out.println("El barco "+barco.getId()+" tiene permiso para Entrar, procede a entrar...");
	}
	
	public synchronized void permisoSalida(Barco barco){
		if(!colaSalida.contains(barco)){
			colaSalida.add(barco);
			barcosEsperandoParaSalir++;
		}
		
		
		while(barcosEntrando>0 || colaSalida.peek().getId() != barco.getId()){
			try {
				System.out.println("El barco "+barco.getId()+" bloqueado para salir.");
				wait();
			} catch (Exception e) {
				{e.printStackTrace();}
			}
		}
	
		barcosSaliendo++;
		System.out.println("El barco "+barco.getId()+" tiene permiso para salir, procede a salir...");
		
	}
	
	public synchronized void finEntrada(Barco barco){
		
		
		colaEntrada.remove();
		barcosEntrando--;
		System.out.println("Ha entrado el barco "+barco.getId()+".");
		notifyAll();
		
	}
	
	public synchronized void finSalida(Barco barco){
		
		
		if(barcosEsperandoParaSalir>0){
			barcosEsperandoParaSalir--;
		}		
		colaSalida.remove();
		barcosSaliendo--;
		System.out.println("El barco "+barco.getId()+" ha salido.");
		notifyAll();
		
	}

}

