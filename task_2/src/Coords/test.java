package Coords;

import Geom.Point3D;

public class test {

	public static void main(String[] args) {
		MyCoords mycroods = new MyCoords();
		Point3D gps0=new Point3D(32.103315,35.209039,670);
		Point3D gps1=new Point3D(32.106352,35.205225,650);
//		System.out.println(mycroods.distance3d(gps0, gps1));
		
		System.out.println(mycroods.azimuth_elevation_dist(gps0, gps1)[0]);
		System.out.println(mycroods.azimuth_elevation_dist(gps0, gps1)[1]);
		System.out.println(mycroods.azimuth_elevation_dist(gps0, gps1)[2]);

		
	}

}
