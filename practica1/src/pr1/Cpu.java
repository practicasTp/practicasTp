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
	
	public boolean execute (Instruction instr){
		TipoInstruction operation;
		int operando;
		boolean execute = true;
		
		operando = instr.getOperando();
		operation = instr.getTipoInstruccion();
		if (operation.equals(TipoInstruction.PUSH) || operation.equals(TipoInstruction.POP) || operation.equals(TipoInstruction.FLIP) || operation.equals(TipoInstruction.DUP) || operation.equals(TipoInstruction.OUT)){
			if (operation.equals(TipoInstruction.PUSH)) {
				this.pila.push(operando);
			} else if (operation.equals(TipoInstruction.POP)) execute = this.pila.pop();
			else if (operation.equals(TipoInstruction.DUP)) execute = this.pila.dup();
			else if (operation.equals(TipoInstruction.OUT)) {
				System.out.println(this.pila.out());
			} else execute = this.pila.flip();
		}else if (operation.equals(TipoInstruction.STORE) || operation.equals(TipoInstruction.LOAD)) {
			if (operation.equals(TipoInstruction.STORE)) {
				if (this.pila.getCima() > -1) {
					this.memoria.store(operando, this.pila.getDato(this.pila.getCima()));
				} else execute = false;
			}
			else {
				if (!this.memoria.isEmpty()) {
					this.pila.push(this.memoria.getDato(operando));
				}
				else execute = false;
			}
		}else if (operation.equals(TipoInstruction.HALT)) {
			System.out.println("Se finaliza la ejecución.");
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
				
				if(resultado != null){
					int result = resultado.intValue();
					this.pila.push(result);
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
		
		contenidoCpu = this.pila.toString();
		contenidoCpu += this.memoria.toString();
		
		return contenidoCpu;
	}
}
