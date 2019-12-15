import java.util.ArrayList;
//import java.util.Scanner;

public class Classification {
	
	// Variables
	private String dataFile; 
    private double dataPercent, trainSize;
    private ArrayList<String> dataHeader;
    private ArrayList<ArrayList<String>> dataSet, trainSet, testSet;
	//private static Scanner scanner;
	
	// Constructor
	public Classification(String filename, double percent){
		this.trainSize = 0.75;
		this.dataFile = filename;
		this.dataPercent = percent/100;
		this.dataSet = new ArrayList<ArrayList<String>>();
		this.trainSet = new ArrayList<ArrayList<String>>();
		this.testSet = new ArrayList<ArrayList<String>>();
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
	
	// Execute the full algorithm 
  	public void execute() {
  		
  		// Step 1: Initialization
  		initializeDataSet();
			
		
		// Step 4: Print final output
		output();
		
  	}
	
	public static void main(String args[]){
//		scanner = new Scanner(System.in);
//		String fileName;
//		int k;
//		double percent;
//		
//		System.out.print("Filename: " );
//		fileName = scanner.nextLine();
//		
//		System.out.print("Percentage to read: " );
//		percent = scanner.nextDouble();
//		
//		Classification obj = new Classification(fileName, percent);
		Classification obj = new Classification("Bank_dataset.csv", 0.2);
		obj.execute();
	}
}
