
package com.MResendizProgramacionNCapas.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class TokenUseService {

    private final Map<String, Integer> useMap = new ConcurrentHashMap<>()  ;
    
    private static final int MAX_USE = 5;
    
    public boolean incrementUse(String jti){
        int use = useMap.merge(jti, 1, Integer:: sum);
        
        return use <= MAX_USE;
    }
    
    
}
