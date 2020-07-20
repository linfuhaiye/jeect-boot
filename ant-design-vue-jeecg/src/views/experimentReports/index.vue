<template>
    <a-card :bordered="false">
        <!-- 查询区域 -->
        <div class="table-page-search-wrapper">
            <a-form layout="inline" @keyup.enter.native="searchQuery">
                <a-row :gutter="24">
                    <a-col :xl="6" :lg="7" :md="8" :sm="24">
                        <a-form-item label="实验名称">
                            <a-input placeholder="请输入实验名称" v-model="queryParam.name"></a-input>
                        </a-form-item>
                    </a-col>
                    <a-col :xl="6" :lg="7" :md="8" :sm="24">
                        <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
                            <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
                            <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
                        </span>
                    </a-col>
                </a-row>
            </a-form>
        </div>
        <!-- 查询区域-END -->

        <!-- table区域-begin -->
        <div>
            <a-table
                ref="table"
                size="middle"
                bordered
                rowKey="id"
                :columns="columns"
                :dataSource="dataSource"
                :pagination="ipagination"
                :loading="loading"
                :rowSelection="{ selectedRowKeys: selectedRowKeys, onChange: onSelectChange }"
                @change="handleTableChange"
            >
                <span slot="action" slot-scope="text, record" style="width:250px">
                    <a @click="checkResult(record)">查看</a>
                </span>
            </a-table>
        </div>
    </a-card>
</template>

<script>
import { JeecgListMixin } from '@/mixins/JeecgListMixin';
import { filterMultiDictText } from '@/components/dict/JDictSelectUtil';

export default {
    name: 'ChengyiExperimentExamReportList',
    mixins: [JeecgListMixin],
    components: { },
    data() {
        return {
            description: '诚毅学院实验考试报告管理页面',
            // 表头
            columns: [
                {
                    title: '#',
                    dataIndex: '',
                    key: 'rowIndex',
                    width: 60,
                    align: 'center',
                    customRender: function(t, r, index) {
                        return parseInt(index) + 1;
                    }
                },
                {
                    title: '实验名称',
                    align: 'center',
                    dataIndex: 'name'
                },
                {
                    title: '得分',
                    align: 'center',
                    dataIndex: 'score'
                },
                {
                    title: '完成时间',
                    align: 'center',
                    dataIndex: 'finishTime',
                    customRender: function(text) {
                        return !text ? '' : text.length > 10 ? text.substr(0, 10) : text;
                    }
                },
                {
                    title: '操作',
                    dataIndex: 'action',
                    align: 'center',
                    scopedSlots: { customRender: 'action' }
                }
            ],
            url: {
                list: '/com.soft.gh/chengyiExperimentExamReport/list',
                delete: '/com.soft.gh/chengyiExperimentExamReport/delete',
                deleteBatch: '/com.soft.gh/chengyiExperimentExamReport/deleteBatch',
                exportXlsUrl: '/com.soft.gh/chengyiExperimentExamReport/exportXls',
                importExcelUrl: 'com.soft.gh/chengyiExperimentExamReport/importExcel'
            },
            dictOptions: {},
            userInfo: {},
            stepEntity: {},
            visible: false
        };
    },
    computed: {
        importExcelUrl: function() {
            return `${process.env.VUE_APP_BASE_API}/${this.url.importExcelUrl}`;
        }
    },
    created() {
        console.log("===============")
    },
    mounted() {
        window.addEventListener('message', this.receiveMessageFromIframe, false);
    },
    methods: {
        receiveMessageFromIframe(event) {
            console.log("event:",event)
            if (event.data.type == 'getUserInfo') {
                this.userInfo = event.data.data
                this.initDictConfig();
            }
        },
        initDictConfig() {
            if (this.userInfo.username === 'admin') {
                this.queryParam.userId = null;
                this.searchQuery();
            } else {
                this.queryParam.userId = this.userInfo.id;
                this.searchQuery();
            }
        },
        checkResult(val) {
            console.log('查看报告', val);
            this.visible = true;
            this.stepEntity = JSON.parse(val.answers);
            this.stepEntity.checkFlag = true;
        },
        _handleCancel() {
            this.visible = false;
        }
    }
};
</script>
<style scoped>
@import '~@assets/less/common.less';
.ant-input[disabled] {
    color: #827c70;
}
</style>
