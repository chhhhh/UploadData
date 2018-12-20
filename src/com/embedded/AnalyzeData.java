package com.embedded;

/**
 * @author jazy
 *
 */
/* ����Ӳ����16λ���ݱ� */
public class AnalyzeData {

	// ��16λ16��������ת��Ϊʮ����integer����
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

	// �õ���������Ψһ��ʶ��id
	public static String getIdCode(Integer[] a) {

		String s = "";
		for (int i = 0; i < 7; i++) {
			s += a[i];
		}
		return s;

	}

	// �������е�������ȡ������������
	public static double[] dataGather(Integer[] a) {
		double[] data = new double[4];
		data[0] = a[7] * 256 + a[8]; // ��ȩŨ��
		data[1] = a[9]; // ��ǿ
		data[2] = Double.parseDouble(a[10] + "." + a[11]); // ʪ��
		data[3] = Double.parseDouble(a[12] + "." + a[13]); // �¶�
		return data;
	}

	// ת������
	private static Integer charToByte(char c) {
		return (Integer) "0123456789ABCDEF".indexOf(c);
	}

}