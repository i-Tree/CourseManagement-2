package com.javen.service;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

import com.javen.db.DBhepler;
import com.javen.entity.StuEntity;


public class StuService {

    public static List<StuEntity> getAllFromDb(){
        List<StuEntity> list=new ArrayList<StuEntity>();
        try {
            DBhepler db=new DBhepler();
            String sql="select * from course";
            ResultSet rs= db.getAllinformation(sql, null);
            while (rs.next()) {
            	String grade=rs.getString("grade");
            	String majar=rs.getString("majar");
                String majarnum=rs.getString("majarnum");
                String coursename=rs.getString("coursename");
                String coursetype=rs.getString("coursetype");
                String credit=rs.getString("credit");
                String classhour=rs.getString("classhour");
                String labhour=rs.getString("labhour");
                String computerhour=rs.getString("computerhour");
                String date=rs.getString("date");
                String teacher=rs.getString("teacher");
                String notice=rs.getString("notice");

                
                list.add(new StuEntity(grade,majar,majarnum,coursename,coursetype,credit,classhour,labhour,computerhour,date,teacher,notice));
            }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
    
    public static List<StuEntity> getAllFromExcel(String file){
        List<StuEntity> list=new ArrayList<StuEntity>();
        try {
        	WorkbookSettings set=new WorkbookSettings();
        	set.setEncoding("UTF-8");
            Workbook rwb=Workbook.getWorkbook(new File(file));
            Sheet rs=rwb.getSheet(0);
            int clos=rs.getColumns();
            int rows=rs.getRows();
            
            System.out.println(clos+" rows:"+rows);
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    String grade=rs.getCell(j++, i).getContents();//Ĭ������߱��Ҳ��һ�� ���������j++
                    String majar=rs.getCell(j++, i).getContents();
                    String majarnum=rs.getCell(j++, i).getContents();
                    String coursename=rs.getCell(j++, i).getContents();
                    String coursetype=rs.getCell(j++, i).getContents();
                    String credit=rs.getCell(j++, i).getContents();
                    String classhour=rs.getCell(j++, i).getContents();
                    String labhour=rs.getCell(j++, i).getContents();
                    String computerhour=rs.getCell(j++, i).getContents();
                    String date=rs.getCell(j++, i).getContents();
                    String teacher=rs.getCell(j++, i).getContents();
                    String notice=rs.getCell(j++, i).getContents();
                    
                    System.out.println("grade=" +grade +", majar=" + majar + ", majarnum=" + majarnum +", coursename="+ coursename +",coursetype"+ coursetype +",credit="+credit+",classhour=" + classhour + ",labhour=" + labhour + ",computerhour=" + computerhour + ",date=" + date + ",teacher=" + teacher + ",notice=" + notice);
                    list.add(new StuEntity(grade,majar,majarnum,coursename,coursetype,credit,classhour,labhour,computerhour,date,teacher,notice));
                }
            } 
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        return list;
        
    }
    
}