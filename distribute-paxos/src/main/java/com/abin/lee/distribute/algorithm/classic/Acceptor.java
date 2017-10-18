package com.abin.lee.distribute.algorithm.classic;


import com.abin.lee.distribute.algorithm.classic.message.AcceptRequest;
import com.abin.lee.distribute.algorithm.classic.message.PrepareRequest;
import com.abin.lee.distribute.algorithm.classic.message.PrepareResponse;

public interface Acceptor extends Component{

    public PrepareResponse prepare(PrepareRequest m);

    public void accept(AcceptRequest m);

    public void sentLearntValue();

}
