/**
 * 
 */
package mx.randalf.digest.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import mx.randalf.digest.MD5;
import mx.randalf.digest.SHA1;
import mx.randalf.digest.SHA256;

/**
 * @author massi
 *
 */
public class DigestTest {

	/**
	 * 
	 */
	public DigestTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MD5 md5 = null;
		SHA1 sha1 = null;
		SHA256 sha256 = null;
		File f = null;
		String value = "";
		
		try {
			f = new File("/Users/massi/Desktop/Lavoro/Sorgenti/Personale/Randalf/RandalfDigest/DEPLOY.txt");
			
			md5 = new MD5(f);
			value = md5.getDigest();
			System.out.println("MD5:\t\t"+value.length()+" -> "+value);
			value = md5.getDigest64Base();
			System.out.println("MD5-64Base:\t"+value.length()+" -> "+value);
			
			sha1 = new SHA1(f);
			value = sha1.getDigest();
			System.out.println("SHA1:\t\t"+value.length()+" -> "+value);
			value = sha1.getDigest64Base();
			System.out.println("SHA1-64Base:\t"+value.length()+" -> "+value);
			
			sha256 = new SHA256(f);
			value = sha256.getDigest();
			System.out.println("SHA256:\t\t"+value.length()+" -> "+value);
			value = sha256.getDigest64Base();
			System.out.println("SHA256-64Base:\t"+value.length()+" -> "+value);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
