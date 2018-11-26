package com.wangtiansoft.KingDarts.common.utils;

import org.apache.commons.codec.binary.Hex;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

public class FileUtil {

	public static String md5(InputStream fis) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 100KB each time
			byte[] buffer = new byte[102400];
			int length;
			while ((length = fis.read(buffer)) != -1) {
				md.update(buffer, 0, length);
			}
			return new String(Hex.encodeHex(md.digest()));
		} catch (Exception e) {
			return null;
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String md5(File file) {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
			String md5 = md5(inputStream);
			return md5;
		} catch (Exception e) {
			return null;
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
