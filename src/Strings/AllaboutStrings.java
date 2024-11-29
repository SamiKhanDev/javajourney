package Strings;

public class AllaboutStrings {
    public static void main(String[] args) {
        String filename = "example.text";
        String filename2= filename.substring(filename.lastIndexOf(".") + 1);
        System.out.println(filename2);
            String original = "  Hello World!  ";
            System.out.println("Original String: \"" + original + "\"");

            String trimmed = original.trim();
            System.out.println("Trimmed String: \"" + trimmed + "\"");

            String lower = trimmed.toLowerCase();
            System.out.println("Lowercase: " + lower);

            String upper = trimmed.toUpperCase();
            System.out.println("Uppercase: " + upper);

            int length = trimmed.length();
            System.out.println("Length: " + length);

            boolean startsWithHello = trimmed.startsWith("Hello");
            System.out.println("Starts with 'Hello': " + startsWithHello);

            boolean endsWithWorld = trimmed.endsWith("World!");
            System.out.println("Ends with 'World!': " + endsWithWorld);

            int indexOfW = trimmed.indexOf('W');
            System.out.println("Index of 'W': " + indexOfW);

            String replaced = trimmed.replace("World", "Java");
            System.out.println("Replaced String: " + replaced);

            String substring = trimmed.substring(6);
            System.out.println("Substring from index 6: " + substring);

            boolean containsHello = trimmed.contains("Hello");
            System.out.println("Contains 'Hello': " + containsHello);

            String[] words = trimmed.split(" ");
            System.out.println("Split into words:");
            for (String word : words) {
                System.out.println("- " + word);
            }

            String concatenated = trimmed.concat(" Welcome!");
            System.out.println("Concatenated String: " + concatenated);

            boolean isEmpty = trimmed.isEmpty();
            System.out.println("Is Empty: " + isEmpty);

            boolean equals = trimmed.equals("Hello World!");
            System.out.println("Equals 'Hello World!': " + equals);

            boolean equalsIgnoreCase = trimmed.equalsIgnoreCase("HELLO world!");
            System.out.println("Equals Ignore Case 'hello world!': " + equalsIgnoreCase);

            char charAt = trimmed.charAt(1);
            System.out.println("Character at index 1: " + charAt);

            char[] charArray = trimmed.toCharArray();
            System.out.println("Char Array:");
            for (char c : charArray) {
                System.out.println("- " + c);
            }
        }


    }

