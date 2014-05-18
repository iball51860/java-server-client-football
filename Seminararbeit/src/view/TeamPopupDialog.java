package view;

import java.awt.*;

import javax.swing.*;

import control.listeners.*;

import model.*;

@SuppressWarnings("serial")
public class TeamPopupDialog extends JDialog
{
	
	private Container c;
	
	private JLabel name;
	private JLabel ip;
	private JLabel strength;
	private JLabel goals;
	private JLabel victories;
	private JLabel success;
	private JButton close;
	
	
	public TeamPopupDialog(final ServerWindow serverWindow, final Team t)
	{
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				setSize(200, 180);
				setLocation(MouseInfo.getPointerInfo().getLocation());
				c = getContentPane();
				c.setLayout(new GridLayout(0, 1));
				name = new JLabel(t.getName());
				ip = new JLabel(t.getClientSocket().getInetAddress().toString());
				strength = new JLabel("Left: " + t.getStrength()[0]
						+ " | Middle: " + t.getStrength()[1] + " | Right: "
						+ t.getStrength()[2]);
				goals = new JLabel("Goals: " + t.getGoals());
				victories = new JLabel("Victories: " + t.getWonMatches());
				int rate = (int) ((double) t.getGoals() * 100 / (double) t
						.getFinishedShots());
				success = new JLabel("Success Rate: " + rate + " %");
				close = new JButton("close");
				close.addActionListener(new ClosePopupListener(serverWindow,
						TeamPopupDialog.this));
				close.addKeyListener(new ClosePopupListener(serverWindow,
						TeamPopupDialog.this));
				c.add(name);
				c.add(ip);
				c.add(strength);
				c.add(goals);
				c.add(victories);
				c.add(success);
				c.add(close);
				
				dispose();
				setUndecorated(true);
				setVisible(true);
			}
		});
		
	}
	
	
}
