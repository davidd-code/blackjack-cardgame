package view;

import java.util.Collection;
import java.util.Deque;
import java.util.logging.Level;

import javax.swing.SwingUtilities;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

/**
 * This class will be responsible for managing all of the graphical updates as the game is played.
 * 
 * @author David Doan
 */
public class GameEngineCallbackGUI implements GameEngineCallback 
{
	SummaryText summary;
	GamePanel main;
	DropDownMenu menu;
	StatusBarPanel status;
	
	public GameEngineCallbackGUI(SummaryText summary, StatusBarPanel status,DropDownMenu menu, GamePanel main)
	{
		this.summary = summary;
		this.status = status;
		this.menu = menu;
		this.main = main;
	}
	
	/**
	 * Sets the status bar panel indicate which player is being dealt to. Also updates the correct panel depending on the player
	 * that is passed in as a parameter.
	 */
	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine) 
	{
		status.dealingPlayer(player);
		for(PlayerCardPanel panel: main.getlistOfPanels())
		{
			if(panel.getPlayer().toString().equals(player.toString())) 
			{
				main.dealCardPlayer(panel, card);
			}
		}
	}

	
	// Similar to the function above except it will update the panel with the card that caused the player to bust
	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) 
	{
		status.playerBusts(player);
		for(PlayerCardPanel panel: main.getlistOfPanels())
		{
			if(panel.getPlayer().toString().equals(player.toString())) 
			{
				main.playerBustCard(panel, card);
			}
		}
	}

	// Similar to the function above except it will display the result on the panel
	@Override
	public void result(Player player, int result, GameEngine engine) 
	{
		status.playerBusts(player);
		for(PlayerCardPanel panel: main.getlistOfPanels())
		{
			if(panel.getPlayer().toString().equals(player.toString())) 
			{
				main.playerResult(panel, result);
			}
		}
	}

	// Similar  to nextCard function but for the dealer panel instead
	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) 
	{
		status.dealingHouse();
		main.updateDealerPanel(card);
	}

	// Similar to bustCard but for the dealer panel
	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) 
	{
		status.houseBusts();
		main.dealerBustPanel(card);
	}
	/**
	 * Similar to result function except for the dealer. When this function is called, the round is over and it 
	 * will set the status bar to "waiting for bets". 
	 */
	@Override
	public void houseResult(int result, GameEngine engine) 
	{
		status.waiting();
		main.dealerResult(result);
		for(PlayerCardPanel panel: main.getlistOfPanels())
		{
			main.playerWin(panel, result);	
		}
		summary.updateSummary(engine);
	}

}
