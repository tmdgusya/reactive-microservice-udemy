package com.roach.webfluxdemo.controller;

import com.roach.webfluxdemo.dto.Response;
import com.roach.webfluxdemo.service.MathService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("math")
public class MathController {

    private final MathService mathService;

    @GetMapping("/square/{input}")
    public Response findSquare(
            @PathVariable("input") int input
    ) {
        return this.mathService.findSquare(input);
    }


    @GetMapping("/table/{input}")
    public List<Response> multiplicationTable(
            @PathVariable("input") int input
    ) {
        return this.mathService.multiplicationTable(input);
    }


}
