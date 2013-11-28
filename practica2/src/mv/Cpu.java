package mv;

public class Cpu {
	private Memory memoria;
	private OperandStack pila;
	private Alu alu;
	private boolean fin;
	
	public Cpu(){
		this.memoria = new Memory ();
		this.pila = new OperandStack ();
		this.fin = false;
		this.alu = new Alu();
	}
	
	/**
	 * Metodo que devuelve si la cpu ha sido finalizada o no
	 * @return true/false
	 */
	public boolean finished(){
		return this.fin;
	}
	
	//Operaciones de la pila
	
	/**
	 * Metodo que almacena en la pila un valor
	 * @param value
	 * @return true/false
	 */
	private boolean push (int value) {
		this.pila.stackData(value);
		return true;
	}
	
	/**
	 * Metodo que elimina de la pila el valor de la cima
	 * @return true/false
	 */
	private boolean pop () {
		//si no se apila hay error
		if (!this.pila.unstackData()) { 
			System.out.println("Error: La pila está vacía.");
			return false;
		} else return true;
	}
	
	/**
	 * Metodo que duplica el valor de la cima
	 * @return tru/false
	 */
	private boolean dup () {
		//si la pila no está vacía
		if (!this.pila.isEmpty()) {
			//obtengo el valor de la cima, la salvo
			int value = this.pila.getDato(this.pila.getCima());
			this.push(value);
			return true;
		//si la pila está vacía aviso
		} else {
			System.out.println("Error: La pila está vacía.");
			return false;
		}
	}
	
	/**
	 * Metodo que devuelve el valor de la cima como caracter
	 * @return caracter resultado
	 */
	private Character out () {
		Character resultado;
		
		//si la pila está vacía devuelvo null
		if (this.pila.isEmpty()){
			System.out.println("Error: La pila está vacía.");
			resultado = null;
		//si la pila no lo est�, devuelvo lo almacenado en la cima de la pila
		}else{
			int intValue = this.pila.getDato(this.pila.getCima()).intValue();
			resultado = (char)intValue;
		}
		
		return resultado;
	}
	
	/**
	 * Metodo que intercambia el valor de la subcima de la pila por el de la cima de la pila
	 * @return true/false
	 */
	private boolean flip () {
		//si la pila tiene más de un operando
		if (this.pila.getCima() >= 1) {
			//obtengo la cima
			int cima = this.pila.getDato(this.pila.getCima());
			//elimino la cima
			this.pop();
			//obtengo la cima (que es la anterior sucima)
			int subcima = this.pila.getDato(this.pila.getCima());
			//elimino la cima
			this.pop();
			//añado la cima (que ahora pasa a ser la subcima)
			this.push(cima);
			//añado la subcima (que ahora pasa a ser la cima)
			this.push(subcima);
			return true;
		//si no lo tiene, aviso de un error
		} else {
			System.out.println("Error: La pila no tiene suficientes valores.");
			return false;
		}
	}
	
	//Operaciones de la memoria.
	
	/**
	 * Metodo que almacena en una posicion de la memoria, un dato.
	 * @param pos
	 * @param dato
	 * @return true/false
	 */
	private boolean store (int pos, int dato) {
		//Si la pila no está vacía
		if (!this.pila.isEmpty()) {
			//almaceno en la posicion deseada, el dato deseado
			this.memoria.storeData(pos, dato);
			//elimino el dato de la pila
			this.pop();
			return true;
		//si está vacía, elimino.
		} else {
			System.out.println("Error: La pila está vacía.");
			return false;
		}
	}
	
