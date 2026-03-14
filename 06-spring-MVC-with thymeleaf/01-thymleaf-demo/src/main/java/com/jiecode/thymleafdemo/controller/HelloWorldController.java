package com.jiecode.thymleafdemo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {

    // 显示初始的HTML表单
    @GetMapping("/showForm")
    public String showForm() {
        return "helloworld-form"; // 返回 helloworld-form.html 页面
    }

    // 处理表单提交
    @PostMapping("/processForm")
    public String processForm() {
        return "helloworld"; // 返回显示结果的页面
    }



    // 读取表单数据并添加到Model
    @RequestMapping("/processFormVerionTwo")
    public String letShoutDue(@RequestParam("studentName") String theName,
                              Model model) {

        // 转换为大写
        theName = theName.toUpperCase();

        // 创建消息
        String result = "YoHEy! " + theName;

        // 将消息添加到模型中
        model.addAttribute("message", result);

        return "helloworld"; // 返回 helloworld.html 页面
    }




















/*
    // 读取表单数据并添加到Model
    @RequestMapping("/processFormVerionTwo")
    public String letShoutDue(HttpServletRequest request, Model model) {
        // 读取表单中的 studentName 参数
        String theName = request.getParameter("studentName");
        // 转换为大写
        theName = theName.toUpperCase();

        // 创建消息
        String result = "Yo! " + theName;

        // 将消息添加到模型中
        model.addAttribute("message", result);

        return "helloworld"; // 返回 helloworld.html 页面
    }

 */
}