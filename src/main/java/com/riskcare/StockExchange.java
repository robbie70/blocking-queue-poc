package com.riskcare;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class
StockExchange {

    public static void main(String [] args) {
        System.out.printf("Hit Enter to terminate%n%n");
        BlockingQueue<Integer> orderQueue = new LinkedBlockingQueue<Integer>();
        Seller seller = new Seller (orderQueue);
        Thread []  sellerThread = new Thread[100];
        for (int i = 0; i < 100; i++){
            sellerThread[i] = new Thread(seller);
            sellerThread[i].start();
        }
        Buyer buyer = new Buyer(orderQueue);
        Thread[] buyerThread = new Thread[100];
        for (int i = 0; i < 100; i++){
            buyerThread[i] = new Thread(buyer);
            buyerThread[i].start();
        }
        try{
            while(System.in.read() != '\n');
        }catch (IOException ex){

        }
        System.out.println("Terminating");
        for (Thread t : sellerThread){
            t.interrupt();
        }
        for (Thread t : buyerThread){
            t.interrupt();
        }
    }

}
