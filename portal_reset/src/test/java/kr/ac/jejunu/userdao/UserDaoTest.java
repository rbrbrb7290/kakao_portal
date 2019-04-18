package kr.ac.jejunu.userdao;

import com.sun.tools.javac.code.Attribute;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class UserDaoTest {
    private UserDao userDao;
    @Before
    public void setup(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = applicationContext.getBean("userDao", UserDao.class);
    }
    @Test
    public void get() throws SQLException, ClassNotFoundException {

        Long id = 1L;
        String name = "오현규";
        String password = "1234";

        User user = userDao.get(id);
        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(password, user.getPassword());
    }

    @Test
    public void add() throws SQLException, ClassNotFoundException {
        //UserDao userDao = new UserDao(new JejuConnectionMaker());
        User user = new User();

        String name = "헐크";
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
    public void Update() throws SQLException, ClassNotFoundException {
        //UserDao userDao = new UserDao(new JejuConnectionMaker());
        User user = new User();

        String name = "헐크";
        String password = "1234";

        user.setName(name);
        user.setPassword(password);

        Long id = userDao.add(user);
        user.setId(id);

        String changeName = "그루트";
        String changePassword = "1234";
        user.setName(changeName);
        user.setPassword(changePassword);

        userDao.Update(user);

        User resultUser = userDao.get(id);
        assertThat(resultUser.getId(), is(id));
        assertThat(resultUser.getName(), is(changeName));
        assertThat(resultUser.getPassword(), is(changePassword));
    }

    @Test
    public void Delete() throws SQLException, ClassNotFoundException {
        //UserDao userDao = new UserDao(new JejuConnectionMaker());
        User user = new User();
        String name = "헐크";
        String password = "1234";
        user.setName(name);
        user.setPassword(password);
        Long id = userDao.add(user);

        userDao.Delete(id);

        user = userDao.get(id);
        assertThat(user, nullValue());
    }


}

