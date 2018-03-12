package zeroEtc_designPattern.proxy_pattern.apply;

public class TestMain {
	public static void main(String[] args) {
		User user = new User("admin", "asdfasdf");
		IFolderProxy folderProxy = new IFolderProxy(user);
		folderProxy.performOperation();
		
		System.out.println("**************************************");
		
		User userWrong = new User("abc", "abc");
		IFolderProxy folderProxyWrong = new IFolderProxy(userWrong);
		folderProxyWrong.performOperation();
	}
}
