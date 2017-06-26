package com.riskcare;

import java.util.concurrent.BlockingQueue;

class Buyer implements Runnable{

    private BlockingQueue orderQueue;
    private boolean shutDownRequest = false;

    public Buyer(BlockingQueue orderQueue){
        this.orderQueue = orderQueue;
    }

    public void run(){
        while(shutDownRequest == false){
            try{
                Integer quantity = (Integer) orderQueue.take();
                System.out.println("Buy order by " + Thread.currentThread().getName() + ": " + quantity);
            }catch (InterruptedException iex){
                shutDownRequest = true;
            }
        }
    }
}
