package status;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;

import network.NetworkGraph;

/**
 * 
 * A panel that shows the status of the network
 * (a text field)
 *
 */

public class StatusPanel{

	private static int height = 100;
	private static JTextArea textArea;

	/**
	 * Creates the status panel
	 * 
	 * @param width - width of the panel in pixels
	 * @return
	 */
	public JScrollPane getPanel(int width){
		//Create text area
		textArea = new JTextArea();
		textArea.setLineWrap(false);
		textArea.setEditable(false);
		textArea.setText("Status panel:");
		textArea.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Right click menu
				if(arg0.getButton() == MouseEvent.BUTTON3 && arg0.getClickCount() == 1) {
					JPopupMenu menu = new JPopupMenu();
					JMenuItem item1 = new JMenuItem("Clear panel");
					item1.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							textArea.setText("Status panel:");
						}
					});
					menu.add(item1);
					
					JMenuItem item2 = new JMenuItem("Clear graph");
					item2.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							NetworkGraph.clearGraph();
						}
					});
					menu.add(item2);
					
					menu.show(arg0.getComponent(), arg0.getX(), arg0.getY());
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {}

			@Override
			public void mouseExited(MouseEvent arg0) {}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}

		});

		//add the text area to a scroll pane
		JScrollPane sbr = new JScrollPane(textArea);
		//set scroll bar policies and size of the panel
		sbr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		sbr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);	
		sbr.setPreferredSize(new Dimension(width, height));
		return sbr;
	}

	/**
	 * Add text to the status panel
	 * @param text - Text to be added on the panel
	 */
	public static void addText(String text){
		String totalText = System.getProperty("line.separator") + text;
		textArea.append(totalText);
	}
}
