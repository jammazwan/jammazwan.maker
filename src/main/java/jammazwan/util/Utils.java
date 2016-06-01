package jammazwan.util;

import java.util.StringTokenizer;

public class Utils {
	public static String upLow(String var) {
		if (var == null | var.equals("")) {
			return " ";
		}
		String start = var.substring(0, 1);
		String finish = var.substring(1, var.length()).toLowerCase();
		return start.toUpperCase() + finish;
	}

	public static String lowFirst(String var) {
		if (var == null | var.equals("")) {
			return " ";
		}
		String start = var.substring(0, 1);
		String finish = var.substring(1, var.length());
		return start.toLowerCase() + finish;
	}

	public static String getMethodNameLikeFromDelimited(String var, char delimiter) {
		StringTokenizer stk = null;
		String token = null;
		String returnValue = "";
		boolean first = true;
		stk = new StringTokenizer(var, String.valueOf(delimiter));
		while (stk.hasMoreTokens()) {
			token = stk.nextToken();
			if (first) {
				token = token.substring(0, token.length());
				first = false;
			} else {
				token = token.substring(0, 1).toUpperCase() + token.substring(1, token.length());
			}
			returnValue = returnValue + token;
		}
		return returnValue;
	}
}
