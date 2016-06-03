package jammazwan.replace;

import java.util.Map;

public class ReplaceXProjectGitStatus implements Replacement {

//	cd ~/work/jammazwan/jammazwan.100
//	echo " "
//	echo "--------------------"
//	pwd
//	git status

	@Override
	public String get(Map<String, String> replacements) {
		StringBuffer sb = new StringBuffer();
		String technology = replacements.get("technology");
		String xyzCode = replacements.get("xyzCode");
		String name = replacements.get("name");
		String desc = replacements.get("desc");
		sb.append("\ncd ~/work/jammazwan/"+xyzCode +"_"+name+"\n");
		sb.append("echo \"--------------------\"\n");
		sb.append("pwd\n");
		sb.append("git status\n");
		replacements.put("xgitstatuscheck", sb.toString());
		return sb.toString();
	}

}
