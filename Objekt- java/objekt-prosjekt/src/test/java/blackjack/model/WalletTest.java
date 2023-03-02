package blackjack.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WalletTest{
	Wallet wallet;
	
	@BeforeEach
	public void setup() {
		wallet=new Wallet(100);
	}
	
	@Test
	public void TestGetWallet() {
		assertEquals(wallet.getWallet(),100);
	}
	
	@Test
	public void TestDeposit() {
		wallet.deposit(100);
		assertEquals(wallet.getWallet(),200);
		assertThrows(IllegalArgumentException.class,() -> {
			wallet.deposit(-5);
		});
	}
	@Test
	public void TestWithdraw() {
		wallet.withdraw(50);
		assertEquals(wallet.getWallet(),50);
		assertThrows(IllegalArgumentException.class,() -> {
			wallet.withdraw(-51);
		});
		assertEquals(wallet.getWallet(),50);
		assertThrows(IllegalStateException.class,() -> {
			wallet.withdraw(51);
		});
		
	}
	
	
	
}