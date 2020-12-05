package p6Coding;

import java.util.ArrayList;

public class HandEvaluatorPatrickjack {
	//Each of these is passed a reference to an ArrayList<Card> object 
	//  with "UNKNOWN" length (so you'll need to "ask" it).

	public static int eval(ArrayList<Card> hand) {
		int total = 0;
		if(hand.size() == 2) {
			if(hand.get(0).getValue() == 2 && hand.get(1).getValue() == 4) {
				return 22;
			}
			else if(hand.get(0).getValue() == 4 && hand.get(1).getValue() == 2) {
				return 22;
			}
			else if(hand.get(0).getValue() == 6 && hand.get(1).getValue() == 9) {
				return 21;
			}
			else if(hand.get(0).getValue() == 9 && hand.get(1).getValue() == 6) {
				return 21;
			}
		}
		for(int i = 0; i < hand.size(); i ++) {
			total+= hand.get(i).getValue();
		}
		if(total < 11) {
			for(int i = 0; i < hand.size(); i ++) {
				if(hand.get(i).getValue() == 5) {
					total += 10;
					break;
				}
			}
		}
		if(total > 21) {
			return 0;
		}
		else {
			return total;
		}
	}


	public static boolean houseWins(ArrayList<Card> player, ArrayList<Card> dealer) {
		if(eval(player) >= eval(dealer)) {
			return false;
		}
		else {
			return true;
		}
	}

}
