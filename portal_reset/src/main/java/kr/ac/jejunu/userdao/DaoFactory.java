package kr.ac.jejunu.userdao;

public class DaoFactory {
    public UserDao getJejuDao() {

        return new UserDao(new JejuConnectionMaker());
    }

    public UserDao getHallaDao() {

        return new UserDao(new HallaConnectionMaker());
    }
}
