2022/05/11 오랜만에 내용을 보니 추억이 새록새록하다.

처음 공부했던 내용 그 이후로 많이 발전한거 같다.







2019/03/19 그간 공부했던 Spring을 다시 복습하면서 공부한 내용들을 기록할 것이다.

JSP를 공부할때 최범균님의 책을 보았는데 스프링역시 최범균님의 <b>스프링 4 프로그래밍 입문</b>을 볼것이다. 

목차는 아례와 같다.
https://github.com/madvirus/spring4fs에서 해당 책의 각 쳅터의 코드를 받아볼 수있다.
현재 페이지는 직접 손으로 코딩하면서 모르는 것들을 주석을 달고 다시 한번 정리하기 위해
만든 개인적인 페이지이다.

1. 스프링의 관한 간략 소개
2. 스프링 시작 이 책을 구입하기전 STS를 사용하였으나 책을 따라 하도록 결심했다.
3. 스프링 DI (의존)
4. DI 자동주입
5. 자바코드를 이용한 설정 
6. 빈 라이클 사이클과 범위
7. AOP 소개
8. DB연동 -> 여기서는 JDBCTemplet을 사용하였다. 개인적으로 MyBatis를 실무에서 많이 사용한다는 말을 들어서 MyBatis를 공부할 예정이다.
사용 DB는 MySQL이다
9. 스프링 MVC
10. 스프링 MVC 동작 방식
11. 매핑, 커맨드객체, 리다이렉트, 폼 태그, 모델
12. 메시지, 커맨드 객체 검증, 세션 , 인터셉터, 쿠키
13. 날짜 값 변화, @PathVariable, 익셉션 처리
14. 스프링 MVC 자바 설정
15. 웹 어플리케이션 구조 개요
16. 프로필과 프로퍼티

부록 A 메이븐 기초

2019/03/19 

Chapter1. jdk, Eclipse, Maven, Apache Tomcat등을 설치하였다.
※책에서는 jdk1.7, Maven 3.2, Apache Tomcat8을 사용하였으나
나는 jdk1.8, Maven3.6, Apache Tomcat8.5를 사용하였다. 톰캣에 경우 기존에 사용하려던 8.0을 사용하려고 했으나
2019년 2월 중순? 부터 8.0 다운로드가 사라지고 8.5가 생겼기 때문에 8.5를 사용하였다.

각 설치 방법은 기초적인 것이므로 생략하였다.
Chapter1의 핵심은 스프링은3버전 이후 각 핵심 모듈을 메이븐을 통해 다운받을 수 있도록 되었다. 


Chapter2. 스프링 프로젝트를 시작했다. 
이클립스 or STS에서 프로젝트를 생성하지 않고 전체 수동으로 설치하였다. 
나의 경우 C드라이브 바로 밑에 sp4-chap02라는 폴더와 하위폴더로 src, main, java, resource를 만들어주었다
이때 java, resource는 main밑에 만들어 주었다. <b>그 이후 메이븐 프로젝트의 설정파일인 pom.xml</b>을 수동으로 생성하였다
수동으로 작성한 후 명령 프롬포트에서 해당 프로젝트를 메이븐으로 컴파일 시켜주었다.
확실히 수동으로 하니 원래 걸렸던 시간에 5배정도는 더 걸린것 같다.

여기서 잠깐 메이븐에 저장소와 폴더 구조에 대해 요약해야겠다.

메이븐은 컴파일 or 코드 실행시 <b>로컬 저장소</b>에서 [groupId]\[artifactId]\[version] 폴더에 artifactId-version.jar 형식의 파일을 찾는다.
만약 존재하지 않으면 <b>원격 저장소</b>에서 해당 파일을 다운받아 로컬 저장소에 복사한 후 사용한다.
※보통 사용자홈폴더/.m2폴더를 로컬 저장소로 사용한다.
※원격 저장소는 http://repo.meven.apache.org 이다. 파일을 복사할때 해당 아티팩트가 다른 아티팩트를 의존하고 있다면 그 아티팩트도 같이 다운로드 받는다. 이를 <b>의존의 전이</b>라고 한다. 수동으로 하기전 나는 이 원격저장소에서 Maven이 적힌 탭을 누르고 복붙하여 가져왔다.

