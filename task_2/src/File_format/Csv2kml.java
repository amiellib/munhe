package File_format;

import Geom.Point3D;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;


public class Csv2kml {

	public String kmlElement( String SSID, String MAC,String AuthMode,/* String Frequency, String Timestamp ,*/String Date, Point3D point)
	{
		return "\t<Placemark>\n" +
				"\t<name><![CDATA[" + SSID + "]]></name>\n" +
				"\t<description><![CDATA[BSSID: <b>"+MAC+"</b>" +
				"<br/>Capabilities: <b>"+AuthMode+"</b>"+ 
				//                 "<br/>Frequency: <b>" + Frequency + "</b>" +
				//                 "<br/>Timestamp: <b>" + Timestamp + "</b>" +
				"<br/>Date: <b>" + Date + "</b>]]></description><styleUrl>#red</styleUrl>\n"+
				"\t<Point>\n" +
				"\t\t<coordinates>"+point.x()+","+point.y()+","+point.z()+ "</coordinates>\n" +
				"\t</Point>\n" +
				"\t</Placemark>\n";

	}

	public String createKMLFile(String path) throws IOException
	{
		String  kmlmiddle = "";
		String kmlstart = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n"
				+ "<Document><Style id=\"red\"><IconStyle><Icon>"
				+ "<href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href>"
				+ "</Icon></IconStyle>"
				+ "</Style><Style id=\"yellow\"><IconStyle><Icon>"
				+ "<href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png"
				+ "</href></Icon></IconStyle></Style>"
				+ "<Style id=\"green\"><IconStyle><Icon>"
				+ "<href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon>"
				+ "</IconStyle></Style><Folder><name>Wifi Networks</name>";
		BufferedReader br = new BufferedReader(new FileReader(path));
		br.readLine();
		br.readLine();
		String line = br.readLine();
		while (line != null && !line.isEmpty()) {
			if((line.replaceAll(",","")).replaceAll(" ","").isEmpty())
				break;
			String[] row = line.split(",");
			kmlmiddle = kmlmiddle + kmlElement(row[0], row[1], row[2], row[3], new Point3D(Double.parseDouble(row[6]),Double.parseDouble(row[7]),Double.parseDouble(row[8])));
			line = br.readLine();
		}
		String kmlend = "</kml>";

		return kmlstart + kmlmiddle + kmlend;
	}
	public void convertCSVToKML(String path_of_csv,String path_of_kml_end_point, String kml_file_name) throws IOException
	{
		String kmldata = createKMLFile(path_of_csv);
		Writer fwriter;
		try {
			fwriter = new FileWriter(path_of_kml_end_point+kml_file_name+".kml");
			fwriter.write(kmldata);
			fwriter.flush();
			fwriter.close();
		}catch (IOException e1) {
			e1.printStackTrace();
		}   
	}
	public ArrayList<String> multiCSV(String dir) throws IOException
	{
		int i=0;
		ArrayList<String> all_layers = new ArrayList<String>();
		File[] filesInDirectory = new File(dir).listFiles();
		for(File f : filesInDirectory)
		{
			String filePath = f.getAbsolutePath();
			String fileExtenstion = filePath.substring(filePath.lastIndexOf(".") + 1,filePath.length());
			if("csv".equals(fileExtenstion))
			{
				convertCSVToKML(filePath, "/Users/shilo/Desktop/", "kmltest_"+i);
				i++;
				all_layers.add(createKMLFile(filePath));
			}
		}
		return all_layers;
	}
}
