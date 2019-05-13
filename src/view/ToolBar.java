package view;

import javax.swing.JButton;
import javax.swing.JToolBar;

import controller.AddPlayerController;
import controller.DealCardsController;
import controller.PlaceBetController;
import model.interfaces.GameEngine;

/**
 * Tool bar which will act as the container for the 3 buttons which will be used in this program.
 * @author David Doan
 *
 */
public class ToolBar extends JToolBar
{
	
	public ToolBar(GameEngine engine, SummaryText summary, DropDownMenu menu, GamePanel main) 
	{
		// Add player button, used to add a player in the game
		JButton addPlayer = new JButton("Add Player");
		// Add the action listener
		AddPlayerController add = new AddPlayerController(engine, summary, menu, main);
		addPlayer.addActionListener(add);
		
		// Place bet button, sets the bet of the selected player to input of the user
		JButton placeBet = new JButton("Place Bet");
		PlaceBetController bet = new PlaceBetController(engine, summary, menu, main);
		placeBet.addActionListener(bet);
		
		// Deal cards button, this button will start the round when the user wants the dealer to deal without having to place a bet for all of the players
		JButton dealCards = new JButton("Deal Cards");
		DealCardsController deal = new DealCardsController(engine, menu, summary, main);
		dealCards.addActionListener(deal);
		
		add(addPlayer);
		add(placeBet);
		add(dealCards);
		
		setFloatable(false);
	}
}
