package org.example.output;

import org.example.model.Racer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Printer {
    public String printResult(List<String> nameAndTime) {
        StringBuilder stringResult = new StringBuilder();

        HashMap<String, Integer> mapOfMillisecondsAndValue = makeMap(nameAndTime);
        List<Map.Entry<String, Integer>> sorted = sortByMillisecond(mapOfMillisecondsAndValue);

        sorted.forEach(x -> {
            int index = sorted.indexOf(x) + 1;
            String line = makeLine(x.getKey(), index);
            stringResult.append(line);

        });
        return stringResult.toString();
    }

    private String makeLine(String value, int index) {
        String sample = "%-5s %-20s |%-30s |%-10s\n";

        String[] nameCarTime = value.split("_");
        String[] carTime = nameCarTime[1].split("@");

        Racer racer = new Racer(nameCarTime[0], carTime[0], carTime[1]);

        String lineString = sample.formatted(
                index,
                racer.racerName(),
                racer.car(),
                racer.time());

        String result;
        int indicatorOfDashes = 15;
        int lengthOfDashes = 70;
        if (index == indicatorOfDashes) {
            result = makeDashes(lengthOfDashes)
                    + lineString;
        } else {
            result = lineString;
        }
        return result;
    }

    private String makeDashes(int lengthOfDashes) {
        return "-".repeat(lengthOfDashes) + "\n";
    }

    private List<Map.Entry<String, Integer>> sortByMillisecond(HashMap<String, Integer> mapOfMillisecondsAndValue) {
        long limiter = 3540000;
        return mapOfMillisecondsAndValue.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .filter(x -> x.getValue() < limiter)
                .toList();
    }

    private HashMap<String, Integer> makeMap(List<String> nameAndTime) {
        HashMap<String, Integer> mapOfMillisecondsAndValue = new HashMap<>();
        for (String name : nameAndTime) {
            String[] twoParts = name.split("%");
            mapOfMillisecondsAndValue.put(twoParts[0], Integer.parseInt(twoParts[1]));
        }
        return mapOfMillisecondsAndValue;
    }
}
