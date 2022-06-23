package io.github.olajhidey.endpoints;

import io.github.olajhidey.model.account.Account;
import io.github.olajhidey.model.account.AccountResponse;

public interface AccountInterface {

    AccountResponse getAccounts();

    AccountResponse getAccountByName(String friendlyName);

    Account getAccountBySid(String sid);

}
