package client;

import java.util.Deque;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import model.GameEngineImpl;
import model.SimplePlayer;
import model.PlayingCardImpl;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import validate.Validator;
import view.AppFrame;
import view.GameEngineCallbackGUI;
import view.GameEngineCallbackImpl;

/**
 * Simple console client for SADI assignment 2, 2018
 * NOTE: This code will not compile until you have implemented code for the supplied interfaces!
 * 
 * You must be able to compile your code WITHOUT modifying this class.
 * Additional testing should be done by copying and adding to this class while ensuring this class still works.
 * 
 * The provided Validator.jar will check if your code adheres to the specified interfaces!
 * 
 * @author Caspar Ryan
 * 
 */
public class SimpleTestClient
{
   private static Logger logger = Logger.getLogger("assignment1");

   public static void main(String args[])
   {
      final GameEngine gameEngine = new GameEngineImpl();

      // call method in Validator.jar to test *structural* correctness
      // just passing this does not mean it actually works .. you need to test yourself!
      // pass false if you want to disable logging .. (i.e. once it passes)
//      Validator.validate(true);

      // create two test players
//      Player[] players = new Player[] { new SimplePlayer("1", "The Shark", 1000), new SimplePlayer("2", "The Loser",
//         500) };

      // add logging callback
      gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
      
      SwingUtilities.invokeLater(new Runnable()
	  {
		  @Override
		  public void run()
		  {
			  new AppFrame(gameEngine).setVisible(true);;
		  }
	  });

   }

   @SuppressWarnings("unused")
   private static void printCards(Deque<PlayingCard> deck)
   {
      for (PlayingCard card : deck)
         logger.log(Level.INFO, card.toString());
   }
}

