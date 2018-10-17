package coupling;


public class BeanFactory { // 팩토리 수동으로 정의해놓은 곳. 스프링에서는 설정파일을 이용해서 사용가능.
	public Object getBean(String beanName) {
		if(beanName.equals("samsung")) {
			return new SamsungTV();
		} else if(beanName.equals("lg")) {
			return new LgTV();
		}
		return null;
	}
}
