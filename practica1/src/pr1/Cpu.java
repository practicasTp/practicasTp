package pr1;

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
	
	public boolean finished(){
		return this.fin;
	}
	
	//Operaciones de la pila
	
	private boolean push (int value) {
		this.pila.stackData(value);
		return true;
	}
	
	private boolean pop () {
		if (!this.pila.unstackData()) { 
			System.out.println("Error: La pila est‡ vac’a.");
			return false;
		} else return true;
	}
	
	private boolean dup () {
		if (!this.pila.isEmpty()) {
			int value = this.pila.getDato(this.pila.getCima());
			this.push(value);
			return true;
		} else {
			System.out.println("Error: La pila est‡ vac’a.");
			return false;
		}
	}
	
	private Character out () {
		Character resultado;
		
		//si la pila est‡ vac’a devuelvo null
		if (this.pila.isEmpty()){
			System.out.println("Error: La pila est‡ vac’a.");
			resultado = null;
		//si la pila no lo est‡, devuelvo lo almacenado en la cima de la pila
		}else{
			int intValue = this.pila.getDato(this.pila.getCima()).intValue();
			resultado = (char)intValue;
		}
		
		return resultado;
	}
	
	private boolean flip () {
		if (this.pila.getCima() >= 1) {
			int cima = this.pila.getDato(this.pila.getCima());
			this.pop();
			int subcima = this.pila.getDato(this.pila.getCima());
			this.pop();
			this.push(cima);
			this.push(subcima);
			return true;
		} else {
			System.out.println("Error: La pila no tiene suficientes valores.");
			return false;
		}
	}
	
	//Operaciones de la memoria.
	
	private boolean store (int pos, int dato) {
		if (!this.pila.isEmpty()) {
			this.memoria.storeData(pos, dato);
			this.pop();
			return true;
		} else {
			System.out.println("Error: La pila est‡ vac’a.");
			return false;
		}
	}
	
	public boolean execute (Instruction instr){
		TipoInstruction operation;
		int operando;
		boolean execute = true;
		
		operando = instr.getOperando();
		operation = instr.getTipoInstruccion();
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
		}else if (operation.equals(TipoInstruction.STORE) || operation.equals(TipoInstruction.LOAD)) {
			if (operation.equals(TipoInstruction.STORE)) execute = this.store(operando, this.pila.getDato(this.pila.getCima()).intValue());
			else {
				if (!this.memoria.isEmpty()) this.push(this.memoria.getDato(operando));
				else execute = false;
			}
		}else if (operation.equals(TipoInstruction.HALT)) {
			System.out.println("Se finaliza la ejecuci—n.");
			this.fin = true;
			execute = true;
		}else if (operation.equals(TipoInstruction.ADD) || operation.equals(TipoInstruction.DIV) || operation.equals(TipoInstruction.MUL) || operation.equals(TipoInstruction.SUB)){
			if (this.pila.getCima() >= 1) {
				
				Integer cima 		= this.pila.getDato (this.pila.getCima());
				Integer subcima 	= this.pila.getDato (this.pila.getCima() - 1);
				Integer resultado 	= null;
				
				if (operation.equals(TipoInstruction.ADD)) {
					resultado = alu.add(subcima,cima);
				} else if (operation.equals(TipoInstruction.SUB)) {
					resultado = alu.sub(subcima,cima);
				} else if (operation.equals(TipoInstruction.DIV)) {
					resultado = alu.div(subcima,cima);
				} else if (operation.equals(TipoInstruction.MUL)) {
					resultado = alu.mul(subcima,cima);
				}
				//Elmino de la pila los valores usados en la operaci—n.
				this.pop();
				this.pop();
				
				if(resultado != null){
					int result = resultado.intValue();
					this.push(result);
					execute = true;
				}else{
					execute = false;
				}
				
			} else execute = false;
		} else execute = false;
		
		return execute;
	}
	
	public boolean isEnd () {
		if (this.fin) return true;
		else return false;
	}
	
	public String toString(){
		String contenidoCpu;
		System.out.println("El estado de la m‡quina tras ejecutar la instrucci—n es:");
		contenidoCpu = this.memoria.toString();
		contenidoCpu += this.pila.toString();
		
		return contenidoCpu;
	}
}
