package src.main.java.project;

import src.main.java.project.FileManager.Writer;
import src.main.java.project.Exceptions.ExceptionHandler;
import src.main.java.project.Algorithm.Datastructure.Kandle.KandleStructure;

public class app {

	public static final boolean printOnTerminal = false;

	public static void main(String[] args) {
		KandleStructure ks = new KandleStructure();
		Writer fileWriter = Writer.getInstance();
		setExceptionWay();

		try{

			System.out.println("What is inside args: " + args[0]);
		
		} catch(Exception e){
				
			ks.add(new Long(1111L), new Long(2222L));
			ks.add(new Long(3333L), new Long(4444L));
			ks.add(new Long(5555L), new Long(6666L));
			ks.add(new Long(8888L), new Long(7777L));
				System.out.println(ks);
			ks.add(new Long(9999L), new Long(1010L));
			ks.add(new Long(1111L), new Long(2222L));
			ks.add(new Long(3333L), new Long(4444L));
			ks.add(new Long(5555L), new Long(6666L));
				System.out.println(ks);
			ks.add(new Long(8888L), new Long(7777L));
				System.out.println(ks);
			
		}
	}

    private static void setExceptionWay(){
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
	}
}