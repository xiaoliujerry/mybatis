package com.jiangnan.designpattern.processor;

public class Step1 implements Processor {

    private Processor next;

    public Step1(Processor processor) {
        this.next = processor;
    }

    @Override
    public void handle() {
        System.out.println("step1 complete!");
        if (next != null) {
            next.handle();
        }
    }
}
