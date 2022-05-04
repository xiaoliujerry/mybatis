package com.jiangnan.designpattern.processor;

public class Step2 implements Processor {

    private Processor next;

    public Step2(Processor processor) {
        this.next = processor;
    }

    @Override
    public void handle() {
        System.out.println("step2 complete!");
        if (next != null) {
            next.handle();
        }
    }
}
