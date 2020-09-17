package ru.javalab.socketapp.protocol;

public class CommandPayload {
    private String command;
    private int page, size;

    public CommandPayload() {

    }

    public CommandPayload(String command, int page, int size) {
        this.command = command;
        this.page = page;
        this.size = size;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

