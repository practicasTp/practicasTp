package mv;
import java.util.Scanner;

import mv.cpu.Cpu;
import mv.instructions.Instruction;
import mv.instructions.InstructionParser;
import mv.program.ProgramMv;

public class Main {

	/**
	 * Función que se encarga de pedir la instrucción
	 * @return line
	 */
	public static String promptUserProgram(){
		String line;
		
		Scanner sc = new Scanner(System.in);
		line = sc.nextLine();
		
		return line;
	}
	
	/**
	 * Función que inicia la ejecución
	 * @param args
	 */
	public static void main(String[] args) {
		ProgramMv userProgram = new ProgramMv();
		boolean stop = false;
		String[] instruccionCortada;
		
		System.out.println("Introduce el programa fuente:");
		
		do{
			//pido la instrucción
			String instructionLine = promptUserProgram();
			
			//divido la cadena obtenida en el prompt
			instruccionCortada = instructionLine.split(" +");
			
			if(!instruccionCortada[0].equalsIgnoreCase("END")){
				//parseo la instruccion
				Instruction instruccion = InstructionParser.parser(instruccionCortada);
				
				//si he identificado la instrucción
				if(instruccion !=null){
					userProgram.push(instruccion);
				}else{
					System.out.println("Error de instrucción");
				}
			}else{
				stop = true;
			}
			
		}while(stop==false);
		
		System.out.println(userProgram.toString());
	}

}
