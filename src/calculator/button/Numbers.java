package calculator.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.text.BadLocationException;

import static calculator.Window.numberButton;
import static calculator.button.Text.firstZero;
import static calculator.button.Text.text;
import static calculator.button.Operators.resultShown;

import calculator.Window;

public class Numbers {

	public Numbers () {
		int hauteur = 260;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				JButton button = new JButton();
				
				button.setBounds(110 * j + 10, hauteur, 100, 50);
				button.setText(Integer.toString(numberButton));
				button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {

						if (resultShown) {
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

						//text.setText(text.getText() + button.getText());
						text.requestFocus();
					}
				});
				Window.addPanel(button);
				numberButton++;
			}
			hauteur -= 60;
		}
		
		JButton button0 = new JButton();

		button0.setBounds(10, 320, 100, 50);
		button0.setText("0");
		button0.addActionListener(new ActionListener() {

			 @Override
			 public void actionPerformed(ActionEvent e) {
				
				if (resultShown) {
					text.setText("");
					resultShown = false;
				}
				
				if (firstZero || text.getText().equals("0")) {
					text.setText("");
					firstZero = true;
				}
				
				try {
					text.getDocument().insertString(text.getCaretPosition(), "0", null);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
				text.requestFocus();
			 }
		});
		Window.addPanel(button0);
		
		JButton buttonComma = new JButton();

		buttonComma.setBounds(120, 320, 100, 50);
		buttonComma.setText(".");
		buttonComma.addActionListener(new ActionListener() {

			 @Override
			 public void actionPerformed(ActionEvent e) {
				
				text.setText(text.getText() + ".");
				text.requestFocus();
			 }
		});
		Window.addPanel(buttonComma);
	}
}
