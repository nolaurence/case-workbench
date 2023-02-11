package cn.nolaurence.caseworkbench.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: nolaurence
 * @Description: MainController
 * @Date: 11/2/2023
 */
@Controller
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    @RequestMapping(value = "/")
    public ModelAndView mainEntrance(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("antd");

        return modelAndView;
    }
}
