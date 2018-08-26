package com.gouge.base.tools;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class EncryptTool {
	
	private static String code = "UTF-8";
	private static String method = "DES";
	
	/**
	 * 生成指定加密算法的密钥�??
	 * 
	 * @param algorithm
	 *            加密算法�?
	 * @return 生成的密钥�??
	 * @throws NoSuchAlgorithmException
	 *             当找不到指定的加密算法时�?
	 */
	public static byte[] generateSecretKey(String algorithm) throws NoSuchAlgorithmException {
		byte[] result = null;

		// 生成�?个可信任的随机数�?
		SecureRandom sr = new SecureRandom();
		// 为我们�?�择的DES算法生成�?个KeyGenerator对象
		KeyGenerator kg;
		kg = KeyGenerator.getInstance(algorithm);
		kg.init(sr);
		// 生成密钥
		SecretKey key = kg.generateKey();
		// 返回密钥的字符串形式
		result = key.getEncoded();

		return result;
	}
	
	/**
	 * 将密钥由字符串形式转换成字节形式�?
	 * 
	 * @param secretKey
	 *            要转换的密钥�?
	 * @return 密钥的字节形式�?�转换使用的字符集不可用时返回null�?
	 */
	public static byte[] getSecretKeyInByteArray(String secretKey) {
		byte[] result = null;

		try {
			result = secretKey.getBytes(code);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	/**
	 * 对明文加密�??
	 * 
	 * @param plainText
	 *            要加密的明文�?
	 * @param key
	 *            密钥�?
	 * @param transformation
	 *            加密算法�?
	 * @return 加过密的密文�?
	 *             当加密过程中出现任何错误时�?�已知的错误有：非法的密钥，未知的加密算法�??
	 */
	private static byte[] encrypt(byte[] plainText, byte[] key, String transformation)
			throws Exception {
		byte[] result = null;

		try {
			// 产生�?个可信任的随机数�?
			SecureRandom sr = new SecureRandom();
			// 从原始密钥数据创建DESKeySpec对象
			DESKeySpec dks = new DESKeySpec(key);
			// 创建�?个密钥工厂，然后用它把DESKeySpec转换成Secret Key对象
			SecretKeyFactory keyFactory = SecretKeyFactory
					.getInstance(transformation);
			SecretKey keySpec = keyFactory.generateSecret(dks);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			// 用密钥初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, sr);
			// 执行加密操作
			result = cipher.doFinal(plainText);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	/**
	 * 对密文解密�??
	 * 
	 * @param cryptoText
	 *            要解密的密文�?
	 * @param key
	 *            密钥�?
	 * @param transformation
	 *            加密算法�?
	 * @return 解密的明文�??
	 *             当解密出错时�?
	 */
	private static byte[] decrypt(byte[] cryptoText, byte[] key, String transformation)
			throws Exception {
		byte[] result = null;

		try {
			// 产生�?个可信任的随机数�?
			SecureRandom sr = new SecureRandom();
			// 从原始密钥数据创建DESKeySpec对象
			DESKeySpec dks = new DESKeySpec(key);
			// 创建�?个密钥工厂，然后用它把DESKeySpec转换成Secret Key对象
			SecretKeyFactory keyFactory = SecretKeyFactory
					.getInstance(transformation);
			SecretKey keySpec = keyFactory.generateSecret(dks);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance(transformation);
			// 用密钥初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, keySpec, sr);
			// 执行解密操作
			result = cipher.doFinal(cryptoText);
		} catch (Exception e) {
			if(e instanceof IllegalStateException || e instanceof IllegalBlockSizeException || e instanceof BadPaddingException){
				throw new Exception("secretKey is not Correct, Decryption failed.", e);
				
			}else{
				throw new Exception("Decryption failed.", e);
			}
		}

		return result;
	}
	
	public static byte[] getBytesFromString(String string){
		if (string == null || string.length()==0 ) {
			return null;
		}
		 byte[] bs = DecConverter.convertStrToBytes(DecConverter.convertBytesToStr(string.getBytes()));
		 return bs;
	}
	
	/**加密字符�?
	 * @param str 原字符串
	 * @param key 密钥
	 * @return 加密后的字符�?
	 * @throws Exception
	 */
	public static String getEnCode(String str, String key) throws Exception {
		String strResult = "";
		strResult =DecConverter.convertBytesToStr(encrypt(str.getBytes(code), getBytesFromString(key), method));//加密
		return strResult;
	}
	
	/**解密字符�?
	 * @param str 密文
	 * @param key 密钥
	 * @return 解密后的字符�?
	 * @throws Exception
	 */
	public static String getDeCode(String str, String key) throws Exception {
		String strResult = "";
		if(str == null || "".equals(str.trim())){
			return strResult;
		}
		strResult = new String(decrypt(DecConverter.convertStrToBytes(str), getBytesFromString(key), method),code);//解密
		return strResult;
	}


}
