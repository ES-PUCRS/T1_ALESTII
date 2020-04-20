package src.main.java.project.Algorith.DataStructure;

import src.main.java.project.logger.Logger;
import java.lang.IllegalArgumentException;
import java.util.TreeMap;
import java.util.Map;

public class TreeMapSpecial{
	
	private treeComparator comparator;
	private static Logger log;
	private TreeMap map;

		public TreeMapSpecial(){
			this.map = new TreeMap(new treeComparator());
			comparator = new treeComparator();
			log = Logger.getInstance();
		}


	public void clear(){
		map.clear();
	}

	public int size(){
		return map.size();
	}

	public void putAll(Map<String, String> map){
		map.putAll(map);
	}
	

	public void add(String ni, String nf){
		if(comparator.compare(ni, nf) > 0)
			throw new IllegalArgumentException("Wrong values order");

		if(map.isEmpty()){
			map.put(ni, nf);
			return;
		}

		ni = util(ni, nf);
	   	nf = util(nf, ni);

		map.put(ni, nf);
		deleteGaps(ni);
	}



	private String util(String key, String value){
		String i, f;

		if(map.containsKey(key)){
			i = key;
		} else {
			i = (String) map.lowerKey(key);
	   		if(i == null){
				i = (String) map.higherKey(key);
	   		}
		}

		f = (String) map.get(i);

	   	if(	comparator.compare(key, i) >= 0 &&
	   		comparator.compare(key, f) <= 0 )
	   		if( comparator.compare(key, value) <= 0 )
		   		key = i;
		   	else
		   		key = f;

	   	return key;
	}



	private static int gaps = 0;

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
				gaps++;
			killGaps(key, (String) map.lowerKey(entry));
		} else if(gaps > 0){
			log.deletedGaps(gaps);
			gaps = 0;
		}
	}


	@Override
	public String toString(){
		return map.toString();
	}
}