import java.util.*;

public class Main {

    static Random random = new Random(42);

    static String randomText(int length) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            sb.append((char) ('a' + random.nextInt(4)));
        }

        return sb.toString();
    }

    public static void main(String[] args) {

        int[] patternCounts = {10, 100, 1000};
        int[] textLengths = {1000, 10000, 100000};

        System.out.println("Patterns | Text | Aho(ms) | KMP(ms)");

        for (int count : patternCounts) {
            for (int length : textLengths) {

                String text = randomText(length);
                List<String> patterns = new ArrayList<>();

                for (int i = 0; i < count; i++) {
                    patterns.add(randomText(8));
                }

                AhoCorasick aho = new AhoCorasick();

                for (String p : patterns) {
                    aho.addPattern(p);
                }

                aho.build();

                aho.search(text);
                for (String p : patterns) {
                    KMP.search(text, p);
                }

                long ahoTotal = 0;
                long kmpTotal = 0;

                for (int run = 0; run < 5; run++) {

                    long start = System.nanoTime();
                    int ahoMatches = aho.search(text);
                    ahoTotal += System.nanoTime() - start;

                    start = System.nanoTime();
                    int kmpMatches = 0;

                    for (String p : patterns) {
                        kmpMatches += KMP.search(text, p);
                    }

                    kmpTotal += System.nanoTime() - start;

                    if (ahoMatches != kmpMatches) {
                        throw new RuntimeException("Wrong result!");
                    }
                }

                double ahoAvg = ahoTotal / 5.0 / 1_000_000;
                double kmpAvg = kmpTotal / 5.0 / 1_000_000;

                System.out.printf(
                        "%d | %d | %.3f | %.3f%n",
                        count, length, ahoAvg, kmpAvg
                );
            }
        }
    }
}