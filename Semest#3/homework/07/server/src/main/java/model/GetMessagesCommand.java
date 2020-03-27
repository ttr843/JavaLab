package model;

import lombok.Getter;

@Getter
public class GetMessagesCommand implements Command {
    public String command = "get messages";
    private int page, size;
    private String token;
    public String getToken() {
        return token;
    }

    public GetMessagesCommand(int page, int size, String token) {
        this.page = page;
        this.size = size;
        this.token = token;
    }

    public int getPage() {
        return page;
    }

    public GetMessagesCommand() {
    }

    public int getSize() {
        return size;
    }

    public GetMessagesCommand(int page, int size) {
        this.page = page;
        this.size = size;
    }
}
