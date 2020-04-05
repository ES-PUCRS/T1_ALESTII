package src.Test.java.project;

import src.Test.java.project.Algorith.DataStructure.TreeMapSpecial_Test;
import src.main.java.project.Algorith.DataStructure.TreeMapSpecial;
import java.io.PrintWriter;
import java.io.Console;

public class UnitTest{

	private static boolean returnPass;
	private static boolean returnFail;

	public static void main(String args[]){

		returnPass = Boolean.parseBoolean(args[0]);
		returnFail = Boolean.parseBoolean(args[1]);

		System.out.println("-------------- Running tests --------------\n");

			TreeMapSpecial_Test.runTests();

		System.out.println("\n--------------- End tests -----------------\n\n");
	}



	public static boolean Run (String methodName,
							   String minimumValue,
							   String maximumValue,
							   String[] resultValue,
							   TreeMapSpecial map){

		String ret = "\n\tBase " + map.toString() + "\n\tinsert ("+minimumValue+", "+maximumValue+")\n\t:- ";			
		map.add(minimumValue, maximumValue);
		methodName += ": ";
		String mapStr = map.toString();

		ret = methodName + ret + mapStr + " ~> ";

		if(methodName.contains("OBJECTIVE"))
			ret = "\n\n" + methodName;

		for(int i = 0; i < resultValue.length; i++){
			if(mapStr.contains(resultValue[i])){
				mapStr = mapStr.replace(resultValue[i], "");
			} else {
				System.out.println(ret + "FAIL");
				return true;
			}
		}

		mapStr = mapStr.replaceAll(" ", "");
		mapStr = mapStr.replaceAll(",", "");

		if(!mapStr.equals("{}")){
			if(returnFail){
				System.out.println(ret + "FAIL");
			} else{
				System.out.println(methodName + "FAIL");
			}
			return true;
		}

		if(returnPass){
			System.out.println(ret + "OK");
		} else{
			System.out.println(methodName + "OK");
		}
		return false;
	}
} 