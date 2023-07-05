package br.ailtonbsj.ssespring.controllers;

import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

import br.ailtonbsj.ssespring.services.SenderService;

@RestController
@RequestMapping("/")
public class IndexController {
    @Autowired
    SenderService senderService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/stream-sse-mvc")
    public SseEmitter streamSseMvc() {
        return senderService.streamSseMvc();
    }

    @GetMapping("/trigger")
    public String trigger() {
        
        return "Trigged";
    }

}
