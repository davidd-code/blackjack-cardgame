package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.interfaces.Player;

/**
 * Status panel which will indicate what the program is doing at any point in time.
 * 
 * @author David Doan
 *
 */
public class StatusBarPanel extends JPanel
{
	
	JLabel playerLabel;
	JLabel statusLabel;
	
	public StatusBarPanel()
	{
		// Initial state when no players are in the game
		statusLabel = new JLabel("Status: Waiting for players to join.");
		setLayout(new GridLayout(1,2));
		// Creating the border
		statusLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(statusLabel);
		
		// Label which shows the current selected player
		playerLabel = new JLabel("Player: ");
		playerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(playerLabel);
	}
	
	public void displaySelected(String playerName)
	{
		playerLabel.setText("Player: " + playerName);
	}
	
	public void dealingPlayer(Player p)
	{
		String labelString = String.format("Status: Dealing to %s", p.getPlayerName());
		statusLabel.setText(labelString);
	}
	
	public void playerBusts(Player p)
	{
		String labelString = String.format("Status: %s busts", p.getPlayerName());
		statusLabel.setText(labelString);
	}
	
	public void dealingHouse()
	{
		String labelString = "Status: Dealing to the House";
		statusLabel.setText(labelString);
	}
	
	public void houseBusts()
	{
		String labelString = "Status: House busts";
		statusLabel.setText(labelString);
	}
	
	
	public void waiting()
	{
		String labelString = "Status: Waiting for bets";
		statusLabel.setText(labelString);
	}	
	
}
