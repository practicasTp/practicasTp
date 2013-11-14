package pr1;
import java.util.Scanner;

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
	
	public static void main(String[] args) {
		Cpu intel = new Cpu();
		
		do{
			String line = prompt();
			Instruction laInstruccion = InstructionParser.parser(line);
			
			if(laInstruccion!=null){
				
				boolean resultado = intel.execute(laInstruccion);
				
				if(resultado){
					System.out.println(intel.toString());
				}else{
					System.out.println("\n");
				}
				
			}else{
				System.out.println("La instrucción que has metido no existe o no está bien escrita.");
			}
			
		}while(!intel.finished());
	}

}
