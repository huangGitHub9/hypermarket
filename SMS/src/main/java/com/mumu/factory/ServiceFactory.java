package com.mumu.factory;

import com.mumu.service.IMemberService;
import com.mumu.service.impl.IMemberServiceImpl;


/**
 * @author Huangxulin
 * @date 2019/9/4 - 19:49
 */
public class ServiceFactory {
    public static IMemberService getIMemberServiceIntance() {
        return new IMemberServiceImpl();
    }
}
