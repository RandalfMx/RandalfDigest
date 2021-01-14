/**
 * 
 */
package mx.randalf.digest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

import it.madeword.bookReader.tetml.PreparaTetml;

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

	public SHA1(File fInput, File fileShaSum)
			throws NoSuchAlgorithmException, FileNotFoundException, IOException, InterruptedException {
		super("SHA-1", fInput, null, fileShaSum);
	}

	public SHA1(InputStream fInput)
			throws NoSuchAlgorithmException, FileNotFoundException, IOException, InterruptedException {
		super("SHA-1", fInput);
	}

	@Override
	public String getDigest(File fInput, File fileShaSum) throws IOException, InterruptedException {
		String result = "";
		String[] cmd = null;
		String output = "";
		String[] st = null;

		cmd = new String[4];
		cmd[0] = fileShaSum.getAbsolutePath();
		cmd[1] = "-a";
		cmd[2] = "1";
		cmd[3] = fInput.getAbsolutePath();
		output = PreparaTetml.executeRuntime(cmd);
		if (output != null && output.trim().length() > 0) {
			st = output.split(" ");
			result = st[0];
		}

		return (result == null || result.trim().equals("") ? null : result);
	}

}
