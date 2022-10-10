package com.example.demo.user.sjmsDemo.spqms.test.impl;

import com.example.demo.user.sjmsDemo.spqms.test.ThreeSocket;
import com.example.demo.user.sjmsDemo.spqms.test.TwoSocket;

public class ThreeSocketImpl implements ThreeSocket {

    TwoSocket twoSocket;

    public ThreeSocketImpl(TwoSocket twoSocket) {
        this.twoSocket = twoSocket;
    }

    @Override
    public void threeSocket() {
        twoSocket.twoSocket();
    }
}
