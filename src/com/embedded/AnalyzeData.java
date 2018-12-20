package com.embedded;

/**
 * @author jazy
 *
 */
/* 解析硬件的16位数据报 */
public class AnalyzeData {

	// 将16位16进制数据转换为十进制integer数组
	public static Integer[] hexStringToBtyes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString + " ";
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 3;
		char[] hexChars = hexString.toCharArray();
		System.out.println(hexChars);
		Integer[] d = new Integer[length];
		int j = 0;
		for (int i = 0; i < hexChars.length; i += 3) {

			d[j] = (Integer) (charToByte(hexChars[i]) << 4 | charToByte(hexChars[i + 1]));
			j++;
		}
		return d;
	}

	// 得到传感器的唯一标识码id
	public static String getIdCode(Integer[] a) {

		String s = "";
		for (int i = 0; i < 7; i++) {
			s += a[i];
		}
		return s;

	}

	// 将数组中的数据提取出传感器数据
	public static double[] dataGather(Integer[] a) {
		double[] data = new double[4];
		data[0] = a[7] * 256 + a[8]; // 甲醛浓度
		data[1] = a[9]; // 光强
		data[2] = Double.parseDouble(a[10] + "." + a[11]); // 湿度
		data[3] = Double.parseDouble(a[12] + "." + a[13]); // 温度
		return data;
	}

	// 转换函数
	private static Integer charToByte(char c) {
		return (Integer) "0123456789ABCDEF".indexOf(c);
	}

}