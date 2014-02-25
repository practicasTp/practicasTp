package mv;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

import mv.commands.CommandInterpreter;
import mv.commands.CommandParser;
import mv.commands.Run;
import mv.cpu.Cpu;
import mv.exceptions.IncorrectParsingInstruction;
import mv.instructions.Instruction;
import mv.instructions.InstructionParser;
import mv.program.ProgramMv;
import mv.reading.FromInputStreamIn;
import mv.reading.InputMethod;
import mv.reading.NullIn;
import mv.writing.FromOutputStreamOut;
import mv.writing.NullOut;
import mv.writing.OutputMethod;
import mv.writing.StdOut;

import org.apache.commons.cli.*;  

public class Main {

	public static ProgramMv getProgram(String[] args, Options options){
		ProgramMv program 		  = null;
		String asmRoute 		  = null;
		CommandLineParser parser  = null;  
        CommandLine       cmdLine = null;
		
		try {
			
			parser  = new BasicParser();  
            cmdLine = parser.parse(options, args); 
			
			 // Si está la opcion de ayuda, la imprimimos y salimos.  
            if (cmdLine.hasOption("h")){    // No hace falta preguntar por el parámetro "help". Ambos son sinónimos  
            	String helpParam = cmdLine.getOptionValue("a");
            	
            	/*
            	 * 
            	 * SI ME VIENE UN PARAMETRO EN EL MENOS H PETO Y ME SALGO
            	 * 
            	 */
            	
                new HelpFormatter().printHelp(Main.class.getCanonicalName(), options );  
                System.exit(1);
            } 
            
            // Si el usuario ha especificado el fichero asm, obtenemos la ruta e intentamos cargar el programa desde el fichero          
            if (cmdLine.hasOption("a")){  
            	asmRoute = cmdLine.getOptionValue("a"); 
            	program = readProgramFromFile(asmRoute);
            } else {//si no lo ha especificado, leemos el programa desde línea de comando          		
        		program = readProgramFromCmd();  
            } 
            
		} catch (Exception e) {
			System.err.println("Error al obtener el programa");	
			System.exit(1);
		}
		
		return program;
	}
	
	
	/**
	 * Función que se encarga de pedir el programa por consola
	 * @return line
	 */
	private static ProgramMv readProgramFromFile(String asmRoute)throws FileNotFoundException, InputMismatchException{ 
		ProgramMv program = new ProgramMv();
		String[] instruccionCortada;
		boolean stop	  = false;
		int contLinea	  = 1;
		
		try{
			Scanner sc= new Scanner(new FileReader(asmRoute)); 
			while(sc.hasNext() || !stop ){ 
				try{ 
					//leo la intrucción del fichero
					String instructionLine = sc.nextLine();
					
					//divido la cadena obtenida en el fichero
					instruccionCortada = instructionLine.split(" +");
					
					if(!instruccionCortada[0].equalsIgnoreCase("END")){
						//parseo la instruccion
						Instruction instruccion = InstructionParser.parser(instruccionCortada);
						
						try{
							//si he identificado la instrucción
							if(instruccion !=null){
								program.push(instruccion);
							}else{
								throw new IncorrectParsingInstruction("Instrucción incorrecta en la linea "+contLinea+": '"+instructionLine+"'");
							}
						}catch(IncorrectParsingInstruction e){
							System.err.println(e);
							System.exit(1);
						}
					}else{
						stop = true;
					}
					
					contLinea++;
				} catch(InputMismatchException e){ 
					sc.next(); 
				}
			}	
		}catch (FileNotFoundException e) { 
			System.err.println("Uso incorrecto: Fichero ASM no especificado, ruta: '"+asmRoute+"'");
			System.exit(1);
		}
		
		return program;
	}
	
	
	/**
	 * Función que se encarga de pedir el programa por consola
	 * @return line
	 */
	private static ProgramMv readProgramFromCmd(){
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
		String inputRoute = null;
		ExecutionMode mode = ExecutionMode.BACH;
		String outputRoute = null;
		CommandLineParser parser  = null;  
        CommandLine       cmdLine = null;
        InputMethod input = null;
        OutputMethod output = null;
		
		//defino los argumentos del programa
		Options options = new Options();  
		options.addOption("a", true,  "Fichero con el codigo en ASM del programa a ejecutar.");
		options.addOption("h",  false, "Imprime el mensaje de ayuda");
		options.addOption("i", true,  "Entrada del programa de la maquina.");
		options.addOption("m", true,  "Modo de funcionamiento (batch | interactive). Por defecto, batch.");
		options.addOption("o", true,  "Fichero donde se guarda la salida del programa de la maquina.");
	
		//obtengo el programa en función de los argumentos
		program = getProgram(args, options);
		
		try {
			
			parser  = new BasicParser();  
            cmdLine = parser.parse(options, args);  
            
            // Si el usuario ha especificado el modo lo leemos y procesamos          
            if (cmdLine.hasOption("m")){  
            	String modeReceived = cmdLine.getOptionValue("m");
            	if(modeReceived.equalsIgnoreCase("BACH")){
            		mode = ExecutionMode.BACH;
            	}else if(modeReceived.equalsIgnoreCase("INTERACTIVE")){
            		mode = ExecutionMode.INTERACTIVE;
            	}else{
            		/*
            		 * 
            		 * LANZO EXCEPCION DE MODO DE EJECUCIÓN NO DEFINIDO
            		 * 
            		 */
            	}
            }
            
            // Si el usuario ha especificado el in lo leemos y procesamos          
            if (cmdLine.hasOption("i")){  
            	String inReceived = cmdLine.getOptionValue("m");
            	input = new FromInputStreamIn(inReceived);
            }else{
            	input = new NullIn();
            }
            
            // Si el usuario ha especificado el out lo leemos y procesamos          
            if (cmdLine.hasOption("o")){  
            	String outReceived = cmdLine.getOptionValue("m");
            	output = new FromOutputStreamOut(outReceived);
            }else{
            	output = new NullOut();
            }
            
		} catch (Exception e) {
			System.err.println("Error al procesar las opciones, teclee -h para ver las opciones disponibles.");	
			System.exit(1);
		}
		
		//Muestra el programa introducido.
		System.out.println(program.toString());
		
		//Segunda fase:
		Scanner sc = new Scanner(System.in);
		boolean end = false;
		
		
		//Creamos la CPU y cargamos el programa.
		Cpu cpu = new Cpu (input, output, program); //Se pasan las E/S y el programa.
		/*cpu.loadProgram(program);*/ //No es necesario, se pasa a la CPU directamente.
		
		//Pasamos al intérprete de comandos la cpu.
		CommandInterpreter.configureCommandInterpreter(cpu);
		
		do{
			CommandInterpreter command = null; 
			
			//si el metodo de ejecución es interactivo entonces pedimos al usuario el comando (step, run, step 3...)
			if(mode == ExecutionMode.INTERACTIVE){
				System.out.print("> ");
				//Muestra el prompt y lee el comando.
				String commandLine = sc.nextLine();
				
				//Parseamos los comandos.
				command = CommandParser.parseCommand(commandLine);
			}else if(mode == ExecutionMode.BACH){
				//si el método de ejecución es bach, entonces ejecutamos un run que ejecute toda la aplicación del programa
				command = new Run();
			}
			
			
			
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
