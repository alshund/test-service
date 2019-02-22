package com.heapix.service.controller;

import com.heapix.service.controller.converter.CustomerCnConverter;
import com.heapix.service.controller.converter.InitialsCnConverter;
import com.heapix.service.controller.model.AccountPermission;
import com.heapix.service.service.CustomerService;
import com.heapix.service.service.bo.CustomerBo;
import com.heapix.service.service.bo.InitialsBo;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController extends BaseController {

    @Autowired
    private CustomerService customerService;

    private CustomerCnConverter converterCnv = Mappers.getMapper(CustomerCnConverter.class);
    private InitialsCnConverter initialsCnv = Mappers.getMapper(InitialsCnConverter.class);

    @GetMapping
    public ResponseEntity getInitials() {
        List<InitialsBo> initials = customerService.getAllInitials();
        return ok(initials.stream().map(i -> initialsCnv.toInitialsModel(i)));
    }

    @GetMapping("/{id}")
    public ResponseEntity getCustomer(@PathVariable String id) {
        AccountPermission permission = getPermission(id);
        CustomerBo customerBo = customerService.getById(permission.getId());

        if (permission.isSameUser()) {
            return ok(converterCnv.toCustomerModel(customerBo));
        } else {
            return ok(initialsCnv.toInitialsModel(customerBo.getInitials()));
        }
    }
}
