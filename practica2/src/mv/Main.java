package mv;
import java.util.Scanner;

import mv.commands.CommandInterpreter;
import mv.cpu.Cpu;
import mv.instructions.Instruction;
import mv.instructions.InstructionParser;
import mv.program.ProgramMv;

public class Main {

	/**
	 * Función que se encarga de pedir la instrucción
	 * @return line
	 */
	/*public static String promptUserProgram(){
		String line;
		
		Scanner sc = new Scanner(System.in);
		line = sc.nextLine();
		
		return line;
	}*/
	
	private static ProgramMv readProgram () {
		
		 // bucle que utiliza ParserInstruction.parseProgramInstruction(line);
	}
	
	/**
	 * Función que inicia la ejecución
	 * @param args
	 */
	public static void main(String[] args) {
		ProgramMv userProgram = new ProgramMv();
		boolean stop = false;
		String[] instruccionCortada;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Introduce el programa fuente:");
		//Primera fase:
		
		//Se lee el programa
		do{
			//pido la instrucción por el prompt.
			String instructionLine = sc.nextLine();
			
			//divido la cadena obtenida en el prompt
			instruccionCortada = instructionLine.split(" +");
			
			if(!instruccionCortada[0].equalsIgnoreCase("END")){
				//parseo la instruccion
				Instruction instruccion = InstructionParser.parser(instruccionCortada);
				
				//si he identificado la instrucción
				if(instruccion !=null){
					userProgram.push(instruccion);
				}else{
					System.err.println("Error de instrucción");					
				}
			}else{
				stop = true;
			}
			
		}while(stop==false);
		
		//Muestra el programa introducido.
		System.out.println(userProgram.toString());
		
		//Segunda fase:
		
		//Muestra el prompt y lee el comando.
		String commandLine = sc.nextLine();
		
		//Creamos la CPU y cargamos el programa.
		Cpu cpu = new Cpu ();
		cpu.loadProgram(userProgram);
		
		//Pasamos al intérprete de comandos la cpu.
		CommandInterpreter.configureCommandInterpreter(cpu);
		
		//Interpretamos los comandos.
		
	}

}
