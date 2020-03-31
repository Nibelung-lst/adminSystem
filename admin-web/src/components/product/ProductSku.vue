<template>
<main>
  <!--导航栏-->
  <el-row :gutter="12">
    <el-col :span="24">
      <el-card shadow="always">
        <el-row :gutter="12">
          <el-col :span="9">
            <el-breadcrumb separator-class="el-icon-arrow-right">
              <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
              <el-breadcrumb-item :to="{path:'/product/category'}">分类管理</el-breadcrumb-item>
              <el-breadcrumb-item :to="{path:'/layout/children'}">子分类管理</el-breadcrumb-item>
              <el-breadcrumb-item :to="{path:'/layout/Spu'}">商品spu管理</el-breadcrumb-item>
              <el-breadcrumb-item>商品sku管理</el-breadcrumb-item>
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
        <el-row :gutter="12">
          <el-col :offset='18' :span="1">
            <el-button @click="addStock" type="primary" style="margin-left: 16px;">
              库存
            </el-button>
          </el-col>
          <el-col :offset='1' :span="1">
            <el-button @click="openSpecs" type="primary" style="margin-left: 16px;">
              规格
            </el-button>
          </el-col>
          <el-col :offset='1' :span="1">
            <el-button @click="handleAdd" type="primary">添加</el-button>
          </el-col>
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
                label="创建时间">
                <template slot-scope="scope">{{ scope.row.creatorTime }}</template>
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
                    label="创建时间">
                    <template slot-scope="scope">{{ scope.row.creatorTime }}</template>
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
          <el-dialog
            title="添加属性"
            :visible.sync="dialogUpdateSpecsValue"
            width="30%">
            <el-form :model="formUpdateSpecsValue">
              <el-form-item :label-width="'100px'" label="属性值">
                <el-input autocomplete="off" v-model="formUpdateSpecsValue.name"></el-input>
              </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="handleUpdateValueClose">取 消</el-button>
                <el-button type="primary" @click="handleUpdateValueSuccess">确 定</el-button>
              </span>
          </el-dialog>
          <!--------------------------------修改-------------------------------------------->
          <el-dialog
            title="修改属性"
            :visible.sync="dialogUpdateSpecsKey"
            width="30%">
            <el-form :model="formUpdateSpecsKey">
              <el-form-item :label-width="'100px'" label="属性值">
                <el-input autocomplete="off" v-model="formUpdateSpecsKey.name"></el-input>
              </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="handleUpdateKeyClose">取 消</el-button>
                <el-button type="primary" @click="handleUpdateKeySuccess">确 定</el-button>
              </span>
          </el-dialog>
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
          <!--------------------------------图片管理------------------------------- -------->
          <el-dialog
            title="sku图片管理"
            :visible.sync="dialogSkuImage"
            width="30%">
            <el-upload
              class="upload-demo"
              drag
              name="image"
              action="http://adminweb.luckinshop.com:8080/adminback/api/addSkuImage"
              :data="imageData"
              :before-upload="beforeUpload"
              :on-success="handleSuccess">
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">将文件拖到此处上传商品主图，或<em>点击上传</em></div>
            </el-upload>
            <div class="demo-image">
              <div class="block" v-for="fit in fits" :key="fit">
                <el-image
                  style="width: 100px; height: 100px"
                  :src="url"
                  :fit="fit"></el-image>
              </div>
            </div>
          </el-dialog>
          <!--修改sku---------------------------------------------------------------------->
          <el-dialog
            title="编辑SKU"
            :visible.sync="dialogUpdateSku"
            width="30%">
            <el-form :model="formUpdateSku">
              <el-form-item :label-width="'100px'" label="Sku名字">
                <el-input autocomplete="off" v-model="formUpdateSku.name"></el-input>
              </el-form-item>
              <el-form-item :label-width="'100px'" label="Sku描述">
                <el-input autocomplete="off" v-model="formUpdateSku.describe"></el-input>
              </el-form-item>
              <el-form-item :label-width="'100px'" label="Sku原价">
                <el-input autocomplete="off" v-model="formUpdateSku.originPrice"></el-input>
              </el-form-item>
              <el-form-item :label-width="'100px'" label="Sku现价">
                <el-input autocomplete="off" v-model="formUpdateSku.currentPrice"></el-input>
              </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="handleAddClose">取 消</el-button>
                <el-button type="primary" @click="handleAddSuccess">确 定</el-button>
              </span>
          </el-dialog>
        </el-row>
      </el-card>
    </el-col>
  </el-row>
  <!--数据表格-->
  <el-row :gutter="12" style="margin-top: 20px">
    <el-col :span="24">
      <el-table
        :data="tableData"
        border
        style="width: 100%"
        tooltip-effect="dark"
        @selection-change="handleSelectionChange">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item label="创建人">
                <span>{{ props.row.creatorName }}</span>
              </el-form-item>
              <el-form-item label="创建时间">
                <span>{{ props.row.creatorTime }}</span>
              </el-form-item>
              <el-form-item label="最后修改人">
                <span>{{ props.row.LastModifyName }}</span>
              </el-form-item>
              <el-form-item label="最后修改人">
                <span>{{ props.row.LastModifyTime }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column
          align="center"
          label="唯一编码">
          <template slot-scope="scope">{{ scope.row.uniqueCode }}</template>
        </el-table-column>
        <el-table-column
          align="center"
          label="SKU编号">
          <template slot-scope="scope">{{ scope.row.skuCode }}</template>
        </el-table-column>
        <el-table-column
          align="center"
          label="SKU名">
          <template slot-scope="scope">{{ scope.row.name }}</template>
        </el-table-column>
        <el-table-column
          align="center"
          label="SKU描述">
          <template slot-scope="scope">{{ scope.row.describe }}</template>
        </el-table-column>
        <el-table-column
          align="center"
          label="SKU初始价格">
          <template slot-scope="scope">{{ scope.row.originPrice }}</template>
        </el-table-column>
        <el-table-column
          align="center"
          label="SKU现价">
          <template slot-scope="scope">{{ scope.row.currentPrice }}</template>
        </el-table-column>
        <el-table-column
          align="center"
          label="当前状态"
          prop="status">
          <template slot-scope="scope">
            <el-button :type="scope.row.status===true?'primary':'danger'"
                       plain size="small"
                       @click="handleUpdateStatus(scope.row)">
              {{ scope.row.status === true ? '正常' : '不启用' }}
            </el-button>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="操作">
          <template slot-scope="scope">
            <el-button type="primary" plain size="small" @click="handleUpdate(scope.row)">编辑</el-button>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="其他">
          <template slot-scope="scope">
            <el-button type="primary" plain size="small" @click="handleImageManage(scope.row)">图片管理</el-button>
          </template>
        </el-table-column>
      </el-table>

    </el-col>
  </el-row>
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
</main>
</template>

<script>
  import {
    addStock,
    createSku,
    getSku,
    getSkuByPage, getSkuImage,
    getSkuKey,
    getSkuValue,
    skuKeyAdd, skuUpdate,
    skuUpdateStatue,
    skuValueAdd, updateSkuKey, updateSkuValue
  } from "../../api/product";

export default {
  mounted () {
    this.send()
  },
  name: 'ProductCategory',
  data () {
    return {
      valueId:'',
      formUpdateSpecsValue: {
        name:''
      },
      dialogUpdateSpecsValue: false,
      keyId:'',
      dialogUpdateSpecsKey: false,
      fits: ['fill'],
      url: '',
      dialogSkuImage: false,
      attributeKeyId: '',
      dialogUpdateSku: false,
      dialogAddSpecsValue: false,
      formUpdateSpecsKey: {
        name:''
      },
      formAddSpecsValue: {
        name: ''
      },
      formAddSpecsKey: {
        name: ''
      },
      imageData: {
        skuCode: '',
        imageUrl:''
      },
      dialogAddSpecsKey: false,
      innerData: [],
      gridData: [],
      uniqueCodeAO: [],
      data1: [],
      spuCode: this.$route.query.spuCode,
      spuName: this.$route.query.spuName,
      name: '',
      drawer: false,
      innerDrawer: false,
      dialogAddSpecs: false,
      formUpdateSku: {
        skuCode: '',
        name: '',
        originPrice: '',
        currentPrice: '',
        describe: ''
      },
      // 数据表格
      tableData: [],
      // 每页几条数据
      pageSize: 5,
      // 总页数
      totalPage: 0,
      // 当前页
      currentPage: 1,
      //选中的数据
      multipleSelection: [],
      syncStockList: []
    }
  },
  methods: {
    handleUpdateValueSuccess () {
      let specsValueAO = {
        'id': this.valueId,
        'name': this.formUpdateSpecsValue.name
      }
      updateSkuValue(specsValueAO).then(res => {
        if (res.code === 1){
          this.$message.success('修改成功!')
          getSkuValue(this.attributeKeyId).then( res =>{
            this.innerData = res.data
          })
          this.dialogUpdateSpecsValue = false
        }
      })
    },
    handleUpdateValueClose () {
      this.dialogUpdateSpecsValue = false
    },
    UpdateValue (row) {
      this.dialogUpdateSpecsValue = true
      this.valueId = row.id
    },
    handleUpdateKeySuccess () {
      let specsKeyAO = {
        'id': this.keyId,
        'name': this.formUpdateSpecsKey.name
      }
      updateSkuKey(specsKeyAO).then(res => {
        if (res.code === 1){
          this.$message.success('修改成功!')
          this.openSpecs()
          this.dialogUpdateSpecsKey = false
        }
      })
    },
    handleUpdateKeyClose () {
      this.dialogUpdateSpecsKey = false
    },
    addStock() {
      this.$confirm('添加库存，是否继续?', '警告', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'error'
      }).then(() => {
        addStock(this.syncStockList)
      })
    },
    handleImageManage (row) {
      this.dialogSkuImage = true
      this.imageData.skuCode = row.skuCode
      getSkuImage(row.skuCode).then(res =>{
        this.url = res.data
        this.imageData.imageUrl = res.data
      })
    },
    handleAddValueSuccess () {
      let specsValueAO = {
        'name': this.formAddSpecsValue.name,
        'attributeKeyId': this.attributeKeyId
      }
      skuValueAdd(specsValueAO).then(res => {
        if (res.code === 1) {
          this.$message.success('添加成功!')
          this.dialogAddSpecsValue = false
          getSkuValue(this.attributeKeyId).then( res =>{
            this.innerData = res.data
          })
        } else {
          this.$message.error('添加失败!')
        }
      })
    },
    addSpecsValue () {
      this.dialogAddSpecsValue = true
    },
    handleAddValueClose () {
      this.dialogAddSpecsValue = false
    },
    handleAddKeySuccess () {
      let specsKeyAO = {
        'name': this.formAddSpecsKey.name,
        'spuCode': this.spuCode
      }
      skuKeyAdd(specsKeyAO).then(res => {
        if (res.code === 1) {
          this.$message.success('添加成功!')
          this.dialogAddSpecsKey = false
          this.openSpecs()
        } else {
          this.$message.error('添加失败!')
        }
      })
    },
    handleAddKeyClose () {
      this.dialogAddSpecsKey = false
    },
    addSpecskey () {
      this.dialogAddSpecsKey = true
    },
    UpdateKey(row) {
      this.dialogUpdateSpecsKey = true
      this.keyId = row.id
    },
    SpaceValue (row) {
      this.attributeKeyId = row.id
      getSkuValue(row.id).then(res => {
        this.innerData = res.data
        this.innerDrawer = true
      })
    },
    openSpecs () {
      getSkuKey(this.spuCode).then(res => {
        this.gridData = res.data
        this.drawer = true
      })
    },
    handleClose (done) {
      this.$confirm('还有未保存的工作哦确定关闭吗？')
        .then(_ => {
          done()
        })
        .catch(_ => {})
    },
    handleSearchSpu (row) {
      this.$router.push({ path: '/layout/ProductSpu', query: { categoryCode: row.categoryCode } })
    },
    send () {
      let params = {
        'spuCode':this.spuCode,
        'pageNumber':this.currentPage,
        'pageSize':this.pageSize
      }
      getSku(params).then(res => {
        this.tableData = res.data.content
        this.totalPage = res.data.count
        console.log(res)
        console.log(this.tableData)
      })
    },
    // 每页几条数据变化
    handleSizeChange (val) {
      this.pageSize = val
      let params = {
        'spuCode':this.spuCode,
        'pageNumber':this.currentPage,
        'pageSize':val
      }
      getSkuByPage(params).then(res => {
        this.tableData = res.data.content
        console.log(res)
        console.log(this.tableData)
      })
    },
    // 当前页变化
    handleCurrentChange (val) {
      this.currentPage = val
      let params = {
        'spuCode':this.spuCode,
        'pageNumber':val,
        'pageSize':this.pageSize
      }
      getSkuByPage(params).then(res => {
        this.tableData = res.data.content
        console.log(res)
        console.log(this.tableData)
      })
    },
    handleSearch () {
    },
    handleUpdateClose () {
      this.dialogUpdateSku = false
    },
    // 修改商品2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222
    handleUpdate (row) {
      this.formUpdateSku.skuCode = row.skuCode
      this.formUpdateSku.describe = row.describe
      this.formUpdateSku.currentPrice = row.currentPrice
      this.formUpdateSku.originPrice = row.originPrice
      this.formUpdateSku.name = row.name
      this.dialogUpdateSku = true
    },
    // 修改商品状态
    handleUpdateStatus (row) {
      this.$confirm('此操作将修改与商品状态，是否继续?', '警告', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'error'
      }).then(() => {
        row.status = row.status === false
        let productCategoryAO = {
          'spuCode': this.spuCode,
          'skuCode': row.skuCode,
          'status': row.status
        }
        skuUpdateStatue(productCategoryAO).then(res => {
          if (res.code === 0) {
            this.$message.success('状态修改成功')
          }
        })
      })
    },
    // 添加分类
    handleAdd () {
      this.$confirm('此操作将会造成原有SKU数据消失', '警告', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        createSku(this.spuCode).then(res => {
          if (res.code === 0) {
            this.send()
          }
        })
      }).catch(() => {})
    },
    handleAddClose () {
      this.$confirm('是否确认取消?', '警告', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$message.success('open')
        this.dialogAddCategory = false
      })
        .catch(() => {})
    },
    handleSuccess (res) {
      getSkuImage(this.imageData.skuCode).then(res =>{
        this.url = res.data
      })
    },
    beforeUpload (file) {
      console.log(file.uid)
      return true
    },
    //选中的数据
    handleSelectionChange(val) {
      let stockDTO = []
      this.multipleSelection = val
      this.multipleSelection.map((item)=> {
        let SyncStockDTO = {
          spuCode: this.spuCode,
          spuName: this.spuName,
          creatorId: '',
          creatorName: '',
          createTime: '',
          skuCode: '',
          skuName: ''
        }
        SyncStockDTO.skuCode = item.skuCode
        SyncStockDTO.skuName = item.name
        SyncStockDTO.creatorId = item.creatorId
        SyncStockDTO.creatorName = item.creatorName
        SyncStockDTO.createTime = item.creatorTime
        stockDTO.push(SyncStockDTO)
      })
      this.syncStockList = stockDTO
      console.log(this.syncStockList)
    },
    // 修改sku-----------------------------------------------------------------------------------------------
    handleAddSuccess () {
      let productSkuAO = {
        'name': this.formUpdateSku.name,
        'originPrice': this.formUpdateSku.originPrice,
        'currentPrice': this.formUpdateSku.currentPrice,
        'describe': this.formUpdateSku.describe,
        'skuCode': this.formUpdateSku.skuCode
      }
      skuUpdate(productSkuAO).then(res => {
        if (res.code === 1) {
          this.$message.success('修改商品成功!')
          this.send()
          this.dialogUpdateSku = false
        }
      })
    }
  }
}
</script>

<style >
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
