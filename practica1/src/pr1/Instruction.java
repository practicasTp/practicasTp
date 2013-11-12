package pr1;

public class Instruction {
	private int operando;
	private TipoInstruction tipo;
	
	public Instruction(TipoInstruction tipo){
		this.operando = 0;
		this.tipo = tipo;
	}
	
	public Instruction(TipoInstruction tipo, int operando){
		this.operando = operando;
		this.tipo 	  = tipo;
	}
	
	public int getOperando(){
		return this.operando;
	}
	
	public TipoInstruction getTipoInstruccion(){
		return this.tipo;
	}
}
