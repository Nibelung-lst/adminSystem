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
                <el-breadcrumb-item>积分规则</el-breadcrumb-item>
              </el-breadcrumb>
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
            label="类型名称">
            <template slot-scope="scope">{{ scope.row.typeName }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="改变积分数">
            <template slot-scope="scope">{{ scope.row.changeScore }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="每日上限积分">
            <template slot-scope="scope">{{ scope.row.pointsMax }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="操作">
            <template slot-scope="">
              <el-button type="primary" plain size="small">编辑</el-button>
            </template>
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
import { pointsType } from '../../api/user'

export default {
  created () {
    this.handlePointsType()
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
          typeName: '登录送积分',
          changeScore: '20',
          pointsMax: '1000'
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
      this.handlePointsType()
    },
    // 查询
    handleSearch () {
      console.log(this.searchData)
    },
    // 初始化数据
    handlePointsType () {
      let params = {
        'pageNum': this.currentPage,
        'pageSize': this.pageSize
      }
      pointsType(params).then(res => {
        if (res.code === 0) {
          this.tableData = res.data.content
          this.totalPage = res.data.count
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
