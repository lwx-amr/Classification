import java.util.ArrayList;

public class NaiveBayes {

	// Variables
	ArrayList<DataFeature> features;
    ArrayList<String> featHeader, dataHeader;
    ArrayList<ArrayList<String>> trainSet;
    
	// Constructor 
	public NaiveBayes(ArrayList<ArrayList<String>> trainSet, ArrayList<String> header) {
		this.trainSet = trainSet;
		this.dataHeader = header;
		this.features = new ArrayList<DataFeature>();
		this.featHeader = new ArrayList<String>();
		//this.featHeader.add("\"id\"");
		this.featHeader.add("\"job\"");
		this.featHeader.add("\"marital\"");
		this.featHeader.add("\"education\"");
		this.featHeader.add("\"housing\"");
		calcAllFeatures();
	}
	
	// Calculate categories number for feature
	public DataFeature calcCategories(int neededIndex) {
		DataFeature obj  = new DataFeature(); 
		obj.name = dataHeader.get(neededIndex);
		int lastIndex = dataHeader.size()-1; // to access ouput
		for(int i=0; i<trainSet.size(); i++) {
			if(trainSet.get(i).get(lastIndex).equals("\"yes\""))
				obj.addYesToCat(trainSet.get(i).get(neededIndex)); // Passing Category name
			else
				obj.addNoToCat(trainSet.get(i).get(neededIndex)); // Passing Category name
		}
		return obj;				
	}
	
	// Calculate all categories for our needed features 
	public void calcAllFeatures() {
		DataFeature obj;
		for(int i=0; i<featHeader.size();i++) {
			int neededIndex = dataHeader.indexOf(featHeader.get(i)); // index of needed feature in dataset
			obj = calcCategories(neededIndex); 
			features.add(obj);
		}
		print();
	}
	
	// Get output
	public String getOutput(ArrayList<String> row) {
		String cat;
		double yesRank = 1, noRank = 1, totalYes = features.get(0).totalYes, totalNo = features.get(0).totalNo;
		for(int i=0; i<4 ; i++) {
			int neededIndex = dataHeader.indexOf(featHeader.get(i)); // index of needed feature in dataset
			cat = row.get(neededIndex);
			yesRank *= features.get(i).categories.get(cat).numOfYes/totalYes;
			noRank *= features.get(i).categories.get(cat).numOfNo/totalNo;
		}
		yesRank= yesRank * (totalYes/trainSet.size());
		noRank = noRank * (totalNo/trainSet.size());
		//System.out.println(yesRank + "   " + noRank);
		if((yesRank >= noRank))
			return "\"yes\"";
		else
			return "\"no\"";
	}
	
	// Print Features
	public void print() {
		for(int i=0; i<features.size();i++) {
			System.out.println(features.get(i).name + "  " + features.get(i).totalNo + "  " + features.get(i).totalYes);
			for (String s : features.get(i).categories.keySet())
				System.out.println("   "+s + "  " + features.get(i).categories.get(s).numOfNo + "  "  + features.get(i).categories.get(s).numOfYes);
		}
	}
}
