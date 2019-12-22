package com.lwt.dataexport.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.lwt.dataexport.common.CsvUtils;
import com.lwt.dataexport.common.StuUtil;
import com.lwt.dataexport.entity.Student;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping(value = "/test")
public class DownloadController {
    @RequestMapping(value = "/download")
    public @ResponseBody
    String download(@RequestBody String requestbody) {
        JSONObject jsonObject = JSON.parseObject(requestbody);
        String ids = jsonObject.getString("ids");
        String colums = jsonObject.getString("colums");

        System.out.println("requestbody:" + requestbody);
        String[] idArr = ids.split(";");
        String[] columArr = colums.split(";");

        for (String id : idArr) {
            System.out.println(id);
        }
        for (String colum : columArr) {
            System.out.println(colum);
        }
        return "success";
    }

    @RequestMapping(value = "/export")
    public @ResponseBody
    File export(@RequestBody String param,HttpServletResponse response) {
//    File export(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jparam = JSONObject.parseObject(param);
//        String jparam = request.getParameter("colums");
//        String[] columns =jparam.split(";");
        String[] columns = jparam.getString("colums").split(";");
        List<String> header = Arrays.asList(columns);
        List<Student> rowDataList = StuUtil.getStus(500);
        String outPath = "D:\\lwp";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String fileName = "CI-用例执行" + sdf.format(new Date());
        File exportFile = CsvUtils.createCSVFile(header, getRowData(rowDataList), outPath, fileName);
        export(exportFile,response);
        return exportFile;
    }

    private List<Map<String, String>> getRowData(List<Student> dataList) {
        List<Map<String, String>> rtnList = new ArrayList<>();
        try {
            for (Student stu : dataList) {
                rtnList.add(BeanUtils.describe(stu));
            }
        } catch (Exception e) {
            // TODO: 2019/12/22 log
        }
        return rtnList;
    }

    private void export(File file, HttpServletResponse response) {
        FileInputStream input = null;
        OutputStream out = null;
        try {
            input = new FileInputStream(file);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            response.setHeader("content-disposition",
                    "attachment;filename=" + URLEncoder.encode("CI-用例执行" + sdf.format(new Date()), "utf-8") + ".csv");
            out = response.getOutputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = input.read(bytes)) > 0) {
                out.write(bytes, 0, len);
            }
        } catch (Exception e) {
        } finally {
            try {
                input.close();
                out.close();
                file.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
