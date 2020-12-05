package p6Coding;

public class HandEvaluatorSFCP {
	//ALL OF THESE ARE PASSED AN ARRAY OF LENGTH 5

	
	public static int numberOfPairs(Card[] cards, int value) {
		int count = 0;
		for(int i = 0; i < cards.length; i++) {
			if(cards[i].getValue() == value) {
				count++;
			}
		}
		return count;
		
	}
	//Cluster 1: Think about how a helper might be useful for these...
	public static boolean hasPair(Card[] cards) {
		for(int i = 0; i < 10;i++) {
			if(numberOfPairs(cards, i) >= 2) {
				return true;
			}			
		}
		return false;
	}

	public static boolean hasThreeOfAKind(Card[] cards) {
		for(int i = 0; i < 10;i++) {
			if(numberOfPairs(cards, i) >= 3) {
				return true;
			}			
		}
		return false;
	}

	public static boolean hasFourOfAKind(Card[] cards) {
		for(int i = 0; i < 10;i++) {
			if(numberOfPairs(cards, i) >= 4) {
				return true;
			}			
		}
		return false;
	}

	public static boolean hasFiveOfAKind(Card[] cards) {
		for(int i = 0; i < 10;i++) {
			if(numberOfPairs(cards, i) >= 5) {
				return true;
			}			
		}
		return false;
	}



	//Cluster 2
	public static boolean hasRainbow(Card[] cards) {
		for(int i = 0; i < cards.length; i++) {
			for(int j = 0; j < cards.length; j++) {
				if(cards[i].getSuit() == cards[j].getSuit() && i != j) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean hasStraight(Card [] cards) {
		int lowest = 10;
		for(int i = 0; i < cards.length;i++) {
			if(cards[i].getValue() < lowest) {
				lowest = cards[i].getValue();
			}
		}
		if(lowest == 1) {
			boolean check = false;
			for(int i = 0; i < cards.length; i ++) {
				if(cards[i].getValue() == 2) {
					check = true;
				}
			}
			if(check == true) {
				lowest++;
			}
			else {
				lowest = 6;
			}
		}
		else {
		lowest++;
		}
		for(int i = 1; i < 5; i++) {
			boolean hasPassed = false;
			for(int j = 0; j < cards.length;j++) {
				if(cards[j].getValue() == lowest) {
					hasPassed = true;
				}
			}
			if(hasPassed == true) {
				if(lowest == 9 && i == 4) {
					lowest = 1;
				}
				else {
					lowest++;
				}
			}
			else {
				return false;
			}
		}
		return true;
	}

	public static boolean hasFlush(Card[] cards) {
		for(int i = 0; i < cards.length-1;i ++) {
			if(cards[i].getSuit() != cards[i+1].getSuit()) {
				return false;
			}
		}
		return true;
	}




	//Cluster 3: Think about how to make use of existing methods to
	//           make the following ones easier to write...
	public static boolean hasStraightRainbow(Card[] cards) {
		if(hasRainbow(cards) == true && hasStraight(cards) == true) {
			return true;
		}
		return false;
	}

	public static boolean hasStraightFlush(Card[] cards) {
		if(hasFlush(cards) == true && hasStraight(cards) == true) {
			return true;
		}
		return false;
	}

	public static boolean hasTwoPair(Card[] cards) {
		int pairCount =0;
		for(int i = 1; i < 10; i ++) {
			if(numberOfPairs(cards,i) >=2) {
				pairCount ++;
			}
		}
		if(pairCount >= 2) {
			return true;
		}
		return false;
	}




	//Challenge
	public static boolean hasFullHouse(Card[] cards) {
		throw new RuntimeException("You need to implement this...");
	}




}

