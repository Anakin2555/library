package com.example.libarary.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.libarary.bean.LendingInfo;
import com.example.libarary.service.LendingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LendingInfoController {
    @Autowired
    private LendingInfoService lendingInfoService;

    @GetMapping("/getLendingInfoByUsername")
    public String getLendingInfoByUsername(@RequestParam("username") String username) {
        List<LendingInfo> lendingInfos = lendingInfoService.queryByUsername(username);
        return JSONObject.toJSONString(lendingInfos);
    }

    @GetMapping("/getLendingInfoByTitle")
    public String getLendingInfoByTitle(@RequestParam("title") String title) {
        List<LendingInfo> lendingInfos = lendingInfoService.queryByTitle(title);
        return JSONObject.toJSONString(lendingInfos);
    }

    @GetMapping("/getLendingInfo")
    public String getLendingInfo() {
        List<LendingInfo> lendingInfos = lendingInfoService.queryAll();
        return JSONObject.toJSONString(lendingInfos);
    }

}
