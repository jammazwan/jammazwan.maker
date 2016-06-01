package jammazwan.util;

public class ReadMeGenerate {
	StringBuffer sb = new StringBuffer();
	StringBuffer name = new StringBuffer();
	Options o = new Options();
	Options.AreYouSure areYouSure = o.randomAreYouSure();
	Options.BeanOptionType beanOptionType = o.randomBeanOptionType();
	Options.BeanRefType beanRefType = o.randomBeanRefType();
	Options.BodyTransformType bodyTransformType = o.randomBodyTransformType();
	Options.ContextType contextType = o.randomContextType();// EXTRA CREDIT
	Options.CXFType cxfType = o.randomCXFType();
	Options.DbType dbType = o.randomDbType();// EXTRA CREDIT
	Options.DeployType deployType = o.randomDeployType();// EXTRA CREDIT
	Options.DispatchType dispatchType = o.randomDispatchType();
	Options.DistributeBodyType distributeBodyType = o.randomDistributeBodyType();
	Options.DoWithSourceFile doWithSourceFile = o.randomDoWithSourceFile();
	Options.EIPType eipType = o.randomEIPType();
	Options.EipCalcsType eipCalcs = o.randomEipCalcsType();
	Options.ExceptionsType exceptionsType = o.randomExceptionsType();// MANDATORY
	Options.ExtraCredit extraCredit = o.randomExtraCredit();// EXTRA CREDIT
	Options.FinalDistributionFormat finalDistributionFormat = o.randomFinalDistributionFormat();
	Options.HasEip hasEIP = o.randomHasEip();
	Options.HasTransform hasTransform = o.randomHasTransform();
	Options.IfSinkFileType ifSinkFileType = o.randomIfSinkFileType();
	Options.IfTextTransformType ifTextTransformType = o.randomIfTextTransformType();
	Options.InitialFileType fileType = o.randomInitialFileType();
	Options.StartType startType = o.randomStartType();
	Options.RoutesType routesType = o.randomRoutesType();
	Options.TestType testType = o.randomTestType();
	Options.TransformWith transformWith = o.randomTransformWith();
	boolean usedDb = false;
	String abcPrefix;

	public ReadMeGenerate(String abcPrefix){
		this.abcPrefix = abcPrefix;
		start();
	}
	

	public String getString() {
		return sb.toString();
	}

	void start() {
		name.append(abcPrefix + "_");
		doGeneralReqs();
		if (startType.equals(Options.StartType.FILE)) {
			name.append("FIL_");
			sb.append("PSEUDOCODE FOR ROUTES\n");
			sb.append("    [1st route]\n");
			sb.append("    - from file:" + fileType + "\n");
			if (doWithSourceFile.equals(Options.DoWithSourceFile.SPLIT_AND_MARSHAL_TO_OBJECTS)) {
				if (("" + fileType).startsWith("CSV")) {
					sb.append("    - unmarshall to [java object model] with bindy \n");
				} else {
					continueWithUnmarshalToObject();
				}
				continueWithObject();
			} else if (doWithSourceFile.equals(Options.DoWithSourceFile.SPLIT_AND_LIST_OF_STRINGS)
					&& ("" + fileType).startsWith("CSV")) {
				sb.append("    - split using camel-csv to List<String> \n");
				continueWithStringRecords();
			} else if ((doWithSourceFile.equals(Options.DoWithSourceFile.SPLIT_AND_LIST_OF_STRINGS)
					|| (doWithSourceFile.equals(Options.DoWithSourceFile.LEAVE_AS_IS)))
					&& (!("" + fileType).startsWith("CSV"))) {
				continueWithFileContents();
			} else if (doWithSourceFile.equals(Options.DoWithSourceFile.LEAVE_AS_IS)
					&& ("" + fileType).startsWith("CSV")) {

				continueWithFileContents();
			} else {
				System.err.println("FAAIL");
			}
		} else if (startType.equals(Options.StartType.CONSUMER_TEMPLATE)) {
			name.append("CSX_");
			sb.append("PSEUDOCODE FOR ROUTES\n");
			sb.append("    [1st route]\n");
			sb.append("    - from cxf.");
			if (cxfType.equals(Options.CXFType.CODE)) {
				sb.append("[code first cxf bean]\n");
			} else {
				sb.append("[contract first cxf bean]\n");
			}
			continueWithObject();
		} else if (startType.equals(Options.StartType.PRODUCER_TEMPLATE)) {
			name.append("TPL_");
			sb.append("PSEUDOCODE FOR ROUTES\n    - template.sendBody([some String by you]) FROM INSIDE JUNIT TEST\n");
			sb.append("    [1st route]\n");
			continueWithStringBody();
		} else if (startType.equals(Options.StartType.TIMER_DISPATCHED_MESSAGE)) {
			sb.append("PSEUDOCODE FOR ROUTES\n");
			sb.append("    [1st route]\n");
			sb.append("    - from timer... with appropriate attributes\n");
			continueWithBean();
		}

	}

