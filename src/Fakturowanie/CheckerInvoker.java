package Fakturowanie;

import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JComponent;

public class CheckerInvoker extends JComponent implements Runnable{

	public CheckerInvoker() {
		super();
	}
	
	public void addCheckerListener(CheckerListener listener) {
		listenerList.add(CheckerListener.class, listener);
	}
	
	public void removeCheckerListener(CheckerListener listener) {
		listenerList.remove(CheckerListener.class, listener);
	}
	
	public void processEvent(AWTEvent evt) {
		CheckerListener[] listeners = listenerList.getListeners(CheckerListener.class);
		if (evt instanceof CheckerEvent) {
			for (int i = 0; i < listeners.length; i++) {
				listeners[i].nextMinute((CheckerEvent) evt); 
			}
		} else {
			super.processEvent(evt);
		}
	}
	
	public void run() {
		EventQueue queue = Toolkit.getDefaultToolkit().getSystemEventQueue();
		while (true) {
			try {
				Thread.sleep(60000);
			} catch (InterruptedException ie) {}
			queue.postEvent(new CheckerEvent(this));
		}
	}
}
