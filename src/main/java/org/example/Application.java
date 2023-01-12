package org.example;

import org.example.output.Printer;
import org.example.reader.Reader;
import org.example.service.Service;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        app();
    }

    private static void app() {
        Reader reader = new Reader();
        List<String> start = reader.readFile("start.log");
        List<String> end = reader.readFile("end.log");
        List<String> abb = reader.readFile("abbreviations.txt");

        Service service = new Service(start, end, abb);

        List<String> abbreviationNameAndTimeResult = service.getAbbreviationNameAndTimeResult();

        Printer printer = new Printer();
        String result = printer.printResult(abbreviationNameAndTimeResult);

        System.out.println(result);
    }
}
