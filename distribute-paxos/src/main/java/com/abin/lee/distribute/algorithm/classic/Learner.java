package com.abin.lee.distribute.algorithm.classic;


import com.abin.lee.distribute.algorithm.classic.message.LearnerMessage;

public interface Learner extends Component{

    public void learnProposedValue(LearnerMessage m);
}
