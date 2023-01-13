package org.example.pathReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PathReader {
    private String start;
    private String end;
    private String abbreviation;
    private String noValueFile;

    public PathReader() {
        initializePath();
    }

    private void initializePath() {
        try (InputStream input = new FileInputStream("src/test/resourcesTest/filesTest.properties")) {
            Properties prop = new Properties();

            prop.load(input);

            start = prop.getProperty("startTest.file");
            end = prop.getProperty("endTest.file");
            abbreviation = prop.getProperty("abbreviationTest.file");
            noValueFile = prop.getProperty("noValueFileTest.file");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getNoValueFile() {
        return noValueFile;
    }
}
