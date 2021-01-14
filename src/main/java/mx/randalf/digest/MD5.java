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
public class MD5 extends Digest {

	/**
	 * @param algorithm
	 * @throws NoSuchAlgorithmException
	 */
	public MD5() throws NoSuchAlgorithmException {
		super("MD5");
	}

	public MD5(File fInput, File fileMd5)
			throws NoSuchAlgorithmException, FileNotFoundException, IOException, InterruptedException {
		super("MD5", fInput, fileMd5, null);
	}

	public MD5(InputStream fInput) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		super("MD5", fInput);
	}

	@Override
	public String getDigest(File fInput, File fileMd5) throws IOException, InterruptedException {
		String result = "";
		OSValidator osValidator = null;
		String[] cmd = null;
		String output = "";
		String[] st = null;

		osValidator = new OSValidator();

		if (osValidator.isMac()) {
			cmd = new String[3];
			cmd[0] = fileMd5.getAbsolutePath();
			cmd[1] = "-r";
			cmd[2] = fInput.getAbsolutePath();
			output = PreparaTetml.executeRuntime(cmd);
			if (output != null && output.trim().length() > 0) {
				st = output.split(" ");
				result = st[0];
			}
		} else if (osValidator.isUnix()) {
			cmd = new String[2];
			cmd[0] = fileMd5.getAbsolutePath();
			cmd[1] = fInput.getAbsolutePath();
			output = PreparaTetml.executeRuntime(cmd);
			if (output != null && output.trim().length() > 0) {
				st = output.split(" ");
				result = st[0];
			}
		}

		return (result == null || result.trim().equals("")? null : result);
	}

}
