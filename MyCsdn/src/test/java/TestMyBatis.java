import com.linbinghui.csdn.dao.UserMapper;
import com.linbinghui.csdn.entity.User;
import com.linbinghui.csdn.util.io.Resources;
import com.linbinghui.csdn.util.session.SqlSession;
import com.linbinghui.csdn.util.session.SqlSessionFactory;
import com.linbinghui.csdn.util.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class TestMyBatis {
    UserMapper userMapper = null;
    SqlSession sqlSession = null;

    @Before
    public void init() throws Exception {


        //从xml中构建SqlSessionFactory
        String resource = "mybatis-config.xml";
        //加载MyBatis的配置文件，返回字节流
        InputStream inputStream = Resources.GetResourceAsStream(resource);
        System.out.println(inputStream);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);

    }


    @Test
    public void test1() {
        System.out.println("测试！");
        List<User> users = userMapper.list();
        for (User user : users) {
            System.out.println(user);
        }
    }
}