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
                <el-breadcrumb-item>优惠券列表</el-breadcrumb-item>
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
                <el-form-item label="优惠券名称：">
                  <el-input v-model="queryParams.name" class="input-width" placeholder="优惠券名称"></el-input>
                </el-form-item>
                <el-form-item label="优惠券类型：">
                  <el-select v-model="queryParams.useType" placeholder="全部" clearable class="input-width">
                    <el-option v-for="item in typeOptions"
                               :key="item.value"
                               :label="item.label"
                               :value="item.value">
                    </el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="优惠券状态：">
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
          :data="couponList"
          style="width: 100%"
          tooltip-effect="dark">
          <el-table-column
            align="center"
            label="编号" width="100">
            <template slot-scope="scope">{{ scope.row.couponCode }}</template>
          </el-table-column>
          <el-table-column label="优惠劵名称" align="center" width="150">
            <template slot-scope="scope">{{scope.row.name}}</template>
          </el-table-column>
          <el-table-column label="类型" width="100" align="center">
            <template slot-scope="scope">{{scope.row.useType | formatUseType}}</template>
          </el-table-column>
          <el-table-column label="可使用商品" width="150" align="center">
            <template slot-scope="scope"><span v-if="scope.row.useType != 1">{{scope.row.useRangeName}}</span><span v-else>全场可用</span></template>
          </el-table-column>
          <el-table-column label="使用门槛" width="150" align="center">
            <template slot-scope="scope">满{{scope.row.minAmount}}元可用</template>
          </el-table-column>
          <el-table-column label="面值" width="70" align="center">
            <template slot-scope="scope">{{scope.row.amount}}元</template>
          </el-table-column>numberLimit
          <el-table-column label="每人限领" width="70" align="center">
            <template slot-scope="scope">{{scope.row.numberLimit}}张</template>
          </el-table-column>
          <el-table-column label="发券类型" width="100" align="center">
            <template slot-scope="scope">{{scope.row.sendType | formatType}} {{scope.row.label | formatLabel}}</template>
          </el-table-column>
          <el-table-column label="有效期" width="250" align="center">
            <template slot-scope="scope">{{scope.row.startTime | formatDate}}至{{scope.row.endTime |formatDate}}</template>
          </el-table-column>
          <el-table-column label="上下线" width="70" align="center">
            <template slot-scope="scope">{{scope.row.status | formatStatus}}</template>
          </el-table-column>
          <el-table-column label="状态" width="70" align="center">
            <template slot-scope="scope">{{scope.row.endTime | formatExpire}}</template>
          </el-table-column>
          <el-table-column label="发布时间" width="130" align="center">
            <template slot-scope="scope"><span v-if="scope.row.releaseTime != null">{{scope.row.releaseTime | formatDate}}</span>
            <el-button type="primary" plain size="small" v-if="(scope.row.releaseTime == null)&&arr.includes('MARKETING_SEARCH_USER_COUPON')" @click="handleEdit(scope.$index,scope.row)">设置发布时间</el-button></template>
          </el-table-column>
          <el-table-column
            align="center"
            label="操作">
            <template slot-scope="scope">
              <el-button type="primary" plain size="small" v-if="arr.includes('MARKETING_SEARCH_USER_COUPON')" @click="handleView(scope.$index,scope.row)">查看流水</el-button>
                <el-button type="danger" plain size="small" v-if="(scope.row.status == 1)&&arr.includes('MARKETING_UPDATE_COUPON')" @click="handleChangeStatus(scope.$index,scope.row)">下线</el-button>
              <el-button type="danger" plain size="small" v-if="(scope.row.status == 2)&&arr.includes('MARKETING_UPDATE_COUPON')" @click="handleChangeStatus(scope.$index,scope.row)">上线</el-button>
              <el-button type="danger" plain size="small" v-if=handleDate(scope.row.endTime) @click="handleSendCoupon(scope.$index,scope.row)">赠券</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
    <el-dialog title="设置发布时间" :visible.sync="setTimeVisible" style="width:80%">
      <el-form>
      <el-form-item label="有效期：">
        <el-date-picker type="datetime" placeholder="选择日期" v-model="editForm.releaseTime" style="width: 80%"></el-date-picker>
      </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleSaveEdit()" style="text-align: center">保存修改</el-button>
        </el-form-item>
      </el-form>
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
  </div>
