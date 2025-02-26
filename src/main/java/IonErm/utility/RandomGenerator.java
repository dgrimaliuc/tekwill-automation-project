package IonErm.utility;

import java.util.Random;

public class RandomGenerator {
    private static final String VOWELS = "aeiou";
    private static final String CONSONANTS = "bcdfghjklmnpqrstvwxyz";
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIALS = "!@#$%^&*";

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

    public static String generateRandomEmail() {
        Random random = new Random();
        int nameLength = random.nextInt(5) + 5; // Длина 5-10 символов
        StringBuilder name = new StringBuilder();

        for (int i = 0; i < nameLength; i++) {
            name.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }
        return name + "@gmail.com";
    }

    public static String generateRandomPassword() {
        Random random = new Random();
        int length = random.nextInt(20) + 6;

        StringBuilder password = new StringBuilder();
        password.append("A");
        password.append("a");
        password.append("1");
        password.append("!");
        for (int i = 4; i < length; i++) {
            String allChars = ALPHABET + ALPHABET.toUpperCase() + DIGITS + SPECIALS;
            password.append(allChars.charAt(random.nextInt(allChars.length())));
        }
        return shuffleString(password.toString());
    }

    private static String shuffleString(String input) {
        char[] array = input.toCharArray();
        Random random = new Random();

        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            char temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
        return new String(array);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println("Random Email: " + generateRandomEmail());
            System.out.println("Random Password: " + generateRandomPassword());
        }
//        System.out.println("Dog Name: " + generateDogName());
    }
}
