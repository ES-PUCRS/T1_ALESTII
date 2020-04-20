package src.main.java.project.logger;

import src.main.java.project.app;

import java.io.FileNotFoundException;
import java.lang.StackTraceElement;
import java.lang.StringBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.FileWriter;
import java.util.Scanner;
import java.lang.String;
import java.io.File;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.Duration;

import src.main.resources.LogMessages;

import java.util.TreeMap;
import java.util.Map;

public class Logger<E>{
	private static PrintWriter logDataWriter;
	public static boolean runningFile;
	private static Path logDateTime;
	private static Logger instance;

	private static DateTimeFormatter time;
	private static LocalDateTime beginProg;
	private static StringBuilder unitTests;
	public static LocalDateTime now;
	public static TreeMap<Integer, Integer> map;

	private boolean printOnTerminal = app.printOnTerminal;

	private Logger(){
		DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH'H'.mm'M'.ss'S'");
		time = DateTimeFormatter.ofPattern("HH:mm:ss:SSS' - '"); 
        
        String filePath = Paths.get("").toAbsolutePath().toString();
        now = LocalDateTime.now();
		beginProg = now;

        filePath += "\\SavedLogs\\" + "LoggerRuntime_" + dt.format(now) + ".txt";   
        
        logDateTime =  Paths.get(filePath);
		String welcome = "";

		try{
			File logFile = new File(logDateTime.toString());

			if(!logFile.exists()){
            	logFile.createNewFile();

				welcome = "Logger file has been created on " +
						  logDateTime.subpath((logDateTime.getNameCount()-3), (logDateTime.getNameCount()-1)) + 
					 	  ",\nat file " +
	  					  logDateTime.getFileName() + ".";

				if(printOnTerminal)
					System.out.println(welcome + "\n");
			
		
				logDataWriter = new PrintWriter(new FileWriter(logFile, true));
		        logDataWriter.println(welcome);

				StringBuilder logEnviroment = new StringBuilder();
				
				logEnviroment
	   				.append("\nProcessor: ").append(System.getenv("PROCESSOR_IDENTIFIER")).append(". ")
					.append("\nComputer: ")	.append(System.getenv("COMPUTERNAME"))		  .append(", ")
					.append("User: ")		.append(System.getenv("USERNAME"))			  .append(".")
					.append("\nSystem: ")	.append(System.getenv("OS"))				  .append(", ")
											.append(System.getenv("NUMBER_OF_PROCESSORS")).append(" Core(s),")
					.append(" v. ")			.append(System.getenv("PROCESSOR_REVISION"))  .append(".\n");

				//logDataWriter.append(logEnviroment);
		        logDataWriter.close();
        	}

        }catch(IOException x){
             System.err.format("Erro de E/S: %s%n", x);
        }
        runningFile = false;
        map = new TreeMap();
		unitTests = new StringBuilder();	
	}
	
	public static Logger getInstance() {
        if (instance == null)
            instance = new Logger();
        return instance;
    }


	public void exception(Thread t, Throwable e){
		StackTraceElement[] stack = e.getStackTrace();
		String exception = "THROWN EXCEPTION\n" + e.getClass().getCanonicalName() + "\n";
		String message = e.getLocalizedMessage();

		if(message == null)
			message = "";
		else
			message += "\n";

		exception += "Thread "+ t.getId() +", " + t.getState() + "\n";
		exception += message + stack[0].toString() + "\n" + stack[stack.length-1].toString();

		publishLog(exception);
	}


	private void publishLog(String data){
		if(data == null)
			return;

		try{
			File logFile = new File(logDateTime.toString());

			if(!logFile.exists()){
            	logFile.createNewFile();
			
				// if(printOnTerminal)
				// 	System.out.println("New log file has been created: " + logDateTime.getFileName());
			}
		
			logDataWriter = new PrintWriter(new FileWriter(logFile, true));
        
        }catch(IOException x){
             System.err.format("Erro de E/S: %s%n", x);
        }

		data = data.replaceAll("\n", "\n               ");
		logDataWriter.append("\n" + time.format(LocalDateTime.now()) + data);
        logDataWriter.close();
	}

	public void close(String map, int mapSize){
		StringBuilder str = new StringBuilder();
			now = LocalDateTime.now();
			Duration duration = Duration.between(beginProg, now);

			for(Map.Entry<Integer,Integer> entry : this.map.entrySet()) {
			  Integer key = entry.getKey();
			  Integer value = entry.getValue();
			  str.append(key + LogMessages.GAPS_REMOVAL.toString() + value + " times;\n");
			}

			str.append(LogMessages.CLOSE_LOG.toString())
			   .append(duration.toDays())
			   .append(" Days, ")
			   .append(duration.toHours()%60)
			   .append(" hours, ")
			   .append(duration.toMinutes()%60)
			   .append(" Minutes, ")
			   .append(duration.toSeconds()%60)
			   .append(" Seconds, ")
			   .append(duration.toMillis()%1000)
			   .append(" Milli.\n")
			   .append("Final Map contains " + mapSize + " objects\n")
			   .append("Final Map result: ")
			   .append(map)
			   .append("\n");
			   
			   if(printOnTerminal)
					System.out.println("\n" + str);
			
			this.map.clear();
			runningFile = false;
		publishLog(str.toString());
	}

	public void deletedGaps(int gaps){
		if(runningFile)
			if(map.containsKey(gaps))
				map.put(gaps, map.get(gaps).intValue() + 1);
			else
				map.put(gaps, 1);
	}

	public void initFile(String filename){
		beginProg = LocalDateTime.now();
		runningFile = true;
		publishLog(LogMessages.INIT_RUN.toString() + filename);
	}

	public void referenceListGrowing(int level){
		publishLog(LogMessages.REFERENCE_LIST_GROWING.toString() + level);
	}


	public void UnitTest(String result){
		unitTests.append(result + "\n");
	}
	public void FinishUnitTest(){
		StringBuilder str = new StringBuilder();
			 str.append("-------------- Running tests --------------\n")
				.append(unitTests.toString())
				.append("\n--------------- End tests -----------------\n\n");
				
				if(printOnTerminal)
					System.out.println(str.toString());

			unitTests = null;
		publishLog(str.toString());
	}
}