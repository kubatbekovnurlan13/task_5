package org.example.output;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PrinterTest {
    @Test
    void printResult_testPrintResult_whenSomeValueInput() {


        List<String> abbreviationNameAndTimeResult = List.of(
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

        Printer printer = new Printer();

        String actual = printer.printResult(abbreviationNameAndTimeResult);
        String expected =
                """
                        1     Sebastian Vettel     |FERRARI                        |01:04.415\s
                        2     Daniel Ricciardo     |RED BULL RACING TAG HEUER      |01:12.13 \s
                        3     Valtteri Bottas      |MERCEDES                       |01:12.434\s
                        4     Lewis Hamilton       |MERCEDES                       |01:12.460\s
                        5     Stoffel Vandoorne    |MCLAREN RENAULT                |01:12.463\s
                        6     Kimi Raikkonen       |FERRARI                        |01:12.639\s
                        7     Fernando Alonso      |MCLAREN RENAULT                |01:12.657\s
                        8     Sergey Sirotkin      |WILLIAMS MERCEDES              |01:12.706\s
                        9     Charles Leclerc      |SAUBER FERRARI                 |01:12.829\s
                        10    Sergio Perez         |FORCE INDIA MERCEDES           |01:12.848\s
                        11    Romain Grosjean      |HAAS FERRARI                   |01:12.930\s
                        12    Pierre Gasly         |SCUDERIA TORO ROSSO HONDA      |01:12.941\s
                        13    Carlos Sainz         |RENAULT                        |01:12.950\s
                        14    Esteban Ocon         |FORCE INDIA MERCEDES           |01:13.28 \s
                        ---------------------------------------------------------------------\s
                        15    Nico Hulkenberg      |RENAULT                        |01:13.65 \s
                        16    Brendon Hartley      |SCUDERIA TORO ROSSO HONDA      |01:13.179\s
                        17    Marcus Ericsson      |SAUBER FERRARI                 |01:13.265\s
                        18    Lance Stroll         |WILLIAMS MERCEDES              |01:13.323\s
                        19    Kevin Magnussen      |HAAS FERRARI                   |01:13.393\s
                        """;
        assertEquals(expected, actual);
    }

    @Test
    void printResult_testPrintResult_whenInputNull() {
        Exception exception = assertThrows(NullPointerException.class,
                () -> {
                    Printer printer = new Printer();
                    printer.printResult(null);
                });
        assertEquals("Cannot invoke \"java.util.List.iterator()\" because \"abbreviationNameAndTimeResult\" is null", exception.getMessage());
    }
}
