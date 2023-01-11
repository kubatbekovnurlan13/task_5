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
    public List<String> getAbbreviationNameAndTimeResult() {
        return getAbbreviationAndDifferenceInTime().map(
                abbreviationAndDifferenceInTime -> {
                    String abbreviationFromDifferenceTimeMethod = getAbbreviationFromValue(abbreviationAndDifferenceInTime);
                    String valueFromAbbreviationsList = abbreviations.stream().filter(
                            abbreviationFromAbbreviationsList -> getAbbreviationFromValue(abbreviationFromAbbreviationsList).equals(abbreviationFromDifferenceTimeMethod)
                    ).toList().get(0);

                    return cutAbbreviationFromValue(valueFromAbbreviationsList) + "@" + cutAbbreviationFromValue(abbreviationAndDifferenceInTime);
                }
        ).toList();
    }

    private Stream<String> getAbbreviationAndDifferenceInTime() {
        return start.stream().map(
                (startTimeValue) -> {
                    String startAbbreviation = getAbbreviationFromValue(startTimeValue);
                    String endTimeValue = end.stream().filter(
                            endValue -> getAbbreviationFromValue(endValue).equals(startAbbreviation)
                    ).toList().get(0);

                    return startAbbreviation + "-" + getDifferenceInTime(startTimeValue, endTimeValue);
                }
        ).sorted();
    }

    private String cutAbbreviationFromValue(String value) {
        return value.substring(4);
    }

    private String getDifferenceInTime(String start, String end) {
        LocalTime startTime = LocalTime.parse(getTimeFromValue(start));
        LocalTime endTime = LocalTime.parse(getTimeFromValue(end));
        long differenceInMillis = Duration.between(startTime, endTime).toMillis();

        long millis = differenceInMillis % 1000;
        long second = (differenceInMillis / 1000) % 60;
        long minute = (differenceInMillis / (1000 * 60)) % 60;

        return String.format("%02d:%02d.%d", minute, second, millis) + "%" + differenceInMillis;
    }

    private String getTimeFromValue(String value) {
        return value.substring(14, 26);
    }

    private String getAbbreviationFromValue(String value) {
        return value.substring(0, 3);
    }
}