package com.ggue;

import org.junit.Test;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


//
public class UserdataTest {
    @Test
    public void testGet() throws SQLException, ClassNotFoundException {
        Long id = 1l;
        String name = "허윤호";
        String password = "1234";

        UserDao userDao = new UserDao();

        User user = userDao.get(id); //실제 값이 가정한 값이랑 같은지
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void testAdd() throws SQLException, ClassNotFoundException {
        String name = "헐크";
        String password = "1111";

        User user = new User();
        UserDao UserDao = new UserDao();

        user.setName(name);
        user.setPassword(password);
        Long id = UserDao.add(user);

        User resultUser = UserDao.get(id);
        assertThat(resultUser.getId(), is(id));
        assertThat(resultUser.getName(), is(name));
        assertThat(resultUser.getPassword(), is(password));
    }
}
