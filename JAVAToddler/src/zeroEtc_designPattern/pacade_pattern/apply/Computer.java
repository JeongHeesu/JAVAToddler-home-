package zeroEtc_designPattern.pacade_pattern.apply;

public class Computer {
	public void startComputer(){
		CPU cpu = new CPU();
		Memory memory = new Memory();
		HardDrive hardDrive = new HardDrive();
		
		cpu.freeze();
		memory.load(987654321l, hardDrive.read(123459876l, 1024));
		cpu.jump(123456789l);
		cpu.execute();
	}
}
