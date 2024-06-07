// 게임의 딜러입니다. 메소드가 플레이어와 거의 같으나, 딜러의 경우 계산식에 특별한 과정이 추가됩니다. 

package blackjack_Pack;
import java.util.Scanner;

public class GameDealer { 
	
	String[] hands = new String[30];
	int hand_count=0;
	
	boolean game_continue = true;
	
	String[] recognized_cards = new String[5];
	int recognized_cards_count = 0;
	
	public GameDealer(){}
	
	void Show_hands() {
		if(hand_count<=0)
		{
			System.out.println("수중에 카드가 없습니다.");
		}
		else {
			for(int i=0; i<hand_count; i++) {
			System.out.print(hands[i] + " | ");
			}
		}
		System.out.println("");
	} // Show_hands : 자신의 패를 sysout으로 출력합니다.
	
	void Get_hands(String card) {
		hands[hand_count]=card;
		hand_count++;
	} // Get_hands : 자신의 패에 카드를 추가합니다.
	
	String Pick_Hands(int i) {
		return hands[i];
	} // Pick_Hands : 자신의 패 중 지정한 순서의 카드를 출력합니다. 이는 블랙잭 경기 규칙 중 카드 공개가 포함되어 있기 때문에 그 부분을 구현하기 위한 것입니다.
		
	int Calc_Dealer_Score1() {
		int[] num_counter = new int[13];
		int result=0;
		for(int i=0; i<hand_count; i++) {
			// 자신의 패를 의미하는 hands 배열에는 자신이 가지고 있는 카드의 정보가 담겨 있습니다.
			// 향상된 for문을 사용하여 카드 정보를 String s에 대입하고, 그 s에 각 카드 정보와 일치하는 부분이 있는지 확인합니다.
			// 이러한 과정으로 자신의 패를 모두 확인하여, 각 카드를 몇 장씩 가지고 있는지 계산합니다.
			if(hands[i].contains("A")) {
				num_counter[0]++;
			}
			if(hands[i].contains("2")) {
				num_counter[1]++;
			}
			if(hands[i].contains("3")) {
				num_counter[2]++;
			}
			if(hands[i].contains("4")) {
				num_counter[3]++;
			}
			if(hands[i].contains("5")) {
				num_counter[4]++;
			}
			if(hands[i].contains("6")) {
				num_counter[5]++;
			}
			if(hands[i].contains("7")) {
				num_counter[6]++;
			}
			if(hands[i].contains("8")) {
				num_counter[7]++;
			}
			if(hands[i].contains("9")) {
				num_counter[8]++;
			}
			if(hands[i].contains("10")) {
				num_counter[9]++;
			}
			if(hands[i].contains("J")) {
				num_counter[10]++;
			}
			if(hands[i].contains("Q")) {
				num_counter[11]++;
			}
			if(hands[i].contains("K")) {
				num_counter[12]++;
			}
		} // 각 카드의 소지한 갯수 확인을 위한, for문 종료
		
		// 딜러는 플레이어와 달리 A카드의 점수 계산 방법을 선택할 수 없습니다.
		
		for(int i=0; i<13; i++) { // 보통의 점수 계산입니다.
			switch(i+1) {
			case 11:
			case 12:
			case 13:
				result += num_counter[i]*10;
				break;
			default:
				result += num_counter[i]*(i+1);
				break;
			} // 블랙잭의 규칙에 따라 J, Q, K는 10점으로 계산합니다.
		}

		return result;
	} // Calc_Dealer_Score1 : 딜러는 플레이어와 달리 A카드의 점수 계산 방법을 선택할 수 없습니다.
	
