public class BankAccount {
    private int balance = 500;
    private final Object lock = new Object();

    public void deposit(int amount) {
        synchronized (lock) {
            balance += amount;
        }
    }

    public void withdraw(int amount) {
        synchronized (lock) {
            balance -= amount;
        }
    }

    public int getBalance() {
        synchronized (lock) {
            return balance;
        }
    }

    public void serviceTax() {
        synchronized (lock) {
            balance -= 5;
        }
    }
}
