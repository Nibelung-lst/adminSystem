<template xmlns:el-col="http://www.w3.org/1999/html">
  <main>
    <el-row :gutter="12">
      <el-col :span="24">
        <el-card shadow="always">
          <el-row :gutter="12">
            <el-col :span="4">
              <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item>系统列表</el-breadcrumb-item>
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
          <el-row :gutter="20">
            <el-col :span="6">
              <el-button type="primary" plain size="small" @click="handleAddSystem()">新建商品</el-button>
            </el-col>
            <el-col :span="6":offset="6">
              <el-cascader
                placeholder="试试搜索：指南"
                :options="options"
                filterable></el-cascader>
            </el-col>
            <el-col :span="6">
              <el-button type="primary" plain size="small" @click="handleDelet()">搜索</el-button>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <el-dialog
      title="添加商品"
      :visible.sync="dialogAddProduct"
      width="30%">
      <el-form :model="formAddSpu">
        <el-form-item :label-width="'100px'" label="商品名">
          <el-input autocomplete="off" v-model="formAddSpu.spuName" ></el-input>
        </el-form-item>
        <el-form-item :label-width="'100px'" label="商品描述">
          <el-input autocomplete="off" v-model="formAddSpu.spuDescribe" ></el-input>
        </el-form-item>
        <el-form-item :label-width="'100px'" label="商品品牌">
          <el-input v-model="formAddSpu.brandName"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
                <el-button @click="handleAddClose">取 消</el-button>
                <el-button type="primary" @click="handleAddSuccess">确 定</el-button>
              </span>
    </el-dialog>

    <el-row :gutter="12" style="margin-top: 20px">
      <el-col :span="24">
        <el-table
          :data="tableData"
          border
          style="width: 100%">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-form label-position="left" inline class="demo-table-expand">
                <el-form-item label="商品名称">
                  <span>{{ props.row.spuName }}</span>
                </el-form-item>
                <el-form-item label="所属店铺">
                  <span>{{ props.row.shop }}</span>
                </el-form-item>
                <el-form-item label="商品 ID">
                  <span>{{ props.row.spuCode }}</span>
                </el-form-item>
                <el-form-item label="店铺 ID">
                  <span>{{ props.row.shopId }}</span>
                </el-form-item>
                <el-form-item label="商品分类">
                  <span>{{ props.row.category }}</span>
                </el-form-item>
                <el-form-item label="店铺地址">
                  <span>{{ props.row.address }}</span>
                </el-form-item>
                <el-form-item label="商品描述">
                  <span>{{ props.row.spuDescribe }}</span>
                </el-form-item>
              </el-form>
            </template>
          </el-table-column>
          <el-table-column
            label="商品 ID"
            prop="spuCode"
            width="100">
          </el-table-column>
          <el-table-column
            label="商品名称"
            prop="spuName">
          </el-table-column>
          <el-table-column
            label="描述"
            prop="spuDescribe">
          </el-table-column>
          <el-table-column
            prop="address"
            label="操作">
            <template slot-scope="scope">
              <el-button type="primary" plain size="small" @click="skuDetail(scope.row)"  >修改</el-button>
              <el-button type="primary" plain size="small" @click="skuDetail(scope.row)"  >查看sku</el-button>
              <el-button type="primary" plain size="small" @click="spuSpecs(scope.row)"  >查看属性</el-button>
              <el-button type="primary" plain size="small" @click="viewPrice(scope.row)"  >查看价格</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
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

    <el-dialog
      title="sku列表"
      :visible.sync="dialogSystem"
      :before-close="handleClose">
      <el-card shadow="always">
        <el-row :gutter="20">
          <el-col :span="6">
          </el-col>
          <el-col :span="6">
            <el-button type="primary" plain size="small" @click="handleFlash()">刷新sku</el-button>
          </el-col>
        </el-row>
      </el-card>
      <el-table
        :data="skuDate"
        border
        style="width: 100%">
        <el-table-column
          prop="skuCode"
          label="skuCode"
          width="150">
        </el-table-column>
        <el-table-column
          prop="name"
          label="skuName"
          width="100">
        </el-table-column>
        <el-table-column
          prop="describe"
          label="描述"
          width="350">
        </el-table-column>
        <el-table-column
          prop="address"
          label="操作">
          <template slot-scope="scope">
            <el-button type="primary" plain size="small" @click="changeRole(scope.row)" >修改</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>

    <el-dialog
      title="选择商品"
      :visible.sync="dialogSpuDetail"
      :before-close="handleClose">
      <div v-for="(option, index) in options">
        <p align="left">{{option.name}}</p>
        <div class="row" v-for="(item, ind) in option.productSkuValueDTO" @click="select(index, ind)">
          <input type="radio" :name="index" :id="item.id" value="">
          <!-- 关键在于name这里设为index，让每一行选项的name一样，属性id和label的for属性一致 -->
          <label :for="item.id">{{item.name}}</label>
        </div>
      </div>
      <el-button style="margin-left: 520px;margin-top: 120px" type="primary" plain size="small" @click="puch()"  >购买</el-button>
    </el-dialog>


    <!--抽屉-------------------------------------------------------------------------->
    <el-drawer
      title="属性"
      :visible.sync="drawer"
      size="50%">
      <el-button style="margin-left: 640px; margin-bottom: 20px" type="primary" plain size="small" @click="addSpecskey">添加属性</el-button>
      <el-table :data="gridData"
                border
                style="width: 100%"
                tooltip-effect="dark">
        <el-table-column
          align="center"
          label="属性名">
          <template slot-scope="scope">{{ scope.row.name }}</template>
        </el-table-column>
        <el-table-column
          align="center"
          label="创建人">
          <template slot-scope="scope">{{ scope.row.creatorName }}</template>
        </el-table-column>
        <el-table-column
          align="center"
          label="描述">
          <template slot-scope="scope">{{ scope.row.name }}</template>
        </el-table-column>
        <el-table-column
          align="center"
          label="操作">
          <template slot-scope="scope">
            <el-button type="primary" plain size="small" @click="UpdateKey(scope.row)">编辑</el-button>
            <el-button type="primary" plain size="small" @click="SpaceValue(scope.row)">查看</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div>
        <!--------第二个表格----------->
        <el-drawer
          title= "属性值"
          :append-to-body="true"
          :before-close="handleClose"
          :visible.sync="innerDrawer">
          <el-button style="margin-left: 375px; margin-bottom: 20px" type="primary" plain size="small" @click="addSpecsValue">添加值</el-button>
          <el-table :data="innerData"
                    border
                    style="width: 100%"
                    tooltip-effect="dark">
            <el-table-column
              align="center"
              label="属性值">
              <template slot-scope="scope">{{ scope.row.name }}</template>
            </el-table-column>
            <el-table-column
              align="center"
              label="创建人">
              <template slot-scope="scope">{{ scope.row.creatorName }}</template>
            </el-table-column>
            <el-table-column
              align="center"
              label="描述">
              <template slot-scope="scope">{{ scope.row.name }}</template>
            </el-table-column>
            <el-table-column
              align="center"
              label="操作">
              <template slot-scope="scope">
                <el-button type="primary" plain size="small" @click="UpdateValue(scope.row)">编辑</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-drawer>
      </div>
    </el-drawer>
    <!--------------------------------------------------------------------------->

    <!--------------------------------添加-------------------------------------------->
    <el-dialog
      title="添加属性"
      :visible.sync="dialogAddSpecsValue"
      width="30%">
      <el-form :model="formAddSpecsValue">
        <el-form-item :label-width="'100px'" label="属性值">
          <el-input autocomplete="off" v-model="formAddSpecsValue.name"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
                <el-button @click="handleAddValueClose">取 消</el-button>
                <el-button type="primary" @click="handleAddValueSuccess">确 定</el-button>
              </span>
    </el-dialog>
    <!--------------------------------添加-------------------------------------------->
    <el-dialog
      title="添加属性"
      :visible.sync="dialogAddSpecsKey"
      width="30%">
      <el-form :model="formAddSpecsKey">
        <el-form-item :label-width="'100px'" label="属性名">
          <el-input autocomplete="off" v-model="formAddSpecsKey.name"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
                <el-button @click="handleAddKeyClose">取 消</el-button>
                <el-button type="primary" @click="handleAddKeySuccess">确 定</el-button>
              </span>
    </el-dialog>
  </main>

