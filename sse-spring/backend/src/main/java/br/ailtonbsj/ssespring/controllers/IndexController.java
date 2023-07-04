package br.ailtonbsj.ssespring.controllers;

import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

@RestController
@RequestMapping("/")
public class IndexController {

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/stream-sse-mvc")
    public SseEmitter streamSseMvc() {
        SseEmitter emitter = new SseEmitter();
        ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
        sseMvcExecutor.execute(() -> {
            try {
                for(int i = 0; true; i++) {
                    SseEventBuilder event = SseEmitter.event()
                        .data("SSE MVC - " + LocalTime.now().toString() + "\n")
                        .id(String.valueOf(i))
                        .name("ping");
                    emitter.send(event);
                    System.out.println(i);
                    Thread.sleep(10000);
                }
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        });
        return emitter;
    }

}
