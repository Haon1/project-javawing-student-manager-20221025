package com.widgettest.pak;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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
	List<StudentData> m_list;
	
	final String COMMAND_ADD 	= "Add";
	final String COMMAND_DEL 	= "Del";
	final String COMMAND_MOD 	= "Mod";
	final String COMMAND_FIND 	= "Find";
	final String COMMAND_BACK	= "Back";
	
	final static String FILE_PATH = "D:/Haon/student_information.txt";
	
	
	
	public DataWindow(String identity) {
		this.setResizable(false);
		this.identity = identity.equals("Teacher")?"教师":"学生";
		init();
	}
	
	public void init() {
		
		String ti = "学生数据管理页,您的身份是：";
		this.setTitle(ti + identity);
		this.setSize(1000, 700);
		int windowWidth = this.getWidth(); //获得窗口宽
		int windowHeight = this.getHeight(); //获得窗口高
		Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
		Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
		int screenWidth = screenSize.width; //获取屏幕的宽
		int screenHeight = screenSize.height; //获取屏幕的高
		this.setLocation((screenWidth - windowWidth)/2, (screenHeight - windowHeight)/2);//设置窗口居中显示
		
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

		//table = new JTable();
		//table.setBounds(10, 10, 785, 643);
		
        m_list = new ArrayList<StudentData>();
        if(isFileExist(FILE_PATH))
        	readFile(FILE_PATH);
        // 表头（列名）
        String[] columnNames = {"序号", "姓名", "语文", "数学", "英语", "总分"};
        Object [][]rowData =  m_list.toArray();
        // 表格所有行数据
//        Object[][] rowData = {
//                {1, "张三", 80, 80, 80, 240},
//                {2, "John", 70, 80, 90, 240},
//                {3, "Sue", 70, 70, 70, 210},
//                {4, "Jane", 80, 70, 60, 210},
//                {5, "Joe_05", 80, 70, 60, 210},
//                {6, "Joe_06", 80, 70, 60, 210},
//                {7, "Joe_07", 80, 70, 60, 210},
//                {8, "Joe_08", 80, 70, 60, 210},
//                {9, "Joe_09", 80, 70, 60, 210},
//                {10, "Joe_10", 80, 70, 60, 210},
//                {11, "Joe_11", 80, 70, 60, 210},
//                {12, "Joe_12", 80, 70, 60, 210},
//                {13, "Joe_13", 80, 70, 60, 210},
//                {14, "Joe_14", 80, 70, 60, 210},
//                {15, "Joe_15", 80, 70, 60, 210},
//                {16, "Joe_16", 80, 70, 60, 210},
//                {17, "Joe_17", 80, 70, 60, 210},
//                {18, "Joe_18", 80, 70, 60, 210},
//                {19, "Joe_19", 80, 70, 60, 210},
//                {20, "Joe_20", 80, 70, 60, 210},
//                {21, "Joe_21", 80, 70, 60, 210},
//                {22, "Joe_22", 80, 70, 60, 210},
//                {23, "Joe_23", 80, 70, 60, 210},
//                {24, "Joe_24", 80, 70, 60, 210},
//                {25, "Joe_25", 80, 70, 60, 210}
//        };

        // 创建一个表格，指定 表头 和 所有行数据
        table = new JTable(rowData, columnNames);
        

        // 设置表格内容颜色
        table.setForeground(Color.BLACK);                   // 字体颜色
        table.setFont(new Font(null, Font.PLAIN, 14));      // 字体样式
        table.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        table.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        table.setGridColor(Color.GRAY);                     // 网格颜色

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
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 785, 643);

        // 添加 滚动面板 到 内容面板
        panel.add(scrollPane);

		
		//添加组件到Pane中
		panel.add(btn_add);
		panel.add(btn_del);
		panel.add(btn_mod);
		panel.add(btn_find);
		panel.add(btn_back);
		//panel.add(table);
	
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
            //int lineCount = 1;
            while(null != (strLine = bufferedReader.readLine())){
                //System.out.println("第[" + lineCount + "]行数据:[" + strLine + "]");
                String[] ite = strLine.split("    ");
                //System.out.println(ite.length);
                StudentData data = new StudentData(ite[0],ite[1],ite[2],ite[3],ite[4]);
                m_list.add(data);
                //lineCount++;
            }
            //关闭句柄
            bufferedReader.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
	
	//把list中的数据写入文件中
	public void writeFile(String strFile) {
		
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
			}
			
		}
		
	}
}
