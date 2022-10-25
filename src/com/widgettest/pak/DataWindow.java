package com.widgettest.pak;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTable;

public class DataWindow extends JFrame {
	
	String identity;
	
	JPanel panel;		//主面板
	JButton btn_add;	//增
	JButton btn_del;	//删
	JButton btn_mod;	//改
	JButton btn_find;	//查
	JButton btn_back;	//查
	
	private JTable table;
	
	final String COMMAND_ADD 	= "Add";
	final String COMMAND_DEL 	= "Del";
	final String COMMAND_MOD 	= "Mod";
	final String COMMAND_FIND 	= "Find";
	final String COMMAND_BACK	= "Back";
	
	
	
	public DataWindow(String identity) {
		setResizable(false);
		this.identity = identity.equals("Teacher")?"教师":"学生";
		init();
	}
	
	public void init() {
		String ti = "学生数据管理页,您的身份是：";
		this.setTitle(ti+identity);
		this.setBounds(250, 85, 1000, 700);
		this.setDefaultCloseOperation(LoginWindow.EXIT_ON_CLOSE);
		
		
		panel = new JPanel();
		panel.setLayout(null);		//使用绝对布局 ***否则添加文本框之后组件全消失
		this.setContentPane(panel);
		
		//JButton
		btn_add = new JButton("增加");
		btn_add.setFont(new Font("宋体", Font.PLAIN, 20));
		btn_add.setBounds(838, 84,102, 44);
		btn_add.setActionCommand(COMMAND_ADD);
		btn_del = new JButton("删除");
		btn_del.setFont(new Font("宋体", Font.PLAIN, 20));
		btn_del.setBounds(838, 193,102, 44);
		btn_del.setActionCommand(COMMAND_DEL);
		btn_mod = new JButton("修改");
		btn_mod.setFont(new Font("宋体", Font.PLAIN, 20));
		btn_mod.setBounds(838, 301,102, 44);
		btn_mod.setActionCommand(COMMAND_MOD);
		btn_find = new JButton("查询");
		btn_find.setFont(new Font("宋体", Font.PLAIN, 20));
		btn_find.setBounds(838, 423,102, 44);
		btn_find.setActionCommand(COMMAND_FIND);
		btn_back = new JButton("返回");
		btn_back.setFont(new Font("宋体", Font.PLAIN, 20));
		btn_back.setBounds(838, 553,102, 44);
		btn_back.setActionCommand(COMMAND_BACK);
		
		//把按钮注册到事件监听器
		MyActionListener listener = new MyActionListener();
		btn_add.addActionListener(listener);
		btn_del.addActionListener(listener);
		btn_mod.addActionListener(listener);
		btn_find.addActionListener(listener);
		btn_back.addActionListener(listener);
		
//		//文本输入框
//		text_usr = new JTextField(11);
//		text_usr.setFont(new Font("宋体", Font.PLAIN, 20));
//		text_usr.setBounds(160, 50, 181, 30);
//		text_pwd = new JTextField(16);
//		text_pwd.setFont(new Font("宋体", Font.PLAIN, 20));
//		text_pwd.setBounds(160, 116, 181, 30);

		
		
		
//		//单选按钮
//		sel_teacher = new JRadioButton("教师");
//		sel_teacher.setFont(new Font("宋体", Font.PLAIN, 20));
//		sel_teacher.setBounds(172, 177, 69, 23);
//		
//		sel_student = new JRadioButton("学生");
//		sel_student.setSelected(true);
//		sel_student.setFont(new Font("宋体", Font.PLAIN, 20));
//		sel_student.setBounds(272, 177, 69, 23);
//		
//		// 创建按钮组，把两个单选按钮添加到该组
//        btnGroup = new ButtonGroup();
//        btnGroup.add(sel_teacher);
//        btnGroup.add(sel_student);

		
		//添加组件到Pane中
		panel.add(btn_add);
		panel.add(btn_del);
		panel.add(btn_mod);
		panel.add(btn_find);
		panel.add(btn_back);
		
		table = new JTable();
		table.setBounds(10, 10, 785, 643);
		panel.add(table);
		
		
		//主窗体可视化
		this.setVisible(true);
	}
	
	//内部类  事件监听器
	private class MyActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String command = e.getActionCommand();	//获取命令得到对应的按钮
			
			//增加按钮被点击
			if(COMMAND_ADD.equals(command)) {
				System.out.println("增加");
			//删除按钮被点击
			}else if(COMMAND_DEL.equals(command)) {
				System.out.println("删除");
			//修改按钮被点击
			}else if(COMMAND_MOD.equals(command)) {
				System.out.println("修改");
			//查询按钮被点击
			}else if(COMMAND_FIND.equals(command)) {
				System.out.println("查询");
			//返回按钮被点击
			}else if(COMMAND_BACK.equals(command)) {
				System.out.println("返回");
			}
			
		}
		
	}
}
