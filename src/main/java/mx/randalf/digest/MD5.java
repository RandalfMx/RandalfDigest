/**
 * 
 */
package mx.randalf.digest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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

	public MD5(File fInput) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		super("MD5", fInput);
	}

}
