package com.thinksync.virtualthreadseries.sec01;

import java.util.concurrent.CountDownLatch;

public class InboundOutboundTaskApp {

    private static final int MAX_PLATFORM_THREAD_SIZE = 10;
    private static final int MAX_VIRTUAL_THREAD_SIZE = 10;


    public static void main(String[] args) throws InterruptedException {
        platformThreadDemo2();
    }


    /**
     * Platform thread starts here
     */
    public static void platformThreadDemo(){
        for(int i=0;i<MAX_PLATFORM_THREAD_SIZE;i++){
            int j = i;
            Thread th = new Thread(() -> Task.ioIntensiveTask(j));
            th.start();
        }
    }

    public static void platformThreadDemo2(){
        var builder =Thread.ofPlatform().name("platform-Thread-",1);

        for(int i=0;i<MAX_PLATFORM_THREAD_SIZE;i++){
            int j = i;
            Thread th = builder.unstarted(() -> Task.ioIntensiveTask(j));
            th.start();
        }

    }

    // Demon
    public static void platformDemonThread3() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(MAX_PLATFORM_THREAD_SIZE);
        var platformThreadBuilder = Thread.ofPlatform().daemon().name("DemonThread-", 1);

        for(int i=0;i<MAX_PLATFORM_THREAD_SIZE;i++){
            int finalI = i;
            Thread th = platformThreadBuilder.unstarted(()-> {
                 Task.ioIntensiveTask(finalI);
                 countDownLatch.countDown();
            });
            th.start();
        }
        countDownLatch.await();
    }

    // Platform Thread Ends Here



    /**
     *  Virtual thread Section starts here.
     *
     *  Virtual Threads are by default demon thread
     *  Hence one need to use count down latch.
     *  If you run the bellow code without count down latch
     *  it will exit immediately. Hence we have to have a countdownlatch for this
     *
     *  public static void virtualThreadDemo1(){
     *         var virtualThread = Thread.ofVirtual().name("Virtual Thread- ",1);
     *         for(int i=0;i<MAX_VIRTUAL_THREAD_SIZE;i++){
     *             int j=i;
     *             Thread th = virtualThread.unstarted(()->{
     *                 Task.ioIntensiveTask(j);
     *             });
     *             th.start();
     *         }
     *     }
     *
     *
     */

    public static void virtualThreadDemo1() throws InterruptedException {
        var latch = new CountDownLatch(MAX_VIRTUAL_THREAD_SIZE);
        var virtualThread = Thread.ofVirtual().name("Virtual Thread- ",1);
        for(int i=0;i<MAX_VIRTUAL_THREAD_SIZE;i++){
            int j=i;
            Thread th = virtualThread.unstarted(()->{
                Task.ioIntensiveTask(j);
                latch.countDown();
            });
            th.start();
        }
        latch.await();
    }










    //Virtual thread ends here


}
