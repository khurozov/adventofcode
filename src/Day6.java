import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) throws IOException {
        part1();
        part2();
    }

    private static void part1() throws IOException {
        System.out.println("part1");
        Long[][] input = readInput();

        long n = 1;
        for (int i = 0; i < input[0].length; i++) {
            n *= getNumberOfWays_v0(input[0][i], input[1][i]);
        }
        System.out.println("v0: " + n);

        n = 1;
        for (int i = 0; i < input[0].length; i++) {
            n *= getNumberOfWays_v1(input[0][i], input[1][i]);
        }
        System.out.println("v1: " + n);
    }

    private static void part2() throws IOException {
        System.out.println("part2");
        Long[][] input = readInput();
        long t = mergeNums(input[0]);
        long d = mergeNums(input[1]);

        System.out.println("v0: " + getNumberOfWays_v0(t, d));
        System.out.println("v1: " + getNumberOfWays_v1(t, d));
    }

    private static Long[][] readInput() throws IOException {
        Scanner in = new Scanner(Path.of("src", "Day6.txt"));
        ArrayList<Long> t = new ArrayList<>();
        ArrayList<Long> d = new ArrayList<>();

        in.next();
        while (in.hasNextLong()) {
            t.add(in.nextLong());
        }

        in.next();
        while (in.hasNextInt()) {
            d.add(in.nextLong());
        }

        return new Long[][]{t.toArray(new Long[0]), d.toArray(new Long[0])};
    }

    private static long mergeNums(Long[] nums) {
        StringBuilder sb = new StringBuilder();

        for (Long num : nums) {
            sb.append(num);
        }

        return Long.parseLong(sb.toString());
    }

    private static long getNumberOfWays_v0(long t, long d) {
        long n = 0;

        for (long i = 1; i < t; i++) {
            if ((t - i) * i > d) n++;
        }

        return n;
    }

    private static long getNumberOfWays_v1(long t, long d) {
        double sqrt = Math.sqrt(Math.pow(t, 2) - 4 * d);
        int left = (int) Math.ceil((t - sqrt) / 2);
        int right = (int) Math.floor((t + sqrt) / 2);

        return right - left + 1;
    }
}
