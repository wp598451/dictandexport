package com.example.demo.user.common.dict.vo;

import lombok.experimental.Accessors;

import java.io.Serializable;

@Accessors(chain = true)
public class QueryVo implements Serializable {
    private static final long serialVersionUID = -2056118369076205750L;

    private String mb;

    public String getMb() {
        return mb;
    }

    public void setMb(String mb) {
        this.mb = mb;
    }
}
