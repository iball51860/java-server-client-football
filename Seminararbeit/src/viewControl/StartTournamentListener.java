package viewControl;

import java.awt.event.*;

import view.*;


public class StartTournamentListener implements ActionListener 
{
	
	private ServerWindow serverWindow;
	
	
	public StartTournamentListener(ServerWindow serverWindow)
	{
		super();
		this.serverWindow = serverWindow;
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		serverWindow.setEnabled(false);
		
		PopupDialogShots p = new PopupDialogShots(serverWindow);
		p.setSize(200, 100);
		p.setLocation(serverWindow.getLocationOnScreen().x + 100, serverWindow.getLocationOnScreen().y + 22); //TODO center popoup independently of ServerWindow size
		p.setVisible(true);
		
	}
}
