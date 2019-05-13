package view;

import java.awt.Color;

//import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.interfaces.GameEngine;
import model.interfaces.Player;

/**
 * This class is used to display the summary which shows the total points of all of the players and their bets for the current round
 * 
 * @author David Doan
 *
 */
public class SummaryText extends JTextArea
{

	public SummaryText(GameEngine engine)
	{
		// Set background color
		setBackground(Color.BLACK);
		updateSummary(engine);
		// Set font to white
		this.setForeground(Color.white);
		// Disable user from editing the text
		setEditable(false);
	}
	
	/**
	 * Searches the GameEngine and displays all of the players and their points. Also shows their current bets.
	 * @param engine
	 */
	public void updateSummary(GameEngine engine) 
	{
		this.setText("");
		this.append("\n  Summary Panel  \n\n");
		
		for(Player player: engine.getAllPlayers()) 
		{
			String newLine = String.format("  Player: %s | Points: %d  \n", player.getPlayerName(), player.getPoints());
			this.append(newLine);
			
			
		}
		
		this.append("\n\n  Bets this Round  \n\n");
		for(Player player: engine.getAllPlayers()) 
		{
			String newLine = String.format("  Player: %s | Bet: %d  \n", player.getPlayerName(), player.getBet());
			this.append(newLine);
			
		}
	}
}
