package group.diamonddev.function;


/**
 *  Represents a function which produces result from input arguments and can throw an exception.
 * 
 *  @param <I> the type of the input of the function
 *  @param <R> the type of the result of the function
 *  @param <E> the type of the exception
 * 
 *  @author Diamond
 *  @see Function
 */
public interface ThrowableFunction {

	/**
	 *  Applies this function to the given argument.
	 * 
	 *  @param i an argument
	 * 
	 *  @return the function result
	 *  @throws E an exception
	 */
	public Object apply(Object i);
}
