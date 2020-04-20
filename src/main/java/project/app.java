package src.main.java.project;

import src.main.java.project.Algorith.DataStructure.TreeMapSpecial;
import src.main.java.project.Exceptions.ExceptionHandler;
import src.main.java.project.logger.Logger;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.lang.String;
import java.io.File;

import src.Test.java.project.UnitTest;

public class app {

	public static boolean printOnTerminal = true;
	private static TreeMapSpecial map;
	private static String returnPass;
	private static String returnFail;
	private static Logger log;

	//@Main
	public static void main(String[] args) {
			int runThroughFiles = -1;

			try{
				
				runThroughFiles = Integer.parseInt(args[0]);
				
				if(args.length > 2)
					returnFail = args[2];
				
				if (args.length > 1){
					returnPass = args[1];
				} else {
					returnPass = "false";
					returnFail = "true";
				}

			}catch(Exception e){
				e.printStackTrace();
				System.out.println("Set a valid case number");
				System.exit(0);
			}


		log = Logger.getInstance();
		map = new TreeMapSpecial();
		setExceptionWay();

		runTests();

	        String path = Paths.get("").toAbsolutePath().toString();
	        path = path.replaceAll("bin", "");
	        char separator = path.charAt(path.length() - 1);
	        path += "src" + separator +
			        "Test" + separator +
			        "Cases" + separator;

    		String filename = "";
    		File filepath = null;
    		Scanner data = null;
    		String[] entry = {};
			String pathFix = path;

	        try{
	        	for(int i = 0; i <= runThroughFiles ;i++){
	        		if(i < 10){
	        			filename = "caso0"+i+".txt";
	        		}else{
	        			filename = "caso"+i+".txt";
	        		}
	        		log.initFile(filename);
	        		path = pathFix + filename;
			        filepath = new File(path);
			        data = new Scanner(filepath);
			        
			        while (data.hasNext()) {
			        	entry = data.nextLine().split("-");
			            add(entry[0], entry[1]);
			        }
	        		data.close();
					log.close(map.toString(), map.size());
					map.clear();
	        	}
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		System.out.println("\n\tFile not found");
	    	}
	}



	private static void runTests(){
		UnitTest.main(
			new String[] {returnPass, returnFail}
		);
	}

	private static void add(String key, String value){
		System.out.println("INSERT >\t" + key + "\t" + value);
		map.add(key, value);
	}

    private static void setExceptionWay(){
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
	}
}