// 게임을 할 본인을 의미합니다. 편의상 게임 중 플레이어1으로 명명하였습니다.
package blackjack_Pack;
import java.util.Scanner;

public class GamePlayer { 
	
	String[] hands = new String[30];
	int hand_count=0;
	boolean game_continue = true;
	
	public GamePlayer(){}
	
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
		
		int[] num_counter = new int[13]; // 카드는 A, 2... 10, J, Q, K으로, 총 13가지 카드가 있습니다. 이 배열에는 각 카드 종류별 갯수가 저장됩니다.
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
		int selection_available = num_counter[0]; // 선택 가능 횟수입니다. A카드의 갯수만큼 선택 가능합니다. num_counter[0]에는 소지한 A카드의 갯수가 저장되어 있으므로, 이를 저장합니다.
		int wildCard = 0; // A카드를 10점으로 환산한 횟수입니다.
		
		while(selection_available>0) { // A카드의 갯수만큼 환산 여부를 묻습니다. 다만 소지한 A카드의 갯수가 0개라면 이를 실행하지 않습니다.
			
			Scanner sc = new Scanner(System.in);
			System.out.println("A 카드를 몇 점으로 계산하시겠습니까? 1. [1점] | 2. [10점]");
			int answer = sc.nextInt();
			
			if(answer == 2) {
				wildCard++;
				num_counter[0]--;
				selection_available--;
			}
			else {
				selection_available--;
			}
			
			System.out.println("남은 A 카드 선택 횟수 : " + selection_available);			
		}
		
		result += wildCard*10;
		// 여기까지가 A카드에 대한 특수한 계산입니다. 
		
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
			// 스스로도 다소의 혼동이 있어 내용을 정리해 두었습니다.
			// 만약 A카드를 10으로 변환했다면 위에서 num_counter[0]--;를 통해 자신이 10으로 변환한 횟수만큼 이미 num_counter[0]은 줄어들었고, 10으로 변환하지 않았다면 그것은 1로 사용하겠다는 의미입니다.
			// 따라서 계산이 정상적으로 진행됩니다.
		}
		return result;
	} // Calc_myScore : 블랙잭 룰에 따른 점수 계산 방법입니다.
	
	void good_game() {
		game_continue = false; // 이거 this 쓰는 게 맞나?
	} // good_game : 카드를 더 이상 받지 않기로 결정했습니다. 다른 플레이어의 카드 구성이 끝날 때까지 대기합니다.
	
}
