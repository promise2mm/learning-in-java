package map_struct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author 一鸣
 * @date 2020/5/14 11:52
 */
@Mapper
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mappings(value = {
        @Mapping(source = "name", target = "studentName"),
        @Mapping(source = "desc", target = "studentInfo")
    })
    StudentVO convert(StudentDTO studentDTO);

}
