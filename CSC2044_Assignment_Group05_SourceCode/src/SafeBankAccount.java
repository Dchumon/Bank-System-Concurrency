public class SafeBankAccount {
    private int balance = 500;

    public synchronized void safeDeposit(int amount) {
        balance = balance + amount;
    }

    public synchronized void safeWithdraw(int amount) {
        balance = balance - amount;
    }

    public synchronized int safeGetBalance() {
        return balance;
    }
}
