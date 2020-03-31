<template>
  <main>
    <el-card>
      <div slot="header" class="clearfix" style="margin-bottom:30px;">
        <span style="float:left; font-weight: bolder">每日登录量统计</span>
        <div style="float:right;">
          <el-form :inline="true" size="small" style="float:left;">
            <!-- 状态选择 -->
            <el-form-item style="margin-left:20px; width:150px;">
              <el-select v-model.trim.lazy="yearLogin" placeholder="选择年份">
                <el-option
                  v-for="item in yearLoginOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.label">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item style="margin-left:20px; width:150px;">
                <el-select v-model.trim.lazy="monthLogin" placeholder="选择月份">
                  <el-option
                    v-for="item in monthLoginOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            <!-- 查询按钮按钮 -->
            <el-form-item>
              <el-button-group>
                <el-button type="primary" @click="handleLoginSelect()">查询</el-button>
              </el-button-group>
            </el-form-item>
          </el-form>
        </div>
      </div>
      <div :span="24" id="countLogin" style="height: 300px"></div>
    </el-card>
    <el-card style="margin-top: 20px">
      <div slot="header" class="clearfix" style="margin-bottom:30px;">
        <span style="float:left; font-weight: bolder">每月注册量统计</span>
        <div style="float:right;">
          <el-form :inline="true" size="small" style="float:left;">
            <!-- 状态选择 -->
            <el-form-item style="margin-left:20px; width:150px;">
              <el-select v-model.trim.lazy="yearRegister" placeholder="选择年份">
                <el-option
                  v-for="item in yearRegisterOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.label">
                </el-option>
              </el-select>
            </el-form-item>
            <!-- 查询按钮按钮 -->
            <el-form-item>
              <el-button-group>
                <el-button type="primary" @click="handleRegisterSelect()">查询</el-button>
              </el-button-group>
            </el-form-item>
          </el-form>
        </div>
      </div>
      <div :span="24" id="countRegister" style="height: 300px"></div>
    </el-card>
  </main>
</template>

<script>
import { getAllRegisterYears, countRegisterUserByMount, getAllLoginYears, countLoginUserByDay } from '../api/user.js'
import echarts from 'echarts'
export default {
  name: 'userStatistics',
  data () {
    return {
      yearRegister: null,
      monthRegister: [],
      countRegister: [],
      yearRegisterOptions: [],
      yearLogin: null,
      monthLogin: null,
      dayLogin: [],
      countLogin : [],
      yearLoginOptions: [],
      monthLoginOptions: [
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
    this.init()
  },
  mounted () {
    this._loadDataRegister()
    this._loadDataLogin()
  },
  methods: {
    init(){
      var myDate = new Date;
      var year = myDate.getFullYear();
      var month = myDate.getMonth()+1;
      var day = myDate.getDate();
      this.yearRegister = year
      this.yearLogin = year
      this.monthLogin = month
    },
    // 初始化注册统计
    _loadDataRegister () {
      getAllRegisterYears().then(res => {
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
          this.yearRegisterOptions = arr
        }
      })
      let params =new URLSearchParams
      params.append('year',this.yearRegister)
      countRegisterUserByMount(params).then( res => {
        if (res.code === 0 && res.data != null) {
          let monthArr = []
          let countArr = []
          res.data.forEach (e => {
            monthArr.push(e.month+'月')
            countArr.push(e.count)
          })
          this.monthRegister = monthArr
          this.countRegister = countArr
          this.showRegisterEchart()
        }
      })
    },
    // 初始化登录统计
    _loadDataLogin () {
      getAllLoginYears().then(res => {
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
          this.yearLoginOptions = arr
        }
      })
      let params = {
        year: this.yearLogin,
        month: this.monthLogin
      }
      countLoginUserByDay(params).then(res => {
        if (res.code === 0 && res.data != null) {
          let dayArr = []
          let countArr = []
          res.data.forEach (e => {
            dayArr.push(e.day+'日')
            countArr.push(e.count)
          })
          this.dayLogin = dayArr
          this.countLogin = countArr
          this.showLoginEchart()
        }
      })
    },
    handleRegisterSelect () {
      this.yearRegister = this.yearRegister
      this._loadDataRegister()
    },
    handleLoginSelect () {
      this.yearLogin = this.yearLogin
      this.monthLogin = this.monthLogin
      this._loadDataLogin()
    },
    showRegisterEchart () {
      this.charts = echarts.init(document.getElementById('countRegister'))
      this.charts.setOption({
            xAxis: {
            type: 'category',
            data: this.monthRegister
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: this.countRegister,
            type: 'line',
            symbol: 'triangle',
            symbolSize: 20,
            lineStyle: {
                normal: {
                    color: 'green',
                    width: 4,
                    type: 'dashed'
                }
            },
            itemStyle: {
                normal: {
                    borderWidth: 3,
                    borderColor: 'yellow',
                    color: 'blue'
                }
            }
        }]
      })
    },
    showLoginEchart () {
      this.charts = echarts.init(document.getElementById('countLogin'))
      this.charts.setOption({
        xAxis: {
            type: 'category',
            data: this.dayLogin
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: this.countLogin,
            type: 'bar'
        }]
      })
    }
  }
}
</script>

<style scoped>
</style>