package GIS;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyGIS_project implements GIS_project{

    Set<GIS_layer> my_layer = new HashSet<GIS_layer>();

	@Override
	public int size() {
		return my_layer.size();
	}

	@Override
	public boolean isEmpty() {
		return my_layer.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return my_layer.contains(o);
	}

	@Override
	public Iterator<GIS_layer> iterator() {
		return my_layer.iterator();
	}

	@Override
	public Object[] toArray() {
		return my_layer.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return my_layer.toArray(a);
	}

	@Override
	public boolean add(GIS_layer e) {
		return my_layer.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return my_layer.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return my_layer.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends GIS_layer> c) {
		return my_layer.addAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return my_layer.retainAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return my_layer.removeAll(c);
	}

	@Override
	public void clear() {
		my_layer.clear();
	}

	@Override
	public Meta_data get_Meta_data() {
		return null;
	}

}
