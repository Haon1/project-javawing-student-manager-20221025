package com.widgettest.pak;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModStudentWindowActionListener implements ActionListener {

	//用于删除窗口的对象,因为需要调用窗口的方法
	ModStudentWindow modStudentWindow;
	
	//构造方法
	public ModStudentWindowActionListener(ModStudentWindow modStudentWindow) {
		this.modStudentWindow = modStudentWindow;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();	//获取命令得到对应的按钮
		if(modStudentWindow.COMMOND_MOD.equals(command)) {
			System.out.println("点击修改");
			//调用删除窗口的btnDelClickHandler方法
			//modStudentWindow.btnModClickHandler();
		}
	}

}
