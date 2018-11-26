package GIS;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyGIS_layer implements GIS_layer{
	

    Set<GIS_element> my_set = new HashSet<GIS_element>();

	@Override
	public int size() {
		
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return my_set.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return my_set.contains(o);
	}

	@Override
	public Iterator<GIS_element> iterator() {
		return my_set.iterator();
	}

	@Override
	public Object[] toArray() {
		return my_set.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return my_set.toArray(a);
	}

	@Override
	public boolean add(GIS_element e) {
		
		return my_set.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return my_set.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return my_set.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends GIS_element> c) {
		return my_set.addAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return my_set.retainAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return my_set.removeAll(c);
	}

	@Override
	public void clear() {
		my_set.clear();		
	}

	@Override
	public Meta_data get_Meta_data() {
		return null;
	}

}
