package src.main.java.project.Algorith.DataStructure;

import java.util.Comparator;

public class treeComparator implements Comparator {
	
	public treeComparator() {}

	@Override
	public int compare(Object compareA, Object compareB){
		String ret = "";
		int a = 0;
		int b = 0;

		try{
			a = Integer.parseInt("-" + compareA);
		}catch(Exception e){
			ret = "\n\t** The value A is inconsistent: " + compareA;
			e.printStackTrace();
		}try{
			b = Integer.parseInt("-" + compareB);
		}catch(Exception f){
			ret = "\n\t** The value B is inconsistent: " + compareB;
			f.printStackTrace();			
		}
		if(!ret.equals("")){
			System.exit(0);
		}


		if(a < b){
			// System.out.println("\t" + a + " > " + b);
			return 1;
		}else if (a > b){
			// System.out.println("\t" + a + " < " + b);
			return -1;
		}

			// System.out.println("\t" + a + " = " + b);
		return 0;
	}
}