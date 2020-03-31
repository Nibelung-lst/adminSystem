<template>
  <el-card class="form-container" shadow="never">
    <el-button  type="primary" @click="handleSubmit()">打卡</el-button>
  </el-card>
</template>

<script>
import { addCoupon } from '../../api/marketing'
import { getAllCategoryTree,getProductByKeyWord } from '@/api/product'
export default {
  name: 'AddCoupon',
  data () {
    return {
        arr:[],
        rules: {
            name: [
                {required: true, message: '请输入优惠券名称', trigger: 'blur'},
                {min: 2, max: 140, message: '长度在 2 到 140 个字符', trigger: 'blur'}
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
      addCouponForm: {},
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
    handleGetCategoryList(){
        getAllCategoryTree().then(res=>{
                let list = res.data;
                this.categoryOptions = [];
                for (let i = 0; i < list.length; i++) {
                    let children = [];
                    if (list[i].children != null && list[i].children.length > 0) {
                        for (let j = 0; j < list[i].children.length; j++) {
                            children.push({label: list[i].children[j].categoryName, value: list[i].children[j].categoryCode});
                        }
                    }
                    this.categoryOptions.push({label: list[i].categoryName, value: list[i].categoryCode, children: children});
                }
        })
    },
    searchProductMethod (query) {
      if (query !== '') {
        this.selectProductLoading = true
        // 通过商品名称或spuCode搜索商品
          let params= "keyWord="+query
          getProductByKeyWord(params).then(res=>{
              this.selectProductLoading=false;
              let productList = res.data;
              for(let i=0;i<productList.length;i++){
                  let item = productList[i];
                  this.selectProductOptions.push({spuCode:item.spuCode,spuName:item.spuName});

              }
              console.log(this.selectProductOptions)
          })
      } else {
        this.selectProductOptions = []
      }
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
