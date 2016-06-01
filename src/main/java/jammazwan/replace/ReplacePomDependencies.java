package jammazwan.replace;

import java.util.Map;

public class ReplacePomDependencies implements Replacement {
	private final String SHARED = "\n<dependency><groupId>jammazwan</groupId><artifactId>shared</artifactId><version>0.0.1-SNAPSHOT</version></dependency>";
	private final String JSON = "\n<dependency><groupId>org.apache.camel</groupId><artifactId>camel-json</artifactId><version>${camel-version}</version></dependency>";

	@Override
	public String get(Map<String, String> replacements) {
		StringBuffer sb = new StringBuffer();
		String technology = replacements.get("technology");
		String xyzCode = replacements.get("xyzCode");
		System.err.println("LOOKING FOR " + xyzCode);
		if (!xyzCode.startsWith("x")) {
			sb.append("<!-- add other dependencies here -->");
		} else {
			sb.append(SHARED);
			if (technology.contains(" json") || technology.startsWith("json ")) {
				sb.append(JSON);
			}
		}
		replacements.put("pomDependencies", sb.toString());
		return sb.toString();
	}

}
