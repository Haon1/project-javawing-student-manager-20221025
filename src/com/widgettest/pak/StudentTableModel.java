package com.widgettest.pak;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class StudentTableModel extends DefaultTableModel {
	// 表头（列名）
    static String[] columnNames = {"序号", "姓名", "学号", "语文", "数学", "英语", "总分"};
	static  Vector<String> tableTitleVector = new Vector<>();
	static{
		tableTitleVector.addElement(columnNames[0]);
		tableTitleVector.addElement(columnNames[1]);
		tableTitleVector.addElement(columnNames[2]);
		tableTitleVector.addElement(columnNames[3]);
		tableTitleVector.addElement(columnNames[4]);
		tableTitleVector.addElement(columnNames[5]);
		tableTitleVector.addElement(columnNames[6]);
	}
	
	//构造
	private StudentTableModel() {
		super(null,tableTitleVector);
	}
	
	private static StudentTableModel studentTableModel = new StudentTableModel();
	
	public static StudentTableModel assembleModel(Vector<Vector<Object>> data) {
		studentTableModel.setDataVector(data,tableTitleVector);
		return studentTableModel;
	}
	
	@Override
	//重写该方法，使得单元格不可直接编辑
	public boolean isCellEditable(int row, int column) {
		// TODO Auto-generated method stub
		//return super.isCellEditable(row, column);
		return false;
	}
	
}
