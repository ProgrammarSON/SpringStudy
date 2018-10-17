package coupling;

public class TvUserDesignPattern {

	public static void main(String[] args) {

		BeanFactory factory = new BeanFactory();
		TV tv = (TV)factory.getBean(args[0]); // args[0] 명령행 인수를 받아내는 배열
		
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
  
	}
}
