/**   
*    
* 类名称：ExcelUtils   
* 类描述：   
* 创建人：Administrator   
* 创建时间：2014-10-14 下午04:40:03   
* 修改人：Administrator   
* 修改时间：2014-10-14 下午04:40:03   
* 修改备注：   
* @version    
*    
*/
package com.comment.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 *类描述：
 *@author: mars
 *@date： 日期：2014-10-14 时间：下午05:19:49
 *
 *@version 1.0
 */
public class ExcelUtils {
	
		// 对外提供读取excel文件的接口
		public static List<List<Object>> readExcel(File file) throws IOException {
		String fName = file.getName();
		String extension = fName.lastIndexOf(".") == -1 ? "" : fName
		.substring(fName.lastIndexOf(".") + 1);
		if ("xls".equals(extension)) {// 2003
		System.err.println("读取excel2003文件内容");
		return read2003Excel(file);
		} else if ("xlsx".equals(extension)) {// 2007
		System.err.println("读取excel2007文件内容");
		return read2007Excel(file);
		} else {
		throw new IOException("不支持的文件类型:" + extension);
		}
		}

		
		public void getWB() throws IOException{
			File file = new File("c:\223123.xls");
			FileInputStream fileInputStream = new FileInputStream(file);
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(fileInputStream);
			int numberOfSheets = hssfWorkbook.getNumberOfSheets();
			for (int i = 0; i < numberOfSheets; i++) {
				HSSFSheet sheetAt = hssfWorkbook.getSheetAt(i);
				int physicalNumberOfRows = sheetAt.getPhysicalNumberOfRows();
				for (int j = 0; j < physicalNumberOfRows; j++) {
					HSSFRow row = sheetAt.getRow(j);
					int physicalNumberOfCells = row.getPhysicalNumberOfCells();
					for (int k = 0; k < physicalNumberOfCells; k++) {
						HSSFCell cell = row.getCell(k);
					}
				}
				
				
			}
		}
	