	/**
	 * Metodo que ejecuta todas las instrucciones que puede usar la cpu
	 * @param instr
	 * @return true/false
	 */
	public boolean execute (Instruction instr){
		TipoInstruction operation;
		int operando;
		boolean execute = true;
		
		//obtengo el operando y la operacion
		operando = instr.getOperando();
		operation = instr.getTipoInstruccion();

		//operaciones de la pila
		if (operation.equals(TipoInstruction.PUSH) || operation.equals(TipoInstruction.POP) || operation.equals(TipoInstruction.FLIP) || operation.equals(TipoInstruction.DUP) || operation.equals(TipoInstruction.OUT)){
			if (operation.equals(TipoInstruction.PUSH)) execute = this.push(operando);
			else if (operation.equals(TipoInstruction.POP)) execute = this.pop();
			else if (operation.equals(TipoInstruction.DUP)) execute = this.dup();
			else if (operation.equals(TipoInstruction.OUT)) {
				if (this.out() != null) {
					System.out.println(this.out());
					this.pop();
					execute = true;
				} else execute = false;
			}
			else execute = this.flip();
		//operaciones de memoria
		}else if (operation.equals(TipoInstruction.STORE)){
			
			//obtengo el dato
			Integer dato = this.pila.getDato(this.pila.getCima());
			
			//si el dato no es null, lo guardo
			if(dato!=null){
			
				//el operando (posicion) no puede ser negativo
				if(operando >= 0){
					execute = this.store(operando, dato.intValue());
				}else{
					System.out.println("Error: No se pueden obtener guardar datos en posiciones negativas.");
					execute = false;
				}
				
			//si el dato es null aviso de que no hay contenido en la pila
			}else{
				System.out.println("Error: No hay contenidos en la pila.");
				execute = false;
			}
	 		
		}else if(operation.equals(TipoInstruction.LOAD)) {
				
					//el operando (posicion) no puede ser negativo
					if(operando >= 0){
						//intento obtener el dato
						Integer dato = this.memoria.getDato(operando);
						//lo almaceno en la pila
						this.push(dato.intValue());
						//si no lo obtengo, aviso
						
					}else{
						execute = false;
						System.out.println("Error: No se pueden cargar posiciones negativas.");
					}
				
				
		
		//operacion de parada de la ejecución
		}else if (operation.equals(TipoInstruction.HALT)) {
			System.out.println("Se finaliza la ejecución.");
			this.fin = true;
			execute = true;
		//operaciones aritmetico-lógicas
		}else if (operation.equals(TipoInstruction.ADD) || operation.equals(TipoInstruction.DIV) || operation.equals(TipoInstruction.MUL) || operation.equals(TipoInstruction.SUB)){
			//si hay más de dos operandos en la pila
			if (this.pila.getCima() >= 1) {
				
				//obtengo los dos operandos
				Integer cima 		= this.pila.getDato (this.pila.getCima());
				Integer subcima 	= this.pila.getDato (this.pila.getCima() - 1);
				Integer resultado 	= null;
				
				//realizo las operaciones
				if (operation.equals(TipoInstruction.ADD)) {
					resultado = alu.add(subcima,cima);
				} else if (operation.equals(TipoInstruction.SUB)) {
					resultado = alu.sub(subcima,cima);
				} else if (operation.equals(TipoInstruction.DIV)) {
					resultado = alu.div(subcima,cima);
				} else if (operation.equals(TipoInstruction.MUL)) {
					resultado = alu.mul(subcima,cima);
				}
				
				//Elmino de la pila los valores usados en la operación.
				this.pop();
				this.pop();
				
				//si la ejecución ha ido bien
				if(resultado != null){
					
					//almaceno el resultado en la pila
					int result = resultado.intValue();
					this.push(result);
					execute = true;
				}else{
					//si no se ha ejecutado bien, todo se queda como estaba
					execute = false;
				}
			//si no hay 2 operandos, lo aviso	
			} else{
				
				System.out.println("Error: No hay operandos suficientes en la pila.");
				execute = false;
			}
			
		}
		
		return execute;
	}
	
	/**
	 * Metodo que devuelve el estado de la cpu en formato string
	 */
	public String toString(){
		String contenidoCpu;
		System.out.println("El estado de la máquina tras ejecutar la instrucción es:");
		contenidoCpu = this.memoria.toString();
		contenidoCpu += this.pila.toString();
		
		return contenidoCpu;
	}
}