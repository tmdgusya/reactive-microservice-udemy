package com.roach.webfluxdemo.config;

import com.roach.webfluxdemo.dto.Response;
import com.roach.webfluxdemo.service.ReactiveMathService;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RequestHandler {

    private final ReactiveMathService reactiveMathService;

    public Mono<ServerResponse> squareHandler(ServerRequest serverRequest) {
        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        Mono<Response> square = this.reactiveMathService.findSquare(input);

        return ServerResponse.ok().body(square, Response.class);
    }

}
