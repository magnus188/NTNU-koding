package blackjack.model;

public class Wallet{
	private double wallet;
	
	public Wallet(double number) {
		deposit(number);
	}
	
	public void deposit(double number) {
		if (isValidAmount(number)) {
			this.wallet += number;
		}
	}
	
	public void withdraw(double number) throws IllegalArgumentException,IllegalStateException{
		if(number>wallet)
			throw new IllegalStateException("Can't withdraw more money than in your wallet");

		if(isValidAmount(number))
			this.wallet-=number;
	}
	
	public double getWallet() {
		return wallet;
	}
	
	private boolean isValidAmount(double number) {
		if(number<=0)
			throw new IllegalArgumentException("Amount must be positive");
		return true;
	}
	
	
	
}
