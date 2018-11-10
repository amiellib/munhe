import java.util.Comparator;
/**
 * This class is a comparator class to help sort objects
 * @author Shilo Gilor and Amiel Liberman
 *
 */
public class Monom_Comperator implements Comparator<Monom> 
{
	/**
	 * The compare function compares the power of the monom 
	 * @param o1 is the first monom to check if is the same as the other monom
	 * @param o2 is the second monom to check if is the same as the other monom
	 * @return the power of o2 - o1
	 */
	@Override
	public int compare(Monom o1, Monom o2) {
		return o2.get_power() - o1.get_power();		
	}
}