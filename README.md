# JavaPractiseBank
BankApp
Тут був Ствроста
public class Account {

    private double balance;
    private String name;
    private String accountId;

     public Account(double balance, String name, String accountId) {
        this.balance = balance;
        this.name = name;
        this.accountId = accountId;
    }

    public Account(){
         this.balance = 0;
         this.name = "";
         this.accountId = "";
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
    public void setName() {
        this.name = name;
    }
    public String getAccountId(){
       return accountId;
    }
    public void setAccountId(String accountId){
        this.accountId = accountId;
    }


}

