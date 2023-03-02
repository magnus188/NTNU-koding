package oving2;

public class Account {
    private double balance = 0.0;
    private double interestRate = 0.0;

    public Account(double balance, double interestRate) {

        if (balance > 0.0 && interestRate > 0.0) {
            this.balance = balance;
            this.interestRate = interestRate;
        } else {
            throw new IllegalArgumentException("Invalid values");
        }

    }

    public void withdraw(double amount) {
        if (amount > 0.0 && amount <= balance) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Invalid values, requires non-negaitve amount");
        }
    }

    public void deposit(double amount) {
        if (amount > 0.0) {
            balance += amount;
        } else {
            throw new IllegalArgumentException("Invalid values, requires non-negaitve amount");
        }
    }

    public void addInterest() {
        balance += (balance * interestRate / 100);
    }

    public double getBalance() {
        return balance;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        if (interestRate > 0.0) {
            this.interestRate = interestRate;
        } else {
            throw new IllegalArgumentException("Invalid values");
        }
    }
}

/*
 * SVAR PÅ SPØRSMÅL:
 * 
 * - Forklar hvorfor metodene over kan sies å være en komplett innkapsling av
 * tilstanden?
 * 
 * Fordi alle datafeltene er private og kan kun endres ved hjelp av innebygde metoder i klassen.
 * Det er ingen annen måte å endre variablene på, uten å benytte disse metodene.
 * Metodene har også validerings-funksjonalitet, så man kan garantere at verdiene er korrekte og at tilstanden er gyldig.
 * 
 * - Er denne klassen data-orientert eller tjeneste-orientert? Begrunn svaret!
 * 
 * Denne klassen er dataorientert da klassen hovedsaklig har som formål å lagre og håndtere data,
 * og ikke har fokus på å gjøre beregninger og operasjoner på data (tjenesteorientert).
 */