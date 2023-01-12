package org.example.reader;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ReaderTest {
    @Test
    void readFile_testReadFile_whenAddressInput() {
        Reader reader = new Reader();
        List<String> actual = reader.readFile("src/test/files/startValues.log");

        List<String> expected = List.of(
                "SVF2018-05-24_12:02:58.917",
                "NHR2018-05-24_12:02:49.914",
                "FAM2018-05-24_12:13:04.512",
                "KRF2018-05-24_12:03:01.250",
                "SVM2018-05-24_12:18:37.735",
                "MES2018-05-24_12:04:45.513",
                "LSW2018-05-24_12:06:13.511",
                "BHS2018-05-24_12:14:51.985",
                "EOF2018-05-24_12:17:58.810",
                "RGH2018-05-24_12:05:14.511",
                "SSW2018-05-24_12:16:11.648",
                "KMH2018-05-24_12:02:51.003",
                "PGS2018-05-24_12:07:23.645",
                "CSR2018-05-24_12:03:15.145",
                "SPF2018-05-24_12:12:01.035",
                "DRR2018-05-24_12:14:12.054",
                "LHM2018-05-24_12:18:20.125",
                "CLS2018-05-24_12:09:41.921",
                "VBM2018-05-24_12:00:00.000");
        assertEquals(expected, actual);
    }

    @Test
    void readFile_testReadFile_whenWrongAddressInput() {
        Reader reader = new Reader();
        List<String> actual = reader.readFile("wrong_address");

        assertNull(actual);
    }

    @Test
    void readFile_testReadFile_whenThereIsNoValue() {
        Reader reader = new Reader();
        List<String> actual = reader.readFile("src/test/files/noValues.log");

        List<String> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }
}
