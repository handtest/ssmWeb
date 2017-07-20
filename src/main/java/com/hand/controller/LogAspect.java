package com.hand.controller;

import com.hand.entity.Log;
import com.hand.service.LogService;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.ValidationEvent;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by hanlu on 2017/7/14.
 */
@Aspect
@Component
public class LogAspect{
    Logger logger = Logger.getLogger(LogAspect.class);
    @Resource
    LogService logService;

    /**
     * 登录操作(后置通知)
     * @param joinPoint
     * @param object
     * @throws Throwable
     */
    @AfterReturning(value = "execution(* com.hand.controller.UserController.login(..))", returning = "object")
    public void loginLog(JoinPoint joinPoint, Object object) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (args == null) {
            return;
        }
        String[] params = {"userName","checkCode"};
        // 获取方法名
        String classInfo = joinPoint.getTarget().getClass().getName();
        String className = classInfo.substring(classInfo.lastIndexOf(".")+1,classInfo.length());
        String packageName = classInfo.substring(0,classInfo.lastIndexOf("."));
        String mothedName = joinPoint.getSignature().getName();
        // 获取方法名
        String methodName = packageName+"."+mothedName;
        optionContent(args,methodName,params);
    }

    /**
     * 添加操作日志(后置通知)
     *
     * @param joinPoint
     * @param object
     */
    @AfterReturning(value = "execution(* com.hand.controller.UserController.save(..))", returning = "object")
    public void insertLog(JoinPoint joinPoint, Object object) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (args == null) {
            return;
        }
        String[] params = {"userName","age","address"};
        // 获取方法名
        String classInfo = joinPoint.getTarget().getClass().getName();
        String className = classInfo.substring(classInfo.lastIndexOf(".")+1,classInfo.length());
        String packageName = classInfo.substring(0,classInfo.lastIndexOf("."));
        String mothedName = joinPoint.getSignature().getName();
        // 获取方法名
        String methodName = packageName+"."+mothedName;
        optionContent(args,methodName,params);
    }

    /**
     * 管理员修改操作日志(后置通知)
     *
     * @param joinPoint
     * @param object
     * @throws Throwable
     */
    @AfterReturning(value = "execution(* com.hand.controller.UserController.updateUser(..))", returning = "object")
    public void updateLog(JoinPoint joinPoint, Object object) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (args == null) {
            return;
        }
        String[] params = {"userName","age","address","id"};
        // 获取方法名
        String classInfo = joinPoint.getTarget().getClass().getName();
        String className = classInfo.substring(classInfo.lastIndexOf(".")+1,classInfo.length());
        String packageName = classInfo.substring(0,classInfo.lastIndexOf("."));
        String mothedName = joinPoint.getSignature().getName();
        // 获取方法名
        String methodName = packageName+"."+mothedName;
        optionContent(args,methodName,params);

    }

    /**
     * 删除操作
     *
     * @param joinPoint
     * @param object
     */
    @AfterReturning(value = "execution(* com.hand.controller.UserController.remove(..))", returning = "object")
    public void deleteLog(JoinPoint joinPoint, Object object) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (args == null) {
            return;
        }
        String[] params = {"id"};

        String classInfo = joinPoint.getTarget().getClass().getName();
        String className = classInfo.substring(classInfo.lastIndexOf(".")+1,classInfo.length());
        String packageName = classInfo.substring(0,classInfo.lastIndexOf("."));
        String mothedName = joinPoint.getSignature().getName();
        // 获取方法名
        String methodName = packageName+"."+mothedName;
        optionContent(args,methodName,params);
    }

    /**
     * 使用Java反射来获取被拦截方法(insert、update)的参数值， 将参数值拼接为操作内容
     *
     * @param args
     * @param strs
     * @return
     */
    public void optionContent(Object[] args,String mName, String[] strs) {
        HttpSession session = null;
        StringBuffer params = new StringBuffer();
        String ip = "";
        // 遍历参数对象
        for (Object info : args) {
            // 获取对象的所有方法
            Method[] methods = info.getClass().getDeclaredMethods();
            // 遍历方法，判断get方法
            for (Method method : methods) {
                String methodName = method.getName();
                // 判断是不是session方法
                Object rsValue = null;
                try {
                    if (methodName.indexOf("getSession") != -1) {
                        rsValue = method.invoke(info);
                        // 调用session方法，获取返回值
                        session = (HttpSession)rsValue;
                    }

                    if (methodName.indexOf("getParameter") != -1) {
                        for (int i = 0; i < strs.length; i++){
                            String param = (String)method.invoke(info,strs[i]);
                            params.append(param);
                            if(i < strs.length-1){
                                params.append("--");
                            }
                        }
                    }

                    if (methodName.indexOf("getRemoteAddr") != -1) {
                        ip = (String) method.invoke(info);
                    }
                } catch (Exception e) {
                    logger.error(e);
                }
            }
        }
        saveLog(session,mName,params,ip);
    }

    public void saveLog(HttpSession session, String methodName, StringBuffer params, String ip){
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        Log log = new Log();
        try {
            log.setOperationUserId((Integer) session.getAttribute("id"));
            log.setOperationUsername((String) session.getAttribute("name"));
            log.setOperationAddress(methodName);
            log.setOperationDescription("");
            log.setOperationParam(params.toString());
            log.setOperationIp(ip);
            log.setOperationDate(dateFormater.parse(dateFormater.format(date)));
        } catch (ParseException e) {
            logger.error(e);
        }
        logService.save(log);
    }
}
