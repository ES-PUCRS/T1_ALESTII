package src.main.java.project.Algorith.DataStructure;

import java.util.TreeMap;

public class TreeMapSpecial{
	
	private treeComparator comparator;
	private TreeMap map;

		public TreeMapSpecial(){
			this.map = new TreeMap(new treeComparator());
			comparator = new treeComparator();
		}


	public void clear(){
		map.clear();
	}
	

	public void add(String ni, String nf){

		if(map.isEmpty()){
			map.put(ni, nf);
			return;
		}


		ni = util(ni, nf, true);
	   	nf = util(nf, ni, false);

		map.put(ni, nf);
		deleteGaps(ni);
	}



	private String util(String key, String value,boolean smallest){
		String i, f;

		if(!map.containsKey(key)){
			i = (String) map.lowerKey(key);
		} else {
			i = key;
		}

   		if(i == null){
			i = (String) map.higherKey(key);
   		}

	   	if(i != null){
	   		f = (String) map.get(i);	   	

	   	if(	comparator.compare(key, i) >= 0 &&
	   		comparator.compare(key, f) <= 0 )
	   		if(smallest == true)
		   		key = i;
		   	else
		   		key = f;
	   	}

	   	return key;
	}



	private void deleteGaps(String key){
			killGaps(key, (String) map.get(key));
	}
	private void killGaps(String key, String entry){
		if( entry == null ){
				return;
		}

		if( comparator.compare(key, entry) < 0 &&
			comparator.compare((String) map.get(key), entry) >= 0){
				map.remove(entry);
			killGaps(key, (String) map.lowerKey(entry));
			killGaps(key, (String) map.higherKey(entry));
		}
	}


	@Override
	public String toString(){
		return map.toString();
	}
}