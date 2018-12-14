package Fakturowanie;

import java.util.EventListener;

public interface CheckerListener extends EventListener{
	public abstract void nextMinute(CheckerEvent e);
}
