package File_format;

import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Csv2kml csv2kml = new Csv2kml();
//		csv2kml.convertCSVToKML("/Users/shilo/Desktop/testkmlconvert.csv", "/Users/shilo/Desktop/", "kmltest1");		
		System.out.println(csv2kml.multiCSV("/Users/shilo/Downloads/Ex2/Ex2/data").size());
	}
}
