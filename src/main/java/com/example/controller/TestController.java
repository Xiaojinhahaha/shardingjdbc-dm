package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dao.TestDateMapper;
import com.example.dao.TestMapper;
import com.example.entity.TestDateDo;
import com.example.entity.TestDo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author xiaoj
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestMapper testMapper;
    @Autowired
    private TestDateMapper testDateMapper;

    @PostMapping("/mod/add")
    public String modAdd(@RequestBody TestDo testDo) {
        testMapper.insert(testDo);
        return "success";
    }

    @GetMapping("/mod/query")
    public List<TestDo> modQuery() {
        return testMapper.selectList(new QueryWrapper<TestDo>());
    }

    @PostMapping("/date/add")
    public String add(@RequestBody TestDateDo testDateDo) {
        testDateMapper.insert(testDateDo);
        return "success";
    }

    @GetMapping("/date/query")
    public ResponseEntity<List<Map<String, Object>>> page(@RequestParam(name = "date", required = false) String date) {
        QueryWrapper<TestDateDo> queryWrapper = new QueryWrapper<TestDateDo>().select("create_date", "count(1) as count").eq(StringUtils.isNotBlank(date), "create_date", date)
                .groupBy("create_date");
         List<Map<String, Object>> testDateDos = testDateMapper.selectMaps(queryWrapper);
        return new ResponseEntity<>(testDateDos, HttpStatus.OK);
    }
}
