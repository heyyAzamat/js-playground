/* Data types in Java
    boolean - true or false
    byte - from -128 to 127
    short - from -32 768 to 32 767
    int -  from -2 147 483 648 to 2 147 483 647
    long - from -9 223 372 036 854 775 to 9 223 372 036 854 775 807
    float - floating-point numbers (0,452)
    double - large floating-point numbers
    char - symbols ('n')
*/

// public class HelloWorld {
//     public static void main(String[] args) {
//         int x = 20;
//         if (x == 20) {
//             System.out.println(++x);
//         } else {
//             System.out.println("Error occurred");
//         }
//     }
// }

import java.util.Random;
import java.util.Scanner;

public class jsplayground {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int min = 1;
        int max = 10;
        int number = random.nextInt(max - min + 1) + min;

        System.out.println("Welcome to the game Guess the Number");
        System.out.println("I asked for a number from 1 to 10. Try to guess.");

        while (true) {
            System.out.println("Enter your hunch: ");
            int guess = scanner.nextInt();

            if (guess == number) {
                System.out.println("Congratulations! You guessed the number: " + number);
                break;
            } else if (guess < number) {
                System.out.println("Try more");
            } else {
                System.out.println("Try less");
            }
        }
        scanner.close();
    }
}