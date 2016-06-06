package jammazwan;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import jammazwan.util.ReadMeGenerate;
import jammazwan.util.Utils;

public class InputRouteBuilder extends RouteBuilder {
	Map<String, String> replacements;

	public InputRouteBuilder(Map<String, String> replacements) {
		this.replacements = replacements;
	}

	public void configure() {
		String rootDir = replacements.get("rootDir");
		String xyzCode = replacements.get("xyzCode");
		boolean isStandalone = (replacements.get("xyzCode")).startsWith("x") ? true : false;
		boolean includesBean = false;
		boolean includesProcessor = false;
		if(isStandalone){
			includesBean = (replacements.get("include")).contains("bean") ? true : false;
			includesProcessor = (replacements.get("include")).contains("processor") ? true : false;
		}
		Processor velocityProcessor = getVelocityMapProcessor();

		/*
		 * First velocity templates, where substitutions are made
		 */
//		from("file:src/main/resources/velocity?noop=true&delete=false&fileName=README.txt.vm")
//				.process(velocityProcessor).to("velocity://velocity/README.txt.vm").process(getProcessor(rootDir))
//				.to(getFolder());

		from("file:src/main/resources/velocity?noop=true&delete=false&fileName=pom.xml.vm").process(velocityProcessor)
				.to("velocity://velocity/pom.xml.vm").process(getProcessor(rootDir)).to(getFolder());

		from("file:src/main/resources/velocity?noop=true&delete=false&fileName=project.vm").process(velocityProcessor)
				.to("velocity://velocity/project.vm").process(getProcessor(rootDir + ".")).to(getFolder());

		from("file:src/main/resources/velocity?noop=true&delete=false&fileName=camel-context.xml.vm")
				.process(velocityProcessor).to("velocity://velocity/camel-context.xml.vm")
				.process(getProcessor(rootDir + "src/main/resources/META-INF/spring/")).to(getFolder());

		/*
		 * This group consumes 3letter name to rename file
		 */
		from("file:src/main/resources/velocity?noop=true&delete=false&fileName=Routes.java.vm")
				.process(velocityProcessor).to("velocity://velocity/Routes.java.vm")
				.process(getProcessor(rootDir + "src/main/java/jammazwan/" + xyzCode + "/")).to(getFolder());

		from("file:src/main/resources/velocity?noop=true&delete=false&fileName=Test.java.vm").process(velocityProcessor)
				.to("velocity://velocity/Test.java.vm")
				.process(getProcessor(rootDir + "src/test/java/jammazwan/" + xyzCode + "/")).to(getFolder());

		if (!isStandalone || includesBean) {

			from("file:src/main/resources/velocity?noop=true&delete=false&fileName=Bean.java.vm")
					.process(velocityProcessor).to("velocity://velocity/Bean.java.vm")
					.process(getProcessor(rootDir + "src/main/java/jammazwan/" + xyzCode + "/")).to(getFolder());
		}

		if (!isStandalone || includesProcessor) {
			from("file:src/main/resources/velocity?noop=true&delete=false&fileName=Processor.java.vm")
					.process(velocityProcessor).to("velocity://velocity/Processor.java.vm")
					.process(getProcessor(rootDir + "src/main/java/jammazwan/" + xyzCode + "/")).to(getFolder());
		}
		/*
		 * Remaining are straight file copy and rename. Creation of empty
		 * folders done with .gitkeep file, per normal conventions
		 */
		from("file:src/main/resources/velocity?noop=true&delete=false&fileName=gitkeep2.vm")
				.process(getProcessor(rootDir + "src/test/resources/.")).to(getFolder());

		from("file:src/main/resources/velocity?noop=true&delete=false&fileName=classpath.vm")
				.to("velocity://velocity/classpath.vm").process(getProcessor(rootDir + ".")).to(getFolder());

		from("file:src/main/resources/velocity?noop=true&delete=false&fileName=gitkeep3.vm")
				.process(getProcessor(rootDir + "src/main/resources/META-INF/spring/.")).to(getFolder());

		from("file:src/main/resources/velocity?noop=true&delete=false&fileName=log4j.properties.vm")
				.process(getProcessor(rootDir + "src/main/resources/")).to(getFolder());

		from("file:src/main/resources/velocity?noop=true&delete=false&fileName=gitkeep5.vm")
				.process(getProcessor(rootDir + "src/test/java/jammazwan/" + xyzCode + "/.")).to(getFolder());

		from("file:src/main/resources/velocity?noop=true&delete=false&fileName=gitkeep1.vm")
				.process(getProcessor(rootDir + "src/main/java/jammazwan/" + xyzCode + "/.")).to(getFolder());

		if (isStandalone) {
			from("file:src/main/resources/velocity?noop=true&delete=false&fileName=gitignore.vm")
					.process(velocityProcessor).to("velocity://velocity/gitignore.vm")
					.process(getProcessor(rootDir + ".")).to(getFolder());

			from("file:src/main/resources/velocity?noop=true&delete=false&fileName=gitinit.sh.vm")
					.process(velocityProcessor).to("velocity://velocity/gitinit.sh.vm").process(getProcessor(rootDir))
					.to(getFolder());

			from("file:src/main/resources/velocity?noop=true&delete=false&fileName=commit.sh.vm")
					.process(velocityProcessor).to("velocity://velocity/commit.sh.vm").process(getProcessor(rootDir))
					.to(getFolder());

			from("file:src/main/resources/velocity?noop=true&delete=false&fileName=README.md.vm")
					.process(velocityProcessor).to("velocity://velocity/README.md.vm").process(getProcessor(rootDir))
					.to(getFolder());

			from("file:src/main/resources/velocity?noop=true&delete=false&fileName=NOTES.md.vm")
					.process(velocityProcessor).to("velocity://velocity/NOTES.md.vm").process(getProcessor(rootDir))
					.to(getFolder());
		}
	}

	private Processor getProcessor(final String prefix) {
		return new Processor() {
			public void process(Exchange exchange) throws Exception {
				String fileName = "" + exchange.getIn().getHeader("CamelFileName");
				fileName = fileName.substring(0, fileName.length() - 3);
				exchange.getIn().setHeader("CamelFileName",
						constant(prefix + getFileName(replacements.get("xyzCode"), fileName)));
			}
		};
	}

	protected String getFileName(String prefix, String fileName) {
		if (fileName.endsWith("java")) {
			return Utils.upLow(prefix) + fileName;
		} else {
			return fileName;
		}
	}

	private Processor getVelocityMapProcessor() {
		return new Processor() {
			public void process(Exchange exchange) throws Exception {
				// exchange.getIn().setHeader("readme",
				// readMeGenerator.getString());
				// exchange.getIn().setHeader("projectname",
				// readMeGenerator.getName());
				// exchange.getIn().setHeader("pckg", abcPrefix);
				// exchange.getIn().setHeader("FileNamePrefix",
				// Utils.upLow(abcPrefix));
				for (String key : replacements.keySet()) {
					exchange.getIn().setHeader(key, replacements.get(key));
				}
			}
		};
	}

	String getFolder() {
		return "file://generated";
	}

}
