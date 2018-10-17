package coupling;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//@Component("stv")	//자동으로 samsungTV bean으로 등록
@Component	//componet 지정시 값을 안줄경우 class의 명(첫 단어 소문자)이 default.
public class SamsungTV implements TV {

	//private SonySpeaker speaker;
	/*
	@Autowired	//speaker인 component를 찾아준다, 만약 component가 여러개일 경우 에러 발생
	@Qualifier("sony")	//component가 여러개일 경우 qualifier를 추가하여 지정해준다. autowired를 무조건 같이 쓴다.
	*/
	@Resource(name="apple")	//resource만 적을시 autowired만 선언한 것과 같다.
	private Speaker speaker;
	private int price;	//필드(맴버변수) = 속성(property)
	private List<String> program;
	
	public List<String> getProgram() {
		return program;
	}

	public void setProgram(List<String> program) {
		this.program = program;
	}

	public Speaker getSpeaker() {
		return speaker;
	}

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	//생성자
	public SamsungTV() {}
	
	public SamsungTV(Speaker speaker) {
		System.out.println("SamsungTV 생성자 호출");
		this.speaker = speaker;
	}
	
	public SamsungTV(Speaker speaker, int price) {
		System.out.println("SamsungTV 생성자 호출 - 인수 2");
		this.speaker = speaker;
		this.price = price;
	}
	
	public void initMethod() {
		System.out.println("초기화 매서드 호출");
	}

	public void destoryMethod() {
		System.out.println("종료 매서드 호출");
	}
	public void powerOn() {
		System.out.println("SamsungTV powerOn");
	}
	public void powerOff() {
		System.out.println("SamsungTV powerDown");
	}
	/*public void volumeUp() {
		System.out.println("SamsungTV volumeUp");
	}
	public void volumeDown() {
		System.out.println("SamsungTV volumeDown");
	}	*/
	public void volumeUp() {
		speaker.volumeUp();
	}
	public void volumeDown() {
		speaker.volumeDown();
	}
	
	
}
