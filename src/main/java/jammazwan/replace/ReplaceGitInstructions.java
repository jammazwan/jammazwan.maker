package jammazwan.replace;

import java.util.Map;

public class ReplaceGitInstructions implements Replacement {
	/*
	 * // Instructions: _From your workspace directory, git clone both this and
	 * it's sibling, then mvn install it's sibling._ // // _from your bash
	 * shell:_ // // ```bash // cd yourworkspacedirectory // git clone
	 * https://github.com/jammazwan/jammazwan.shared.git // git clone
	 * https://github.com/jammazwan/${headers.xyzCode}_${headers.projectName}.
	 * git // cd jammazwan.shared // mvn install // ``` // Now you may import
	 * this (or both) into your IDE, either via it's pom, or as a native eclipse
	 * project.
	 */

	@Override
	public String get(Map<String, String> replacements) {
		StringBuffer sb = new StringBuffer();
		String include = replacements.get("include");
		String name = replacements.get("xyzCode") + "_" + replacements.get("name");
		boolean hasShared = include.contains("shared");
		if (hasShared) {
			sb.append("#### Install Dependency Project:\n\n");
			sb.append(
					"First, this project requires [jammazwan.shared](https://github.com/jammazwan/jammazwan.shared), if not in your IDE then at least in your mvn repository:\n\n");
			sb.append("You may skip this step if you already imported jammazwan.shared with another x_project!\n\n");
			sb.append("_from your bash shell:_\n\n");
			sb.append("```bash\n");
			sb.append("cd yourworkspacedirectory\n");
			sb.append("git clone https://github.com/jammazwan/jammazwan.shared.git\n");
			sb.append("cd jammazwan.shared\n");
			sb.append("mvn install\n");
			sb.append("\n");
			sb.append("```\n\n");
		}
		sb.append("#### Install This Project:\n\n");
		sb.append("Git clone the " + name + " project into your workspace:\n\n");
		sb.append("_from your bash shell:_\n\n");
		sb.append("```bash\n");
		sb.append("cd yourworkspacedirectory\n");
		sb.append("git clone https://github.com/jammazwan/" + name + ".git\n");
		sb.append("cd " + name + "\n");
		sb.append("mvn install -DskipTests\n");
		sb.append("\n");
		sb.append("```\n\n");
		sb.append("You man now import " + name + " into your IDE.\n");
		if (hasShared) {
			sb.append(
					" Importing [jammazwan.shared](https://github.com/jammazwan/jammazwan.shared) into your IDE is also sometimes helpful, but not required unless it is reading files from src/main/resources - in that case it has to be cloned as a sibling to this project in the workspace.\n\n");
		}
		replacements.put("gitinstructions", sb.toString());
		return sb.toString();
	}

}
