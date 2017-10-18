package com.abin.lee.distribute.algorithm.classic.manage;


import com.abin.lee.distribute.algorithm.classic.*;
import com.abin.lee.distribute.algorithm.classic.impl.AcceptorImpl;
import com.abin.lee.distribute.algorithm.classic.impl.LearnerImpl;
import com.abin.lee.distribute.algorithm.classic.impl.NetworkDeliveryImpl;
import com.abin.lee.distribute.algorithm.classic.impl.ProposerImpl;

public class MainWorker {

    private static int NUMBER_OF_ACCEPTORS = 7;
    private static int NUMBER_OF_PROPOSERS = 3;
    private static int NUMBER_OF_LEARNERS = 3;
    public static void main(String args[]){
        
        NetworkDelivery networkDelivery = new NetworkDeliveryImpl(0.9);
        
        for(int i=0; i < NUMBER_OF_LEARNERS; i++){
            Learner learner = new LearnerImpl("Leaner"+i, NUMBER_OF_ACCEPTORS);
            networkDelivery.registerComponent(learner.getId(), ComponentType.LEARNER, learner);
        }
        
        for(int i=0; i < NUMBER_OF_ACCEPTORS ; i++){
            Acceptor acceptor = new AcceptorImpl(networkDelivery, "Acceptor" + i);
            networkDelivery.registerComponent(acceptor.getId(), ComponentType.ACCEPTOR, acceptor);
            AcceptorDaemon daemon = new AcceptorDaemon(acceptor);
            daemon.start();
        }

        for(int i=0; i < NUMBER_OF_PROPOSERS ; i++){
            Proposer proposer = new ProposerImpl(NUMBER_OF_ACCEPTORS, networkDelivery, "Proposer" + i);
            networkDelivery.registerComponent(proposer.getId(), ComponentType.PROPOSER, proposer);
            ProposerDaemon daemon = new ProposerDaemon(proposer);
            daemon.start();
        }
    }
}
