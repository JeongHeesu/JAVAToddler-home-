package zeroEtc_designPattern.proxy_pattern.apply;

public class IFolderProxy implements IFolder {
	private IFolder folder;
	private User user;
	
	public IFolderProxy(User user) {
		this.user = user;
	}

	@Override
	public void performOperation() {
		// User의 Folder 처리 권한 검증
		if(user.getUserName().intern() == "admin".intern() &&
				user.getPassword().intern() == "asdfasdf".intern()){
			this.folder = new IFolderImpl();
			this.folder.performOperation();
		}else{
			System.out.println("해당 폴더에대한 처리권한이 없습니다.");
		}
		
	}

}
