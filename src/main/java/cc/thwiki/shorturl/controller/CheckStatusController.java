package cc.thwiki.shorturl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckStatusController {

    @GetMapping("/checkstatus")
    public String checkStatus() {
        return "success";
    }
}
