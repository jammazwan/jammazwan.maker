package jammazwan;

import java.util.Map;

import org.apache.camel.impl.DefaultCamelContext;

import jammazwan.replace.ReplaceGitInstructions;
import jammazwan.replace.ReplacePomDependencies;
import jammazwan.replace.ReplaceProcessorBeanRefs;
import jammazwan.replace.ReplaceXProjectGitStatus;
import jammazwan.replace.ReplaceXProjectLinks;
import jammazwan.util.HoldContextOpenUntilDone;
import jammazwan.util.Utils;

public class XyzReRunMain {
	UnMarshalExampleProjects unMarshalExampleProjects = new UnMarshalExampleProjects();
	StringBuffer linksContent = new StringBuffer();
	StringBuffer xgitstatusContent = new StringBuffer();

	public static void main(String[] args) throws Exception {
		XyzReRunMain xyzReRun = new XyzReRunMain();
		xyzReRun.go();
	}

	private void buildProject(Map<String, String> replacements) throws Exception {
		replacements.put("projectname", replacements.get("name"));
		replacements.put("rootDir", replacements.get("xyzCode") + "_" + replacements.get("name") + "/");
		// replacements.put("xyzCode", xyzCode);
		replacements.put("pckg", replacements.get("xyzCode"));
		replacements.put("readme", replacements.get("specialInstructions"));
		replacements.put("FileNamePrefix", Utils.upLow(replacements.get("xyzCode")));
		new ReplacePomDependencies().get(replacements);
		new ReplaceProcessorBeanRefs().get(replacements);
		new ReplaceGitInstructions().get(replacements);
		if (replacements.get("xyzCode").equals("xbo")
				||replacements.get("xyzCode").equals("xbp")
				) {
			GenerateProject.go(replacements);
		}
		linksContent.append(new ReplaceXProjectLinks().get(replacements));
		xgitstatusContent.append(new ReplaceXProjectGitStatus().get(replacements));
	}

	private void go() throws Exception {
		DefaultCamelContext context = new DefaultCamelContext();
		context.addRoutes(unMarshalExampleProjects);
		context.start();
		HoldContextOpenUntilDone.go(context);
		context.stop();
		System.gc();
		for (Map xmplProject : unMarshalExampleProjects.getExampleProjects()) {
			buildProject(xmplProject);
		}
		GenerateXLinks.go(linksContent.toString(), xgitstatusContent.toString());
	}

}
