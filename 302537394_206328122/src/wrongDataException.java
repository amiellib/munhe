/**
 * This exception is for our own exceptions for when the data provided has a bad input
 * we just pass on the message for the exception
 * @author shilo Gilor and Amiel Liberman 
 *
 */
public class wrongDataException extends Exception
{
	public wrongDataException(String message)
	{
		super(message);
	}
}
