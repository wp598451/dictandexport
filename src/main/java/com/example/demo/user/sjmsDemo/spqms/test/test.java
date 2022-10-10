package com.example.demo.user.sjmsDemo.spqms.test;

import com.example.demo.user.sjmsDemo.spqms.test.impl.ThreeSocketImpl;
import com.example.demo.user.sjmsDemo.spqms.test.impl.TwoSocketImpl;

import java.util.Observer;

/**
 * 适配器模式：
 *      场景：现有二插插口，一个三插插口，需要在三插插口插入二插插头
 */

public class test {

    public static void main(String[] args) {

        TwoSocket twoSocket = new TwoSocketImpl();

        ThreeSocket threeSocket = new ThreeSocketImpl(twoSocket);
        threeSocket.threeSocket();
    }
}
