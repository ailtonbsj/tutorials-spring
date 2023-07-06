package br.ailtonbsj.ssespring.services;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public class SenderService {

    SseEmitter emitter;
    SenderThread senderThread;

    public SenderService() {
        emitter = new SseEmitter();
        ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
        senderThread = new SenderThread(emitter);
        sseMvcExecutor.execute(senderThread);
    }

    public SseEmitter streamSseMvc() {
        return emitter;
    }

    public void doWork() {
        senderThread.doWork();
    }

}
