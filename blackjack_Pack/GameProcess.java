// 게임의 세부적인 진행 사항을 구성하는 게임프로세스입니다.

package blackjack_Pack;
import java.util.Scanner;

public class GameProcess {
	
	CardManager cm = new CardManager();
	
	GamePlayer user = new GamePlayer();
	GameDealer dealer = new GameDealer();
	AnotherPlayer ap = new AnotherPlayer();
	
	static int save_myScore;
	
	public GameProcess() {}
	
	void open_game() {
		
		cm.Create_Card();
		
		System.out.println("첫 번째 카드를 받습니다.");
		
		user.Get_hands(cm.draw_Card());
		user.Show_hands();
		
		dealer.Get_hands(cm.draw_Card());
		
		ap.Get_hands(cm.draw_Card());
				
		System.out.println("첫 번째 카드는 모두에게 공개됩니다.");
		System.out.println("딜러 : " + dealer.Pick_Hands(0));
		System.out.println("또다른 플레이어 : " + ap.Pick_Hands(0));
		// System.out.println("본인 : " + user.Pick_Hands(0)); 이건 이미 봤으니까 또 보여줄 필요 없으려나?
		
		String[] unveiled_cards = {dealer.Pick_Hands(0), user.Pick_Hands(0), ap.Pick_Hands(0)};
		
		dealer.recognize_yours(unveiled_cards);
		ap.recognize_yours(unveiled_cards);
		
		System.out.println("두 번째 카드를 받습니다.");
		
		dealer.Get_hands(cm.draw_Card());
		ap.Get_hands(cm.draw_Card());
		
		user.Get_hands(cm.draw_Card());
		user.Show_hands();
		
	}
	
	void make_yourHands() {
		boolean show_must_go_on = true;
		
		while(show_must_go_on) { 
			
			if(dealer.game_continue) { // 딜러의 턴
				if(dealer.make_a_decision()) { // 딜러는 카드 드로우에 대한 선택지가 없는 수준이라 판단에 인수가 필요 없음.
					dealer.Get_hands(cm.draw_Card());
					System.out.println("딜러가 카드를 한 장 받습니다.");
				}
				else {				
					System.out.println("딜러가 더 이상 카드를 받지 않습니다.");
					dealer.good_game();
				}
			}
			else {
				System.out.println("딜러는 이미 카드 구성을 끝마쳤습니다.");
				dealer.good_game();
			}
			
			if(ap.game_continue) { // 또다른 플레이어의 턴
				int ap_count = cm.Count_used();
				
				if(ap.make_a_decision(ap_count)) { // 어나더 플레이어는 카드 드로우에 대한 선택이 가능해서 판단에 인수가 필요함.
					ap.Get_hands(cm.draw_Card());
					System.out.println("또다른 플레이어가 카드를 한 장 받습니다.");
				}
				else {
					System.out.println("또다른 플레이어가 더 이상 카드를 받지 않습니다.");
					ap.good_game();
				}	
			}
			else {
				System.out.println("또다른 플레이어가 더 이상 카드를 받지 않습니다.");
				ap.good_game();
			}
			
			if(user.game_continue) { // 유저의 턴
				
				Scanner sc = new Scanner(System.in);
				System.out.println("카드를 더 받으시겠습니까? Y / N");
				String answer = sc.nextLine();
				
				if(answer.equals("N")||answer.equals("n")) {
					user.good_game();
				}
				else {
					user.Get_hands(cm.draw_Card());
					user.Show_hands();
				}
				
			}
			
			if((dealer.game_continue==false)&&(user.game_continue==false)&&(ap.game_continue==false)) { // 모두 턴을 종료하면 덱 구성 끝
				show_must_go_on = false;
			}
		
		}
	}
	
	void who_is_the_winner() {
		
		System.out.println("모든 플레이어와 딜러의 카드 수령이 끝났습니다.");
		System.out.println("점수 계산을 진행합니다.");
		
		save_myScore = user.Calc_myScore();
		
		System.out.println("모든 점수 계산이 끝났습니다. 결과를 공개합니다.");
		
		System.out.println("============= 딜러 =============");
		dealer.Show_hands();
		System.out.println("점수 : " + dealer.Final_Dealer_Score());
		System.out.println("===============================");
		
		System.out.println("");
		System.out.println("===== 플레이어2(또다른 플레이어) =====");
		ap.Show_hands();
		System.out.println("점수 : " + ap.Calc_myScore());
		System.out.println("===============================");

		System.out.println("");
		System.out.println("========= 플레이어1(본인)=========");
		user.Show_hands();
		System.out.println("점수 : " + save_myScore);
		System.out.println("===============================");
				
	}
	
	void we_have_a_winner() {

		this_is_judgement("플레이어1(본인)", save_myScore);
		this_is_judgement("플레이어2(또다른 플레이어)", ap.Calc_myScore());
	}
	
	void this_is_judgement(String player_name, int playerS_score) { // 아래는 블랙잭 게임 결과에 따른 승패에 대한 판단표입니다.
/*		
		딜러	
플레이어	    	 21초과		블랙잭 		21미만
		21초과	딜러 win		딜러 win		딜러 win
		블랙잭	플레이어 win	무승부		플레이어 win
		21미만	플레이어 win  딜러 win		점수 판단(동점 시 무승부)
*/	
// 이러한 내용이 담긴 String 2차원 배열로서 표를 구성하고, 점수에 따라 표에서 적절한 값을 찾도록 하였습니다.
		
		int dealerS_score = dealer.Final_Dealer_Score();
		
		String[][] judgement_table = {
		{"딜러 win", "딜러 win", "딜러 win"},
		{"플레이어 win", "무승부", "플레이어 win"},
		{"플레이어 win", "딜러 win", "점수판단"}
		};
		
		int dealerS_side = (dealerS_score == 21) ? 1 : ((dealerS_score > 21) ? 0 : 2); 
		int playerS_side = (playerS_score == 21) ? 1 : ((playerS_score > 21) ? 0 : 2);
		
		String judge_text = judgement_table[playerS_side][dealerS_side]; // 배열의 height가 플레이어 쪽이고, width가 딜러쪽.
		
		if(judge_text.equals("딜러 win")) {
			System.out.println(player_name + "님, 안타깝습니다. 딜러의 승리입니다.");
		}
		else if(judge_text.equals("플레이어 win")){
			System.out.println(player_name + "님, 축하드립니다. 당신의 승리입니다.");
		}
		else if(judge_text.equals("무승부")) {
			System.out.println(player_name + "님, 이번 경기는 무승부입니다.");
		}
		else { // 점수판단일 경우
			if(dealerS_score>playerS_score) {
				System.out.println(player_name + "님, 안타깝습니다. 딜러의 승리입니다.");
			}
			else if(dealerS_score<playerS_score) {
				System.out.println(player_name + "님, 축하드립니다. 당신의 승리입니다.");
			}
			else {
				System.out.println(player_name + "님, 이번 경기는 무승부입니다.");
			}
		}
		
	}

}
