package IonErm.utility;

import java.util.Random;

public class DogNameGenerator {
    private static final String VOWELS = "aeiou";
    private static final String CONSONANTS = "bcdfghjklmnpqrstvwxyz";

    public static String generateDogName() {
        Random random = new Random();
        StringBuilder name = new StringBuilder();
        int length = random.nextInt(5) + 4;
        for (int i = 0; i < length; i++) {
            if (i % 2 == 0) {
                name.append(CONSONANTS.charAt(random.nextInt(CONSONANTS.length())));
            } else {
                name.append(VOWELS.charAt(random.nextInt(VOWELS.length())));
            }
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println("Dog Name: " + generateDogName());
        }
    }
}
