package client.main;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import client.base.IAction;
import client.catan.CatanPanel;
import client.join.JoinGameController;
import client.join.JoinGameView;
import client.join.NewGameView;
import client.join.PlayerWaitingController;
import client.join.PlayerWaitingView;
import client.join.SelectColorView;
import client.login.LoginController;
import client.login.LoginView;
import client.misc.MessageView;
import client.models.Poller;
import client.models.Proxy;
import client.models.interfaces.IProxy;

/**
 * Main entry point for the Catan program
 */
@SuppressWarnings("serial")
public class Catan extends JFrame
{
	private CatanPanel catanPanel;
	private static IProxy proxy;
	private static Poller poller;

	public Catan()
	{
		client.base.OverlayView.setWindow(this);

		this.setTitle("Settlers of Catan");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		catanPanel = new CatanPanel(proxy);
		this.setContentPane(catanPanel);

		display();
	}

	private void display()
	{
		pack();
		setVisible(true);
	}

	//
	// Main
	//

	public static void main(final String[] args)
	{
		String default_host = "localhost";
		int default_port = 8081;
		
		if(args.length == 2)
		{
			default_host = args[0];
			default_port = Integer.parseInt(args[1]);
		}
		else if(args.length == 1)
		{
			try
			{
				default_port = Integer.parseInt(args[0]);
			}
			catch(NumberFormatException e)
			{
				default_host = args[0];
			}
		}
		
		final String host = default_host;
		final int port = default_port;
		
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

//		System.out.println("Settlement cost:");
//		for(client.models.Resource resource : client.models.Settlement.getResourceCost())
//			System.out.printf("\t%-5s : %-2d\n", resource.getResourceType(), resource.getAmount());
//
//		System.out.println("City cost:");
//		for(client.models.Resource resource : client.models.City.getResourceCost())
//			System.out.printf("\t%-5s : %-2d\n", resource.getResourceType(), resource.getAmount());
//
//		System.out.println("RoadSegment cost:");
//		for(client.models.Resource resource : client.models.RoadSegment.getResourceCost())
//			System.out.printf("\t%-5s : %-2d\n", resource.getResourceType(), resource.getAmount());

		SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				proxy = new Proxy(host, port);

				new Catan();


				PlayerWaitingView playerWaitingView = new PlayerWaitingView();
				final PlayerWaitingController playerWaitingController = new PlayerWaitingController(playerWaitingView,
																									proxy);
				playerWaitingView.setController(playerWaitingController);


				JoinGameView joinView = new JoinGameView();
				NewGameView newGameView = new NewGameView();
				SelectColorView selectColorView = new SelectColorView();
				MessageView joinMessageView = new MessageView();
				final JoinGameController joinController = new JoinGameController(joinView,
																				 newGameView,
																				 selectColorView,
																				 joinMessageView,
																				 proxy);
				joinController.setJoinAction(new IAction() {
					@Override
					public void execute()
					{
						poller = new Poller(proxy);
//						poller = new Thread(new Poller(proxy));
//						System.out.println("after created");
//						poller.start();
//						System.out.println("after start");
//						poller.startTimer();
						playerWaitingController.start();
//						proxy.getGameModel();
					}
				});
				joinView.setController(joinController);
				newGameView.setController(joinController);
				selectColorView.setController(joinController);
				joinMessageView.setController(joinController);

				LoginView loginView = new LoginView();
				MessageView loginMessageView = new MessageView();
				LoginController loginController = new LoginController(loginView,
																	  loginMessageView,
																	  proxy);
				loginController.setLoginAction(new IAction() {
					@Override
					public void execute()
					{
						joinController.start();
					}
				});
				loginView.setController(loginController);
				loginView.setController(loginController);

				loginController.start();
			}
		});
	}

}
