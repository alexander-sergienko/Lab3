package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        // Задание 1
        Random random = new Random();

        int[] numbers = new int[10];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(10);
        }

        for (int i : numbers) {
            System.out.print(i + " ");
        }
        System.out.println();

        List<Integer> numberList = new ArrayList<>();
        for (int num : numbers) {
            numberList.add(num);
        }
        System.out.println("Список: " + numberList);

        numberList.sort(Comparator.comparingInt(o -> o));
        System.out.println("Отсортированный список: " + numberList);

        Collections.reverse(numberList);
        System.out.println("Обратный порядок: " + numberList);

        Collections.shuffle(numberList);
        System.out.println("Перемешанный список: " + numberList);

        Collections.rotate(numberList, 1);
        System.out.println("Список после вращения: " + numberList);

        Set<Integer> uniqueNumbers = new LinkedHashSet<>();
        for (int num : numberList) {
            uniqueNumbers.add(num);
        }
        System.out.println("Список без дубликатов: " + uniqueNumbers);

        List<Integer> filteredList = new ArrayList<>();
        for (Integer num : numberList) {
            int count = 0;
            for (Integer n : numberList) {
                if (n.equals(num)) {
                    count++;
                }
            }
            if (count >= 2) {
                filteredList.add(num);
            }
        }
        System.out.println("Список с удаленными уникальными элементами: " + filteredList);

        int[] resultArray = new int[filteredList.size()];
        for (int i = 0; i < filteredList.size(); i++) {
            resultArray[i] = filteredList.get(i);
        }
        System.out.println("Массив из списка: " + Arrays.toString(resultArray));

        Map<Integer, Long> frequencyMap = new HashMap<>();
        for (int num : resultArray) {
            if (!frequencyMap.containsKey(num)) {
                frequencyMap.put(num, 1L);
            } else {
                frequencyMap.put(num, frequencyMap.get(num) + 1);
            }
        }
        System.out.println("Частота элементов: " + frequencyMap);

        // Задание 2
        PrimesGeneratorTest primesTest = new PrimesGeneratorTest();
        primesTest.test();
        System.out.println();

        // Задание 3
        List<Human> humans = Arrays.asList(
                new Human("Данил", "Петров", 20),
                new Human("Иван", "Иванов", 25),
                new Human("Анастасия", "Неткачева", 30)
        );

        // Сортировка по имени
        humans.sort(new Comparator<Human>() {
            @Override
            public int compare(Human h1, Human h2) {
                return h1.name.compareTo(h2.name);
            }
        });
        System.out.println("Сортировка по имени:");
        for (Human human : humans) {
            System.out.println(human);
        }

        // Сортировка по фамилии
        humans.sort(new HumanComparatorByLastName());
        System.out.println("Сортировка по фамилии:");
        for (Human human : humans) {
            System.out.println(human);
        }

        // Задание 4
        String text = "This is a test test".toLowerCase();
        Map<String, Integer> wordCount = countWords(text);
        System.out.println("Подсчет слов в тексте: " + wordCount);

        // Задание 5
        Map<Integer, String> originalMap = new HashMap<>();
        originalMap.put(1, "Один");
        originalMap.put(2, "Один");
        originalMap.put(3, "Три");

        Map<String, Integer> invertedMap = invertMap(originalMap);
        System.out.println("Инвертированная Map: " + invertedMap);
    }

    private static Map<String, Integer> countWords(String text) {
        Map<String, Integer> wordCounts = new HashMap<>();
        String[] words = text.split("\\W+");
        for (String word : words) {
            if (!wordCounts.containsKey(word)) {
                wordCounts.put(word, 1);
            } else {
                wordCounts.put(word, wordCounts.get(word) + 1);
            }
        }
        return wordCounts;
    }

    public static <K, V> Map<V, List<K>> invertMap(Map<K, V> map) {
        Map<V, K> inverted = new HashMap<>();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            inverted.put(entry.getValue(), entry.getKey());
        }
        return inverted;
    }
}

// Генератор простых чисел
class PrimesGenerator {
    private final List<Integer> primes;

    public PrimesGenerator(int limit) {
        primes = new ArrayList<>();
        for (int i = 2; primes.size() < limit; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
    }

    private boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public List<Integer> getPrimes() {
        return primes;
    }
}

class PrimesGeneratorTest {
    public void test() {
        PrimesGenerator generator = new PrimesGenerator(10);
        System.out.println("Простые числа: " + generator.getPrimes());
    }
}

class Human {
    String name;
    String surname;
    int age;

    public Human(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("Имя: %s, Фамилия: %s, Возраст: %d", name, surname, age);
    }
}

class HumanComparatorByLastName implements Comparator<Human> {
    @Override
    public int compare(Human h1, Human h2) {
        return h1.surname.compareTo(h2.surname);
    }
}