package com.abin.lee.distribute.algorithm.classic.manage;


import com.abin.lee.distribute.algorithm.classic.Acceptor;

public class AcceptorDaemon extends Thread{
    
    private Acceptor acceptor;
    public AcceptorDaemon(Acceptor acceptor){
        this.acceptor = acceptor;
    }
    
    @Override
    public void run(){
        while(true){
            try{
                acceptor.sentLearntValue();
                Thread.sleep(5000);
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("Exception during sending message to learners");
            }
        }
    }
    
}
