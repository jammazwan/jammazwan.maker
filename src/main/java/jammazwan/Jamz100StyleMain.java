package jammazwan;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import jammazwan.replace.ReplacePomDependencies;
import jammazwan.replace.ReplaceProcessorBeanRefs;
import jammazwan.util.ReadMeGenerate;
import jammazwan.util.ThreeCharOrdinal;
import jammazwan.util.Utils;

public class Jamz100StyleMain {
	static StringBuffer sb = new StringBuffer();
	static int i;

	public static void main(String args[]) throws Exception {
		new Jamz100StyleMain().loop();
	}

	private void loop() throws Exception {
		/*
		 * Super awkward way of avoiding Multiple Consumer on Endpoint
		 * exceptions. Wish I knew a better way than to run a completely new
		 * context every time.
		 */
		List<String> partList = new ThreeCharOrdinal().get("faa", 1);
		for (String xyzCode : partList) {
			Map<String, String> replacements = new HashMap<String, String>();
			ReadMeGenerate readMeGenerate = new ReadMeGenerate(xyzCode);
			replacements.put("projectname", readMeGenerate.getName());
			replacements.put("rootDir", readMeGenerate.getName() + "/");
			replacements.put("xyzCode", xyzCode);
			replacements.put("pckg", xyzCode);
			replacements.put("readme",readMeGenerate.getString() );
			replacements.put("FileNamePrefix", Utils.upLow(xyzCode));
			new ReplacePomDependencies().get(replacements);
			new ReplaceProcessorBeanRefs().get(replacements);
			GenerateProject.go(replacements);
			sb.append("" + i++ +" begin: " + readMeGenerate.getName()+ "\n");
			sb.append(readMeGenerate.getString()+ "\n\n");
		}
		FileWriter fileWriter = new FileWriter(new File("generated/f_batch.txt"));
		fileWriter.write(sb.toString());
		fileWriter.close();
	}
}