</template>

<script>
import { getAllCoupon,handleChangeStatus,setReleaseTime } from '@/api/marketing'
import { formatDate } from '@/utils/date'

export default {
  created () {
    this.handleGetAllCoupon()
      this.handleShowButton()
  },
  mounted () {
  },
    computed:{

    },
  filters: {
      formatLabel(label){
          if (label == "register") {
              return '注册'
          }
          if (label == "shopping") {
              return '购物'
          }
      },
    formatType (type) {
      if (type === 1) {
        return '用户领取'
      } else {
        return '后台赠送'
      }
    },
    formatUseType (useType) {
      if (useType === 1) {
        return '全场通用'
      } else if (useType === 2) {
        return '指定分类'
      } else {
        return '指定商品'
      }
    },
    formatDate (time) {
      if (time == null || time === '') {
        return 'N/A'
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
    },
  },
  data () {
    return {
        arr:[],
      useTypeOptions: [
        {
          label: '全品类',
          value: 1
        },
        {
          label: '指定分类',
          value: 2
        },
        {
          label: '指定商品',
          value: 3
        }
      ],
      typeOptions: [
        {
          label: '用户领取',
          value: 1
        },
        {
          label: '后台赠送',
          value: 2
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
      // 搜索栏
      name: '',
      // 优惠券数组
      couponList: [],
      queryParams: {
        pageNum: 1,
        pageSize: 5,
        name: '',
        type: 1,
        status: null
      },
      addCouponForm: {},
      // 每页几条数据
      pageSize: 5,
      // 总数
      total: 0,
      // 当前页
      currentPage: 1,
        setTimeVisible:false,
        editForm:{

        }
    }
  },
  methods: {
      handleShowButton(){
          this.arr =  localStorage.getItem("ButtonList");
      },
      handleDate(time) {
          let now = new Date().getTime()
          let endDate = new Date(time)
          if (endDate > now) {
              return true
          } else {
              return false
          }
      },
    handleView (index, row) {
      console.log(row.couponCode)
        this.$router.push({ path: '/marketing/couponHistory', query: { couponCode: row.couponCode } })
    },
      handleSendCoupon(index,row){
          this.$router.push({ path: '/marketing/sendCoupon', query: { couponCode: row.couponCode } })
      },
    // 每页几条数据变化
    handleSizeChange (val) {
      this.pageSize = val
        this.handleGetAllCoupon()
    },
    // 当前页变化
    handleCurrentChange (val) {
      this.currentPage = val
      this.handleGetAllCoupon()
    },
    // 查询按钮
    handleSearch () {
      this.currentPage = 1
      this.handleGetAllCoupon()
    },
      handleChangeStatus(index,row){
        let params="couponCode="+row.couponCode
          handleChangeStatus(params).then(res=>{
              if(res.code===0){
                  this.$message({
                      message: '修改成功',
                      type: 'success'
                  })
                  this.handleGetAllCoupon();
              } else {
                  this.$message({
                      message: '修改失败',
                      type: 'fail'
                  })
              }
          })

      },

    // 获取优惠券
    handleGetAllCoupon () {
      this.queryParams.pageNum = this.currentPage
      this.queryParams.pageSize = this.pageSize
      getAllCoupon(this.queryParams).then(res => {
        if (res.code === 0) {
          this.couponList = res.data.content
          this.total = res.data.count
        }
      })
    },
      handleEdit(index, row){
          this.editForm={
              couponCode:row.couponCode,
              releaseTime:row.releaseTime
          }
          this.setTimeVisible = true
      },
      handleSaveEdit(){
          let params = this.editForm;
          console.log(params)
          setReleaseTime(params).then(res => {
              if (res.code === 0) {
                  this.$message({
                      message: '修改成功',
                      type: 'success'
                  })
                  this.editForm = {}
                  this.setTimeVisible = false
                  this.handleGetAllCoupon()
              }
          })
      },

  }
}
</script>

<style scoped>
  .input-width {
    width: 203px;
  }
</style>
