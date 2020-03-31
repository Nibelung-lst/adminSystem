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
                <el-breadcrumb-item>广告列表</el-breadcrumb-item>
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
          <el-row>
            <el-col>
              <el-form :inline="true" :model="queryParams" size="small" label-width="140px">
                <el-form-item label="广告名称：">
                  <el-input v-model="queryParams.name" class="input-width" placeholder="请输入名称"></el-input>
                </el-form-item>
                <el-form-item label="投放平台：">
                  <el-select v-model="queryParams.platform" placeholder="全部" clearable class="input-width">
                    <el-option v-for="item in platformOptions"
                               :key="item.value"
                               :label="item.label"
                               :value="item.value">
                    </el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="广告位置：">
                  <el-select v-model="queryParams.location" placeholder="全部" clearable class="input-width">
                    <el-option v-for="item in locationOptions"
                               :key="item.value"
                               :label="item.label"
                               :value="item.value">
                    </el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="广告状态：">
                  <el-select v-model="queryParams.status" placeholder="全部" clearable class="input-width">
                    <el-option v-for="item in statusOptions"
                               :key="item.value"
                               :label="item.label"
                               :value="item.value">
                    </el-option>
                  </el-select>
                </el-form-item>
                <el-form-item>
                  <el-button @click="handleSearch" type="primary">查询</el-button>
                </el-form-item>
              </el-form>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <!--数据表格-->
    <el-row :gutter="12" style="margin-top: 20px">
      <el-col :span="24">
        <el-table
          :data="advertisementList"
          style="width: 100%"
          tooltip-effect="dark">
          <el-table-column
            align="center"
            label="编号" width="100">
            <template slot-scope="scope">{{ scope.row.id }}</template>
          </el-table-column>
          <el-table-column label="广告名称" align="center" width="250">
            <template slot-scope="scope">{{scope.row.name}}</template>
          </el-table-column>
          <el-table-column label="投放平台" width="100" align="center">
            <template slot-scope="scope">{{scope.row.platform | formatPlatform}}</template>
          </el-table-column>
          <el-table-column label="广告位置" width="100" align="center">
            <template slot-scope="scope">{{scope.row.location | formatLocation}}</template>
          </el-table-column>
          <el-table-column label="广告图片" width="250" align="center">
            <template slot-scope="scope"><img style="height: 80px" :src="baseUrl+scope.row.image"></template>
          </el-table-column>
          <el-table-column label="广告链接" width="200" align="center">
            <template slot-scope="scope">{{scope.row.spuCode}}</template>
          </el-table-column>
          <el-table-column label="有效期" width="250" align="center">
            <template slot-scope="scope">{{scope.row.startTime |formatDate}}至{{scope.row.endTime |formatDate}}</template>
          </el-table-column>
          <el-table-column label="上下线" width="100" align="center">
            <template slot-scope="scope">{{scope.row.status | formatStatus}}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="操作">
            <template slot-scope="scope">
              <el-button type="primary" plain size="small" @click="handleEdit(scope.$index,scope.row)">编辑</el-button>
               <!-- <el-button type="danger" plain size="small" v-if="scope.row.status == 1" @click="handleChangeStatus(scope.$index,scope.row)">下线</el-button>
              <el-button type="danger" plain size="small" v-if="scope.row.status == 2" @click="handleChangeStatus(scope.$index,scope.row)">上线</el-button>-->
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
    <el-dialog>

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
                  :total=total
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
    <el-dialog title="编辑广告" :visible.sync="editDialogVisible" style="text-align: left">
      <el-form :model="editForm"
               :rules="rules"
               ref="couponFrom"
               label-width="150px"
               size="small">
        <el-form-item label="投放平台：">
          <el-select v-model="editForm.platform" collapse-tags>
            <el-option
              v-for="type in platformOptions"
              :key="type.value"
              :label="type.label"
              :value="type.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="广告位置：">
          <el-select v-model="editForm.location" collapse-tags>
            <el-option
              v-for="type in locationOptions"
              :key="type.value"
              :label="type.label"
              :value="type.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="名称：" prop="name">
          <el-input v-model="editForm.name" class="input-width"></el-input>
        </el-form-item>
        <el-form-item label="有效期：">
          <el-date-picker type="datetime" placeholder="选择日期" v-model="editForm.startTime" style="width: 150px"></el-date-picker>
          <span style="margin-left: 20px;margin-right: 20px">至</span>
          <el-date-picker type="datetime" placeholder="选择日期" v-model="editForm.endTime" style="width: 150px"></el-date-picker>
        </el-form-item>
        <el-form-item label="广告图片：" v-model="editForm.image">
          <img style="height: 80px" :src="baseUrl+editForm.image">
          <el-button type="text" style="margin-top:-30px" @click="changeImageVisible=true">修改图片</el-button>
        </el-form-item>
        <el-form-item label="排序：" prop = "sort">
          <el-input v-model="editForm.sort" class="input-width"></el-input>
        </el-form-item>
        <el-form-item label="广告链接：" prop="url">
          <el-input v-model="editForm.spuCode" class="input-width"></el-input>
        </el-form-item>
        <el-form-item label="是否上线" >
          <el-radio-group v-model="editForm.status">
            <el-radio :label="1">是</el-radio>
            <el-radio :label="2">否</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注：">
          <el-input
            class="input-width"
            type="textarea"
            :rows="5"
            placeholder="请输入内容"
            v-model="editForm.note">
          </el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" style="text-align: center">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleSaveEdit()">保存修改</el-button>
      </div>
    </el-dialog>
    <el-dialog title="点击修改图片" :visible.sync="changeImageVisible">
      <el-upload
        class="avatar-uploader"
        action="http://adminweb.luckinshop.com:8080/adminback/uploadImage"
        :show-file-list="false"
        :on-success="handleImageSuccess"
        :on-preview="handlePreview"
        :before-upload="beforeImageUpload"
        v-model="editForm.image">
        <img v-if="editForm.image" :src="baseUrl+editForm.image" class="avatar" style="height: 80px">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
    </el-dialog>
  </div>
