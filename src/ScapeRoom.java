import java.util.Scanner;

public class ScapeRoom {
	/*Creación de 42134 variables, creo que todas tienen un nombre bastante explicativo
	Algunas como los objetos son para ver si el jugador los tiene en el inventario
	Otras se usan en la pelea
	Tambien decir que estas variables las he creado fuera del main para que puedan ser usadas por los subprogramas (aunque no sea muy correcto es lo mejor por ahora)
	 */
	public static int vida=100,energy=100,queso=0,cuchillo=0,hojaReceta=0,llave=0,llaveAntigua=0,pinzas=0,golpeJug,golpeZom,dañoMaxArma,dañoJug,dañoZom,vidaZombie=25,recoil,rendirte;
	public static char accion; 
	//Variable usada para la toma de decisiones, al principio era un int pero como no queria que se pudiese saber que habia mas opciones disponibles por los numeros
	public static Scanner sc=new Scanner(System.in); //Scanner inicializado fuera porque lo uso en un subprograma
	public static void pelea (int dañoMaxArma2) { //subprograma de la pelea con el zombie, entra el dato del daño maximo del arma para calcular el daño que le haces (el del zombie es fijo)
		golpeJug=(int)Math.floor(Math.random()*10+1);
		energy-=5;
		if (golpeJug>5) {
			dañoJug=(int)Math.floor(Math.random()*dañoMaxArma2+1);
			vidaZombie-=dañoJug;
		}
		golpeZom=(int)Math.floor(Math.random()*10+1);
		if (golpeZom>5) {
			dañoZom=(int)Math.floor(Math.random()*5+1);
			vida-=dañoZom;
		}
		dañoMaxArma2=0;
	}
	public static void estado() { //subprograma que muestra el estado (vida, energia, objetos) y ademas permite usar, o ver, ciertos objetos para mas información
		do {
			System.out.println("Vida: "+vida);
			System.out.println("Energía: "+energy);
			System.out.print("Lista de objetos: ");
			/*Un monton de condicionales para que la lista solo muestre los objetos que posee el jugador, 
			asi como unos cuantos condicionales más para que la lista se vea bonita (con comas entre medio si es necesario y punto al final).
			Deseando ver objetos, arrays, etc para que cosas como esta sean mas sencillas (o al menos no ocupen tanto)
			 */
			if (queso==1) {
				System.out.print("cuña de queso");
				if ((cuchillo+hojaReceta+llave+llaveAntigua+pinzas)==0)
					System.out.print(".");
			}
			if (cuchillo==1) {
				if (queso==1)
					System.out.print(", ");
				System.out.print("cuchillo");
				if ((hojaReceta+llave+llaveAntigua+pinzas)==0)
					System.out.print(".");
			}
			if (cuchillo==2) { //No esta mal, el cuchillo es especial y puede romperse para eso le he dado un segundo estado en el que esta agrietado (esto se tiene en cuenta cada vez es usado en el codigo
				if (queso==1)
					System.out.print(", ");
				System.out.print("cuchillo agrietado");
				if ((hojaReceta+llave+llaveAntigua+pinzas)==0)
					System.out.print(".");
			}
			if (hojaReceta==1) {
				if ((queso+cuchillo)>=1)
					System.out.print(", ");
				System.out.print("hoja de una receta");
				if ((llave+llaveAntigua+pinzas)==0)
					System.out.print(".");
			}
			if (llave==1) {
				if ((queso+cuchillo+hojaReceta)>=1)
					System.out.print(", ");
				System.out.print("llave");
				if ((llaveAntigua+pinzas)==0)
					System.out.print(".");
			}
			if (llaveAntigua==1) {
				if ((queso+cuchillo+hojaReceta+llave)>=1)
					System.out.print(", ");
				System.out.print("llave antigua");
				if (pinzas==0)
					System.out.print(".");
			}
			if (pinzas==1) {
				if ((queso+cuchillo+hojaReceta+llave+llaveAntigua)>=1)
					System.out.print(", ");
				System.out.print("pinzas");
				System.out.print(".");
			}
			System.out.println();//Aqui termina la lista y meto un "salto de página"
			System.out.println("U. Usar/ver un objeto");
			System.out.println("C. Cerrar estado");
			accion=sc.next().toUpperCase().charAt(0);
			switch (accion) {//Opción e usar un objeto o cerrar el estado con un switch
			case 'U': {
				if ((queso+cuchillo+hojaReceta+llave+llaveAntigua+pinzas)==0)//Comrpobacion de que posee al menos un objeto (si tuviera alguno la suma deberia dar 1 o superior)
					System.out.println("No puedes usar ningún objeto porque no tienes objetos");//Caso de no poseer ningun objeto
				else {//Caso de poseer al menos un objeto muesta los que posee con una letra para elegir cual usar
					System.out.println("¿Qué objeto quieres usar?");
					if (queso==1)
						System.out.println("Q. Cuña de queso");
					if (cuchillo==1)
						System.out.println("C. Cuchillo");
					if (cuchillo==2)
						System.out.println("C. Cuchillo");
					if (hojaReceta==1)
						System.out.println("H. Hoja de una receta");
					if (llave==1)
						System.out.println("L. Llave");
					if (llaveAntigua==1)
						System.out.println("A. Llave antigua");
					if (pinzas==1) 
						System.out.println("P. Pinzas");
					System.out.println("N. No usar ninguno");
					accion=sc.next().toUpperCase().charAt(0);
					switch (accion) {//casos a elegir
					case 'Q': {
						if (queso==1) { /*Comprobación de que el caso elegido esta disponible 
						(al ser opciones que no siempre estan disponibles debo hacer una comprobacion de que realmente esa opcion esta disponible),
						 sino la hiciera el jugador podria elegir esa opcion incluso si no le sale en la lista en vez de decir "opcion no valida"

						 IMPORTANTE!! Esto se aplica a todos los casos siguientes a lo largo del código siempre que el caso en cuestion pueda o no estar disponible,
						 IMPORTANTE!! solo lo comento aqui porque es el primero, pero no quiero llenar el codigo con este mismo comentario una y otra vez
						 */
							System.out.println("Recuperas algo de energía (50)");
							energy+=50;
							queso=0;
						}
						else
							System.out.println("Opción no valida, introduzca una opción correcta.");
						break;
					}
					case 'C':{
						switch (cuchillo) {//Como ya dije arriba el caso del cuchillo es especial y cada vez que sale se tiene en cuenta con un switch su estado (dejare de repetir este comentario)
						case 1:{
							System.out.println("Un cuchillo de cocina muy simple, nada fuera de lo normal");
							break;
						}
						case 2:{
							System.out.println("Un cuchillo de cocina que se ha agrietado debido a un mal uso");
							break;
						}
						case 0:{
							System.out.println("Opción no valida, introduzca una opción correcta.");
							break;
						}
						}
						break;
					}
					case 'H':{
						if (hojaReceta==1) {
							System.out.println("Una hoja de una... ¿receta? arrancada de algun libro, tiene algunos ingredientes tachados, otros nuevos escritos con prisa asi como multiples garabatos que no logras comprender");
							System.out.println("Solo puedes sacar a deducir dos cosas: quien escribio esto tenia prisa y que el resultado no tiene pinta de ser comestible ¿tal vez no es una receta de cocina?");
							System.out.println("No tienes forma de comprobarlo, pero la letra te resulta extrañamente familiar como si fuese tu propia letra");
						}
						else
							System.out.println("Opción no valida, introduzca una opción correcta.");
						break;
					}
					case 'L':{
						if (llave==1) {
							System.out.println("Una llave normal y corriente sin nada digno de mencion");
						}
						else
							System.out.println("Opción no valida, introduzca una opción correcta.");
						break;
					}
					case 'A':{
						if (llaveAntigua==1) {
							System.out.println("Una llave grande, pesada y muy antigua.");
						}
						else
							System.out.println("Opción no valida, introduzca una opción correcta.");
						break;
					}
					case 'P':{
						if (pinzas==1) {
							System.out.println("Unas pinzas de metal muy largas, probablemente fabricadas para manejar el fuego");
						}
						else
							System.out.println("Opción no valida, introduzca una opción correcta.");
						break;
					}
					case 'N':{
						System.out.println("Decides no usar ningun objeto ahora mismo");
						break;
					}
					default:{
						System.out.println("Opción no valida, introduzca una opción correcta.");
					}
					}
				}
				break;
			}
			case 'C':{
				System.out.println("Cerrando estado...");
				break;
			}
			default:{
				System.out.println("Opción no valida, introduzca una opción correcta.");
			}
			}
		} while (!(accion=='C'));//El estado solo se cerrara cuando el jugador asi lo desee
	}
	public static void main (String[] args) {//Programa principal
		final String combinacion="031011"; //combinacion de la "puerta trasera", mas información de donde sale este número en su final correspondiente
		//PD.: Es un String porque queria que el primer número fuese un 0, con int salia un valor extraño
		int queso1=0, cuchillo1=0, llave1=0, llaveAntigua1=0,agua=0,candado=0,loreZombie=0,end=0,cocina=0,biblioteca=0,dormitorio=0;
		//Unas cuantas variables para tener en cuenta si el objeto ya ha sido recogido o si ya se ha observado algo, asi como una variable para que se termine el programa (end)
		String pruebaComb="0";//para comprobar si coincide o no incluso sin haber intentado poner alguna debo inicializarla
		System.out.println("El juego es corto pero con multiples cosas que hacer, cada vez que te muevas o hagas ciertas acciones consumiras algo de energia."+"\n"
				+ "Tambien habra ciertos casos donde puedas perder vida, suerte y que lo disfrutes.");
		System.out.println("Vida inicial: "+vida);
		System.out.println("Energia inicial: "+energy);
		System.out.println("Inicio del juego:"+"\n");
		System.out.println("Te despiertas en el suelo, un dolor de cabeza que no te deja pensar con claridad sacude tu mente."+"\n"
				+"No recuerdas nada, por mucho que lo intentes no recuerdas ni tu nombre ni donde estas."+"\n"
				+"Poco a poco el dolor disminuye, aun mareado observas a tu alrededor."+"\n"
				+"Te encuentras en una sala con tamaño considerablemente grande, justo a tu lado hay una mesa enorme con montones de frascos de cristal y botes,"+"\n"
				+"casi parece una mesa de química o alquimia,"
				+"todos ellos contienen extraños liquidos y estan etiquetados con simbolos que no comprendes."+"\n"
				+"Justo en el borde de la mesa a tu lado hay un pequeño tubo de ensayo vacio pero aun con algunas gotas"+"\n"
				+"¿Te bebiste esto? ¿Es este liquido extraño el responsable de tu perdida de memoria?"+"\n"
				+"¿Si es asi por que lo bebiste? ¿O tal vez fue un error?"+"\n"
				+"Con mas preguntas que respuestas decides observar a tu alrededor"+"\n"
				+"Cada lado de la sala donde te encuentras parece conectar con otras habitaciones"+"\n"
				+"La primera puerta que ves esta completamente abierta y a juzgar por las sartenes y cacerolas que ves colgando en el interior deduces que es una cocina"+"\n"
				+"Lo segundo que ves es una puerta de madera cerrada con lo que parece ser un candado"+"\n"
				+"Lo siguiente es el marco de una puerta, pero sin la puerta propiamente dicha, al otro lado puedes algunas estanterias llenas de libros"+"\n"
				+"Y finalmente al otro lado de la mesa ves una puerta de metal bastante robusta que no tiene pinta de que se pueda abrir facilmente"
				);
		do {//bucle principal/menu principal desde donde vas a todos lados
			System.out.println("Te encuentras en la sala donde despertastes");
			System.out.println("¿Que vas a hacer?");
			System.out.println("C. Cocina");
			if (vidaZombie>0)//Si aun no has entrado en la habitación y derrotado al zombie (algo obligatorio ya que es una pelea a muerte) saldra que es una puerta
				System.out.println("H. Puerta de madera");
			else
				System.out.println("H. Dormitorio");//Pero una vez entrado al menos una vez saldra que es un dormitorio
			System.out.println("B. Biblioteca");
			System.out.println("T. Puerta de metal");
			System.out.println("E. Estado");
			System.out.println("R. Rendirte");
			accion=sc.next().toUpperCase().charAt(0);
			switch (accion){//se contemplan los casos
			case 'C':{
				//cocina
				energy-=2;
				if (cocina==0) {
					cocina=1;
					System.out.println("Nada más entrar puedes confirmar que es una cocina."+"\n"
							+"A simple vista puedes ver una mesa con una tabla de cortar, una cuña de queso encima y un cuchillo."+"\n"
							+"A un lado una chimenea aun encendida que supones es usada para calentar comida entre otras cosas"+"\n"
							+"Al otro lado varias estanterias con cuberteria diversa asi como unas pinzas de metal usadas normalmente en el fuego"+"\n"
							+"Y al fondo lo que parece ser un barril del cual desconoces su contenido"
							);
				}
				else
					System.out.println("Entras en la cocina");
				do {
					System.out.println("Estas en la cocina");
					System.out.println("I. Investigar ");
					if (queso1==0) 
						System.out.println("Q. Coger cuña de queso");
					if (cuchillo1==0)
						System.out.println("C. Coger cuchillo de cocina");
					System.out.println("H. Acercarse a la chimenea");
					if (pinzas==0)
						System.out.println("P. Coger pinzas");
					System.out.println("V. Volver");
					System.out.println("E. Estado");
					accion=sc.next().toUpperCase().charAt(0);
					switch (accion) {
					case 'I':{ //se contemplan los casos (diria que es bastante intuitivo cada cosa)
						if (hojaReceta==0) {
							System.out.println("Al acercarte al fondo ves sobre la mesa una hoja arrancada de lo que parece ser una receta, decides cogerla por si acaso");
							hojaReceta=1;
							System.out.println("Tambien observas que el enorme barril contiene agua, al menos no te moriras de sed");
							agua=1;//confirmación de que se ha visto
							energy-=5;
						}
						else
							System.out.println("Nada nuevo, solo el barril con agua");
						break;
					}
					case 'Q':{//objeto
						if (queso==0) {
							System.out.println("Coges la cuña de queso");
							queso=1;
							queso1=1;
						}
						else
							System.out.println("Opción no valida, introduzca una opción correcta.");
						break;
					}
					case 'P':{//objeto
						if (pinzas==0) {
							System.out.println("Coger las pinzas");
							pinzas=1;
						}
						else
							System.out.println("Opción no valida, introduzca una opción correcta.");
						break;
					}
					case 'C':{//objeto
						System.out.println("Coges el cuchillo");
						cuchillo=1;
						cuchillo1=1;
						break;
					}
					case 'V':{//opcion para volver a la habitación anterior (tambien consume energia volver)
						System.out.println("Vuelves a la habitación anterior...");
						energy-=2;
						break;
					}
					case 'H':{//la chimenea es especial y depende del estado de la llave que al igual que el cuchillo tiene varios 0->esta en la chimenea, 1-> en el inventario 2->en el suelo aun caliente
						switch (llave1) {
						case 0:{
							do {//caso de estar en la chimenea tendremos que sacarla
								System.out.println("Al acercarte a la chimenea puedes ver lo que parece ser una llave dentro, esta al rojo vivo");
								System.out.println("¿Que vas a hacer?");
								System.out.println("I. Intetar cogerla con las manos rapidamente");
								if (cuchillo==1)
									System.out.println("C. Intentar sacarla con el cuchillo");
								if (pinzas==1)
									System.out.println("P. Usar las pinzas ");
								System.out.println("B. Buscar algo con lo que poder sacarla");
								accion=sc.next().toUpperCase().charAt(0);
								switch (accion) {
								case 'I':{
									System.out.println("Consigues sacar la llave de la chimenea y tirarla al suelo pero sufres mucho daño debido a las quemaduras... (-20 vida)");
									vida-=20;
									energy-=15;
									System.out.println("¿¿¡A quien se le ocurre meter la mano en una chimenea para coger una llave al rojo vivo!??");
									llave1=2;
									break;
								}
								case 'C':{
									if (cuchillo==1) {
										System.out.println("Con esfuerzo consigues lanzar la llave fuera de la chimenea pero al mismo tiempo te lanzas brasas encima (-10 vida)");
										vida-=10;
										energy-=10;
										System.out.println("El cuchillo se agrieta debido al calor, no crees que sea bueno forzarlo mucho mas");
										cuchillo=2;
										llave1=2;
									}
									else
										System.out.println("Opción no valida, introduzca una opción correcta.");
									break;
								}
								case 'P':{
									if (pinzas==1) {//para poder enfriarla debemos llevarla al agua con las pinzas (como es logico debemos tener las pinzas y saber que hay agua)
										do{
											System.out.println("La coges con las pinzas pero aun sigue caliente ¿ahora qué?");
											energy-=5;
											if (agua==1)
												System.out.println("A. Meterla en el barril de agua");
											System.out.println("S. Soltar en el suelo hasta que se te ocurra una forma de poder enfriarla");
											accion=sc.next().toUpperCase().charAt(0);
											switch (accion) {
											case 'A' :{/*esta opción solo esta disponible si has visto el agua, sino te veras obligado a soltarla hasta investigar la cocina
												repito el codigo mas abajo para el caso en que se saca sin ayuda de las pinzas
												(¿repeticion de codigo? seria perfecto usar un subprograma pero... no queria poner mas,
												suficientes he pueseto teniendo en cuenta que no habia que meter ni uno) */
												if (agua==1) {
													System.out.println("La metes en el agua, durante un instante sale un mucho vapor pero luego para,"+"\n"
															+"parece que se ha enfriado rapidamente y ya puedes cogerla con la mano."+"\n"
															+"Decides guardarla por si la necesitas mas adelante.");
													energy-=5;
													llave=1;
													llave1=1;
												}
												else
													System.out.println("Opción no valida, introduzca una opción correcta.");
												break;
											}
											case 'S':{
												System.out.println("La sueltas al lado de la chimenea");
												llave1=2;
												break;
											}
											default:{
												System.out.println("Opción no valida, introduzca una opción correcta.");
												break;
											}
											}
										} while (llave1==0);
									}
									else
										System.out.println("Opción no valida, introduzca una opción correcta.");
									break;
								}
								case 'B':{
									System.out.println("Te alejas de la chimenea...");
									break;
								}
								default:{
									System.out.println("Opción no valida, introduzca una opción correcta.");
									break;
								}
								}
							} while (!(accion=='B') && llave1==0 && energy>0 && vida>0);
							break;
						}
						case 1:{//si ya esta la llave en el inventario no hay nada mas
							System.out.println("Nada extraño, solo una chimenea para calentar");
							break;
						}
						case 2: {//caso de que quede fuera, aqui se repite bastante como dije arriba
							do {
								System.out.println("La llave esta en el suelo, pero aun sigue al rojo vivo por lo que no puedes cogerla con las manos desnudas");
								if (pinzas==1)
									System.out.println("P. Usar las pinzas para cogerla");
								System.out.println("B. Buscar alguna solución");
								accion=sc.next().toUpperCase().charAt(0);
								switch (accion) {
								case 'P':{
									if (pinzas==1) {
										do{
											System.out.println("La coges con las pinzas ¿ahora qué?");
											energy-=5;
											if (agua==1)
												System.out.println("A. Meterla en el barril de agua");
											System.out.println("S. Soltar en el suelo hasta que se te ocurra una forma de poder enfriarla");
											accion=sc.next().toUpperCase().charAt(0);
											switch (accion) {
											case 'A' :{
												if (agua==1) {
													System.out.println("La metes en el agua, durante un instante sale mucho vapor pero luego para,"+"\n"
															+"parece que se ha enfriado rapidamente y ya puedes cogerla con la mano."+"\n"
															+"Decides guardarla por si la necesitas mas adelante.");
													energy-=5;
													llave=1;
													llave1=1;
												}
												else
													System.out.println("Opción no valida, introduzca una opción correcta.");
												break;
											}
											case 'S':{
												System.out.println("La sueltas al lado de la chimenea");
												llave1=2;
												break;
											}
											default:{
												System.out.println("Opción no valida, introduzca una opción correcta.");
												break;
											}
											}
										} while (accion!='S' && llave1==2 && energy>0 && vida>0);
									}
									else
										System.out.println("Opción no valida, introduzca una opción correcta.");
									break;
								}
								case 'B':{
									System.out.println("Te alejas de la chimenea...");
									break;
								}
								default:{
									System.out.println("Opción no valida, introduzca una opción correcta.");
									break;
								}
								}
							} while (!(accion=='B') && llave1==2 && energy>0 && vida>0);
							break;
						}
						}
						break;
					}
					case 'E':{//estado
						estado();
						break;
					}
					default:{//comprobante y mensaje de error para cualquier caso que on esta en la lista (los casos que no estan disponibles temporalmente se comprueban dentro del propio caso)
						System.out.println("Opción no valida, introduzca una opción correcta.");
					}
					}
				} while (!(accion=='V') && energy>0 && vida>0);
				break;
			}
			case 'H':{
				//Habitación
				if (candado==0) {//primero comprueba si el candado a sido abierto
					do {
						System.out.println("La puerta tiene un candado ¿Qué quieres hacer?");
						if (cuchillo==1)
							System.out.println("C. Intentar forzar el candado con el cuchillo");
						if (cuchillo==2)
							System.out.println("C. Volver a intentar forzar el candado con el cuchillo");
						if (llave==1)
							System.out.println("U. Usar la llave para abrirlo");
						if (llaveAntigua==1)
							System.out.println("A. Intentar usar la llave antigua");
						System.out.println("V. Volver");
						accion=sc.next().toUpperCase().charAt(0);
						switch (accion) {
						case 'A':{
							if (llaveAntigua==1) 
								System.out.println("Salta a simple vista que no puede usar esta llave en este candado");
							else
								System.out.println("Opción no valida, introduzca una opción correcta.");
							break;
						}
						case 'C':{
							switch (cuchillo) {
							case 0:{
								System.out.println("Opción no valida, introduzca una opción correcta.");
							}
							case 1: {
								System.out.println("El cuchillo se agrieta y decides parar");
								energy-=5;
								cuchillo=2;
								System.out.println("Por un segundo te parece escuchar algo del otro lado, pero probablemente sea solo tu imaginación");
								break;
							}
							case 2: {
								System.out.println("El cuchillo de rompe, la punta salta hacia ti y se clava en tu pecho, por suerte no es muy grave (-5 vida)");
								System.out.println("Y lo que es peor el candado sigue en perfectas condiciones");
								energy-=5;
								vida-=5;
								cuchillo=0;
								System.out.println("Escuchas un ruido extraño desde el otro lado de la puerta, como una especie de gruñido");
								break;
							}
							}
							break;
						}
						case 'U':{
							System.out.println("El candado se abre, ahora puedes entrar en la habitación");
							candado=1;
							llave=0; //Si, el candado se queda donde esta y la llave desaparece del inventario por arte de magia. #CosasDeVideojuegos
							break;
						}
						case 'V':{
							System.out.println("Te alejas de la puerta");
							break;
						}
						default:{
							System.out.println("Opción no valida, introduzca una opción correcta.");
							break;
						}
						}
					} while (accion!='V' && candado==0);
				}
				else {//si el candado no esta cerrado (es decir esta abierto) podras entrar
					if (vidaZombie>0) { //la primera vez que entras el zombie esta con vida y tendras que vencerlo, la segunda vez ya no lo esta asique sirve de comprobante para mostar la pelea o la habitacion
						System.out.println("Lo primero que ves al entrar es la silueta de una persona de espaldas."+"\n"
								+"\"¡Por fin!\" Piensas, pensabas que estabas solo pero resulta que no lo estabas."+"\n"
								+"Justo cuando estas a punto de avanzar y entablar conversación la persona se gira."+"\n"
								+"Por un segundo te quedas sin hacer ni pensar nada, casi como si te hubiesen paralizado."+"\n"
								+"La persona que tienes delante, o lo que queda de ella, tiene la piel palida, se mueve de forma extraña,"+"\n"
								+"tiene los ojos blancos como si de un pez muerto se tratase, claramente ya no esta viva o al menos no en su sano juicio."+"\n"
								+"Antes de que puedas reaccionar y cerrar la puerta el...¿zombie? se dirige hacia ti con bastante velocidad como si quisiera comerte."
								);
						//mensaje fuera del bucle que te pone en situacion
						do {
							recoil=0;//pongo el valor a cero para que no siga haciendo daño en caso de elegir otro arma
							System.out.println("¿Qué haces?");
							System.out.println("A. Atacarlo con las manos desnudas");
							if (cuchillo==1 || cuchillo==2)
								System.out.println("C. Atacarlo con el cuchillo");
							if (pinzas==1)
								System.out.println("P. Atacarlo con las pinzas");
							if (queso==1) {
								System.out.println("L. Lanzar la cuña de queso");
								System.out.println("Q. Consumir cuña de queso");
							}
							accion=sc.next().toUpperCase().charAt(0);
							switch (accion) {
							case 'A':{
								System.out.println("Lo atacas con las manos desnudas");
								dañoMaxArma=5;
								recoil=1;//el recoil esta porque golpear con las manos desnudas y con fuerza duele
								break;
							}
							case 'C':{
								switch (cuchillo) {
								case 0:{
									System.out.println("Opción no valida, debido a tu inseguridad y lentitud en la toma de decisiones el zombie se acerca y te ataca");
									break;
								}
								case 1:{
									System.out.println("Usas el cuchillo");
									dañoMaxArma=15;
									break;
								}
								case 2: {
									System.out.println("Usas el cuchillo");
									dañoMaxArma=15;
									break;
								}
								}
								break;
							}
							case 'P':{
								if (pinzas==1) {
									System.out.println("Usas las pinzas como arma");
									dañoMaxArma=10;
								}
								else
									System.out.println("Opción no valida, debido a tu inseguridad y lentitud en la toma de decisiones el zombie se acerca y te ataca");
								break;
							}
							case 'L':{
								if (queso==1) {
									do {
										System.out.println("¿Estas seguro de querer lanzar el unico alimento que has podido encontrar hasta ahora?");
										System.out.println("S. Si");
										System.out.println("N. No");
										accion=sc.next().toUpperCase().charAt(0);
										dañoMaxArma=0;
										switch (accion) {
										case 'S':{
											System.out.println("Lanzas la cuña de queso al zombie sin efecto alguno. ¿Pensabas que iba a servir de algo lanzar el queso?");
											System.out.println("Pierdes el queso");
											queso=0;
											break;
										}
										case 'N':{
											System.out.println("Debido a tu inseguridad y lentitud en la toma de decisiones el zombie se acerca y te ataca");
											break;
										}
										default:{
											System.out.println("Opción no valida, introduzca una opción correcta.");
											break;
										}
										}
									} while (accion!='S' && accion!='N');
								}
								else
									System.out.println("Opción no valida, puesto que no sabes que hacer con tu vida el zombie te ataca");
								break;
							}
							case 'Q':{
								if (queso==1) {
									System.out.println("No es el mejor momento para tomarte un tentempié pero ¿quien soy yo para negarte los placeres de la vida?");
									System.out.println("Recuperas algo de energia (+50)");
									energy+=50;
									queso=0;
									dañoMaxArma=0;
								}
								else
									System.out.println("Opción no valida, puesto que no sabes que hacer con tu vida el zombie te ataca");
								break;
							}
							default:{
								System.out.println("Opción no valida, puesto que no sabes que hacer con tu vida el zombie te ataca");
							}
							}
							pelea(dañoMaxArma);//envio el daño del arma seleccionada al subprograma
							if (dañoZom>0 && golpeZom>5)//solo quiero que el mensaje aparezca si el zombie no ha fallado el golpe y el daño es mayor a cero
								System.out.println("El zombie te hace "+dañoZom+" punto/s de daño");
							else
								System.out.println("El zombie falla el ataque");
							System.out.println("Vida restante: "+vida);
							System.out.println("Energia restante: "+energy);
							if (dañoJug>0 && golpeJug>5) {//lo mismo que con el daño del zombie
								System.out.println("Tu haces "+dañoJug+" punto/s de daño");
								if (recoil!=0) {//ademas recibo daño por recoil en caso de que se cumpla
									System.out.println("Debido a que atacas con las manos desnudas recibes "+recoil+" punto/s de daño");
									vida-=recoil;
									System.out.println("Vida restante: "+vida);
								}
								if (cuchillo==2) {//si el cuchillo esta agrietado e impacta se rompera
									System.out.println("El cuchillo se rompe");
									cuchillo=0;
								}
							}//no quise mostar la vida maxima del zombie para que fuese mas realista (tu vida se podria decir que es como te encuentras y lo puedes deducir ¿pero la vida del zombie? :S)
							else
								System.out.println("No has hecho daño");
							if (vidaZombie<=0) {//cuando el zombie es derrotado aparece este mensaje
								System.out.println("Despues de mucho esfuerzo por fin das el golpe definitivo y el zombie cae ante tus pies");
								System.out.println("Ahora que por fin ha acabado la pelea te das cuenta que la habitación es un dormitorio");
								System.out.println("Despues de la pelea vuelves a la sala principal para decidir que hacer a continuación");
							}
						} while (vidaZombie>0 && vida>0 && energy>0);//ademas como ya no tiene vida sales del bucle automaticamente (lo mismo si te quedas sin vida o energia
					}
					else {//por fin despues de todo ese trabajo puedes entrar sin problemas en la habitacion y seguir haciendo cosas
						energy-=2;
						if (dormitorio==0) {
							dormitorio=1;
							System.out.println("Entras nuevamente al dormitorio, es bastante pequeño y el cadaver sigue en el suelo."+"\n"
									+"A un lado puedes ver un armario bastante grande, probablemente lleno de ropa,"+"\n"
									+"al otro lado puedes observar una cama de matrimonio y sientes angustia solo con mirarla."+"\n"
									+"Al otro lado hay una puerta, esta vez es mucho más antigua que las vistas hasta ahora y te preguntas que tipo de llave podrá abrirla"
									);
						}
						else
							System.out.println("Entras al dormitorio");
						do {
							System.out.println("Estas en el dormitorio");
							System.out.println("O. Observar el cadaver");
							System.out.println("A. Abrir armario");
							System.out.println("P. Puerta antigua");
							System.out.println("E. Estado");
							System.out.println("V. Volver");
							accion=sc.next().toUpperCase().charAt(0);
							switch (accion) {
							case 'O':{//si has visto los mensajes y tienes dudas si, la mujer era tu esposa
								if (loreZombie==0) {
									do {
										System.out.println("Ahora que esta inmovil en el suelo tienes tiempo para observar que el cuerpo es de una mujer joven sobre los 30,"+"\n"
												+"sus ropas no son muy distintas de las que llevas y a juzgar por el color de la piel y que no produce ningun olor piensas"+"\n"
												+"que no debe haber muerto (o convertido en un cadaver andante) hace mucho puesto que si no estaria en descomposición"
												);
										System.out.println("O. Oberservar mas detenidamente");
										System.out.println("D. Dejar el cadaver donde esta");
										accion=sc.next().toUpperCase().charAt(0);
										switch (accion) {
										case 'O':{
											System.out.println("Observas el rostro de la mujer con angustia, como si la conocieras de algo pero no consigues recordar nada."+"\n"
													+"Despues miras su mano, te das cuenta que tiene un anillo en el dedo anular derecho."+"\n"
													+"Rapidamente sin pensar como de forma instintiva miras tu mano derecha..."+"\n"
													+"Tal vez por las prisas, tal vez por la perdida de memoria, tal vez porque tu cuerpo ya estaba acostumbrado pero..."+"\n"
													+"no te habias dado cuenta hasta ahora de que tambien tienes un anillo identico en el dedo anular derecho."+"\n"
													+"Vuelves a mirar el cuerpo sin vida de la mujer, mientras te llevas la mano al pecho"+"\n"
													+"Sientes dolor en el pecho, no saber por que, sigues sin recordar nada y aun asi..."+"\n"
													+"...las lagrimas no dejan de salir..."+"\n"
													+"\n" 
													+"Te toma unos minutos recomponerte, pero finalmente decides levantarte y pensar que hacer a continuación."
													);// 10/10 la historia, si no me llevo un oscar con esto no se que tengo que hacer 
											loreZombie=1;
											break;
										}
										case 'D':{
											System.out.println("Mejor dejarlo donde está");
											break;
										}
										default:{
											System.out.println("Opción no valida, introduzca una opción correcta.");
											break;
										}
										}
									} while (accion!='D' && loreZombie==0);
								}
								else
									System.out.println("El cadaver sigue en su sitio, inmovil y prefieres no volver a acercarte");//nadie quiere repetir una escena triste
								break;
							}
							case 'P':{
								do {//por fin la puerta de salida, eso si necesitaras una llave
									System.out.println("Una puerta muy antigua con una cerradura peculiar");
									if (llaveAntigua==1)
										System.out.println("U. Usar llave antigua");
									else
										System.out.println("No parece que puedas hacer nada para abrirlo, sera mejor buscar una llave");
									System.out.println("V. Alejarte de la puerta");
									accion=sc.next().toUpperCase().charAt(0);
									switch (accion) {
									case 'U':{
										if (llaveAntigua==1) {
											System.out.println("La puerta se abre y ves un pasillo oscuro y luz al final de este. Parece que da al exterior (o eso quieres pensar)");
											do { //una vez abierta la puerta tienes la opcion de irte y terminar el juego o volver (cosa inutil teniendo en cuenta la duraccion o cantidad de cosas que hacer pero quise ponerlo
												System.out.println("¿Quieres marcharte?");
												System.out.println("S. Si");
												System.out.println("N. No");
												accion=sc.next().toUpperCase().charAt(0);
												switch (accion) {
												case 'S':{
													end=1;
													break;
												}
												case 'N':{
													System.out.println("Decides cerrar la puerta de nuevo y quedarte un poco más");
													break;
												}
												default:{
													System.out.println("Opción no valida, introduzca una opción correcta.");
													break;
												}
												}
											} while (accion!='N' && accion!='S');//tanto si dices que si como si no se sale de este bucle pero si has dicho que si la variable end hara que termine el programa
										}
										else
											System.out.println("Opción no valida, introduzca una opción correcta.");
										break;
									}
									case 'V':{
										System.out.println("Te alejas de la puerta");
										break;
									}
									default:{
										System.out.println("Opción no valida, introduzca una opción correcta.");
										break;
									}
									}
								} while (accion!='V' && end==0);//ejemplo de como la variable end terminara todos los bucles que encuentre por el camino de ser necesario
								break;
							}
							case 'A':{
								System.out.println("Abres el armario para comprobar su interior."+"\n"
										+"Solo hay, parece estar dividido en dos partes,"+"\n"
										+"como si una mitad fuese es una persona y la otra mitad de otra."
										);
								break;
							}
							case 'E':{
								estado();
								break;
							}
							case 'V':{
								System.out.println("Vuelves a la sala anterior");
								energy-=2;
								break;
							}
							default:{
								System.out.println("Opción no valida, introduzca una opción correcta.");
								break;
							}
							}
						} while (!(accion=='V') && energy>0 && vida>0 && end==0);
					}
				}
				break;
			}
			case 'B':{
				//Biblioteca
				energy-=2;
				do {//lo mismo que las otras opciones, a repetir hasta que el jugador diga la contrario
					System.out.println("Estas en la biblioteca");
					if (hojaReceta==1 && llaveAntigua1==0)
						System.out.println("B. Buscar libro al que pertenece la receta");
					System.out.println("O. Observar la biblioteca");
					System.out.println("E. Estado");
					System.out.println("V. Volver");
					accion=sc.next().toUpperCase().charAt(0);
					switch (accion) {
					case 'B':{
						if (hojaReceta==1 && llaveAntigua1==0) {
							System.out.println("Despues de buscar bastante por fin encuentras el libro al que pertenece,"+"\n"
									+"lo mas extraño es que pertenece a un libro titulado: \"Alquimia y Pocimas: Nivel experto\"."+"\n"
									+"Sin pensar mucho en ello observas el papel que habia entre las páginas, justo donde deberia estar la hoja arrancada,"+"\n"
									+"\"E18\""
									+"parece una combinación sin sentido pero mientras buscabas en varios libros por toda la biblioteca"+"\n"
									+"pudiste observar que todas las estanterias corresponden a una letra y cada libro esta númerado."+"\n"
									+"Vas en busca del libro en cuestión y lo coges, a simple vista parece un libro normal pero tiene un peso extraño,"+"\n"
									+"cuando lo abres puedes ver en el interior una llave bastante grande y muy antigua, pocas tienen que quedar como esta y menos aun usarse"
									);
							llaveAntigua=1;
							llaveAntigua1=1;
							energy-=5;
						}
						else
							System.out.println("Opción no valida, introduzca una opción correcta.");
						break;
					}
					case 'O':{
						System.out.println("La cantidad de libros es enorme y el solo pensar cuanto se tardaria en leer todo esto hace que vuelva el dolor de cabeza."+"\n"
								+"En el centro de la habitación y a una distancia prudente de cualquier estanteria hay un mesa redonda con dos sillas situadas una fentre a la otra,"+"\n"
								+"dos pilas de libros al lado de cada silla y en el centro un juego de te con solo dos tazas que parece ser usado con frecuencia."
								);
						break;
					}
					case 'E':{
						estado();
						break;
					}
					case 'V':{
						System.out.println("Vuelves a la habitación anterior");
						energy-=2;
						break;
					}
					default:{
						System.out.println("Opción no valida, introduzca una opción correcta.");
						break;
					}
					}
				} while (!(accion=='V') && energy>0 && vida>0);
				break;
			}
			case 'T':{
				/*puerta trasera/final oculto/easter egg/referencia
				A no ser que seas fan de cierta serie este final solo esta como un bonus sin mucha importancia
				en la parte del final que le corresponde hay mas información sobre el codigo
				 */
				do {
					System.out.println("Además del pomo para tirar la puerta tiene un enorme candado que solo se abre introducciendo una combinación de seis digitos, como si fuese una FECHA."+"\n"
							+"Eso y un grabado en el metal que dice: \"Don't forget\""+"\n"
							+"¿Pero cual podría ser?"+"\n"
							+"No se te ocurre como relacionar eso con una puerta COMPLETAMENTE DE METAL y en una sala de ALQUIMIA"
							);
					System.out.println("C. Probar combinación");
					System.out.println("V. Alejarse");
					accion=sc.next().toUpperCase().charAt(0);
					switch (accion) {
					case 'C':{
						System.out.print("Introducce la combinación: ");
						pruebaComb=sc.next();
						if (!(pruebaComb.equals(combinacion)))
							System.out.println("No se abre");	
						break;
					}
					case 'V':{
						System.out.println("Te alejas de la puerta");
						break;
					}
					default:{
						System.out.println("Opción no valida, introduzca una opción correcta.");
					}
					}

				} while (!(accion=='V') && !(pruebaComb.equals(combinacion)));

				break;
			}
			case 'R':{//caso de querer rendirse
				do {//por si acaso se pregunta al salir
					System.out.println("¿Seguro?");
					System.out.println("S. Si");
					System.out.println("N. No");
					accion=sc.next().toUpperCase().charAt(0);
					switch (accion) {
					case 'S':{
						rendirte=1;
						break;
					}
					case 'N':{
						System.out.println("No es momento de pensar en rendirse, es momento de tomar decisiones.");
						break;
					}
					default:{
						System.out.println("Opción no valida, introduzca una opción correcta.");
					}
					}
				} while (accion!='N' && accion!='S');
				break;
			}
			case 'E':{
				//estado
				estado();
				break;
			}
			default:{
				System.out.println("Opción no valida, introduzca una opción correcta.");
				break;
			}
			}
		} while (energy>0 && vida>0 && !(pruebaComb.equals(combinacion)) && end==0 && rendirte==0);
		//Rendición
		if (rendirte==1) {
			System.out.println("Te has rendido");
			System.out.println("Game Over");
			System.out.println("No creo que fuera tan dificil... :S");
		}
		//Finales malos
		if (energy<0) {
			System.out.println("Exausto te quedas sin energia poco a poco, caes rendido en el suelo y cierras los ojos para tomar un descanso...");
			System.out.println("Un descanso eterno");
			System.out.println("Game Over");
		}
		if (vida<0) {
			System.out.println("Al final caes de un golpe seco propinado por el monstruo al suelo y un charco enorme se forma con tu sangre");
			System.out.println("Game Over");
		}
		if (vida<0 && energy<0) {
			if (vidaZombie>0) {
				System.out.println("Usas toda tu energia, pero de nada sirve. El monstruo era demasiado poderoso, caes rendido viendo como se abalanza sobre ti y te devora.");
				System.out.println("Game Over");
			}
			else {
				System.out.println("Tal vez fue por la adrenalina, tal vez por el calor del momento, pero a pesar de haber conseguido derrotar al monstruo"+"\n"
						+"todo tu cuerpo esta temblando y quedandose sin fuerza, estando ya en el suelo te das cuenta de que hay mucha sangre y es tuya..."
						);
				System.out.println("Game Over");
			}
		}
		//Final oculto
		if (pruebaComb.equals(combinacion)) {
			System.out.println("Consigues salir como un verdadero alquimista de acero"+"\n"//Referencia
					/*
					la fecha 03/10/11 es una referencia al anime FULLMETAL ALCHEMIST (ya tiene unos cuantos años),
					en la serie es una fecha importante para los protagonistas y la tienen grabada en un reloj de bolsillo
					con la frase "Don't forget 3.oct.11" para mas informacion busque en google.
					 */
					 +"Sales al exterior, hay un jardin con y un pequeño huerto a ambos lados del camino,"+"\n"
					 +"más adelante puedes ver varios animales de granja pastando, tambien puedes ver multiples fuentes de agua que no paran de brotar desde algun manantial"+"\n"
					 +"Al menos ahora sabes que no moriras de hambre, casi parece perfecto."+"\n"
					 +"Si tuvieses que ponerle alguna pega...bueno, creo que seria ESO sin duda."+"\n"
					 +"A una distancia bastante considerable se levantan unos muros enormes, son tan altos que ni se te pasa por la cabeza el intentar escalarlos."+"\n"
					 +"Pero bueno, al menos podras vivir sin problemas"+"\n"
					 +"\n"
					 +"FIN"
					);
		}
		//Final verdadero
		if (end==1) {
			System.out.println("Despues de un largo pasillo por fin consigues salir al exterior"+"\n"
					+"Pero para tu sorpresa no queda nada, puedes divisar una ciudad no muy lejos pero los edificios estan derruidos,"+"\n"
					+"hay humo por todas partes y no parece que vayas a encontrarte con alguien pronto."+"\n"
					+"Tampoco tenias las expectacivas muy altas, pero al menos esperabas algo de información, alguna forma de conseguir comida... lo normal."+"\n"
					+"Bueno, sigues con vida que eso ya es algo despues de lo que has pasado ahi dentro."+"\n"
					+"Ahora solo queda avanzar hacia delante y sobrevivir como puedas."+"\n"
					+"\n"
					+"The end...?"
					);
		}
		sc.close();
	}
}
/*
La ruta mas rapida seria ir a la cocina e investigar, coger el cuchillo, las pinzas y el queso por si acaso. sacar la llave de la chimenea con las pinzas y meterla en el agua luego vuelves.
Vas a la biblioteca y buscas el libro para obtener la llave antigua.
Vas a la puerta la abres con la llave, luchas contra el zombie con el cuchillo y una vez derrotado abres las puerta final y sales.

Tecnicamente la mas corta seria usar la puerta trasera, pero tienes que saber la contraseña y esta no se dice en ningun sitio dentro del juego asique...
 */