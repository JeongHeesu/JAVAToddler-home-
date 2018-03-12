package zeroEtc_designPattern.abstract_factory_pattern.apply;

public abstract class Car {
	private CarType model;
	private Location location;
	
	public Car(CarType model, Location location) {
		this.model = model;
		this.location = location;
	}
	
	protected abstract void construct();

	public CarType getModel() {
		return model;
	}

	public void setModel(CarType model) {
		this.model = model;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	
	
}
