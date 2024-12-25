//Амелін Даніїл
public class Account {
    private double balance;
    private String name;
    private String accountId;

    public Account(String name, String accountId, double initialBalance) {
        this.name = name;
        this.accountId = accountId;
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public String getAccountId() {
        return accountId;
    }

    @Override
    public String toString() {
        return "Account{" +
                "Name='" + name + '\'' +
                ", Account ID='" + accountId + '\'' +
                ", Balance=" + balance +
                '}';
    }
}
