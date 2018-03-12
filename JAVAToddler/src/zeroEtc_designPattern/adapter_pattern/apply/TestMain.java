package zeroEtc_designPattern.adapter_pattern.apply;

public class TestMain {

	public static void main(String[] args) {
		// 아답터 패턴 적용 전
		// (oldTv를 시청하기위해서는 해당 클래스의 인스턴스와 시청 메서드 호출이 요구됨)
		// (newTv를 시청하기위해서는 해당 클래스의 인스턴스와 시청 메서드 호출이 요구됨).
		IOldTV oldTv = new IOldTVImpl();
		oldTv.viewOldTV();
		
		INewTV newTv = new INewTVImpl();
		newTv.viewNewTV();
		
		System.out.println("##################################");
		
		// 아답터 패턴 적용 후
		AdapterCls adapter = new AdapterCls();
		adapter.viewNewTV();
		adapter.viewOldTV();
	}

}
