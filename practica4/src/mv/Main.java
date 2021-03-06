package mv;
import gui.swing.MainWindow;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import mv.commands.CommandInterpreter;
import mv.commands.CommandParser;
import mv.cpu.Cpu;
import mv.exceptions.DivisionByZeroException;
import mv.exceptions.EmptyStackException;
import mv.exceptions.IncorrectMemoryPositionException;
import mv.exceptions.IncorrectParsingCommandException;
import mv.exceptions.IncorrectParsingInstruction;
import mv.exceptions.IncorrectProgramCounterException;
import mv.exceptions.InsufficientInstructionsException;
import mv.exceptions.InsufficientOperandsException;
import mv.exceptions.NegativeNumberIntoMemoryException;
import mv.instructions.Instruction;
import mv.program.ProgramMv;
import mv.reading.FromInputStreamIn;
import mv.reading.InputMethod;
import mv.reading.NullIn;
import mv.reading.StdIn;
import mv.writing.FromOutputStreamOut;
import mv.writing.NullOut;
import mv.writing.OutputMethod;
import mv.writing.StdOut;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;


public class Main {

	private static final int _BATCH_MODE 	= 0;
	private static final int _INTER_MODE 	= 1;
	private static final int _WINDOW_MODE 	= 2;
	
	private static int executionMode;
	private static Cpu cpu;
	private static InputMethod input;
	private static OutputMethod output;
	private static String programFileName;
	
