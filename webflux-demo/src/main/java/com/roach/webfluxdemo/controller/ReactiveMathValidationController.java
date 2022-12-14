package com.roach.webfluxdemo.controller;

import com.roach.webfluxdemo.dto.Response;
import com.roach.webfluxdemo.exception.InputValidationException;
import com.roach.webfluxdemo.service.ReactiveMathService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("reactive-math")
public class ReactiveMathValidationController {

    private final ReactiveMathService mathService;

    @GetMapping("/square/{input}/throw")
    public Mono<Response> findSquare(
            @PathVariable("input") int input
    ) {
        if (input < 10 || input > 20) {
            throw new InputValidationException(input);
        }
        return this.mathService.findSquare(input);
    }

    @GetMapping("/square/{input}/monoError")
    public Mono<Response> monoError(
            @PathVariable("input") int input
    ) {
        return Mono
                .just(input)
                .handle((integer, sink) -> {
                    if (integer < 10 || input > 20) {
                        sink.error(new InputValidationException(input));
                    } else {
                        sink.next(integer);
                    }
                })
                .cast(Integer.class)
                .flatMap(this.mathService::findSquare);
    }

    @GetMapping("/square/{input}/assignment")
    public Mono<ResponseEntity<Response>> assignment(
            @PathVariable("input") int input
    ) {
        return Mono
                .just(input)
                .filter(i -> i >= 10 && i <= 20)
                .flatMap(this.mathService::findSquare)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }


}
