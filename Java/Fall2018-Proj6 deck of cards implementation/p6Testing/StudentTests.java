package p6Testing;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

import p6Coding.Card;
import p6Coding.Deck;
import p6Coding.HandEvaluatorSFCP;
import p6Coding.HandEvaluatorPatrickjack;

public class StudentTests {
	@Test
	public void playerWinsTest() {
		ArrayList<Card> player = new ArrayList<Card>();
		player.add(new Card(5,1));
		player.add(new Card(6,1));
		ArrayList<Card> house = new ArrayList<Card>();
		house.add(new Card(1,1));
		house.add(new Card(7,1));
		assertFalse(HandEvaluatorPatrickjack.houseWins(player, house));
	}
	
	@Test
	public void tieTest() {
		ArrayList<Card> player = new ArrayList<Card>();
		player.add(new Card(5,1));
		player.add(new Card(6,1));
		ArrayList<Card> house = new ArrayList<Card>();
		house.add(new Card(4,1));
		house.add(new Card(7,1));
		assertFalse(HandEvaluatorPatrickjack.houseWins(player, house));
	}
	
	@Test
	public void houseWinsTest() {
		ArrayList<Card> player = new ArrayList<Card>();
		player.add(new Card(5,1));
		player.add(new Card(6,1));
		ArrayList<Card> house = new ArrayList<Card>();
		house.add(new Card(4,1));
		house.add(new Card(9,1));
		assertTrue(HandEvaluatorPatrickjack.houseWins(player, house));
	}
	
	@Test
	public void testExampleTest_SinglePairTest() {
		Card[] testHand = new Card[5];
		testHand[0] = new Card(1,1);
		testHand[1] = new Card(2,1);
		testHand[2] = new Card(3,1);
		testHand[3] = new Card(4,1);
		testHand[4] = new Card(5,1);
		assertFalse(HandEvaluatorSFCP.hasPair(testHand));
	}


	@Test
	public void testExampleTest_SingleRainbowTest() {
		Card testHand[] = new Card[5];

		testHand[0] = new Card(4,0);
		testHand[1] = new Card(5,1);
		testHand[2] = new Card(5,2);
		testHand[3] = new Card(5,3);
		testHand[4] = new Card(9,4);
		assertTrue(HandEvaluatorSFCP.hasRainbow(testHand));
	}


	@Test
	public void testExampleTest_SinglePatrickjackEvalTest() {
		ArrayList<Card> player = new ArrayList<Card>();
		player.add(new Card(3,1));
		player.add(new Card(2,1));

		assertEquals(5, HandEvaluatorPatrickjack.eval(player));
	}


	@Test
	public void testExampleTest_DeckDealCardsLengthTest() {
		Deck theDeck = new Deck();
		Card[] cards = theDeck.deal(41);
		assertEquals(41, cards.length);
	}
	@Test
	public void pairTest() {
		Deck theDeck = new Deck();
		Card[] cards = theDeck.deal(5);
		cards[0] = new Card(6,1);
		cards[1] = new Card(6,1);
		cards[2] = new Card(7,1);
		cards[3] = new Card(8,1);
		cards[4] = new Card(9,1);
		assertTrue(HandEvaluatorSFCP.hasPair(cards));
	}
	@Test
	public void threeOfAKindTest() {
		Deck theDeck = new Deck();
		Card[] cards = theDeck.deal(5);
		cards[0] = new Card(6,1);
		cards[1] = new Card(6,1);
		cards[2] = new Card(6,1);
		cards[3] = new Card(8,1);
		cards[4] = new Card(9,1);
		assertTrue(HandEvaluatorSFCP.hasThreeOfAKind(cards));
	}
	@Test
	public void fourOfAKindTest() {
		Deck theDeck = new Deck();
		Card[] cards = theDeck.deal(5);
		cards[0] = new Card(6,1);
		cards[1] = new Card(6,1);
		cards[2] = new Card(6,1);
		cards[3] = new Card(6,1);
		cards[4] = new Card(9,1);
		assertTrue(HandEvaluatorSFCP.hasFourOfAKind(cards));
	}
	@Test
	public void fiveOfAKindTest() {
		Deck theDeck = new Deck();
		Card[] cards = theDeck.deal(5);
		cards[0] = new Card(6,1);
		cards[1] = new Card(6,1);
		cards[2] = new Card(6,1);
		cards[3] = new Card(6,1);
		cards[4] = new Card(6,1);
		assertTrue(HandEvaluatorSFCP.hasFiveOfAKind(cards));
	}
	@Test
	public void flushTest() {
		Deck theDeck = new Deck();
		Card[] cards = theDeck.deal(5);
		cards[0] = new Card(6,1);
		cards[1] = new Card(6,1);
		cards[2] = new Card(7,1);
		cards[3] = new Card(8,1);
		cards[4] = new Card(9,1);
		assertTrue(HandEvaluatorSFCP.hasFlush(cards));
	}
	
	
	@Test
	public void straightTest() {
		Deck theDeck = new Deck();
		Card[] cards = theDeck.deal(5);
		cards[0] = new Card(6,1);
		cards[1] = new Card(5,1);
		cards[2] = new Card(7,1);
		cards[3] = new Card(8,1);
		cards[4] = new Card(9,1);
		assertTrue(HandEvaluatorSFCP.hasStraight(cards));
	}
	@Test
	public void straightFlushTest() {
		Deck theDeck = new Deck();
		Card[] cards = theDeck.deal(5);
		cards[0] = new Card(6,1);
		cards[1] = new Card(5,1);
		cards[2] = new Card(7,1);
		cards[3] = new Card(8,1);
		cards[4] = new Card(9,1);
		assertTrue(HandEvaluatorSFCP.hasStraightFlush(cards));
	}
	@Test
	public void straightRainbowTest() {
		Deck theDeck = new Deck();
		Card[] cards = theDeck.deal(5);
		cards[0] = new Card(6,0);
		cards[1] = new Card(5,1);
		cards[2] = new Card(7,2);
		cards[3] = new Card(8,3);
		cards[4] = new Card(9,4);
		assertTrue(HandEvaluatorSFCP.hasStraightRainbow(cards));
	}
	@Test
	public void twoPairTest() {
		Deck theDeck = new Deck();
		Card[] cards = theDeck.deal(5);
		cards[0] = new Card(6,1);
		cards[1] = new Card(6,1);
		cards[2] = new Card(8,1);
		cards[3] = new Card(8,1);
		cards[4] = new Card(1,1);
		assertTrue(HandEvaluatorSFCP.hasTwoPair(cards));
	}


	//You will add many other tests here.  You can also modify the ones above.




}
