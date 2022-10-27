package com.widgettest.pak;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JTable;

public class DataWindow extends JFrame {
	
	String identity;
	
	Container container;
	
	JPanel northPanel;	//上边主面板
	JPanel centerPanel;	//中间主面板
	JPanel southPanel;	//下边主面板
	JButton btn_add;	//增
	JButton btn_del;	//删
	JButton btn_mod;	//改
	JButton btn_find;	//查
	JButton btn_back;	//查
	JButton btn_pre;	//上一页
	JButton btn_next;	//下一页
	JTextField	text_input;
	
	JTable table;
	DefaultTableModel tableModel;
	Vector<String> tableTitleVector;		//存放表格标题文本
	Vector<Vector<Object>> dataVector;	//学生数据
	JScrollPane scrollPane;
	
	final String COMMAND_ADD 	= "Add";
	final String COMMAND_DEL 	= "Del";
	final String COMMAND_MOD 	= "Mod";
	final String COMMAND_FIND 	= "Find";
	final String COMMAND_BACK	= "Back";
	final String COMMAND_PRE	= "Pre";
	final String COMMAND_NEXT	= "Next";
	
	final static String FILE_PATH = "D:/Haon/student_information.txt";
	
	
	
	public DataWindow(String identity) {
		//this.setResizable(false);
		this.identity = identity.equals("Teacher")?"教师":"学生";
		init();
	}
	