	private void continueWithBean() {
		if (transformWith.equals(Options.TransformWith.ANONYMOUS_PROCESS)
				|| transformWith.equals(Options.TransformWith.BEAN_AS_REF)) {
			sb.append("    - begin route with Processor bean, declared in Camel Context as a ref, not a URI \n");
		} else {
			sb.append("    - begin route with Processor bean, declared in Camel Context as a URI, not a ref \n");
		}
		continueWithObject();
	}

	void doGeneralReqs() {
		sb.append("PROJECT REQUIREMENTS\n");
		if (exceptionsType.equals(Options.ExceptionsType.CATCH_AND_HANDLE)) {
			name.append("HND_");
			sb.append("    - in at least one area, catch exceptions and deal with them with a handler\n");
		} else if (exceptionsType.equals(Options.ExceptionsType.USE_DEFAULT)) {
			name.append("DFT_");
			sb.append("    - default to the normal exception handling provided\n");
		} else if (exceptionsType.equals(Options.ExceptionsType.DEAD_LETTER)) {
			name.append("DDL_");
			sb.append("    - in at least one area or process, utilize the Dead Letter approach\n");
		}

		if (testType.equals(Options.TestType.INTEGRATION_TEST)) {
			name.append("NTG_");
			sb.append(
					"    - in at least one area, use an integration test to confirm that actual message or recipients conform to expectations\n");
		} else if (testType.equals(Options.TestType.MOCKS)) {
			name.append("MOC_");
			sb.append("    - use mocks to confirm that recipients and/or messages conform to expectations\n");
		} else if (testType.equals(Options.TestType.NO_TEST)) {
			name.append("WOW_");
			sb.append("    - use the \"act surprised method\" and do no testing of any sort\n");
		}
	}

	void continueWithStringBody() {
		distributeBodyType = Options.DistributeBodyType.STRING;
		sb.append("    - your message body is now a simple string\n");
		if (doTransform()) {
			transformString();
		} else {
			distributeBodyType = Options.DistributeBodyType.STRING;
		}
		distribute();
	}

	void continueWithUnmarshalToObject() {
		if (("" + fileType).startsWith("XML")) {
			sb.append("    - unmarshall to [java object model] with [jaxb or xstream] \n");
		} else if (("" + fileType).startsWith("JSON")) {
			sb.append("    - unmarshall to [java object model] with jackson \n");
		}
	}

	void continueWithFileContents() {
		if (("" + fileType).startsWith("XML")) {
			sb.append("    - your Message body should now consist of XML file contents \n");
			if (doTransform()) {
				transformBody();
			} else {
				distributeBodyType = Options.DistributeBodyType.XML;
			}
		} else if (("" + fileType).startsWith("JSON")) {
			sb.append("    - your Message body should now consist of JSON file contents \n");
			if (doTransform()) {
				transformBody();
			} else {
				distributeBodyType = Options.DistributeBodyType.JSON;
			}
		} else if (("" + fileType).startsWith("CSV")) {
			sb.append("    - your Message body should now consist of CSV file contents \n");
			if (doTransform()) {
				transformBody();
			} else {
				distributeBodyType = Options.DistributeBodyType.JSON;
			}
		}
		distribute();
	}

