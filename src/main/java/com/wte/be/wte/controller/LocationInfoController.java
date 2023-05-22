package com.wte.be.wte.controller;

import com.fasterxml.jackson.core.*;
import com.wte.be.wte.application.*;
import com.wte.be.wte.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/location")
@CrossOrigin("*")
public class LocationInfoController {

    @Autowired
    private GetLocationService getLocationService;

    @PostMapping
    public ResponseEntity<Message> getLocationInfo(@RequestBody HashMap param) throws JsonProcessingException {
        return getLocationService.getLocationInfo((String) param.get("location"), "random");
    }
}
