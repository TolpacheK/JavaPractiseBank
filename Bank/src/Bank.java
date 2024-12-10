import java.util.ArrayList;

public class Bank {
    private String bankName;
    private ArrayList<Account> bankAccounts;

    public Bank(String bankName) {
        this.bankName = bankName;
        this.bankAccounts = new ArrayList<>();
    }

    public boolean addAccount(Account account) {
        if (isAccountIdUnique(account.getAccountId())) {
            bankAccounts.add(account);
            return true;
        }
        return false;
    }

    public boolean removeAccount(String accountId) {
        return bankAccounts.removeIf(account -> account.getAccountId().equals(accountId));
    }

    public Account getAccountById(String accountId) {
        for (Account account : bankAccounts) {
            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }
        return null;
    }

    public boolean isAccountIdUnique(String accountId) {
        return bankAccounts.stream().noneMatch(account -> account.getAccountId().equals(accountId));
    }

    public void deposit(Account account, double amount) {
        account.setBalance(account.getBalance() + amount);
    }

    public boolean withdraw(Account account, double amount) {
        if (account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            return true;
        }
        return false;
    }

    public ArrayList<Account> getAccounts() {
        return bankAccounts;
    }

    public String getBankName() {
        return bankName;
    }
}
