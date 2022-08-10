package com.roach.webfluxdemo.controller;

import com.roach.webfluxdemo.dto.Response;
import com.roach.webfluxdemo.service.MathService;
import com.roach.webfluxdemo.service.ReactiveMathService;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("reactive-math")
public class ReactiveMathController {

    private final ReactiveMathService mathService;

    @GetMapping("/square/{input}")
    public Mono<Response> findSquare(
            @PathVariable("input") int input
    ) {
        return this.mathService.findSquare(input);
    }


    @GetMapping("/table/{input}")
    public Flux<Response> multiplicationTable(
            @PathVariable("input") int input
    ) {
        return this.mathService.multiplicationTable(input);
    }

    @GetMapping(value = "/table/{input}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Response> multiplicationTableStream(
            @PathVariable("input") int input
    ) {
        return this.mathService.multiplicationTable(input);
    }



}
