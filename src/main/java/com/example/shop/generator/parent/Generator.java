package com.example.shop.generator.parent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Generator {

    protected List<String> readAllLines(String file) throws IOException {
        return Files.readAllLines(Paths.get(file));
    }
}
