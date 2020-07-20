package org.jeecg.com.soft.gh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.com.soft.gh.entity.ChengyiExperimentExamReport;

import java.io.Serializable;
import java.util.Collection;

/**
 * @Description: 诚毅学院实验考试报告
 * @Author: jeecg-boot
 * @Date: 2020-06-19
 * @Version: V1.0
 */
public interface IChengyiExperimentExamReportService extends IService<ChengyiExperimentExamReport> {

    /**
     * 添加一对多
     */
    public void saveMain(ChengyiExperimentExamReport chengyiExperimentExamReport);

    /**
     * 修改一对多
     */
    public void updateMain(ChengyiExperimentExamReport chengyiExperimentExamReport);

    /**
     * 删除一对多
     */
    public void delMain(String id);

    /**
     * 批量删除一对多
     */
    public void delBatchMain(Collection<? extends Serializable> idList);

}
