package client.catan;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

import client.discard.*;
import client.misc.*;
import client.roll.*;


@SuppressWarnings("serial")
public class CatanPanel extends JPanel {
	
	private TitlePanel titlePanel;
	private LeftPanel leftPanel;
	private MidPanel midPanel;
	private RightPanel rightPanel;
	
	private DiscardView discardView;
	private WaitView discardWaitView;
	private DiscardController discardController;
	
	private RollView rollView;
	private RollResultView rollResultView;
	private RollController rollController;

	public CatanPanel() {
		
		this.setLayout(new BorderLayout());
		
		titlePanel = new TitlePanel();
		midPanel  = new MidPanel();
		leftPanel = new LeftPanel(titlePanel, midPanel.getGameStatePanel());
		rightPanel = new RightPanel(midPanel.getMapController());
		
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(leftPanel, BorderLayout.WEST);
		this.add(midPanel, BorderLayout.CENTER);
		this.add(rightPanel, BorderLayout.EAST);
		
		discardView = new DiscardView();
		discardWaitView = new WaitView();
		discardWaitView.setMessage("Waiting for other Players to Discard");
		discardController = new DiscardController(discardView, discardWaitView);
		discardView.setController(discardController);
		discardWaitView.setController(discardController);
		
		rollView = new RollView();
		rollResultView = new RollResultView();
		rollController = new RollController(rollView, rollResultView);
		rollView.setController(rollController);
		rollResultView.setController(rollController);
		
		JButton testButton = new JButton("Test");
		testButton.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				new client.points.GameFinishedView().showModal();
			}

//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				rollView.showModal();
//			}

//			@Override
//			public void actionPerformed(java.awt.event.ActionEvent e) {
//
//				midPanel.getMapController().startMove(PieceType.ROBBER, false, false);
//			}
			
//			int state = 0;	
//			@Override
//			public void actionPerformed(java.awt.event.ActionEvent e) {
//				if (state == 0) {
//					discardView.showModal();
//					state = 1;
//				}
//				else if (state == 1) {
//					discardWaitView.showModal();
//					state = 2;
//				}
//			}	
			
		});
//		this.add(testButton, BorderLayout.SOUTH);
	}
	
}

