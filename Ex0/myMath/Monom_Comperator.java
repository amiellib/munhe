package myMath;

import java.util.Comparator;
/**
 * This function compare between the powers of two monoms
 * 
 * return 
 * '0' - When they equals.
 * '+' - when the One on the left is bigger.
 * '-' - when the One  on the right is bigger.
 * @author yoavG eladN.
 * 
 *
 */
public class Monom_Comperator implements Comparator<Monom> 
{

	/**
	 * @param m1 First monom
	 * @param m2 Second monom
	 * @return Positive number - the power of the first monom is bigger. Negative number - the power of the second monom is bigger. zero - equal powers of the monoms.
	 */
	public int compare(Monom m1, Monom m2) 
	{
		return m1.get_power() - m2.get_power();
	}

}
