package com.bald.uriah.baldphone.contact_providers;

import android.content.Intent;

import java.util.Objects;

public abstract class AccountId {
    private final String accountId;
    private final AccountInfo accountInfo;
    private final int hashCode;

    AccountId(String accountId, AccountInfo accountInfo) {
        this.accountId = accountId;
        this.accountInfo = accountInfo;
        hashCode = Objects.hash(accountId, accountInfo);
    }

    public String getAccountId() {
        return accountId;
    }

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public abstract Intent getConversationIntent();

    public abstract Intent prepareIntent(Intent intent);

//    @Override
//    public int hashCode() {
//        return hashCode;
//    }
}
