package com.ggue;

import javafx.application.Application;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    private UserDao userDao;

    @Before
    public void setup() {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(DaoFactory.class);

        userDao = ApplicationContext.getBean("userDao", UserDao.class);

    }
    @Test
    public void testGet() throws SQLException, ClassNotFoundException {
        Long id = 1l;
        String name = "허윤호";
        String password = "1234";
        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void testAdd() throws SQLException, ClassNotFoundException {
        String name = "헐크";
        String password = "1111";
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        Long id = userDao.add(user);
        User resultUser = userDao.get(id);
        assertThat(resultUser.getId(), is(id));
        assertThat(resultUser.getName(), is(name));
        assertThat(resultUser.getPassword(), is(password));
    }

    @Test
    public void testHallaGet() throws SQLException, ClassNotFoundException {
        Long id = 1l;
        String name = "허윤호";
        String password = "1234";
        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

}
