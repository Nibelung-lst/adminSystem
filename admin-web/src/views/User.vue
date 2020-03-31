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
                <el-breadcrumb-item>用户列表</el-breadcrumb-item>
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
                v-model="searchData.userName">
              </el-input>
            </el-col>
            <el-col :span="4">
              <el-select :span="4" v-model="searchData.sex" clearable placeholder="请选择性别" value="">
                <el-option
                  v-for="item in sex"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-col>
            <el-col :span="4">
              <el-select :span="4" v-model="searchData.status" clearable placeholder="请选择状态" value="">
                <el-option
                  v-for="item in status"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-col>
            <el-col :span="4">
              <el-select :span="4" v-model="searchData.levelName" clearable placeholder="请选择等级" value="">
                <el-option
                  v-for="item in levelName"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-col>
            <el-col :offset='3' :span="1">
              <el-button v-if="arr.includes('USER_SEARCH_USER')" @click="handleSearchUser(),searchData.pageNum=1" type="primary">查询</el-button>
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
            label="头像"
            width="100">
            <template slot-scope="scope">
              <el-image
                :src="scope.row.headImgPath"
                :fit="'fit'"
                style="width: 60px;">
                <div class="image-slot" slot="error">
                  <el-image :fit="'fit'"
                            :src="scope.row.headImgPath"
                            style="width: 60px;">
                  </el-image>
                </div>
              </el-image>
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            label="用户名">
            <template slot-scope="scope">{{ scope.row.userName }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="昵称">
            <template slot-scope="scope">{{ scope.row.nickName }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="等级">
            <template slot-scope="scope">{{ scope.row.levelName }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="性别">
            <template slot-scope="scope">
              <i :class="scope.row.sex === 1?'el-icon-male':'el-icon-female'"></i>
              {{ scope.row.sex === 1 ? '男':'女'}}
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            label="总积分">
            <template slot-scope="scope">{{ scope.row.points }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="总消费">
            <template slot-scope="scope">{{ scope.row.totalConsumption }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="生日">
            <template slot-scope="scope">{{ scope.row.birthday }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="当前状态"
            prop="status">
            <template slot-scope="scope">
              <el-button :type="scope.row.status===1?'primary':'danger'"
                         plain size="small" v-if="arr.includes('USER_CHANGE_STATUS')"
                         @click="handleUpdateStatus(scope.row)">
                {{ scope.row.status === 1 ? '正常' : '已禁用' }}
              </el-button>
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            label="操作">
            <template slot-scope="">
              <el-button type="primary" plain size="small">查看详情</el-button>
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
                  :page-sizes="[5]"
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
import { changeStatus, searchUser } from '../api/user'

export default {
  created () {
    this.handleSearchUser()
    this.handleShowButton()
  },
  mounted () {

  },
  data () {
    return {
    arr:[],
      // 搜索栏
      searchData: {
        userName: '',
        sex: '',
        status: '',
        levelName: '',
        pageNum: 1,
        pageSize: 5
      },
      name: '',
      sex: [
        {
          value: 1,
          label: '男'
        },
        {
          value: 2,
          label: '女'
        }
      ],
      status: [
        {
          value: 1,
          label: '正常'
        },
        {
          value: 2,
          label: '禁用'
        }
      ],
      levelName: [
        {
          value: '青铜',
          label: '青铜'
        },
        {
          value: '白银',
          label: '白银'
        },
        {
          value: '黄金',
          label: '黄金'
        },
        {
          value: '钻石',
          label: '钻石'
        },
        {
          value: '星耀',
          label: '星耀'
        }
      ],
      // 数据
      tableData: [
        {
          id: '1',
          levelId: 1,
          levelName: '青铜',
          userName: '12323434556',
          nickName: '传说中的勇者',
          headImgPath: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
          status: 1,
          sex: 1,
          birthday: '2019-9-12',
          points: '122',
          totalConsumption: '1000.00',
          createTime: '2019-10-23',
          updateTime: '2020-12-09'
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
  handleShowButton(){
        this.arr =  localStorage.getItem("ButtonList");
      },
    // 每页几条数据变化
    handleSizeChange (val) {
      this.pageSize = val
    },
    // 当前页变化
    handleCurrentChange (val) {
      this.currentPage = val
      this.searchData.pageNum = this.currentPage
      this.searchData.pageSize = this.pageSize
      this.handleSearchUser()
    },
    // 修改用户状态
    handleUpdateStatus (row) {
      let params = {
        'userId': row.id,
        'status': row.status
      }
      changeStatus(params).then(res => {
        if (res.code === 0) {
          row.status = row.status === 1 ? 0 : 1
          this.$message.success('成功!')
        } else {
          this.$message.success('失败!')
        }
      })
    },
    // 初始化数据
    // 模糊查询用户信息
    handleSearchUser () {
      console.log(this.searchData)
      searchUser(this.searchData).then(res => {
        if (res.code === 0) {
          this.$message.success('成功')
          this.totalPage = res.data.count
          this.tableData = res.data.content
          console.log(res.data)
        } else {
          this.$message.error('失败')
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
