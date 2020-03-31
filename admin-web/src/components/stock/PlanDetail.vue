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
                <el-breadcrumb-item>自动订货单</el-breadcrumb-item>
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
           <!--sku名称-->
            <el-col :span="4">
              <el-input
                clearable
                placeholder="请输入sku 名称"
                prefix-icon="el-icon-search"
                v-model="queryData.skuName">
              </el-input>
            </el-col>
            <!--方案名称-->
            <el-col :span="4">
              <el-input
                clearable
                placeholder="请输入方案名称"
                prefix-icon="el-icon-search"
                v-model="queryData.planName">
              </el-input>
            </el-col>
            <!--状态-->
          <!--  <el-col :span="4">
              <el-input
                clearable
                placeholder="状态"
                prefix-icon="el-icon-search"
                v-model="queryData.status">
              </el-input>
            </el-col>
            &lt;!&ndash;类型&ndash;&gt;
            <el-col :span="4">
              <el-input
                clearable
                placeholder="类型"
                prefix-icon="el-icon-search"
                v-model="queryData.type">
              </el-input>
            </el-col>-->
            <!--状态-->
            <el-col :span="4">
              <el-select v-model="queryData.status" clearable placeholder="请选择状态">
                <el-option
                  v-for="item in statuses"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-col>
            <!--类型-->
            <el-col :span="4">
              <el-select v-model="queryData.type" clearable placeholder="请选择类型">
                <el-option
                  v-for="item in types"
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
          <!--planName-->
          <el-table-column
            align="center"
            label="方案名称">
            <template slot-scope="scope">{{ scope.row.planName }}</template>
          </el-table-column>
          <!--数量-->
          <el-table-column
            align="center"
            label="数量">
            <template slot-scope="scope">{{ scope.row.amount }}</template>
          </el-table-column>
          <!--状态-->
          <el-table-column
            align="center"
            label="状态">
            <template slot-scope="scope">{{ scope.row.status ===0?'未完成':'已完成' }}</template>
          </el-table-column>
          <!--类型-->
          <el-table-column
            align="center"
            label="类型">
            <template slot-scope="scope">{{ scope.row.type===0?'自动订货':'管理员补货' }}</template>
          </el-table-column>
          <!--编辑-->
          <el-table-column
            v-if="arrTemp.includes('STOCK_UPDATE_DETAIL')"
            align="center"
            label="编辑" >
            <template slot-scope="scope">
              <el-button type="primary" icon="el-icon-edit" circle @click="editDetail(scope.row)"></el-button> <!--@click="editStock(scope.row)" -->
            </template>
          </el-table-column>
          <!--完成补货-->
          <el-table-column
            v-if="arrTemp.includes('STOCK_FINISH_DETAIL')"
            align="center"
            label="补货" >
            <template slot-scope="scope">
              <el-button  type="primary" icon="el-icon-success" circle @click="completeDetail(scope.row)" ></el-button> <!--@click="openAddForm(scope.row)"-->
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
    <!--添加补货数量-->
    <el-dialog title="更新数量" :visible.sync="dialogForUpdate">
      <el-form :model="updateAmount">
        <el-form-item label="spu名称" :label-width="formLabelWidth">
          <el-input v-model="updateAmount.spuName" :disabled="true" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="sku名称" :label-width="formLabelWidth">
          <el-input v-model="updateAmount.skuName" :disabled="true" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="进货量" :label-width="formLabelWidth">
          <el-input v-model="updateAmount.amount" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitDetail" >确 定</el-button>
      </div>
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
import { getAllPlanDetailList , updateDetail,completeDetails} from '../../api/stock'
export default {
  data: function () {
    return {
      statuses:[
        {
          value: '0',
          label: '未完成'
        },
        {
          value: '1',
          label: '已完成'
        }
      ],
      types:[
        {
          value: '0',
          label: '自动补货'
        },
        {
          value: '1',
          label: '管理员补货'
        }
      ],
      // 搜索栏
      queryData: {
        skuName: '',
        planName:'',
        status:'',
        type:'',
        pageSize:5,
        pageNum:1
      },
      // 表格数据
      tableData: [
        {
          id: '',
          spuName: '',
          skuName: '',
          planName: '',
          amount: '',
          status:'',
          type:''
        }
      ],
      // 更新数量
      updateAmount:{
        id: '',
        amount: '',
      },
      // 明细细节
      comDetail:{
        id: '',
        spuCode:'',
        spuName:'',
        skuCode: '',
        skuName: '',
        amount: '',
      },
      // 样式大小
      formLabelWidth: '120px',
      // 更新补货量的开关
      dialogForUpdate:false,
      // 每页几条数据
      pageSize: 5,
      // 总页数
      totalRecord: 0,
      // 当前页
      currentPage: 1,
      // 按钮权限
      arrTemp:[]
    }
  },
  methods:{
    // 按钮权限标识
    handleShowButton(){
      this.arrTemp = localStorage.getItem("ButtonList");
      // console.log(this.arrTemp);
    },

    // 每页几条数据变化
    handleSizeChange (val) {
      this.pageSize = val;
      this.currentPage = 1;
      this.getPlanDetailInfo();
    },
    handleCurrentChange(val){
      this.currentPage = val;
      this.getPlanDetailInfo();
    },
    // 动态查询
    handleSearch(){
      this.queryData.pageNum = 1;
      this.getPlanDetailInfo();
    },

    // 将值出入苒弹框
     editDetail(detail){
      if (detail.status!=1){
        this.dialogForUpdate = true;
        this.updateAmount = detail;
      }else{
        this.$message.error('该方案明细已经完成，不可再修改!')
      }


     },

    // 更新补货量
    submitDetail(){
      this.dialogForUpdate = false;
      // 后台接口
      if(!/^\d+$/.test(this.updateAmount.amount)){
        this.$message.error("请输入正整数");
        return;
      }
      updateDetail(this.updateAmount).then(res => {
        if (res.code === 0) {
          this.$message.info('更新成功!')
        } else {
          this.$message.error('更新失败，请稍后再试!')
        }
      })
    },
    // 完成补货操作
    completeDetail(comDetail){
      // 如果添加量不存在或者不合法，则不能进行该操作
      if (comDetail.status==1) {
        this.$message.error('该方案明细已经完成，不可重复完成!')
        return;
      }
      if (comDetail.amount<=0) {
        this.$message.error('补货数量为正整数!')
        return;
      }
      // 后台接口
      this.comDetail = comDetail;
      completeDetails(this.comDetail).then(res => {
        if (res.code === 0) {
          this.$message.info('操作成功!')
        } else {
          this.$message.error('操作失败，请稍后再试!')
        }
      })
    },

   /**
   * 后台获取数据
   * */
    getPlanDetailInfo(){
      this.queryData.pageNum = this.currentPage;
      this.queryData.pageSize = this.pageSize;
      getAllPlanDetailList(this.queryData).then(res => {
        if (res.code === 0) {
          this.tableData = res.data.recordList;
          this.totalRecord = res.data.totalRecord;
        } else {
          this.$message.error('获取方案明细失败!')
        }
      })
    }
  },
  mounted(){
    this.getPlanDetailInfo();
    this.handleShowButton();
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
