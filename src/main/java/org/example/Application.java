package org.example;

import org.example.output.Printer;
import org.example.reader.Reader;
import org.example.service.Service;

import java.io.IOException;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        app();
    }

    private static void app() {
        Reader reader = new Reader();
        String pathStart = "src/main/files/start.log";
        String pathEnd = "src/main/files/end.log";
        String pathAbbreviation = "src/main/files/abbreviations.txt";
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

        Service service = new Service(start, end, abb);

        List<String> nameAndTime = service.getNameAndTime();

        Printer printer = new Printer();
        String result = printer.printResult(nameAndTime);

        System.out.println(result);
    }
}
