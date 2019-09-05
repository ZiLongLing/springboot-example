package com.demo.springboot2.pojo;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Validated({GroupA.class})
public class User {
    @NotBlank(groups = {GroupA.class})
    @Length(min = 1, max = 5)
    private String id;
    @NotEmpty
    @Length(max = 10)
    @ApiModelProperty(value = "姓名")
    private String name;
    @NotNull
    @Min(1)
    @Max(99)
    private Integer age;
    @Valid
//    @NotNull
    private Student stu;

    public String getId() {
        return id == null ? null : id.trim();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Student getStu() {

        return stu;
    }

    public void setStu(Student stu) {
        this.stu = stu;
    }
}
