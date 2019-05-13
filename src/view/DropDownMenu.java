package view;

import java.awt.Color;

import javax.swing.JComboBox;

import controller.MenuController;
import model.interfaces.GameEngine;
import model.interfaces.Player;

/**
 * This component of the GUI is used to select the player. When a player is selected, the PlayerCardPanel that 
 * corresponds to that particular player will be visible and the user will be able to place a bet for the player 
 * that is currently selected.
 * 
 * @author David Doan
 *
 */
public class DropDownMenu extends JComboBox 
{
	StatusBarPanel status;
	
	@SuppressWarnings("unchecked")
	public DropDownMenu(GameEngine engine, StatusBarPanel status, GamePanel main) 
	{
		// Retrieve all of the players from the Game Engine and place them all into the JComboBox as an array
		super(engine.getAllPlayers().toArray());

		this.status = status;
		// Create and add the itemListener for this component 
		MenuController listener = new MenuController(engine, this, status, main);
		this.addItemListener(listener);
	}
	
	/**
	 * This function is called everytime a new player is added to the game. It will add that player to the drop down
	 * menu so that the player can be selected.
	 * 
	 * @param newPlayer
	 */
	public void updateMenu(Player newPlayer)
	{
		super.addItem(newPlayer);
	}
	
}
