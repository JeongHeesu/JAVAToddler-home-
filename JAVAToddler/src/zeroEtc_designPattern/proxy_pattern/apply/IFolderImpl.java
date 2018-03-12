package zeroEtc_designPattern.proxy_pattern.apply;

public class IFolderImpl implements IFolder {
	
	@Override
	public void performOperation() {
		System.out.println("해당 폴더에대한 처리를 시작합니다.");
	}

}
