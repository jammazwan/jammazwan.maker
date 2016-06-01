package jammazwan;

import java.util.Map;

import org.apache.camel.impl.DefaultCamelContext;

import jammazwan.replace.ReplacePomDependencies;
import jammazwan.util.Utils;

public class XyzReRunMain {
	UnMarshalExampleProjects unMarshalExampleProjects = new UnMarshalExampleProjects();

	public static void main(String[] args) throws Exception {
		XyzReRunMain xyzReRun = new XyzReRunMain();
		xyzReRun.go();
	}

	private static void buildProject(Map<String, String> replacements) throws Exception{
		replacements.put("projectname", replacements.get("name"));
		replacements.put("rootDir", replacements.get("xyzCode")+"_"+replacements.get("name") + "/");
//		replacements.put("xyzCode", xyzCode);
		replacements.put("pckg", replacements.get("xyzCode"));
		replacements.put("readme", replacements.get("specialInstructions") );
		replacements.put("FileNamePrefix", Utils.upLow(replacements.get("xyzCode")));
		new ReplacePomDependencies().get(replacements);
		GenerateProject.go(replacements);
	}

	private void go() throws Exception {
		DefaultCamelContext context = new DefaultCamelContext();
		context.addRoutes(unMarshalExampleProjects);
		context.start();
		Thread.sleep(5000);
		context.stop();
		System.gc();
		for(Map xmplProject:unMarshalExampleProjects.getExampleProjects()){
			buildProject(xmplProject);
		}
	}

}
