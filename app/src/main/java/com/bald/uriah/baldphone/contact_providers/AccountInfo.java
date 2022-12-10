package com.bald.uriah.baldphone.contact_providers;

import java.util.Objects;

public class AccountInfo {
    private final String packageName;
    private final String mimeType;
    private final AccountType accountType;
    private final int hashCode;
    //MIME TYPE DESCRIPTION FOR TRANSLATIONS
    //MIME TYPE ICON/S
    //MIME TYPE LAYOUT

    public AccountInfo(String packageName, String mimeType, AccountType accountType) {
        this.packageName = packageName;
        this.mimeType = mimeType;
        this.accountType = accountType;
        hashCode = Objects.hash(packageName, mimeType, packageName, accountType);
    }

    @Override
    public boolean equals(Object object) {
        return (object instanceof AccountInfo) && hashCode() == object.hashCode();
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getMimeType() {
        return mimeType;
    }

    public AccountType getAccountType() {
        return accountType;
    }
}
