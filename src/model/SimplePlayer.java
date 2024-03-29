package model;

import model.interfaces.Player;

public class SimplePlayer implements Player
{
	private String playerId;
	private String playerName;
	private int points, bet, result;
	
	// Constructor that takes 3 arguments
	public SimplePlayer(String playerId, String playerName, int initialPoints)
	{
		this.playerId = playerId;
		this.playerName = playerName;
		this.points = initialPoints;
	}
	
   /**
    * @return human readable player name
    */
	@Override
	public String getPlayerName() 
	{	
		return playerName;
	}
	
   /**
    * @param playerName
    *            human readable player name
    */
	@Override
	public void setPlayerName(String playerName) 
	{
		this.playerName = playerName;
	}
	
   /**
    * 
    * @return number of points from setPoints()
    */
	@Override
	public int getPoints() 
	{
		return points;
	}
	
   /**
    * @param points
    *            for betting (updated by GameEngineImpl with each win or loss)
    */
	@Override
	public void setPoints(int points) 
	{
		this.points = points;
	}
	
   /**
    * @return the player ID which is generated by the implementing class
    */
	@Override
	public String getPlayerId() 
	{
		return playerId;
	}
	
	/**
	 * If the player has enough points to place the bet then return true, otherwise
	 * return false.
	 */
	@Override
	public boolean placeBet(int bet) 
	{
		// TODO Auto-generated method stub
		if(points >= bet && bet >= 0)
		{
			this.bet = bet;
			//points = points - bet;
			return true;
		}
		else
		{
			return false;
		}
	}
	
   /**
    * @return the bet as set with placeBet()
    */
	@Override
	public int getBet() 
	{
		return bet;
	}
	
   /**
    * reset the bet to 0 for next round (in case player does not bet again in next round)
    */
	@Override
	public void resetBet() 
	{
		this.bet = 0;
	}
	
   /**
    * @return the result of the most recent hand
    */
	@Override
	public int getResult() 
	{
		return result;
	}

   /**
    * @param result
    *            the result of the most recent hand
    */
	@Override
	public void setResult(int result) 
	{
		this.result = result;
	}
	
	public String toString()
	{
		// TODO finish overriding this method
		return this.playerName;
	}

}
