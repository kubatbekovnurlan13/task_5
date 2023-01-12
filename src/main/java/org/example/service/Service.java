package org.example.service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Stream;

public class Service {

    private final List<String> start;
    private final List<String> end;
    private final List<String> abbreviations;

    public Service(List<String> start, List<String> end, List<String> abbreviations) {
        this.start = start;
        this.end = end;
        this.abbreviations = abbreviations;
    }
    public List<String> getNameAndTime() {
        return getAbbreviationAndDifferenceInTime().map(
                abbreviationAndDifferenceInTime -> {
                    String abbreviationFromDifferenceTimeMethod = getAbbreviation(abbreviationAndDifferenceInTime);
                    String valueFromAbbreviationsList = abbreviations.stream().filter(
                            abbreviationFromAbbreviationsList -> getAbbreviation(abbreviationFromAbbreviationsList).equals(abbreviationFromDifferenceTimeMethod)
                    ).toList().get(0);

                    return cutAbbreviation(valueFromAbbreviationsList) + "@" + cutAbbreviation(abbreviationAndDifferenceInTime);
                }
        ).toList();
    }

    private Stream<String> getAbbreviationAndDifferenceInTime() {
        return start.stream().map(
                (startTimeValue) -> {
                    String startAbbreviation = getAbbreviation(startTimeValue);
                    String endTimeValue = end.stream().filter(
                            endValue -> getAbbreviation(endValue).equals(startAbbreviation)
                    ).toList().get(0);

                    return startAbbreviation + "-" + getDifferenceInTime(startTimeValue, endTimeValue);
                }
        ).sorted();
    }

    private String cutAbbreviation(String value) {
        return value.substring(4);
    }

    private String getDifferenceInTime(String start, String end) {
        LocalTime startTime = LocalTime.parse(getTime(start));
        LocalTime endTime = LocalTime.parse(getTime(end));
        long differenceInMillis = Duration.between(startTime, endTime).toMillis();

        long millis = differenceInMillis % 1000;
        long second = (differenceInMillis / 1000) % 60;
        long minute = (differenceInMillis / (1000 * 60)) % 60;

        return String.format("%02d:%02d.%d", minute, second, millis) + "%" + differenceInMillis;
    }

    private String getTime(String value) {
        return value.substring(14, 26);
    }

    private String getAbbreviation(String value) {
        return value.substring(0, 3);
    }
}