package com.example.dialog;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by GYX on 2016/11/22.
 */
public class MD5Util {
	/**
	 * md5加密方法
	 * @param str
	 * @return
	 */
	public static String getStringMD5(String str) {

		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));

			byte[] byteArray = messageDigest.digest();

			StringBuilder md5StrBuff = new StringBuilder();
			for (byte aByteArray : byteArray) {
				if (Integer.toHexString(0xFF & aByteArray).length() == 1)
					md5StrBuff.append("0").append(Integer.toHexString(0xFF & aByteArray));
				else
					md5StrBuff.append(Integer.toHexString(0xFF & aByteArray));
			}

			return md5StrBuff.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	/**
	 * md5加密方法
	 * @param password
	 * @return
	 */
	public static String md5Password(String password) {

		try {
			// 得到一个信息摘要器
			MessageDigest digest = MessageDigest.getInstance("md5");
			byte[] result = digest.digest(password.getBytes());
			StringBuffer buffer = new StringBuffer();
			// 把没一个byte 做一个与运算 0xff;
			for (byte b : result) {
				// 与运算
				int number = b & 0xff;// 加盐
				String str = Integer.toHexString(number);
				if (str.length() == 1) {
					buffer.append("0");
				}
				buffer.append(str);
			}

			// 标准的md5加密后的结果
			return buffer.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}

	}
}
