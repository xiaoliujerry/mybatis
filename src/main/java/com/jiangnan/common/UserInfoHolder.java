package com.jiangnan.common;

public class UserInfoHolder {

    private static final ThreadLocal<LoginInfo> LOGIN_INFO = new ThreadLocal<>();

    public static void setLoginInfo(LoginInfo loginInfo) {
        LOGIN_INFO.set(loginInfo);
    }

    public static LoginInfo getLoginInfo() {
        return LOGIN_INFO.get();
    }

    public static void remove() {
        LOGIN_INFO.remove();
    }
}
