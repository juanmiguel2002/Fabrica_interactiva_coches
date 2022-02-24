
public class Coche {
	public static final int MAX_COCHE = 5;
	public static int numcoches;
	private String matricula;
	private String marca;
	private String modelo;
	private String color;
	private boolean techoSolar;
	private float kilometro;
	private int numPuertas;
	private int numPlazas;
	
	public Coche() {
		//this.matricular(matricula);
		this.marca="SEAT";
		this.modelo="ALTEA";
		this.color="blanco";
		this.numPlazas=5;
		this.numPuertas=3;
	}
	public Coche(String matri) {
		this.matricula = matri;
		this.marca="SEAT";
		this.modelo="ALTEA";
		this.color="blanco";
		this.numPlazas=5;
		this.numPuertas=3;
	}
	public Coche(int numP,int numPlaza) {
		this.setNumPlazas(numPlaza);
		this.setNumPuertas(numP);
		this.color="blanco";
		this.marca="SEAT";
		this.modelo="ALTEA";
	}	
	public Coche(String marca, String mode, String color) {
		this.marca= marca;
		this.color= color;
		this.modelo= mode;
		this.numPlazas=5;
		this.numPuertas=3;
	}
	public String tunear() {
		this.kilometro=0;
		if(!this.techoSolar) {//false
			this.techoSolar= true;
		}
		return "Se ha aplicado el techo solar";
	}
	public String tunear(String color) {
		tunear();
		this.color = color;
		return tunear()+ " y el color. ";
	}
	public void matricular(String matri) {
		this.matricula = matri;
	}
	public void avanzar(int km) {
		 this.setKilometro(km+this.kilometro);
	}
	

	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public boolean getTechoSolar() {
		return techoSolar;
	}
	public boolean isTechoSolar(boolean b) {
		this.techoSolar = b;
		return this.techoSolar;
	}
	public float getKilometro() {
		return kilometro;
	}
	public void setKilometro(float km) {
		if(km < 0) {
			System.out.println("Kilometros negativos");
		}else {
			this.kilometro = km;
		}
	}
	public int getNumPuertas() {
		return numPuertas;
	}
	public void setNumPuertas(int num) {
		if(num >5 || num <3) {
			System.out.println("Intrudouce un valor entre 3 y 5");
		}else {
			this.numPuertas = num;
		}
	}
	public int getNumPlazas() {
		return numPlazas;
	}
	public void setNumPlazas(int num) {
		if(num >7 || num <2) {
			System.out.println("Intrudouce un valor entre 2 y 7");
		}else {
			this.numPlazas = num;
		}
	}
}
