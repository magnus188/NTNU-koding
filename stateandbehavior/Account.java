package stateandbehavior;

public class Account {
    private double balance = 0.0;
    private  double interestRate = 0.0;


    public void deposit(double amount) {
        if (amount > 0.0) {
            balance += amount;
        }
    }

    public void addInterest() {
        balance += (balance * interestRate/100);
    }

    public double getBalance() {
        return balance;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
