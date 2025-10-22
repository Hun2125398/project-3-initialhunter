package com.example.streams;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import com.example.streams.EmptyCollectionException;
import com.example.streams.InvalidDataException;

/**
 * Enhanced coding kata on the Stream API with exception handling, generics, and advanced concepts.
 * All methods include proper validation and can be completed with a single return statement plus validation.
 */
public class GentlyDownTheStream {

    protected List<String> fruits;
    protected List<String> veggies;
    protected List<Integer> integerValues;

    public GentlyDownTheStream() {
        fruits = Arrays.asList("Apple", "Orange", "Banana", "Pear", "Peach", "Tomato");
        veggies = Arrays.asList("Corn", "Potato", "Carrot", "Pea", "Tomato");
        integerValues = new Random().ints(0, 1001)
                .boxed()
                .limit(1000)
                .collect(Collectors.toList());
    }

    /**
     * Example method showing proper exception handling and validation
     * Returns a sorted list of fruits with comprehensive error checking
     */
    public List<String> sortedFruits() throws InvalidDataException, IllegalArgumentException, EmptyCollectionException {
        if (fruits == null) {
            throw new IllegalArgumentException("Fruits collection cannot be null");
        }

        if (fruits.isEmpty()) {
            throw new EmptyCollectionException("Fruits collection cannot be empty");
        }

        return fruits.stream()
                .filter(Objects::nonNull) // Handle potential null elements
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Enhanced version with custom predicate and exception handling
     */
    public List<String> sortedFruitsException() throws InvalidDataException {
        return sortedFruitsWithFilter(fruit -> !fruit.startsWith("A"));
    }

    /**
     * Returns a list with the first 2 elements of a sorted list of fruits
     * Handles gracefully when fewer than 2 elements are available
     *
     * @return List containing up to 2 sorted fruits
     * @throws InvalidDataException if the fruits collection is null
     */
    public List<String> sortedFruitsFirstTwo() throws InvalidDataException {
        try {
            // Validate that collection exists (empty is acceptable)
            if (fruits == null) {
                throw new IllegalArgumentException("Fruits collection cannot be null");
            }

            // Return empty list if collection is empty
            if (fruits.isEmpty()) {
                return Collections.emptyList();
            }

            return fruits.stream()
                    .filter(Objects::nonNull)
                    .sorted()
                    .limit(2)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new InvalidDataException("Failed to get first two sorted fruits: " + e.getMessage());
        }
    }

    /**
     * Returns a comma separated String of sorted fruits
     * Handles null values and empty results gracefully
     *
     * @return Comma-separated string of sorted fruits, or empty string if no fruits
     * @throws InvalidDataException if the fruits collection is null
     */
    public String commaSeparatedListOfFruits() throws InvalidDataException {
        try {
            // Validate that collection exists (empty is acceptable)
            if (fruits == null) {
                throw new IllegalArgumentException("Fruits collection cannot be null");
            }

            // Return empty string if collection is empty
            if (fruits.isEmpty()) {
                return "";
            }

            return fruits.stream()
                    .filter(Objects::nonNull)
                    .sorted()
                    .collect(Collectors.joining(", "));
        } catch (Exception e) {
            throw new InvalidDataException("Failed to create comma-separated fruits list: " + e.getMessage());
        }
    }

    /**
     * Returns a list of veggies sorted in reverse (descending) order
     * Uses Comparator.reverseOrder() and handles edge cases
     *
     * @return List of vegetables sorted in descending alphabetical order
     * @throws InvalidDataException if the veggies collection is null or cannot be processed
     */
    public List<String> reverseSortedVeggies() throws InvalidDataException {
        try {
            // Validate that collection exists (empty is acceptable)
            if (veggies == null) {
                throw new IllegalArgumentException("Veggies collection cannot be null");
            }

            // Return empty list if collection is empty
            if (veggies.isEmpty()) {
                return Collections.emptyList();
            }

            return veggies.stream()
                    .filter(Objects::nonNull)
                    .filter(v -> !v.trim().isEmpty())
                    .sorted(Comparator.reverseOrder())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new InvalidDataException("Failed to sort veggies in reverse: " + e.getMessage());
        }
    }

    /**
     * Returns a list of veggies sorted in reverse order, all in upper case
     * Chains multiple stream operations with proper exception handling
     *
     * @return List of vegetables sorted in descending order and converted to uppercase
     * @throws InvalidDataException if the veggies collection is null or cannot be processed
     */
    public List<String> reverseSortedVeggiesInUpperCase() throws InvalidDataException {
        try {
            // Validate that collection exists (empty is acceptable)
            if (veggies == null) {
                throw new IllegalArgumentException("Veggies collection cannot be null");
            }

            // Return empty list if collection is empty
            if (veggies.isEmpty()) {
                return Collections.emptyList();
            }

            return veggies.stream()
                    .filter(Objects::nonNull)
                    .filter(v -> !v.trim().isEmpty())
                    .sorted(Comparator.reverseOrder())
                    .map(v -> v.toUpperCase(Locale.ROOT))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new InvalidDataException("Failed to sort and uppercase veggies: " + e.getMessage());
        }
    }

    /**
     * Returns a list of the top 10 values in the list of random integers
     * Handles cases where list has fewer than 10 elements gracefully
     *
     * @return List containing up to 10 largest integers in descending order
     * @throws InvalidDataException if the integer values collection is null or cannot be processed
     */
    public List<Integer> topTen() throws InvalidDataException {
        try {
            // Validate that collection exists (empty is acceptable)
            if (integerValues == null) {
                throw new IllegalArgumentException("Integer values collection cannot be null");
            }

            // Return empty list if collection is empty
            if (integerValues.isEmpty()) {
                return Collections.emptyList();
            }

            return integerValues.stream()
                    .sorted(Comparator.reverseOrder())
                    .limit(10)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new InvalidDataException("Failed to retrieve top ten values: " + e.getMessage());
        }
    }

    /**
     * Returns a list of the top 10 unique values in the list of random integers
     * Uses distinct() operation and handles empty results gracefully
     *
     * @return List containing up to 10 largest unique integers in descending order
     * @throws InvalidDataException if the integer values collection is null or cannot be processed
     */
    public List<Integer> topTenUnique() throws InvalidDataException {
        try {
            // Validate that collection exists (empty is acceptable)
            if (integerValues == null) {
                throw new IllegalArgumentException("Integer values collection cannot be null");
            }

            // Return empty list if collection is empty
            if (integerValues.isEmpty()) {
                return Collections.emptyList();
            }

            return integerValues.stream()
                    .sorted(Comparator.reverseOrder())
                    .distinct()
                    .limit(10)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new InvalidDataException("Failed to retrieve top ten unique values: " + e.getMessage());
        }
    }

    /**
     * Returns a list of the top 10 unique values that are odd
     * Combines filtering, distinct, and limiting operations
     *
     * @return List containing up to 10 largest unique odd integers in descending order
     * @throws InvalidDataException if the integer values collection is null or cannot be processed
     */
    public List<Integer> topTenUniqueOdd() throws InvalidDataException {
        try {
            // Validate that collection exists (empty is acceptable)
            if (integerValues == null) {
                throw new IllegalArgumentException("Integer values collection cannot be null");
            }

            // Return empty list if collection is empty
            if (integerValues.isEmpty()) {
                return Collections.emptyList();
            }

            return integerValues.stream()
                    .sorted(Comparator.reverseOrder())
                    .distinct()
                    .filter(x -> x % 2 != 0)  // Keep only odd numbers
                    .limit(10)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new InvalidDataException("Failed to retrieve top ten unique odd values: " + e.getMessage());
        }
    }

    /**
     * Returns the average of all random numbers
     * Handles potential OptionalDouble and division by zero scenarios
     *
     * @return The average (mean) of all integer values
     * @throws InvalidDataException if the integer values collection is null,
     *         empty, or if the average cannot be calculated
     */
    public Double average() throws InvalidDataException {
        if (integerValues == null) {
            throw new InvalidDataException("Integer values collection cannot be null");
        }

        if (integerValues.isEmpty()) {
            throw new InvalidDataException("Cannot calculate average of empty collection");
        }

        OptionalDouble result = safeAverage(integerValues);

        if (result.isPresent()) {
            return result.getAsDouble();
        } else {
            throw new InvalidDataException(
                    "Average calculation failed: no valid values to average");
        }
    }

    // Generic method for safe collection operations
    private <T> void validateCollection(Collection<T> collection, String collectionName) throws EmptyCollectionException {
        if (collection == null) {
            throw new IllegalArgumentException(collectionName + " cannot be null");
        }
        if (collection.isEmpty()) {
            throw new EmptyCollectionException(collectionName + " cannot be empty");
        }
    }

    // Helper method demonstrating advanced generics and functional programming
    private <T> List<T> sortedWithFilter(Collection<T> collection,
                                         Predicate<T> filter,
                                         Comparator<T> comparator) throws InvalidDataException {
        try {
            validateCollection(collection, "Input collection");

            return collection.stream()
                    .filter(Objects::nonNull)
                    .filter(filter)
                    .sorted(comparator)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new InvalidDataException("Failed to sort and filter collection: " + e.getMessage());
        }
    }

    // Specialized method using the generic helper
    private List<String> sortedFruitsWithFilter(Predicate<String> filter) throws InvalidDataException {
        return sortedWithFilter(fruits, filter, String::compareTo);
    }

    // Utility method for safe integer operations
    private OptionalDouble safeAverage(Collection<Integer> numbers) {
        return numbers.stream()
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue)
                .average();
    }

}