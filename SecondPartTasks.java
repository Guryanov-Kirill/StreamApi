package com.example.streams;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class SecondPartTasks {

    private SecondPartTasks() {}

    // Найти строки из переданных файлов, в которых встречается указанная подстрока.
    public static List<String> findQuotes(List<String> paths, CharSequence sequence) {
        return paths.stream().flatMap(path -> {
            try {
                return Files.lines(Path.of(path));
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }).filter(line -> line.contains(sequence)).toList();
    }

    // В квадрат с длиной стороны 1 вписана мишень.
    // Стрелок атакует мишень и каждый раз попадает в произвольную точку квадрата.
    // Надо промоделировать этот процесс с помощью класса java.util.Random и посчитать, какова вероятность попасть в мишень.
    public static double piDividedBy4() {
        final int shots = 1000000;
        Random random = new Random();
        long hits = IntStream.range(0, shots).filter(i -> {
            double x = random.nextDouble() - 0.5;
            double y = random.nextDouble() - 0.5;
            return x * x + y * y <= 0.25;
        }).count();
        double result = (double) hits / shots;
        return result;
    }

    // Дано отображение из имени автора в список с содержанием его произведений.
    // Надо вычислить, чья общая длина произведений наибольшая.
    public static String findPrinter(Map<String, List<String>> compositions) {
        return compositions.keySet().stream()
                .max(Comparator.comparing(author -> String.join("", compositions.get(author)).length())).orElse(null);
    }

    // Вы крупный поставщик продуктов. Каждая торговая сеть делает вам заказ в виде Map<Товар, Количество>.
    // Необходимо вычислить, какой товар и в каком количестве надо поставить.
    public static Map<String, Integer> calculateGlobalOrder(List<Map<String, Integer>> orders) {
        return orders.stream().flatMap(order -> order.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Integer::sum));
    }
}
