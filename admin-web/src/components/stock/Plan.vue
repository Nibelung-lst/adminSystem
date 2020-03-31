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
                <el-breadcrumb-item>订货方案</el-breadcrumb-item>
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
                placeholder="请输入方案 名称"
                prefix-icon="el-icon-search"
                v-model="queryData.planName">
              </el-input>
            </el-col>
            <el-col :span="4">
              <el-input
                clearable
                placeholder="请输入方案备注"
                prefix-icon="el-icon-search"
                v-model="queryData.planCommon">
              </el-input>
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
          <el-table-column
            align="center"
            label="序号">
            <template slot-scope="scope">{{ scope.row.id }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="方案编码">
            <template slot-scope="scope">{{ scope.row.planCode }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="方案名称">
            <template slot-scope="scope">{{ scope.row.planName }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="方案备注">
            <template slot-scope="scope">{{ scope.row.planCommon }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="详情" >
            <template slot-scope="scope">
              <el-button type="primary" icon="el-icon-s-data" round @click="getPlanDetail(scope.row)"></el-button> <!--@click="updateStock(scope.row)"--><!--@click="getPlanDetail(scope.row)"-->
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
    <!--明细弹窗-->
    <!-- Table -->
<el-dialog title="方案详情" :visible.sync="dialogTableVisible">
    <el-table :data="planDetail">
      <el-table-column property="spuName" label="spu" width="120"></el-table-column>
      <el-table-column property="skuName" label="sku" width="120"></el-table-column>
      <el-table-column property="amount" label="数量"width="120"></el-table-column>
      <el-table-column property="status" :formatter="forStatus" label="状态" width="120"></el-table-column>
      <el-table-column property="type" :formatter="forType " label="类型"width="120"></el-table-column>
    </el-table>
  </el-dialog>
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
import { getAllPlanList,getPlanDetailByPlanCode } from "../../api/stock";
export default {
  data: function () {
    return {
      // 搜索栏
      queryData: {
        planName:'',
        planCommon:'',
        pageNum: 1,
        pageSize:5
      },
      // 表格数据
      tableData: [
        {
          id:'',
          planCode: '',
          planName:'',
          planCommon:''
        }
      ],
      // 明细数据
      planDetail:{
        spuName: '',
        skuName: '',
        planName: '',
        status:'',
        type:''
      },
      //明细弹窗
      dialogTableVisible:false,
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
    this.getPlanInfo();
  },

  methods:{
    // 每页几条数据变化
    handleSizeChange (val) {
      this.pageSize = val;
      this.currentPage = 1;
      this.getPlanInfo();
    },
    handleCurrentChange(val){
      this.currentPage = val;
      this.getPlanInfo();
    },
    handleSearch(){
      this.queryData.pageNum = 1;
      this.getPlanInfo();
    },
    // 通过方案编码获取详情
    getPlanDetail(plan){
      getPlanDetailByPlanCode( plan.planCode).then(res => {
          if (res.code === 0) {
            this.dialogTableVisible=true;
            this.planDetail = res.data;
          } else {
            this.$message.error('获取方案明细为空!')
          }
        })
    },
    getPlanInfo(){
      this.queryData.pageSize = this.pageSize;
      this.queryData.pageNum = this.currentPage;
      getAllPlanList(this.queryData).then(res => {
        if (res.code === 0) {
          console.log(res.data)
          this.tableData = res.data.recordList;
          this.totalRecord = res.data.totalRecord;
        } else {
          this.$message.error('获取方案失败!')
        }
      })
    },
    forStatus(row,column){
      const status = row[column.property];
      return status===0?'未完成':'已完成';
    },
    forType(row,column){
      const type = row[column.property];
      return type===0?'自动订货':'管理员补货'
    },
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
