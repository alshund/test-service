package com.heapix.service.service;

import com.heapix.service.service.bo.AccountBo;
import com.heapix.service.service.bo.CustomerBo;
import com.heapix.service.service.bo.InitialsBo;

import java.util.List;

public interface CustomerService {
    List<InitialsBo> getAllInitials();
    CustomerBo getById(Long id);
    AccountBo getAccount(Long id);
}
