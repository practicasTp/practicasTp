package mv;
import java.util.Scanner;

import mv.commands.CommandInterpreter;
import mv.commands.CommandParser;
import mv.cpu.Cpu;
import mv.instructions.Instruction;
import mv.instructions.InstructionParser;
import mv.program.ProgramMv;

public class Main {

	/**
	 * Función que se encarga de pedir el programa
	 * @return line
	 */
	private static ProgramMv readProgram(){
		ProgramMv program = new ProgramMv();
		boolean stop = false;
		String[] instruccionCortada;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("> Introduce el programa fuente:");
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
					program.push(instruccion);
				}else{
					System.err.println("Error: Instrucción incorrecta");					
				}
			}else{
				stop = true;
			}
			
		}while(stop==false);
		
		return program;
	}
	
	/**
	 * Función que inicia la ejecución
	 * @param args
	 */
	public static void main(String[] args) {
		ProgramMv program = null;
		Scanner sc = new Scanner(System.in);
		boolean end = false;
		
		program =  readProgram();
		
		//Muestra el programa introducido.
		System.out.println(program.toString());
		
		//Segunda fase:
		
		//Creamos la CPU y cargamos el programa.
		Cpu cpu = new Cpu ();
		cpu.loadProgram(program);
		
		//Pasamos al intérprete de comandos la cpu.
		CommandInterpreter.configureCommandInterpreter(cpu);
		
		do{
			System.out.print("> ");
			//Muestra el prompt y lee el comando.
			String commandLine = sc.nextLine();
			
			//Parseamos los comandos.
			CommandInterpreter command = CommandParser.parseCommand(commandLine);
			
			if(command != null){
				command.executeCommand();
				if(command.isFinished()){
					end = true;
				}
			}else{
				System.err.println("No te entiendo.");
			}
			
		}while(!end);
		
		
	}

}
