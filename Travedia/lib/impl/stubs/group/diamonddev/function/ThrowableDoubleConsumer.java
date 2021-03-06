package group.diamonddev.function;


/**
 *  Represents an operation on a {@code double}-valued input argument.
 * 
 *  @param <E> the type of the exception
 * 
 *  @author Diamond
 *  @see DoubleConsumer
 *  @since 1.0.0
 */
public interface ThrowableDoubleConsumer {

	/**
	 *  Performs operation on the given argument.
	 * 
	 *  @param value the input argument
	 * 
	 *  @throws E an exception
	 */
	public void accept(double value);
}
