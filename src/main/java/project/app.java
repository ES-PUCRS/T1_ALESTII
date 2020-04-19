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
	private static Logger log;

	//@Main
	public static void main(String[] args) {
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
	        	for(int i = 0; i <= 4 ;i++){
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
		String[] init = {"false", "true"};
		UnitTest.main(init);
	}

	private static void add(String key, String value){
		System.out.println("INSERT >\t" + key + "\t" + value);
		map.add(key, value);
	}

    private static void setExceptionWay(){
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
	}
}



/*
	135087090=539840832,
	613234685=1045135809,
	77865186=481960112,
	837159809=839258919,
	851097643=1045135809,
	860031788=1011116869

* - insert: 163828202, 386942572
        -163828202 < -793359858
        -386942572 < -793359858
        -163828202 < -386942572
        -386942572 = -386942572
        -163828202 = -163828202
        -386942572 > -163828202
        -163828202 < -793359858
        -386942572 < -793359858
        -163828202 < -793359858
        -386942572 < -793359858
* - insert: 860031788, 1045135809
        -860031788 > -793359858
        -860031788 < -872845434
        -1045135809 > -163828202
        -1045135809 > -386942572
        -793359858 < -1045135809
        -1045135809 = -1045135809
        -793359858 > -163828202


*/