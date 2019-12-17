import java.util.ArrayList;

public class NaiveBayes {

	// Variables
	ArrayList<DataFeature> features;
	 
	// Constructor 
	public NaiveBayes(ArrayList<DataFeature> features) {
		this.features = features;
	}
	
	// Get output
	public String getOutput(ArrayList<String> row) {
		String cat;
		double yesRank = 0, noRank = 0, totalYes = features.get(0).totalYes, totalNo = features.get(0).totalNo;
		int [] indexes = {1,2,3,6};
		for(int i=0; i<4 ; i++) {
			cat = row.get(indexes[i]);
			yesRank += features.get(i).categories.get(cat).yes/totalYes;
			noRank += features.get(i).categories.get(cat).no/totalNo;
		}
		return (yesRank >= noRank)? "\"yes\"" : "\"no\"";
	}
	
}
