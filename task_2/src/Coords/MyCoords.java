package Coords;

import Geom.Point3D;

public class MyCoords implements coords_converter
{
	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		Point3D cartesian= gps.convert_radians_to_cartesian(gps);
		cartesian.add(local_vector_in_meter);
		gps = cartesian.convert_cartesian_to_radians(cartesian);
		return gps;
	}

	@Override
	public double distance3d(Point3D gps0, Point3D gps1) 
	{		
		Point3D cartesian0= gps0.convert_radians_to_cartesian(gps0);
		Point3D cartesian1= gps0.convert_radians_to_cartesian(gps1);
		return cartesian1.distance3D(cartesian0);
	}

	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) 
	{
		Point3D cartesian0= gps0.convert_radians_to_cartesian(gps0);
		Point3D cartesian1= gps0.convert_radians_to_cartesian(gps1);
		cartesian1.add(-cartesian0.x(), -cartesian0.y(), -cartesian0.z());
		return cartesian1;
	}

	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		double[] my_azimuth_elevation_dist= new  double[3];
		double  dx = gps0.x()- gps1.x();
		double dy = gps0.y() - gps1.y();
		double result = (dx > 0) ? ((Math.PI*0.5) - Math.atan(dy/dx)) :  (dx < 0) ? ((Math.PI*1.5) - Math.atan(dy/dx)) :
			(dy < 0) ? Math.PI : 0;
		my_azimuth_elevation_dist[0] = Math.toDegrees(result);

		//		my_azimuth_elevation_dist[0] = gps1.north_angle(gps0);
		my_azimuth_elevation_dist[2] = distance3d(gps1,gps0);
		my_azimuth_elevation_dist[1] = Math.toDegrees(Math.asin((gps1.z()-gps0.z())/my_azimuth_elevation_dist[2]));
		return my_azimuth_elevation_dist;
	}

	@Override
	public boolean isValid_GPS_Point(Point3D p) 
	{
		return (p.x()<-180 || p.x()>180 || p.y()<-90 || p.y()>90 || p.z()<-450) ? false : true;
	}
}
