package p6Coding;

import java.util.ArrayList;

public class Deck {

	//You need to use this ArrayList<Card> structure to hold the deck
	//  
	//Your cannot use regular arrays in this class other than in the
	//  deal method, which needs to return an array
	private ArrayList<Card> cards;
	
	

	public Deck() {
		cards = new ArrayList<Card>(0);
		for(int i = 0; i < 5; i++) {
			for(int j = 1; j < 10; j++) {
				cards.add(new Card(j,i));
			}
		}
		
	}

	public Deck(Deck other) {
		cards = new ArrayList<Card>(0);
		for(int i = 0; i < other.cards.size();i++) {
			cards.add(new Card(other.getCardAt(i).getValue(),other.getCardAt(i).getSuit()));
		}
	}

	public Card getCardAt(int position) {
		return new Card(cards.get(position).getValue(),cards.get(position).getSuit());
	}

	public int getNumCards() {
		return cards.size();
	}


	public Card[] deal(int numCards) {

		Card[] dealt = new Card[numCards];
		if(cards.size() >0) {
		for(int i = 0; i < numCards; i ++) {
			dealt[i] = cards.get(i);
		}
		
			for(int i = 0; i<numCards; i ++) {
				cards.remove(0);
			}
		}
		return dealt;
	}
		


	public void cut(int position) throws StarDeckException {
		if(position > 42 || position < 3) {
			throw new StarDeckException ("Too few cards in one part of the cut.");
		}
		else {
			ArrayList<Card> first = new ArrayList<Card>(0);
			for(int i = 0; i <position; i ++) {
				first.add(cards.get(i));
			}
			ArrayList<Card> second = new ArrayList<Card>(0);
			for(int i = position; i < cards.size();i++) {
				second.add(cards.get(i));
			}
			for(int i = 0; i <second.size();i++) {
				cards.set(i, second.get(i));
			}
			for(int i = second.size();i<cards.size();i++) {
				cards.set(i, first.get(i - second.size()));
			}
		}	
	}
	
	
	
	
	public void shuffle() {
		throw new RuntimeException("Challenge problem...");
	}
	

}
