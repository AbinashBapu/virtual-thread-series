package com.thinksync.virtualthreadseries.sec01;

public class InboundOutboundTaskApp {

    private static final int MAX_PLATFORM_THREAD_SIZE = 10;

    public static void main(String[] args) {
        platformThreadDemo2();
    }
    public static void platformThreadDemo(){
        for(int i=0;i<MAX_PLATFORM_THREAD_SIZE;i++){
            int j = i;
            Thread th = new Thread(() -> Task.ioIntensiveTask(j));
            th.start();
        }
    }

    public static void platformThreadDemo2(){
        var builder =Thread.ofPlatform().name("platform-Thread");


        for(int i=0;i<MAX_PLATFORM_THREAD_SIZE;i++){
            int j = i;
            Thread th = builder.unstarted(() -> Task.ioIntensiveTask(j));
            th.start();
        }
    }
}
