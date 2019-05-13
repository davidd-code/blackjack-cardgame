package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.DropDownMenu;
import view.GamePanel;
import view.SummaryText;

/**
 * This is the listener for the "Place Bet" button. It will set the bet of the player that is currently selected 
 * and when all of the players have made a bet, the dealer will automatically start dealing cards to each player.
 * @author David Doan
 *
 */
public class PlaceBetController implements ActionListener 
{
	private GameEngine engine;
	private DropDownMenu menu;
	private SummaryText text;
	private GamePanel main;
	
	public PlaceBetController(GameEngine engine, SummaryText text, DropDownMenu menu, GamePanel main)
	{
		this.engine = engine;
		this.menu = menu;
		this.text = text;
		this.main = main;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		Player selectedPlayer = (Player) menu.getSelectedItem();
		int bet;
		
		// While loop which will keep prompting for input until the user has input a positive integer
		while(true)
		{
			try
			{
				String input = JOptionPane.showInputDialog("Please enter the desired bet amount.");
				if(input != null)
				{
					bet = Integer.parseInt(input);
					if(selectedPlayer.placeBet(bet) == true) 
					{
						break;
					}
					// If the user has input a negative integer then a different dialog will be displayed
					else
					{
						JOptionPane.showMessageDialog(null, "Not enough points to place that bet, please try again.");
					}
				}
			}
			// If the user has input anything that is not a number then this dialog will be displayed
			catch(NumberFormatException ne)
			{
				JOptionPane.showMessageDialog(null, "You must enter positive integer, please try again.");
			}
		}
		
		// Boolean which will only activate when all players have made a bet
		boolean valid = true;
		for(Player player: engine.getAllPlayers())
		{
			if(player.getBet() == 0 && player.getPoints() > 0)
			{
				valid = false;
			}
		}
		
		if(valid)
		{
			new Thread()
			{
				@Override
				public void run()
				{
					for(Player player: engine.getAllPlayers())
					{
						main.clearPlayerPanel(player);
						engine.dealPlayer(player, 1000);
					}
					main.clearDealerPanel();
					engine.dealHouse(1000);					
				}
			}.start();
			
			text.updateSummary(engine);
			
		}

		text.updateSummary(engine);
	}
	
}
