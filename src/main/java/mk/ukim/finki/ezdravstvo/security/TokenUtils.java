package mk.ukim.finki.ezdravstvo.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Hex;

public class TokenUtils {
	public static final String MAGIC_KEY = "obfuscate";

	public static String createToken(UserDetails userDetails) {
		/* Expires in one hour */
		// long expires = System.currentTimeMillis() + 1000L * 60 * 60 * 8;
		StringBuilder tokenBuilder = new StringBuilder();
		tokenBuilder.append(userDetails.getUsername());
		// tokenBuilder.append(":");
		// tokenBuilder.append(expires);
		tokenBuilder.append(":");
		tokenBuilder.append(TokenUtils.computeSignature(userDetails));

		return tokenBuilder.toString();
	}

	public static String computeSignature(UserDetails userDetails) {
		StringBuilder signatureBuilder = new StringBuilder();
		signatureBuilder.append(userDetails.getUsername());
		// signatureBuilder.append(":");
		// signatureBuilder.append(expires);
		signatureBuilder.append(":");
		signatureBuilder.append(userDetails.getPassword());
		signatureBuilder.append(":");
		signatureBuilder.append(TokenUtils.MAGIC_KEY);
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("No MD5 algorithm available!");
		}

		String out= new String(Hex.encode(digest.digest(signatureBuilder.toString()
				.getBytes())));
		System.out.println(out);
		return out;
	}

	public static String getUserNameFromToken(String authToken) {
		if (null == authToken) {
			return null;
		}
		String[] parts = authToken.split(":");
		return parts[0];
	}

	public static boolean validateToken(String authToken,
			UserDetails userDetails) {
		String[] parts = authToken.split(":");
		// long expires = Long.parseLong(parts[1]);
		String signature = parts[1];
		/*
		 * if (expires < System.currentTimeMillis()) { return false; }
		 */
		return signature.equals(TokenUtils.computeSignature(userDetails));
	}
}