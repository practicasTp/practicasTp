package controllers;

import java.io.FileNotFoundException;
import java.io.FileReader;

import mv.cpu.Cpu;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.exceptions.IncorrectParsingInstruction;
import mv.exceptions.MvError;
import mv.program.ProgramMv;
import mv.reading.FromInputStreamIn;
import mv.reading.InputMethod;
import mv.reading.NullIn;
import mv.writing.OutputMethod;

public class GUIControler extends Controller {
	
	public GUIControler(Cpu cpu) {
		super(cpu);
	}
	
	JDialog dialogo = null;	
	
	/**
	 * Aviso de que el programa se ha cargado
	 */
	public void start() {
		//avisamos al programa de que el programa ya ha sido cargado
		cpu.programLoaded();
	}
	
	/**
	 * Pausa la ejecución del programa
	 */
	public void stop(){
		cpu.pause();
	}
	
	/**
	 * Carga un nuevo programa en la cpu
	 */
	public void loadNewProgram(ProgramMv program){
		cpu.loadProgram(program);
		cpu.resetCpu(false);
	}
	
	/**
	 * Carga una nueva entrada en la cpu
	 */
	public void loadNewInput(String path){
		InputMethod input;
		//intentamos abrir el archivo de entrada que nos pasan
    	try{
    		//con esto determino si el archivo existe o no
    		new FileReader(path);
    		//si existe, creará un nuevo método de entrada
    		input = new FromInputStreamIn(path);
    	}catch (FileNotFoundException e) {
			input = new NullIn();
		}
    	
    	try {
    		//establezco la nueva entrada
			this.cpu.setInStream(input);
			cpu.resetCpu(true);
		} catch (MvError e) {}
	}
	
	/**
	 * Resetea la cpu
	 */
	public void reset(){
		cpu.resetCpu(false);
	}
	
	/**
	 * Cierra el programa finalizando todos los archivos que puedan estar abiertos.
	 */
	public void quit() { 
		 String ObjButtons[] = {"Aceptar","Cancelar"};
		 int PromptResult = JOptionPane.showOptionDialog(null,"¿Estás seguro de que deseas salir?","Cerrar máquina virtual",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,ObjButtons,ObjButtons[1]);
		 if(PromptResult==JOptionPane.YES_OPTION) {
			 InputMethod in = this.cpu.getInStream();
			 in.close();
			 OutputMethod out = this.cpu.getOutStream();
			 out.close();
			 System.exit(0);
		 }
	}
	
	/**
	 * Devuelve el valor del PC de la cpu.
	 * @return int
	 */
	public int getPC() {
		return cpu.getPC();
	}
	
	/**
	 * Rellena el InStream de la cpu caturando las excepciones que se puedan
	 * producir.
	 * @param in
	 */
	public void setInStream(InputMethod in) { 
		try {
			this.cpu.setInStream(in);
		} catch (MvError e) {}
	}
	
	/**
	 * Rellena el OutStream de la cpu capturando las excepciones que se puedan
	 * producir.
	 * @param out
	 */
	public void setOutStream(OutputMethod out) { 
		try {
			this.cpu.setOutStream(out);
		} catch (MvError e) {}
	}
	
	/**
	 * Devuelve el InStream de la cpu
	 * @return InputMethod
	 */
	public InputMethod getInStream() {
		return this.cpu.getInStream();
	}
	
	/**
	 * Devuelve el OutStream de la cpu.
	 * @return OutputStream
	 */
	public OutputMethod getOutStream() { 
		return this.cpu.getOutStream();
	}
	
	/**
	 * Devuelve el programa de la cpu.
	 * @return ProgramMv
	 */
	public ProgramMv getProgram() { 
		return this.cpu.getProgram();
	}

	/**
	 * Devuelve la pila de la cpu.
	 * @return OperandStack<Integer>
	 */
	public OperandStack<Integer> getOperandStack() { 
		return this.cpu.getOperandStack();
	}
	
	/**
	 * Devuelve la memoria de la cpu
	 * @return Memory<Integer>
	 */
	public Memory<Integer> getMemory() { 
		return this.cpu.getMemory();
	}
}