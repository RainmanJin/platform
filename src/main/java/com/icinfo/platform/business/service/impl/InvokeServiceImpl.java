package com.icinfo.platform.business.service.impl;

import com.icinfo.platform.business.base.Resolver.MessageRouter;
import com.icinfo.platform.business.service.IInvokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/30.
 */
@Service
public class InvokeServiceImpl implements IInvokeService {

    @Autowired
    private MessageRouter messageRouter;

    @Override
    public Object doProcess(Map<String, Object> map) throws Exception {

        return messageRouter.router(map);
    }
}
