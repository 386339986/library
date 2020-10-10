package cn.plutonight.library.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author LPH
 * @since 2020-10-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "用户名称")
    private String name;

    @ApiModelProperty(value = "管理员/学生")
    private String password;

    @ApiModelProperty(value = "学生学号")
    private String studentNum;

    @ApiModelProperty(value = "用户电话")
    private String phone;

    @ApiModelProperty(value = "0: 普通用户;1: 管理员; 2: 超级管理员")
    private Integer role;

    @ApiModelProperty(value = "0: 停用; 1: 启用; -1: 黑名单")
    private Integer status;

    @ApiModelProperty(value = "默认0, 到3 自动转换黑名单")
    private Integer badRecord;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;


}
