package org.xelvias.logan.handlers;

public interface Handler<T> {
    void setNextInChain(Handler<T> handler);
    void handle(T request);
}
