package src.Test.java.project.Algorith.DataStructure;

import src.main.java.project.Algorith.DataStructure.TreeMapSpecial;

import src.Test.java.project.UnitTest;

//@Test
public class TreeMapSpecial_Test{

	private static TreeMapSpecial map;
	private static int tests;

	public static void runTests(){
		tests = 0;
		run();
	}

	private static void run(){

		map = new TreeMapSpecial();
	
		firstInsertion();

		secondInsertion_Before();
		secondInsertion_After();

		thirdInsertion_Between_INTO_MergeOneLeft();
		thirdInsertion_Between_HEAD_MergeOneLeft();
		thirdInsertion_Between_INTO_MergeOneRight();
		thirdInsertion_Between_HEAD_MergeOneRight();
		thirdInsertion_OUT_MergeTwo();
		thirdInsertion_OUT_HEAD_MergeTwo();

		fourthInsertion_OUT_MergeAll();
		fourthInsertion_OUT_HEAD_MergeAll();
		fourthInsertion_INTO_MergeAll();
		fourthInsertion_INTO_HEADMIN_MergeAll();
		fourthInsertion_INTO_HEADMAX_MergeAll();

		seventhInsertion_OUT_MergeAll();

		objective();

		System.out.println("\nRunned " + tests + " tests.");
	}

	private static boolean firstInsertion(){ tests++;
		//CLEAR TRASH TESTS
		map.clear();

		//KNOWLEDGE BASE
		// NULL MAP

		//TEST PARAMETERS
		String 	 methodName 	= "firstInsertion";
		String 	 minimumValue 	= "100";
		String 	 maximumValue  	= "200";
		String[] shouldContain 	= {"100=200"};

		return UnitTest.Run(methodName, minimumValue, maximumValue, shouldContain, map);
	}



	private static boolean secondInsertion_Before(){ tests++;
		//CLEAR TRASH TESTS
		map.clear();

		//KNOWLEDGE BASE
		map.add("100", "200");

		//TEST PARAMETERS
		String 	 methodName 	= "secondInsertion_Before";
		String 	 minimumValue 	= "80";
		String 	 maximumValue  	= "90";
		String[] shouldContain 	= {"80=90", "100=200"};

		return UnitTest.Run(methodName, minimumValue, maximumValue, shouldContain, map);
	}

	private static boolean secondInsertion_After(){ tests++;
		//CLEAR TRASH TESTS
		map.clear();

		//KNOWLEDGE BASE
		map.add("100", "200");

		//TEST PARAMETERS
		String 	 methodName 	= "secondInsertion_After";
		String 	 minimumValue 	= "201";
		String 	 maximumValue  	= "299";
		String[] shouldContain 	= {"100=200", "201=299"};

		return UnitTest.Run(methodName, minimumValue, maximumValue, shouldContain, map);
	}



	private static boolean thirdInsertion_Between_INTO_MergeOneLeft(){ tests++;
		//CLEAR TRASH TESTS
		map.clear();

		//KNOWLEDGE BASE
		map.add("100", "200");
		map.add("300", "400");

		//TEST PARAMETERS
		String 	 methodName 	= "thirdInsertion_Between_INTO_MergeOneLeft";
		String 	 minimumValue 	= "160";
		String 	 maximumValue  	= "280";
		String[] shouldContain 	= {"100=280", "300=400"};

		return UnitTest.Run(methodName, minimumValue, maximumValue, shouldContain, map);
	}

	private static boolean thirdInsertion_Between_HEAD_MergeOneLeft(){ tests++;
		//CLEAR TRASH TESTS
		map.clear();

		//KNOWLEDGE BASE
		map.add("100", "200");
		map.add("300", "400");

		//TEST PARAMETERS
		String 	 methodName 	= "thirdInsertion_Between_HEAD_MergeOneLeft";
		String 	 minimumValue 	= "200";
		String 	 maximumValue  	= "280";
		String[] shouldContain 	= {"100=280", "300=400"};

		return UnitTest.Run(methodName, minimumValue, maximumValue, shouldContain, map);
	}

