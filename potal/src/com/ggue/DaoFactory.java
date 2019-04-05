package com.ggue;


public class DaoFactory {
    public UserDao userDao() {
        return new UserDao(getConnectionMaker());
    }

    public ConnectionMaker getConnectionMaker() {
        return new JejuConnectionMaker();
    }
}

