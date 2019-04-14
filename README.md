## 2019 포탈서비스 개발 방법론 

> "Known To Unknown"

### TDD?
> 테스트 주도 개발(Test Driven Development)

**'테스트 케이스를 먼저 만들고 테스트를 통과하기 위한 것을 짜는것'**
### 무슨 말일까?
* * *
보통은 개발을 하게되면 코딩이 끝난 후 테스트를 하게 된다.<br>
**TDD는 테스트를 만들어가면서 에러를 통한 피드백을 받으면서 리펙토링을 해나가며 개발을 완성하는 것.**

결국, TDD는 개발기법이나 기술이기 보다는 심리적인 것에서부터 자연스럽게 나오는 방법이다.
<pre>
<code>1.개발을 하기 전에 '이렇게 하면 되겠다'라는 생각으로부터 시작해서
2.개발을 하다보면 에러(피드백)에 부딪히게 될것이다.
3.개발하기 전에 했던 생각과 에러(피드백)에 갭이 생기게 됨
4.이 둘 사이의 갭을 인지하고 줄여나간다(Refactory)면 TDD를 하고 있는것이다!</code>
</pre>

### 어떻게 리펙토리를 하는데?
**객치지향방식(OOP)으로, 5대원칙(SOLID)을 생각하면서!**
 * **S - SRP(Single responsibility principle) 단일 책임 원칙**<br>
  클래스든 함수든 단 하나의 책임만 가진다.(클래스를 수정할 이유가 단 하나여야 한다)
 * **O - OCP(Open Closed Principle) 개방 - 폐쇄 원칙**<br>
  변경에는 닫혀있고 확장에는 열려있어야 한다.
 * **L - LSP(Liskov Substitusion Principle) 리스코프 치환 법칙**<br>
   
 * **I - ISP(Interface Segregation Principle) 인터페이스 분리 원칙**<br>
 
 * **DIP(Dependency Inversion Principle) 의존성 역전 법칙**<br>
 




