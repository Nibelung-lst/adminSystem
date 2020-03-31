<template>
  <div>
    <!--导航栏-->
    <el-row :gutter="12">
      <el-col :span="24">
        <el-card shadow="always">
          <el-row :gutter="12">
            <el-col :span="4">
              <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/' }">库存</el-breadcrumb-item>
                <el-breadcrumb-item>库存流水</el-breadcrumb-item>
              </el-breadcrumb>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <!--搜索栏-->
    <el-row :gutter="12" style="margin-top: 20px">
      <el-col :span="24">
        <el-card shadow="always">
          <el-row :gutter="12">
            <el-col :span="4">
              <el-input
                clearable
                placeholder="请输入spu名称"
                prefix-icon="el-icon-search"
                v-model="queryData.spuName">
              </el-input>
            </el-col>
            <el-col :span="4">
              <el-input
                clearable
                placeholder="请输入sku 名称"
                prefix-icon="el-icon-search"
                v-model="queryData.skuName">
              </el-input>
            </el-col>
            <el-col :span="4">
              <el-input
                clearable
                placeholder="请输入编码"
                prefix-icon="el-icon-search"
                v-model="queryData.code">
              </el-input>
            </el-col>
            <!--来源-->
            <el-col :span="4">
              <el-select v-model="queryData.source" clearable placeholder="请选择来源">
                <el-option
                  v-for="item in sources"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-col>
            <el-col :offset='3' :span="1">
              <el-button @click="handleSearch" type="primary">查询</el-button>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <!--数据表格-->
    <el-row :gutter="12" style="margin-top: 20px">
      <el-col :span="24">
        <el-table
          :data="tableData"
          border
          style="width: 100%"
          tooltip-effect="dark">
          <!--序号-->
          <el-table-column
            align="center"
            label="序号">
            <template slot-scope="scope">{{ scope.row.id }}</template>
          </el-table-column>
          <!--spuName-->
          <el-table-column
            align="center"
            label="spu 名称">
            <template slot-scope="scope">{{ scope.row.spuName }}</template>
          </el-table-column>
          <!--skuName-->
          <el-table-column
            align="center"
            label="sku 名称">
            <template slot-scope="scope">{{ scope.row.skuName }}</template>
          </el-table-column>
          <!--code-->
          <el-table-column
            align="center"
            label="编码">
            <template slot-scope="scope">{{ scope.row.code }}</template>
          </el-table-column>
          <!--modifyStocks-->
          <el-table-column
            align="center"
            label="改变量">
            <template slot-scope="scope">{{ scope.row.modifyStocks }}</template>
          </el-table-column>
          <!--spotStocks-->
          <el-table-column
            align="center"
            label="库存">
            <template slot-scope="scope">{{ scope.row.spotStocks }}</template>
          </el-table-column>
          <!--source-->
          <el-table-column
            align="center"
            label="流水来源">
            <template slot-scope="scope">{{ scope.row.source ===1?'完成订单':scope.row.source ===2?'取消订单':scope.row.source ===3?'商品同步': '商品添加' }}</template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
    <!--分页-->
    <el-row :gutter="12" style="margin-top: 20px">
      <el-col :span="24">
        <el-card shadow="always">
          <el-row :gutter="12">
            <el-col :offset="10" :span="12">
              <div class="block">
                <el-pagination
                  :current-page=currentPage
                  :page-size=pageSize
                  :page-sizes="[1, 5, 10, 15, 20, 30]"
                  :total=totalRecord
                  @current-change="handleCurrentChange"
                  @size-change="handleSizeChange"
                  background
                  layout="total, sizes, prev, pager, next, jumper">
                </el-pagination>
              </div>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {getFlowList} from "../../api/stock";
export default {
  data: function () {
    return {

      sources:[
        {
          value: '1',
          label: '完成订单'
        },
        {
          value: '2',
          label: '取消订单'
        },
        {
          value: '3',
          label: '商品同步'
        },
        {
          value: '4',
          label: '商品补货'
        }
      ],
      // 搜索栏
      queryData: {
        spuName: '',
        skuName: '',
        code:'',
        source:'',
        pageNum: 1,
        pageSize: 5
      },
      // 表格数据
      tableData: [
        {
          id: '',
          spuName: '',
          skuName: '',
          code:'',
          modifyStocks: '',
          spotStocks: '',
          source:''
        }
      ],
      // 每页几条数据
      pageSize: 5,
      // 总页数
      totalRecord: 0,
      // 当前页
      currentPage: 1
    }

  },
  mounted () {
    this.queryData.pageNum = this.currentPage;
    this.queryData.pageSize = this.pageSize;
   this.getFlowInfo();
  },

  methods:{
    // 每页几条数据变化
    handleSizeChange (val) {
      this.currentPage = 1;
      this.pageSize = val;
      this.getFlowInfo()
    },
    handleCurrentChange(val){
      this.currentPage = val;
      this.getFlowInfo();
    },
    handleSearch(){
      this.queryData.pageNum = 1;
      this.getFlowInfo();
    },
    getFlowInfo(){
      this.queryData.pageSize = this.pageSize;
      this.queryData.pageNum = this.currentPage;
      getFlowList(this.queryData).then(res => {
        if (res.code === 0) {
          this.tableData = res.data.recordList;
          this.totalRecord = res.data.totalRecord;
        } else {
          this.$message.error('获取库存流水失败!')
        }
      })
    }


  }

}
</script>

<style scoped>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }

  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }

  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }

  .el-upload--picture-card {
    background-color: #fbfdff;
    border: 1px dashed #c0ccda;
    border-radius: 6px;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    width: 178px;
    height: 178px;
    line-height: 176px;
    vertical-align: top;
  }
</style>
