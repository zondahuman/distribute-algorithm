package com.abin.lee.distribute.algorithm.classic;


import com.abin.lee.distribute.algorithm.classic.message.SourceDestination;

public interface Component {

    void receiveMessage(SourceDestination sd, Message m);
    String getId();
}
