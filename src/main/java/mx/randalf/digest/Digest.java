/**
 * 
 */
package mx.randalf.digest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

/**
 * Classe utilizzata per la gestione dei Digest dei files
 * 
 * @author massi
 *
 */
class Digest {

	private String algorithm = null;

	/**
	 * @throws NoSuchAlgorithmException 
	 * 
	 */
	public Digest(String algorithm) {
		this.algorithm = algorithm;
	}

	public String getDigest64Base(String testo) throws NoSuchAlgorithmException{
		return getDigest(testo, true);
	}

	public String getDigest(String testo) throws NoSuchAlgorithmException{
		return getDigest(testo, false);
	}

	private String getDigest(String testo, boolean base64) throws NoSuchAlgorithmException{
		MessageDigest md = null;
		String result = null;

		try {
			md = MessageDigest.getInstance(algorithm);
			md.update(testo.getBytes());
			 
			if (base64){
				result = Base64.encodeBase64String(md.digest());
			} else {
				result = convert(md.digest());
			}
		} catch (NoSuchAlgorithmException e) {
			throw e;
		}

		return result;
	}

	public String getDigest(File fInput) throws NoSuchAlgorithmException, 
			FileNotFoundException, IOException{
		return getDigest(fInput, false);
	}

	public String getDigest64Base(File fInput) throws NoSuchAlgorithmException, 
			FileNotFoundException, IOException{
		return getDigest(fInput, true);
	}

	private String getDigest(File fInput, boolean base64) throws NoSuchAlgorithmException, 
			FileNotFoundException, IOException{
		MessageDigest md = null;
		FileInputStream fis = null;
		String result = null;
		
		try {
			md = MessageDigest.getInstance(algorithm);
			fis = new FileInputStream(fInput);
 
			byte[] dataBytes = new byte[1024];
 
			int nread = 0; 
			while ((nread = fis.read(dataBytes)) != -1) {
			  md.update(dataBytes, 0, nread);
			};
 
			if (base64){
				result = Base64.encodeBase64String(md.digest());
			} else {
				result = convert(md.digest());
			}
		} catch (NoSuchAlgorithmException e) {
			throw e;
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (fis != null){
					fis.close();
				}
			} catch (IOException e) {
				throw e;
			}
		}
        return result;
	}

	private String convert(byte[] mdbytes){
		StringBuffer sb = null;
		String result = null;

		//convert the byte to hex format method 1
		sb = new StringBuffer();
		for (int i = 0; i < mdbytes.length; i++) {
		  sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
		}

		result = sb.toString();
		return result;
	}
}
