<template>
  <main>

    <el-card>
      <div slot="header" style="margin-bottom:10px;" class="clearfix" @click="$router.go(-1)">
        <i class="el-icon-arrow-left" style="font-weight: bolder; float: left;"></i>
        <span v-if="order.type == 1" style="margin-left: 10px; font-weight: bolder; float: left;">普通订单详情</span>
        <span v-if="order.type == 2" style="margin-left: 10px; font-weight: bolder; float: left;">积分订单详情</span>
        <span v-if="order.status == -1" style="float: right; color:#ff4949;">已取消</span>
        <span v-if="order.status == 1" style="float: right; color:#ff4949;">待付款</span>
        <span v-if="order.status == 2" style="float: right; color:#ff4949;">待发货</span>
        <span v-if="order.status == 3" style="float: right; color:#ff4949;">待收货</span>
        <span v-if="order.status == 4" style="float: right; color:#ff4949;">待评价</span>
        <span v-if="order.status == 5" style="float: right; color:#ff4949;">交易完成</span>
      </div>
      <div class="addressLogo">
        <img class="logo" src="../../assets/addresslogo.jpg">
      </div>
      <div class="address">
        <div style="font-weight: bolder; float:left; margin-bottom:15px;">收货信息：</div><br/>
        <span style="float:left; margin-left: 20px">{{order.name}}</span>
        <span style="float:left; margin-left: 30px">{{order.phone}}</span>
        <span style="float:left; margin-left: 30px">{{order.address}}</span>
      </div>
    </el-card>

    <el-card style="margin-top: 20px">
      <!-- 订单详情列表 -->
      <el-table :data="tableData">
        <el-table-column  prop="skuImage" label="图片" align="center">
          <template width="90" slot-scope="scope">
            <img style="width:80px; height:80px; border:none;" :src="baseURL+scope.row.skuImage">
          </template>
        </el-table-column>
        <el-table-column prop="skuCode" label="编码" sortable align="center"></el-table-column>
        <el-table-column prop="skuName" label="商品" sortable align="center"></el-table-column>
        <el-table-column prop="specs" label="属性" sortable align="center"></el-table-column>
        <el-table-column prop="originPrice" label="原价" sortable align="center"></el-table-column>
        <el-table-column prop="currentPrice" label="现价" sortable align="center"></el-table-column>
        <el-table-column prop="skuNum" label="数量" sortable align="center"></el-table-column>
        <el-table-column prop="price" label="总价" sortable align="center">
          <template slot-scope="scope">
            <span>￥{{scope.row.currentPrice*scope.row.skuNum}}</span>
          </template>
        </el-table-column>
      </el-table>
      <el-row>
        <div>
          <span style="float: right; margin-top:10px;">商品总价：￥{{order.originalPrice}}</span>
        </div>
      </el-row>
      <el-row>
        <div>
          <span style="float: right; color:#ff4949; margin-top:10px;">实付金额：￥{{order.truthPrice}}</span>
        </div>
      </el-row>
    </el-card>
    <el-card style="margin-top: 20px;">
      <div slot="header" style="margin-bottom:10px;" class="clearfix" @click="$router.go(-1)">
        <span style="font-weight: bolder; float:left;">订单信息</span>
      </div>
      <div style="width: 300px;">
        <el-row v-if="order.discount != null">
          <div style="float:left;">
            <span style="font-weight: bolder; color: #909399">折&nbsp;&nbsp;扣:</span>
            <span style="margin-left:10px; color: #909399">{{order.discount}}</span>
          </div>
        </el-row>
        <el-row v-if="order.couponName != null">
          <div style="float:left; margin-top:10px;">
            <span style="font-weight: bolder; color: #909399">优&nbsp;惠&nbsp;券:</span>
            <span style="margin-left:10px; color: #909399">{{order.couponName}}</span>
          </div>
        </el-row>
        <el-row v-if="order.points != null">
          <div style="float:left; margin-top:10px;">
            <span style="font-weight: bolder; color: #909399">使用积分:</span>
            <span style="margin-left:10px; color: #909399">{{order.points}}</span>
          </div>
        </el-row>
        <el-row>
          <div style="float:left; margin-top:10px;">
            <span style="font-weight: bolder; color: #909399">订单编号：</span>
            <span style="margin-left:10px; color: #909399">{{order.orderCode}}</span>
          </div>
        </el-row>
        <el-row>
          <div style="float:left; margin-top:10px;">
            <span style="font-weight: bolder; color: #909399">下单时间：</span>
            <span style="margin-left:10px; color: #909399">{{order.createTime}}</span>
          </div>
        </el-row>
        <el-row v-if="order.payTime != null">
          <div style="float:left; margin-top:10px;">
            <span style="font-weight: bolder; color: #909399">付款时间:</span>
            <span style="margin-left:10px; color: #909399">{{order.payTime}}</span>
          </div>
        </el-row>
        <el-row v-if="order.deliverTime != null">
          <div style="float:left; margin-top:10px;">
            <span style="font-weight: bolder; color: #909399">发货时间:</span>
            <span style="margin-left:10px; color: #909399">{{order.deliverTime}}</span>
          </div>
        </el-row>
        <el-row v-if="order.finishTime != null">
          <div style="float:left; margin-top:10px;">
            <span style="font-weight: bolder; color: #909399">完成时间：</span>
            <span style="margin-left:10px; color: #909399">{{order.finishTime}}</span>
          </div>
        </el-row>
        <el-row v-if="order.cancelTime != null">
          <div style="float:left; margin-top:10px;">
            <span style="font-weight: bolder; color: #909399">取消时间：</span>
            <span style="margin-left:10px; color: #909399">{{order.cancelTime}}</span>
          </div>
        </el-row>
        <el-row v-if="order.cancelReason != null">
          <div style="float:left; margin-top:10px;">
            <span style="font-weight: bolder; color: #909399">取消原因：</span>
            <span style="margin-left:10px; color: #909399">{{order.cancelReason}}</span>
          </div>
        </el-row>
      </div>
    </el-card>
  </main>
</template>

<script>
  export default {
    name: 'pointsOrder',
    data () {
      return {
        order: '',
        baseURL:'http://10.104.131.160/',
        tableData: []
      }
    },
    created () {},
    mounted () {
      this._loadData()
    },
    methods: {
      _loadData () {
        this.order = this.$route.query.order
        console.log(this.order)
        this.tableData = this.order.orderDetailVOList
        console.log(this.tableData)
      }
    }
  }
</script>

<style scoped>
  .pagination {
    text-align: right;
    margin-top: 20px;
  }
  .addressLogo {
    float: left;
    width: 100px;
    height: 90px;
    margin-left: 20px;
  }
  .logo {
    width: 70px;
    height: 70px;
  }
  .address {
    float: left;
    width: 800px;
    margin-left: 30px;
  }
  .el-table td, .el-table th {
    text-align: center
  }
</style>
