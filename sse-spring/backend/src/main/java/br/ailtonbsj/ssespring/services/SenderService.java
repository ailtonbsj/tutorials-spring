package br.ailtonbsj.ssespring.services;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public class SenderService {

    SseEmitter emitter;

    public SenderService() {
        this.emitter = new SseEmitter();
        ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
        sseMvcExecutor.execute(new SenderThread(this.emitter));
    }

    public SseEmitter streamSseMvc() {
        return this.emitter;
    }

}
