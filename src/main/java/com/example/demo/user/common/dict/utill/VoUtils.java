package com.example.demo.user.common.dict.utill;

import java.lang.reflect.Field;

public class VoUtils {

    public static boolean checkObjFieldIsNotNull(Object obj) {   // true 不为空  false 为空
        boolean flag = false;
        try {
            for (Field f : obj.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if (!f.getName().equals("serialVersionUID")) {
                    if (f.get(obj) == null || f.get(obj) == "") {
                    } else {
                        flag = true;
                    }
                }
            }
        } catch (Exception e) {
            return false;
        }
        return flag;
    }
}
