/**
 * 
 */
package mx.randalf.digest;

import java.security.NoSuchAlgorithmException;

/**
 * @author massi
 *
 */
public class MD5 extends Digest {

	/**
	 * @param algorithm
	 * @throws NoSuchAlgorithmException
	 */
	public MD5() throws NoSuchAlgorithmException {
		super("MD5");
	}

}
