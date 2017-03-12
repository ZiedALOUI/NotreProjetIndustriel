package data;

public class TimedValue {
	
	private double value;
	private double time;
	
	public TimedValue() {
		super();
		
	}
	
	public TimedValue(double value) {
		super();
		this.value = value;
	}
	
	public TimedValue(double value, double time) {
		super();
		this.value = value;
		this.time = time;
	}
	
	
	public double getvalue() {
		return value;
	}
	public void setvalue(double value) {
		this.value = value;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}

	public void affiche()
	{
		System.out.print(value);
		System.out.print(" ");
		System.out.println(time);
	}
	
}
