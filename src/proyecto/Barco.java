package proyecto;

public class Barco implements Runnable{
	private boolean direccion;
	private int id;
	private Puerta puerta;

	//True: entra
	//False: salir
	public Barco (Puerta p,  boolean direccion, int id){
		
		this.puerta = p;
		this.id = id;
		this.direccion = direccion;
	}

	public boolean getDireccion() {
		return direccion;
	}

	public void setDireccion(boolean direccion) {
		this.direccion = direccion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Puerta getPuerta() {
		return puerta;
	}

	public void setPuerta(Puerta puerta) {
		this.puerta = puerta;
	}
@Override
public String toString() {
	// TODO Auto-generated method stub
	return "Barco: "+id+'\n';
}
	public void run () {
		if(this.direccion==false){
			puerta.salir(this);
		} else {
			try {
				puerta.entrar(this);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
