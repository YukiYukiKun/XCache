package edu.jlu.yuki.xcache.utils;

public class CheckUtils {

	/**
	 * Ensure object not null. Used in method entrance to check method
	 * parameters.
	 * 
	 * @param obj
	 * @return not null object
	 * @throws NullPointerException
	 *             if checked object is null
	 */
	public static <T> T checkNotNull(T obj) {
		if (null == obj) {
			throw new NullPointerException();
		}
		return obj;
	}

}
