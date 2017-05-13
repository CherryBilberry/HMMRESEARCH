package hiddenMarkovChain;

import java.io.*;
import java.util.*;

public class Cellinfo {
	private static BaumWelchAlg cells;
	private static List<Status> cellinfo;
	private static List<List<String>> temp;

	public static void main(String[] args) {

		cells = new BaumWelchAlg();

		cellinfo = new LinkedList<Status>();

		temp = parse(args[0]);

		
		//transpose the matrix imported from the csv file
		for (int i = 0; i < temp.size(); i++) {
			Status tempStatus = new Status();

			for (List<String> data : temp) {

				
				if (data.get(i).equals("0")){
					
					tempStatus.insert(0);	
				}
				else{
					tempStatus.insert(1);
				}

			}
			tempStatus.printStatus();
			System.out.println("*************");
			cellinfo.add(tempStatus);
		}

		System.out.println("after transpose");
		for (int i = 0; i < cellinfo.get(1).getSize(); i++) {
			System.out.println(i + "th status is " + cellinfo.get(0).getNth(i));
		}

	}

	public static List<List<String>> parse(String file) {
		List<List<String>> dataForm = new ArrayList<List<String>>();
		String line = "";
		String csvSplitBy = ",";

		try (BufferedReader br = new BufferedReader(
				new FileReader(file))) {

			while ((line = br.readLine()) != null) {

				String[] data = line.split(csvSplitBy);
				dataForm.add(Arrays.asList(data));

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}

		int lineNo = 1;
		for (List<String> data : dataForm) {
			int columnNo = 1;
			for (String value : data) {
				System.out.println(
						"Line " + lineNo + " Column " + columnNo + ": " + value);
				columnNo++;
			}
			System.out.println("start of another line");
			lineNo++;
		}
		return dataForm;
	}
}
