//package com.yxyk.utils;
//
//import com.yxyk.bean.po.AdminSensitive;
//import com.yxyk.bean.po.ImportData;
//import com.yxyk.service.impl.SensitiveSeviceImpl;;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.multipart.MultipartFile;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.net.URLEncoder;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Excel {
//    @Autowired
//    private static SensitiveSeviceImpl sensitiveSevice;
//
//    public static String uploadExcel(MultipartFile file) {
//        AdminSensitive adminSensitive = new AdminSensitive();
//
//        if (file.isEmpty()) {
//            return "文件为空！";
//        }
//        try {
//            //根据路径获取这个操作excel的实例
//            XSSFWorkbook wb = new XSSFWorkbook (file.getInputStream());
//            //根据页面index 获取sheet页
//            XSSFSheet sheet = wb.getSheetAt(0);
//            //实体类集合
//            List<ImportData> importDatas = new ArrayList<>();
//            //循环sesheet页中数据从第二行开始，第一行是标题
//            for (int i = 1; i < sheet.getLastRowNum()+1; i++) {
//                //获取每一行数据
//                Cell cell = sheet.getRow(i).getCell(0);
//                System.out.println("row:"+cell.getStringCellValue());
//                ImportData data = new ImportData();
//                data.setSensitiveword(cell.getStringCellValue());
//                importDatas.add(data);
//            }
//            //循环展示导入的数据，实际应用中应该校验并存入数据库
//            for (ImportData imdata : importDatas) {
//                adminSensitive.setSensitiveword(imdata.getSensitiveword());
//                sensitiveSevice.saveSensitive(adminSensitive);
//                System.out.println("Sensitiveword:" + imdata.getSensitiveword());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "导入成功!";
//    }
//
//
//    public static  void downloadExcel(HttpServletResponse response, HttpServletRequest request) {
//        try {
//            //获取文件的路径
//            String excelPath = request.getSession().getServletContext().getRealPath("/Excel/" + "模板.xlsx");
//            // 读到流中
//            InputStream inStream = new FileInputStream(excelPath);//文件的存放路径
//            // 设置输出的格式
//            response.reset();
//            response.setContentType("bin");
//            response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode("模板.xlsx", "UTF-8"));
//            // 循环取出流中的数据
//            byte[] b = new byte[200];
//            int len;
//            while ((len = inStream.read(b)) > 0) {
//                response.getOutputStream().write(b, 0, len);
//            }
//            inStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
