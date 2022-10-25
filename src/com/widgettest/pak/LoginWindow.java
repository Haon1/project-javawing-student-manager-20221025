package com.widgettest.pak;

import java.awt.Color;
import java.awt.Container;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame {
	
	JPanel panel;
	JLabel lb_usr;
	JLabel lb_pwd;
	JLabel lb_mode;
	
	JButton btn_login;
	JButton btn_reset;
	JButton btn_exit;
	
	JTextField text_usr;
	JTextField text_pwd;
	
	JRadioButton sel_teacher;
	JRadioButton sel_student;
	ButtonGroup  btnGroup;
	
	String identity;
	
	final String COMMAND_LOGIN 	= "Login";
	final String COMMAND_RESET 	= "Reset";
	final String COMMAND_EXIT 	= "Exit";
	final String USERNAME 		= "admin";
	final String PASSWORD		= "admin";
	final String TEACHER		= "Teacher";
	final String STUDENT		= "Student";
	
	//构造方法
	LoginWindow(String title){
		super(title);
		setResizable(false);
		init();
	}
	
	//组件初始化
	public void init() {
		this.setBounds(300, 300, 480, 400);
		this.setDefaultCloseOperation(LoginWindow.EXIT_ON_CLOSE);
		
		
		panel = new JPanel();
		panel.setLayout(null);		//使用绝对布局 ***否则添加文本框之后组件全消失
		this.setContentPane(panel);
		
		//JLabel
		lb_usr = new JLabel("用户名:");
		lb_usr.setFont(new  java.awt.Font("宋体",0,20));
		lb_usr.setBounds(80, 50, 80, 30);
		lb_pwd = new JLabel("密  码:");
		lb_pwd.setFont(new  java.awt.Font("宋体",0,20));
		lb_pwd.setBounds(80, 116, 80, 30);
		lb_mode = new JLabel("权  限:");
		lb_mode.setFont(new  java.awt.Font("宋体",0,20));
		lb_mode.setBounds(80, 177, 80, 30);
		
		//文本输入框
		text_usr = new JTextField(11);
		text_usr.setFont(new Font("宋体", Font.PLAIN, 20));
		text_usr.setBounds(160, 50, 181, 30);
		text_pwd = new JTextField(16);
		text_pwd.setFont(new Font("宋体", Font.PLAIN, 20));
		text_pwd.setBounds(160, 116, 181, 30);
		
		//JButton
		btn_login = new JButton("登录");
		btn_login.setFont(new Font("宋体", Font.PLAIN, 20));
		btn_login.setBounds(64, 247,102, 30);
		btn_login.setActionCommand(COMMAND_LOGIN);
		btn_reset = new JButton("重置");
		btn_reset.setFont(new Font("宋体", Font.PLAIN, 20));
		btn_reset.setBounds(176, 247, 98, 30);
		btn_reset.setActionCommand(COMMAND_RESET);
		btn_exit = new JButton("退出");
		btn_exit.setFont(new Font("宋体", Font.PLAIN, 20));
		btn_exit.setBounds(284, 247, 98, 30);
		btn_exit.setActionCommand(COMMAND_EXIT);
		
		MyActionListener listener = new MyActionListener();
		btn_login.addActionListener(listener);
		btn_reset.addActionListener(listener);
		btn_exit.addActionListener(listener);
		
		//单选按钮
		sel_teacher = new JRadioButton("教师");
		sel_teacher.setFont(new Font("宋体", Font.PLAIN, 20));
		sel_teacher.setBounds(172, 177, 69, 23);
		
		sel_student = new JRadioButton("学生");
		sel_student.setSelected(true);
		sel_student.setFont(new Font("宋体", Font.PLAIN, 20));
		sel_student.setBounds(272, 177, 69, 23);
		
		// 创建按钮组，把两个单选按钮添加到该组
        btnGroup = new ButtonGroup();
        btnGroup.add(sel_teacher);
        btnGroup.add(sel_student);

		
		//添加组件到Pane中
		panel.add(lb_usr);
		panel.add(lb_pwd);
		panel.add(lb_mode);
		panel.add(btn_login);
		panel.add(btn_reset);
		panel.add(btn_exit);
		panel.add(text_usr);
		panel.add(text_pwd);
		panel.add(sel_teacher);
		panel.add(sel_student);
		
		//主窗体可视化
		this.setVisible(true);
	}
	
	//检查账号密码是否正确
	public boolean checkAccount() {
		String usr = text_usr.getText();
		String pwd = text_pwd.getText();
		//账号密码均正确
		if(USERNAME.equals(usr)&&PASSWORD.equals(pwd)) {
			return true;
		}else {
			JOptionPane.showMessageDialog(this,"账号或密码错误","提示",JOptionPane.WARNING_MESSAGE);
			return false;
		}
	}
	//跳转到数据界面
	public void gotoDataWindow(String identity) {
		DataWindow datawin = new DataWindow(identity);
		this.dispose();
	}
	
	//重置登录界面数据
	public void reset() {
		text_usr.setText("");
		text_pwd.setText("");
		sel_student.setSelected(true);
	}
	
	//内部类  事件监听器
	private class MyActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String command = e.getActionCommand();	//获取命令得到对应的按钮
			
			//登录按钮被点击
			if(COMMAND_LOGIN.equals(command)) {
				System.out.println("登录");
				//检查账号密码是否正确
				boolean isRight = checkAccount();
				if(isRight) {
					//确认登录身份
					if(sel_student.isSelected()) {
						identity = STUDENT;
					}else {
						identity = TEACHER;
					}
					//去数据窗体
					gotoDataWindow(identity);
				}
			//重置按钮被点击
			}else if(COMMAND_RESET.equals(command)) {
				System.out.println("重置");
				reset();
			//退出按钮被点击
			}else if(COMMAND_EXIT.equals(command)) {
				System.out.println("退出");
			}
			
		}
		
	} 
}
