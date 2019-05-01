package kr.ac.jejunu.userdao;

import org.junit.Test;

import java.sql.*;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserDaoTest {

    public void setup() {

    }

    //입력한 id에 속한 name ,password Data가 일치하는지 확인하는 test
    @Test
    public void get() throws SQLException, ClassNotFoundException {

        Long id = 1l;
        String name = "오현규";
        String password = "1234";
        UserDao userDao = new JejuUserDao();

        User user = userDao.get(id);
        //db를 연결해서 저장한 데이터가 일치하는지 확인 (assertEquals(id user.getId())도 가능)
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void add() throws SQLException , ClassNotFoundException{

        User user = new User();
        UserDao userDao = new JejuUserDao();

        String name = "ㅎㅇㅎㅇ";
        String password = "1234";

        user.setName(name);
        user.setPassword(password);
        Long id = userDao.add(user);

        User resultUser = userDao.get(id);
        assertThat(resultUser.getId(), is(id));
        assertThat(resultUser.getName(), is(name));
        assertThat(resultUser.getPassword(), is(password));

    }

    @Test
    public void getHalla() throws SQLException, ClassNotFoundException {

        Long id = 1l;
        String name = "허윤호";
        String password = "1234";
        UserDao userDao = new HallaUserDao();

        User user = userDao.get(id);
        //db를 연결해서 저장한 데이터가 일치하는지 확인 (assertEquals(id user.getId())도 가능)
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));

    }

    @Test
    public void addHalla() throws SQLException , ClassNotFoundException{

        User user = new User();
        UserDao userDao = new JejuUserDao();

        String name = "ㅎㅇㅎㅇ";
        String password = "1234";

        user.setName(name);
        user.setPassword(password);
        Long id = userDao.add(user);

        User resultUser = userDao.get(id);
        assertThat(resultUser.getId(), is(id));
        assertThat(resultUser.getName(), is(name));
        assertThat(resultUser.getPassword(), is(password));

    }

}
