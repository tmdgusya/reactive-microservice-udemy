package com.roach.webfluxdemo;

import com.roach.webfluxdemo.dto.Response;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class SingleResponseTest extends WebfluxDemoApplicationTests {

    @Autowired
    private WebClient webClient;

    @Test
    public void blockTest() {

        Response response = this.webClient
                .get()
                .uri("reactive-math/square/{number}", 5)
                .retrieve()
                .bodyToMono(Response.class)
                .block(); // Do not use .. Only Test

        System.out.println(response);
    }

    @Test
    public void stepVerifierTest() {
        Mono<Response> response = this.webClient
                .get()
                .uri("reactive-math/square/{number}", 5)
                .retrieve()
                .bodyToMono(Response.class);

        StepVerifier.create(response)
                .expectNextMatches(r -> r.getOutput() == 25)
                .verifyComplete();

        System.out.println(response);
    }

}
