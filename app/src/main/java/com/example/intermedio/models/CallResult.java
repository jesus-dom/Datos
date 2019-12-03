package com.example.intermedio.models;

public class CallResult {

    private boolean error;
    private int id;

    public CallResult(){

    }

    public CallResult(boolean error, int id) {
        this.error = error;
        this.id = id;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
