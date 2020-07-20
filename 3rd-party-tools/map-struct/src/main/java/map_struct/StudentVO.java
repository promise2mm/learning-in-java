package map_struct;

import lombok.Data;
import org.mapstruct.Mapper;

/**
 * @author 一鸣
 * @date 2020/5/14 11:49
 */
@Data
public class StudentVO {

    private Long id;

    private String studentName;

    private Integer age;

    private String studentInfo;

}
