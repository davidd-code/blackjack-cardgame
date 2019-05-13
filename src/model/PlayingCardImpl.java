package model;

import model.interfaces.PlayingCard;

public class PlayingCardImpl implements PlayingCard
{

	private Suit cardSuit;
	private Value cardValue;
	
	/**
	 * Constructor which takes two arguments and assigns the suit and
	 * the value of the card based on the integers passed into the constructor.
	 */
	public PlayingCardImpl(int suit, int value)
	{
		// Switch that uses the number passed into the constructor to assign a suit to the card
		switch(suit)
		{
			case 1:
				this.cardSuit = Suit.HEARTS;
				break;
			case 2:
				this.cardSuit = Suit.SPADES;
				break;
			case 3:
				this.cardSuit = Suit.CLUBS;
				break;
			case 4:
				this.cardSuit = Suit.DIAMONDS;
				break;
		}
		// Switch that assigns a value to the card based on integer passed in the constructor
		switch(value)
		{
			case 1:
				this.cardValue = Value.ACE;
				break;
			case 2:
				this.cardValue = Value.TWO;
				break;
			case 3:
				this.cardValue = Value.THREE;
				break;
			case 4:
				this.cardValue = Value.FOUR;
				break;
			case 5:
				this.cardValue = Value.FIVE;
				break;
			case 6:
				this.cardValue = Value.SIX;
				break;
			case 7:
				this.cardValue = Value.SEVEN;
				break;
			case 8:
				this.cardValue = Value.EIGHT;
				break;
			case 9:
				this.cardValue = Value.NINE;
				break;
			case 10:
				this.cardValue = Value.TEN;
				break;
			case 11:
				this.cardValue = Value.JACK;
				break;
			case 12:
				this.cardValue = Value.QUEEN;
				break;
			case 13:
				this.cardValue = Value.KING;
				break;
		}
	}
	
	@Override
	public Suit getSuit() 
	{
		return cardSuit;
	}

	@Override
	public Value getValue() 
	{
		return cardValue;
	}
	
	/**
	 * Ace returns 1, Face cards return 10 and all the other cards are return their face value
	 */
	@Override
	public int getScore() 
	{
		switch(this.cardValue)
		{
			case ACE:
				return 1;
			case KING: case QUEEN: 
			case JACK: case TEN:
				return 10;
			case TWO: 
				return 2;
			case THREE: 
				return 3;
			case FOUR: 
				return 4;
			case FIVE:
				return 5;
			case SIX: 
				return 6;
			case SEVEN: 
				return 7;
			case EIGHT: 
				return 8;
			case NINE:
				return 9;
			default:
				return 0;
		}
	}
	
	/**
	 * Method that returns true if the card passed into the argument has the same value and suit
	 */
	@Override
	public boolean equals(PlayingCard card) 
	{
		if(this.cardSuit == card.getSuit() && this.cardValue == card.getValue())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public String toString()
	{
		return "Suit: " + this.cardSuit +", Value: " + this.cardValue +", Score: " + this.getScore();
	}

}
