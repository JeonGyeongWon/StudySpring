package chap02;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main2 {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = 
				new GenericXmlApplicationContext("classpath:applicationContext.xml");
		Greeter g1 = ctx.getBean("greeter", Greeter.class);
		Greeter g2 = ctx.getBean("greeter", Greeter.class);
		System.out.println("(g1 == g2) = " + (g1 == g2));
		ctx.close();
		// 싱글톤 이라는 것을 보여주는 예제이다
	}
}
