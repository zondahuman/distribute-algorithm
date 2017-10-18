package com.abin.lee.distribute.algorithm.classic;


import com.abin.lee.distribute.algorithm.classic.message.SourceDestination;

import java.util.Set;

public interface NetworkDelivery {
    void registerComponent(String id, ComponentType type, Component component);
    Set<Component> getComponents(ComponentType type);
    void deliverMessage(SourceDestination id, Message m);
}
