package client.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.base.*;


/**
 * Implementation for the login view, which lets the user create a new account and login
 */
@SuppressWarnings("serial")
public class LoginView extends OverlayView implements ILoginView {

	private final int LABEL_TEXT_SIZE = 40;
	private final int BUTTON_TEXT_SIZE = 28;
	private final int BORDER_WIDTH = 10;

	private JLabel label;
	private JButton signInButton;
	private JPanel buttonPanel;

	public LoginView() {
		
		this.setOpaque(true);
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.black, BORDER_WIDTH));
		
		label = new JLabel("Login View");
		Font labelFont = label.getFont();
		labelFont = labelFont.deriveFont(labelFont.getStyle(), LABEL_TEXT_SIZE);
		label.setFont(labelFont);
		this.add(label, BorderLayout.CENTER);
		
		signInButton = new JButton("Sign in");
		signInButton.addActionListener(actionListener);
		Font buttonFont = signInButton.getFont();
		buttonFont = buttonFont.deriveFont(buttonFont.getStyle(), BUTTON_TEXT_SIZE);
		signInButton.setFont(buttonFont);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));		
		buttonPanel.add(signInButton);		
		this.add(buttonPanel, BorderLayout.SOUTH);	
	}

	private ActionListener actionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == signInButton) {
				
				getController().signIn();
			}
		}	
	};

	@Override
	public ILoginController getController() {

		return (ILoginController)super.getController();
	}

	@Override
	public String getLoginUsername() {

		return null;
	}

	@Override
	public String getLoginPassword() {

		return null;
	}

	@Override
	public String getRegisterUsername() {

		return null;
	}

	@Override
	public String getRegisterPassword() {

		return null;
	}

	@Override
	public String getRegisterPasswordRepeat() {

		return null;
	}
	
}

