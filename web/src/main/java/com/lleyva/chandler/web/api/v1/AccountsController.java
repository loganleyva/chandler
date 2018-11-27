package com.lleyva.chandler.web.api.v1;

import com.lleyva.chandler.data.Account;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/accounts")
public class AccountsController extends ApiV1CrudControllerBase<Account> { }
