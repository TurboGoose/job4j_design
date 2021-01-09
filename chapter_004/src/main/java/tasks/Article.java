package tasks;

import java.util.HashMap;
import java.util.Map;

public class Article {
    private static final Map<String, Integer> availableWords = new HashMap<>();

    public static boolean generateBy(String text, String check) {
        createMapOfWordsFromOriginalText(text);
        return checkText(check);
    }

    private static void createMapOfWordsFromOriginalText(String text) {
        availableWords.clear();
        text = processText(text);
        for (String word : text.split(" ")) {
            availableWords.put(word, availableWords.getOrDefault(word, 0) + 1);
        }
    }

    private static String processText(String text) {
        return text.toLowerCase().replaceAll("[^a-zа-я ]", "");
    }

    private static boolean checkText(String text) {
        text = processText(text);
        for (String word : processText(text).split(" ")) {
            if (!availableWords.containsKey(word)) {
                return false;
            } else {
                int numberOfOccurrences = availableWords.get(word);
                if (numberOfOccurrences > 1) {
                    availableWords.put(word, numberOfOccurrences - 1);
                } else {
                    availableWords.remove(word);
                }
            }
        }
        return true;
    }
}
