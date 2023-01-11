package org.example.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    public List<String> readFile(String path) {

        try (FileReader reader = new FileReader(path)) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();

            List<String> all = new ArrayList<>();

            while (line != null) {
                all.add(line);
                line=bufferedReader.readLine();
            }

            return all;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
