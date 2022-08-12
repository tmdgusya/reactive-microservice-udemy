package com.roach.webfluxdemo.service;

import com.roach.webfluxdemo.dto.MultiplyRequest;
import com.roach.webfluxdemo.dto.Response;

import org.springframework.stereotype.Service;

import java.time.Duration;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveMathService {

    public Mono<Response> findSquare(int input) {
        return Mono
                .fromSupplier(() -> input * input)
                .map(Response::new);
    }

    public Flux<Response> multiplicationTable(int input) {
        return Flux
                .range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("reactive-math service processing : " + i))
                .map(i -> new Response(i * input));
    }

    public Flux<Response> multiplicationTableStream(int input) {
        return Flux
                .range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("reactive-math service processing : " + i))
                .map(i -> new Response(i * input));
    }

    public Mono<Response> multiply(Mono<MultiplyRequest> request) {
        return request
                .map(data -> data.getFirst() * data.getSecond())
                .map(Response::new);
    }

}
