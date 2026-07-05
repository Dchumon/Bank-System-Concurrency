class UnsafeBankAccount {
    private int balance = 500;

    public void unsafeDeposit(int amount) {
        balance = balance + amount;
    }

    public void unsafeWithdraw(int amount) {
        balance = balance - amount;
    }

    public int unsafeGetBalance() {
        return balance;
    }
}