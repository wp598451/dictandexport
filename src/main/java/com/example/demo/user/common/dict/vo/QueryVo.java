package com.example.demo.user.common.dict.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class QueryVo implements Serializable {
    private static final long serialVersionUID = -2056118369076205750L;

    private String mb;
}
