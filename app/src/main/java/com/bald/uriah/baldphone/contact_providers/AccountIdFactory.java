package com.bald.uriah.baldphone.contact_providers;

import java.util.HashMap;
import java.util.Map;

public class AccountIdFactory {
    private static final Map<AccountInfo, IAccountIdFactory> factoryMap = new HashMap<AccountInfo, IAccountIdFactory>() {{
        put(new AccountInfo(
                WhatsAppAccountId.PACKAGE_NAME,
                WhatsAppAccountId.PROFILE_MIMETYPE,
                AccountType.MESSAGE
        ), WhatsAppAccountId::new);
    }};

    public static AccountId getIdFromInfo(String accountId, AccountInfo accountInfo) {
        IAccountIdFactory factory = factoryMap.get(accountInfo);
        if (factory != null)
            return factory.create(accountId, accountInfo);
        return null;
    }

    private interface IAccountIdFactory {
        AccountId create(String accountId, AccountInfo accountInfo);
    }
}
