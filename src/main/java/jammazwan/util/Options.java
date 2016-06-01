package jammazwan.util;

import java.util.Random;

public class Options {
	public StartType randomStartType() {
		return new RandomEnum<StartType>(StartType.class).random();
	}

	public enum StartType {
		FILE, CONSUMER_TEMPLATE, PRODUCER_TEMPLATE, TIMER_DISPATCHED_MESSAGE
	}

	public InitialFileType randomInitialFileType() {
		return new RandomEnum<InitialFileType>(InitialFileType.class).random();
	}

	public enum InitialFileType {
		CSV_CRIME, CSV_STOCK, CSV_SALE, CSV_TRANS, JSON_BANK, JSON_STOCK, JSON_ZIP, XML_CD, XML_PERSON, XML_MENU, XML_PLANT, YOUR_OWN_FILE
	}

	public CXFType randomCXFType() {
		return new RandomEnum<CXFType>(CXFType.class).random();
	}

	public enum CXFType {
		CODE, CONTRACT
	}

	public DoWithSourceFile randomDoWithSourceFile() {
		return new RandomEnum<DoWithSourceFile>(DoWithSourceFile.class).random();
	}

	public enum DoWithSourceFile {
		SPLIT_AND_MARSHAL_TO_OBJECTS, SPLIT_AND_LIST_OF_STRINGS, LEAVE_AS_IS
	}

	public IfSinkFileType randomIfSinkFileType() {
		return new RandomEnum<IfSinkFileType>(IfSinkFileType.class).random();
	}

	public enum IfSinkFileType {
		LEAVE_AS_IS, UNMARSHAL_TO_CSV, UNMARSHAL_TO_XML
	}

	public BodyTransformType randomBodyTransformType() {
		return new RandomEnum<BodyTransformType>(BodyTransformType.class).random();
	}

	public enum BodyTransformType {
		VELOCITY, JAVA_STRING_MANIPULATION, NONE
	}
	public DistributeBodyType randomDistributeBodyType() {
		return new RandomEnum<DistributeBodyType>(DistributeBodyType.class).random();
	}

	public enum DistributeBodyType {
		OBJECT, STRING, XML, CSV, JSON
	}

	public HasEip randomHasEip() {
		return new RandomEnum<HasEip>(HasEip.class).random();
	}

	public enum HasEip {
		TRUE, FALSE
	}

	public AreYouSure randomAreYouSure() {
		return new RandomEnum<AreYouSure>(AreYouSure.class).random();
	}

	public enum AreYouSure {
		YES, NO
	}

	public HasTransform randomHasTransform() {
		return new RandomEnum<HasTransform>(HasTransform.class).random();
	}

	public enum HasTransform {
		TRUE, FALSE
	}

	public EipCalcsType randomEipCalcsType() {
		return new RandomEnum<EipCalcsType>(EipCalcsType.class).random();
	}

	public enum EipCalcsType {
		BODY, HEADERS
	}

	public IfTextTransformType randomIfTextTransformType() {
		return new RandomEnum<IfTextTransformType>(IfTextTransformType.class).random();
	}

	public enum IfTextTransformType {
		MODIFY_OUT, MODIFY_IN
	}


	public FinalDistributionFormat randomFinalDistributionFormat() {
		return new RandomEnum<FinalDistributionFormat>(FinalDistributionFormat.class).random();
	}

	public enum FinalDistributionFormat {
		STRING, XML, CSV, DB, SERIALIZED_OBJECT
	}

	public EIPType randomEIPType() {
		return new RandomEnum<EIPType>(EIPType.class).random();
	}

	public enum EIPType {
		AGGREGATOR, ROUTING_SLIP, DYNAMIC_ROUTE, WIRE_TAP, RECIPIENT_LIST
	}

	public BeanRefType randomBeanRefType() {
		return new RandomEnum<BeanRefType>(BeanRefType.class).random();
	}

	public enum BeanRefType {
		REFERENCE, URI
	}

	public DbType randomDbType() {
		return new RandomEnum<DbType>(DbType.class).random();
	}

	public enum DbType {
		EMBEDDED, REMOTE
	}

	public TestType randomTestType() {
		return new RandomEnum<TestType>(TestType.class).random();
	}

	public enum TestType {
		NO_TEST, MOCKS, INTEGRATION_TEST
	}

	public ContextType randomContextType() {
		return new RandomEnum<ContextType>(ContextType.class).random();
	}

	public enum ContextType {
		JAVA_TEST, JAVA, BLUEPRINT, SPRING
	}
	public TransformWith randomTransformWith() {
		return new RandomEnum<TransformWith>(TransformWith.class).random();
	}

	public enum TransformWith {
		ANONYMOUS_PROCESS, BEAN_AS_REF, BEAN_AS_URI
	}

	public ExceptionsType randomExceptionsType() {
		return new RandomEnum<ExceptionsType>(ExceptionsType.class).random();
	}

	public enum ExceptionsType {
		CATCH_AND_HANDLE, DEAD_LETTER, USE_DEFAULT
	}

	public RoutesType randomRoutesType() {
		return new RandomEnum<RoutesType>(RoutesType.class).random();
	}

	public enum RoutesType {
		XML, JAVA
	}

	public DeployType randomDeployType() {
		return new RandomEnum<DeployType>(DeployType.class).random();
	}

	public enum DeployType {
		NO_DEPLOY, FABRIC, DOCKER, SPRING_BOOT, KARAF, EAP
	}

	public DispatchType randomDispatchType() {
		return new RandomEnum<DispatchType>(DispatchType.class).random();
	}

	public enum DispatchType {
		JMS, DIRECT
	}

	public ExtraCredit randomExtraCredit() {
		return new RandomEnum<ExtraCredit>(ExtraCredit.class).random();
	}

	public enum ExtraCredit {
		TRANSACTIONAL
	}

	public BeanOptionType randomBeanOptionType() {
		return new RandomEnum<BeanOptionType>(BeanOptionType.class).random();
	}

	public enum BeanOptionType {
		ANONYMOUS_CLASS, BEAN_AS_REF, BEAN_AS_URI
	}

	private class RandomEnum<E extends Enum> {

		private final Random RND = new Random();
		private final E[] values;

		public RandomEnum(Class<E> token) {
			values = token.getEnumConstants();
		}

		public E random() {
			return values[RND.nextInt(values.length)];
		}
	}

}
