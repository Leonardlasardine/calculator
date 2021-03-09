package calculator.button;

import static calculator.button.Operators.resultShown;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import calculator.Window;

public class Text {
	
	public static JTextArea text;
	public static Object result;
	public static boolean firstZero = true;

	public Text () {

		text = new JTextArea();
		text.setBounds(10, 10, 430, 60);
		text.setFont(new Font("Calibri", Font.PLAIN, 22));
		text.setText("0");
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
	    text.addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent e) {

	            char key = e.getKeyChar();
	            
	        	if (key != '+' && key != '-' && key != '/' && key != '*' && key != '.' && key != '(' && key != ')') {
	            	
	        		if (resultShown) {
						text.setText("");
						resultShown = false;
					}
	            }
	        	
	        	if (text.getText().equals("Error")) {
	        		text.setText("");
					resultShown = false;
	        	}
	        	
	        	if (firstZero || text.getText().equals("0")) {
					text.setText("");
					firstZero = true;
				}

	            if (((key < '0') || (key > '9')) && (key != KeyEvent.VK_BACK_SPACE) && (key != '+') && (key != '-')
						  && (key != '/') && (key != '*') && (key != '.') && (key != '(') && (key != ')')) {
	            	
	            		Toolkit.getDefaultToolkit().beep();
	            		e.consume();
	            } else {
	            	firstZero = false;
	            }
	            if (key == KeyEvent.VK_ENTER) {
	            	
	            	Operators.buttonEqual.doClick();
	            }
	        }
	    });
	    
	    JScrollPane jScrollPane = new JScrollPane(text);
	    jScrollPane.setBounds(text.getBounds());

		Window.addPanel(jScrollPane);
		text.requestFocus();
	}
}
