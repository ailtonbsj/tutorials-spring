package br.ailtonbsj.ssespring.services;

import java.time.LocalTime;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

public class SenderThread implements Runnable {

    SseEmitter emitter;

    public SenderThread(SseEmitter emitter) {
        this.emitter = emitter;
    }

    @Override
    public void run() {
        try {
                for(int i = 0; true; i++) {
                    SseEventBuilder event = SseEmitter.event()
                        .data("SSE MVC - " + LocalTime.now().toString() + "\n")
                        .id(String.valueOf(i))
                        .name("ping");
                    emitter.send(event);
                    System.out.println(i);
                    Thread.sleep();
                }
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
    }
    
}
