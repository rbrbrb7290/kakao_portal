package kr.ac.jejunu.userdao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.*;


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserDaoTest {
    private UserDao userDao;
    @Before
    public void setup(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = applicationContext.getBean("userDao", UserDao.class);
    }


    //입력한 id에 속한 name ,password Data가 일치하는지 확인하는 test
    @Test
    public void get() throws SQLException, ClassNotFoundException {

        Long id = 1l;
        String name = "오현규";
        String password = "1234";

        User user = userDao.get(id);
        //db를 연결해서 저장한 데이터가 일치하는지 확인 (assertEquals(id user.getId())도 가능)
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void add() throws SQLException , ClassNotFoundException{

        User user = new User();
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
    public void update() throws SQLException, ClassNotFoundException {
        User user = new User();
        String name = "업데이트";
        String password = "9999";

        user.setName(name);
        user.setPassword(password);
        Long id = userDao.add(user);

        user.setId(id);

        String changeName = "9999";
        String changePassword = "업데이트";
        user.setName(changeName);
        user.setPassword(changePassword);

        userDao.update(user);
        User resultUser = userDao.get(id);
        assertThat(resultUser.getId(), is(id));
        assertThat(resultUser.getName(), is(changeName));
        assertThat(resultUser.getPassword(), is(changePassword));

    }

    @Test
    public void delete() throws SQLException, ClassNotFoundException {
        User user = new User();
        String name = "삭제";
        String password = "1111";

        user.setName(name);
        user.setPassword(password);
        Long id = userDao.add(user);

        userDao.delete(id);
        user = userDao.get(id);
        assertThat(user, nullValue());
    }

}
