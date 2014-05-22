package views;

import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.instructions.Instruction;
import mv.program.ProgramMv;
import observers.CPUObserver;
import observers.Observable;

public class InteractiveView implements CPUObserver {

	public InteractiveView(Observable<CPUObserver> cpu) { 
		cpu.addObserver(this);
	}
	
	/**
	 * Método que muestra el inicio de la instrucción actual
	 */
	public void onStartInstrExecution(Instruction instr) {
		System.out.println("Comienza la ejecución de "+instr.toString()+"\n");
	}

	/**
	 * Método que muestra el fin de la instrucción actual
	 */
	public void onEndInstrExecution(int pc, Memory<Integer> memory, OperandStack<Integer> stack, ProgramMv program) {
		String contenidoCpu;
		System.out.println("El estado de la máquina tras ejecutar la instrucción es:");
		contenidoCpu = memory.toString();
		contenidoCpu += stack.toString();
		
		System.out.println(contenidoCpu);
	}

	/**
	 * Método que avisa del inicio de la instrucción run
	 */
	public void onStartRun(){} //NO SÉ QUÉ HACER AQUI, HABLAR CON SAMIR

	/**
	 * Método que avisa del fin de la instrucción run
	 */
	public void onEndRun(){} //NO SÉ QUÉ HACER AQUI, HABLAR CON SAMIR

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
		System.out.println("La ejecución de la máquina se ha parado.\n");
	}

	/**
	 * Método que muestra que la máquina se ha resetado
	 */
	public void onReset(ProgramMv program) {
		System.out.println(program.toString());
	}

	public void onNewIn() {}
	
}