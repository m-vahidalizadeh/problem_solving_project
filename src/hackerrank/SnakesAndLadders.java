package hackerrank;

import java.io.*;
import java.util.*;

public class SnakesAndLadders {

    static int minLocation;
    static int minSteps;
    static int target = 100;

    static class Slide implements Comparable<Slide> {
        Integer cost;
        Integer start;
        Integer end;

        Slide(int start, int end) {
            this.start = start;
            this.end = end;
            this.cost = 0;
        }

        Slide(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Slide slide) {
            return this.start.compareTo(slide.start);
        }
    }

    static int quickestWayUp(int[][] ladders, int[][] snakes) {
        boolean isAnySlideToFinish = false;
        boolean[] doSnakesBlocked = new boolean[6];
        minLocation = 1;
        minSteps = 100;
        // Build slides
        PriorityQueue<Slide> slides = new PriorityQueue<>();
        Map<Integer, Integer> mixers = new HashMap<>();
        for (int i = 0; i < ladders.length; i++) {
            slides.add(new Slide(ladders[i][0], ladders[i][1]));
            mixers.put(ladders[i][0], ladders[i][1]);
            if (ladders[i][1] == target) {
                isAnySlideToFinish = true;
            }
        }
        for (int i = 0; i < snakes.length; i++) {
            if (((target - 7) < snakes[i][0]) && (snakes[i][0] < (target))) {
                doSnakesBlocked[target - snakes[i][0] - 1] = true;
            }
            int end = snakes[i][1];
            for (int j = end + 1; j <= end + 7; j++) {
                if (mixers.containsKey(j)) {
                    slides.add(new Slide(snakes[i][0], mixers.get(j), 1));
                }
            }
        }
        boolean snakesDone = true;
        for (int i = 0; i < doSnakesBlocked.length; i++) {
            if (!doSnakesBlocked[i]) {
                snakesDone = false;
                break;
            }
        }
        if (snakesDone && !isAnySlideToFinish) {
            return -1;
        }
        return quickest(1, 0, slides);
    }

    static int quickest(int currentLocation, int currentSteps, PriorityQueue<Slide> slides) {
        if (currentLocation > target) {
            // Not a feasible solution.
            return Integer.MAX_VALUE;
        } else if (currentLocation == target) {
            // Slides took us to the finish line.
            return currentSteps;
        } else {
            /*
            1- We ran out of slides.
            2- There is no more slides.
             */
            if (slides.isEmpty()) {
                // If there is no slide left. Throw dice to the finish line.
                return currentSteps + Double.valueOf(
                        Math.ceil(
                                (target - currentLocation) / 6.0
                        )
                ).intValue();
            } else {
                /*
                For each one of the slides, we either take it or leave it.
                 */
                int minSteps = Integer.MAX_VALUE;
                while (!slides.isEmpty()) {
                    Slide currSlide = slides.poll();
                    if (currSlide.start > currentLocation) {
                        // Min between taking it and not taking it.
                        int costToSlide = Double.valueOf(
                                Math.ceil(
                                        (currSlide.start - currentLocation) / 6.0
                                )
                        ).intValue();
                        int costWithTakingTheSlide = quickest(currSlide.end, currentSteps + costToSlide + currSlide.cost, new PriorityQueue<>(slides));
                        int costWithSkippingSlide = quickest(currentLocation, currentSteps, new PriorityQueue<>(slides));
                        int tempMin = Math.min(costWithSkippingSlide, costWithTakingTheSlide);
                        if (tempMin < minSteps) {
                            minSteps = tempMin;
                        }
                    }
                }
                return minSteps == Integer.MAX_VALUE ? quickest(currentLocation, currentSteps, new PriorityQueue<>(slides)) : minSteps;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(System.in);
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/sal.out")))) {
            int t = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int tItr = 0; tItr < t; tItr++) {
                int n = scanner.nextInt();
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                int[][] ladders = new int[n][2];

                for (int i = 0; i < n; i++) {
                    String[] laddersRowItems = scanner.nextLine().split(" ");
                    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                    for (int j = 0; j < 2; j++) {
                        int laddersItem = Integer.parseInt(laddersRowItems[j]);
                        ladders[i][j] = laddersItem;
                    }
                }

                int m = scanner.nextInt();
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                int[][] snakes = new int[m][2];

                for (int i = 0; i < m; i++) {
                    String[] snakesRowItems = scanner.nextLine().split(" ");
                    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                    for (int j = 0; j < 2; j++) {
                        int snakesItem = Integer.parseInt(snakesRowItems[j]);
                        snakes[i][j] = snakesItem;
                    }
                }

                int result = quickestWayUp(ladders, snakes);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            }
        }
    }
}
