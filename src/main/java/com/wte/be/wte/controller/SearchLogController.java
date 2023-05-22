package com.wte.be.wte.controller;

import com.wte.be.wte.application.*;
import com.wte.be.wte.dtos.*;
import com.wte.be.wte.util.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logs")
@CrossOrigin("*")
@Slf4j
public class SearchLogController {
    @Autowired
    private SearchLogService searchLogService;

    @PostMapping("insert")
    public ResponseEntity<Message> insertLog(@RequestBody SearchLogDTO param) {
        return searchLogService.insertLog(param);
    }
}
