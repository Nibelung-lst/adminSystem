<template>
  <main>
    <el-card style="margin-top: 20px">
      <div slot="header" class="clearfix" style="margin-bottom:30px;">
          <span style="float:left; font-weight: bolder">订单统计</span>
          <div style="float:right;">
            <el-form :inline="true" size="small" style="float:left;">
              <!-- 状态选择 -->
              <el-form-item style="margin-left:20px; width:150px;">
                <el-select v-model.trim.lazy="year" placeholder="选择年份">
                  <el-option
                    v-for="item in yearOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.label">
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item style="margin-left:20px; width:150px;">
                <el-select v-model.trim.lazy="month" placeholder="选择月份">
                  <el-option
                    v-for="item in monthOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
              <!-- 查询按钮按钮 -->
              <el-form-item>
                <el-button-group>
                  <el-button type="primary" @click="handleSelect()">查询</el-button>
                </el-button-group>
              </el-form-item>
            </el-form>
          </div>
        </div>
        <div id="countMain" style="float:left; height: 400px; width: 50%;"></div>
        <div id="saleMain" style="float:left; height: 400px; width: 50%;"></div>
    </el-card>
  </main>
</template>

<script>
import { reqListSpuMonthSales, reqListYears } from '../../api/order.js'
import echarts from 'echarts'
export default {
  name: 'spuStatistics',
  data () {
    return {
      name: [],
      count: [],
      sale: [],
      year: null,
      yearOptions: [],
      month: null,
      monthOptions: [
        {
          value: '1',
          label: '1月'
        },{
          value: '2',
          label: '2月'
        },
        {
          value: '3',
          label: '3月'
        },
        {
          value: '4',
          label: '4月'
        },
        {
          value: '5',
          label: '5月'
        },
        {
          value: '6',
          label: '6月'
        },
        {
          value: '7',
          label: '7月'
        },
        {
          value: '8',
          label: '8月'
        },
        {
          value: '9',
          label: '9月'
        },
        {
          value: '10',
          label: '10月'
        },
        {
          value: '11',
          label: '11月'
        },
        {
          value: '12',
          label: '12月'
        }
      ]
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
      var month = myDate.getMonth()+1;
      var day = myDate.getDate();
      this.year = year
      this.month = month
    },
    _loadData () {
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
          this.yearOptions = arr
        }
      })
      let params = {
        year: this.year,
        month: this.month
      }
      reqListSpuMonthSales(params).then(res => {
        if (res.code === 0 && res.data != null) {
          let nameArr = []
          let countArr = []
          let saleArr = []
          res.data.forEach (e => {
            nameArr.push(e.spuName)
            let item = {
              value: e.spuCount,
              name: e.spuName
            }
            countArr.push(item)
            item = {
              value: e.spuSale,
              name: e.spuName
            }
            saleArr.push(item)
          })
          this.name = nameArr
          this.count = countArr
          this.sale = saleArr
          this.showCountEchart()
          this.showSaleEchart()
        }
      })
    },
    handleSelect () {
      this.year = this.year
      this.month = this.month
      this._loadData()
    },
    showCountEchart () {
      this.charts = echarts.init(document.getElementById('countMain'))
      this.charts.setOption({
        title : {
          text: '商品月销量',
          subtext: '每种商品对应的每月销量',
          x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: this.name
        },
        series : [
            {
                name: '月销量',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data: this.count,
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
      })
    },
    showSaleEchart () {
      this.charts = echarts.init(document.getElementById('saleMain'))
      this.charts.setOption({
        title : {
          text: '商品月销售额',
          subtext: '每种商品对应的每月销售额',
          x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} :"+"￥"+" {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: this.name
        },
        series : [
            {
                name: '月销售额',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data: this.sale,
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
      })
    }
  }
}
</script>

<style scoped>
</style>
