<template>
  <div>
    <!--导航栏-->
    <el-row :gutter="12">
      <el-col :span="24">
        <el-card shadow="always">
          <el-row :gutter="12">
            <el-col :span="4">
              <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item>积分记录</el-breadcrumb-item>
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
                placeholder="请输入用户名"
                prefix-icon="el-icon-search"
                v-model="searchData.name">
              </el-input>
            </el-col>
            <el-col :offset='15' :span="1">
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
            label="id">
            <template slot-scope="scope">{{ scope.row.id }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="订单编号">
            <template slot-scope="scope">{{ scope.row.orderCode }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="用户名">
            <template slot-scope="scope">{{ scope.row.userName }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="消费类型">
            <template slot-scope="scope">{{ scope.row.consumeType }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="消费金额">
            <template slot-scope="scope">{{ scope.row.consumeMoney }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="结果码">
            <template slot-scope="scope">{{ scope.row.resultCode }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="创建时间">
            <template slot-scope="scope">{{ scope.row.createTime }}</template>
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
                  :total=totalPage
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

export default {
  created () {

  },
  mounted () {

  },
  data () {
    return {
      // 搜索栏
      searchData: {
        name: ''
      },
      name: '',
      // 数据
      tableData: [
        {
          id: '1',
          orderCode: '12340987',
          userName: '张育欣',
          consumeType: '2',
          consumeMoney: '1234',
          resultCode: '1',
          createTime: '2019-09-12'
        }
      ],
      // 每页几条数据
      pageSize: 5,
      // 总页数
      totalPage: 0,
      // 当前页
      currentPage: 1
    }
  },
  methods: {
    // 每页几条数据变化
    handleSizeChange (val) {
      this.pageSize = val
    },
    // 当前页变化
    handleCurrentChange (val) {
      this.currentPage = val
    },
    // 查询
    handleSearch () {
      console.log(this.searchData)
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
