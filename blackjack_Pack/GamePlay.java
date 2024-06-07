// 게임을 실행할 메인 클래스입니다.
// GamePlayer, GameDealer, AnotherPlayer의 클래스 내용이 거의 유사한데, 상속과 abstract로 처리할 걸 그랬나
package blackjack_Pack;

public class GamePlay{

	public static void main(String[] args) {
		
		GameProcess GP = new GameProcess();
		
		GP.open_game();
		
		GP.make_yourHands();
		
		GP.who_is_the_winner();
		
		GP.we_have_a_winner();

	}

}