	void continueWithStringRecords() {
		sb.append("    - your Message body should now consist of a list of delimited strings \n");
		if (doTransform()) {
			transformBody();
		} else {
			distributeBodyType = Options.DistributeBodyType.STRING;
		}
		distribute();
	}

	void continueWithObject() {
		sb.append("    - your Message body should now consist of java object \n");
		if (doTransform()) {
			transformBody();
		} else {
			distributeBodyType = Options.DistributeBodyType.OBJECT;
		}
		distribute();
	}

	void transformBody() {
		transformWith();
		exitTransform();
	}

	private void transformWith() {
		if (transformWith.equals(Options.TransformWith.ANONYMOUS_PROCESS)) {
			sb.append("    - transform body here with anonymous inner class Processor \n");
		} else if (transformWith.equals(Options.TransformWith.BEAN_AS_REF)) {
			sb.append(
					"    - transform body here with Processor bean, declared in Camel Context as a ref, not a URI \n");
		} else {
			sb.append(
					"    - transform body here with Processor bean, declared in Camel Context as a URI, not a ref \n");
		}
	}

	void transformString() {
		if (ifTextTransformType.equals(Options.IfTextTransformType.MODIFY_IN)) {
			sb.append(
					"    - transform the string using a Processor, by modifying the existing string in the IN message\n");
		} else if (ifTextTransformType.equals(Options.IfTextTransformType.MODIFY_OUT)) {
			sb.append(
					"    - transform the string using a Processor, but alter the string to the OUT message, leaving the IN message as is\n");
		}
	}

	void exitTransform() {
		if (distributeBodyType.equals(Options.DistributeBodyType.CSV)) {
			sb.append("    - exit transform with body as a delimited CSV string \n");
		} else if (distributeBodyType.equals(Options.DistributeBodyType.XML)) {
			sb.append("    - exit transform with body as XML \n");
		} else if (distributeBodyType.equals(Options.DistributeBodyType.JSON)) {
			sb.append("    - exit transform with body as JSON \n");
		} else if (distributeBodyType.equals(Options.DistributeBodyType.OBJECT)) {
			sb.append("    - exit transform with body as a java object \n");
		} else if (distributeBodyType.equals(Options.DistributeBodyType.STRING)) {
			sb.append("    - exit transform with body as a string \n");
		}
		areYouSure = o.randomAreYouSure();
	}

