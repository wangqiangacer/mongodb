package com.jacken.mongodb;

public class ThreadMain {
    public static void main(String[] args) {
        new Thread(new ThreadDemo()).start();
    }
}