		/**
		* 读取2003excel
		* 
		* @param file
		* @return
		*/
		private static List<List<Object>> read2003Excel(File file)
		throws IOException {
		List<List<Object>> dataList = new ArrayList<List<Object>>();
		HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(file));
		int shelltNum=wb.getNumberOfSheets();
		System.out.println("几页>"+shelltNum);
		for(int c=0;c<shelltNum;c++){
			HSSFSheet sheet = wb.getSheetAt(c);
			System.out.println("读取第几页数据>>>"+wb.getSheetName(c));
			String str=city(wb.getSheetName(c));
			HSSFRow row = null;
			HSSFCell cell = null;
			Object val = null;
			DecimalFormat df = new DecimalFormat("0");// 格式化数字
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
			System.out.println("==="+sheet.getFirstRowNum()+"======"+sheet.getPhysicalNumberOfRows());
			int rowN=sheet.getLastRowNum()+1; //行数
			System.out.println( rowN );
			for (int i = sheet.getFirstRowNum(); i < sheet
					.getPhysicalNumberOfRows(); i++) {
				System.out.println("第"+i+"行");
				row = sheet.getRow(i);
				if(i==0) continue;	//第一为标题 continue
				if (row == null) {
					continue;
				}
				List<Object> objList = new ArrayList<Object>();
				 System.out.println("==="+row.getFirstCellNum()+"======"+row.getLastCellNum());
//				 for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
				for (int j = 0; j < row.getLastCellNum(); j++) {
					cell = row.getCell(j);
					if (cell == null) {
						val ="";
						objList.add(val);
						continue;
					}
					switch (cell.getCellType()) {
					case HSSFCell.CELL_TYPE_STRING:
						val = cell.getStringCellValue().trim();
						break;
					case HSSFCell.CELL_TYPE_NUMERIC:
						// System.out.println(cell.getCellStyle().getDataFormatString()+"====11=====");
						if ("@".equals(cell.getCellStyle().getDataFormatString())) {
							val = df.format(cell.getNumericCellValue());
//							val=cell.getNumericCellValue();
							System.out.println("@@@@"+cell.getCellStyle().getDataFormatString());
						} else if ("General".equals(cell.getCellStyle()
								.getDataFormatString())) {
//							val = df.format(cell.getNumericCellValue());
							System.out.println("gggg"+cell.getCellStyle().getDataFormatString());
							val=cell.getNumericCellValue();
						} else {
							// System.err.println(cell.getCellStyle().getDataFormatString()+"====cell.getCellStyle().getDataFormatString()=====");
//							val=cell.getNumericCellValue();
							val = df.format(cell.getNumericCellValue());
//							val = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
							System.out.println("XXXXX"+cell.getCellStyle().getDataFormatString());
						}
						break;
					case HSSFCell.CELL_TYPE_BOOLEAN:
						val = cell.getBooleanCellValue();
						break;
					case HSSFCell.CELL_TYPE_BLANK:
						val = "";
						break;
					default:
						val = cell.toString();
						break;
					}
					if(j==9){
						val=str+val;
					}
					
					objList.add(val);
				}
				dataList.add(objList);
			}
		 }
		return dataList;
		}

		/**
		* 读取excel表头
		* 
		* @param file
		* @return
		* @throws IOException
		*/
		public static String[] readExcelHead(File file) throws IOException {
		HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(file));
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow row = null;
		HSSFCell cell = null;
		row = sheet.getRow(0);
		String[] buff = new String[row.getLastCellNum()];
		for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
		cell = row.getCell(i);
		buff[i] = cell.getStringCellValue();
		}
		return buff;
		}

		/**
		* 读取2007excel
		* 
		* @param file
		* @return
		*/

		private static List<List<Object>> read2007Excel(File file)
		throws IOException {
		List<List<Object>> dataList = new ArrayList<List<Object>>();
		XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));
		int shelltNum=xwb.getNumberOfSheets();
		
		for(int c=0;c<shelltNum;c++){
		String name=xwb.getSheetName(c);
		System.out.println(name);
		XSSFSheet sheet = xwb.getSheetAt(c);
		XSSFRow row = null;
		XSSFCell cell = null;
		Object val = null;
		DecimalFormat df = new DecimalFormat("0");// 格式化数字
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
		for (int i = sheet.getFirstRowNum(); i < sheet
				.getPhysicalNumberOfRows(); i++) {
		if(i==0) continue;
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			
			List<Object> objList = new ArrayList<Object>();
			for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
				cell = row.getCell(j);
				if (cell == null) {
					val = null;
					objList.add(val);
					continue;
				}
				switch (cell.getCellType()) {
				case XSSFCell.CELL_TYPE_STRING:
					
						val = cell.getStringCellValue().trim();
					
					break;
				case XSSFCell.CELL_TYPE_NUMERIC:
					if ("@".equals(cell.getCellStyle().getDataFormatString())) {
						val = df.format(cell.getNumericCellValue());
					} else if ("General".equals(cell.getCellStyle()
							.getDataFormatString())) {
						val = df.format(cell.getNumericCellValue());
					} else {
						val = sdf.format(HSSFDateUtil.getJavaDate(cell
								.getNumericCellValue()));
					}
					break;
				case XSSFCell.CELL_TYPE_BOOLEAN:
					val = cell.getBooleanCellValue();
					break;
				case XSSFCell.CELL_TYPE_BLANK:
					val = "";
					break;
				default:
					val = cell.toString();
					break;
				}
				objList.add(val);
			}
			dataList.add(objList);
		}
			
		}
		
		return dataList;
		}
		
		public static Integer getIndex(List<List<Object>> list ,String col){
			Integer index=null;
			if(list!=null&&list.size()>0){
				for (int i = 0; i <list.size(); i++) {
					if(col.equals(list.get(i).get(1)+"")){
						index= i;
						break;
					}
				}
			}
			return index;
			}
		public static void main(String[] args)  {
			File file = new File("C:/3.xls");
			try {
				List<List<Object>>  a=readExcel( file) ;
				for(int i=0;i<a.size();i++){
						System.out.println(a.get(i).get(1));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		private static String city(String key){
			System.out.println(">>>key>"+key);
			Map<String ,Object> map=new HashMap<String, Object>();
			map.put("南京", "江苏省-南京市-");
			map.put("北京", "北京市-北京市-");
			map.put("天津", "天津市-天津市-");
			map.put("上海", "上海市-上海市-");
			map.put("广州", "广东省-广州市-");
			map.put("深圳", "广东省-深圳市-");
			map.put("苏州", "江苏省-苏州市-");
			map.put("杭州", "浙江省-杭州市-");
			map.put("武汉", "湖北省-武汉市-");
			map.put("西安", "陕西省-西安市-");
			map.put("成都", "四川省-成都市-");
			map.put("宁波", "浙江省-宁波市-");
			map.put("合肥", "安徽省-合肥市-");
			map.put("大连", "辽宁省-大连市-");
			map.put("全部数据", "");
			return map.get(key).toString();
		}
		
		
		public final static String getUUID() {
			return UUID.randomUUID().toString().replace("-", "");
		}
}
