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

    @GetMapping("/lendingInfo")
    public String getLendingInfo(@RequestParam("username") String username) {
        List<LendingInfo> lendingInfos = lendingInfoService.query(username);
        return JSONObject.toJSONString(lendingInfos);
    }

    @GetMapping("/allLendingInfo")
    public String getLendingInfo() {
        List<LendingInfo> lendingInfos = lendingInfoService.queryAll();
        return JSONObject.toJSONString(lendingInfos);
    }

}
