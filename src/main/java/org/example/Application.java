package org.example;

import org.example.output.Printer;
import org.example.reader.Reader;
import org.example.service.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class Application {
    public static void main(String[] args) {
        app();
    }

    private static void app() {
        Reader reader = new Reader();

        String pathStart = getPath().get("start");
        String pathEnd = getPath().get("end");
        String pathAbbreviation = getPath().get("abbreviation");
        List<String> start;
        List<String> end;
        List<String> abb;

        try {
            start = reader.readFile(pathStart);
            end = reader.readFile(pathEnd);
            abb = reader.readFile(pathAbbreviation);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<String> nameAndTime = new Service(start, end, abb).getNameAndTime();
        
        String result = new Printer().printResult(nameAndTime);

        System.out.println(result);
    }

    private static HashMap<String, String> getPath() {
        HashMap<String, String> paths = new HashMap<>();

        try (InputStream input = new FileInputStream("src/main/resources/files.properties")) {

            Properties prop = new Properties();

            prop.load(input);

            paths.put("start", prop.getProperty("start.file"));
            paths.put("end", prop.getProperty("end.file"));
            paths.put("abbreviation", prop.getProperty("abbreviation.file"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return paths;
    }
}