폴더구조로는 java는 자바소스코드, resource는 설정XML파이리나 프로퍼티 파일등을 위치시키는 곳이다.
만약 웹 어플리케이션 일 경우 webapp에 JSP 코드나 web.xml 파일 등이 들어간다.

수동으로 생성한 프로젝트를 임포트 하는것은 생략하겠다.

해당 프로젝트에서 xml(설정)파일을 만들어 <bean>태그를 사용하여 객체를 생성하고 property를 이용하여 set메서드를 호출하였다.
이후 Main클래스에서 GenericXmlApplicationContext(xml파일을 읽어 bean를 생성하고 관리하는 역할)을 이용하여 테스트 하였다.
  
GenericXmlApplicationContext은 중요한 개념이니 정리를 한번 더 하겠다.

스프링의 핵심 기능은 객체 생성 및 초기화이다.
이와 관련된 기능은 ApplicationContext인터페이스의 정의되어있다. 
※상속도는 P47에 표시되엉 있다.
자주 사용하는 스프링 컨테이너 객체(최하위 계층에 위치)를 정리하겠다.

GenericXmlApplicationContext : XML파일 읽어들임 <br>
AnnotationConfigApplicationContext : <br>
GenericGroovyApplicationContext : 솔직히 아직 그루비가 무엇인지 정확히 모르겠다. 그루비 설정 정보를 읽어들임 <br>
이 3가지를 많이 사용하는데 나는 보통 GenericApplicationContext나 GenericXmlApplicationContext를 많이 사용했다.
참고로 GenericApplicationContext은 위 3개의 부모 클래스이다.

<b>중요! 스프링은 별도의 설정을 하지 않을 경우 한 개의 빈 객체만을 생성한다 (싱글톤)</b> 이외에 프로토타입 빈이 있지만 이 후 설명하겠다.

2019/03/20

Chapter3. DI(의존주입)

의존 : 한 클래스가 다른 클래스의 메서드를 실행할 때 이를 의존이라고 부른다. 본인은 이것을 모델 2 커맨드 패턴에서 서비스클래스가 dao클래스를 사용하는것이 하나의 의존 예제라고 생각하였다.

의존하는 대상이 있으면 그 대상을 구하는 방법이 필요하다 대표적으로 
남궁성님의 자바의정석 책에서 is ~ a 관계와 has ~ a 관계를 생각해보자 is~a는 상속, has~a는 포함이다 즉 포함의 관계라고 생각하면 편하다.
이와같이 직접적으로 생성하는 것이 쉽다. 하지만 유지보수관점에서 문제가 있다.

유지보수관점 문제 : 직접적으로 객체를 생성시 객체가 변경이되면 직접적으로 생성한 모든 클래스에서 코드를 수정해야한다.
때문에 스프링에서는 DI를 이용하여 의존 객체를 구한다 DI를 이용하면 객체가 변경이되어도 의존하는 부분 1곳에 코드만 수정하면 되기 때문이다.

DI는 의존 객체를 전달받는 방식을 사용한다. Chapter3 예제를 보면서 하는것이 이해하기 편하다.

예제는 회원가입 예제이나 이 책은 뒷부분에서 DB에 관한 설명을 하므로 DAO는 Map객체를 이용하여 구현하였다.
또한 XML파일을 통한 DI가 아닌 단순 DI의 개념을 이해하기 위한 예제이다.
위 예제를 실행해보았다면 DI의 대헤 이해가 되었으며 <b>조립기클래스</b>가 무엇인지 이해가 될것이다. 

조립기클래스에 대해 이해를 했다면 이제 본격적으로 스프링의 DI를 시작할 것이다.
앞서 Chapter2에서 <bean>태그를 사용해보았다. 사실 이것이 DI다.
constructor-arg와 property 이 2개의 사용법과 스프링 컨테이너에 대헤 이해하고 있다면 DI의 대해 어느정도는 이해를 하고 있는것이다.

