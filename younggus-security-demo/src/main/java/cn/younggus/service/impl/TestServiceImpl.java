package cn.younggus.service.impl;

import cn.younggus.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @author Glenn.Zheng
 * @date 2018/5/8 22:06
 */
@Service
public class TestServiceImpl implements TestService {

    @Override
    public String greeting(String name) {
        System.out.println("greeting");
        return "hello " + name;
    }
}
