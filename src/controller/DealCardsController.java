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
 * This is the ActionListener for the "Deal Cards" button. This program will begin dealing when all players have made a bet but
 * when this button is clicked, the dealer will only deal cards to the players who have made a bet that is greater than 0 and so
 * players who have not made a bet will be left out of this round and no cards will be dealt to them.
 * 
 * @author David Doan
 *
 */
public class DealCardsController implements ActionListener 
{
	GameEngine engine;
	SummaryText text;
	DropDownMenu menu;
	GamePanel main;
	
	public DealCardsController(GameEngine engine, DropDownMenu menu,SummaryText text, GamePanel main)
	{
		this.engine = engine;
		this.text = text;
		this.menu = menu;
		this.main = main;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		new Thread()
		{
			@Override
			public void run()
			{
				for(Player player: engine.getAllPlayers())
				{
					if(player.getBet() > 0)
					{
						main.clearPlayerPanel(player);
						engine.dealPlayer(player, 1000);
					}
				}
				main.clearDealerPanel();
				engine.dealHouse(1000);		
			}
		}.start();
		
		text.updateSummary(engine);
		

	}
	
}
