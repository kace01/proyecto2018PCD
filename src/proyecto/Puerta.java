package proyecto;

public class Puerta {
	
	TorreControl c;
	public Puerta (TorreControl c){
		this.c = c;
	}
	public void entrar(Barco b) throws InterruptedException {
		c.permisoEntrada(b);
		for(int i = 0; i<3; i++) {
			System.out.println("Está entrando el barco: "+b.getId());
		}
		c.finEntrada(b);
		if(b instanceof Mercante) {
			System.out.println("Ha entrado el mercante: "+b.getId());
		while(((Mercante) b).cantidad()>0) {
			
			((Mercante) b).getZona().desempacar((Mercante)b);
		/*	if(((Mercante) b).getAzucar()>0) {
				((Mercante) b).getZona().desempacar(1);
				((Mercante) b).setAzucar(((Mercante) b).getAzucar()-1);
			}
			if(((Mercante) b).getHarina()>0) {
				((Mercante) b).getZona().desempacar(2);
				((Mercante) b).setHarina(((Mercante) b).getHarina()-1);
			}
			if(((Mercante) b).getSal()>0) {
				((Mercante) b).getZona().desempacar(3);
				((Mercante) b).setSal(((Mercante) b).getSal()-1);
			}
			*/
		}
	}
		
	}
	public void salir(Barco b) {
		c.permisoSalida(b);
		for(int i = 0; i<3; i++) {
			System.out.println("Está saliendo el barco: "+b.getId());
		}
		c.finSalida(b);
	}

}
