<template>
  <main>
    <el-row :gutter="12">
      <el-col :span="24">
        <el-card shadow="always">
          <el-row :gutter="12">
            <el-col :span="4">
              <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item>积分订单列表</el-breadcrumb-item>
              </el-breadcrumb>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <el-card style="margin-top: 20px">
      <div style="float:left;">
        <el-form :inline="true" size="small" style="float:left;">
          <!-- 状态选择 -->
          <el-form-item style="margin-left:20px;">
            <el-select v-model="status" placeholder="全部订单">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <!-- 搜索框 -->
          <el-form-item>
            <el-input style="width: 250px;" v-model.trim.lazy="searchContent" placeholder="请输入内容搜索">
              <i style="margin: 12px 5px 0 0;font-size:16px" class="el-icon-search" slot="suffix"></i>
            </el-input>
          </el-form-item>
          <!-- 查询按钮、重置按钮 -->
          <el-form-item>
            <el-button-group>
              <el-button type="primary" @click="handleSelect()">查询</el-button>
              <el-button @click="handleReset()">重置</el-button>
            </el-button-group>
          </el-form-item>
        </el-form>
      </div>
      <!-- 订单列表 -->
      <el-table :data="tableData">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" class="demo-table-expand">
              <el-form-item v-if="props.row.originalPrice != null" label="原价:">
                <span>{{ props.row.originalPrice }}</span>
              </el-form-item>
              <el-form-item v-if="props.row.truthPrice != null" label="实付金额:">
                <span>{{ props.row.truthPrice }}</span>
              </el-form-item>
              <el-form-item v-if="props.row.points != null" label="使用积分:">
                <span>{{ props.row.points }}</span>
              </el-form-item>
              <el-form-item v-if="props.row.cancelTime != null" label="取消时间:">
                <span>{{ props.row.cancelTime }}</span>
              </el-form-item>
              <el-form-item v-if="props.row.payTime != null" label="付款时间:">
                <span>{{ props.row.payTime }}</span>
              </el-form-item>
              <el-form-item v-if="props.row.deliverTime != null" label="发货时间:">
                <span>{{ props.row.deliverTime }}</span>
              </el-form-item>
              <el-form-item v-if="props.row.finishTime != null" label="完成时间:">
                <span>{{ props.row.finishTime }}</span>
              </el-form-item>
              <el-form-item v-if="props.row.cancelReason != null" label="取消原因:">
                <span>{{ props.row.cancelReason }}</span>
              </el-form-item>
              <el-form-item label="收货地址:">
                <span>{{ props.row.address }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column prop="orderCode" label="订单流水号" width="200" sortable></el-table-column>
        <el-table-column prop="name" label="收货人" width="100" sortable align="center"></el-table-column>
        <el-table-column prop="phone" label="联系电话" sortable align="center"></el-table-column>
        <el-table-column prop="createTime" label="下单时间" width="170" sortable align="center"></el-table-column>
        <el-table-column prop="status" label="订单状态" sortable align="center">
          <template slot-scope="scope">
            <span v-if="scope.row.status === -1" style="color:#ff4949">已取消</span>
            <span v-else-if="scope.row.status === 1" style="color:#ff4949">未支付</span>
            <span v-else-if="scope.row.status === 2" style="color:#ff4949">待发货</span>
            <span v-else-if="scope.row.status === 3" style="color:#ff4949">待收货</span>
            <span v-else-if="scope.row.status === 4" style="color:#ff4949">待评价</span>
            <span v-else-if="scope.row.status === 5" style="color:#ff4949">已完成</span>
          </template>
        </el-table-column>
        <el-table-column label="订单详情" align="center">
          <template slot-scope="scope">
            <el-button type="primary" size="mini" plain  @click="handleOrderDetail(scope.$index, scope.row)">查看明细</el-button>
          </template>
        </el-table-column>
        <el-table-column label="操作" fixed="right" align="center">
          <template slot-scope="scope">
            <el-button v-if="scope.row.status === 1 " type="danger" plain size="mini"  @click="handleCancel(scope.$index, scope.row)">取消订单</el-button>
            <el-button v-else-if="scope.row.status === 2 " type="warning" plain size="mini"  @click="handleDeliver(scope.$index, scope.row)">点击发货</el-button>
            <el-button v-else-if="scope.row.status === 5" type="success" plain size="mini">交易完成</el-button>
            <el-button v-else type="info" plain size="mini">无</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页栏 -->
      <div class="pagination">
        <el-pagination @size-change="handleSizeChange"
                       @current-change="handleCurrentChange"
                       :current-page="currentPage"
                       :page-sizes="[10, 20, 30, 40]"
                       :page-size="pageSize"
                       layout="total, sizes, prev, pager, next, jumper"
                       :total="count">
        </el-pagination>
      </div>
    </el-card>
  </main>
</template>

<script>
  import { reqListOrders, reqCancelOrder, reqDeliverOrder } from '../../api/order.js'
  export default {
    name: 'pointsOrder',
    data () {
      return {
        arr:[],
        count: 0,
        pageSize: 10,
        currentPage: 1,
        status: '',
        searchContent: '',
        tableData: [],
        options: [
          {
            value: '',
            label: '全部订单'
          }, {
            value: '-1',
            label: '已取消订单'
          }, {
            value: '1',
            label: '待付款订单'
          }, {
            value: '2',
            label: '待发货订单'
          }, {
            value: '3',
            label: '待收货订单'
          }, {
            value: '4',
            label: '待评价订单'
          }, {
            value: '5',
            label: '已成交订单'
          }]
      }
    },
    created () {
      this.handleShowButton()
    },
    mounted () {
      this._loadData()
    },
    methods: {
      handleShowButton(){
        this.arr =  localStorage.getItem("ButtonList");
      },
      _loadData () {
        let params = {
          pageSize: this.pageSize,
          pageNum: this.currentPage,
          type: 2,
          status: this.status,
          keyword: this.searchContent
        }
        reqListOrders(params).then(res => {
          if (res.status === 200 && res.data != null) {
            this.count = res.data.count
            this.tableData = res.data.content
            console.log(this.tableData)
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
        this._loadData()
      },
      // 根据搜索框关键字查询
      handleSelect () {
        this.status = this.status
        this._loadData()
      },
      handleReset () {
        this.searchContent = ''
        this.status = ''
        this._loadData()
      },
      handleCancel (index, row) {
        this.$confirm('是否取消订单?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let params = {
            orderCode: row.orderCode,
            cancelReason: '后台取消订单'
          }
          reqCancelOrder(params).then(res => {
            if (res.status === 200) {
              this.$message({
                type: 'success',
                message: '取消订单成功!'
              })
              this._loadData()
            }
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '暂不取消订单'
          })
        })
      },
      handleDeliver (index, row) {
        this.$confirm('是否发货?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let params = 'orderCode=' + row.orderCode
          reqDeliverOrder(params).then(res => {
            if (res.status === 200) {
              this.$message({
                type: 'success',
                message: '发货成功!'
              })
              this._loadData()
            }
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '暂不发货'
          })
        })
      },
      handleOrderDetail (index, row) {
        this.$router.push({
          path: '/layout/orderDetail',
          query: {
            order: row
          }
        })
      }
    }
  }
</script>

<style scoped>
  .pagination {
    text-align: right;
    margin-top: 20px;
  }
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
</style>