	void distribute() {
		if (dispatchType.equals(Options.DispatchType.DIRECT)) {
			name.append("DIR_");
			sb.append("    - to direct endpoint\n    [2nd route]\n    - from direct endpoint\n");
		} else if (dispatchType.equals(Options.DispatchType.JMS)) {
			name.append("JMS_");
			sb.append("    - to JMS endpoint\n    [2nd route]\n    - from JMS endpoint\n");
		}
		if (hasEIP.equals(Options.HasEip.TRUE) && areYouSure.equals(Options.AreYouSure.YES)) {
			name.append("EIP_");
			if (eipType.equals(Options.EIPType.AGGREGATOR)) {
				sb.append(
						"    - use the Aggregator EIPattern to create dispatch(s) by some kind of reasonable logic\n");
			} else if (eipType.equals(Options.EIPType.DYNAMIC_ROUTE)) {
				sb.append(
						"    - use the Dynamic Route EIPattern to create dispatch(s) by some kind of reasonable logic\n");
			} else if (eipType.equals(Options.EIPType.RECIPIENT_LIST)) {
				sb.append(
						"    - use the Recipient List EIPattern to create dispatch(s) by some kind of reasonable logic\n");
			} else if (eipType.equals(Options.EIPType.ROUTING_SLIP)) {
				sb.append(
						"    - use the Routing Slip EIPattern to create dispatch(s) by some kind of reasonable logic\n");
			} else if (eipType.equals(Options.EIPType.WIRE_TAP)) {
				sb.append("    - use the Wire-Tap EIPattern to log a relevant comment about this message\n");
			}

			if (eipCalcs.equals(Options.EipCalcsType.BODY) && !(eipType.equals(Options.EIPType.WIRE_TAP))) {
				sb.append("    - AND use body of your message as part of this 'reasonable logic\n");
			} else if (!(eipType.equals(Options.EIPType.WIRE_TAP))) {
				sb.append("    - consume or modify headers of your message as part of this 'reasonable logic'\n");
			}
		} else {
			name.append("NIP_");
			if (distributeBodyType.equals(Options.DistributeBodyType.STRING)) {
				if (dispatchType.equals(Options.DispatchType.JMS)) {
					sb.append("    - send this message via JMS to something that sounds important\n");
				} else {
					sb.append("    - FTP this to a named folder, using the file name of 'Important', suffixed with a timestamp, and '.txt'\n");
				}
			} else if (distributeBodyType.equals(Options.DistributeBodyType.OBJECT)) {
				if (ifSinkFileType.equals(Options.IfSinkFileType.LEAVE_AS_IS)) {
					sb.append("    - use JPA to persist object into database\n");
					usedDb = true;
				} else if (ifSinkFileType.equals(Options.IfSinkFileType.UNMARSHAL_TO_CSV)) {
					sb.append("    - unmarshal object(s) into CSV format\n");
					sb.append("    - FTP into named folder\n");
				} else if (ifSinkFileType.equals(Options.IfSinkFileType.UNMARSHAL_TO_XML)) {
					sb.append("    - unmarshal object(s) into XML format\n");
					sb.append("    - file into named folder\n");
				}
			} else if (distributeBodyType.equals(Options.DistributeBodyType.CSV)
					|| distributeBodyType.equals(Options.DistributeBodyType.JSON)
					|| distributeBodyType.equals(Options.DistributeBodyType.XML)) {
				sb.append("    - FTP file to a specified folder\n");
				sb.append("    - log as FTP'd to a folder and identify name of file in the log\n");
			}
		}
		extraCredit();
	}

	void extraCredit() {
		sb.append("FOR EXTRA CREDIT:\n");
		if (contextType.equals(Options.ContextType.BLUEPRINT)) {
			sb.append("    - modify to create your camel contex in blueprint.xml\n");
		} else if (contextType.equals(Options.ContextType.JAVA)) {
			sb.append("    - modify to create your camel contex in java\n");
		} else if (contextType.equals(Options.ContextType.JAVA_TEST)) {
			sb.append("    - modify to create your camel contex in java, within your testt\n");
		} else if (contextType.equals(Options.ContextType.SPRING)) {
			sb.append("    - modify to use spring for your camel context, if not already in spring\n");
		}
		if (usedDb) {
			sb.append("    - modify to use a remote or embedded db, whichever you did not use for your first pass\n");
		}
		if (deployType.equals(Options.DeployType.DOCKER)) {
			sb.append("    - add a script, or manually if only option, deploying to a Docker engine\n");
		} else if (deployType.equals(Options.DeployType.EAP)) {
			sb.append("    - add a script, or manually if only option, deploying to a EAP\n");
		} else if (deployType.equals(Options.DeployType.FABRIC)) {
			sb.append("    - add a script, or manually if only option, deploying to Fabric8\n");
		} else if (deployType.equals(Options.DeployType.KARAF)) {
			sb.append("    - add a script, or manually if only option, deploying to a Karaf\n");
		}
		if (routesType.equals(Options.RoutesType.JAVA)) {
			sb.append("    - write your routes in java, unless you already did in your first pass\n");
		} else if (routesType.equals(Options.RoutesType.XML)) {
			sb.append("    - write your routes in xml, unless you already did in your first pass\n");
		}
		sb.append("\n\n");
	}

	// used to make this a 25% rather than 50% probability
	boolean doTransform() {
		return (hasTransform.equals(Options.HasTransform.TRUE) && areYouSure.equals(Options.AreYouSure.YES));
	}

	public String getName() {
		String myName = name.toString();
		myName = myName.substring(0, myName.length() - 1);
		return myName;
	}

}
