/*
 * info...
 */

 public class SavingsAccount {

    // Properties of the class
    private int balance;

    // Constructors
    public SavingsAccount() {
        balance = 0;
    }

    public SavingsAccount(int initialBalance) {
        balance = initialBalance;
    }

    // Methods of the class
    public void greet() {
        System.out.println("Hello!");
    }

    public void showBalance() {
        System.out.println("Your balance is: " + balance);
    }

    public void deposit(int howMuch) {
        if (howMuch < 0) {
            System.out.println("You can not deposit a negative amount.");
        }
        else {
            balance = balance + howMuch;
        }
    }

    public void withdraw(int howMuch) {
        if (howMuch < 0) {
            System.out.println("You can not withdraw a negative amount.");
        }
        else {
            balance = balance - howMuch;
        }
    }
}