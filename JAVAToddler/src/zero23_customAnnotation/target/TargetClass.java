package zero23_customAnnotation.target;

import zero23_customAnnotation.definition.ParamAnnotation;
import zero23_customAnnotation.definition.StringAnnotation;

@StringAnnotation(belongTo="타겟클래스", value="런타임시취득어노테이션", defaultValue="1")
public class TargetClass {
	
	@StringAnnotation(belongTo="타겟클래스", value="전역변수")
	public double dblValue;
	
	@StringAnnotation(belongTo="타겟클래스", value="생성자")
	public TargetClass() {}
	
	@StringAnnotation(belongTo="타겟클래스", value="메서드")
	private void testFunc(@ParamAnnotation(belongTo="타겟클래스", value="param1") int x,
			@ParamAnnotation(belongTo="타겟클래스", value="param2") int y){
		System.out.println(x+y);
	}

}
