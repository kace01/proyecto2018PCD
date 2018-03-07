package proyecto;

public class Grua implements Runnable{
	int id;
	char ident;
	ZonaDescarga zona;
	public Grua(int id , ZonaDescarga zona) {
		this.id = id;
		this.zona = zona;
	}

	public Grua(int id, char ident, ZonaDescarga zona) {
		this.id = id;
		this.ident = ident;
		this.zona = zona;
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
			while(true)
				try {
					zona.coger(id);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		
	}
}
