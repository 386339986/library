package cn.plutonight.library.entity;

import java.io.Serializable;
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
@ApiModel(value="School对象", description="")
public class School implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @ApiModelProperty(value = "学校校名")
    private String name;

    @ApiModelProperty(value = "学校所在省")
    private String province;

    @ApiModelProperty(value = "学校所在市")
    private String city;

    @ApiModelProperty(value = "使用规则")
    private String usageRole;

    @ApiModelProperty(value = "预约规则")
    private String appointmentRule;

    @ApiModelProperty(value = "违规规则")
    private String violationRule;

    @ApiModelProperty(value = "黑名单规则")
    private String blacklistRules;

    @ApiModelProperty(value = "保留规则")
    private String retentionRule;


}
