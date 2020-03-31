<template>
  <el-main>
    <el-row>
      <el-col :span="24" >
        <el-card class="box-card">
          <div slot="header" class="clearfix" style="width: 120px;font-weight: bolder">
            <span>待处理事务</span>
          </div>
          <el-row>
            <el-col :span="4">
              <el-row>
                <el-col :span="10" :offset="4">
                  待付款订单
                </el-col>
                <el-col :span="2" :offset="1">{{countOrdersByStatus.notPayStatus}}</el-col>
              </el-row>
              <el-divider></el-divider>
            </el-col>
            <el-col :span="4" :offset="1">
              <el-row>
                <el-col :span="10" :offset="4">
                  待发货订单
                </el-col>
                <el-col :span="2" :offset="1">{{countOrdersByStatus.notDeliverStatus}}</el-col>
              </el-row>
              <el-divider></el-divider>
            </el-col>
            <el-col :span="4" :offset="1">
              <el-row>
                <el-col :span="10" :offset="4">
                  待收货订单
                </el-col>
                <el-col :span="2" :offset="3">{{countOrdersByStatus.notReceiveStatus}}</el-col>
              </el-row>
              <el-divider></el-divider>
            </el-col>
            <el-col :span="4" :offset="1">
              <el-row>
                <el-col :span="10" :offset="4">
                  待评价订单
                </el-col>
                <el-col :span="2" :offset="3">{{countOrdersByStatus.notEvaluateStatus}}</el-col>
              </el-row>
              <el-divider></el-divider>
            </el-col>
            <el-col :span="4" :offset="1" >
              <el-row>
                <el-col :span="10" :offset="4">
                  已完成订单
                </el-col>
                <el-col :span="2" :offset="3">{{countOrdersByStatus.finishStatus}}</el-col>
              </el-row>
              <el-divider></el-divider>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <el-row style="margin-top: 30px">
      <el-col :span="6" :offset="2">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>今日订单总数</span>
          </div>
          <el-row>
            <el-col :span="8">
              <img
                style="width: 50px;height: 50px;padding: 0"
                src="../../../static/img/orderSum.png"/>
            </el-col>
            <el-col :span="8" style="line-height: 50px">
              <span>{{countOrdersByDay.todayOrderNum}}</span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
      <el-col :span="6" :offset="1">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>今日销售总额</span>
          </div>
          <el-row>
            <el-col :span="8">
              <img
                style="width: 50px;height: 50px;padding: 0"
                src="../../../static/img/saleSum.png"/>
            </el-col>
            <el-col :span="8" style="line-height: 50px">
              <span>￥{{countOrdersByDay.todayOrderAmount}}</span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
      <el-col :span="6" :offset="1">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>昨日销售总额</span>
          </div>
          <el-row>
            <el-col :span="8">
              <img
                style="width: 50px;height: 50px;padding: 0"
                src="../../../static/img/yesterdaySum.png"/>
            </el-col>
            <el-col :span="8" style="line-height: 50px">
              <span>￥{{countOrdersByDay.yesterdayOrderAmount}}</span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    
    <el-row style="margin-top: 30px">
      <el-col :span="20" :offset="2">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <div style="float:left; font-weight: bolder">
              <span style="">订单统计</span>
            </div>
            <div style="float:right;">
              <el-form :inline="true" size="small" style="float:left;">
                <!-- 状态选择 -->
                <el-form-item style="margin-left:20px;">
                  <el-select v-model.trim.lazy="year" placeholder="选择年份">
                    <el-option
                      v-for="item in options"
                      :key="item.value"
                      :label="item.label"
                      :value="item.label">
                    </el-option>
                  </el-select>
                </el-form-item>
                <!-- 查询按钮、重置按钮 -->
                <el-form-item>
                  <el-button-group>
                    <el-button type="primary" @click="handleSelect()">查询</el-button>
                  </el-button-group>
                </el-form-item>
              </el-form>
            </div>
          </div>
          <div :span="24" id="countMain" style="height: 300px"></div>
          <div :span="24" id="saleMain" style="height: 300px; margin-top:20px;"></div>
        </el-card>
      </el-col>
    </el-row>
  </el-main>
</template>

<script>
import { reqCountOrdersByDay, reqCountOrdersByStatus, reqListYears, reqListMonthSalesByYears } from '../../api/order.js'
import echarts from 'echarts'
export default {
  name: 'orderStatistics',
  data () {
    return {
      countOrdersByDay: {},
      countOrdersByStatus: {},
      charts: '',
      status: '',
      year: null,
      count: [],
      sale: [],
      options: []
    }
  },
  created () {
    this.init();
  },
  mounted () {
    this._loadData()
  },
  methods: {
    init(){
      var myDate = new Date;
      var year = myDate.getFullYear();
      this.year = year
    },
    _loadData () {
      reqCountOrdersByDay().then(res => {
        if (res.code === 0 && res.data != null) {
          this.countOrdersByDay = res.data
        }
      })
      reqCountOrdersByStatus().then(res => {
        if (res.code === 0 && res.data != null) {
          this.countOrdersByStatus = res.data
        }
      })
      reqListYears().then(res => {
        if (res.code === 0 && res.data != null) {
          let i = 0
          let arr = []
          res.data.forEach (e => {
            let item = {
              value: i,
              label: e
            }
            i += 1
            arr.push(item)
          })
          this.options = arr
        }
      })
      let params =new URLSearchParams
      params.append('year',this.year)
      reqListMonthSalesByYears(params).then( res => {
        if (res.code === 0 && res.data != null) {
          let countArr = []
          let saleArr = []
          res.data.forEach (e => {
            countArr.push(e.count)
            saleArr.push(e.sale)
          })
          this.count = countArr
          this.sale = saleArr
          this.showCountEchart()
          this.showSaleEchart()
        }
      })
    },
    showCountEchart () {
      this.charts = echarts.init(document.getElementById('countMain'))
      this.charts.setOption({
        title : {
          text: '订单月销量',
          subtext: '订单每月对应销量',
          x:'center'
        },
        color: ['#3398DB'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {            
                type : 'shadow'        
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        toolbox: {
          feature: {
            magicType: { type: ['line', 'bar'] },
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            saveAsImage : {show: true}
          }
        },
        xAxis : [
            {
                type : 'category',
                data : ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'订单月销量',
                type:'bar',
                barWidth: '60%',
                data: this.count
            }
        ]
      })
    },
    showSaleEchart () {
      this.charts = echarts.init(document.getElementById('saleMain'))
      this.charts.setOption({
        title : {
          text: '订单月销售额',
          subtext: '订单每月对应销售额',
          x:'center'
        },
        tooltip: {
          trigger: 'axis'
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        toolbox: {
          feature: {
            magicType: { type: ['line', 'bar'] },
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            saveAsImage : {show: true}
          }
        },
        xAxis: {
          type: 'category',
          data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'] // 日期数据

        },
        yAxis: {
          type: 'value'
        },
        series: [{
          name: '订单月销售额',
          type: 'line',
          stack: '总量',
          data: this.sale,
          markPoint: {
            data: [
              { type: 'max', name: '最大值' },
              { type: 'min', name: '最小值' }
            ]
          },
          markLine: {
            data: [
              { type: 'average', name: '平均值' }
            ]
          }
        }]
      })
    },
    handleSelect () {
      this.year = this.year
      this._loadData()
    }
  }
}
</script>

<style scoped>
  .el-main {
    width: 100%;
    height: 100%;
    background-color: #ffffff
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }
</style>
