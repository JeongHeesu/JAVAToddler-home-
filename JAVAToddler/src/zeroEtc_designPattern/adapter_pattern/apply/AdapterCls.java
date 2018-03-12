package zeroEtc_designPattern.adapter_pattern.apply;

// INewTV 인터페이스 구현 및 IOldTVImpl 상속으로 해당 클래스는 INewTV is-a이고
// IOldTVImpl is-a이며 has-a가 됨.
public class AdapterCls extends IOldTVImpl implements INewTV {

	@Override
	public void viewNewTV() {
		if(true){
			System.out.println("확장된 새로운 TV를 볼수있습니다.");
		}else{
			System.out.println("새로운 TV를 볼수있습니다.");
		}
	}

	@Override
	public void viewOldTV() {
		if(true){
			System.out.println("확장된 오래된 TV를 볼수있습니다.");
		}else{
			super.viewOldTV();
		}
	}
	
	

}
