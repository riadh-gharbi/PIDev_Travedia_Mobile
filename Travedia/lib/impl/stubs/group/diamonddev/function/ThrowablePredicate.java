package group.diamonddev.function;


/**
 *  Represents a predicate (function with boolean type result) which can throw an exception.
 * 
 *  @param <T> the type of the input to the function
 *  @param <E> the type of the exception
 * 
 *  @author Diamond
 *  @see Predicate
 */
public interface ThrowablePredicate {

	/**
	 *  Tests the value for satisfying predicate.
	 * 
	 *  @param value the value to be tested
	 * 
	 *  @return {@code true} if the value matches the predicate, otherwise {@code false}
	 *  @throws E an exception
	 */
	public boolean test(Object value);
}
