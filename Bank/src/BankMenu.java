import java.util.ArrayList;
import java.util.Scanner;

public class BankMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<Bank> banks = new ArrayList<>();

    public void runApp() {
        System.out.println("Welcome to the Bank Management System!");
        addSampleData();
        while (true) {
            showMenu();
            int choice = getInputAsInt("Enter your choice: ");
            if (choice == 0) break;
            handleMenuChoice(choice);
        }

        System.out.println("Exit completed. Thank you!");
    }

    private void addSampleData() {
        Bank bank1 = new Bank("Monobank");
        Bank bank2 = new Bank("PUMB Bank");
        Bank bank3 = new Bank("DUIKT Bank");

        bank1.addAccount(new Account("Chabanuk Violetta", "9780743273565", 105700.0));
        bank1.addAccount(new Account("Zelinskyi Yaroslav", "1234567890", 1488.0));

        bank2.addAccount(new Account("Pyzko Denis", "9876543210", 156500.0));
        bank2.addAccount(new Account("Naumova Veronika", "5432167890", 1200.5));

        bank3.addAccount(new Account("Budko Katya", "1122334455", 300.0));
        bank3.addAccount(new Account("Amelin Danil", "9988776655", 750.0));


        banks.add(bank1);
        banks.add(bank2);
        banks.add(bank3);

        System.out.println("Sample data added.");
    }

    private void showMenu() {
        System.out.println("""
            Please choose an option:
            1: Add bank
            2: Add account
            3: View accounts in a bank
            4: Deposit to an account
            5: Withdraw from an account
            6: Remove account
            0: Exit
        """);
    }

    private void handleMenuChoice(int choice) {
        switch (choice) {
            case 1 -> addBank();
            case 2 -> addAccount();
            case 3 -> viewAccountsInBank();
            case 4 -> depositToAccount();
            case 5 -> withdrawFromAccount();
            case 6 -> removeAccount();
            default -> System.out.println("Invalid choice. Try again.");
        }
    }

    private void addBank() {
        System.out.print("Enter bank name: ");
        String bankName = scanner.nextLine();
        banks.add(new Bank(bankName));
        System.out.println("Bank added: " + bankName);
    }

    private void addAccount() {
        Bank bank = chooseBank();
        if (bank == null) return;

        System.out.print("Enter account holder's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter account ID: ");
        String accountId = scanner.nextLine();
        System.out.print("Enter initial balance: ");
        double balance = scanner.nextDouble();
        scanner.nextLine(); // Clear buffer

        Account account = new Account(name, accountId, balance);
        if (bank.addAccount(account)) {
            System.out.println("Account added: " + account);
        } else {
            System.out.println("Account ID is not unique.");
        }
    }

    private void viewAccountsInBank() {
        Bank bank = chooseBank();
        if (bank == null) return;

        System.out.println("Accounts in " + bank.getBankName() + ":");
        bank.getAccounts().forEach(System.out::println);
    }

    private void depositToAccount() {
        Bank bank = chooseBank();
        if (bank == null) return;

        Account account = chooseAccount(bank);
        if (account == null) return;

        System.out.print("Enter deposit amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Clear buffer

        bank.deposit(account, amount);
        System.out.println("Deposit successful. New balance: " + account.getBalance());
    }

    private void withdrawFromAccount() {
        Bank bank = chooseBank();
        if (bank == null) return;

        Account account = chooseAccount(bank);
        if (account == null) return;

        System.out.print("Enter withdrawal amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Clear buffer

        if (bank.withdraw(account, amount)) {
            System.out.println("Withdrawal successful. New balance: " + account.getBalance());
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    private void removeAccount() {
        Bank bank = chooseBank();
        if (bank == null) return;

        System.out.print("Enter account ID to remove: ");
        String accountId = scanner.nextLine();

        if (bank.removeAccount(accountId)) {
            System.out.println("Account removed.");
        } else {
            System.out.println("Account not found.");
        }
    }

    private Bank chooseBank() {
        System.out.println("Available banks:");
        for (int i = 0; i < banks.size(); i++) {
            System.out.println(i + ": " + banks.get(i).getBankName());
        }

        int index = getInputAsInt("Choose a bank: ");
        if (index >= 0 && index < banks.size()) {
            return banks.get(index);
        }

        System.out.println("Invalid bank choice.");
        return null;
    }

    private Account chooseAccount(Bank bank) {
        System.out.println("Available accounts:");
        ArrayList<Account> accounts = bank.getAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(i + ": " + accounts.get(i));
        }

        int index = getInputAsInt("Choose an account: ");
        if (index >= 0 && index < accounts.size()) {
            return accounts.get(index);
        }

        System.out.println("Invalid account choice.");
        return null;
    }

    private int getInputAsInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
