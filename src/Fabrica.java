import java.util.Scanner;
import java.util.Vector;

public class Fabrica {
	static Scanner teclado = new Scanner(System.in);
	public static void main(String[] args) {
		Vector vec = new Vector(5);
		int op = 0;
		do {
			System.out.println("1) Fabricar coche (conociendo la matrícula)");
			System.out.println("2) Fabricar coche (a partir del nº puertas y el nº de plazas)");
			System.out.println("3) Fabricar coche (a partir de la marca, el modelo y el color)");
			System.out.println("4) Fabricar coche");
			System.out.println("5) Tunear coche (pintándolo de color)"+"\n6) Tunear coche (sin pintarlo)");
			System.out.println("7) Avanzar kilómetros.");	
			System.out.println("8) Mostrar características de un coche.");
			System.out.println("9) Ordenar Coches.");
			System.out.println("10) Sumar KMs recursivo:");
			System.out.println("11) Buscar Recursivo");
			System.out.println("12) Salir del programa.");
			System.out.print("Introduce opción introducida: ");
			try {
				op = Integer.parseInt(teclado.next());
			}catch(Exception e) {
				System.out.println("Solo números entre 1 y 9.");
			}
			menu(vec,op);
		}while(op !=12);
	}
	private static void menu(Vector vec, int op) {
		Coche c=null;
		int num=0;
			switch (op){
			case 1: //pide matricula
				if(Coche.numcoches < Coche.MAX_COCHE) {
					fabricarConMatricula(vec);		
				}else {
					System.out.println("No se puede crear mas coches.");
				}
				break;
			case 2://puertas y plazas
				if(Coche.numcoches < Coche.MAX_COCHE) {
					fabricarConPuertPlazas(vec);
				}else {
					System.out.println("No se puede crear mas coches.");
				}
				break;
			case 3://marca,mdelo y color
				if(Coche.numcoches < Coche.MAX_COCHE) {
					fabricarConmarcolormode(vec);
				}else {
					System.out.println("No se puede crear mas coches.");
				}
				break;
			case 4://constructor vacio
				if(Coche.numcoches < Coche.MAX_COCHE) {
					fabricarConVacio(vec);
				}else {
					System.out.println("No se puede crear mas coches.");
				}
				break;
			case 5://tunear color
				tunearColor(vec,c);
				break;
			case 6://tunear vacio
				tunearVacio(vec,c);
				break;
			case 7://metodo avanzar
				Avanzar(vec,c);
				break;
			case 8:
				mostrarCoche(vec,c);
				break;
			case 9:		
				System.out.println("Dame una opcion para ordenar entre 1 o 2: ");
				try {
					num= teclado.nextInt();
				}catch(Exception e) {
					System.out.println("Solo números entre 1 y 2.");
				}
				if(num ==1) {
					odenarPorKM(vec);
				}else if(num ==2){
					ordenarRecursivo(vec,0,0);
				}
				for(int i=0;i<vec.size();i++) {
					System.out.println(caracteristicas((Coche) vec.get(i)));
				}
				break;
			case 10://sumar recursivo km
				System.out.println("La suma de todos los KMs es: "+sumarRecursivo(vec, 0));
				break;
			case 11://buscar matricula recursivo
				System.out.print("Dime la matrícula: ");
				String mat = teclado.next();
				c = buscarCocheRecursivo(vec,mat,0);
				if(c == null) {
					System.out.println("No se encuentra la matricula.");
				}else {
					System.out.println("\nEste coche  tiene los siguientes datos: "+caracteristicas(c));
				}
				break;
			case 12:
				System.out.println("Fin del programa.");
				break;
			}
	}
	private static Coche buscarCocheRecursivo(Vector vec, String mat, int i) {
		if(i==vec.size()) {
			return null;		
		}else {
			if(((Coche) vec.get(i)).getMatricula().equalsIgnoreCase(mat)) {
				return (Coche) vec.get(i);
			}else {
				return buscarCocheRecursivo(vec,mat,i+1);
			}
		}
	}
	private static float sumarRecursivo(Vector vec,int i) {
		if(i== vec.size()-1) {
			return ((Coche) vec.get(i)).getKilometro();
		}else {
			return ((Coche) vec.get(i)).getKilometro()+sumarRecursivo(vec, i+1);
		} 
	}
	private static void odenarPorKM(Vector vec) {
		Coche max=null;
		for(int i=0; i<vec.size();i++) {
			for(int j=i+1; j<vec.size()-1;j++) {
				if(((Coche) vec.get(i)).getKilometro() > ((Coche) vec.get(j+1)).getKilometro()) {
					max = (Coche) vec.get(i);
					vec.set(i, vec.get(j+1));
					vec.set(j+1, max);
				}else {
					if(((Coche) vec.get(j+1)).getKilometro() < ((Coche) vec.get(i)).getKilometro()) {
						max = (Coche) vec.get(i);
						vec.set(i, vec.get(j+1));
						vec.set(j+1, max);
					}
				}
			}
		}
	}
	private static void ordenarRecursivo(Vector vec,int i,int j) {//ordenar recurivamente los km
		Object max;
		if(i==vec.size()-1) {
			if(((Coche) vec.get(i)).getKilometro() > ((Coche) vec.get(j+1)).getKilometro()) {
				max = (Coche) vec.get(i);
				vec.set(i, vec.get(j+1));
				vec.set(j+1, max);
			}
			if(j == vec.size()-2) {
				ordenarRecursivo(vec,i+1,i+1);
			}else {
				ordenarRecursivo(vec,i,j+1);
			}
		}
	}
	private static void mostrarCoche(Vector vec, Coche c) {
		System.out.print("Dime la matrícula: ");
		String matricula = teclado.next();
		c = buscarCoche(vec,matricula);
		if(c != null) {
			System.out.println("\nEste coche  tiene los siguientes datos: "+caracteristicas(c));
		}else {
			System.out.println("No se encuentra la matricula");
		}
	}
	private static void Avanzar(Vector vec, Coche c) {
		int ava=0;
		System.out.print("Dime la matrícula: ");
		String matricula= teclado.next();
		c = buscarCoche(vec,matricula);	
		if(c != null) {
			System.out.println("Dime cuanto quieres avanzar: ");
			try {
				ava = Integer.parseInt(teclado.next());
			}catch(Exception e) {
				System.out.println("Valor incorrecto.");
			}
			c.avanzar(ava);
			System.out.println("Se a avanzado "+c.getKilometro()+"Km");
			System.out.println("\nEste coche  tiene los siguientes datos: "+caracteristicas(c));
		}else {
			System.out.println("No se encuentra la matricula");
		}
	}
	private static void tunearVacio(Vector vec, Coche c) {
		System.out.print("Dime la matrícula: ");
		String matri = teclado.next();
		c = buscarCoche(vec,matri);
		if(c != null) {
			if(c.getTechoSolar() == true) {
				System.out.println("Ya tiene instalado el techo");
			}else {
				System.out.println(c.tunear());
				System.out.println("Este coche tiene los siguientes datos: "+caracteristicas(c));
			}
		}else {
			System.out.println("No se encuentra la matricula");
		}
		
	}
	private static void tunearColor(Vector vec, Coche c) {
		System.out.print("Dime la matrícula: ");
		String matricula = teclado.next();
		System.out.println("Dime el color: ");
		String color = teclado.next();
		c= buscarCoche(vec,matricula);
		if(c != null) {
			if(c.getTechoSolar() == true) {
				System.out.println("Ya tiene instalado el techo");
			}else {
				c.tunear(color);
				System.out.println("Este coche tiene los siguientes datos: "+caracteristicas(c));
			}
		}else {
			System.out.println("No se encuentra la matricula");
		}
	}
	private static void fabricarConVacio(Vector vec) {
		Coche car4 = new Coche();
		car4.matricular(matAleatoria());
		car4.avanzar((int) ((float)(Math.floor(Math.random()*(10000-0+1))+0)));
		Coche.numcoches++;
		System.out.println("Este coche tiene los siguientes datos: "+caracteristicas(car4));
		vec.add(car4);
	}
	private static void fabricarConmarcolormode(Vector vec) {
		System.out.print("Dime la marca del coche: ");
		String marca= teclado.next();
		System.out.print("Dime el modelo del coche: ");
		String modelo= teclado.next();
		System.out.print("Dime el color del coche: ");
		String color= teclado.next();
		Coche car3 = new Coche(marca,modelo,color);
		car3.matricular(matAleatoria());
		System.out.println("Este coche tiene los siguientes datos: "+caracteristicas(car3));
		Coche.numcoches++;
		vec.add(car3);		
	}
	private static void fabricarConPuertPlazas(Vector vec) {
		int numP=0,numPl=0;
		System.out.print("Dime el nº puertas: ");
		try {
			numP = Integer.parseInt(teclado.next());
		}catch(Exception e) {
			System.out.println("Valor incorrecto.");
			numP=3;
		}
		System.out.print("Dime el nº de plazas: ");
		try {
			numPl = Integer.parseInt(teclado.next());
		}catch(Exception e) {
			System.out.println("Valor incorrecto.");
			numPl=5;
		}
		Coche car2 = new Coche(numP,numPl);
		car2.matricular(matAleatoria());
		System.out.println("Este coche tiene los siguientes datos: "+caracteristicas(car2));
		Coche.numcoches++;
		vec.add(car2);
	}
	private static void fabricarConMatricula(Vector vec) {
		System.out.print("Dime la matrícula: ");
		String matricula = teclado.next();
		Coche car1 = new Coche(matricula);
		vec.add(car1);
		Coche.numcoches++;
		System.out.println("Este coche tiene los siguientes datos: "+caracteristicas(car1));
	}
	private static Coche buscarCoche(Vector vec, String matricula) {
		Coche c=null;
		int posi = 0;
		while(posi < vec.size() && c==null) {
			if(((Coche) vec.get(posi)).getMatricula().equalsIgnoreCase(matricula)) {
				c=(Coche) vec.get(posi);
			}
			posi++;
		}
		return c;
	}
	
	private static String matAleatoria() {
		String matricula="";
		String letras ="BCDFGHJKLMNPQRSTVWXYZ";
		for(int i=0;i<4;i++) {
			matricula = matricula + ((int)(Math.floor(Math.random()*(9-0+1))+0));
		}
		matricula = matricula+"-";
		for(int i=0;i<2;i++) {
			matricula = matricula +""+ letras.charAt((int)(Math.floor(Math.random()*(20-0+1))+0));
		}
		return matricula;
	}
	private static String caracteristicas(Coche c) {
		String tech;
		if(c.getTechoSolar() == true) {
			tech="Sí";
		}else {
			tech="No";
		}
		return "\n Matricula: "+c.getMatricula()+"\n Marca: "+c.getMarca()+"\n Modelo: "+c.getModelo()+"\n Color: "+c.getColor()+"\n TechoSolar: "
				+tech+"\n KM: "+c.getKilometro()+"\n NumPlazas: "+c.getNumPlazas()+"\n NumPuertas: "+c.getNumPuertas();
	}
}