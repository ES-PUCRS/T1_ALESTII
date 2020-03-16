package src.main.java.project;

import src.main.java.project.FileManager.Writer;
import src.main.java.project.Exceptions.ExceptionHandler;
import src.main.java.project.Algorithm.Datastructure.Kandle.KandleStructure;

public class app {

	public static final boolean printOnTerminal = false;

	public static void main(String[] args) {
		Writer fileWriter = Writer.getInstance();
		setExceptionWay();

		try{
			System.out.println("What is inside args: " + args[0]);
		} catch(Exception e){
				
			KandleStructure ks = new KandleStructure();
			ks.add(new Long(5678L), new Long(1234L));
			ks.add(new Long(4321L), new Long(9876L));
				System.out.println(ks);
			ks.pack();
				System.out.println(ks);
			ks.add(new Long(1111L), new Long(2222L));
			ks.add(new Long(3333L), new Long(4444L));
				System.out.println(ks);
			ks.forcePack();
				System.out.println(ks);

		}
	}

    private static void setExceptionWay(){
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
	}
}