	private static boolean thirdInsertion_Between_INTO_MergeOneRight(){ tests++;
		//CLEAR TRASH TESTS
		map.clear();

		//KNOWLEDGE BASE
		map.add("100", "200");
		map.add("300", "400");

		//TEST PARAMETERS
		String 	 methodName 	= "thirdInsertion_Between_INTO_MergeOneRight";
		String 	 minimumValue 	= "250";
		String 	 maximumValue  	= "380";
		String[] shouldContain 	= {"100=200", "250=400"};

		return UnitTest.Run(methodName, minimumValue, maximumValue, shouldContain, map);
	}

	private static boolean thirdInsertion_Between_HEAD_MergeOneRight(){ tests++;
		//CLEAR TRASH TESTS
		map.clear();

		//KNOWLEDGE BASE
		map.add("100", "200");
		map.add("300", "400");

		//TEST PARAMETERS
		String 	 methodName 	= "thirdInsertion_Between_HEAD_MergeOneRight";
		String 	 minimumValue 	= "280";
		String 	 maximumValue  	= "300";
		String[] shouldContain 	= {"100=200", "280=400"};

		return UnitTest.Run(methodName, minimumValue, maximumValue, shouldContain, map);
	}

	private static boolean thirdInsertion_OUT_MergeTwo(){ tests++;
		//CLEAR TRASH TESTS
		map.clear();

		//KNOWLEDGE BASE
		map.add("100", "200");
		map.add("300", "400");

		//TEST PARAMETERS
		String 	 methodName 	= "thirdInsertion_OUT_MergeTwo";
		String 	 minimumValue 	= "80";
		String 	 maximumValue  	= "500";
		String[] shouldContain 	= {"80=500"};

		return UnitTest.Run(methodName, minimumValue, maximumValue, shouldContain, map);
	}

	private static boolean thirdInsertion_OUT_HEAD_MergeTwo(){ tests++;
		//CLEAR TRASH TESTS
		map.clear();

		//KNOWLEDGE BASE
		map.add("100", "200");
		map.add("300", "400");

		//TEST PARAMETERS
		String 	 methodName 	= "thirdInsertion_OUT_HEAD_MergeTwo";
		String 	 minimumValue 	= "100";
		String 	 maximumValue  	= "400";
		String[] shouldContain 	= {"100=400"};

		return UnitTest.Run(methodName, minimumValue, maximumValue, shouldContain, map);
	}



	private static boolean fourthInsertion_OUT_MergeAll(){ tests++;
		//CLEAR TRASH TESTS
		map.clear();

		//KNOWLEDGE BASE
		map.add("100", "200");
		map.add("300", "400");
		map.add("500", "600");

		//TEST PARAMETERS
		String 	 methodName 	= "fourthInsertion_OUT_MergeAll";
		String 	 minimumValue 	= "80";
		String 	 maximumValue  	= "700";
		String[] shouldContain 	= {"80=700"};

		return UnitTest.Run(methodName, minimumValue, maximumValue, shouldContain, map);
	}

	private static boolean fourthInsertion_OUT_HEAD_MergeAll(){ tests++;
		//CLEAR TRASH TESTS
		map.clear();

		//KNOWLEDGE BASE
		map.add("100", "200");
		map.add("300", "400");
		map.add("500", "600");

		//TEST PARAMETERS
		String 	 methodName 	= "fourthInsertion_OUT_HEAD_MergeAll";
		String 	 minimumValue 	= "100";
		String 	 maximumValue  	= "600";
		String[] shouldContain 	= {"100=600"};

		return UnitTest.Run(methodName, minimumValue, maximumValue, shouldContain, map);
	}

