package com.example.webfluxerror.controller;

import com.example.webfluxerror.domain.MyRequest;
import com.example.webfluxerror.domain.MyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@Slf4j
public class MyController {

    @Autowired
    private WebClient webClient;

    @PostMapping
    public Mono<MyResponse> myEndpoint(@RequestBody MyRequest myRequest) {
        final long startTime = System.currentTimeMillis();

        return webClient.post()
                .uri("http://localhost:8181/mock")
                .header("Content-Type", "application/json")
                .bodyValue(myRequest)
                .retrieve()
                .bodyToMono(MyResponse.class)
                .flatMap(myResponse -> {
                    final long endTime = System.currentTimeMillis() - startTime;

                    log.info("Duration: {}ms", endTime);
                    // Business Logic
                    return  Mono.just(MyResponse.builder()
                                    .id(myResponse.getId())
                            .build());
                }).timeout(Duration.ofMillis(600))
                .doOnError(e -> log.error(e.getCause().getMessage()));
    }
}