	int Calc_Dealer_Score2() {
		int[] num_counter = new int[13];
		int result=0;
		for(int i=0; i<hand_count; i++) {
			// 자신의 패를 의미하는 hands 배열에는 자신이 가지고 있는 카드의 정보가 담겨 있습니다.
			// 향상된 for문을 사용하여 카드 정보를 String s에 대입하고, 그 s에 각 카드 정보와 일치하는 부분이 있는지 확인합니다.
			// 이러한 과정으로 자신의 패를 모두 확인하여, 각 카드를 몇 장씩 가지고 있는지 계산합니다.
			if(hands[i].contains("A")) {
				num_counter[0]++;
			}
			if(hands[i].contains("2")) {
				num_counter[1]++;
			}
			if(hands[i].contains("3")) {
				num_counter[2]++;
			}
			if(hands[i].contains("4")) {
				num_counter[3]++;
			}
			if(hands[i].contains("5")) {
				num_counter[4]++;
			}
			if(hands[i].contains("6")) {
				num_counter[5]++;
			}
			if(hands[i].contains("7")) {
				num_counter[6]++;
			}
			if(hands[i].contains("8")) {
				num_counter[7]++;
			}
			if(hands[i].contains("9")) {
				num_counter[8]++;
			}
			if(hands[i].contains("10")) {
				num_counter[9]++;
			}
			if(hands[i].contains("J")) {
				num_counter[10]++;
			}
			if(hands[i].contains("Q")) {
				num_counter[11]++;
			}
			if(hands[i].contains("K")) {
				num_counter[12]++;
			}
		} // 각 카드의 소지한 갯수 확인을 위한, for문 종료
		
		
		// 이 아래는 블랙잭의 특수한 점수 환산 방법으로 인한 것입니다.
		// 점수 계산 시, 플레이어는 A카드를 1점으로 계산할 것인지, 10점으로 계산할 것인지 선택할 수 있습니다.
		for(int i=0; i<13; i++) { // 보통의 점수 계산이지만, 와일드카드 계산 조건을 구성해야 하기 때문에 1(카드 A)은 계산하지 않고 넘어갑니다.
			// 선택 가능 횟수입니다. A카드의 갯수만큼 선택 가능합니다. num_counter[0]에는 소지한 A카드의 갯수가 저장되어 있으므로, 이를 저장합니다.
			switch(i+1) {
			case 1:
				break; // 1(카드 A)은 계산하지 않고 넘어갑니다. 이는 뒤에서 따로 계산하기 위합니다.
			case 11:
			case 12:
			case 13:
				result += num_counter[i]*10;
				break;
			default:
				result += num_counter[i]*(i+1);
				break;
			} // 블랙잭의 규칙에 따라 J, Q, K는 10점으로 계산합니다.
		}
		
		int selection_available = num_counter[0]; // A카드 선택권의 숫자입니다.
		int[] arr = new int[selection_available]; // A카드 선택권 사용의 경우에 수를 계산할 배열입니다.
		int candidate = 0; // 최종적으로 선택하기에 가장 적절해 보이는, 숫자를 저장할 변수입니다.
		
		for(int i=0; i<selection_available; i++) { // 갯수가 제한된 선택권을 1과 10에 각각 사용했을 때의 결과를 계산하는 for문입니다.
			int j=selection_available-i;
			arr[i]= i*10 + j;
			
			if((17<=(arr[i]+result))&&((arr[i]+result)<21)&&(arr[i]>candidate)){ // 선택하기 좋은 숫자를 고르는 조건문입니다.
				candidate = arr[i];
			}
		}
		
		candidate = (candidate <= selection_available) ? selection_available : candidate;
		// 하지만 좋은 숫자가 없어서 결국 아무것도 고르지 못했다면 모두 1을 선택한 것으로 계산합니다.
		
		result += candidate;
		
		return result;
	} // Calc_Dealer_Score2 : 그러나 A 선택권 없이 계산한 점수가 21을 초과할 경우 A 선택권을 사용할 수 있습니다.
	
	int Final_Dealer_Score() {
		int result = Calc_Dealer_Score1(); // A선택권 없을 때의 점수입니다.
		int sub_score = Calc_Dealer_Score2(); // A선택권을 사용했을 때의 점수입니다.
		
		return (result>21) ? sub_score : result; // 21을 오버했을 때보다 차라리 숫자가 낮은 편이 딜러가 이기는 경우의 수가 더 많으므로, 더 낮은 수를 선택.
		// 만약 sub_score도 21을 넘었다면 어차피 둘 다 넘은 거라 승부에는 아무런 상관이 없음
	}
	
	void good_game() {
		game_continue = false;
	}  // good_game : 카드를 더 이상 받지 않기로 결정했습니다. 다른 플레이어의 카드 구성이 끝날 때까지 대기합니다.
	
	void recognize_yours(String[] strArr) {
		recognized_cards_count = strArr.length;
		int count=0; // recognized_cards 배열에 앞에서부터 차례대로 정보를 입력하기 위한 카운트입니다.
		
		for(int i=0; i<hand_count; i++) { // 입력된 배열 strArr은 공개된 모든 카드의 정보가 입력된 배열입니다.
			for(int j=0; j<recognized_cards_count; j++) { // 그 중에서 자신의 카드와 일치하는 것을 조사합니다
				if(hands[i].equals(strArr[j])){ // 자신이 가진 카드는 배열에 저장하지 않고, 자신의 것이 아닌 것만 저장합니다. 
					break;
				}
				else {
					recognized_cards[count] = strArr[j];
					count++;
				}	
			}
		}
	} // recognize_yours : 게임 진행 중 타인의 카드가 공개됩니다. 이 때, 공개된 카드의 정보들을 수집하고 기억합니다.
	// 플레이어가 선택해줄 수 없는, 컴퓨터의 입장이기 때문에 자체적인 판단이 필요합니다.
	
	boolean make_a_decision() {
		
		int now_myScore = Final_Dealer_Score();
		// 블랙잭의 규칙에 의해 딜러는 점수가 17보다 작으면 무조건 뽑아야 하지만 17 이상이면 무조건 멈춰야 합니다.
		
		return (now_myScore < 17)? true : false; 
	} // make_a_decision : 카드를 더 받을지에 대한 판단을 내립니다.
	// 플레이어가 선택해줄 수 없는, 컴퓨터의 입장이기 때문에 자체적인 판단이 필요합니다.
	
}
