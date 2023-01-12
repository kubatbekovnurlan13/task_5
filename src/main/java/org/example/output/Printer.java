package org.example.output;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Printer {
    public String printResult(List<String> abbreviationNameAndTimeResult) {
        StringBuilder stringResult = new StringBuilder();

        HashMap<String, Integer> mapOfMillisecondsAndValue = makeMap(abbreviationNameAndTimeResult);
        List<Map.Entry<String, Integer>> sorted = sort(mapOfMillisecondsAndValue);

        sorted.forEach(x -> {
            int index = sorted.indexOf(x) + 1;
            String ready = makeLineForPrinting(x.getKey(), index);
            stringResult.append(ready);

        });
        return stringResult.toString();
    }

    private String makeLineForPrinting(String value, int index) {
        String sample = "%-5s %-20s |%-30s |%-10s\n";

        String[] nameCarTime = value.split("_");
        String name = nameCarTime[0];
        String[] carTime = nameCarTime[1].split("@");
        String car = carTime[0];
        String time = carTime[1];

        String linString = sample.formatted(index, name, car, time);
        String result;
        if (index == 15) {
            result = "--------------------------------------------------------------------- \n"
                    + linString;
        } else {
            result = linString;
        }
        return result;
    }

    private List<Map.Entry<String, Integer>> sort(HashMap<String, Integer> mapOfMillisecondsAndValue) {
        return mapOfMillisecondsAndValue.entrySet().stream()
                .sorted(Map.Entry.comparingByValue()).toList();
    }

    private HashMap<String, Integer> makeMap(List<String> abbreviationNameAndTimeResult) {
        HashMap<String, Integer> mapOfMillisecondsAndValue = new HashMap<>();
        for (String abb : abbreviationNameAndTimeResult) {
            String[] twoParts = abb.split("%");
            mapOfMillisecondsAndValue.put(twoParts[0], Integer.parseInt(twoParts[1]));
        }
        return mapOfMillisecondsAndValue;
    }
}
