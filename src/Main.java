import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your input: ");
        String input = sc.nextLine();

        Calculator calculator = new Calculator();
        double result = calculator.evaluate(input);
        System.out.printf("%.2f",result);
    }
}
