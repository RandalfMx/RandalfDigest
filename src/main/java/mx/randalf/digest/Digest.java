/**
 * 
 */
package mx.randalf.digest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

/**
 * Classe utilizzata per la gestione dei Digest dei files
 * 
 * @author massi
 *
 */
abstract class Digest {

	private String algorithm = null;
	private String sDiget = null;
	private byte[] digest = null;

	/**
	 * @throws NoSuchAlgorithmException
	 * 
	 */
	public Digest(String algorithm) {
		this.algorithm = algorithm;
	}

	public Digest(String algorithm, File fInput, File fileMd5, File fileShaSum)
			throws NoSuchAlgorithmException, FileNotFoundException, IOException, InterruptedException {
		this.algorithm = algorithm;

		digest = null;
		if (fileMd5 != null && fileMd5.exists()) {
			sDiget = getDigest(fInput, fileMd5);
		}
		if (fileShaSum != null && fileShaSum.exists()) {
			sDiget = getDigest(fInput, fileShaSum);
		}
		if (sDiget == null) {
			digest = getDigest(fInput);
		}
	}

	public abstract String getDigest(File fInput, File fileMd5) throws IOException, InterruptedException;

	public Digest(String algorithm, InputStream fInput)
			throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		this.algorithm = algorithm;
		digest = getDigest(fInput);
	}

	public String getDigest64Base(String testo) throws NoSuchAlgorithmException {
		return getDigest(testo, true);
	}

	public String getDigest(String testo) throws NoSuchAlgorithmException {
		return getDigest(testo, false);
	}

	private String getDigest(String testo, boolean base64) throws NoSuchAlgorithmException {
		MessageDigest md = null;
		String result = null;

		try {
			md = MessageDigest.getInstance(algorithm);
			md.update(testo.getBytes());

			if (base64) {
				result = new String(Base64.encodeBase64(convert(md.digest()).getBytes()));
			} else {
				result = convert(md.digest());
			}
		} catch (NoSuchAlgorithmException e) {
			throw e;
		}

		return result;
	}

	public String getDigest() throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		return (sDiget == null ? convert(digest) : sDiget);
	}

	public String getDigest64Base() throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		try {
			return new String(Base64.encodeBase64(convert(digest).getBytes()));
		} catch (Exception e) {
			return null;
		}
	}

	private byte[] getDigest(File fInput) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		MessageDigest md = null;
		FileInputStream fis = null;
		byte[] result = null;

		try {
			md = MessageDigest.getInstance(algorithm);
			fis = new FileInputStream(fInput);

			byte[] dataBytes = new byte[1024];

			int nread = 0;
			while ((nread = fis.read(dataBytes)) != -1) {
				md.update(dataBytes, 0, nread);
			}
			;

			result = md.digest();
		} catch (NoSuchAlgorithmException e) {
			throw e;
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				throw e;
			}
		}
		return result;
	}

	private byte[] getDigest(InputStream fInput) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		MessageDigest md = null;
		byte[] result = null;

		try {
			md = MessageDigest.getInstance(algorithm);

			byte[] dataBytes = new byte[1024];

			int nread = 0;
			while ((nread = fInput.read(dataBytes)) != -1) {
				md.update(dataBytes, 0, nread);
			}
			;

			result = md.digest();
		} catch (NoSuchAlgorithmException e) {
			throw e;
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
		return result;
	}

	private String convert(byte[] mdbytes) {
		StringBuffer sb = null;
		String result = null;

		// convert the byte to hex format method 1
		sb = new StringBuffer();
		for (int i = 0; i < mdbytes.length; i++) {
			sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
		}

		result = sb.toString();
		return result;
	}

	public String checkSum(byte[] testo) throws NoSuchAlgorithmException {
		MessageDigest complete = null;
		String result = "";
		byte[] b = null;

		try {
			complete = MessageDigest.getInstance(algorithm);
			complete.update(testo);
			b = complete.digest();

			for (int i = 0; i < b.length; i++) {
				result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
			}
		} catch (NoSuchAlgorithmException e) {
			throw e;
		}
		return result;
	}
}
