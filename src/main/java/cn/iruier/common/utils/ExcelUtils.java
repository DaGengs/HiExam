package cn.iruier.common.utils;

import cn.iruier.entity.Question;
import cn.iruier.entity.Student;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ExcelUtils {

	//批量导入试题
	public static List<Question> parseExcel(InputStream is, String user_no, int subj_id, int status) {
		List<Question> questions = new ArrayList<>();
		// 创建单元
		HSSFWorkbook workbook;
		try {
            workbook = new HSSFWorkbook(is);
			HSSFSheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            System.out.println(lastRowNum);
            for (int i = 1; i <= lastRowNum; i++) {
                HSSFRow row = sheet.getRow(i);
                Question question = new Question();
                question.setQue_title(row.getCell(0).getStringCellValue());
                question.setQue_AnswerA(row.getCell(1).getStringCellValue());
                question.setQue_AnswerB(row.getCell(2).getStringCellValue());
                question.setQue_AnswerC(row.getCell(3).getStringCellValue());
                question.setQue_AnswerD(row.getCell(4).getStringCellValue());
                question.setQue_AnswerE(row.getCell(5).getStringCellValue());
                question.setQue_AnswerF(row.getCell(6).getStringCellValue());
                question.setQue_type(row.getCell(7).getStringCellValue());
                question.setQue_RightAnswer(row.getCell(8).getStringCellValue());
                question.setQue_level(row.getCell(9).getStringCellValue());
                question.setCreateTime(new Date());
                question.setSubj_id(subj_id);
                question.setUser_no(user_no);
                question.setStatus(status);
                questions.add(question);
			}
            workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return questions;
	}
	
	//解析学员
	public static List<Student> parseExcelS(InputStream is, int class_no, int status) {
		List<Student> students = new ArrayList<>();
		// 创建单元
		HSSFWorkbook workbook;
		try {
            workbook = new HSSFWorkbook(is);
			HSSFSheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {
                HSSFRow row = sheet.getRow(i);
                Student student=new Student();
				student.setStu_no(new Double(row.getCell(0).getNumericCellValue()).longValue() + "");
				student.setStu_idNumber(row.getCell(1).getStringCellValue());
				student.setStu_name(row.getCell(2).getStringCellValue());
				student.setStu_gender(row.getCell(3).getStringCellValue());
				student.setStu_phone(new Double(row.getCell(4).getNumericCellValue()).longValue() + "");
				student.setClass_no(class_no);
				student.setStatus(status);
				students.add(student);
			}
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return students;
	}
}
