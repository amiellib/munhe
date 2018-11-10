
/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Shilo Gilor and Amiel Liberman
 *
 */
public class Monom implements function
{
	/**
	 * This is the coontractor for monom which defines a monom
	 * @param a is the coefficient given as created
	 * @param b is the power given as created
	 * @throws wrongDataException  when given data is not compatible
	 */
	public Monom(double a, int b) throws wrongDataException
	{
		if (b<0)
		{
			throw new wrongDataException("A monom can not have a negitive power =  " + b);
		}
		else
		{
			this.set_coefficient(a);
			this.set_power(b);
		} 
	}
	/**
	 * this constructor is a copy constructor, it is given a monom to copy
	 * @param ot is the monom to copy
	 * @throws wrongDataException when given data is not compatible 
	 */
	public Monom(Monom ot) throws wrongDataException 
	{
		this(ot.get_coefficient(), ot.get_power());
	}
	/**
	 * getter for _coefficient
	 * @return _coefficient
	 */
	public double get_coefficient() {
		return _coefficient;
	}
	/**
	 * getter for _power
	 * @return _power
	 */
	public int get_power() {
		return _power;
	}
	/**
	 * when monom have the same power so we add the coefficient to the other coefficient
	 * this condition should always be true if the polynom class was built correctly
	 * @param monom given for adding
	 * @throws wrongDataException when given data is not compatible
	 */
	public void add(Monom monom) throws wrongDataException
	{
		if (monom._power == _power)
		{
			set_coefficient(_coefficient + monom._coefficient);
		}
		else
		{
			throw new wrongDataException ("You can not add 2 monoms with different powers");
		}
	}
	/**
	 * 
	 * @param monom is the given monom to compare to this monom
	 * @return true if they are the same and false otherwise
	 */
	public boolean equals(Monom monom)
	{
		return (monom.get_power() != _power || monom.get_coefficient() != _coefficient) ? false : true;
	}
	/**
	 * the f function calculates the function value f(x) for a given x 
	 * @param x is a given x to calculate is y value
	 * @return the f(x) value
	 */
	@Override
	public double f(double x) {
		return _coefficient*(Math.pow(x,_power));
	} 
	/**
	 * This derivative function calculates the derivative for this monom and return it
	 * @return The derivative of this monom 
	 */
	public Monom derivative() 
	{	
		try {
			return (_power>0) ? new Monom(_coefficient*_power,_power-1) : null;
		} catch (wrongDataException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * @param ot another monom to do the multiply calculation
	 * @return The new monom after calculating the multiply
	 */
	public Monom multiply(Monom ot) 
	{	
		try {
			return new Monom(_coefficient*ot._coefficient , _power+ot._power);
		} catch (wrongDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * This function is helpful for printing
	 * @return the string for printing
	 */
	@Override
	public String toString() {
	//	return  " " + _coefficient + "*X^" + _power;
		return (_coefficient==0) ? "" :(_power==0 && _coefficient == 1) ? "" +_coefficient :(_power==0) ? "" + ((_coefficient == 1) ? "" :_coefficient ): (_power == 1) ? "" + ((_coefficient == 1) ? "X" :_coefficient + "*X") : "" + ((_coefficient == 1) ? "X^" : _coefficient + "*X^") + _power;
	}

	//****************** Private Methods and Data *****************
	
	private void set_coefficient(double a){
		this._coefficient = a;
	}
	private void set_power(int p) {
		this._power = p;
	}
	private double _coefficient; 
	private int _power;
}
