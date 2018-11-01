import static org.junit.jupiter.api.Assertions.*;


class Test {
	@org.junit.jupiter.api.Test
	void testPolynom() {
		try {
			new Polynom();	      
		} catch (IllegalArgumentException e) {  
			fail("Exception was expected for null input");
		}
	}

	@org.junit.jupiter.api.Test
	void testPolynomPolynomAndTestPolynomString() {
		try {
			Polynom poly = new Polynom("3x^2+2x+4");
			new Polynom(poly);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			fail("failed on format of number");
			e1.printStackTrace();
		} catch (wrongDataException e1) {
			fail("failed on format of data");
			e1.printStackTrace();
		}
		try {
			Polynom poly = new Polynom("3x^2+2x+asd4");
			fail("failed on format of number");
			fail("failed on format of data");
			new Polynom(poly);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (wrongDataException e1) {
			e1.printStackTrace();
		}
	}

	@org.junit.jupiter.api.Test
	void testAddPolynom_able() {
		try {
			Polynom poly = new Polynom("3x^2+2x+4");
			Polynom poly2 = new Polynom("7x^2+x+5");
			poly.add(poly2);
			assertEquals("[10.0*X^2, 3.0*X, 9.0]" , poly.toString());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			fail("bad input");
			e.printStackTrace();
		} catch (wrongDataException e) {
			// TODO Auto-generated catch block
			fail("bad input");
			e.printStackTrace();
		}
	}

	@org.junit.jupiter.api.Test
	void testAddMonom() {
		Polynom poly = new Polynom();
		try {
			poly.add(new Monom(5,5));
			assertEquals("[5.0*X^5]" , poly.toString());
		} catch (wrongDataException e) {
			// TODO Auto-generated catch block
			fail("bad input");
			e.printStackTrace();
		}
		try {
			poly.add(new Monom(4,-1));
			fail("bad input");
		} catch (wrongDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

	@org.junit.jupiter.api.Test
	void testSubstract() {
		try {
			Polynom poly = new Polynom("3x^2+2x+4");
			Polynom poly2 = new Polynom("7x^2+x+5");
			poly.substract(poly2);
			assertEquals("[-4.0*X^2, X, -1.0]" , poly.toString());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			fail("bad input");
			e.printStackTrace();
		} catch (wrongDataException e) {
			// TODO Auto-generated catch block
			fail("bad input");
			e.printStackTrace();
		}	
	}

	@org.junit.jupiter.api.Test
	void testMultiply() {
		try {
			Polynom poly = new Polynom("3x^2+2x+4");
			Polynom poly2 = new Polynom("7x^2+x+5");
			poly.multiply(poly2);
			assertEquals("[21.0*X^4, 17.0*X^3, 45.0*X^2, 14.0*X, 20.0]" , poly.toString());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			fail("bad input");
			e.printStackTrace();
		} catch (wrongDataException e) {
			// TODO Auto-generated catch block
			fail("bad input");
			e.printStackTrace();
		}		}

	@org.junit.jupiter.api.Test
	void testEqualsPolynom_able() {
		try {
			Polynom poly = new Polynom("3x^2+2x+4");
			Polynom poly2 = new Polynom("3x^2+2x+4");
			Polynom poly3 = new Polynom("3x^2+4");
			assertEquals(true, poly.equals(poly2));
			assertEquals(false, poly.equals(poly3));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			fail("bad input");
			e.printStackTrace();
		} catch (wrongDataException e) {
			// TODO Auto-generated catch block
			fail("bad input");
			e.printStackTrace();
		}		}

	@org.junit.jupiter.api.Test
	void testIsZero() {
		Polynom poly = new Polynom();
		assertEquals(true, poly.isZero());
		try {
			Polynom poly2 = new Polynom("3x^2+2x+4");
			assertEquals(false, poly2.isZero());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			fail("bad input");
			e.printStackTrace();
		} catch (wrongDataException e) {
			// TODO Auto-generated catch block
			fail("bad input");
			e.printStackTrace();
		}
	}

	@org.junit.jupiter.api.Test
	void testRoot() {
		try {
			Polynom poly2 = new Polynom("x^2-5x+4");
			double valueOfX = poly2.root(0, 2, 0.1);
			assertTrue(0.9 <= valueOfX && 1.1 >= valueOfX);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			fail("bad input");
			e.printStackTrace();
		} catch (wrongDataException e) {
			// TODO Auto-generated catch block
			fail("bad input");
			e.printStackTrace();
		}	}

	@org.junit.jupiter.api.Test
	void testCopy() {
		try {
			Polynom poly = new Polynom("x^2-5x+4");
			Polynom poly2 = (Polynom) poly.copy();
			assertEquals(poly.toString(), poly2.toString());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			fail("bad input");
			e.printStackTrace();
		} catch (wrongDataException e) {
			// TODO Auto-generated catch block
			fail("bad input");
			e.printStackTrace();
		}	}

	@org.junit.jupiter.api.Test
	void testDerivative() {
		try {
			Polynom poly = new Polynom("x^2-5x+4");
			assertEquals("[2.0*X, -5.0]", (poly.derivative()).toString());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			fail("bad input");
			e.printStackTrace();
		} catch (wrongDataException e) {
			// TODO Auto-generated catch block
			fail("bad input");
			e.printStackTrace();
		}	}

	@org.junit.jupiter.api.Test
	void testArea() {
		try {
			Polynom poly = new Polynom("-x^2+5x-4");
			double valueOfArea = poly.area(3, 4, 0.01);
			assertTrue(1.17 <= valueOfArea && 1.19 >= valueOfArea);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			fail("bad input");
			e.printStackTrace();
		} catch (wrongDataException e) {
			// TODO Auto-generated catch block
			fail("bad input");
			e.printStackTrace();
		}	}


	@org.junit.jupiter.api.Test
	void testToString() {
		Polynom poly = new Polynom();
		try {
			poly.add(new Monom(5,5));
			assertEquals("[5.0*X^5]" , poly.toString());
		} catch (wrongDataException e) {
			// TODO Auto-generated catch block
			fail("bad input");
			e.printStackTrace();
		}
	}

	@org.junit.jupiter.api.Test
	void testF() {
		try {
			Polynom poly = new Polynom("-x^2+5x-4");
			assertEquals(2 , poly.f(2));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			fail("bad input");
			e.printStackTrace();
		} catch (wrongDataException e) {
			// TODO Auto-generated catch block
			fail("bad input");
			e.printStackTrace();
		}
	}

}
