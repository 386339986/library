package cn.plutonight.library.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StudentDto {

    @ApiModelProperty(value = "学生Id", required = true)
    public Long id;

    @ApiModelProperty(value = "学校Id", required = true)
    public Long schoolId;

    @ApiModelProperty(value = "学校名称", required = true)
    public String schoolName;

    @ApiModelProperty(value = "学生姓名", required = true)
    public String userName;

    @ApiModelProperty(value = "电话号码", required = true)
    public String phone;

    @ApiModelProperty(value = "学号", required = true)
    public String idNumber;

}
