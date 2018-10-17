package coupling;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TvUserContainer {

	public static void main(String[] args) {

		AbstractApplicationContext  factory =								//미리필요한 bean객체를 등록해놓고 필요할때마다 불러서 사용.
				new GenericXmlApplicationContext("applicationContext.xml"); // 설정파일 읽어들임. 설정파일에 등록된 빈을 팩토리가 관리.
		//TV tv = (TV)factory.getBean("stv"); // 팩토리에게 요청. id가 tv인 빈을 넘겨달라.
		TV tv = (TV)factory.getBean(SamsungTV.class);
		tv.powerOn();
		tv.volumeUp();
		//System.out.println(((SamsungTV) tv).getProgram());
		
		/*tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		
		TV tv2 = (TV)factory.getBean("tv");
		tv2.powerOn();*/
	}
}
