
package com.MResendizProgramacionNCapas.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class TokenUseService {

    private final Map<String, Integer> useMap = new ConcurrentHashMap<>()  ;
    
    public int incrementUse(String jti){
        return useMap.merge(jti, 1, Integer:: sum);
    }
    
    
}
