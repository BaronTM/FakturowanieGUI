package Fakturowanie;

import java.awt.AWTEvent;

public class CheckerEvent extends AWTEvent {
	
	private static final int CHECKER_EVENT = AWTEvent.RESERVED_ID_MAX + 184;
	
	public CheckerEvent(Object source) {
		super(source, CHECKER_EVENT);
	}
}
