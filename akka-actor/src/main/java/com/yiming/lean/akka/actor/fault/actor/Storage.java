package com.yiming.lean.akka.actor.fault.actor;

import com.alibaba.fastjson.JSON;
import lombok.Data;

@Data
public class Storage {

    public static final Boolean[] arr = new Boolean[]{false, false, false, false, false};

    @Override
    public String toString() {
        return JSON.toJSONString(arr);
    }
}
