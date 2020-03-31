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
                <el-breadcrumb-item>库存列表</el-breadcrumb-item>
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
                placeholder="请输入spu 名称"
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
                placeholder="请输入库存"
                prefix-icon="el-icon-search"
                v-model="queryData.spotStocks">
              </el-input>
            </el-col>
            <el-col :span="4">
              <el-input
                clearable
                placeholder="请输入库存警戒值"
                prefix-icon="el-icon-search"
                v-model="queryData.minStocks">
              </el-input>
            </el-col>
            <el-col :offset='3' :span="1"> <!-- v-if="arrTemp.includes('STOCK_SEARCH_STOCK')"-->
              <el-button  type="primary" @click="handleSearch">查询</el-button> <!--@click="handleSearch" -->
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
          <!--spu名称-->
          <el-table-column
            align="center"
            label="spu 名称">
            <template slot-scope="scope">{{ scope.row.spuName }}</template>
          </el-table-column>
          <!--sku名称-->
          <el-table-column
            align="center"
            label="sku 名称">
            <template slot-scope="scope">{{ scope.row.skuName }}</template>
          </el-table-column>
          <!--库存-->
          <el-table-column
            align="center"
            label="可用库存">
            <template slot-scope="scope">{{ scope.row.spotStocks }}</template>
          </el-table-column>
          <!--警戒线-->
          <el-table-column
            align="center"
            label="库存警戒线">
            <template slot-scope="scope">{{ scope.row.minStocks }}</template>
          </el-table-column>
          <!--编辑-->
          <el-table-column
            v-if="arrTemp.includes('STOCK_UPDATE_STOCK')"
            align="center"
            label="编辑" >
            <template slot-scope="scope">
              <el-button   type="primary" icon="el-icon-edit" circle @click="editStock(scope.row)" ></el-button> <!--@click="updateStock(scope.row)"-->
            </template>
          </el-table-column>
          <!--补货-->
          <el-table-column
            v-if="arrTemp.includes('STOCK_FINISH_STOCK')"
            align="center"
            label="补货" >
            <template slot-scope="scope">
              <el-button  type="primary" icon="el-icon-setting" circle @click="openAddForm(scope.row)" ></el-button> <!--@click="updateStock(scope.row)"-->
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
    <!--编辑库存信息的对话框-->
    <el-dialog title="库存信息" :visible.sync="dialogFormVisible">
      <el-form :model="updateForm">
        <el-form-item label="spu名称" :label-width="formLabelWidth">
          <el-input v-model="updateForm.spuName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="sku名称" :label-width="formLabelWidth">
          <el-input v-model="updateForm.skuName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="可用库存" :label-width="formLabelWidth">
          <el-input v-model="updateForm.spotStocks" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="库存警戒线" :label-width="formLabelWidth">
          <el-input v-model="updateForm.minStocks" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="subStock(updateForm)" >确 定</el-button>
      </div>
    </el-dialog>
    <!--补货操作-->
    <el-dialog title="补货" :visible.sync="dialogForAddStock">
      <el-form :model="addForm">
        <el-form-item label="spu名称" :label-width="formLabelWidth">
          <el-input v-model="addForm.spuName" :disabled="true" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="sku名称" :label-width="formLabelWidth">
          <el-input v-model="addForm.skuName" :disabled="true"  autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="进货量" :label-width="formLabelWidth">
          <el-input v-model="addForm.modifyStocks" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogForAddStock = false">取 消</el-button>
        <el-button type="primary" @click="subAddStock(addForm)" >确 定</el-button> <!--@click="subStock(updateForm)"-->
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
import {getDynamicStockList, getStockList, updateStock,addStockByAdmin} from '../api/stock'

export default {
  data: function () {
    return {
      // 搜索栏
      queryData: {
        spuName: '',
        skuName: '',
        spotStocks:'',
        minStocks:'',
        pageNum: 1,
        pageSize: 5
      },
      // 表格数据
      tableData:[
        {
          id: '',
          spuCode: '',
          spuName: '',
          skuCode: '',
          skuName: '',
          spotStocks: '',
          minStocks: ''
        }
      ] ,
      // 添加库存操作
      addForm:{
        id:'',
        spuCode: '',
        spuName: '',
        skuCode: '',
        skuName: '',
        modifyStocks:'',
        spotStocks:''
      },
      // 弹出框
      dialogFormVisible: false,
      // 添加库存
      dialogForAddStock:false,
      // 编辑库存数据
      updateForm: {
        spuName: '',
        skuName: '',
        spotStocks: '',
        minStocks: ''
      },
      // 样式大小
      formLabelWidth: '120px',
      // 每页几条数据
      pageSize: 5,
      // 总条数
      totalRecord: 0,
      // 当前页
      currentPage: 1,
      // 权限按钮
      arrTemp:[]
    }
  },
  mounted () {
    this.getStockInfo();
    this.handleShowButton();
  },
  methods:{
    // 按钮权限标识
    handleShowButton(){
      this.arrTemp = localStorage.getItem("ButtonList");
      // console.log(this.arrTemp);
    },
    // 整数校验
    checkNum(num){
      if(!/^\d+$/.test(num)){
        this.$message.error("请输入正整数");
        return;
      }
    },
    // 每页几条数据变化
    handleSizeChange (size) {
      this.pageSize = size;
      this.currentPage = 1;
      this.getStockInfo();
    },
    //  页码变化
    handleCurrentChange(val){
      this.currentPage = val;
      this.getStockInfo();
    },
    // 动态查询
    handleSearch(){
      this.queryData.pageNum = 1;
      this.queryData.pageSize = 5;
      this.getStockInfo();
    },

    /*
    * 编辑
    * */
    editStock(stock){
      this.dialogFormVisible = true;
      this.updateForm = stock;
      this.tempObject = stock;
    },
    subAddStock(){
      this.dialogForAddStock=false;
      if(!/^\d+$/.test(this.addForm.modifyStocks)){
        this.$message.error("请输入正整数");
        return;
      }
      addStockByAdmin(this.addForm).then(res => {
        if (res.code === 0) {
          this.$message.info('操作成功!')
        } else {
          this.$message.error('操作失败!')
        }
      }).catch((err) => {
        this.$message.error(err)
      })
    },

    /*
    * 更新库存
    * */
    subStock(stock){
      this.dialogFormVisible = false;
      if(!/^\d+$/.test(stock.spotStocks)){
        this.$message.error("请输入正整数");
        return;
      }
      if(!/^\d+$/.test(stock.minStocks)){
        this.$message.error("请输入正整数");
        return;
      }
      updateStock(this.updateForm).then(res => {
        if (res.code === 0) {
          this.$message.info('更新库存成功!')
        } else {
          this.$message.error('更新库存失败!')
        }
      }).catch((err) => {
        this.$message.error(err)
      })
    },

    openAddForm(stock){
      this.dialogForAddStock = true;
      this.addForm = stock;
    },

    /*
    * 后台获取数据
    * */
    getStockInfo(){
      this.queryData.pageSize = this.pageSize;
      this.queryData.pageNum = this.currentPage;
      getDynamicStockList(this.queryData).then(res => {
        if (res.code === 0) {
          this.tableData = res.data.recordList;
          this.totalRecord = res.data.totalRecord;
        } else {
          this.$message.error('获取库存失败!')
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
