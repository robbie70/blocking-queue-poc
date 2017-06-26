package com.riskcare;

import jdk.nashorn.internal.ir.Block;

import java.util.concurrent.BlockingQueue;

class Seller implements Runnable{

    private BlockingQueue orderQueue;
    private boolean shutDownRequest = false;
    private static int id;

    public Seller(BlockingQueue orderQueue){
        this.orderQueue = orderQueue;
    }

    public void run(){
        while(shutDownRequest == false){
            Integer quantity = (int) (Math.random() * 100);
            try{
                orderQueue.put(quantity);
                System.out.println("Sell order by " + Thread.currentThread().getName() + ": " + quantity);
            }catch (InterruptedException iex){
                shutDownRequest = true;
            }
        }
    }

}