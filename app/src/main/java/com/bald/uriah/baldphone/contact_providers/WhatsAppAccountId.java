package com.bald.uriah.baldphone.contact_providers;

import android.content.ComponentName;
import android.content.Intent;

import com.bald.uriah.baldphone.utils.CommonTypeUtils;
import com.bald.uriah.baldphone.utils.D;

import java.util.HashMap;
import java.util.Map;

public class WhatsAppAccountId extends AccountId {
    public static final String PACKAGE_NAME = D.WHATSAPP_PACKAGE_NAME;
    public static final String PROFILE_MIMETYPE = D.WHATSAPP_PROFILE_MIMETYPE;
//    public static final Map<String, AccountType> ACCOUNTTYPE_BY_MIMETYPE = new HashMap<String, AccountType>(){{
//        put(D.WHATSAPP_PROFILE_MIMETYPE, AccountType.MESSAGE);
//    }};
//    public static final Map<AccountType, String> MIMETYPE_BY_ACCOUNTTYPE = CommonTypeUtils.invertMap(ACCOUNTTYPE_BY_MIMETYPE);

    WhatsAppAccountId(String accountId, AccountInfo accountInfo) {
        super(fixRawId(accountId), accountInfo);
    }

    private static String fixRawId(String str){
        if (str.startsWith("Message"))
            str = str.replace("Message", "");
        return str;
    }

    @Override
    public Intent getConversationIntent() {
        Intent intent = new Intent("android.intent.activate.MAIN");
        intent.setComponent(new ComponentName(getAccountInfo().getPackageName(),
                getAccountInfo().getPackageName() + ".Conversation"));
        prepareIntent(intent);
        return intent;
    }

    @Override
    public Intent prepareIntent(Intent intent) {
        intent.putExtra("jid", getSpecificId(getAccountId()));
        return intent;
    }

    private String getSpecificId(String id){
        return id.replaceAll("[^0123456789]", "") + "@s.whatsapp.net";
    }
}
