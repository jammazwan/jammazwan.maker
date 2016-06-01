package jammazwan.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReadMeGenerateTest {
	ReadMeGenerate readMeGenerate = new ReadMeGenerate("abc");

	@Test
	public void testRandom() {
		readMeGenerate.start();
		System.out.println(readMeGenerate.getString());
	}

	@Test
	public void testRandomMultiple() {
		for (int i = 0; i < 100; i++) {
			readMeGenerate = new ReadMeGenerate("abc");
			readMeGenerate.start();
			System.out.println(readMeGenerate.getString());
		}
	}

	@Test
	public void testTemplate() {
		readMeGenerate.startType = Options.StartType.PRODUCER_TEMPLATE;
		readMeGenerate.start();

		System.out.println(readMeGenerate.getString());
	}

	@Test
	public void testCsvToObject() {
		readMeGenerate.startType = Options.StartType.FILE;
		readMeGenerate.fileType = Options.InitialFileType.CSV_CRIME;
		readMeGenerate.doWithSourceFile = Options.DoWithSourceFile.SPLIT_AND_MARSHAL_TO_OBJECTS;
		readMeGenerate.start();

		System.out.println(readMeGenerate.getString());
	}

	@Test
	public void testCsvToList() {
		readMeGenerate.startType = Options.StartType.FILE;
		readMeGenerate.fileType = Options.InitialFileType.CSV_CRIME;
		readMeGenerate.doWithSourceFile = Options.DoWithSourceFile.SPLIT_AND_LIST_OF_STRINGS;
		readMeGenerate.start();

		System.out.println(readMeGenerate.getString());
	}

	@Test
	public void testCsvAsIs() {
		readMeGenerate.startType = Options.StartType.FILE;
		readMeGenerate.fileType = Options.InitialFileType.CSV_CRIME;
		readMeGenerate.doWithSourceFile = Options.DoWithSourceFile.LEAVE_AS_IS;
		readMeGenerate.start();

		System.out.println(readMeGenerate.getString());
	}

	@Test
	public void testXmlToObject() {
		readMeGenerate.startType = Options.StartType.FILE;
		readMeGenerate.fileType = Options.InitialFileType.XML_MENU;
		readMeGenerate.doWithSourceFile = Options.DoWithSourceFile.SPLIT_AND_MARSHAL_TO_OBJECTS;
		readMeGenerate.start();
		System.out.println(readMeGenerate.getString());
	}

	@Test
	public void testJsonToObject() {
		readMeGenerate.startType = Options.StartType.FILE;
		readMeGenerate.fileType = Options.InitialFileType.JSON_BANK;
		readMeGenerate.doWithSourceFile = Options.DoWithSourceFile.SPLIT_AND_MARSHAL_TO_OBJECTS;
		readMeGenerate.start();
		System.out.println(readMeGenerate.getString());
		readMeGenerate = new ReadMeGenerate("abc");
		readMeGenerate.startType = Options.StartType.FILE;
		readMeGenerate.fileType = Options.InitialFileType.JSON_BANK;
		readMeGenerate.doWithSourceFile = Options.DoWithSourceFile.SPLIT_AND_LIST_OF_STRINGS;
		readMeGenerate.start();
		System.out.println(readMeGenerate.getString());
	}

	@Test
	public void testXmlAsString() {
		readMeGenerate.startType = Options.StartType.FILE;
		readMeGenerate.fileType = Options.InitialFileType.XML_MENU;
		readMeGenerate.doWithSourceFile = Options.DoWithSourceFile.LEAVE_AS_IS;
		readMeGenerate.start();
		System.out.println(readMeGenerate.getString());
		readMeGenerate = new ReadMeGenerate("abc");
		readMeGenerate.startType = Options.StartType.FILE;
		readMeGenerate.fileType = Options.InitialFileType.XML_MENU;
		readMeGenerate.doWithSourceFile = Options.DoWithSourceFile.SPLIT_AND_LIST_OF_STRINGS;
		readMeGenerate.start();
		System.out.println(readMeGenerate.getString());
	}

	@Test
	public void testJsonAsString() {
		readMeGenerate.startType = Options.StartType.FILE;
		readMeGenerate.fileType = Options.InitialFileType.JSON_STOCK;
		readMeGenerate.doWithSourceFile = Options.DoWithSourceFile.LEAVE_AS_IS;
		readMeGenerate.start();
		System.out.println(readMeGenerate.getString());
		readMeGenerate = new ReadMeGenerate("abc");
		readMeGenerate.startType = Options.StartType.FILE;
		readMeGenerate.fileType = Options.InitialFileType.JSON_STOCK;
		readMeGenerate.doWithSourceFile = Options.DoWithSourceFile.SPLIT_AND_LIST_OF_STRINGS;
		readMeGenerate.start();
		System.out.println(readMeGenerate.getString());
	}

}
