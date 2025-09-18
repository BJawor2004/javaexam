import java.sql.SQLOutput;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true)
        {
            System.out.println("What you wanna do?");
            System.out.println("1.Add");
            System.out.println("2.Sub");
            System.out.println("3.Multiply");
            System.out.println("4.Divide");
            System.out.println("5.Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice)
            {
                case 1:
                    System.out.println("Enter first number: ");
                    double a = scanner.nextDouble();
                    System.out.println("Enter second number: ");
                    double b = scanner.nextDouble();
                    Add add = (a1,b1) -> a+b; // <- interface
                    double result = add.add(a,b);
                    System.out.println("Result: " + result);
                    break;
                case 2:
                    System.out.println("Enter first number: ");
                    double a2 = scanner.nextDouble();
                    System.out.println("Enter second number: ");
                    double b2 = scanner.nextDouble();
                    Sub sub = (a3, b3) -> a2-b2; // <- interface
                    double result2 = sub.sub(a2,b2);
                    System.out.println("Result: " + result2);
                    break;
                case 3:
                    System.out.println("Enter first number: ");
                    double a3 = scanner.nextDouble();
                    System.out.println("Enter second number: ");
                    double b3 = scanner.nextDouble();
                    Mul mul = (a4, b4) -> a3*b3; // <- interface
                    double result3 = mul.mul(a3,b3); 
                    System.out.println("Result: " + result3);
                    break;
                case 4:
                    System.out.println("Enter first number: ");
                    double a4 = scanner.nextDouble();
                    System.out.println("Enter second number: ");
                    double b4 = scanner.nextDouble();
                    Div div = (a5, b5) -> a4/b4; // <- interface
                    if(b4 == 0)
                    {
                        System.out.println("Can't divide by zero");
                    }
                    double result4 = div.div(a4,b4);
                    System.out.println("Result: " + result4);
                    break;
                case 5:
                    System.out.println("Quitting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
