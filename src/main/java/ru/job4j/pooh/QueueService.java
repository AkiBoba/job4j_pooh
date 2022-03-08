package ru.job4j.pooh;

import java.util.LinkedList;

public class QueueService implements Service {
    LinkedList<Req> linkedList = new LinkedList<>();

    public Resp process(Req req) {
        return new Resp(req.getParam(), "200");
    }
}
