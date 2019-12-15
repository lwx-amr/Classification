import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CSVFileController {

	// Variables
	private String filename;
	private int numOflines;
	private double dataPercent;
	private ArrayList<ArrayList<String>> dataSet;
	
	//Constructor
	public CSVFileController(String filename, double dataPercent, int numOflines){
		this.setFilename(filename);
		this.setNumOflines(numOflines);
		this.setDataPercent(dataPercent);
		this.dataSet = new ArrayList<ArrayList<String>>();
	}
	
	public ArrayList<ArrayList<String>> readData() {
		BufferedReader csvReader;
		try {
			csvReader = new BufferedReader(new FileReader(filename));
			String row;
			int numOfReaded = 0, maxRead = (int)(numOflines*dataPercent);
			ArrayList<String> readedRow;
			
			// Read header of file
			row=csvReader.readLine();
			String[] data = row.split(";");
		    readedRow = new ArrayList<String>(Arrays.asList(data));
		    dataSet.add(readedRow);
			while ((row = csvReader.readLine()) != null && numOfReaded<maxRead) {
				numOfReaded++;
			    data = row.split(";");
			    readedRow = new ArrayList<String>(Arrays.asList(data));
			    dataSet.add(readedRow);
			}
			csvReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataSet;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getNumOflines() {
		return numOflines;
	}

	public void setNumOflines(int numOflines) {
		this.numOflines = numOflines;
	}

	public double getDataPercent() {
		return dataPercent;
	}

	public void setDataPercent(double dataPercent) {
		this.dataPercent = dataPercent;
	}

	public ArrayList<ArrayList<String>> getDataset() {
		return dataSet;
	}

	public void setDataset(ArrayList<ArrayList<String>> dataset) {
		this.dataSet = dataset;
	}
}
