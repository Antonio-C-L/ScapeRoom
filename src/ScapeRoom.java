import java.util.Scanner;

public class ScapeRoom {
	public static int energy=100,cafe=0,cuchillo=0,receta=0,llave=0,ruta;
	public static Scanner sc=new Scanner(System.in);
	public static void estado() {
		do {
			System.out.println("Energía: "+energy);
			System.out.print("Lista de objetos: ");
			if (cafe==1)
				System.out.print("cafe, ");
			if (cuchillo==1)
				System.out.print("cuchillo, ");
			System.out.println();
			System.out.println("1. Usar un objeto");
			System.out.println("2. Cerrar estado");
			ruta=sc.nextInt();
			switch (ruta) {
			case 1: {
				if (!(cafe==1))
					System.out.println("No puedes usar ningún objeto de los que tienes");
				else
					System.out.println("¿Qué objeto quieres usar?");
				if (cafe==1)
					System.out.println("1. cafe");
				break;
			}
			//comprobar casos
			}
		} while (!(ruta==2));
	}
	public static void main (String[] args) {
		final String combinacion="031011";
		String pruebaComb="0";
		System.out.println("historia");
		do {
			System.out.println("1. Sala 1");
			System.out.println("2. Sala 2");
			System.out.println("3. Sala 3");
			System.out.println("4. Puerta");
			System.out.println("5. Estado");
			ruta=sc.nextInt();
			switch (ruta){
			case 1:{
				//sala 1
				energy-=10;
				do {
					System.out.println("Entras a la cocina");
					System.out.println("1. Investigar ");
					if (cafe==0) 
						System.out.println("2. Coger cafe");
					if (cuchillo==0)
						System.out.println("3. Coger cuchillo de cocina");
					System.out.println("0. Volver");
					System.out.println("10. Estado");
					ruta=sc.nextInt();
					switch (ruta) {
					case 1:{
						if (receta==0) {
							System.out.println("Receta");
							receta=1;
						}
						else
							System.out.println("Nada nuevo");
						break;
					}
					case 2:{
						System.out.println("Coges el cafe");
						cafe=1;
						break;
					}
					case 3:{
						System.out.println("Coges el cuchillo");
						cuchillo=1;
						break;
					}
					case 4:{
						System.out.println("Vuelves a la habitación anterior...");
						break;
					}
					case 10:{
						estado();
						break;
					}
					}
				} while (!(ruta==0));
				break;
			}
			case 2:{
				//sala 2
				do {
					System.out.println("Biblioteca");
					if (receta==1) {
						System.out.println("Buscas libro");
						llave=1;
					}
				} while (!(ruta==0));
				break;
			}
			case 3:{
				//sala 3
				do {

				} while (!(ruta==0));
				break;
			}
			case 4:{
				//puerta trasera
				do {
					System.out.println("Candado.");
					System.out.println("1. Probar combinación");
					System.out.println("2. Alejarse");
					ruta=sc.nextInt();
					if (ruta==1) {
						System.out.print("Introducce la combinación: ");
						pruebaComb=sc.next();
						if (pruebaComb.equals(combinacion)) {
							System.out.println("Sales");
						}
						else System.out.println("No se abre");
					}
					else {
						ruta=2;
					}
				} while (!(ruta==2) && !(pruebaComb.equals(combinacion)));

				break;
			}
			case 0:{
				//estado
				estado();
				break;
			}
			default:{
				//nada/comprobar
				break;
			}
			}
		} while (energy>0 && !(pruebaComb.equals(combinacion)));

		sc.close();
	}
}