</template>

<script>

  import {
    addAuthority, addMenu,
    checkSystemName,
    checkSystemUrl,
    listMenu,
    listRole,
    listRoleByMid,
    searchRoleByName
  } from "../../api/admin";
  import {
    createSku, getAttributeBySpuCode, getSku,
    getSkuBySpuCode,
    getSkuKey,
    getSkuValue, querySku,
    querySpu,
    querySpuByPage, skuKeyAdd, skuValueAdd,
    spuAdd
  } from "../../api/product";

  export default {
    mounted () {
      this.send()
    },
    name: 'role',
    inject:['reload'],
    data () {
      let checkName = (rule, value, callback) =>{
        checkSystemName(value).then(res=>{
          if ( res && res.data === true){
            console.log("true")
            callback("该系统名已存在")
          }else if(res && res.data === false){
            callback()
          }
        })
      }
      let checkUrl = (rule, value, callback) =>{
        checkSystemUrl(value).then(res=>{
          if ( res && res.data === true){
            console.log("true")
            callback("该系统路由已存在")
          }else if(res && res.data === false){
            callback()
          }
        })
      }
      return {

        dialogAddSpecsValue: false,
        dialogAddSpecsKey: false,
        drawer: false,
        gridData: [],
        innerDrawer: false,
        innerData: [],

        formAddSpecsValue: {
          name: ''
        },
        formAddSpecsKey: {
          name: ''
        },


        dialogAddProduct:false,

        skuDate:[{
          skuCode:'',
          name:'',
          describe:'',
          status:'',
        }],


        id: [],

        options: [{
          productSkuValueDTO: [{
            'name': '',
            "id": ""
          }],
          name: ''
        }],



        formAddSpu: {
          spuName: '',
          spuDescribe: '',
          status: false
        },


        // 每页几条数据
        pageSize: 5,
        // 总页数
        totalPage: 0,
        // 当前页
        currentPage: 1,
        spuCode:'',
        sizeForm: {
          name: '',
          url: '',
          component: '',
          parentId:0
        },
        rules: {
          name: [ { required: true, message: "请输入系统名", trigger: "blur" },
            {validator:checkName, trigger: "blur"}
          ],
          url:[ { required: true, message: "请输入系统路径", trigger: "blur" },
            {validator:checkUrl, trigger: "blur"}
          ],
        },
        dialogSpuDetail:false,
        dialogSystem:false,
        buttonStatue:true,
        dialogVisible: false,
        attributeKeyId:'',

        skuMsg:{
          skuCode:'',
          originPrice:'',
          currentPrice:'',
          describe:''
        },


        tableData: [{
          spuCode:'',
          spuName:'',
          spuDescribe:'',
        }]
      }
    },
    methods: {
      puch(){
        let params={
          'id':this.id,
          'spuCode':this.spuCode
        }
        querySku(params).then(res =>{
          this.skuMsg = res.data;
        })
        console.log(this.skuMsg)
      },
      addSpecsValue(){
        this.dialogAddSpecsValue = true
      },
      addSpecskey() {
        this.dialogAddSpecsKey = true
      },

      handleAddKeySuccess () {
        let specsKeyAO = {
          'name': this.formAddSpecsKey.name,
          'spuCode': this.spuCode
        }
        skuKeyAdd(specsKeyAO).then(res => {
          if (res.data === true) {
            this.dialogAddSpecsKey = false
            getSkuKey(this.spuCode).then(res => {
              this.gridData = res.data
              this.drawer = true
            })
          }
        })
      },
      handleAddValueSuccess () {
        let specsValueAO = {
          'name': this.formAddSpecsValue.name,
          'attributeKeyId': this.attributeKeyId
        }
        skuValueAdd(specsValueAO).then(res => {
          if (res.data === true) {
            this.dialogAddSpecsValue = false
            getSkuValue(this.attributeKeyId).then( res =>{
              this.innerData = res.data
            })
          }
        })
      },
      handleFlash(){
        createSku(this.spuCode).then(res=>{
          if (res.data === true){
            getSkuBySpuCode(this.spuCode).then(res=>{
              this.skuDate = res.data;
            })
          }
        })
      },
      spuSpecs(row){
        this.drawer = true
        getSkuKey(row.spuCode).then(res => {
          this.gridData = res.data
          this.spuCode = row.spuCode
          console.log(this.gridData)
          this.drawer = true
        })
      },
      SpaceValue (row) {
        this.innerDrawer = true
        this.attributeKeyId = row.id
        getSkuValue(row.id).then(res => {
          this.innerData = res.data
          this.innerDrawer = true
        })
      },

      handleAddSystem(){
        this.dialogAddProduct =true
      },
      handleAddSuccess() {
        let productSpuAO = {
          'spuName': this.formAddSpu.spuName,
          'spuDescribe': this.formAddSpu.spuDescribe,
          'brandName':this.formAddSpu.brandName,
          'status': this.formAddSpu.status,
        }
        spuAdd(productSpuAO).then(res => {
          if (res.data === true) {
            this.$message.success('添加商品成功!')
            this.send()
            this.dialogAddProduct = false
          }
        })
      },


      select(index, ind) {
        var itemId = this.options[index].productSkuValueDTO[ind].id; //获取选中的id号
        this.id.splice(index, 1, itemId); //替换数组id[]中对应的元素，获得所有选中的id
        console.log(this.id);
      },

      viewPrice(row){
        this.spuCode = row.spuCode
        this.id = []
        this.dialogSpuDetail = true
        getAttributeBySpuCode(row.spuCode).then(res=>{
          this.options = res.data
        })
      },
      skuDetail(row){
        this.dialogSystem = true
        getSkuBySpuCode(row.spuCode).then(res=>{
          this.spuCode = row.spuCode
          this.skuDate = res.data;
          console.log(this.skuDate)
        })
      },

      // 每页几条数据变化
      handleSizeChange (val) {
        this.pageSize = val
        let params = {
          'pageNumber':this.currentPage,
          'pageSize':val
        }
        querySpuByPage(params).then(res => {
          this.tableData = res.data.content
          console.log(res)
          console.log(this.tableData)
        })
      },
      // 当前页变化
      handleCurrentChange (val) {
        this.currentPage = val
        let params = {
          'pageNumber':val,
          'pageSize':this.pageSize
        }
        querySpuByPage(params).then(res => {
          this.tableData = res.data.content
          console.log(res)
          console.log(this.tableData)
        })
      },

      send () {
        let params = {
          'pageNumber':this.currentPage,
          'pageSize':this.pageSize
        }
        querySpu(params).then(res => {
          this.tableData = res.data.content
          this.totalPage = res.data.count
          console.log(res)
          console.log(this.tableData)
        })
      },
    }
  }
</script>

<style scoped>
  input {
    display: none;
  }

  input[type="radio"]+label {
    /* 默认label的样式 */
    /* content: "\a0"; */
    display: inline-block;
    padding: 10px 20px;
    background-color: #666;
    margin-left: 20px;
    color: #fff;
    border-radius: 10px;
  }

  input[type="radio"]:checked+label {
    /* 当点击label的时候背景颜色发生改变 */
    background-color: aqua;
  }

  .row {
    display: inline-block;
  }
</style>
