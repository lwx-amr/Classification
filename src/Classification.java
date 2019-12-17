import java.util.ArrayList;

public class Classification {
	
	// Variables
	private String dataFile; 
    private double dataPercent, trainSize;
    private ArrayList<String> dataHeader, naiveOutput, treeOutput, featHeader;
    private ArrayList<ArrayList<String>> dataSet, trainSet, testSet;
	private ArrayList<DataFeature> features;
    private NaiveBayes naiveObject;
    private DecisionTree dTreeObject;
	
	// Constructor
	public Classification(String filename, double percent){
		this.trainSize = 0.75;
		this.dataFile = filename;
		this.dataPercent = percent/100;
		this.dataSet = new ArrayList<ArrayList<String>>();
		this.trainSet = new ArrayList<ArrayList<String>>();
		this.testSet = new ArrayList<ArrayList<String>>();
		this.naiveOutput = new ArrayList<String>();
		this.treeOutput = new ArrayList<String>();
		this.features = new ArrayList<DataFeature>();
		this.featHeader = new ArrayList<String>();
		this.featHeader.add("\"job\"");
		this.featHeader.add("\"marital\"");
		this.featHeader.add("\"education\"");
		this.featHeader.add("\"housing\"");
	}
	
	// Initialize data set to be ready to use
	public void initializeDataSet() {
		CSVFileController csvReader = new CSVFileController(dataFile, dataPercent, 4522);
		dataSet = csvReader.readData();
		dataHeader = dataSet.get(0);
		dataSet.remove(0);
		int dataSize = dataSet.size();
		int trainLines = (int)Math.floor((dataSize)*trainSize);
		for(int i = 0; i<dataSize; i++) {
			if(i<trainLines) {
				trainSet.add(dataSet.get(i));
				continue;
			}
			testSet.add(dataSet.get(i));
		}
	}
	
	// Calculate categories number for feature
	public DataFeature calcCategories(int index) {
		DataFeature obj  = new DataFeature(); 
		obj.name = dataHeader.get(index);
		int lastIndex = trainSet.get(0).size()-1;
		for(int i=0; i<trainSet.size(); i++) {
			if(trainSet.get(i).get(lastIndex).equals("\"yes\""))
				obj.addYesToCat(trainSet.get(i).get(index));
			else
				obj.addNoToCat(trainSet.get(i).get(index));
		}
		return obj;				
	}
	
	// Calculate categories number for feature
	public void calcAllCategories() {
		DataFeature obj;
		for(int i=0; i<featHeader.size();i++) {
			obj = calcCategories(dataHeader.indexOf(featHeader.get(i)));
			features.add(obj);
		}
	}
	
	// Print the needed output
	public void output() {
		/*System.out.println("\n\n----------------Data Header ---------------------");
		System.out.println(dataHeader.toString());
		
		System.out.println("\n\n----------------Training Set ---------------------");
		for(int i = 0; i<trainSet.size(); i++) {
			System.out.println(trainSet.get(i).toString());
		}
		System.out.println("\n\n----------------Test Set ---------------------");
		for(int i = 0; i<testSet.size(); i++) {
			System.out.println(testSet.get(i).toString());
		}*/
	}
	
	// Run test part
	public void runTest() {
		String naiveOut, treeOut;
		for(int i=0; i<testSet.size(); i++) {
			naiveOut = naiveObject.getOutput(testSet.get(i));
			naiveOutput.add(naiveOut);
			treeOut = dTreeObject.getOutput(testSet.get(i));
			treeOutput.add(treeOut);
		}
	}
	
	// Calculate accuracy of given our output
	public void calcAccuracy() {
		double naiveCorrectNum = 0, treeCorrectNum = 0, testSize= testSet.size();
		int lastIndex=testSet.get(0).size()-1;
		for(int i=0; i<testSet.size(); i++) {
			if(naiveOutput.get(i).equals(testSet.get(i).get(lastIndex)))
				naiveCorrectNum ++;
			if(treeOutput.get(i).equals(testSet.get(i).get(lastIndex)))
				treeCorrectNum ++;
		}
		System.out.println("Naive Bayes accuracy: " + ((naiveCorrectNum/testSize)*100));
		System.out.println("Desicion Tree accuracy: " + ((treeCorrectNum/testSize)*100));
	}
	
	// Execute the full algorithm 
  	public void execute() {
  		
  		// Step 1: Initialization
  		initializeDataSet();
  		calcAllCategories();
  		print();
  		
  		// Step 2: Naive Bayes part
  		naiveObject = new NaiveBayes(features);
  		
  		// Step 3: Decision tree part
  		dTreeObject = new DecisionTree(trainSet, features, featHeader);
  		
  		// Step 4: Test part
  		runTest();
  		
  		// Step 5: Calculate accuracy
  		calcAccuracy();
  		
		// Step 6: Print final output
		output();
		
  	}
	
  	// Print Features
	public void print() {
		for(int i=0; i<features.size();i++) {
			System.out.println(features.get(i).name + "  " + features.get(i).totalNo + "  " + features.get(i).totalYes);
			for (String s : features.get(i).categories.keySet())
				System.out.println("   "+s + "  " + features.get(i).categories.get(s).no + "  "  + features.get(i).categories.get(s).yes);
		}
	}
  	
	public static void main(String args[]){
		Classification obj = new Classification("Bank_dataset.csv", 100);
		obj.execute();
	}
}