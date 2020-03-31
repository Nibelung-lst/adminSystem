<template>
<main>
  <!--导航栏-->
  <el-row :gutter="12">
    <el-col :span="24">
      <el-card shadow="always">
        <el-row :gutter="12">
          <el-col :span="5">
            <el-breadcrumb separator-class="el-icon-arrow-right">
              <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
              <el-breadcrumb-item :to="{path:'/product/category'}">分类管理</el-breadcrumb-item>
              <el-breadcrumb-item>子分类管理</el-breadcrumb-item>
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
          <el-col :offset='22' :span="1">
            <el-button @click="handleAdd" type="primary">添加</el-button>
          </el-col>
          <!--添加分类-->
          <el-dialog
            title="添加分类"
            :visible.sync="dialogAddCategory"
            width="30%">
            <el-form :model="formAddCategory">
              <el-form-item :label-width="'100px'" label="分类编号">
                <el-input autocomplete="off" v-model="formAddCategory.categoryCode" @blur="check"></el-input>
              </el-form-item>
              <el-form-item :label-width="'100px'" label="分类名">
                <el-input autocomplete="off" v-model="formAddCategory.name" ></el-input>
              </el-form-item>
              <el-input  v-model="formAddCategory.parentCode" :value= "0" type="hidden"></el-input>
              <el-form-item>
                <el-switch
                  style="display: block"
                  v-model="formAddCategory.status"
                  active-color="#13ce66"
                  inactive-color="#ff4949"
                  active-text="启用"
                  inactive-text="不启用">
                </el-switch>
              </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="handleAddClose">取 消</el-button>
                <el-button type="primary" @click="handleAddSuccess">确 定</el-button>
              </span>
          </el-dialog>
          <!--修改分类-->
          <el-dialog
            title="修改分类"
            :visible.sync="dialogUpdateCategory"
            width="30%">
            <el-form :model="formUpdateCategory">
              <el-input autocomplete="off" v-model="formUpdateCategory.categoryCode" type="hidden"></el-input>
              <el-form-item :label-width="'100px'" label="分类名">
                <el-input autocomplete="off" v-model="formUpdateCategory.name" ></el-input>
              </el-form-item>
              <el-form-item>
                <el-switch
                  style="display: block"
                  v-model="formUpdateCategory.status"
                  active-color="#13ce66"
                  inactive-color="#ff4949"
                  active-text="启用"
                  inactive-text="不启用">
                </el-switch>
              </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="handleUpdateClose">取 消</el-button>
                <el-button type="primary" @click="handleUpdateSuccess">确 定</el-button>
              </span>
          </el-dialog>
          <!--添加品牌-->
          <el-dialog
            title="添加品牌"
            :visible.sync="dialogAddBrand"
            width="30%">
            <el-form :model="formAddBrand">
              <el-form-item :label-width="'100px'" label="品牌名">
                <el-input autocomplete="off" v-model="formAddBrand.name"></el-input>
              </el-form-item>
              <el-form-item :label-width="'100px'" label="品牌描述">
                <el-input  autocomplete="off" v-model="formAddBrand.describe"></el-input>
              </el-form-item>
              <el-form-item :label-width="'100px'" label="品牌编号">
                <el-input  autocomplete="off" v-model="formAddBrand.code" @blur="checkBrand"></el-input>
              </el-form-item>
              <el-form-item>
                <el-switch
                  style="display: block"
                  v-model="formAddBrand.status"
                  active-color="#13ce66"
                  inactive-color="#ff4949"
                  active-text="启用"
                  inactive-text="不启用">
                </el-switch>
              </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="handleAddBrandClose">取 消</el-button>
                <el-button type="primary" @click="handleAddBrandSuccess">确 定</el-button>
              </span>
          </el-dialog>
          <!--------------------------------图片管理------------------------------- -------->
          <el-dialog
            title="分类图片管理"
            :visible.sync="dialogCategoryImage"
            width="60%">
            <el-row :gutter="25">
              <el-col :span="10">
            <el-upload
              class="upload-demo"
              drag
              name="image"
              action="http://adminweb.luckinshop.com:8080/adminback/api/addCategoryImage"
              :data="imageData"
              :before-upload="beforeUpload"
              :on-success="handleSuccess">
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">将文件拖到此处上传分类图片，或<em>点击上传</em></div>
            </el-upload>
            <div class="demo-image">
              <div class="block" v-for="fit in fits" :key="fit">
                <el-image
                  style="width: 100px; height: 100px"
                  :src="url"
                  :fit="fit"></el-image>
              </div>
            </div>
              </el-col>
              <el-col :span="14">
            <el-upload
              class="upload-demo"
              drag
              name="iconImage"
              action="http://adminweb.luckinshop.com:8080/adminback/api/updateIconImage"
              :data="iconImageData"
              :before-upload="beforeUpload"
              :on-success="handleIconSuccess">
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">将文件拖到此处上传分类图标，或<em>点击上传</em></div>
            </el-upload>
            <div class="demo-image">
              <div class="block" v-for="fit in fits" :key="fit">
                <el-image
                  style="width: 100px; height: 100px"
                  :src="iconUrl"
                  :fit="fit"></el-image>
              </div>
            </div>
              </el-col>
            </el-row>
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
        tooltip-effect="dark">
        <el-table-column
          align="center"
          label="分类编号">
          <template slot-scope="scope">{{ scope.row.categoryCode }}</template>
        </el-table-column>
        <el-table-column
          align="center"
          label="分类名">
          <template slot-scope="scope">{{ scope.row.name }}</template>
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
            <el-button type="primary" plain size="small" @click="handleAddBrand(scope.row)">添加品牌</el-button>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="其他">
          <template slot-scope="scope">
            <el-button type="primary" plain size="small" @click="handleSearchSpu(scope.row)">查看商品</el-button>
            <el-button type="primary" plain size="small" @click="imageManage(scope.row)">图片管理</el-button>
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
    brandAdd,
    categoryCodeCheck, categoryTopAdd, categoryTopUpdate, getCategoryIconImage, getCategoryImage,
    queryChildrenCategory,
    queryChildrenCategoryByPageNumber,
    queryChildrenCategoryByPageSize
  } from "../../api/product";

