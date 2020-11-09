package shape;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ClsCircle extends ClsShape {
    Integer radius;    //弧度
}