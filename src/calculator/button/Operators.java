package calculator.button;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JButton;
import javax.swing.text.BadLocationException;

import calculator.Window;

import static calculator.button.Text.firstZero;
import static calculator.button.Text.result;
import static calculator.button.Text.text;

public class Operators {
	
	static JButton buttonEqual;
	static boolean resultShown;
	
	public Operators () {

		buttonEqual = new JButton();
		buttonEqual.setBounds(230, 320, 100, 50);
		buttonEqual.setText("=");
		buttonEqual.addActionListener(new ActionListener() {

			 @Override
			 public void actionPerformed(ActionEvent e) {
				 
				try {
				    ScriptEngineManager mgr = new ScriptEngineManager();
				    ScriptEngine engine = mgr.getEngineByName("JavaScript");
				    if (!text.getText().equals("")) {

					    result = engine.eval(text.getText());
						text.setText(result.toString());
						resultShown = true;
					} else {
						Toolkit.getDefaultToolkit().beep();
				    	text.setText("0");
					}
				} catch (ScriptException exeption) {
					
					text.setText("Error");
					resultShown = true;
					Toolkit.getDefaultToolkit().beep();
					exeption.printStackTrace();
				}
				
				text.requestFocus();
			 }
		});
		
		Window.addPanel(buttonEqual);
		
		JButton buttonC = new JButton();
		buttonC.setBounds(230, 80, 100, 50);
		buttonC.setText("C");
		buttonC.addActionListener(new ActionListener() {

			 @Override
			 public void actionPerformed(ActionEvent e) {
				 
				text.setText("0");
				firstZero = true;
				text.requestFocus();
				text.setCaretPosition(0);
			 }
		});
		
		Window.addPanel(buttonC);
		
		JButton buttonCE = new JButton();
		buttonCE.setBounds(340, 80, 100, 50);
		buttonCE.setText("CE");
		buttonCE.addActionListener(new ActionListener() {

			 @Override
			 public void actionPerformed(ActionEvent e) {
				 
				 if (text.getText().length() > 0) {
					text.setText(text.getText().substring(0, text.getText().length() - 1));
				 }
				text.requestFocus();
			 }
		});
		
		Window.addPanel(buttonCE);
		
		addButton(340, 140, 100, 50, "+");
		addButton(340, 200, 100, 50, "-");
		addButton(340, 260, 100, 50, "*");
		addButton(340, 320, 100, 50, "/");
		addButton(10, 80, 100, 50, "(");
		addButton(120, 80, 100, 50, ")");
	}
	
	public void addButton (int x, int y, int width, int height, String name) {
		
		JButton button = new JButton();

		button.setBounds(x, y, width, height);
		button.setText(name);
		button.addActionListener(new ActionListener() {
			
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 
				if (resultShown) {
					resultShown = false;
				}
				
				if (text.getText().equals("Error")) {
	        		text.setText("");
					resultShown = false;
	        	}
				
				 if (firstZero) {
						text.setText("");
						firstZero = false;
				}
				 
				 try {
						text.getDocument().insertString(text.getCaretPosition(), button.getText(), null);
				} catch (BadLocationException e1) {
						e1.printStackTrace();
				}
				 text.requestFocus();
			 }	 
		});
		Window.addPanel(button);
	}
}
