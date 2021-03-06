package group.diamonddev.function;


/**
 *  Represents a function for supplying result which can throw an exception.
 * 
 *  @param <T> the type of the result
 *  @param <E> the type of the exception
 * 
 *  @author Diamond
 *  @see Supplier
 */
public interface ThrowableSupplier {

	/**
	 *  Gets a result.
	 * 
	 *  @return a result
	 *  @throws E an exception
	 */
	public Object get();
}
