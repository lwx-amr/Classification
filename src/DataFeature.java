import java.util.HashMap;
import java.util.Map;

public class DataFeature {

	// Variables 
	String name;
	Map<String,Category> categories;
	double totalYes,totalNo;
	
	// Constructor
	public DataFeature(){
		this.categories = new HashMap<String, Category>();
		this.totalYes = 0;
		this.totalNo = 0;
	}
	
	// Function to increment category values in map
	public void addYesToCat(String name) {
		if(categories.containsKey(name)) {
			double oldYes= categories.get(name).yes;
			categories.get(name).yes = ++oldYes;
		}else
			categories.put(name, new Category(1,0));
		totalYes++;
	}
	public void addNoToCat(String name) {
		if(categories.containsKey(name)) {
			double oldNo = categories.get(name).no;
			categories.get(name).no = ++oldNo;
		}else
			categories.put(name, new Category(0,1));
		totalNo++;
	}

}
