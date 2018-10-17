package coupling;

public class TvUser {

	public static void main(String[] args) {
		//TV tv = new SamsungTV();
		TV tv = new LgTV();		// 개발표준(interface)에 맞추어 개발했기때문에 클래스 바뀌어도 사용가능. 확장성을 고려함. 클래스간의 결합도가 낮음. 
								// 초창기 클래스 교체방식. 소스변경 필요함.
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();

	}
}
