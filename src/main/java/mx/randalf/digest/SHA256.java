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
public class SHA256 extends Digest {

	/**
	 * @param algorithm
	 * @throws NoSuchAlgorithmException
	 */
	public SHA256() {
		super("SHA-256");
	}

	public SHA256(File fInput) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		super("SHA-256", fInput);
	}
}
