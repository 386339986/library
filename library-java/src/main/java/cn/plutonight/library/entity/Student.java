package cn.plutonight.library.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 学生信息
 * </p>
 *
 * @author LPH
 * @since 2020-10-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Student对象", description="学生信息")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学生id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "学生姓名")
    private String name;

    @ApiModelProperty(value = "学生学号")
    private String number;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "状态：1 正常 2 停用 3 黑名单")
    private Integer status;

    @ApiModelProperty(value = "违规次数")
    private Integer violationTime;

    @ApiModelProperty(value = "学校id")
    private Long schoolId;


}
