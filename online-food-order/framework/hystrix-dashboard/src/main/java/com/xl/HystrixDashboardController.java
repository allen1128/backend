package com.xl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by XL on 8/30/2017.
 */
@Controller
public class HystrixDashboardController {

    @RequestMapping(value="/")
    public String home(){
        return "forward:/hystrix";
    }
}
