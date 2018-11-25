package GIS;

import Geom.Point3D;

public class My_meta_data implements Meta_data{

	long UTC;
	Point3D orientation;
	public My_meta_data(long UTC, Point3D orientation) {
		super();
		this.UTC = UTC;
		this.orientation = orientation;
	}
	@Override
	public long getUTC() {
		return UTC;
	}

	@Override
	public Point3D get_Orientation() {
		return orientation;
	}
	
	@Override
	public String toString() {
		return "My_meta_data [UTC=" + UTC + ", orientation=" + orientation + "]";
	}
	
}
