package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;

/**
 * This class will be the main panel that will hold both the player's cards and the dealer's cards. The dealer panel
 * is persistent and will remain at the top portion of the panel whilst the player panel will depend on which player
 * is selected in the JComboBox component.
 * 
 * @author David Doan
 *
 */
public class GamePanel extends JPanel 
{
	GameEngine engine;
	DropDownMenu menu;

	JLabel card;
	JPanel dealerPanel, playerPanel;
	ArrayList<PlayerCardPanel> listOfPanels;
	
	public GamePanel(GameEngine engine) 
	{
		super(new BorderLayout());
		this.engine = engine;
		listOfPanels = new ArrayList<PlayerCardPanel>();
		
		// Top half of the panel, represents the dealer
		JPanel dealerPanelContainer = new JPanel(new BorderLayout());
		JLabel dealer = new JLabel("Dealer cards:", SwingConstants.CENTER);
		dealerPanelContainer.add(dealer, BorderLayout.PAGE_START);
		//Creating another panel for the dealer so that the cards can be cleared every round 
		dealerPanel = new JPanel(new GridLayout(16,1));
		dealerPanelContainer.add(dealerPanel, BorderLayout.CENTER);
	
		// Bottom half of the panel, represents the player
		JPanel playerPanelContainer = new JPanel(new BorderLayout());
		JLabel player = new JLabel("Player cards:", SwingConstants.CENTER);
		playerPanelContainer.add(player, BorderLayout.PAGE_START);
		// Another panel for the player so that the player cards can be cleared every round
		playerPanel = new JPanel();
		playerPanelContainer.add(playerPanel, BorderLayout.CENTER);
		
		// Divides the whole panel into two 
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, dealerPanelContainer, playerPanelContainer);
		splitPane.setDividerLocation(230);
		splitPane.setEnabled(false);
		add(splitPane);
	}
	
	/**
	 * Getter which will be used to match the correct player to the corresponding PlayerCardPanel
	 * @return The ArrayList of PlayerCardPanels
	 */
	public ArrayList<PlayerCardPanel> getlistOfPanels()
	{
		return this.listOfPanels;
	}
	
	/**
	 * Clears the PlayerCardPanel that responds to the player passed in as a parameter
	 * @param player
	 */
	public void clearPlayerPanel(Player player)
	{
		for(PlayerCardPanel panel: listOfPanels) 
		{
			if(panel.getPlayer().toString().equals(player.toString()))
			{
				panel.removeAll();
			}
		}
	}
	
	// Clears the dealer panel
	public void clearDealerPanel()
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				dealerPanel.removeAll();
				dealerPanel.revalidate();
				dealerPanel.repaint();
			}
		});
	}
		
	// Adds the array list of panels individually to the bottom half, setting visibility to false so they are hidden
	public void addPlayerPanels(PlayerCardPanel panel)
	{
		listOfPanels.add(panel);
		for(PlayerCardPanel panels: listOfPanels)
		{
			playerPanel.add(panels);
		}
	}
	
	/**
	 * Sets the visibility of the PlayerCardPanel corresponding to the Player object passed in as a parameter
	 * @param player
	 */
	public void displayCorrectPanel(Player player)
	{
		for(PlayerCardPanel panel: listOfPanels) 
		{
			panel.setVisible(false);
			if(panel.getPlayer().toString().equals(player.toString()))
			{
				panel.setVisible(true);
			}
		}
	}
	
	/**
	 * Updates the UI by adding a JLabel to the PlayerCardPanel
	 * @param panel to be updated
	 * @param card to be inserted into the panel
	 */
	public void dealCardPlayer(PlayerCardPanel panel, PlayingCard card)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				String cardString = String.format("%s | %s", card.getValue(), card.getSuit());
				JLabel cardLabel = new JLabel(cardString, SwingConstants.CENTER);
				panel.add(cardLabel);
				panel.revalidate();
				panel.repaint();
			}
		});
	}
	
	/**
	 * Updates the UI by adding a JLabel to the PlayerCardPanel
	 * @param panel to be updated
	 * @param card which caused the player to bust
	 */
	public void playerBustCard(PlayerCardPanel panel, PlayingCard card)	
	{
		String bustCardString = String.format("%s | %s | Player Busts", card.getValue(), card.getSuit());
		JLabel bustLabel = new JLabel(bustCardString);
		panel.add(bustLabel);
		panel.revalidate();
		panel.repaint();
	}
	
	/**
	 * Updates the UI with the final result of the player
	 * @param panel to be updated
	 * @param result of the player
	 */
	public void playerResult(PlayerCardPanel panel, int result)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				String resultString = String.format("Player result: %d", result);
				JLabel resultLabel = new JLabel(resultString, SwingConstants.CENTER);
				panel.add(resultLabel);
				String betString = String.format("Player bet: %d", panel.getPlayer().getBet());
				JLabel betLabel = new JLabel(betString, SwingConstants.CENTER);
				panel.add(betLabel);
				panel.revalidate();
				panel.repaint();
			}
		});
	}
	
	/**
	 * Updates the player panel with the result of this round
	 * @param panel to be updated
	 * @param result of the dealer
	 */
	public void playerWin(PlayerCardPanel panel, int result)
	{

		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{		
				JLabel label;
				if(panel.getPlayer().getResult() > result)
				{
					String winString = String.format("%s won!", panel.getPlayer().getPlayerName());
					label = new JLabel(winString, SwingConstants.CENTER);
				}
				else
				{
					String winString = String.format("%s lost", panel.getPlayer().getPlayerName());
					label = new JLabel(winString, SwingConstants.CENTER);
				}
				panel.add(label);
				panel.revalidate();
				panel.repaint();
			}
		});
	}
	
	/**
	 * Updates the dealer panel when a card is dealt to the dealer
	 * @param pcard dealt to the dealer
	 */
	public void updateDealerPanel(PlayingCard pcard)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				String cardString = String.format("%s | %s", pcard.getValue(), pcard.getSuit());
				JLabel cardLabel = new JLabel(cardString, SwingConstants.CENTER);
				dealerPanel.add(cardLabel);
				dealerPanel.revalidate();
				dealerPanel.repaint();
			}
		});
	}
	
	/**
	 * Updates the dealer panel with the card that caused them to bust
	 * @param card
	 */
	public void dealerBustPanel(PlayingCard card)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				String cardString = String.format("%s | %s | Dealer Busts", card.getValue(), card.getSuit());
				JLabel cardLabel = new JLabel(cardString, SwingConstants.CENTER);
				dealerPanel.add(cardLabel);
				dealerPanel.revalidate();
				dealerPanel.repaint();
			}
		});
	}
	
	/**
	 * Updates the dealer panel with the final result of the dealer
	 * @param result
	 */
	public void dealerResult(int result)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				String resultString = String.format("Dealer result: %d", result);
				JLabel resultLabel = new JLabel(resultString, SwingConstants.CENTER);
				dealerPanel.add(resultLabel);
				dealerPanel.revalidate();
				dealerPanel.repaint();
			}
		});
	}
}


