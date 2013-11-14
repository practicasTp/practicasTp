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
	
	/**
	 * Método que devuelve el operando de la instrucción
	 * @return
	 */
	public int getOperando(){
		return this.operando;
	}
	
	/**
	 * Método que devuelve el tipo de instrucción
	 * @return
	 */
	public TipoInstruction getTipoInstruccion(){
		return this.tipo;
	}
}
