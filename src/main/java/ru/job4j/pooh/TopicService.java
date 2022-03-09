package ru.job4j.pooh;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TopicService implements Service {
    private static ConcurrentHashMap<String, ConcurrentHashMap<String,
            ConcurrentLinkedQueue<String>>> topics = null;

    public TopicService() {
        this.topics = new ConcurrentHashMap<>();
    }

    public Resp process(Req req) {

        if ("POST".equals(req.httpRequestType())) {
            add(req);
            return new Resp(req.getParam(), "200");
        } else {
            return get(req);
        }
    }

    private void add(Req req) {
        topics.values().forEach(topic -> {
            topic.get(req.getSourceName()).add(req.getParam());
        });
    }

    private Resp get(Req req) {
        Resp resp;
        if (topics.putIfAbsent(req.getParam(), new ConcurrentHashMap<String,
                ConcurrentLinkedQueue<String>>()) != null) {
            resp = new Resp(topics.get(req.getParam()).get(req.getSourceName()).poll(), "200");
        } else {
            topics.get(req.getParam()).put(req.getSourceName(), new ConcurrentLinkedQueue<>());
            resp = new Resp("", "201");
        }
        return resp;

    }
}