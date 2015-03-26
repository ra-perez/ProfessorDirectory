package database;

import java.io.FileNotFoundException;

import org.junit.Test;

public class TestParser {
	Parser p = new Parser();
	@Test
	public void testreadingfile() throws FileNotFoundException {
		p.read();
		
	}

}
