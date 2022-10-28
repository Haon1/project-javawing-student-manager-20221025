package com.widgettest.pak;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DelStudentWindow extends JDialog {

	DataWindow dataWindow;
	
	Container container;
	JPanel panel;
	JLabel lb_id;
	JTextField text_id;
	JButton btn_del;
	
	final String COMMOND_DEL = "Del";
	
	
	public DelStudentWindow(DataWindow dataWindow) {
		super(dataWindow,"删除学生",true);
		this.dataWindow = dataWindow;
		setSize(400,300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		
		uiInit();
		
		
		setVisible(true);
	}
	
	public void  uiInit() {
		container = this.getContentPane();
		
		panel = new JPanel(new FlowLayout(FlowLayout.CENTER,12,20));
		
		
		lb_id = new JLabel("输入要删除的学生学号",JLabel.CENTER);
		lb_id.setPreferredSize(new Dimension(300,30));
		lb_id.setFont(new Font("宋体", Font.PLAIN, 20));
		
		
		text_id = new JTextField();
		text_id.setPreferredSize(new Dimension(300,30));
		text_id.setFont(new Font("宋体", Font.PLAIN, 20));

		
		btn_del = new JButton("删除");
		btn_del.setFont(new Font("宋体", Font.PLAIN, 20));
		btn_del.setActionCommand(COMMOND_DEL);
		DelStudentWindowActionListener listener = new DelStudentWindowActionListener(this);
		btn_del.addActionListener(listener);
		
		panel.add(lb_id);
		panel.add(text_id);
		panel.add(btn_del);
		
		container.add(panel);
	}
	
	//点击删除按钮之后执行的方法
	public void btnDelClickHandler() {
		
		//获取输入框数据
		String id = text_id.getText();
		//判断是否为空
		if(id.isEmpty()) {
			JOptionPane.showMessageDialog(this,"请输入学号","删除",JOptionPane.WARNING_MESSAGE);
			return ;
		}
		
		//定义一个Vector容器指向 数据界面的容器
		Vector<Vector<Object>> tmpVector = dataWindow.dataVector;
		//建立迭代器用于遍历数据容器
		Iterator<Vector<Object>> it =  tmpVector.iterator();
		//遍历学生数据
    	while(it.hasNext()) {
    		Vector<Object> data = it.next();
    		//如果data中的第二项和id匹配,也就是存在该学生
    		if(data.elementAt(2).equals(id)) {
    			//从学生数据容器中删除这一项数据
    			tmpVector.removeElement(data);
    			//数据界面重新加载表格
    			dataWindow.reloadTable(tmpVector);
    			//弹框提示
    			JOptionPane.showMessageDialog(this,"删除成功","删除",JOptionPane.WARNING_MESSAGE);
    			//清空文本输入框
    			text_id.setText("");
    			return ;
    		}

    	}
    	//学生数据容器中没有匹配的学号
    	JOptionPane.showMessageDialog(this,"该生不存在","删除",JOptionPane.WARNING_MESSAGE);
	}
}
