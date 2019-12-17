import java.util.ArrayList;

public class DecisionTree {
	
	// Variables
	ArrayList<ArrayList<String>> dataSet;
	ArrayList<String> featHeader;
	ArrayList<DataFeature> features;
	 
	// Constructor 
	public DecisionTree(ArrayList<ArrayList<String>> dataSet, ArrayList<DataFeature> features, ArrayList<String> header) {
		this.dataSet = dataSet;
		this.features = features;
		this.featHeader = header;
		initializeTree();
	}
	
	// Initialize our tree with data set
	public void initializeTree(){
		
	}
	
	// Get output
	public String getOutput(ArrayList<String> row) {
		String cat = "\"no\"";
		return cat;
	}
}
