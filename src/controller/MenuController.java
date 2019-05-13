package controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.DropDownMenu;
import view.GamePanel;
import view.StatusBarPanel;

/**
 * ItemListener for the drop down menu. When the drop down menu's state has been changed then 
 * this will call the functions required to update the UI accordingly.
 * @author dayvi
 *
 */
public class MenuController implements ItemListener 
{
	private GameEngine engine;
	private DropDownMenu menu;
	private StatusBarPanel status;
	private GamePanel main;
	
	public MenuController(GameEngine engine, DropDownMenu menu, StatusBarPanel status, GamePanel main)
	{
		this.engine = engine;
		this.menu = menu;
		this.status = status;
		this.main = main;				
	}
	
	// Indicate the current player selected in the status bar and display the correct PlayerCardPanel
	@Override
	public void itemStateChanged(ItemEvent event) {
		if(event.getSource() == menu)
		{
			status.displaySelected(menu.getSelectedItem().toString());
			main.displayCorrectPanel((Player) menu.getSelectedItem());
		}
		
	}
	
}
