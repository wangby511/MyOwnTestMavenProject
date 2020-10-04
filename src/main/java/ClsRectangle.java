import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ClsRectangle extends ClsShape {
    private Integer width;
    private Integer height;
}