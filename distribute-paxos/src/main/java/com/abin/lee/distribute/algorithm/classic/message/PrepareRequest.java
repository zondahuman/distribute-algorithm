package com.abin.lee.distribute.algorithm.classic.message;


import com.abin.lee.distribute.algorithm.classic.Message;

public class PrepareRequest implements Message {

    private long proposalNumber;
    private String sourceId;

    public PrepareRequest(long proposalNumber, String sourceId){
        this.proposalNumber = proposalNumber;
        this.sourceId = sourceId;
    }
    
    public long getProposalNumber() {
        return proposalNumber;
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.PrepareRequest;
    }
    
    public String getSourceId(){
        return sourceId;
    }

    @Override
    public String toString() {
        return "PrepareRequest [proposalNumber=" + proposalNumber
                + ", sourceId=" + sourceId + "]";
    }
    
    
}
