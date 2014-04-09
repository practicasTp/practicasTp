package mv.program;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import mv.exceptions.IncorrectParsingInstruction;
import mv.instructions.Instruction;
import mv.instructions.InstructionParser;

public class ProgramMv {
	static private final int MAX_PROGRAM = 2;
	private Instruction[] userProgram;
	private int numberInstructions;
	static public int contLinea;
	static public String instructionLine;
	
	public ProgramMv(){	
		//inicializo los atributos
		this.userProgram =  inicializarProgram(MAX_PROGRAM);
		numberInstructions = 0;
	}

	/**
	 * Metodo que inicializa el programa a vacío
	 * @param tamanio
	 * @return programa inicializado
	 */
	private Instruction[] inicializarProgram(int tam){
		
		//creo un auxiliar con el tamaño que me indican
		Instruction[] r = new Instruction[tam];
		
		//recorro ese array y lo voy inicializando
		for(int i=0; i < tam; i++){
			r[i] = null;
		}
		
		//devuelvo el array inicializado
		return r;
		
	}
	
	/**
	 * Metodo que almacena una instruccion del programa
	 * @param userInstruction
	 */
	public void push(Instruction userInstruction){
		
		//si la posicion donde deseas guardar es menor que la posicion de mayor tama�o que tengo lo guardo
		if(numberInstructions < this.userProgram.length){
			
			this.userProgram[numberInstructions] = userInstruction;
			this.numberInstructions++;
			
		}else{			
			
			//creo nuevo tamaño con el doble de lo que tengo ahora
			int nuevo_tamanio = this.userProgram.length * 2;			
			
			//inicializo una memoria auxiliar que posteriormente pasará a ser la del sistema
			Instruction[] aux = this.inicializarProgram(nuevo_tamanio);
			
			//redimensiono el numero de tamaño de memoria
			this.userProgram = this.redimensionar(aux);
			
			//almaceno la instrucción
			this.userProgram[numberInstructions] = userInstruction;
			this.numberInstructions++;
		}
		
		
	}
	
	/**
	 * Metodo que redimensiona la memoria a traves de una memoria auxiliar previamente inicializada
	 * @param aux
	 * @return memoria redimensionada
	 */
	private Instruction[] redimensionar(Instruction[] aux){
		
		//recorro todos los registros que tengo y los copio al auxiliar, el cual está previamente inicializado
		for(int i=0;i< this.userProgram.length;i++){
			aux[i] = this.userProgram[i];
		}
	
		return aux;
	}
	
	public Instruction get(int i){
		return this.userProgram[i];
	}
	
	public int getSizeProgram(){
		return this.numberInstructions;
	}
	/**
	 * Metodo que se encarga de determinar si la memoria esta vacia o no
	 * @return true/false
	 */
	private boolean isEmpty () {
		boolean empty = true;
		
		//solo si la posicion 0 está vacía
		if(this.userProgram[0] == null){
			empty = true;
		}else{
			empty = false;
		}
		
		return empty;
	}
	
	/**
	 * Metodo que devuelve el estado de la memoria en formato String
	 */
	public String toString (){
		String contenidoProgram = "";
		if(this.isEmpty()) contenidoProgram = "<vacio>";
		else{
			for(int i = 0; i <= (this.userProgram.length - 1); i++){
				if (this.userProgram[i] != null) contenidoProgram += "["+ i + "]: " + this.userProgram[i].toString() + "\n";
			}
		}
		
		return "El programa introducido es: \n"+contenidoProgram;
	}
	
	/**
	 * Función que se encarga de pedir el programa por consola
	 * @return line
	 * @throws IncorrectParsingInstruction 
	 */
	public static ProgramMv readProgram(String asmRoute) throws IncorrectParsingInstruction{ 
		ProgramMv program = new ProgramMv();
		String[] instruccionCortada;
		boolean stop	  = false;
		ProgramMv.contLinea	  = 1;
		String[] lineaSinPuntoComa;
		
		try{
			Scanner sc= new Scanner(new FileReader(asmRoute)); 
			while(sc.hasNext() || !stop ){ 
				try{ 
					//leo la intrucción del fichero
					ProgramMv.instructionLine = sc.nextLine();
					
					if(ProgramMv.instructionLine.charAt(0)!= ';' && ProgramMv.instructionLine!=""){
						
						lineaSinPuntoComa = ProgramMv.instructionLine.split(";");
						
						//divido la cadena obtenida en el fichero
						instruccionCortada = lineaSinPuntoComa[0].split(" +");
						
						if(!instruccionCortada[0].equalsIgnoreCase("END")){
							//parseo la instruccion
							Instruction instruccion = InstructionParser.parser(instruccionCortada);
							program.push(instruccion);
							/*}catch(IncorrectParsingInstruction e){
								System.err.println(e.getMessage());
								System.err.println("La instrucción fallida se encuentra en la linea "+contLinea+": '"+instructionLine+"'");
								System.exit(1);
							}*/
						}else{
							stop = true;
						}
						
					}
					ProgramMv.contLinea++;
				} catch(InputMismatchException e){ 
					sc.next(); 
				} catch(NoSuchElementException e){
					stop = true;
				} catch(StringIndexOutOfBoundsException e){
					ProgramMv.contLinea++;
				}
			}	
		}catch (FileNotFoundException e) { 
			System.err.println("Uso incorrecto: Fichero ASM no especificado, ruta: '"+asmRoute+"'");
			System.exit(1);
		}
		
		return program;
	}
	
	
	/**
	 * Función que se encarga de pedir el programa por consola
	 * @return line
	 * @throws IncorrectParsingInstruction 
	 */
	public static ProgramMv readProgram(){
		ProgramMv program = new ProgramMv();
		boolean stop = false;
		String[] instruccionCortada;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("> Introduce el programa fuente:");
		//Primera fase:
		
		//Se lee el programa
		do{
			//pido la instrucción por el prompt.
			String instructionLine = sc.nextLine();
			
			//divido la cadena obtenida en el prompt
			instruccionCortada = instructionLine.split(" +");
			
			if(!instruccionCortada[0].equalsIgnoreCase("END")){
				//parseo la instruccion
				Instruction instruccion = null;
				try {
					instruccion = InstructionParser.parser(instruccionCortada);
					program.push(instruccion);
				} catch (IncorrectParsingInstruction e) {
					System.err.println(e.getMessage());
				}
			}else{
				stop = true;
			}
			
		}while(stop==false);
		
		return program;
	}
}
