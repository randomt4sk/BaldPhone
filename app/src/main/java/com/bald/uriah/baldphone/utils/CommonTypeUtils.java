package com.bald.uriah.baldphone.utils;

import java.util.HashMap;
import java.util.Map;

public class CommonTypeUtils {
    public static <K,V> Map<V, K> invertMap(Map<K, V> toInvert) {
        Map<V, K> result = new HashMap<V, K>();
        for(K k: toInvert.keySet()){
            result.put(toInvert.get(k), k);
        }
        return result;
    }

//    public static boolean isNullOrEmptyString(String str){
//        return str == null || str.isEmpty();
//    }
}