	public void init() {
		
		String ti = "学生数据管理页,您的身份是：";
		this.setTitle(ti + identity);
		
		//根据屏幕大小设置数据界面大小
		this.setBounds(DimensionUtil.getBounds());
		//设置窗体完全充满整个屏幕可见大小
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//关闭窗口结束进程
		this.setDefaultCloseOperation(LoginWindow.EXIT_ON_CLOSE);
		
		//获得数据页面主面板
		container = this.getContentPane();
		
		northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));	//流布局
		centerPanel = new JPanel(new BorderLayout());				//边界布局
		southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));									//南边面板
		
		//JButton
		btn_add = new JButton("增加");
		btn_add.setFont(new Font("宋体", Font.PLAIN, 20));
		btn_add.setActionCommand(COMMAND_ADD);
		btn_del = new JButton("删除");
		btn_del.setFont(new Font("宋体", Font.PLAIN, 20));
		btn_del.setActionCommand(COMMAND_DEL);
		btn_mod = new JButton("修改");
		btn_mod.setFont(new Font("宋体", Font.PLAIN, 20));
		btn_mod.setActionCommand(COMMAND_MOD);
		btn_find = new JButton("查询");
		btn_find.setFont(new Font("宋体", Font.PLAIN, 20));
		btn_find.setActionCommand(COMMAND_FIND);
		btn_back = new JButton("返回");
		btn_back.setFont(new Font("宋体", Font.PLAIN, 20));
		btn_back.setActionCommand(COMMAND_BACK);
		
		btn_pre  = new JButton("上一页");
		btn_pre.setFont(new Font("宋体", Font.PLAIN, 20));
		btn_pre.setActionCommand(COMMAND_PRE);
		btn_next = new JButton("下一页");
		btn_next.setFont(new Font("宋体", Font.PLAIN, 20));
		btn_next.setActionCommand(COMMAND_NEXT);
		
		//把按钮注册到事件监听器
		MyActionListener listener = new MyActionListener();
		btn_add.addActionListener(listener);
		btn_del.addActionListener(listener);
		btn_mod.addActionListener(listener);
		btn_find.addActionListener(listener);
		btn_back.addActionListener(listener);
		btn_pre.addActionListener(listener);
		btn_next.addActionListener(listener);
		
		// 表头（列名）
        String[] columnNames = {"序号", "姓名", "学号", "语文", "数学", "英语", "总分"};
		tableTitleVector = new Vector<>();
		tableTitleVector.addElement(columnNames[0]);
		tableTitleVector.addElement(columnNames[1]);
		tableTitleVector.addElement(columnNames[2]);
		tableTitleVector.addElement(columnNames[3]);
		tableTitleVector.addElement(columnNames[4]);
		tableTitleVector.addElement(columnNames[5]);
		tableTitleVector.addElement(columnNames[6]);
        
        //创建学生数据容器
        dataVector = new Vector<>();
        //从文件读取学生数据到vector中
        if(isFileExist(FILE_PATH))
        	readFile(FILE_PATH);
        
        tableModel = new DefaultTableModel();
        tableModel.setDataVector(dataVector,tableTitleVector);
        // 创建一个表格，指定 表头 和 所有行数据
        table = new JTable(tableModel);
        

        // 设置表格内容颜色
        table.setForeground(Color.BLACK);                   // 字体颜色
        table.setFont(new Font(null, Font.PLAIN, 14));      // 字体样式
        table.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        table.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        table.setGridColor(Color.BLACK);                     // 网格颜色
        //设置文字居中
        DefaultTableCellRenderer dc=new DefaultTableCellRenderer();
        dc.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, dc);


        // 设置表头
        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // 设置表头名称字体样式
        table.getTableHeader().setForeground(Color.RED);                // 设置表头名称字体颜色
        table.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        table.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列

        // 设置行高
        table.setRowHeight(30);

        // 第一列列宽设置为40
        table.getColumnModel().getColumn(0).setPreferredWidth(40);

        // 设置滚动面板视口大小（超过该大小的行数据，需要拖动滚动条才能看到）
        table.setPreferredScrollableViewportSize(new Dimension(400, 300));

        // 把 表格 放到 滚动面板 中（表头将自动添加到滚动面板顶部）
        scrollPane = new JScrollPane(table);

        // 添加 滚动面板 到 内容面板
        centerPanel.add(scrollPane);
        container.add(centerPanel,BorderLayout.CENTER);

        //设置文本框
        text_input = new JTextField(16);
        text_input.setPreferredSize(new Dimension(0,35));
		//添加组件到北边的组件中
        northPanel.add(btn_add);
        northPanel.add(btn_del);
        northPanel.add(btn_mod);
        northPanel.add(text_input);
        northPanel.add(btn_find);
        container.add(northPanel,BorderLayout.NORTH);
        
        southPanel.add(btn_pre);
        southPanel.add(btn_next);
        southPanel.add(btn_back);
        container.add(southPanel,BorderLayout.SOUTH);
        
	
		//writeFile("D:/Haon/1.txt");
		//主窗体可视化
		this.setVisible(true);
	}
	
	//检查文件是否存在
	public boolean isFileExist(String strFile) {
		
		File file = new File(strFile);
		return file.exists();
	}
	
	//从文件中读取学生数据
	public void readFile(String strFile){
        try {
            File file = new File(strFile);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String strLine = null;
            int lineCount = 1;
            while(null != (strLine = bufferedReader.readLine())){
                System.out.println("第[" + lineCount + "]行数据:[" + strLine + "]");
                String[] ite = strLine.split("    ");
                //System.out.println(ite.length);
                //StudentData data = new StudentData(ite[0],ite[1],ite[2],ite[3],ite[4]);
                Vector<Object> data = new Vector<>();
                data.addElement(ite[0]);
                data.addElement(ite[1]);
                data.addElement(ite[2]);
                data.addElement(ite[3]);
                data.addElement(ite[4]);
                data.addElement(ite[5]);
                data.addElement(ite[6]);
                dataVector.addElement(data);
                lineCount++;
            }
            //关闭句柄
            bufferedReader.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
	
	//把list中的数据写入文件中
	public void writeFile(String strFile) {
        File file = new File(strFile);
        FileWriter fileWriter = null;
	    try {
        	fileWriter = new FileWriter(file);
        	fileWriter.write("");	//清空文件内容
        	System.out.println("有 " + dataVector.size() + " 项数据");
        	Iterator<Vector<Object>> it =  dataVector.iterator();
        	int i=1;
        	while(it.hasNext()) {
        		Vector<Object> data = it.next();
        		//System.out.println((Object)it.next());
        		System.out.println("data.size = " + data.size());
        		String s = data.elementAt(0).toString() + "    " 
        					+ data.elementAt(1) + "    " + data.elementAt(2) + "    "
        					+ data.elementAt(3) + "    " + data.elementAt(4) + "    "
        					+ data.elementAt(5) + "    " + data.elementAt(6) + "\n";
        		System.out.println("写入[" + i + "]" + " " + s);
        		fileWriter.write(s);
        		fileWriter.flush();
        		i++;
        	}
        	
        	//关闭写文件句柄
        	fileWriter.close();

	    }catch(IOException e) {
	    	e.printStackTrace();
	    }
	}

	//返回登录界面
	public void back() {
		LoginWindow loginwin = new LoginWindow();
		this.dispose();
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
				back();
			}else if(COMMAND_PRE.equals(command)) {
				System.out.println("上一页");
			//返回按钮被点击
			}else if(COMMAND_NEXT.equals(command)) {
				System.out.println("下一页");
			}
			
		}
		
	}
}
