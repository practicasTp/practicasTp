package mv.instructions;
import mv.cpu.Cpu;

abstract public class Instruction {
	private int operando;
	private TipoInstruction tipo;
	
	/**
	 * Ejecuta la instrucción
	 * @param cpu
	 * @return boolean
	 */
	abstract public boolean execute (Cpu cpu);
	
	/**
	 * Recibe un string para identificar la instrucción a ejecutar
	 * @param s
	 * @return Instruction
	 */
	abstract public Instruction parse (String[] s);
	
	public Instruction (TipoInstruction tipo){
		this.operando = 0;
		this.tipo = tipo;
	}
	
	public Instruction(TipoInstruction tipo, int operando){
		this.operando = operando;
		this.tipo 	  = tipo;
	}
	
	/**
	 * Metodo que devuelve el operando de la instruccion
	 * @return operando
	 */
	public int getOperando(){
		return this.operando;
	}
	
	/**
	 * Metodo que devuelve el tipo de instruccion
	 * @return tipo de instruccion
	 */
	public TipoInstruction getTipoInstruccion(){
		return this.tipo;
	}
}
