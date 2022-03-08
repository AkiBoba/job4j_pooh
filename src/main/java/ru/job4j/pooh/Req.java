package ru.job4j.pooh;

import java.util.List;

public class Req {

    private final String httpRequestType;
    private final String poohMode;
    private final String sourceName;
    private final String param;

    public Req(String httpRequestType, String poohMode, String sourceName, String param) {
        this.httpRequestType = httpRequestType;
        this.poohMode = poohMode;
        this.sourceName = sourceName;
        this.param = param;
    }

    public static Req of(String content) {
        String[] list = content.split(" ");
        String param = "";
        String httpRequestType = list[0];
        String poohMode = list[1].split("/")[1];
        String sourceName = list[1].split("/")[2];
        if (list[1].split("/").length > 3) {
            param = list[1].split("/")[3];
            return new Req(httpRequestType, poohMode, sourceName, param);
        }
        if ("GET".equals(httpRequestType) && list[1].split("/").length < 4) {
            return new Req(httpRequestType, poohMode, sourceName, param);
        }
        param = list[7].split("\r\n\r\n")[1].split("\r\n")[0];
        return new Req(httpRequestType, poohMode, sourceName, param);
    }

    public String httpRequestType() {
        return httpRequestType;
    }

    public String getPoohMode() {
        return poohMode;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getParam() {
        return param;
    }
}