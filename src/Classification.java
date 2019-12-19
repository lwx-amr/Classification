import java.util.ArrayList;

public class Classification {
	
	// Variables
	private String dataFile; 
    private double dataPercent, trainSize;
    private ArrayList<String> dataHeader, naiveOutput;
    private ArrayList<ArrayList<String>> dataSet, trainSet, testSet;
    private NaiveBayes naiveObject;
	
	// Constructor
	public Classification(String filename, double percent){
		this.trainSize = 0.75;
		this.dataFile = filename;
		this.dataPercent = percent/100;
		this.dataSet = new ArrayList<ArrayList<String>>();
		this.trainSet = new ArrayList<ArrayList<String>>();
		this.testSet = new ArrayList<ArrayList<String>>();
		this.naiveOutput = new ArrayList<String>();
	}
	
	// Initialize data set to be ready to use
	public void initializeDataSet() {
		CSVFileController csvReader = new CSVFileController(dataFile, dataPercent, 4522);
		dataSet = csvReader.readData();
		dataHeader = dataSet.get(0);
		dataSet.remove(0);
		int dataSize = dataSet.size(); //4521
		int trainLines = (int)Math.floor((dataSize)*trainSize); // 3390.5 --> 3390.00
		for(int i = 0; i<dataSize; i++) {
			if(i<trainLines) {
				trainSet.add(dataSet.get(i));
				continue;
			}
			testSet.add(dataSet.get(i));
		}
	}
	
	// Print the needed output
	public void output() {
		System.out.println("\n\n----------------Data Header ---------------------");
		System.out.println(dataHeader.toString());
		
		System.out.println("\n\n----------------Training Set ---------------------");
		for(int i = 0; i<trainSet.size(); i++) {
			System.out.println(trainSet.get(i).toString());
		}
		System.out.println("\n\n----------------Test Set ---------------------");
		for(int i = 0; i<testSet.size(); i++) {
			System.out.println(testSet.get(i).toString());
		}
	}
	
	// Run test part
	public void runTest() {
		String naiveOut;
		for(int i=0; i<testSet.size(); i++) {
			naiveOut = naiveObject.getOutput(testSet.get(i));
			naiveOutput.add(naiveOut);
		}
	}
	
	// Calculate accuracy of given our output
	public void calcAccuracy() {
		double naiveCorrectNum = 0, testSize= testSet.size();
		int lastIndex=testSet.get(0).size()-1;
		for(int i=0; i<testSet.size(); i++) {
			if(naiveOutput.get(i).equals(testSet.get(i).get(lastIndex)))
				naiveCorrectNum ++;
		}
		System.out.println("Naive Bayes accuracy: " + ((naiveCorrectNum/testSize)*100));
	}
	
	// Execute the full algorithm 
  	public void execute() {
  		
  		// Step 1: Initialization
  		initializeDataSet();
  		
  		// Step 2: Naive Bayes part
  		naiveObject = new NaiveBayes(trainSet, dataHeader);
  		
  		// Step 4: Test part
  		runTest();
  		
  		// Step 5: Calculate accuracy
  		calcAccuracy();
  		
		// Step 6: Print final output
		//output();
		
  	}
  	
	public static void main(String args[]){
		Classification obj = new Classification("Bank_dataset.csv", 100);
		obj.execute();
	}
}