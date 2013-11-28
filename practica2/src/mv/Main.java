package mv;
import java.util.Scanner;

import mv.cpu.Cpu;
import mv.instructions.Instruction;
import mv.instructions.InstructionParser;

public class Main {

	/**
	 * Función que se encarga de pedir la instrucción
	 * @return line
	 */
	public static String prompt(){
		String line;
	
		System.out.print("Instrucción a ejecutar:");
		
		Scanner sc = new Scanner(System.in);
		line = sc.nextLine();
		
		System.out.println("Comienza la ejecución de "+line.toUpperCase());
		
		return line;
	}
	
	/**
	 * Función que inicia la ejecución
	 * @param args
	 */
	public static void main(String[] args) {
		Cpu intel = new Cpu();
		
		do{
			//pido la instrucción
			String line = prompt();
			
			//identifico la instrucción
			Instruction laInstruccion = InstructionParser.parser(line);
			
			//si he identificado la instrucción
			if(laInstruccion!=null){
				
				//la ejecuto
				boolean resultado = intel.execute(laInstruccion);
				
				//si la ejecución se ha ejecutado correctamente, muestro el estado de la máquina
				if(resultado){
					System.out.println(intel.toString());
				//si falla sólo añado una línea para que la ejecución sea más clara de cara al usuario
				}else{
					System.out.println();
				}
				
			}
			
			//repito la ejecución hasta que la instrucción que finaliza la máquina es introducida (halt)
		}while(!intel.finished());
	}

}
