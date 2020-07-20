package map_struct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 一鸣
 * @date 2020/5/14 11:49
 */
public class StudentDTO {

    private Long id;

    private String name;

    private Integer age;

    private String desc;

    public StudentDTO () {

    }

    public StudentDTO(Long id, String name, Integer age, String desc) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.desc = desc;
    }
    public Integer getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public String getName() {
        return name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
