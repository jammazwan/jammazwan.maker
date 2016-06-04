package jammazwan.replace;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import jammazwan.util.Utils;

public class ReplaceProcessorBeanRefs implements Replacement {
	String namePrefix;
	String xyz;

	@Override
	public String get(Map<String, String> replacements) {
		xyz = replacements.get("xyzCode");
		namePrefix = Utils.upLow(xyz);
		boolean isStandalone = (xyz).startsWith("x") ? true : false;
		boolean includesBean = false;
		boolean includesProcessor = false;
		if (isStandalone) {
			includesBean = (replacements.get("include")).contains("bean") ? true : false;
			includesProcessor = (replacements.get("include")).contains("processor") ? true : false;
		}
		StringBuffer sb = new StringBuffer();
		if (!isStandalone || includesBean) {
			replacements.put("beanJavaRef", getBeanJava());
			replacements.put("beanXmlRef", getBeanXml());
			replacements.put("answerCode", getAnswerCode(true));
		} else {
			replacements.put("beanJavaRef", "");
			replacements.put("beanXmlRef", "");
			replacements.put("answerCode", getAnswerCode(false));
		}
		if (!isStandalone || includesProcessor) {
			replacements.put("processorJavaRef", getProcessorJava());
			replacements.put("processorXmlRef", getProcessorXml());
		} else {
			replacements.put("processorJavaRef", "");
			replacements.put("processorXmlRef", "");
		}
		return sb.toString();
	}

	private String getAnswerCode(boolean hasBean) {
		if (hasBean) {
			return "String newAnswer = " + xyz + "Bean.answer(text);";
		} else {
			return "String newAnswer = \"My \" + text;";
		}
	}

	private String getProcessorXml() {
		return "<bean id=\"" + xyz + "Processor\" class=\"jammazwan." + xyz + "." + namePrefix + "Processor\"/>";
	}

	private String getProcessorJava() {
		return "@Autowired private " + namePrefix + "Processor " + xyz + "Processor;";
	}

	private String getBeanXml() {
		return "<bean id=\"" + xyz + "Bean\" class=\"jammazwan." + xyz + "." + namePrefix + "Bean\"/>";
	}

	private String getBeanJava() {
		return "@Autowired private " + namePrefix + "Bean " + xyz + "Bean;";
	}

}
