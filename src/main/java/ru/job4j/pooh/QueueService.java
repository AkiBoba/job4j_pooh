package ru.job4j.pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueService implements Service {
    private static ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue = new ConcurrentHashMap<>();

    public QueueService() {
        this.queue = new ConcurrentHashMap<>();
    }

    public Resp process(Req req) {

        if ("POST".equals(req.httpRequestType())) {
            add(req);
            return new Resp(req.getParam(), "200");
        } else {
            return new Resp(queue.get(req.getSourceName()).poll(), "200");
        }
    }

    private void add(Req req) {
        queue.putIfAbsent(req.getSourceName(), new ConcurrentLinkedQueue<>());
           queue.get(req.getSourceName()).add(req.getParam());
    }
}
