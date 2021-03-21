package com.platov.rest_api_test.controller;

import com.platov.rest_api_test.entity.FioRequest;
import com.platov.rest_api_test.entity.FioResponse;
import com.platov.rest_api_test.service.FioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FioController {

    @Autowired
    private FioService fioService;

    /**
     * Формирование ответа
     *
     * @param fioRequest
     */
    @PostMapping(value = "getFioById")
    public FioResponse sendResponse(@RequestBody FioRequest fioRequest) {
        return fioService.getFioById(fioRequest);
    }
}
