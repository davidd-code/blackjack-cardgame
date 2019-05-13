package model;

import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

public class GameEngineImpl implements GameEngine
{
	private Map<String, Player> players = new HashMap<String, Player>();
	private Deque<PlayingCard> deck = new LinkedList<PlayingCard>(); 
	private Set<GameEngineCallback> callbacks = new HashSet<GameEngineCallback>();
	
	/**
	 * Deal a cards to the player passed in as the first argument, after a card
	 * has been dealt, call GameEngineCallback.nextCard(...). Continue looping until
	 * player busts and then call GameEngineCallback.bustCard(...). Once the player 
	 * busts then call GameEngineCallback.result(...).
	 * 
	 * If the player passed into the first argument is the house, then call the 
	 * house methods from GameEngineCallback class instead.
	 */
	@Override
	public void dealPlayer(Player player, int delay) 
	{
		// Reset the result of the player 
		player.setResult(0);
		PlayingCard dealtCard = null;
		
		// Get a new shuffled deck
		this.getShuffledDeck();
		
		// Loop that continues until the player busts
		while(player.getResult() <= BUST_LEVEL)
		{
			// Take the top card from the deck of playing cards and add the value of the card
			// to the players result
			dealtCard = deck.poll();
			player.setResult(player.getResult() + dealtCard.getScore());

			// Iterate through the collection of GameEngineCallbacks
			for(GameEngineCallback call: callbacks)
			{
				// If player result is more than 21, the player busts
				if(player.getResult() > BUST_LEVEL)
				{
					// Call houseBustCard(...) from GameEngineCallback if the player receiving a card was the House
					if(player.getPlayerId().equals("House"))
					{
						call.houseBustCard(dealtCard, this);
					}
					// Call bustCard(...) for the actual players
					else
					{
						call.bustCard(player, dealtCard, this);
					}
				}
				// If player result is less than 21
				else
				{
					// Call nextHouseCard(...) if the player being dealt a card is the House
					if(player.getPlayerId().equals("House"))
					{
						call.nextHouseCard(dealtCard, this);
					}
					// Call nextCard(...) for the actual players
					else
					{
						call.nextCard(player, dealtCard, this);
					}
				}
			}
			
			// Set a delay in milliseconds
			try 
			{
				Thread.sleep(delay);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
		
		// Since last card dealt would be the bust card, subtract the value of that card from player's result
		player.setResult(player.getResult() - dealtCard.getScore());
		
		
		// Iterate through GameEngineCallbacks and call result(...) 
		for(GameEngineCallback call: callbacks)
		{
			call.result(player, player.getResult(), this);
		}
	}
	
	/**
	 * Method that will deal cards to the house. This method works by instantiating a new Player object
	 * to represent the House and then passing this Player object into the dealPlayer() method above. After
	 * the dealPlayer() method has finished, this method will set the points of each player depending on 
	 * whether or not they beat the House. Finally after each players points have been set then this method
	 * will call houseResult(...) from GameEngineCallbackImpl
	 */
	@Override
	public void dealHouse(int delay) 
	{
		// Create new player object to represent the House
		Player house = new SimplePlayer("House", "House", 0);
		
		// Call dealPlayer while passing in the object created above
		this.dealPlayer(house, delay);
		
		// Iterate through all players in the collection of players and set their points 
		for(Player player: players.values())
		{
			// If the house result is higher than the player's result
			if(house.getResult() > player.getResult())
			{
				// Subtract the points equal to the bet placed for that hand
				player.setPoints(player.getPoints() - player.getBet());
			}
			// If the house result is lower than the player's result
			else if(house.getResult() < player.getResult())
			{
				// Add points equal to the bet placed for that hand
				player.setPoints(player.getPoints() + player.getBet());
			}
			// Reset the beat after each round
			player.resetBet();
		}
		
		// Iterate through all the GameEngineCallbacks and call houseResult(...)
		for(GameEngineCallback call: callbacks)
		{
			call.houseResult(house.getResult(), this);
		}
	}
	
   /**
    * Add the player passed in as the argument into the collection of players
    */
	@Override
	public void addPlayer(Player player) 
	{
		players.put(player.getPlayerId(), player);
	}
	
   /**
    * @param id
    *            id of player to retrieve (null if not found)
    * @return the Player or null if Player does no exist
    */
	@Override
	public Player getPlayer(String id) 
	{
		if(players.containsKey(id))
		{
			return players.get(id);
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * Check if the player passed in as the argument exists in the players collection
	 * if the player exists, remove that player from the collection and return true.
	 * If the player was not found in the collection, return false.
	 */
	@Override
	public boolean removePlayer(Player player) 
	{
		if(players.containsValue(player))
		{
			players.remove(player.getPlayerId());
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Add the GameEnginecallback passed as the argument into the collection of 
	 * GameEngineCallbacks
	 */
	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) 
	{
		callbacks.add(gameEngineCallback);
	}
	
	/**
	 * Check if the collection of GameEngineCallbacks contains the object passed in as
	 * the argument. Remove from the collection and return true if found in the collection,
	 * otherwise return false.
	 */
	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) 
	{
		// TODO Auto-generated method stub
		if(callbacks.contains(gameEngineCallback))
		{
			callbacks.remove(gameEngineCallback);
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Create a copy of the collection of players and return this copy so that the collection is 
	 * unmodifiable.
	 */
	@Override
	public Collection<Player> getAllPlayers() 
	{
		Collection<Player> immutableList = Collections.unmodifiableCollection(players.values());
		return immutableList;
	}
	
	/**
	 * Call the placeBet(...) method from the SimplePlayer class
	 */
	@Override
	public boolean placeBet(Player player, int bet) 
	{
		return player.placeBet(bet);
	}

	/**
	 * Create a deck of 52 cards that is randomly shuffled. Each card in this deck is unique.
	 */
	@Override
	public Deque<PlayingCard> getShuffledDeck() 
	{
		// Outer loop to iterate over each of the suits
		for(int i = 1; i <= 4; i++)
		{
			// Inner loop will iterate over each of the possible values of the cards
			// Ace, King, Queen, Jack, 10, 9, 8 7 ...
			for(int j = 1; j <= 13; j++)
			{
				// Add the card to the Deque
				deck.add(new PlayingCardImpl(i,j));
			}
		}
		// Shuffle the deck to randomize the order of each card and then return the Deque.
		Collections.shuffle((List<?>) deck);
		return deck;
	}
}
