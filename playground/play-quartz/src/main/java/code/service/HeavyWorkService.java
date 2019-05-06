package code.service;

import org.springframework.stereotype.Service;

@Service
public class HeavyWorkService {

    public void work(String caller) {
        System.out.println("doing heavy work for: " + caller);
    }
}
