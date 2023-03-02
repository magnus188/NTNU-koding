package blackjack.model;

public interface GameStatisticsManager{
	void addRound(String content);
	void clearStatistics();
	String getStatistics();
	
}