	private static boolean fourthInsertion_INTO_MergeAll(){ tests++;
		//CLEAR TRASH TESTS
		map.clear();

		//KNOWLEDGE BASE
		map.add("100", "200");
		map.add("300", "400");
		map.add("500", "600");

		//TEST PARAMETERS
		String 	 methodName 	= "fourthInsertion_INTO_MergeAll";
		String 	 minimumValue 	= "120";
		String 	 maximumValue  	= "580";
		String[] shouldContain 	= {"100=600"};

		return UnitTest.Run(methodName, minimumValue, maximumValue, shouldContain, map);
	}

	private static boolean fourthInsertion_INTO_HEADMIN_MergeAll(){ tests++;
		//CLEAR TRASH TESTS
		map.clear();

		//KNOWLEDGE BASE
		map.add("100", "200");
		map.add("300", "400");
		map.add("500", "600");

		//TEST PARAMETERS
		String 	 methodName 	= "fourthInsertion_INTO_HEADMIN_MergeAll";
		String 	 minimumValue 	= "100";
		String 	 maximumValue  	= "500";
		String[] shouldContain 	= {"100=600"};

		return UnitTest.Run(methodName, minimumValue, maximumValue, shouldContain, map);
	}

	private static boolean fourthInsertion_INTO_HEADMAX_MergeAll(){ tests++;
		//CLEAR TRASH TESTS
		map.clear();

		//KNOWLEDGE BASE
		map.add("100", "200");
		map.add("300", "400");
		map.add("500", "600");

		//TEST PARAMETERS
		String 	 methodName 	= "fourthInsertion_INTO_HEADMAX_MergeAll";
		String 	 minimumValue 	= "200";
		String 	 maximumValue  	= "600";
		String[] shouldContain 	= {"100=600"};

		return UnitTest.Run(methodName, minimumValue, maximumValue, shouldContain, map);
	}

	private static boolean seventhInsertion_OUT_MergeAll(){ tests++;
		//CLEAR TRASH TESTS
		map.clear();

		//KNOWLEDGE BASE			
		map.add("80", "90");
		map.add("100", "200");
		map.add("300", "400");
		map.add("500", "600");
		map.add("700", "800");
		map.add("900", "1000");

		//TEST PARAMETERS
		String 	 methodName 	= "seventhInsertion_OUT_MergeAll";
		String 	 minimumValue 	= "70";
		String 	 maximumValue  	= "2000";
		String[] shouldContain 	= {"70=2000"};

		return UnitTest.Run(methodName, minimumValue, maximumValue, shouldContain, map);
	}


	private static boolean objective(){ tests++;
		//CLEAR TRASH TESTS
		map.clear();

		//KNOWLEDGE BASE
		map.add("793359858", "872845434");		//
		map.add("163828202", "386942572");		//
		map.add("860031788", "1045135809");		// HIGHER ENTRY (1045135809)
		map.add("220012220", "481960112");		//
		map.add("107202696", "258581056");		//
		map.add("77865186", "199064861");		//
		map.add("207437354", "311919151");		//
		map.add("125701728", "213021371");		//
		map.add("851097643", "935519522");		//
		map.add("613234685", "874907996");		// HIGHER ENTRY (613234685)
		map.add("432569835", "566753675");		// LOWER ENTRY (566753675)
		map.add("45389652", "232145031");		// LOWER ENTRY (45389652)
		map.add("896106680", "1011116869");		//
		map.add("272672436", "491730771");		//
		map.add("354054836", "415525648");		//
		map.add("436958726", "539840832");		//
		map.add("861203282", "877295410");		//
		map.add("837159809", "839258919");		//
		map.add("273032866", "505234870");		//

		//TEST PARAMETERS
		String 	 methodName 	= "OBJECTIVE";
		String 	 minimumValue 	= "135087090";
		String 	 maximumValue  	= "305043137";
		String[] shouldContain 	= {"45389652=566753675", "613234685=1045135809"};

		return UnitTest.Run(methodName, minimumValue, maximumValue, shouldContain, map);
	}
		
}