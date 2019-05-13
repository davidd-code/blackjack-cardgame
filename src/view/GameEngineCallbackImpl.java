package view;

import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

/**
 * 
 * Skeleton/Partial example implementation of GameEngineCallback showing Java logging behaviour
 * 
 * @author Caspar Ryan
 * @see view.interfaces.GameEngineCallback
 * 
 */
public class GameEngineCallbackImpl implements GameEngineCallback
{
   private final Logger logger = Logger.getLogger(this.getClass().getName());

   public GameEngineCallbackImpl()
   {
      // FINE shows dealing output, INFO only shows result
      logger.setLevel(Level.FINE);
   }

   /**
    * called for each card as the house is dealing to a Player, use this to
    * update your display for each card or log to console
    * 
    * @param player
    *            the Player who is receiving cards
    * @param card
    *            the next card that was dealt
    * @param engine
    *            a convenience reference to the engine so the receiver can call
    *            methods if necessary
    */
   @Override
   public void nextCard(Player player, PlayingCard card, GameEngine engine)
   {
	  // Check if player exists inside of the collection of players in the GameEngine
	  if(engine.getAllPlayers().contains(player))
	  {
		  logger.log(Level.FINE, "Card Dealt to " + player.getPlayerName() + " .. " + card.toString());		  
	  }
   }
   
   /**
    * called when the player has bust with final result (result is score prior
    * to the last card that caused the bust)
    * 
    * @param player
    *            the current Player
    * @param result
    *            the final score of the hand
    * @param engine
    *            a convenience reference to the engine so the receiver can call
    *            methods if necessary
    * @see model.interfaces.GameEngine
    */
   @Override
   public void result(Player player, int result, GameEngine engine)
   {
      // final results logged at Level.INFO
	  // If player exists in the players collection inside the GameEngine
	  if(engine.getAllPlayers().contains(player))
	  {
		  logger.log(Level.INFO, player.toString() + ", final result=" + player.getResult());
	  }
   }
   
   /**
    * called when the card causes the player to bust 
    * this method is called instead of {@link #nextCard(Player, PlayingCard, GameEngine)} 
    * this method is called before {@link #result(Player, int, GameEngine)} 
    * use this to update your display for each card or log to console
    * 
    * @param player
    *            the Player who is receiving cards
    * @param card
    *            the bust card that was dealt
    * @param engine
    *            a convenience reference to the engine so the receiver can call
    *            methods if necessary
    * @see model.interfaces.GameEngine
    */
	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) 
	{
		// If player exists in the players collection inside the GameEngine
		if(engine.getAllPlayers().contains(player))
	  	{
    		logger.log(Level.FINE, "Card Dealt to " + player.getPlayerName() + " .. " + card.toString() + " ... YOU BUSTED!");
	  	}
	}

   /**
    * called as the house is dealing their own hand, use this to update your
    * display for each card or log to console
    * 
    * @param card
    *            the next card that was dealt
    * @param engine
    *            a convenience reference to the engine so the receiver can call
    *            methods if necessary
    * @see model.interfaces.GameEngine
    */
	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) 
	{
		logger.log(Level.FINE, "Card Dealt to House .. " + card.toString());
	}
	
   /**
    * HOUSE version of 
    * {@link GameEngineCallback#bustCard(Player, PlayingCard, GameEngine)}
    * 
    * @param card
    *            the bust card that was dealt
    * @param engine
    *            a convenience reference to the engine so the receiver can call
    *            methods if necessary
    * @see model.interfaces.GameEngine
    */
	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) 
	{
		logger.log(Level.FINE, "Card Dealt to House .. " + card.toString() + " ... HOUSE BUSTED!");
	}
	
   /**
    * called when the HOUSE has bust with final result (result is score prior to the last card
    * that caused the bust)
    * 
    * PRE-CONDITION: This method should only be called AFTER bets have been updated on all Players 
    * so this callback can log Player results
    * 
    * Called from {@link GameEngine#dealHouse(int)} 
    * 
    * @param result
    *            the final score of the dealers (house) hand
    * @param engine
    *            a convenience reference to the engine so the receiver can call
    *            methods if necessary
    * @see model.interfaces.GameEngine
    */
	@Override
	public void houseResult(int result, GameEngine engine) 
	{
		// Create string buffer which will be used to log the final results of each player after each round
		StringBuffer buffer = new StringBuffer();
		
		// Iterate through every player inside of the player collection in GameEngine
		for(Player player: engine.getAllPlayers())
		{
			// Add the final result to the StringBuffer with a newline at the end for each player
			buffer.append("Player: id=" + player.getPlayerId() +", name=" +
					player.getPlayerName() + ", points=" +player.getPoints() + "\n");
		}
		logger.log(Level.INFO, "House, final result=" + result);
		logger.log(Level.INFO, "Final Player Results\n" + buffer);
	}
}
