package controllers;

import java.util.Scanner;

import mv.commands.CommandInterpreter;
import mv.commands.CommandParser;
import mv.cpu.Cpu;
import mv.exceptions.DivisionByZeroException;
import mv.exceptions.EmptyStackException;
import mv.exceptions.IncorrectMemoryPositionException;
import mv.exceptions.IncorrectParsingCommandException;
import mv.exceptions.IncorrectProgramCounterException;
import mv.exceptions.InsufficientInstructionsException;
import mv.exceptions.InsufficientOperandsException;
import mv.exceptions.NegativeNumberIntoMemoryException;
import mv.instructions.Instruction;

public class InteractiveController extends Controller {
	
	public InteractiveController(Cpu cpu) {
		super(cpu);
	}
	
	/**
	 * Ejecuta el modo interactivo cargando el programa, pasando la cpu, leyendo los
	 * comandos y ejecutándolos.
	 */
	public void start() {
		boolean end = false;
		
		//avisamos al programa de que el programa ya ha sido cargado
		cpu.programLoaded();
		
		//Pasamos al intérprete de comandos la cpu.
		CommandInterpreter.configureCommandInterpreter(this.cpu);
		
		do {
			try {				
				Scanner sc = new Scanner(System.in);
				CommandInterpreter command = null; 
				
				System.out.print("> ");
				//Muestra el prompt y lee el comando. 
				String commandLine;
				
				do{
					sc.reset();
					commandLine = sc.nextLine();
				}while(commandLine.length() == 0);
				
				//Parseamos los comandos.
				command = CommandParser.parseCommand(commandLine);
				
				if(command != null){
					try {
						command.executeCommand();
					} 
					catch (EmptyStackException e){}
					catch (NegativeNumberIntoMemoryException e) {}
					catch (InsufficientOperandsException e) {}
					catch (DivisionByZeroException e) {}
					catch (IncorrectProgramCounterException e) {} 
					catch (IncorrectMemoryPositionException e) {}
					if(command.isFinished()){
						end = true;
					}
				}
				
			}catch (InsufficientInstructionsException e) {
				System.err.println(e.getMessage());
			} catch (IncorrectParsingCommandException e) {
				System.err.println(e.getMessage());	
			}
		// escribir el estado de la maquina
		} while (!end);
	}

}
