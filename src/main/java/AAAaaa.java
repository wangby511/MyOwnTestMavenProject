import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;

import java.util.Date;

@Data
public class AAAaaa {

    @JSONField(name = "userName", ordinal = 2)
    private String name;

//    @JSONField(name = "userAge", ordinal = 5)
    private Integer age;

    private String address;

    @JSONField(name = "userWork", ordinal = 1, serialize = false)
    private String work;

    @JSONField(name = "userSchool", ordinal = 4, deserialize = false)
    private String school;

    @JSONField(name = "normalJson", ordinal = 4, jsonDirect = true)
    private String studentjson;
    //yyyy年MM月dd日 HH时mm分ss秒

    @JSONField(ordinal = 3, name = "time", format = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JSONField(name = "userAge")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @JSONField(name = "userAddress")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getStudentjson() {
        return studentjson;
    }

    public void setStudentjson(String studentjson) {
        this.studentjson = studentjson;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public AAAaaa() {
    }

    public AAAaaa(String name, Integer age, String address, String work, String school, String studentjson, Date createtime) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.work = work;
        this.school = school;
        this.studentjson = studentjson;
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "AAAaaa{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", work='" + work + '\'' +
                ", school='" + school + '\'' +
                ", studentjson='" + studentjson + '\'' +
                ", createtime=" + createtime +
                '}';
    }
}