package entity;

public class MycombomaKH {
	String text;

	public MycombomaKH(String text) {
		super();
		this.text = text;
	}

	@Override
	public String toString() {
		return "MycombomaKH [text=" + text + "]";
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}


	
}
