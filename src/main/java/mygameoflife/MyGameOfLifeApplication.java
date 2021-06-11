package mygameoflife;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MyGameOfLifeApplication {

    public void run(String[] args) {
        System.out.println("Welcome to My Game of Life!");
        System.out.println("Press Ctrl+C to stop.");
        System.out.println("1. Block");
        System.out.println("2. Bee Hive");
        System.out.println("3. Blinker");
        System.out.println("4. Glider");
        System.out.print("Please pick a number:");

        try (Scanner scanner = new Scanner(System.in)) {
            int number = scanner.nextInt();
            Universe universe = parse(number);

            while (true) {
                clearScreen();
                System.out.println(universe);
                TimeUnit.SECONDS.sleep(1);
                universe = universe.generate();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Number not recognized.");
        } catch (InterruptedException e) {
            System.out.println("Something terrible happened.");
        }
    }

    private Universe parse(int number) {
        switch (number) {
            case 1:
                return UniverseFixture.block();
            case 2:
                return UniverseFixture.beeHive();
            case 3:
                return UniverseFixture.blinker1();
            case 4:
                return UniverseFixture.glider1();
            default:
                throw new IllegalArgumentException("Number not recognized");
        }
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
