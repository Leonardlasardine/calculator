package calculator;

import calculator.button.Numbers;
import calculator.button.Operators;
import calculator.button.Text;

public class Main {

	public static void main(String[] args) {
		Window window = new Window();
		new Text();
	    new Numbers();
	    new Operators();
	    
	    window.setVisible(true);
	}
}
