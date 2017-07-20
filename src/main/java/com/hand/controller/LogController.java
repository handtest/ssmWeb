package com.hand.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hand.entity.Log;
import com.hand.entity.LogQueryParam;
import com.hand.service.LogService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hanlu on 2017/7/17.
 */
@Controller
public class LogController {
    final private int size = 10;

    @Resource
    LogService logService;

    /*
    * 显示日志列表
    * param logQueryParam  查询参数类
    * param page  页数
    * return "showLog" 日志列表
    * */
    @RequestMapping(value = "/listLog", method = RequestMethod.POST)
    public void listLog(HttpServletRequest request, HttpServletResponse response,
                          @Param("logQueryParam")LogQueryParam logQueryParam,
                          Model model,
                          @RequestParam(value = "page",required = false,defaultValue = "1")int page) {
        PageHelper.startPage((page-1)*10,10);
        List<Log> listLog = logService.listLog(logQueryParam);
        int totalSize = totalPage((int)((Page<?>) listLog).getTotal());
        ObjectMapper objectMapper = new ObjectMapper();
        String callback = request.getParameter("callback");
        response.setCharacterEncoding("utf-8");
        model.addAttribute("listLog", listLog);
        model.addAttribute("totalPage",totalSize);
        try {
            String s = objectMapper.writeValueAsString(listLog);
            response.getWriter().print(callback+"("+s+");");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*
    * 根据日期查询用户操作日志
    * param afterDate
    * param beforeDate
    * return "showLog"
    * */
    @RequestMapping("/getLogForDate")
    public String getLogForDate(HttpServletRequest request,
                                HttpServletResponse response, Model model,
                                @RequestParam(value = "page",required = false,defaultValue = "0")int page) {
        String afterDate = (String) request.getParameter("afterDate");
        String beforeDate = (String) request.getParameter("beforeDate");
        java.text.SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        HashMap<String,Object> dateMap = new HashMap<String, Object>();
        try {
            dateMap.put("afterDate",formatter.parse(afterDate));
            dateMap.put("beforeDate",formatter.parse(beforeDate));
            dateMap.put("page",page);
            dateMap.put("size",size);
            List<Log> listLog = logService.listLogForDate(dateMap);
            int totalPageSize = totalPage(listLog.size());
            model.addAttribute("listLog",listLog);
            model.addAttribute("totalPage",totalPageSize);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "showLog";
    }


    /*
    * 分页
    * param totalSize
    * return totalPage
    * */
    public int totalPage(int totalSize){
        if (totalSize%10 > 0){
            return totalSize = totalSize/10+1;
        }else {
            return totalSize = totalSize/10;
        }
    }
}
