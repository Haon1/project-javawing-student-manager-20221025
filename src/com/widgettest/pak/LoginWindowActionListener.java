package com.widgettest.pak;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindowActionListener implements ActionListener {
	
	private LoginWindow loginWindow;
	
	public LoginWindowActionListener(LoginWindow loginWindow) {
		this.loginWindow = loginWindow;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();	//获取命令得到对应的按钮
		
		//登录按钮被点击
		if(loginWindow.COMMAND_LOGIN.equals(command)) {
			System.out.println("登录");
			//检查账号密码是否正确
//			boolean isRight = loginWindow.checkAccount();
//			if(isRight) {
//				//确认登录身份
//				if(loginWindow.sel_student.isSelected()) {
//					loginWindow.identity = loginWindow.STUDENT;
//				}else {
//					loginWindow.identity = loginWindow.TEACHER;
//				}
//				//去数据窗体
//				loginWindow.login(loginWindow.identity);
//			}
			loginWindow.login("学生");
		//重置按钮被点击
		}else if(loginWindow.COMMAND_RESET.equals(command)) {
			System.out.println("重置");
			loginWindow.reset();
		//退出按钮被点击
		}else if(loginWindow.COMMAND_EXIT.equals(command)) {
			System.out.println("退出");
			loginWindow.exit();
		}
		
	}

}
