// 본인이나 딜러가 아닌 또 다른 플레이어로서, 컴퓨터인 플레이어입니다.
// 대부분의 메서드가 게임플레이어와 동일하나, 다른 플레이어의 A카드 사용이나 카드 드로우 여부 등을
// 플레이어 본인이 대신 선택해줄 수는 없으므로, 컴퓨터가 알아서 처리할 수 있도록 조건문을 설정하였습니다.

package blackjack_Pack;
import java.util.Scanner;

public class AnotherPlayer {
	
	String[] hands = new String[30];
	int hand_count=0;
	
	boolean game_continue = true;
	
	String[] recognized_cards = new String[5];
	int recognized_cards_count = 0;
	
	public AnotherPlayer(){}
	
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
			
	int Calc_myScore() {
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
		for(int i=0; i<13; i++) { // 보통의 점수 계산이지만, 와일드카드 계산을 위해 1(카드 A)은 계산하지 않고 넘어갑니다.
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
	} // Calc_myScore : 그러나 A 선택권 없이 계산한 점수가 21을 초과할 경우 A 선택권을 사용할 수 있습니다.

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
	
	boolean make_a_decision(int used_card_number) {
		
		int now_myScore = Calc_myScore();
		int random_num = (int)(Math.random()*101);
		int possibility;
		
		if(now_myScore>=21) { // 다른 임의의 컴퓨터 플레이어는 카드 드로우 여부를 선택하는 것에 제약이 없습니다. 따라서 21 미만이라면 뭘 선택해도 좋습니다.
			return false;
		}
		else {
		
			int count = 0;
			String[] AllCards_I_know = new String[32]; // 일단 현재 알고 있는(= 사용된) 카드들 정리
			int[] num_sort = new int[13]; // int 배열이니 0으로 초기화해주지 않아도 무방.
			
			for(int i=0; i<hand_count; i++){
				AllCards_I_know[count] = hands[i];
				count++;
			}
			
			for(int i=0; i<recognized_cards_count; i++){
				AllCards_I_know[count] = recognized_cards[i];
				count++;
			}
			
			for(int i=0; i<count; i++) { // 사용된 카드들을 종류별로 갯수 정리 시작
				
				if(AllCards_I_know[i] == null) { // 솔직히 다 멀쩡한 값만 골라서 집어넣도록 구성했는데 왜 그런지 모르겠지만, null값 때문에 오류가 나는 경우가 많음.
					break; // 그래서 null이면 계산 안 하도록 처리.
				}
				else {
					if(AllCards_I_know[i].contains("A")) {
						num_sort[0]++;
					}
					if(AllCards_I_know[i].contains("2")) {
						num_sort[1]++;
					}
					if(AllCards_I_know[i].contains("3")) {
						num_sort[2]++;
					}
					if(AllCards_I_know[i].contains("4")) {
						num_sort[3]++;
					}
					if(AllCards_I_know[i].contains("5")) {
						num_sort[4]++;
					}
					if(AllCards_I_know[i].contains("6")) {
						num_sort[5]++;
					}
					if(AllCards_I_know[i].contains("7")) {
						num_sort[6]++;
					}
					if(AllCards_I_know[i].contains("8")) {
						num_sort[7]++;
					}
					if(AllCards_I_know[i].contains("9")) {
						num_sort[8]++;
					}
					if(AllCards_I_know[i].contains("10")) {
						num_sort[9]++;
					}
					if(AllCards_I_know[i].contains("J")) {
						num_sort[10]++;
					}
					if(AllCards_I_know[i].contains("Q")) {
						num_sort[11]++;
					}
					if(AllCards_I_know[i].contains("K")) {
						num_sort[12]++;
					}
				}
			} // 사용된 카드들을 종류별로 갯수 정리 시작
			
			boolean[] I_need = new boolean[13]; // 자신이 필요한 숫자가 무엇인지 판단하는 과정입니다.
			
			for(int i=0; i<13; i++) {
				
				int added_score = (i>10) ? 10 : i+1;
				
				if(21>(now_myScore+added_score)) {
					I_need[i]=true;
				}
				else {
					I_need[i]=false;
				}
			}
			
			int number_I_want = 0; // 자신이 원하는 숫자 중 남아 있는 숫자의 갯수를 저장할 변수입니다.
			
			for(int i=0; i<13; i++) {
				if(I_need[i]) { // 각 종류별로 동일한 카드는 4장이고, num_sort에 저장된 숫자는 이미 사용된 숫자의 갯수입니다.
					number_I_want += (4-num_sort[i]); // 그러니 자신이 원하는 종류의 카드 중 남아 있는 갯수는 4-num_sort입니다.
					// 예를 들어, 자신이 필요한 카드는 5입니다. 그런데 카드 5 중 이미 사용되었음을 인지한 갯수는 1개입니다. 이 때, 남은 5카드는 3장입니다.				
				}
			}
			
			possibility = (int)(number_I_want/(52-used_card_number)*100); // 총 52장의 카드 중 사용된 카드를 빼고, 남은 카드 중에서 자신이 원하는 카드가 나올 확률을 계산합니다. 
			
		} // else문 종료
		
		return (random_num<possibility)? true : false;	// 무작위로 정해진 난수가 자신이 카드를 뽑을 확률보다 낮으면 카드를 더 뽑는 것으로 판단하고, 높으면 멈춥니다.
		// 예를 들어, 원하는 카드가 나올 확률이 25일 때, 난수가 25와 같거나 보다 크면 뽑지 않고, 25보다 작으면 카드를 뽑습니다.
		// 반대로, 원하는 카드가 나올 확률이 85일 때, 난수가 85와 같거나 보다 크면 뽑지 않고, 85보다 작으면 카드를 뽑습니다.
		// 이러한 조건을 통해, 보다 확률이 높은 쪽의 결론이 도출되도록 하였습니다.
		
	} // make_a_decision : 카드를 더 받을지에 대한 판단을 내립니다.
	// 플레이어가 선택해줄 수 없는, 컴퓨터의 입장이기 때문에 자체적인 판단이 필요합니다.

}
