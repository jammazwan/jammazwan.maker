package jammazwan.replace;

import java.util.Map;

public class ReplaceXProjectLinks implements Replacement {

	// [**xab_SeedDbFromManyCsvFiles**](https://github.com/jammazwan/xab_SeedDbFromManyCsvFiles)
	//
	// * Uses JPA and biny to seed a database from many different csv files
	// * Uses camel-bindy, camel-jpa, camel-file

	@Override
	public String get(Map<String, String> replacements) {
		StringBuffer sb = new StringBuffer();
		String technology = replacements.get("technology");
		String xyzCode = replacements.get("xyzCode");
		String name = replacements.get("name");
		String desc = replacements.get("desc");
		sb.append("\n\n[**"+xyzCode +"_"+name+"**](https://github.com/jammazwan/"+ xyzCode +"_"+name+")\n\n");
		sb.append(" * " +desc +"\n");
		sb.append(" * Uses " +technology +"\n");
		replacements.put("xlinks", sb.toString());
		return sb.toString();
	}

}
