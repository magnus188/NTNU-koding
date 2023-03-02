package blackjack.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class GameStatisticsTest {
	DealersTurn dt;
	
	
	private void clearStats() {
		dt.statisticsManager.clearStatistics();

	}
	
	
	@BeforeEach
	public void setUp() {
		this.dt=new DealersTurn();
		clearStats();
		String[] cards1={"S10","D10"};
		dt.setPlayersHand(cards1);
		String[] cards2={"S10","D9"};
		dt.setDealersHand(cards2);
		dt.dealersTurn();
		dt.dealersTurn();
	}

	@AfterEach
	public void cleanUp() {
		clearStats();
	}
	

	@Test
	@DisplayName("Test that the statistics are reset correctly")
	public void TestClearStatistics() {
		clearStats();
		assertEquals("0/0", dt.statisticsManager.getStatistics());
	}
	
	@Test
	@DisplayName("Test that the statistics are retrieved correctly")
	public void TestGetStatistics() {
		assertEquals("2/2",dt.statisticsManager.getStatistics());
	}
	
	@Test
	@DisplayName("Test that the statistics are updated correctly")
	public void TestAddRound() {
		String[] cards1={"S10","D9"};
		dt.setPlayersHand(cards1);
		String[] cards2={"H10","C10"};
		dt.setDealersHand(cards2);
		dt.dealersTurn();
		assertEquals("2/3",dt.statisticsManager.getStatistics());
	}
	

	
	
}
