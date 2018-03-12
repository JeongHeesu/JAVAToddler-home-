package zeroEtc_designPattern.builder_pattern.apply;

// 객체의 인스턴스화 시점에 복잡한 초기설정을 특정 객체에 전담시켜 활용함.
public class Pizza {
	private int size;
	private boolean cheese;
	private boolean pepperoni;
	private boolean bacon;

	// static 클래스내 public한 전역변수와 함수는 모두 static 설정됨.
	public static class Builder {
		// 필수
		private final int size;

		// 옵션
		public boolean cheese = false;
		public boolean pepperoni = false;
		public boolean bacon = false;

		// 필수적인 설정은 telescoping constructor pattern 적용.
		public Builder(int size) {
			this.size = size;
		}

		// 선택적인 설정은 javabeans pattern 적용
		public Builder cheese(boolean value) {
			this.cheese = value;
			return this;
		}

		public Builder pepperoni(boolean value) {
			this.pepperoni = value;
			return this;
		}

		public Builder bacon(boolean value) {
			this.bacon = value;
			return this;
		}

		public Pizza build() {
			return new Pizza(this);
		}
	}

	private Pizza(Builder builder) {
		size = builder.size;
		cheese = builder.cheese;
		pepperoni = builder.pepperoni;
		bacon = builder.bacon;
	}
	
	public void prtPizzaOrderInfo(){
		System.out.println("size : " + this.size + 
				" / cheese : " + this.cheese + 
				" / pepperoni : " + this.pepperoni + 
				" / bacon : " + this.bacon); 
	}
}
