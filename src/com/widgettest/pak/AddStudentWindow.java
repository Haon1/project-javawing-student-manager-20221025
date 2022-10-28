package com.widgettest.pak;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddStudentWindow extends JDialog {
	
	Container container;
	JPanel panel;
	JLabel lb_name;
	JLabel lb_id;
	JLabel lb_cn;
	JLabel lb_math;
	JLabel lb_eng;
	JLabel lb_all;
	JTextField text_name;
	JTextField text_id;
	JTextField text_cn;
	JTextField text_math;
	JTextField text_eng;
	JTextField text_all;
	JButton btn_add;
	
	final String COMMOND_ADD = "Add";
	
	
	public AddStudentWindow(DataWindow dataWindow) {
		super(dataWindow,"添加学生",true);
		setSize(400,450);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		
		uiInit();
		
		
		setVisible(true);
	}
	
	
	void  uiInit() {
		container = this.getContentPane();
		
		panel = new JPanel(new FlowLayout(FlowLayout.CENTER,12,20));
		
		lb_name = new JLabel("姓名:",JLabel.RIGHT);
		lb_name.setPreferredSize(new Dimension(100,30));
		lb_name.setFont(new Font("宋体", Font.PLAIN, 20));
		
		lb_id = new JLabel("学号:",JLabel.RIGHT);
		lb_id.setPreferredSize(new Dimension(100,30));
		lb_id.setFont(new Font("宋体", Font.PLAIN, 20));
		
		lb_cn = new JLabel("语文成绩:",JLabel.RIGHT);
		lb_cn.setPreferredSize(new Dimension(100,30));
		lb_cn.setFont(new Font("宋体", Font.PLAIN, 20));
		
		lb_math = new JLabel("数学成绩:",JLabel.RIGHT);
		lb_math.setPreferredSize(new Dimension(100,30));
		lb_math.setFont(new Font("宋体", Font.PLAIN, 20));
		
		lb_eng = new JLabel("英语成绩:",JLabel.RIGHT);
		lb_eng.setPreferredSize(new Dimension(100,30));
		lb_eng.setFont(new Font("宋体", Font.PLAIN, 20));
		
		lb_all = new JLabel("总分:",JLabel.RIGHT);
		lb_all.setPreferredSize(new Dimension(100,30));
		lb_all.setFont(new Font("宋体", Font.PLAIN, 20));
		
		text_name = new JTextField();
		text_name.setPreferredSize(new Dimension(200,30));
		text_name.setFont(new Font("宋体", Font.PLAIN, 20));
		
		text_id = new JTextField();
		text_id.setPreferredSize(new Dimension(200,30));
		text_id.setFont(new Font("宋体", Font.PLAIN, 20));
		
		text_cn = new JTextField();
		text_cn.setPreferredSize(new Dimension(200,30));
		text_cn.setFont(new Font("宋体", Font.PLAIN, 20));
		
		text_math = new JTextField();
		text_math.setPreferredSize(new Dimension(200,30));
		text_math.setFont(new Font("宋体", Font.PLAIN, 20));
		
		text_eng = new JTextField();
		text_eng.setPreferredSize(new Dimension(200,30));
		text_eng.setFont(new Font("宋体", Font.PLAIN, 20));
		
		text_all = new JTextField();
		text_all.setPreferredSize(new Dimension(200,30));
		text_all.setFont(new Font("宋体", Font.PLAIN, 20));

		
		btn_add = new JButton("添加");
		btn_add.setFont(new Font("宋体", Font.PLAIN, 20));
		btn_add.setActionCommand(COMMOND_ADD);
		AddStudentWindowActionListener listener = new AddStudentWindowActionListener(this);
		btn_add.addActionListener(listener);
		
		
		panel.add(lb_name);
		panel.add(text_name);
		panel.add(lb_id);
		panel.add(text_id);
		panel.add(lb_cn);
		panel.add(text_cn);
		panel.add(lb_math);
		panel.add(text_math);
		panel.add(lb_eng);
		panel.add(text_eng);
		panel.add(lb_all);
		panel.add(text_all);
		
		panel.add(btn_add);
		
		container.add(panel);
	}

}
