package views;

import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.instructions.Instruction;
import mv.program.ProgramMv;
import observers.CPUObserver;
import observers.Observable;

public class BatchView implements CPUObserver{

	/* Esta variable es porque en el caso en el que se salte o intente saltar
	 * en una instrucción a una posición incorrecta, se llama a exit a la hora
	 * de saltar en una comprobación y en otra al intentar obtener la instrucción
	 * Sólo pasa en esta vista, por lo que necesito esta variable.  
	 */
	boolean closed = false;
	
	public BatchView(Observable<CPUObserver> cpu) {
		cpu.addObserver(this);
	}
	
	/**
	 * Método que muestra el inicio de la instrucción actual
	 */
	public void onStartInstrExecution(Instruction instr) {}

	/**
	 * Método que muestra el fin de la instrucción actual
	 */
	public void onEndInstrExecution(int pc, Memory<Integer> memory, OperandStack<Integer> stack, ProgramMv program) {}

	/**
	 * Método que avisa del inicio de la instrucción run
	 */
	public void onStartRun() {}

	/**
	 * Método que avisa del fin de la instrucción run
	 */
	public void onEndRun() {}

	/**
	 * Método que muestra un error
	 */
	public void onError(String msg) {
		System.err.println(msg);
	}

	/**
	 * Método que muestra que la ejecución ha terminado
	 */
	public void onHalt() {
		//explicación en la definición de la variable
		if(!closed){
			closed = true;
			System.out.println("\n\nLa ejecución de la máquina se ha parado.\n");
		}
	}

	public void onReset(ProgramMv program) {}
	
	public void onNewIn() {}

}
