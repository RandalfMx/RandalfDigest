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
public class SHA1 extends Digest {

	/**
	 * @param algorithm
	 * @throws NoSuchAlgorithmException
	 */
	public SHA1() {
		super("SHA-1");
	}

	public SHA1(File fInput) throws NoSuchAlgorithmException, FileNotFoundException, IOException, InterruptedException {
		super("SHA-1", fInput, null);
	}

}
