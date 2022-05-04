package com.jiangnan.designpattern.handle;

import java.util.ArrayList;
import java.util.List;

public class HandleAdapter {

    private List<Handle> handles = new ArrayList<>();

    public void register(Handle handle) {
        this.handles.add(handle);
    }

    public void handle() {
        handles.forEach(Handle::handle);
    }
}
