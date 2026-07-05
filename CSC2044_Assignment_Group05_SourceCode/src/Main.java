class Main {
    public static void main(String[] args) throws Exception{
        //Task 2: Sequential Transaction Processing
        BankAccount account1 = new BankAccount();
        System.out.println(">>>>> Sequential Transaction Processing Output <<<<<");

        System.out.println("Initial account balance: RM" + account1.getBalance());
        System.out.println("\nStarting Transaction 1: Withdraw RM200");
        Thread.sleep(2000);
        account1.withdraw(200);
        System.out.println("Finished Transaction 1");
        System.out.println("Current balance: RM" + account1.getBalance());

        System.out.println("\nStarting Transaction 2: Apply Service Charge");
        Thread.sleep(1000);
        account1.serviceTax();
        System.out.println("Finished Transaction 2");
        System.out.println("Current balance: RM" + account1.getBalance());

        System.out.println("\nStarting Transaction 3: Deposit RM1000");
        Thread.sleep(2000);
        account1.deposit(1000);
        System.out.println("Finished Transaction 3");
        System.out.println("Current balance: RM" + account1.getBalance());

        System.out.println("\nStarting Transaction 4: Apply Service Charge");
        Thread.sleep(1000);
        account1.serviceTax();
        System.out.println("Finished Transaction 4");
        System.out.println("Current balance: RM" + account1.getBalance());

        System.out.println("\nStarting Transaction 5: Deposit RM300");
        Thread.sleep(2000);
        account1.deposit(300);
        System.out.println("Finished Transaction 5");
        System.out.println("Current balance: RM" + account1.getBalance());

        System.out.println("\nStarting Transaction 6: Apply Service Charge");
        Thread.sleep(1000);
        account1.serviceTax();
        System.out.println("Finished Transaction 6");
        System.out.println("Current balance: RM" + account1.getBalance());

        System.out.println("\nStarting Transaction 7: Withdraw RM600");
        Thread.sleep(2000);
        account1.withdraw(600);
        System.out.println("Finished Transaction 7");
        System.out.println("Current balance: RM" + account1.getBalance());

        System.out.println("\nStarting Transaction 8: Apply Service Charge");
        Thread.sleep(1000);
        account1.serviceTax();
        System.out.println("Finished Transaction 8");
        System.out.println("Current balance: RM" + account1.getBalance());

        //Task 3: Concurrent Transaction Processing
        BankAccount account2 = new BankAccount();
        System.out.println("\n>>>>> Concurrent Transaction Processing Output <<<<<");

        // Thread 1 - Deposit
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " started Deposit RM200");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " was interrupted.");
            }
            account2.deposit(200);
            System.out.println(Thread.currentThread().getName() + " finished Deposit RM200");
        });

        // Thread 2 - Withdraw
        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " started Withdraw RM100");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " was interrupted.");
            }
            account2.withdraw(100);
            System.out.println(Thread.currentThread().getName() + " finished Withdraw RM100");
        });

        // Thread 3 - Balance Check
        Thread t3 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " started Balance Check");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " was interrupted.");
            }
            System.out.println(Thread.currentThread().getName() + " finished Balance Check");
        });

        // Thread 4 - Service Charge
        Thread t4 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " started Service Charge");
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " was interrupted.");
            }
            account2.serviceTax();
            System.out.println(Thread.currentThread().getName() + " finished Service Charge");
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();

        //Task 5: Race condition demonstration
        UnsafeBankAccount account3 = new UnsafeBankAccount();

        Thread t5 = new Thread(() -> {
            for(int i = 1; i <= 10000; i++) {
                account3.unsafeDeposit(1);
            }
        });

        Thread t6 = new Thread(() -> {
            for(int i = 1; i <= 5000; i++) {
                account3.unsafeWithdraw(1);
            }
        });

        t5.start();
        t6.start();
        t5.join();
        t6.join();

        System.out.println("\n>>>>> Race Condition Demonstration Output <<<<<");
        System.out.println("Expected balance: 5500");
        System.out.println("Calculated balance: " + account3.unsafeGetBalance());

        //Task 6: Synchronization Solution
        SafeBankAccount account4 = new SafeBankAccount();

        Thread t7 = new Thread(() -> {
            for(int i = 1; i <= 10000; i++) {
                account4.safeDeposit(1);
            }
        });
        Thread t8 = new Thread(() -> {
            for(int i = 1; i <= 5000; i++) {
                account4.safeWithdraw(1);
            }
        });

        t7.start();
        t8.start();
        t7.join();
        t8.join();

        System.out.println("\n>>>>> Synchronization Solution Output <<<<<");
        System.out.println("Expected balance: 5500");
        System.out.println("Calculated balance: " + account4.safeGetBalance());
    }
}