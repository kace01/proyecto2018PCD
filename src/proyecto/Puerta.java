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
		if(b instanceof Mercante) {
			((Mercante) b).getZ().vaciarContenedores((Mercante)b);
		}
		c.finEntrada(b);
	}
	public void salir(Barco b) {
		c.permisoSalida(b);
		for(int i = 0; i<3; i++) {
			System.out.println("Está saliendo el barco: "+b.getId());
		}
		c.finSalida(b);
	}

}
