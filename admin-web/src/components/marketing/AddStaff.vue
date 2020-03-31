<template>
  <el-card class="form-container" shadow="never">
    <el-form :model="employeeInformation"
             :rules="rules"
             ref="couponFrom"
             label-width="150px"
             size="small">
      <el-form-item label="员工名称：" prop="name">
        <el-input  v-model="employeeInformation.name" class="input-width"></el-input>
      </el-form-item>
      <el-form-item label="邮箱：" prop="email">
        <el-input  v-model="employeeInformation.email" class="input-width"></el-input>
      </el-form-item>
      <el-form-item label="联系方式：" prop="address">
        <el-input v-model="employeeInformation.address" placeholder="" class="input-width">
        </el-input>
      </el-form-item>

      <el-form-item label="备注：">
        <el-input
          class="input-width"
          type="textarea"
          :rows="5"
          placeholder="请输入内容"
          v-model="addCouponForm.note">
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" v-if="arr.includes('MARKETING_CREATE_COUPON')" @click="handleSubmit()">提交</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
import { addCoupon } from '../../api/marketing'
import { getAllCategoryTree,getProductByKeyWord } from '@/api/product'
import {test} from "../../api/admin";
export default {
  name: 'AddStaff',
  data () {
    return {
        arr:[],
        rules: {
            name: [
                {required: true, message: '请输入员工名称', trigger: 'blur'},
                {min: 2, max: 140, message: '长度在 2 到 140 个字符', trigger: 'blur'}
            ],
          email: [ { required: true, message: "请输入邮箱", trigger: "blur" },
                   { pattern:/^([0-9A-Za-z\-_\.]+)@([0-9a-z]+\.[a-z]{2,3}(\.[a-z]{2})?)$/g, message: "请输入正确的邮箱", trigger: "blur"}
            ],
            amount: [
                {type: 'number',required: true,message: '面值只能是数值，限2位小数',trigger: 'blur'}
            ],
            minAmount: [
                {type: 'number',required: true,message: '只能输入正整数',trigger: 'blur'}
            ]
        },
      useTypeOptions: [
        {
          label: '门店主管',
          value: 1
        },
        {
          label: '正式员工',
          value: 2
        },
        {
          label: '实习员工',
          value: 3
        }
      ],
        labelOptions:[
            {
                label:'注册',
                value:'register'
            },
            {
                label:'购物',
                value:'shopping'
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
        },
      ],
      statusOptions: [
        {
          label: '上线',
          value: 1
        },
        {
          label: '下线',
          value: 2
        }
      ],
      employeeInformation: {
          name:'',
          email:'',
          password:'',
          phone:'',
          address:''
      },
      selectCategory: {
          categoryCode:'',
          categoryName: ''
      },
      selectProduct: {},
      categoryOptions: [],
        selectProductLoading: false,
        selectProductOptions : []

    }
  },
  mounted(){
      this.handleShowButton();
      this.handleGetCategoryList();
  },
  methods: {
    test1(){
      test().then(res => {
        this.$message.success(res);
      })
    },
      handleShowButton(){
          this.arr =  localStorage.getItem("ButtonList");
      },
    handleSubmit () {
      let params = this.addCouponForm
      console.log('kkan')
      console.log(params)
      addCoupon(params).then(res => {
        if (res.code === 0) {
          this.$message({
            message: '新增成功',
            type: 'success'
          })
        } else {
          this.$message({
            message: '添加失败',
            type: 'fail'
          })
        }
      })
    },
    handleAddCategory () {
      this.addCouponForm.useRangeCode = this.selectCategory[1]
        console.log(this.selectCategory[1])
        for (let i = 0; i < this.categoryOptions.length; i++) {
            if (this.categoryOptions[i].value === this.selectCategory[0]) {
                for (let j = 0; j < this.categoryOptions[i].children.length; j++) {
                    if (this.categoryOptions[i].children[j].value === this.selectCategory[1]) {
                        this.addCouponForm.useRangeName = this.categoryOptions[i].children[j].label;
                    }
                }
            }
        }
        console.log(this.addCouponForm)
    },
    handleAddProduct () {
        for(let i=0;i<this.selectProductOptions.length;i++){
            if(this.selectProduct===this.selectProductOptions[i].spuCode){
                this.addCouponForm.useRangeName = this.selectProductOptions[i].spuName
            }
        }
      this.addCouponForm.useRangeCode = this.selectProduct
      this.selectProduct = {}
    }
  }
}
</script>

<style scoped>
  .input-width {
    width: 80%;
  }
  .form-container{
    width: 70%;
    margin-left: 20%;
    text-align: left;
  }
</style>
