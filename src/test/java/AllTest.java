import org.junit.jupiter.api.Test;

import shape.*;
import jsonfield.*;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;


public class AllTest {
    @Test
    public void testJSON2Object() throws IOException {
        ClsRectangle rectangle = new ClsRectangle(7,9); //构建正方形对象
        ClsCircle circle = new ClsCircle(8); //构建长方形对象
        List<ClsShape> shapes = new ArrayList<>();  //List<多种形状>
        shapes.add(circle);
        shapes.add(rectangle);
        ClsView view = new ClsView();  //将List放入画面View
        view.setShapes(shapes);

        ObjectMapper mapper = new ObjectMapper();
        System.out.println("-- 序列化 --");
        String jsonStr = mapper.writeValueAsString(view);
        System.out.println(jsonStr);

        System.out.println("-- 反序列化 --");
        ClsView deserializeView = mapper.readValue(jsonStr, ClsView.class);
        System.out.println(deserializeView);

        //        值得注意的是在序列化之后的java字符串中，每个Json对象都包含了一个新的属性@class
        //        这也是该对象在继承关系下能够反序列化为正确的java对象(@class的值的类对象)的关键所在。
        //        https://zhuanlan.zhihu.com/p/259966759
    }

    @Test
    public void testUser() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("tom",20, new Date()));
        userList.add(new User("jack", 14, new Date()));

        String jsonString = JSON.toJSONString(userList,true);
        //true表示转换为带格式的json文本
        System.out.println(jsonString);
    }

    @Test
    public void testStudentJSON2Object() throws IOException {
        // http://www.yiidian.com/fastjson/fastjson-jsontype.html

        Student student = new Student();
        student.setStudentName("eric");
        student.setStudentGender("男");
        student.setStudentAge(20);

        String jsonString = JSON.toJSONString(student);
        System.out.println(jsonString);
    }

    @Test
    public void deserialize() {
        String jsonString = "{\"createDate\":\"2018-08-17 14:38:38\",\"age\":11,\"name\":\"西安\"}";
        User user = JSON.parseObject(jsonString, User.class);
        System.out.println(user.getName());
        System.out.println(user.getCreateDate());
        System.out.println(user.getAge());

        System.out.println(user.toString());
    }

    @Test
    public void testAAAaaa() {
        AAAaaa aa = new AAAaaa("xiaoming",20,"北京","英语老师","海淀小学","[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]",new Date());
//        System.out.println(aa);
        String s = JSONObject.toJSONString(aa);
        //测试序列化
        System.out.println(s);

//        //跟AAAaaa这个对象无关，所以跟@JSONField注解无关
        System.out.println(JSONObject.parseObject(s));

        //测试反序列化
        System.out.println(JSONObject.parseObject(s, AAAaaa.class));
    }
}
