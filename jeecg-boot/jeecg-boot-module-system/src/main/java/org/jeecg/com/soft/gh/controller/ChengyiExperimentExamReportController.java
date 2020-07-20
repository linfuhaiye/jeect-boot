package org.jeecg.com.soft.gh.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.jeecg.com.soft.gh.entity.ChengyiExperimentExamReport;
import org.jeecg.com.soft.gh.service.IChengyiExperimentExamReportService;
import org.jeecg.com.soft.gh.vo.ChengyiExperimentExamReportPage;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 诚毅学院实验考试报告
 * @Author: jeecg-boot
 * @Date: 2020-06-19
 * @Version: V1.0
 */
@Api(tags = "诚毅学院实验考试报告")
@RestController
@RequestMapping("/com.soft.gh/chengyiExperimentExamReport")
@Slf4j
public class ChengyiExperimentExamReportController {
    @Autowired
    private IChengyiExperimentExamReportService chengyiExperimentExamReportService;

    /**
     * 分页列表查询
     *
     * @param chengyiExperimentExamReport
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "诚毅学院实验考试报告-分页列表查询")
    @ApiOperation(value = "诚毅学院实验考试报告-分页列表查询", notes = "诚毅学院实验考试报告-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(ChengyiExperimentExamReport chengyiExperimentExamReport,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<ChengyiExperimentExamReport> queryWrapper = QueryGenerator.initQueryWrapper(chengyiExperimentExamReport, req.getParameterMap());
        Page<ChengyiExperimentExamReport> page = new Page<ChengyiExperimentExamReport>(pageNo, pageSize);
        IPage<ChengyiExperimentExamReport> pageList = chengyiExperimentExamReportService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param chengyiExperimentExamReportPage
     * @return
     */
    @AutoLog(value = "诚毅学院实验考试报告-添加")
    @ApiOperation(value = "诚毅学院实验考试报告-添加", notes = "诚毅学院实验考试报告-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody ChengyiExperimentExamReportPage chengyiExperimentExamReportPage) {
        ChengyiExperimentExamReport chengyiExperimentExamReport = new ChengyiExperimentExamReport();
        BeanUtils.copyProperties(chengyiExperimentExamReportPage, chengyiExperimentExamReport);
        chengyiExperimentExamReportService.saveMain(chengyiExperimentExamReport);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param chengyiExperimentExamReportPage
     * @return
     */
    @AutoLog(value = "诚毅学院实验考试报告-编辑")
    @ApiOperation(value = "诚毅学院实验考试报告-编辑", notes = "诚毅学院实验考试报告-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody ChengyiExperimentExamReportPage chengyiExperimentExamReportPage) {
        ChengyiExperimentExamReport chengyiExperimentExamReport = new ChengyiExperimentExamReport();
        BeanUtils.copyProperties(chengyiExperimentExamReportPage, chengyiExperimentExamReport);
        ChengyiExperimentExamReport chengyiExperimentExamReportEntity = chengyiExperimentExamReportService.getById(chengyiExperimentExamReport.getId());
        if (chengyiExperimentExamReportEntity == null) {
            return Result.error("未找到对应数据");
        }
        chengyiExperimentExamReportService.updateMain(chengyiExperimentExamReport);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "诚毅学院实验考试报告-通过id删除")
    @ApiOperation(value = "诚毅学院实验考试报告-通过id删除", notes = "诚毅学院实验考试报告-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        chengyiExperimentExamReportService.delMain(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "诚毅学院实验考试报告-批量删除")
    @ApiOperation(value = "诚毅学院实验考试报告-批量删除", notes = "诚毅学院实验考试报告-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.chengyiExperimentExamReportService.delBatchMain(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "诚毅学院实验考试报告-通过id查询")
    @ApiOperation(value = "诚毅学院实验考试报告-通过id查询", notes = "诚毅学院实验考试报告-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        ChengyiExperimentExamReport chengyiExperimentExamReport = chengyiExperimentExamReportService.getById(id);
        if (chengyiExperimentExamReport == null) {
            return Result.error("未找到对应数据");
        }
        return Result.ok(chengyiExperimentExamReport);

    }


    /**
     * 导出excel
     *
     * @param request
     * @param chengyiExperimentExamReport
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ChengyiExperimentExamReport chengyiExperimentExamReport) {
        // Step.1 组装查询条件查询数据
        QueryWrapper<ChengyiExperimentExamReport> queryWrapper = QueryGenerator.initQueryWrapper(chengyiExperimentExamReport, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        //Step.2 获取导出数据
        List<ChengyiExperimentExamReport> queryList = chengyiExperimentExamReportService.list(queryWrapper);
        // 过滤选中数据
        String selections = request.getParameter("selections");
        List<ChengyiExperimentExamReport> chengyiExperimentExamReportList = new ArrayList<ChengyiExperimentExamReport>();
        if (oConvertUtils.isEmpty(selections)) {
            chengyiExperimentExamReportList = queryList;
        } else {
            List<String> selectionList = Arrays.asList(selections.split(","));
            chengyiExperimentExamReportList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
        }

        // Step.3 组装pageList
        List<ChengyiExperimentExamReportPage> pageList = new ArrayList<ChengyiExperimentExamReportPage>();
        for (ChengyiExperimentExamReport main : chengyiExperimentExamReportList) {
            ChengyiExperimentExamReportPage vo = new ChengyiExperimentExamReportPage();
            BeanUtils.copyProperties(main, vo);
            pageList.add(vo);
        }

        // Step.4 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, "诚毅学院实验考试报告列表");
        mv.addObject(NormalExcelConstants.CLASS, ChengyiExperimentExamReportPage.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("诚毅学院实验考试报告数据", "导出人:" + sysUser.getRealname(), "诚毅学院实验考试报告"));
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
        return mv;
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setNeedSave(true);
            try {
                List<ChengyiExperimentExamReportPage> list = ExcelImportUtil.importExcel(file.getInputStream(), ChengyiExperimentExamReportPage.class, params);
                for (ChengyiExperimentExamReportPage page : list) {
                    ChengyiExperimentExamReport po = new ChengyiExperimentExamReport();
                    BeanUtils.copyProperties(page, po);
                    chengyiExperimentExamReportService.saveMain(po);
                }
                return Result.ok("文件导入成功！数据行数:" + list.size());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return Result.error("文件导入失败:" + e.getMessage());
            } finally {
                try {
                    file.getInputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Result.ok("文件导入失败！");
    }

}
