package ru.job4j.pooh;

import java.util.LinkedList;

public class TopicService implements Service {
    LinkedList<Req> linkedList = new LinkedList<>();

    @Override
    public Resp process(Req req) {
        return new Resp(req.getParam(), "200");
    }
}