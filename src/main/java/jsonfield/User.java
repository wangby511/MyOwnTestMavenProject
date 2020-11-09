package jsonfield;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import lombok.Data;

//@JSONType(includes = {""})
@Data
@JSONType(includes = {"name", "hireDate"}, ignores = {"age"})
public class User {

    private String name;

    private int age;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createDate;

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public Date getHireDate() {
//        return hireDate;
//    }
//
//    public void setHireDate(Date hireDate) {
//        this.hireDate = hireDate;
//    }

    public User(String name, int age, Date hireDate) {
        this.name = name;
        this.age = age;
        this.createDate = hireDate;
    }

//    public User() {
//
//    }
//    常用的几个注解：
//    @Data ： 注在类上，提供类的get、set、equals、hashCode、canEqual、toString方法
//    @AllArgsConstructor ： 注在类上，提供类的全参构造
//    @NoArgsConstructor ： 注在类上，提供类的无参构造
//    @Setter ： 注在属性上，提供 set 方法
//    @Getter ： 注在属性上，提供 get 方法
//    @EqualsAndHashCode ： 注在类上，提供对应的 equals 和 hashCode 方法
//    @Log4j/@Slf4j ： 注在类上，提供对应的 Logger 对象，变量名为 log
//
//    链接：https://www.jianshu.com/p/c1ee7e4247bf
}
