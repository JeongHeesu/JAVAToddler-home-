package zero32.jmx.notification;

import zero16_ibatis.bean.MemberBean;

public interface INotificationMBean{
	public void setPresentTemperature(double temperature);
	public double getPresentTemperature();
	public double getMaximumTemperature(); 
	public double getMinimumTemperature();
	public void resetMaxAndMin();
	public void setMemberBean(MemberBean memberBean);
}
