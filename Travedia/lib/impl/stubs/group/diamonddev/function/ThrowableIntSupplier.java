package group.diamonddev.function;


/**
 *  Represents a supplier of {@code int}-valued results.
 * 
 *  @param <E> the type of the exception
 * 
 *  @author Diamond
 *  @see IntSupplier
 *  @since 1.0.0
 */
public interface ThrowableIntSupplier {

	/**
	 *  Gets a result.
	 * 
	 *  @return a result
	 *  @throws E an exception
	 */
	public int getAsInt();
}
