package org.jeecg.com.soft.gh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.com.soft.gh.entity.ChengyiExperimentExamReport;
import org.jeecg.com.soft.gh.mapper.ChengyiExperimentExamReportMapper;
import org.jeecg.com.soft.gh.service.IChengyiExperimentExamReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;

/**
 * @Description: 诚毅学院实验考试报告
 * @Author: jeecg-boot
 * @Date: 2020-06-19
 * @Version: V1.0
 */
@Service
public class ChengyiExperimentExamReportServiceImpl extends ServiceImpl<ChengyiExperimentExamReportMapper, ChengyiExperimentExamReport> implements IChengyiExperimentExamReportService {

    @Autowired
    private ChengyiExperimentExamReportMapper chengyiExperimentExamReportMapper;

    @Override
    @Transactional
    public void saveMain(ChengyiExperimentExamReport chengyiExperimentExamReport) {
        chengyiExperimentExamReportMapper.insert(chengyiExperimentExamReport);
    }

    @Override
    @Transactional
    public void updateMain(ChengyiExperimentExamReport chengyiExperimentExamReport) {
        chengyiExperimentExamReportMapper.updateById(chengyiExperimentExamReport);

        //1.先删除子表数据

        //2.子表数据重新插入
    }

    @Override
    @Transactional
    public void delMain(String id) {
        chengyiExperimentExamReportMapper.deleteById(id);
    }

    @Override
    @Transactional
    public void delBatchMain(Collection<? extends Serializable> idList) {
        for (Serializable id : idList) {
            chengyiExperimentExamReportMapper.deleteById(id);
        }
    }

}
