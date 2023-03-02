package blackjack.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/*
 * Merk at normalt sett trekkes playersBet ifra wallet før dealersTurn()
 */
public class DealersTurnTest {
	DealersTurn dt;
	
	@BeforeEach
	public void setUp() {
		dt=new DealersTurn();
	}
	
	@Test
	public void TestPlayerIsBusted(){
		String[] cards={"S10","H10","S2"};
		dt.setPlayersHand(cards);
		assertEquals(dt.dealersTurn(),"You are busted, house wins");
		assertEquals(dt.getPlayersWallet(),100);
	}
	
	@Test
	public void TestNaturalVsNot() {
		String[] cards1={"S10","S1"};
		dt.setPlayersHand(cards1);
		String[] cards2={"S8","S1","S2"};
		dt.setDealersHand(cards2);
		assertEquals(dt.dealersTurn(),"Congratulations on your natural, \nyou won : 25.0");
		assertEquals(dt.getPlayersWallet(),125);
	}
	
	@Test
	public void TestPlayerHasHighestValue() {
		String[] cards1={"S10","D10"};
		dt.setPlayersHand(cards1);
		String[] cards2={"S10","D9"};
		dt.setDealersHand(cards2);
		assertEquals(dt.dealersTurn(),"Congratulations, you won : 20.0");
		assertEquals(dt.getPlayersWallet(),120);
	}
	
	@Test
	public void TestHouseIsBusted() {
		String[] cards1={"S10","D10"};
		dt.setPlayersHand(cards1);
		String[] cards2={"S10","D10","S2"};
		dt.setDealersHand(cards2);
		assertEquals(dt.dealersTurn(),"House is busted!\nCongratulations, you won : 20.0");
		assertEquals(dt.getPlayersWallet(),120);
	}
	
	@Test
	public void TestDraw() {
		String[] cards1={"S10","D10"};
		dt.setPlayersHand(cards1);
		String[] cards2={"H10","C10"};
		dt.setDealersHand(cards2);
		assertEquals(dt.dealersTurn(),"It is draw");
		assertEquals(dt.getPlayersWallet(),110);
	}
	
	@Test 
	public void TestHouseWins() {
		String[] cards1={"S10","D9"};
		dt.setPlayersHand(cards1);
		String[] cards2={"H10","C10"};
		dt.setDealersHand(cards2);
		assertEquals(dt.dealersTurn(), "10H, 10C beats 10S, 9D");
		assertEquals(dt.getPlayersWallet(),100);
	}
	
	
}
