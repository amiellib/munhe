package GIS;

import Geom.Geom_element;
import Geom.My_geom_element;
import Geom.Point3D;

public class My_GIS_element implements GIS_element{
	
	private String MAC , AuthMode , FirstSeen , Channel , RSSI , AccuracyMeters , Type;
	My_geom_element geom;
	My_meta_data data;
	

	public My_GIS_element(My_geom_element geom, My_meta_data data, String mAC, String authMode, String firstSeen, String channel, String rSSI,
			String accuracyMeters, String type) {
		super();
		MAC = mAC;
		AuthMode = authMode;
		FirstSeen = firstSeen;
		Channel = channel;
		RSSI = rSSI;
		AccuracyMeters = accuracyMeters;
		Type = type;
		this.geom = geom;
		this.data = data;
	}

	@Override
	public Geom_element getGeom() {
		return geom;
	}

	@Override
	public Meta_data getData() {
		return data;
	}

	@Override
	public void translate(Point3D vec) {
		// TODO Auto-generated method stub
		
	}

	public Point3D getMy_geom() {
		return null;
	}

	public String getMAC() {
		return MAC;
	}

	public String getAuthMode() {
		return AuthMode;
	}

	public String getFirstSeen() {
		return FirstSeen;
	}

	public String getChannel() {
		return Channel;
	}

	public String getRSSI() {
		return RSSI;
	}

	public String getAccuracyMeters() {
		return AccuracyMeters;
	}

	public String getType() {
		return Type;
	}


}
