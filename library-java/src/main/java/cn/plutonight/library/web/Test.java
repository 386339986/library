package cn.plutonight.library.web;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "test")
@RestController
@RequestMapping("/test")
public class Test {

    @RequestMapping("/test")
    public String test() {
        return "test";
    }
}
