package map_struct;

import com.alibaba.fastjson.JSON;

/**
 * @author 一鸣
 * @date 2020/5/14 11:48
 */
public class MapStructTest {

    public static void main(String[] args) {
        StudentDTO studentDTO = new StudentDTO(1L, "Jack", 18, "desc");
        StudentVO vo = StudentMapper.INSTANCE.convert(studentDTO);
        System.out.println(JSON.toJSONString(vo));
    }

}
