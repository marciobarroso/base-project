package com.icodeuplay.base.common.utils;

import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class CryptUtils implements Serializable {

	private static final long serialVersionUID = -2546279875578490652L;

	private String securityKey;
	private BASE64Encoder encoder;
	private BASE64Decoder decoder;
	private SecretKeyFactory secretKeyFactory;
	private PBEParameterSpec pSpec;
	private KeySpec kSpec;
	private SecretKey skey;
	private String algorithm;

	private static Logger LOGGER = LoggerFactory.getLogger(CryptUtils.class);
	
	public CryptUtils(String securityKey) {
		this.securityKey = securityKey;
		try {
			this.algorithm = "PBEWithMD5AndDES";
			this.secretKeyFactory = SecretKeyFactory.getInstance(this.algorithm);
			this.pSpec = new PBEParameterSpec(new byte[] { 3, 1, 4, 1, 5, 9, 2, 6 }, 20);
			this.kSpec = new PBEKeySpec(this.securityKey.toCharArray());
			this.skey = this.secretKeyFactory.generateSecret(this.kSpec);
			this.encoder = new BASE64Encoder();
			this.decoder = new BASE64Decoder();
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (InvalidKeySpecException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	private static MessageDigest static_digester;
	private static BASE64Encoder static_encoder;

	private static void useAlgorithm(String algorithm) throws NoSuchAlgorithmException {
		if (static_digester == null
				|| static_digester.getAlgorithm() != null && !static_digester.getAlgorithm().equals(algorithm)) {
			static_digester = MessageDigest.getInstance(algorithm);
		}

		if (static_encoder == null) {
			static_encoder = new BASE64Encoder();
		}
	}

	public static String encryptyMD5(String value) throws NoSuchAlgorithmException {
		useAlgorithm("MD5");
		byte[] hash = static_digester.digest(value.getBytes());
		return static_encoder.encode(hash);
	}

	public static String encryptySHA256(String value) throws NoSuchAlgorithmException {
		useAlgorithm("SHA256");
		byte[] hash = static_digester.digest(value.getBytes());
		return static_encoder.encode(hash);
	}

	public static String encryptySHA512(String value) throws NoSuchAlgorithmException {
		useAlgorithm("SHA512");
		byte[] hash = static_digester.digest(value.getBytes());
		return static_encoder.encode(hash);
	}

	public String encrypty(final String value) throws NoSuchAlgorithmException {
		try {
			Cipher cipher = Cipher.getInstance(this.algorithm);
			cipher.init(Cipher.ENCRYPT_MODE, this.skey, this.pSpec);
			return this.encoder.encode(cipher.doFinal(value.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(e.getMessage(), e);
			throw new NoSuchAlgorithmException(e);
		} catch (NoSuchPaddingException e) {
			LOGGER.error(e.getMessage(), e);
			throw new NoSuchAlgorithmException(e);
		} catch (InvalidKeyException e) {
			LOGGER.error(e.getMessage(), e);
			throw new NoSuchAlgorithmException(e);
		} catch (InvalidAlgorithmParameterException e) {
			LOGGER.error(e.getMessage(), e);
			throw new NoSuchAlgorithmException(e);
		} catch (IllegalBlockSizeException e) {
			LOGGER.error(e.getMessage(), e);
			throw new NoSuchAlgorithmException(e);
		} catch (BadPaddingException e) {
			LOGGER.error(e.getMessage(), e);
			throw new NoSuchAlgorithmException(e);
		}
	}

	public String decrypty(final String value) throws NoSuchAlgorithmException {
		try {
			Cipher cipher = Cipher.getInstance(this.algorithm);
			cipher.init(Cipher.DECRYPT_MODE, this.skey, this.pSpec);
			return new String(cipher.doFinal(this.decoder.decodeBuffer(value)));
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(e.getMessage(), e);
			throw new NoSuchAlgorithmException(e);
		} catch (NoSuchPaddingException e) {
			LOGGER.error(e.getMessage(), e);
			throw new NoSuchAlgorithmException(e);
		} catch (InvalidKeyException e) {
			LOGGER.error(e.getMessage(), e);
			throw new NoSuchAlgorithmException(e);
		} catch (InvalidAlgorithmParameterException e) {
			LOGGER.error(e.getMessage(), e);
			throw new NoSuchAlgorithmException(e);
		} catch (IllegalBlockSizeException e) {
			LOGGER.error(e.getMessage(), e);
			throw new NoSuchAlgorithmException(e);
		} catch (BadPaddingException e) {
			LOGGER.error(e.getMessage(), e);
			throw new NoSuchAlgorithmException(e);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new NoSuchAlgorithmException(e);
		}
	}

}
