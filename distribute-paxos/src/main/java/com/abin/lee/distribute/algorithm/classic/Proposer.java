package com.abin.lee.distribute.algorithm.classic;


import com.abin.lee.distribute.algorithm.classic.message.PrepareResponse;

public interface Proposer extends Component{

    public void propose(String proposedValue);
    public void receivePrepare(PrepareResponse r);
}
