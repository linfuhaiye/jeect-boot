package org.jeecg.com.soft.gh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @Description: 诚毅学院实验考试报告
 * @Author: jeecg-boot
 * @Date: 2020-06-19
 * @Version: V1.0
 */
@ApiModel(value = "chengyi_experiment_exam_report对象", description = "诚毅学院实验考试报告")
@Data
@TableName("chengyi_experiment_exam_report")
public class ChengyiExperimentExamReport implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
    /**
     * 创建人
     */
    @Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
    private java.lang.String createUser;
    /**
     * 创建日期
     */
    @Excel(name = "创建日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
    /**
     * 更新人
     */
    @Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
    private java.lang.String lastModifiedUser;
    /**
     * 更新日期
     */
    @Excel(name = "更新日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date lastModifiedTime;
    /**
     * 学生ID
     */
    @Excel(name = "学生ID", width = 15, dictTable = "chengyi_sys_user", dicText = "name", dicCode = "id")
    @Dict(dictTable = "chengyi_sys_user", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "学生ID")
    private java.lang.String userId;
    /**
     * 实验名称
     */
    @Excel(name = "实验名称", width = 15)
    @ApiModelProperty(value = "实验名称")
    private java.lang.String name;
    /**
     * 学生答案
     */
    @Excel(name = "学生答案", width = 15)
    @ApiModelProperty(value = "学生答案")
    private java.lang.String answers;
    /**
     * 得分
     */
    @Excel(name = "得分", width = 15)
    @ApiModelProperty(value = "得分")
    private java.lang.String score;
    /**
     * 完成时间
     */
    @Excel(name = "完成时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "完成时间")
    private java.util.Date finishTime;
}
