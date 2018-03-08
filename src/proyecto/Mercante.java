package proyecto;

public class Mercante extends Barco {
	private int azucar, harina, sal;
	private ZonaDescarga zona;
	Grua grua = new Grua();
	
	public Mercante(Puerta p, boolean direccion, int id, int azucar, int sal, int harina, ZonaDescarga z) {
		super(p, direccion, id);
		this.azucar = azucar;
		this.harina = harina;
		this.sal = sal;
		this.zona = z;
		}

	public int getAzucar() {
		return azucar;
	}

	public void setAzucar(int azucar) {
		this.azucar = azucar;
	}

	public int getHarina() {
		return harina;
	}

	public void setHarina(int harina) {
		this.harina = harina;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}

	public ZonaDescarga getZona() {
		return zona;
	}

	public void setZona(ZonaDescarga z) {
		this.zona = z;
	}
	public int cantidad () {
		return sal+azucar+harina;
	}
	public boolean vacio () {
	boolean vacio = true;
		if(sal > 0)
			vacio = false;
		if(azucar > 0)
			vacio = false;
		if(harina > 0)
			vacio = false;
		
		return vacio;
	}

}