constuctor-arg :생성자 <br>
property : set메서드 <br>

이 두가지 방법은 각각의 장단점이 있는데 생성자태그의 장점이 set태그의 단점이며 set태그의 장점이 생성자태그의 단점이다.

참고로 각 객체의 값을 전달할때는 ref 사용하지만 기본값을 지정할경우 value에 저장하면된다.
기본값의 경우 예를들어 1이라는 값을 저장한다면 이 1의 값이 int, double, long인지 알 수없다.
이에 대해서는 스프링이 알아서 알맞게 값의 타입을 변환해 준다.

추가로 2개 이상의 설정파일 정보를 읽어들이때 방법은 2가지가 있는데
본인은 설정파일에 <import> 태그를 사용하는것을 선호한다.
다른 방법으로는 스프링 컨테이너를 호출할떄 ','를 이용하여 배열형태로 전달해주는것이다.
 
여기까지가 <b>DI수동주입</b>이다.

Chapter4에서는 <b>자동주입</b>을 정리할것이다. 솔직히 개인적으로 자동주입이 편하다...

2019/03/21 ~ 2019/03/23
-> 개인사정으로 인해 정리를 하지 못하였다.

2019/03/24

Chapter4 자동 의존주입
자동으로 의존 주입 기능을 사용하는 것은 간단하다.
1. 주입 대상에 @(어노테이션)Autowried 혹은 @Resource를 사용하고
2. XML설정 파일에 <context:annotation-config/> 설정을 추가해주기만 하면 된다.
위와 같이하면 <bean>태그내에 <construct-arg>(생성자) <property>(set메서드)를 사용하지 않아도 된다.
앞선 3장의 예제를 변경하여 사용해보겠다.


 꼭 주입을 하지 않아도 될때가 있다. 그 이유로 bean태그를 만들지 않았지만 @AutoWried가 적용되어있다면 <br>
 required속성값을 false로 사용하여 명시해주어야 한다. <br>
@AutoWired(required = (true or false)) , @Resource는 없다.<br>
->주의점! : 기본 생성자는 반드시 있어야한다. p111 참조

Tip  
1. 자동 주입에서는 타입 매칭을 동일한타입(x)이 아닌 할당할 수 있는 빈 객체(o)로 해준다.
2. 만약 XML설정 파일에서 동일타입 bean이 2개가 존재한다면 스프링 컨테이너 초기화시 예외가 발생한다.
어떤 객체를 주입해야할지 모르기 때문 이를 해결하기 위해 @Qualifier어노테이션을 사용할수 있다.
-> 만약 2개의 동일타입이 존재하더라도 bean태그의 id값과 set메서드의 id값이 일치하면 예외를 발생시키지 않는다. (이름매칭)
3. @AutoWired는 빈 태그의 타입을 이용하여 찾고 @Resource는 이름값을 이용해서 찾는다. 
4. 당연한 거지만 자동주입보다는 명시적 주입이 우선순위가 더 높다.

개인적으로 중요하다 생각
★AutoWried 적용 순서
1. 타입이 같은 빈 객체를 검색 한 개면 그 빈 객체를 사용 @Qualifier가 명시되어 있을 경우 같으 값을 가져야함
2. 2개 이상 존재시 @Qualifier로 지정한 빈 객체를 사용
3. 2개 이상 존재하고 @Qualifier로 명시되어 있지 않으면 이름이 같은 빈 객체를 찾음

@Qualifier("값")으로 사용한다.
XML 설정파일에 빈 태그 밑 자식태그로 <qualifier>태그를 추가하고 속성값(value)로 값을 지정한후
주입하고 싶은 필드나 메서드에 적어주면 된다.
  
@AutoWired VS @Resource
개인적으로 http://goo.gl/AyLfR9 글을 읽은후 @AutoWired를 더 선호한다. 

Chapter4 
