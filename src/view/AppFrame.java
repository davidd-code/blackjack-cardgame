package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.interfaces.GameEngine;

/**
 * Main frame of the whole application. This is the container that holds all of the components that will make up
 * the GUI for this application.
 * 
 * @author David Doan
 *
 */
public class AppFrame extends JFrame
{
	
	public AppFrame(GameEngine engine)
	{
		super("Black Jack Game");
		setLayout(new BorderLayout());
		
		setSize(new Dimension(800, 600));
		
		// Set the start position
		setLocationRelativeTo(null);
		
		setResizable(false);
		// Set default close operation
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		createView(engine);
	}

	/**
	 * This function adds the GameEngineCallbackGUI to the gameEngine. It will also instantiate the GUI objects
	 * and add them to the frame.
	 * 
	 * @param engine
	 */
	public void createView(GameEngine engine)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				// Summary panel on the right of the frame
				SummaryText summary = new SummaryText(engine);
				getContentPane().add(summary, BorderLayout.LINE_END);
				
				// Status bar which will be at the bottom of the frame
				StatusBarPanel status = new StatusBarPanel();
				getContentPane().add(status, BorderLayout.PAGE_END);

				// Main panel which will show the player and dealer panel
				GamePanel main = new GamePanel(engine);
				getContentPane().add(main,  BorderLayout.CENTER);	
				
				// Top panel which will act as container for the drop down menu and the toolbar
				JPanel topPanel = new JPanel(new GridLayout(1,2));
				
				// Drop down menu which will allow the user to select which player
				DropDownMenu menu = new DropDownMenu(engine, status, main);
				topPanel.add(menu);
				
				// Tool bar which will allow the user to add a player, place a bet and deal cards
				ToolBar bar = new ToolBar(engine, summary, menu, main);
				topPanel.add(bar);
				getContentPane().add(topPanel, BorderLayout.PAGE_START);
				
				engine.addGameEngineCallback(new GameEngineCallbackGUI(summary, status, menu, main));				
			}
		});
	}
	
}