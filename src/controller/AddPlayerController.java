package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.DropDownMenu;
import view.GamePanel;
import view.PlayerCardPanel;
import view.SummaryText;

/**
 * This is the listener for the "Add Player" button, when the button is clicked, this class will add the player into the game engine
 * and call the necessary functions to update the user interface accordingly.
 * 
 * @author David Doan
 *
 */
public class AddPlayerController implements ActionListener 
{
	private static int playerID = 0;
	private GameEngine engine;
	private SummaryText text;
	private DropDownMenu menu;
	private GamePanel main;
	
	public AddPlayerController(GameEngine engine, SummaryText text, DropDownMenu menu, GamePanel main)
	{
		this.engine = engine;
		this.text = text;
		this.menu = menu;
		this.main = main;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		int balance;
		String name = JOptionPane.showInputDialog("Please enter the username of the new player.");
		// If the user has not input an empty string
		if(name != null) 
		{
			// While loop which will keep asking the user for input until they have input a positive integer
			while(true)
			{
				try 
				{
					String balanceInput = JOptionPane.showInputDialog("Please enter the starting balance for this player.");
					balance = Integer.parseInt(balanceInput);	
					// Let the users out of the loop if conditions met
					break;
				}
				// Display message and prompt the user for input again
				catch(NumberFormatException ne)
				{
					JOptionPane.showMessageDialog(null, "You must enter positive integer, please try again.");
				}
			}
			
			// Creating new player
			Player newPlayer = new SimplePlayer(Integer.toString(++playerID), name, balance);
			engine.addPlayer(newPlayer);
			PlayerCardPanel newPanel = new PlayerCardPanel(newPlayer);
			
			// Update summary pannel
			text.updateSummary(engine);
			// Add the PlayerCardPanel to the ArrayList
			main.addPlayerPanels(newPanel);
			// Add player to the drop down menu
			menu.updateMenu(newPlayer);
			// Display the panel of the player that is currently chosen
			main.displayCorrectPanel((Player) menu.getSelectedItem());
			
			
			
		}
	}

}
