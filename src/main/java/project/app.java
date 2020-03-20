package src.main.java.project;

import src.main.java.project.Algorithm.Datastructure.Kandle.KandleStructure;
import src.main.java.project.Exceptions.ExceptionHandler;
import src.main.java.project.FileManager.Writer;
import src.main.java.project.logger.Logger;

public class app {

	public static final boolean printOnTerminal = true;
	public static Logger log;

	public static void main(String[] args) {
		KandleStructure ks = new KandleStructure();
		Writer fileWriter = Writer.getInstance();
		log = log.getInstance();
		setExceptionWay();

		try{

			System.out.println("What is inside args: " + args[0]);

		} catch(Exception e){
			// System.out.println("HEAD> " + ks.getHead());
	
			ks.add(new Integer(10), new Integer(10));
			ks.add(new Integer(11), new Integer(11));
			ks.add(new Integer(12), new Integer(12));
			ks.add(new Integer(13), new Integer(13));
			System.out.println(ks);	

			// System.out.println(ks);
			ks.add(new Integer(20), new Integer(20));
			ks.add(new Integer(21), new Integer(21));
			ks.add(new Integer(22), new Integer(22));
			ks.add(new Integer(23), new Integer(23));
			System.out.println(ks);

				
			ks.add(new Integer(30), new Integer(30));
			ks.add(new Integer(31), new Integer(31));
			ks.add(new Integer(32), new Integer(32));
			ks.add(new Integer(33), new Integer(33));
			System.out.println(ks);
	
			ks.add(new Integer(40), new Integer(40));
			ks.add(new Integer(41), new Integer(41));
			ks.add(new Integer(42), new Integer(42));
			ks.add(new Integer(43), new Integer(43));
			System.out.println(ks);
	
			ks.add(new Integer(50), new Integer(50));
			// ks.add(new Integer(51), new Integer(51));
			// ks.add(new Integer(52), new Integer(52));
			// ks.add(new Integer(53), new Integer(53));
			System.out.println(ks);

	

			// ks.add(new Integer(60), new Integer(60));			
			// ks.add(new Integer(50), new Integer(50));
			// ks.add(new Integer(51), new Integer(51));
			// ks.add(new Integer(52), new Integer(52));
			// System.out.println(ks);
			try{
				System.out.println("\nPackages on Array(0): " + ks.referenceSize(0));
				ks.printArrayOn(0);
			}catch(Exception f){}	try{			
				System.out.println("\nPackages on Array(1): " + ks.referenceSize(1));
				ks.printArrayOn(1);
			}catch(Exception g){}	try{
				System.out.println("\nPackages on Array(2): " + ks.referenceSize(2));
				ks.printArrayOn(2);
			}catch(Exception h){}	try{
				System.out.println("\nPackages on Array(3): " + ks.referenceSize(3));
				ks.printArrayOn(3);
			}catch(Exception i){}	try{
				System.out.println("\nPackages on Array(4): " + ks.referenceSize(4));
				ks.printArrayOn(4);
			}catch(Exception j){}	try{	
				System.out.println("\nPackages on Array(5): " + ks.referenceSize(5));
				ks.printArrayOn(5);
			}catch(Exception k){}

			// ks.add(new Integer(53), new Integer(53));
			// ks.add(new Integer(60), new Integer(60));			
			// ks.add(new Integer(50), new Integer(50));
			// ks.add(new Integer(51), new Integer(51));
			// System.out.println(ks);
			// ks.add(new Integer(52), new Integer(52));
			// System.out.println(ks);
	



				
			// ks.add(new Long(1111L), new Long(2222L));
			// ks.add(new Long(3333L), new Long(4444L));
			// ks.add(new Long(5555L), new Long(6666L));
			// ks.add(new Long(8888L), new Long(7777L));
			// ks.add(new Long(9999L), new Long(1010L));
			// ks.add(new Long(1111L), new Long(2222L));
			// ks.add(new Long(3333L), new Long(4444L));
			// ks.add(new Long(5555L), new Long(6666L));
			// ks.add(new Long(8888L), new Long(7777L));
		
		}
		log.close();
	}

    private static void setExceptionWay(){
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
	}
}