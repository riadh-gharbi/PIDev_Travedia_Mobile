package group.diamonddev.function;


/**
 *  Represents a function which produces an {@code double}-valued result from input argument.
 * 
 *  @author Diamond
 *  @see Function
 *  @since 1.0.0
 */
public interface IntToDoubleFunction {

	/**
	 *  Applies this function to the given argument.
	 * 
	 *  @param value an argument
	 * 
	 *  @return the function result
	 */
	public double applyAsDouble(int value);
}
