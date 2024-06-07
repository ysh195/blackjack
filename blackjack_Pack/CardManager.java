// 게임에 사용되는 카드 중 아직 사용되지 않은 것들을 관리할 카드 매니저입니다.

package blackjack_Pack;

public class CardManager {
	
	String[][] card = new String[4][13];
	int[] remained_card = new int[13];
	int used_number;
	
	public CardManager() {}
	
	void Create_Card(){
	
	for(int i=0; i<card.length; i++) {
		
		String shape="";
		String num="";
		
		switch(i) {
		case 0:
			shape = "하트";
			break;
		case 1:
			shape = "클로버";
			break;
		case 2:
			shape = "다이아";
			break;
		case 3:
			shape = "스페이드";
			break;
		default :
			break;
		}
		
		for(int j=0; j<card[i].length; j++) {

			switch(j+1) {
			case 1:
				num = "A";
				break;
			case 11:
				num = "J";
				break;
			case 12:
				num = "Q";
				break;
			case 13:
				num = "K";
				break;
			default:
				num = String.valueOf(j+1);
				break;
			}
			
			card[i][j] = "[" + shape + " " + num + "]";
		}
	}
	} // Create_Card : 카드를 새롭게 생성합니다.
	
	void Show_Remain_Card() {
		
		for(int i=0; i<card.length; i++) {

			for(int j=0; j<card[i].length; j++) {
				System.out.println(card[i][j]+" ");
					}

		}
	} // Show_Remain_Card : 남아 있는 카드 목록들을 sysout으로 보여줍니다.
	
	String draw_Card(){
		
		int x=0, y=0;
		
		do {
		 x = (int)(Math.random()*4); // 눈 앞에 보여줄 숫자가 아니라 배열 순서값이니까 0부터 출력
		 y = (int)(Math.random()*13);
		}while(card[x][y].equals("used"));
		
		String temp = card[x][y];
		
		card[x][y]="used";
		
		return temp;
	} // draw_Card : 무작위로 카드 한 장을 뽑습니다. 뽑은 카드가 이미 사용되었다면 사용된 적 없는 카드가 나올 때까지 계속 뽑습니다.
	 // 사용된 카드에는 "used"를 저장하고, 그 카드에 저장되어 있던 값을 출력합니다.
	
	int Count_Remain_Card(String str) {
		
		int count=0;
		
		for(int i=0; i<card.length; i++) {
			for(int j=0; j<card[i].length; j++) {
				if(card[i][j].contains(str)) {
					count++;
				}
			}

		}
		
		return count;
	} // Count_Remain_Card : 남아 있는 카드 중 찾고자 하는 값이 포함된 갯수를 찾습니다. 에이스 : "A", 킹 : "K"...와 같이 표기하니 찾고자 하는 문자 그대로 입력하면 됩니다.
	
	void Count_All_Remain_Card() {
		
		for(int i=0; i<13; i++) {
			remained_card[i] = 0;
		} // 이전에 이미 카운트해서 저장된 숫자가 있을 수 있으니, 한 번 0으로 만들어줍니다.
		
		for(int j=0; j<13; j++) { // j값에 따라 Count_Remain()에 들어갈 문자열이 달라지도록 합니다.
			
			String num="";
			
			switch(j+1) {
			case 1:
				num = "A";
				break;
			case 11:
				num = "J";
				break;
			case 12:
				num = "Q";
				break;
			case 13:
				num = "K";
				break;
			default:
				num = String.valueOf(j+1);
				break;
			}
			
			remained_card[j] = Count_Remain_Card(num); // j값에 따라 문자열이 변화하며 순차적으로 Count_Remain()을 실행합니다.
		}
		
	} // Count_All_Remain_Card : Count_Remain 메서드로 13번을 반복하기엔 너무나도 번거롭고 오래 걸립니다.
	// 따라서 메서드 하나를 실행시키는 것으로 모두 처리할 수 있도록 Count_All_Remain 함수를 만들었습니다.
	
	void Show_All_Remains_Count() {
		System.out.println("남은 카드들");
		for(int i=0; i<13; i++) {
			System.out.println((i+1) + " : "+ remained_card[i]);
		}
	} // Show_All_Remains_Count : 각 카드가 몇 장씩 남았는지 sysout으로 출력합니다. 다만, Count_Remain 혹은 Count_All_Remain 을 사용한 후에 사용하지 않으면 0이 출력될 뿐입니다.

	int Count_used() {
		
		used_number = 0; // 이미 계산된 것이 있을 수 있으니 한 번 초기화
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<13; j++) {
				if(card[i][j].equals("used")) {
					used_number++;
				}
				
			}
		}
		
		return used_number;
	}

}

	