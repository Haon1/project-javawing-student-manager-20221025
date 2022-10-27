package com.widgettest.pak;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class DimensionUtil {
	
	public static Rectangle getBounds() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Insets screenInset = Toolkit.getDefaultToolkit().getScreenInsets(new JFrame().getGraphicsConfiguration());
		
		Rectangle rectangle = new Rectangle(screenInset.left,screenInset.top,
				          screenSize.width - screenInset.left - screenInset.right,
				          screenSize.height - screenInset.top - screenInset.bottom);
		
		return rectangle;
	}

}
