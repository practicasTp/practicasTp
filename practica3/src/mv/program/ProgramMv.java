package mv.program;
import mv.instructions.Instruction;

public class ProgramMv {
	static private final int MAX_PROGRAM = 2;
	private Instruction[] userProgram;
	private int numberInstructions;
	
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
}
