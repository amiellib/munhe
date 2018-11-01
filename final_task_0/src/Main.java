
public class Main {

	public static void main(String[] args) throws NumberFormatException, wrongDataException 
	{
		
		String string1 = "-x^2+5x-4";
		String string2 = "7x^2+x+5";
//		testing polynom empty constructor
		Polynom poly = new Polynom();
		System.out.println("poly empty constructor " + poly);
		
// 		testing polynom string constructor
		Polynom poly2 = new Polynom(string1);
		System.out.println("poly2 string constructor " + poly2);
		Polynom poly3 = new Polynom(string2);
		System.out.println("poly3 string constructor " + poly3);
		
// 		testing copy constructor
		Polynom poly4 = (Polynom) poly2.copy();
		System.out.println("poly4 copy poly2 constructor " + poly4);
	
//		testing adding new monom
		poly.add(new Monom(3,3));
		poly3.add(new Monom(3,8));
		System.out.println("adding new monom to poly " + poly);
		System.out.println("adding new monom to poly3 " + poly3);
		
//		testing equals
		System.out.println("check if poly2 equals poly3 " + poly2.equals(poly3));
		System.out.println("check if poly2 equals poly4 " + poly2.equals(poly4));
		
//		testing adding polynom
		poly3.add(poly);
		poly3.add(poly2);
		System.out.println("adding poly and poly2 to poly3 " + poly3);
		
// 		testing subtract polynom
		poly3.substract(poly);
		poly3.substract(poly2);
		poly4.substract(poly4.copy());
		System.out.println("subtracting poly and poly2 to poly3 " + poly3);
		System.out.println("subtracting poly4 to poly4 " + poly4);

//		testing multiply polynom
		poly2.multiply(poly3);
		System.out.println("multiplying poly2 and poly3 into poly2" + poly2);

//		testing is zero polynom
		System.out.println("checking if poly4 is zero " + poly4.isZero());
		System.out.println("checking if poly2 is zero " + poly2.isZero());
		
//		testing root function
		System.out.println("root of poly between 3.2 and -5.4 with eps of 0.001 " +poly.root(3.2, -5.4, 0.001));
		System.out.println("root of poly3 between 1 and -5.2 with eps of 0.001 " + poly3.root(1, -5.2, 0.001));
		System.out.println("root of poly2 between 10 and 15.2 with eps of 0.001 " + poly.root(10, 15.2, 0.001));
		
//		testing area
		System.out.println("area of poly between 3.2 and -5.4 with eps of 0.001 " +poly.area(3.2, -5.4, 0.001));

//		testing derivative
		System.out.println("original poly3 " + poly3);
		System.out.println("derivative poly3 " + poly3.derivative());

// 		testing f(x)
		System.out.println("f(4) poly3 " + poly3.f(4));
	}
}
