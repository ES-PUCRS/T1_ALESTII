package src.main.java.project;

import src.main.java.project.Algorithm.Datastructure.Kandle.KandleStructure;
import src.main.java.project.Exceptions.ExceptionHandler;
import src.main.java.project.FileManager.Writer;
import src.main.java.project.logger.Logger;

public class app {

	public static final boolean printOnTerminal = true;
	public static Logger log;

	private static boolean each = true;
	private static boolean packing = true;
	
	public static void main(String[] args) {
		KandleStructure ks = new KandleStructure();
		Writer fileWriter = Writer.getInstance();
		log = log.getInstance();
		setExceptionWay();

		try{

			if(args.length < 2){
				each = false;
				packing = false;
			}else if(args.length < 3){
				each = Boolean.parseBoolean(args[1]);
				packing = false;
			}else if(args.length > 3){
				System.out.println("Have I said that there were more than three options? Tha fuc boy.");
				System.exit(1);
			}else{

				if(args[1] == null)
					each = false;
				else
					each = Boolean.parseBoolean(args[1]);
				
				packing = Boolean.parseBoolean(args[2]);
			}

			int iterations = Integer.parseInt(args[0]);
			
			for(int i = 0; i < iterations; i++){
				creatPacks(ks, i);
			}

			if(each == true)
				System.out.println("\nFinal list:\n" + ks);
			else
				System.out.println("Final list:\n" + ks);

			showReferences(ks);

		} catch(Exception e){
			System.out.println("Can not creat null structure to run once."+
							   "\nIt does not make even sense."+
							   "\nAm I a fucking joke to you?"+
							   "\nRun this again with some value. Moron.");
			System.exit(1);
		}

		log.close();
	}

	private static void creatPacks(KandleStructure ks, int i){
		i *= 10;
		ks.add(new Integer(i), new Integer(i+9));
		if(each == true)
			System.out.println(ks);
		if(packing == true)
			showReferences(ks);
	}

	private static void showReferences(KandleStructure ks){
		try{
			int i = 0;
			while(true){
				System.out.println("\nPackages on Array("+i+"): " + ks.referenceSize(i));
				ks.printArrayOn(i);
				i++;
			}
		}catch(Exception e){}
	}

    private static void setExceptionWay(){
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
	}
}