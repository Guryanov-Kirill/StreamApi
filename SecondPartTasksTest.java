package com.example.streams;

import org.junit.jupiter.api.Test;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;

import static com.example.streams.SecondPartTasks.*;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SecondPartTasksTest {

    @Test
    public void testFindQuotes(@TempDir Path dir) throws IOException {
        Path file1 = dir.resolve("file1.txt");
        Path file2 = dir.resolve("file2.txt");
        Files.write(file1, Arrays.asList("hello world", "nothing here", "say hello again"));
        Files.write(file2, Arrays.asList("Hello", "oh hello there"));

        assertEquals(
                Arrays.asList("hello world", "say hello again", "oh hello there"),
                findQuotes(Arrays.asList(file1.toString(), file2.toString()), "hello"));

        assertEquals(
                Collections.emptyList(),
                findQuotes(Arrays.asList(file1.toString()), "xyz"));

        assertEquals(
                Collections.emptyList(),
                findQuotes(Collections.emptyList(), "hello"));
    }

    @Test
    public void testPiDividedBy4() {
        assertEquals(Math.PI / 4, piDividedBy4(), 0.01);
    }

    @Test
    public void testFindPrinter() {
        assertEquals(
                "Pushkin",
                findPrinter(ImmutableMap.of(
                        "Pushkin", Arrays.asList("12345", "12345", "12345"),
                        "Tolstoy", Arrays.asList("1234567890"))));

        assertEquals(
                null,
                findPrinter(Collections.emptyMap()));
    }

    @Test
    public void testCalculateGlobalOrder() {
        assertEquals(
                ImmutableMap.of("apple", 5, "milk", 1, "bread", 5),
                calculateGlobalOrder(Arrays.asList(
                        ImmutableMap.of("apple", 3, "milk", 1),
                        ImmutableMap.of("apple", 2, "bread", 5))));

        assertEquals(
                Collections.emptyMap(),
                calculateGlobalOrder(Collections.emptyList()));
    }
}