export default {
  mounted () {
    this.send()
  },
  name: 'ProductCategory',
  data () {
    return {
      iconUrl:'',
      fits: ['fill'],
      url: '',
      name: '',
      iconImageData: {
        categoryCode: '',
        imageUrl: ''
      },
      imageData: {
        categoryCode: '',
        imageUrl: ''
      },
      parentCode:'',
      dialogCategoryImage: false,
      dialogUpdateCategory: false,
      dialogAddCategory: false,
      dialogAddBrand: false,
      formUpdateCategory: {
        categoryCode: '',
        name: '',
        status: true
      },
      formAddBrand: {
        code: '',
        categoryCode: '',
        name: '',
        describe: '',
        status: true
      },
      formAddCategory: {
        categoryCode: '',
        name: '',
        parentCode: '',
        status: true
      },
      parentCategoryList: [
        {
          code: 0,
          name: '分类A'
        },
        {
          code: 1,
          name: '分类B'
        }
      ],
      // 数据表格
      tableData: [],
      // 每页几条数据
      pageSize: 5,
      // 总页数
      totalPage: 0,
      // 当前页
      currentPage: 1
    }
  },
  methods: {
    handleIconSuccess () {
      getCategoryIconImage(this.iconImageData.categoryCode).then(res => {
          this.iconUrl = res.data
      })
    },
    imageManage (row) {
      this.dialogCategoryImage = true
      this.imageData.categoryCode = row.categoryCode
      this.iconImageData.categoryCode = row.categoryCode
      getCategoryImage(row.categoryCode).then(res => {
        if (res.data != null){
          this.url = res.data
          this.imageData.imageUrl = res.data
        }
      })
      getCategoryIconImage(row.categoryCode).then(res => {
        if (res.data != null){
          this.iconUrl = res.data
          this.iconImageData.imageUrl = res.data
        }
      })
    },
    handleSearchSpu (row) {
      this.$router.push({ path: '/layout/Spu', query: { categoryCode: row.categoryCode } }).then( res =>{
        this.$store.commit('setCategoryCode', row.categoryCode)
      })
    },
    check () {
      let check = this.formAddCategory.categoryCode
      categoryCodeCheck(check).then(res => {
        if (res.code === 0) {
          this.$message({
            message: '编号重复',
            type: 'warning'
          })
        } else {
          this.$message({
            message: '编号可使用',
            type: 'success'
          })
        }
      })
    },
    send () {
      if (this.$route.query.categoryCode == null){
        this.parentCode = this.$store.state.ParentCode
        this.formAddCategory.parentCode = this.$store.state.ParentCode
      }
      else {
        this.parentCode = this.$route.query.categoryCode
        this.formAddCategory.parentCode = this.$route.query.categoryCode
      }
      let params = {
        'parentCode':this.parentCode,
        'pageNumber':this.currentPage,
        'pageSize':this.pageSize
      }
        queryChildrenCategory(params).then(res => {
        this.tableData = res.data.content
        this.totalPage = res.data.count
        console.log(this.$store.state.ParentCode)
      })
    },
    handleAddBrand (row) {
      this.dialogAddBrand = true
      this.formAddBrand.categoryCode = row.categoryCode
    },
    // 每页几条数据变化
    handleSizeChange (val) {
      this.pageSize = val
      let params = {
        'parentCode':this.$route.query.categoryCode,
        'pageNumber':this.currentPage,
        'pageSize':val
      }
      queryChildrenCategoryByPageSize(params).then(res => {
        this.tableData = res.data.content
        console.log(res)
        console.log(this.tableData)
      })
    },
    handleAddBrandSuccess () {
      let productBrandAO = {
        'categoryCode': this.formAddBrand.categoryCode,
        'code': this.formAddBrand.code,
        'describe': this.formAddBrand.describe,
        'status': this.formAddBrand.status,
        'name': this.formAddBrand.name
      }
      brandAdd(productBrandAO).then(res => {
        if (res.code === 1) {
          this.$message.success('添加品牌成功!')
          this.dialogAddBrand = false
        } else {
          this.$message.error('都跟你讲编号错误了!')
        }
      })
    },
    handleAddBrandClose () {
      this.dialogAddBrand = false
    },
    // 当前页变化
    handleCurrentChange (val) {
      this.currentPage = val
      let params = {
        'parentCode':this.$route.query.categoryCode,
        'pageNumber':val,
        'pageSize':this.pageSize
      }
      queryChildrenCategoryByPageNumber(params).then(res => {
        this.tableData = res.data.content
        console.log(res)
        console.log(this.tableData)
      })
    },
    handleUpdateSuccess () {
      let productCategoryAO = {
        'categoryCode': this.formUpdateCategory.categoryCode,
        'name': this.formUpdateCategory.name,
        'status': this.formUpdateCategory.status
      }
      categoryTopUpdate(productCategoryAO).then(res => {
        if (res.code === 1) {
          this.$message.success('修改成功')
          this.send()
          this.dialogUpdateCategory = false
        } else {
          this.$message.error('未知错误')
        }
      })
    },
    handleSearch () {
    },
    handleUpdateClose () {
      this.dialogUpdateCategory = false
    },
    // 修改商品
    handleUpdate (row) {
      this.formUpdateCategory.categoryCode = row.categoryCode
      this.formUpdateCategory.name = row.name
      this.dialogUpdateCategory = true
    },
    // 修改商品状态
    handleUpdateStatus (row) {
      this.$confirm('此操作将修改与分类状态，是否继续?', '警告', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'error'
      }).then(() => {
        row.status = row.status === false
        let productCategoryAO = {
          'categoryCode': row.categoryCode,
          'status': row.status
        }
        categoryTopUpdate(productCategoryAO).then(res => {
          if (res.code === 1) {
            this.$message.success('状态修改成功')
          }
        })
      })
    },
    // 添加分类
    handleAdd () {
      this.dialogAddCategory = true
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
    handleSuccess () {
      getCategoryImage(this.imageData.categoryCode).then(res => {
        this.url = res.data
      })
    },
    beforeUpload (file) {
      console.log(file.uid)
      return true
    },
    handleAddSuccess () {
      let categoryAO = {
        'categoryCode': this.formAddCategory.categoryCode,
        'name': this.formAddCategory.name,
        'parentCode': this.formAddCategory.parentCode,
        'status': this.formAddCategory.status
      }
      categoryTopAdd(categoryAO).then(res => {
        if (res.code === 1) {
          this.$message.success('添加商品成功!')
          this.send()
          this.dialogAddCategory = false
        } else {
          this.$message.error('都跟你讲编号错误了!')
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
