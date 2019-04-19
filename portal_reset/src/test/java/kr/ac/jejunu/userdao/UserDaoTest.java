package kr.ac.jejunu.userdao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.sql.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;


public class UserDaoTest {
    private UserDao userDao;
    @Before
    public void setup() {
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
    public void add() throws SQLException, ClassNotFoundException {
        User user = new User();
        String name = "kakao";
        String passowrd = "2222";

        user.setName(name);
        user.setPassword(passowrd);

        Long id = userDao.add(user);

        User resultUser = userDao.get(id);
        assertThat(resultUser.getId() , is(id));
        assertThat(resultUser.getName(), is(name));
        assertThat(resultUser.getPassword(), is(passowrd));
    }

    @Test
    public void Update() throws SQLException, ClassNotFoundException {
        User user = new User();
        String name = "kakao";
        String passowrd = "2222";
        user.setName(name);
        user.setPassword(passowrd);
        Long id = userDao.add(user);

        user.setId(id);

        String changeName = "kakao";
        String changePassword = "1234";
        user.setName(changeName);
        user.setPassword(changePassword);

        userDao.Update(user);

        User resultUser = userDao.get(id);
        assertThat(resultUser.getId() , is(id));
        assertThat(resultUser.getName(), is(changeName));
        assertThat(resultUser.getPassword(), is(changePassword));
    }

    @Test
    public void Delete() throws SQLException, ClassNotFoundException {
        User user = new User();
        String name = "kakao";
        String passowrd = "2222";
        user.setName(name);
        user.setPassword(passowrd);
        Long id = userDao.add(user);


        userDao.Delete(id);

        user = userDao.get(id);
        assertThat(user , nullValue());
    }

}

