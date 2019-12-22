package com.lwt.dataexport.common;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * 这个工具类没有使用common-csv的相关组件
 * 仅仅使用字符串的拼接底层还是io流的操作来实现csv格式数据的输出
 */
public class CsvUtils {
    /**
     * CSV文件生成方法
     */
    public static File createCSVFile(List<String> head, List<Map<String, String>> rowList,
                              String outPutPath, String filename) {
        File csvFile = null;
        BufferedWriter csvWtriter = null;
        try {
            csvFile = new File(outPutPath + File.separator + filename + ".csv");
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();
            // GB2312使正确读取分隔符","
            csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                    csvFile), "GB2312"), 1024);
            // 写入文件头部
            writeHeader(head, csvWtriter);
            // 写入文件内容
            for (Map<String, String> row : rowList) {
                writeRowData(head, row, csvWtriter);
            }
            csvWtriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (csvWtriter != null) {
                    csvWtriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvFile;
    }


    /**
     * 写一行数据方法
     * 不同列之间的分隔符可以设置为（，）或（\t） 即逗号或制表符等
     */
    private static void writeHeader(List<String> row, BufferedWriter csvWriter) throws IOException {
        // 写入文件头部
        for (String data : row) {
            StringBuffer sb = new StringBuffer();
            String rowStr = sb.append("\"").append(data).append("\",").toString();
            csvWriter.write(rowStr);
        }
        csvWriter.newLine();
    }


    private static void writeRowData(List<String> head, Map<String, String> rowData, BufferedWriter csvWriter) throws IOException {
        // 写入文件头部
        for (String heaData : head) {
            StringBuffer sb = new StringBuffer();
            String rowStr = sb.append("\"").append(rowData.get(heaData)).append("\",").toString();
            csvWriter.write(rowStr);
        }
        csvWriter.newLine();
    }
}
