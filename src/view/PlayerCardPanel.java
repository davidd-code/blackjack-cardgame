package view;

//import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

import model.interfaces.Player;

/**
 * This class will be used to hide and display the correct panel based on the player that is selected
 * @author David Doan
 *
 */
public class PlayerCardPanel extends JPanel
{
	private Player player;
	
	public PlayerCardPanel(Player thisPlayer)
	{
		super(new GridLayout(16,1));
		this.player = thisPlayer;
	}
	
	public Player getPlayer()
	{
		return this.player;
	}
	

}
