
package myMath;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 * @version 1.0
 */
public class Monom implements function
{
	/**
	 * This is a constructor for Monom, that get two numbers.
	 *  One for the coefficient, and another for the power. 
	 * 
	 * @param a The coefficient of the new Monom.
	 * @param b The power of the new Monom.
	 */
	public Monom(double a, int b)  
	{				

		this.set_coefficient(a);
		this.set_power(b);
		if (b<0)
			throw new RuntimeException("The power can't be negative"); 
	}
	/**
	 * This is a constructor for Monom, that copy from another monom,
	 * the coefficient and the power
	 * 
	 * @param ot The Monom that we will copy from.
	 */
	public Monom(Monom ot) 
	{

		this(ot.get_coefficient(), ot.get_power());
	}
	/**
	 * This is a constructor for Monom, that makes string to Monom.
	 * 
	 * @param str The String will become to Monom
	 */
	public Monom(String str)
	{
		if (!str.contains("x") && !str.contains("X")) //There is no x.
		{
			this.set_coefficient(Double.parseDouble(str));
			this.set_power(0);
		}
		else
		{
			boolean starExist = str.contains("*");
			int x = Math.max(str.indexOf('x'), str.indexOf('X')); //The index of 'x' in the string.

			if (x==0)
				this.set_coefficient(1);
			else if (x==1 && str.charAt(0) == '-')
				this.set_coefficient(-1);
			else
			{
				String start;
				if (starExist)
					start = str.substring(0, x-1);
				else
					start = str.substring(0, x);
				this.set_coefficient(Double.parseDouble(start));
			} 

			if (x==str.length()-1)
				this.set_power(1);
			else
			{
				String end = str.substring(x+2, str.length());
				this.set_power(Integer.parseInt(end));
			}
		}
		
	}
	/**
	 * This function use the comparator to compare between the power of two monoms.  
	 * @param other The monom that compare with this monom
	 * @return Positive number - the power of this monom is bigger. Negative number - the power of other monom is bigger. zero - equal powers of the monoms. 
	 */
	public int compareTo(Monom other)
	{
		Monom_Comperator cmp = new Monom_Comperator();
		return cmp.compare(this, other);
	}
	/**
	 * This function multiplies one monom in another monom.
	 *
	 * @param other The other monom that we will multiply to this monom.
	 */
	public void multiply(Monom other)
	{ 
		if (this.isZero() || other.isZero()) //  If one of them is zero.
		{
			this.set_coefficient(0);
			this.set_power(0);
			return;
		}
		else 
		{
			this.set_coefficient(this._coefficient * other._coefficient);
			this.set_power(this._power + other._power);
		}
	}

	/**
	 * This function add one monom with another monom.
	 * 
	 * @param other The other monom that we will add to this monom.
	 */

	public void add(Monom other)
	{
		if (this._power == other._power) // We can add them only when they have both the same power
			this.set_coefficient(this._coefficient + other._coefficient);
	}

	/**
	 * This function compute a new monom which is the derivative of this monom.

	 * @return New monom which is the derivative of this monom.
	 */
	public Monom derivative()
	{
		if (this._power == 0)
			return new Monom(0,0);
		return new Monom(this._coefficient*this._power,this._power-1);
	}

	/**
	 * This function compute the value of f(x).
	 * 
	 * @return The value of f(x).
	 */
	public double f(double x)
	{
		return Math.pow(x,_power)*_coefficient;
	}
	/**
	 * Setter for a monom coefficient.
	 * @param a The new coefficient for the monom.
	 */
	private void set_coefficient(double a)
	{
		this._coefficient = a;
	}
	/**
	 * Setter for a monom power.
	 * @param p The new power for the monom.
	 */
	private void set_power(int p) 
	{
		this._power = p;
	}

	/**
	 * Getter for a monom coefficient.
	 * @return the coefficient of the monom.
	 */
	public double get_coefficient() 
	{
		return _coefficient;
	}
	/**
	 * Getter for a monom power.
	 * 
	 * @return The coefficient of the monom.
	 */
	public int get_power() 
	{
		return _power;
	}

	/**
	 * This is a boolean function that checks if the monom coefficient is zero
	 *  which make the monom to zero.
	 * 
	 * @return True if the monom is zero False if the monom is not a zero.
	 */
	public boolean isZero() 
	{
		return this.get_coefficient() == 0;
	}

	/**
	 * This function print a string of the monom.
	 * @return String of the monom.
	 */
	public String toString()
	{
		if (this.isZero())
			return "0";
		String s = "";
		if (this.get_power() == 0)
		{
			s += this.get_coefficient();
			return s;
		}
		if (this.get_coefficient() == -1)
			s += "-";
		else if (this.get_coefficient() != 1)
			s += this.get_coefficient() + "*";
		s += "x";
		if (this.get_power() >= 2)
			s += "^" + this.get_power();
		return s;
	}

	/**
	 * This is a boolean function that checks if two monoms are equals.
	 *
	 * @param m1 The monom with which we will examine equality.
	 * @return True if they are equal, and false if they are not.
	 */
	public boolean equals(Monom m1)
	{
		return (this.get_power() == m1.get_power() && this.get_coefficient() == m1.get_coefficient());
	}
// properties
	private double _coefficient;  
	private int _power; 
}
