package Geom;

public class My_geom_element implements Geom_element{
	
	private Point3D my_geom;
	
	public My_geom_element(Point3D my_geom) {
		super();
		this.my_geom = my_geom;
	}
	public My_geom_element(double x,double y,double z) 
	{
		this(new Point3D (x,y,z));
	}
	@Override
	public double distance3D(Point3D p) {
		Point3D cartesian0= p.convert_radians_to_cartesian(p);
		Point3D cartesian1= my_geom.convert_radians_to_cartesian(my_geom);
		return cartesian1.distance3D(cartesian0);
	}

	@Override
	public double distance2D(Point3D p) {
		return distance3D(new Point3D(p.x(),p.y(),my_geom.z()));
	}

	
}