</template>

<script>
import { getAdvertisementList,changeAdvertisementStatus,editAdvertisement } from '@/api/marketing'
import { formatDate } from '@/utils/date'

export default {
  created () {
    this.handleGetAdvertisementList()
  },
  mounted () {
  },
  filters: {
    formatLocation (location) {
      if (location === 1) {
        return '首页轮播'
      } else if (location === 2) {
        return '左侧'
      } else if (location === 3){
        return '右侧'
      }else{
          return '底部'
      }
    },
      formatPlatform (platform) {
          if (platform === 1) {
              return '小程序'
          } else if (platform === 2) {
              return 'PC'
          } else {
              return 'APP'
          }
      },
    formatDate (time) {
      if (time == null || time === '') {
        return '-'
      }
      let date = new Date(time)
      return formatDate(date, 'yyyy-MM-dd mm:ss')
    },
    formatStatus (status) {
      if (status === 1) {
        return '已上线'
      } else {
        return '下线'
      }
    },
    formatExpire (endTime) {
      let now = new Date().getTime()
      let endDate = new Date(endTime)
      if (endDate > now) {
        return '未过期'
      } else {
        return '已过期'
      }
    }
  },
  data () {
    return {
        arr:[],
        baseUrl:'http://10.104.131.160/',
      locationOptions: [
        {
          label: '首页轮播',
          value: 1
        },
        {
          label: '左侧',
          value: 2
        },
        {
          label: '右侧',
          value: 3
        },
      {
          label: '底部',
          value: 4
      }
      ],
      statusOptions: [
        {
          label: '已上线',
          value: 1
        },
        {
          label: '下线',
          value: 2
        }
      ],
        platformOptions: [
            {
                label: '小程序',
                value: 1
            },
            {
                label: 'PC',
                value: 2
            },
            {
                label: 'APP',
                value: 3
            }
        ],
      // 搜索栏
      name: '',
      // 优惠券数组
      advertisementList: [],
      queryParams: {
        pageNum: 1,
        pageSize: 5,
        name: '',
        location: null,
        status: null
      },
      addCouponForm: {},
      // 每页几条数据
      pageSize: 5,
      // 总数
      total: 0,
      // 当前页
      currentPage: 1,
      editDialogVisible: false,
      editForm:{},
        changeImageVisible:false
    }
  },
  methods: {
      handleShowButton(){
          this.arr =  localStorage.getItem("ButtonList");
      },
      handleImageSuccess(response) {
          console.log(response)
          this.editForm.image = response.data;
      },
      beforeImageUpload(file) {
          /*console.log("确定图片格式及大小")
          const isJPG = file.type === 'image/jpeg';*/
          const isLt2M = file.size / 1024 / 1024 < 2;
          if (!isLt2M) {
              this.$message.error('上传图片大小不能超过 2MB!');
          }
          return isLt2M;
      },
      handlePreview(file) {
          console.log(file);
      },
      handleEdit(index, row){
          this.editForm={
              id:row.id,
              name:row.name,
              platform:row.platform,
              location:row.location,
              spuCode:row.spuCode,
              image:row.image,
              note:row.note,
              status:row.status,
              sort:row.sort,
              startTime:row.startTime,
              endTime:row.endTime
          }
          this.editDialogVisible = true
      },
      handleSaveEdit(){
          let params = this.editForm;
          console.log(params)
          editAdvertisement(params).then(res => {
              if (res.code === 0) {
                  this.$message({
                      message: '修改成功',
                      type: 'success'
                  })

                  this.editForm = {}
                  this.handleGetAdvertisementList();
                  this.editDialogVisible = false
              }
          })
      },
    // 每页几条数据变化
    handleSizeChange (val) {
      this.pageSize = val
    },
    // 当前页变化
    handleCurrentChange (val) {
      this.currentPage = val
      this.handleGetAdvertisementList()
    },
    // 查询按钮
    handleSearch () {
      this.currentPage = 1
      this.handleGetAdvertisementList()
    },
      handleChangeStatus(index,row){
        let params="id="+row.id
          changeAdvertisementStatus(params).then(res=>{
              if(res.code===0){
                  this.$message({
                      message: '修改成功',
                      type: 'success'
                  })
                  this.handleGetAdvertisementList();
              } else {
                  this.$message({
                      message: '修改失败',
                      type: 'fail'
                  })
              }
          })

      },

    // 获取广告列表
    handleGetAdvertisementList () {
      this.queryParams.pageNum = this.currentPage
      this.queryParams.pageSize = this.pageSize
      getAdvertisementList(this.queryParams).then(res => {
        if (res.code === 0) {
          this.advertisementList = res.data.content
          this.total = res.data.count
        }
      })
    }

  }
}
</script>

<style scoped>
  .input-width {
    width: 203px;
  }
</style>
