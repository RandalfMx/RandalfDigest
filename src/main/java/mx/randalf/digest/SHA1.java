/**
 * 
 */
package mx.randalf.digest;

import java.security.NoSuchAlgorithmException;

/**
 * @author massi
 *
 */
public class SHA1 extends Digest {

	/**
	 * @param algorithm
	 * @throws NoSuchAlgorithmException
	 */
	public SHA1() {
		super("SHA-1");
	}

}
