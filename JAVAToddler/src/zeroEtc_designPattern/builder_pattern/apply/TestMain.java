package zeroEtc_designPattern.builder_pattern.apply;

public class TestMain {

	public static void main(String[] args) {
		Pizza pizza = new Pizza.Builder(12).cheese(true).pepperoni(true).bacon(true).build();
		pizza.prtPizzaOrderInfo();
	}

}
