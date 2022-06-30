package io.github.signalwirecommunity.endpoints;

import io.github.signalwirecommunity.model.account.Account;
import io.github.signalwirecommunity.model.account.AccountResponse;

public interface AccountInterface {

    AccountResponse getAccounts();

    AccountResponse getAccountByName(String friendlyName);

    Account getAccountBySid(String sid);

}
