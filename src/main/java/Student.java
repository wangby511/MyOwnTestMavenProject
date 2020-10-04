import com.alibaba.fastjson.annotation.JSONType;
import lombok.*;

@Data
@JSONType(ignores = {"studentAge"})
public class Student {
    private String studentName;
    private Integer studentAge;
    private String studentGender;

    @Override
    public String toString() {
        return "Student{" +
                "studentName='" + studentName + '\'' +
                ", studentAge='" + studentAge + '\'' +
                ", studentGender='" + studentGender + '\'' +
                '}';
    }
}

