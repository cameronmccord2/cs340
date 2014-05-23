package client.join;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.base.OverlayView;
import client.data.GameInfo;
import client.data.PlayerInfo;

/**
 * Implementation for the join game view, which lets the user select a game to
 * join
 */
@SuppressWarnings("serial")
public class JoinGameView extends OverlayView implements IJoinGameView
{

	private final int LABEL_TEXT_SIZE = 40;
	private final int PANEL_TEXT_SIZE = 14;
	private final int BUTTON_TEXT_SIZE = 28;
	private final int BORDER_WIDTH = 10;

	private JLabel label;
	private JLabel subLabel;

	private JLabel hash;
	private JLabel name;
	private JLabel currentPlayer;
	private JLabel join;

	private JButton createButton;
	private JButton joinButton;

	private JPanel labelPanel;
	private JPanel gamePanel;
	private JPanel buttonPanel;

	private List<GameInfo> games;
	private PlayerInfo localPlayer;

	public JoinGameView()
	{
		this.initialize();
	}

	private void initialize()
	{
		this.initializeView();
	}

//	public void showModal()
//	{
//		System.out.println("showModal called");
//		super.showModal();
//	}

	private void initializeView()
	{
		this.setOpaque(true);
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.black, BORDER_WIDTH));

		label = new JLabel("Welcome to the game hub");
		Font labelFont = label.getFont();
		labelFont = labelFont.deriveFont(labelFont.getStyle(), LABEL_TEXT_SIZE);
		label.setFont(labelFont);
		subLabel = new JLabel("Join an existing game, or create your own");
		labelFont = subLabel.getFont();
		labelFont = labelFont.deriveFont(labelFont.getStyle(), LABEL_TEXT_SIZE * 2 / 3);
		subLabel.setFont(labelFont);

		labelPanel = new JPanel();
		labelPanel.setLayout(new FlowLayout());
		labelPanel.add(label);
		labelPanel.add(subLabel);
		this.add(labelPanel, BorderLayout.NORTH);

		// This is the header layout
		gamePanel = new JPanel();
		gamePanel.setLayout(new GridLayout(0, 4));
		hash = new JLabel("#");
		labelFont = new Font(labelFont.getFontName(), Font.BOLD, PANEL_TEXT_SIZE);
		hash.setFont(labelFont);
		name = new JLabel("Name");
		name.setFont(labelFont);
		currentPlayer = new JLabel("Current Players");
		currentPlayer.setFont(labelFont);
		join = new JLabel("Join");
		join.setFont(labelFont);

		gamePanel.add(hash);
		gamePanel.add(name);
		gamePanel.add(currentPlayer);
		gamePanel.add(join);

		// This is the looped layout
		if (games != null && games.size() > 0)
		{
			labelFont = labelFont.deriveFont(labelFont.getStyle(), PANEL_TEXT_SIZE);
			for (GameInfo game : games)
			{
				JLabel tmp1 = new JLabel(String.valueOf(game.getId()));
				tmp1.setFont(labelFont);
				gamePanel.add(tmp1);

				JLabel tmp2 = new JLabel(game.getTitle());
				tmp2.setFont(labelFont);
				gamePanel.add(tmp2);

				String players = String.valueOf(game.getPlayers().size()) + "/4 : ";
				for (int j = 0; j < game.getPlayers().size(); j++) {
					if (j < game.getPlayers().size() - 1) {
						players = players + game.getPlayers().get(j).getName() + ", ";
					} else {
						players = players + game.getPlayers().get(j).getName();
					}
				}
				JLabel tmp3 = new JLabel(players);
				tmp3.setFont(labelFont);
				gamePanel.add(tmp3);

				//gamePanel.add(box1);
				JButton joinBtn = null;


				for(PlayerInfo p : game.getPlayers()){
					if(p.getId() == localPlayer.getId()
							&& p.getName().equals(localPlayer.getName())){
						joinBtn = new JButton("Re-Join");
					}
				}
//				if (game.getPlayers().contains(localPlayer))
//				{
//					joinButton = new JButton("Re-Join");
//				}
				if(joinBtn == null){
					if (game.getPlayers().size() >= 4)
					{
						joinBtn = new JButton("Full");
						joinBtn.setEnabled(false);
					}
					else
					{
						joinBtn = new JButton("Join");
					}
				}

				joinBtn.setActionCommand("" + game.getId());
				joinBtn.addActionListener(actionListener);
				gamePanel.add(joinBtn);
			}
		}

		//Add all the above
		this.add(gamePanel, BorderLayout.CENTER);

		joinButton = new JButton("Join");
		joinButton.addActionListener(actionListener);
		Font buttonFont = joinButton.getFont();
		buttonFont = buttonFont.deriveFont(buttonFont.getStyle(), BUTTON_TEXT_SIZE);
		joinButton.setFont(buttonFont);

		createButton = new JButton("Create Game");
		createButton.addActionListener(actionListener);
		createButton.setFont(buttonFont);

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.add(createButton);
//		buttonPanel.add(joinButton);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}

//	private void updateGames()
//	{
//		// This is the looped layout
//			if (games != null && games.length > 0) {
//				labelFont = labelFont.deriveFont(labelFont.getStyle(), PANEL_TEXT_SIZE);
//				for (int i = 0; i < games.length; i++) {
//					gamePanel.add(box0);
//					JLabel tmp = new JLabel(String.valueOf(games[i].getId()));
//					tmp.setFont(labelFont);
//					gamePanel.add(tmp);
//
//					gamePanel.add(box0);
//					tmp = new JLabel(games[i].getTitle());
//					tmp.setFont(labelFont);
//					gamePanel.add(tmp);
//
//					gamePanel.add(box1);
//					String players = String.valueOf(games[i].getPlayers().size()) + "/4 : ";
//					for (int j = 0; j < games[i].getPlayers().size(); j++) {
//						players = players + games[i].getPlayers().get(j).getName() + " ";
//					}
//					tmp = new JLabel(players);
//					tmp.setFont(labelFont);
//					gamePanel.add(tmp);
//
//					gamePanel.add(box1);
//					JButton joinButton;
//					if (games[i].getPlayers().size() < 4) {
//						if (games[i].getPlayers().contains(localPlayer)) {
//							joinButton = new JButton("Re-Join");
//						} else {
//							joinButton = new JButton("Join");
//						}
//					} else {
//						joinButton = new JButton("Full");
//						joinButton.setEnabled(false);
//					}
//					gamePanel.add(joinButton);
//					gamePanel.add(box1);
//				}
//			}
//	}

	private ActionListener actionListener = new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == createButton)
			{
				getController().startCreateNewGame();
			}
			else if (e.getSource() == joinButton)
			{
				System.out.println("1");
				getController().startJoinGame(null);
			}
			else
			{
				try
				{
					//System.out.println(e.getActionCommand());
					int gameId = Integer.parseInt(e.getActionCommand());
					GameInfo game = null;
					for (GameInfo g : games)
					{
						if (g.getId() == gameId)
						{
							game = g;
							break;
						}
					}
					getController().startJoinGame(game);
				}
				catch (NumberFormatException ex)
				{
					ex.printStackTrace();
				}
			}
		}
	};

	@Override
	public IJoinGameController getController()
	{

		return (IJoinGameController) super.getController();
	}

	@Override
	public void setGames(List<GameInfo> games, PlayerInfo localPlayer)
	{
		this.games = games;
		this.localPlayer = localPlayer;
		this.removeAll();
		this.initialize();
	}

}
