package com.bald.uriah.baldphone.contact_providers;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.text.TextUtils;

import java.util.LinkedHashSet;
import java.util.Set;

public class AccountService {
    private static final Set<String> _packageNames = new LinkedHashSet<String>(){{
        add(WhatsAppAccountId.PACKAGE_NAME);
    }};

    private static final Set<String> _mimeTypes = new LinkedHashSet<String>(){{
        addAll(WhatsAppAccountId.ACCOUNTTYPE_BY_MIMETYPE.keySet());
    }};

    public static Set<AccountId> getAccountIdsByName(ContentResolver contentResolver, String contactName){
        Set<AccountId> accIds = new LinkedHashSet<>();
        try (final Cursor cIdCursor = queryContactId(contentResolver, contactName)) {
            String rawContactId, rawAccountId, mimeType;
            AccountId accountId;
            while (cIdCursor.moveToNext()) {
                rawContactId = cIdCursor.getString(cIdCursor.getColumnIndex(ContactsContract.RawContacts._ID));
                try (Cursor aIdCursor = queryAccountIdByRawContactId(contentResolver,  rawContactId)) {
                    while (aIdCursor.moveToNext()) {
                        rawAccountId = aIdCursor.getString(aIdCursor.getColumnIndex(ContactsContract.Data.DATA3));
                        mimeType = aIdCursor.getString(aIdCursor.getColumnIndex(ContactsContract.Data.MIMETYPE));
                        if (TextUtils.isEmpty(rawAccountId))
                            continue;
                        accountId = fromTypeAndId(rawAccountId, mimeType);
                        if(accountId != null)
                            accIds.add(accountId);
                    }
                }
            }
        }
        return  accIds;
    }

    private static Cursor queryContactId(ContentResolver contentResolver, String contactName){
        return contentResolver.query(
                ContactsContract.RawContacts.CONTENT_URI,
                new String[]{ContactsContract.RawContacts._ID},
                ContactsContract.RawContacts.ACCOUNT_TYPE + " IN " + "("
                        + String.join(",",_packageNames) + ") AND "
                        + ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME_PRIMARY + " = ?",
                new String[]{contactName},
                null);
    }

    private static Cursor queryAccountIdByRawContactId(ContentResolver contentResolver, String rawContactId){
        return contentResolver.query(ContactsContract.Data.CONTENT_URI,
                new String[]{ContactsContract.Data.DATA3, ContactsContract.Data.MIMETYPE},
                ContactsContract.Data.MIMETYPE + " IN " + "("
                        + String.join(",", _mimeTypes) + ") AND "
                        + ContactsContract.Data.RAW_CONTACT_ID + " = ? ",
                new String[]{rawContactId}, null);
    }

    private static AccountId fromTypeAndId(String accountId, String mimeType){
        if(mimeType != null && WhatsAppAccountId.ACCOUNTTYPE_BY_MIMETYPE.containsKey(mimeType)){
            return new WhatsAppAccountId(accountId, mimeType);
        }
        return null;
    }
}
