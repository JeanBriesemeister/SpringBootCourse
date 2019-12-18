package com.jean.sbc.services.validation.utils;

public class Utils {

	public static boolean isValidSocialInsuranceNumber(String sin) {
		if (sin == null || !sin.matches("\\d{9}") || sin.matches(sin.charAt(0) + "{9}"))
			return false;

		int total = 0;
		int checkDigit = Integer.parseInt(sin.substring(8));
		int digits[] = new int[8];
		String eightDigits = sin.substring(0, 8);

		for (int i = 0; i < digits.length; i++) {
			digits[i] = Integer.parseInt(eightDigits.substring(i, i + 1));
			if (i % 2 == 0) {
				digits[i] = digits[i] * 2 > 9 ? digits[i] / 5 + digits[i] % 5 : digits[i] * 2;
			}
			total += digits[i];
		}

		return ((total / 10 + 1) * 10) - total == checkDigit;
	}

	public static boolean isValidBusinessNumber(String bn) {
		if (bn == null || !bn.matches("\\d{9}") || bn.matches(bn.charAt(0) + "{9}"))
			return false;

		return false;
	}
}
