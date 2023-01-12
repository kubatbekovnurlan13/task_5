package org.example.service;

import org.example.pathReader.PathReader;
import org.example.reader.Reader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceTest {
    private final PathReader pathReader = new PathReader();

    @Test
    void getNameAndTime_testGetNameAndTime_whenThereIsSomeValue() {
        Reader reader = new Reader();
        List<String> start;
        List<String> end;
        List<String> abb;
        try {
            start = reader.readFile(pathReader.getStart());
            end = reader.readFile(pathReader.getEnd());
            abb = reader.readFile(pathReader.getAbbreviation());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Service service = new Service(start, end, abb);

        List<String> actual = service.getNameAndTime();

        List<String> expected = List.of(
                "Brendon Hartley_SCUDERIA TORO ROSSO HONDA@01:13.179%73179",
                "Charles Leclerc_SAUBER FERRARI@01:12.829%72829",
                "Carlos Sainz_RENAULT@01:12.950%72950",
                "Daniel Ricciardo_RED BULL RACING TAG HEUER@01:12.13%72013",
                "Esteban Ocon_FORCE INDIA MERCEDES@01:13.28%73028",
                "Fernando Alonso_MCLAREN RENAULT@01:12.657%72657",
                "Kevin Magnussen_HAAS FERRARI@01:13.393%73393",
                "Kimi Raikkonen_FERRARI@01:12.639%72639",
                "Lewis Hamilton_MERCEDES@01:12.460%72460",
                "Lance Stroll_WILLIAMS MERCEDES@01:13.323%73323",
                "Marcus Ericsson_SAUBER FERRARI@01:13.265%73265",
                "Nico Hulkenberg_RENAULT@01:13.65%73065",
                "Pierre Gasly_SCUDERIA TORO ROSSO HONDA@01:12.941%72941",
                "Romain Grosjean_HAAS FERRARI@01:12.930%72930",
                "Sergio Perez_FORCE INDIA MERCEDES@01:12.848%72848",
                "Sergey Sirotkin_WILLIAMS MERCEDES@01:12.706%72706",
                "Sebastian Vettel_FERRARI@01:04.415%64415",
                "Stoffel Vandoorne_MCLAREN RENAULT@01:12.463%72463",
                "Valtteri Bottas_MERCEDES@01:12.434%72434");
        assertEquals(expected, actual);
    }

    @Test
    void getNameAndTime_testGetNameAndTime_whenFirstValueIsNull() {
        Exception exception = assertThrows(NullPointerException.class,
                () -> {
                    Reader reader = new Reader();
                    List<String> end = reader.readFile(pathReader.getEnd());
                    List<String> abb = reader.readFile(pathReader.getAbbreviation());

                    Service service = new Service(null, end, abb);

                    service.getNameAndTime();
                });
        assertEquals("Cannot invoke \"java.util.List.stream()\" because \"this.start\" is null", exception.getMessage());
    }

    @Test
    void getNameAndTime_testGetNameAndTime_whenSecondValueIsNull() {
        Exception exception = assertThrows(NullPointerException.class,
                () -> {
                    Reader reader = new Reader();
                    List<String> start = reader.readFile(pathReader.getStart());
                    List<String> abb = reader.readFile(pathReader.getAbbreviation());

                    Service service = new Service(start, null, abb);

                    service.getNameAndTime();
                });
        assertEquals("Cannot invoke \"java.util.List.stream()\" because \"this.end\" is null", exception.getMessage());
    }

    @Test
    void getNameAndTime_testGetNameAndTime_whenThirdValueIsNull() {
        Exception exception = assertThrows(NullPointerException.class,
                () -> {
                    Reader reader = new Reader();
                    List<String> start = reader.readFile(pathReader.getStart());
                    List<String> end = reader.readFile(pathReader.getEnd());

                    Service service = new Service(start, end, null);

                    service.getNameAndTime();
                });
        assertEquals("Cannot invoke \"java.util.List.stream()\" because \"this.abbreviations\" is null", exception.getMessage());
    }

    @Test
    void getNameAndTime_testGetNameAndTime_whenThereIsNoValueInThirdInput() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> {
                    Reader reader = new Reader();
                    List<String> start = reader.readFile(pathReader.getStart());
                    List<String> end = reader.readFile(pathReader.getEnd());
                    List<String> abb = reader.readFile(pathReader.getNoValueFile());

                    Service service = new Service(start, end, abb);

                    service.getNameAndTime();
                });
        assertEquals("Index 0 out of bounds for length 0", exception.getMessage());
    }

    @Test
    void getNameAndTime_testGetNameAndTime_whenThereIsNoValueInSecondInput() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> {
                    Reader reader = new Reader();
                    List<String> start = reader.readFile(pathReader.getStart());
                    List<String> end = reader.readFile(pathReader.getNoValueFile());
                    List<String> abb = reader.readFile(pathReader.getAbbreviation());

                    Service service = new Service(start, end, abb);

                    service.getNameAndTime();
                });
        assertEquals("Index 0 out of bounds for length 0", exception.getMessage());
    }

    @Test
    void getNameAndTime_testGetNameAndTime_whenThereIsNoValueInFirstInput() {
        Reader reader = new Reader();
        List<String> start;
        List<String> end;
        List<String> abb;
        try {
            start = reader.readFile(pathReader.getNoValueFile());
            end = reader.readFile(pathReader.getEnd());
            abb = reader.readFile(pathReader.getAbbreviation());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Service service = new Service(start, end, abb);

        List<String> actual = service.getNameAndTime();

        List<String> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }
}
