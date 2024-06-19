블랙잭
코딩학원 3주차만에 무언가를 만들어내서 스스로 뿌듯하고 기쁘지만, 부족한 점이 많았음.
1. 상속을 안 써서 비효율
   - 뒤늦게 생각났지만 GamePlayer, GameDealer, AnotherPlayer 겹치는 기능이 대부분임. 차라리 상속을 사용해서 겹치는 부분의 코딩 및 수정에 들어가는 시간을 줄였더라면 훨씬 효율적이고 좋았을 것임
   - 거의 막힘 없이 쳤는데도, 코딩에 4~5시간 정도 걸렸고, 사소한 수정도 똑같은 걸 3번씩 해서 매우 비효율적이었음.
2. 여전히 public, private나 static에 대한 이해도가 부족함. 그리고 그나마 this는 이제 이해함
3. String 타입의 변수를 활용하는 것이 까다로웠음
   - 특히 계산과정에서 발생하는 null값의 처리가 문제였음
   - AnotherPlayer는 unveiled cards를 recognized yours로 받아옴.
   > 받아온 카드와 자신의 패를 대조해서, 받아온 카드 중 자신의 카드는 제외하고 새롭게 알게 된 카드만 추려냄.
   >> 그리고 새롭게 알게 된 카드와 자신의 패를 합쳐서 recognized cards에 저장함.
   >>> recognized cards에 저장한 카드는 나중에 카드 카운팅 과정을 거쳐 현재 자신의 상황에서 카드를 더 받는 것이 이득일지, 멈추는 것이 이득일지 컴퓨터가 자체적으로 판단하는 데에 사용됨.
   - 근데 위의 저장 과정으로 인해 사용 시에 문제가 발생함.
   > unveiled cards의 갯수를 확인하고, 그것을 하나하나 대조하도록 하여 모든 카드가 빠짐없이 확인되면서도, 카드 갯수만큼만 반복하는 만큼 null값이 들어갈 여지가 없다고 생각했음
   >> 그러나 unveiled cards (= n개)에는 필연적으로 자신의 카드가 하나는 섞여 있고, 자신의 카드는 제외하고 저장하니까 저장된 갯수는 (n-1)개인데 앞으로 체크는 n번만큼 함.
   >>> 그러니 아직 값이 저장되지 않은 곳(null값이 들어있는 곳)까지 계산에 사용하면서 null 오류가 발생함.
   - 이 문제는 저장 과정이 끝난 후 앞으로 카운트에 사용할 숫자에서 자신의 카드 갯수만큼 빼서 해결함.
4. 간단하게 구성할 수 있는 조건문도 괜히 복잡하게 꼬아서 오류가 났었음. 조건문 구성할 때 좀 더 빈틈없고 간결하게 구성할 필요가 있음.