	@SuppressWarnings("all") public static void parseOptions(String[] args) throws ParseException{
		CommandLineParser parser  = null;  
        CommandLine       cmdLine = null;
        
		//defino los argumentos del programa
		Options options = new Options();  
		options.addOption(OptionBuilder.withLongOpt("asm")
                .withDescription("Fichero con el codigo en ASM del programa a ejecutar.")
                .hasArg(true)
                .withArgName("asmfile")
                .isRequired(false)
                .create('a'));
		options.addOption(OptionBuilder.withLongOpt("help")
                .withDescription("Imprime el mensaje de ayuda")
                .hasArg(false)
                .isRequired(false)
                .create('h'));
		options.addOption(OptionBuilder.withLongOpt("in")
                .withDescription("Entrada del programa de la maquina-p.")
                .hasArg(true)
                .withArgName("infile")
                .isRequired(false)
                .create('i'));
		options.addOption(OptionBuilder.withLongOpt("mode")
                .withDescription("Modo de funcionamiento (batch | interactive). Por defecto, batch")
                .hasArg(true)
                .withArgName("mode")
                .isRequired(false)
                .create('m'));
		options.addOption(OptionBuilder.withLongOpt("out")
                .withDescription("Fichero donde se guarda la salida del programa de la maquina.")
                .hasArg(true)
                .withArgName("outfile")
                .isRequired(false)
                .create('o'));
	
		try {
			parser  = new BasicParser();  
            cmdLine = parser.parse(options, args); 
			
			 // Si está la opcion de ayuda, la imprimimos y salimos.  
            if (cmdLine.hasOption("h")){    // No hace falta preguntar por el parámetro "help". Ambos son sinónimos  
            	String helpParam = cmdLine.getOptionValue("a");
            	
                new HelpFormatter().printHelp(Main.class.getCanonicalName(), options );  
                System.exit(1);
            } 
            
            // Si el usuario ha especificado el modo lo leemos y procesamos          
            if (cmdLine.hasOption("m")){  
            	String modeReceived = cmdLine.getOptionValue("m");
            	if(modeReceived.equalsIgnoreCase("BATCH")){
            		executionMode = _BATCH_MODE;
            	}else if(modeReceived.equalsIgnoreCase("INTERACTIVE")){
            		executionMode = _INTER_MODE;
            	}else if(modeReceived.equalsIgnoreCase("WINDOW")){
            		executionMode = _WINDOW_MODE;
            	}else{
            		throw new Exception("Modo de ejecución no indentificado, use -h para ver los modos de ejecución");
            	}
            }else{
            	executionMode = _INTER_MODE;
            }
            
            // Si el usuario ha especificado el fichero asm, obtenemos la ruta e intentamos cargar el programa desde el fichero          
            if (cmdLine.hasOption("a")){  
            	programFileName = cmdLine.getOptionValue("a"); 
            	
            } else {//si no lo ha especificado, leemos el programa desde línea de comando   
            	if(executionMode != _INTER_MODE){
            		throw new Exception("Para usar este modo debes pasar el programa a ejecutar en el parámetro -a, usa -help para ver las opciones");
            	}else{
            		programFileName = null;  
            	}
            } 
            
            // Si el usuario ha especificado el in lo leemos y procesamos          
            if (cmdLine.hasOption("i")){  
            	String inReceived = cmdLine.getOptionValue("i");
            	
            	//intentamos abrir el archivo de entrada que nos pasan
            	try{
            		new FileReader(inReceived);
            		input = new FromInputStreamIn(inReceived);
            	}catch (FileNotFoundException e) { 
            		//si no existe el fichero, capturo la excepción  y activo el modo interactivo
            		executionMode	= _INTER_MODE;
        			System.err.println("No se ha encontrado el fichero de entrada, se activa el modo interactivo");
        			input = new NullIn();
        		}
            }else{		
            	if(executionMode != _BATCH_MODE){
            		//si no lo ha especificado, forzamos el modo interactivo
            		input = new NullIn();
            	}else{
            		input = new StdIn();
            	}
            }
            
            // Si el usuario ha especificado el out lo leemos y procesamos          
            if (cmdLine.hasOption("o")){  
            	String outReceived = cmdLine.getOptionValue("o");
            	output = new FromOutputStreamOut(outReceived);
            }else{
            	if(executionMode != _BATCH_MODE){
            		//si no lo ha especificado, forzamos el modo interactivo
                	output = new NullOut();
            	}else{
            		output = new StdOut();
            	}
            	
            }
            
		}catch(UnrecognizedOptionException e){ 
			System.err.println("Parámetro no reconocido. usa -h para ver los parámetros aceptados.");	
			System.exit(1);
		}catch(Exception e){
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
	
	/**
	 * Función que inicia la ejecución
	 * @param args
	 * @throws InsufficientOperandsException 
	 */
	public static void main(String[] args)throws InsufficientOperandsException{
		startMv(args);
	}
	
	/**
	 * Función que ejecuta la máquina virtual en el modo deseado
	 * @param args
	 */
	public static void startMv(String[] args){
		try{
			parseOptions(args);
			switch(executionMode){
				case _INTER_MODE:
					interactiveMode();
				break;
				case _BATCH_MODE:
					batchMode();
				break;
				case _WINDOW_MODE:
					windowMode();
				break;
				default:
					System.exit(1);
				break;
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
			System.exit(1);
		}
			
	}
	
	/**
	 * Método que se ejecuta en caso de que se haya indicado el modo interactivo e 
	 * inicializa dicho método.
	 * @throws InputMismatchException
	 * @throws FileNotFoundException
	 * @throws IncorrectParsingInstruction
	 */
	private static void interactiveMode() throws InputMismatchException, FileNotFoundException, IncorrectParsingInstruction {
		boolean end = false;
		
		// leer el programa
		ProgramMv program = programFileName == null ? ProgramMv.readProgram() : ProgramMv.readProgram(programFileName);
		
		//Creamos la CPU y cargamos el programa.
		cpu = new Cpu (input, output, program); //Se pasan las E/S y el programa.
		
		//Muestra el programa introducido.
		System.out.println(program.toString());
		
		//Pasamos al intérprete de comandos la cpu.
		CommandInterpreter.configureCommandInterpreter(cpu);
		
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
						Instruction inst = cpu.getCurrentInstruction();		
						System.out.println("Comienza la ejecución de "+inst.toString()+"\n");
						command.executeCommand();
					} catch (EmptyStackException e){
						System.err.println(e.getMessage());
					} catch (NegativeNumberIntoMemoryException e) {
						System.err.println(e.getMessage());
					}catch (InsufficientOperandsException e) {
						System.err.println(e.getMessage());
					} catch (DivisionByZeroException e) {
						System.err.println(e.getMessage());
					} catch (IncorrectProgramCounterException e) {
						System.err.println(e.getMessage());
					} catch (IncorrectMemoryPositionException e) {
						System.err.println(e.getMessage());
					}
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
	
	/**
	 * Método que se ejecuta en caso de que se haya seleccionado el modo batch y que
	 * inicializa el programa en función del modo.
	 * @throws InputMismatchException
	 * @throws FileNotFoundException
	 */
	private static void batchMode() throws InputMismatchException, FileNotFoundException {
		// leer el programa
		ProgramMv program = null;
		try {
			program = programFileName == null ? ProgramMv.readProgram() : ProgramMv.readProgram(programFileName);
		} catch (IncorrectParsingInstruction e) {
			System.err.println(e.getMessage());
			System.err.println("La instrucción fallida se encuentra en la linea "+ProgramMv.contLinea+": '"+ProgramMv.instructionLine+"'");
			System.exit(1);
		}
		
		//Creamos la CPU y cargamos el programa.
		cpu = new Cpu (input, output, program); //Se pasan las E/S y el programa.
		
		try {
			cpu.run();
		} catch (EmptyStackException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		} catch (NegativeNumberIntoMemoryException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}catch (InsufficientOperandsException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		} catch (DivisionByZeroException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		} catch (IncorrectProgramCounterException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		} catch (IncorrectMemoryPositionException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}

	}
	
	/**
	 * Método que se ejecuta en caso de que se haya indicado el modo window y que
	 * inicializa el programa en función del modo.
	 */
	private static void windowMode() {
		// leer el programa
		ProgramMv program = null;
		try {
			program = ProgramMv.readProgram(programFileName);
		} catch (IncorrectParsingInstruction e) {
			System.err.println(e.getMessage());
			System.err
					.println("La instrucción fallida se encuentra en la linea "
							+ ProgramMv.contLinea + ": '"
							+ ProgramMv.instructionLine + "'");
			System.exit(1);
		}

		// Creamos la CPU y cargamos el programa.
		cpu = new Cpu (input, output, program); //Se pasan las E/S y el programa.
		
		//le metemos un poco de look&feel predeterminado
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		    	//le metemos nimbus que es el predeterminado
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		    
		} catch (Exception e) {
			// si nimbus no existe, le metemos el predeterminado del sistema operativo
		    try {
		        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		    } catch (Exception ex) {
		    	//si no existe, no hay más que hacer
		    	System.exit(0);
		    }
		}
		
		// Construir el objeto que corresponde a la vista
		MainWindow view = new MainWindow(cpu);
	}
}
