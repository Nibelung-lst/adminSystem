<template>
<main>
  <!--导航栏-->
  <el-row :gutter="12">
    <el-col :span="24">
      <el-card shadow="always">
        <el-row :gutter="12">
          <el-col :span="4">
            <el-breadcrumb separator-class="el-icon-arrow-right">
              <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
              <el-breadcrumb-item>分类管理</el-breadcrumb-item>
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
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="其他">
          <template slot-scope="scope">
            <el-button type="primary" plain size="small" @click="searchChildren(scope.row)">查看下级分类</el-button>
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
    categoryCodeCheck, categoryTopAdd,
    categoryTopHandleCurrentChange,
    categoryTopHandleSizeChange,
    categoryTopSend, categoryTopUpdate
  } from "../../api/product";

export default {
  mounted () {
    this.send()
  },
  name: 'ProductCategory',
  data () {
    return {
      name: '',
      dialogUpdateCategory: false,
      dialogAddCategory: false,
      formUpdateCategory: {
        categoryCode: '',
        name: '',
        status: true
      },
      formAddCategory: {
        categoryCode: '',
        name: '',
        parentCode: 0,
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
      loading: true,
      // 每页几条数据
      pageSize: 5,
      // 总页数
      totalPage: 0,
      // 当前页
      currentPage: 1,
      allCategoryUrl: 'http://localhost:8080/adminback/api/getCategoryNoParent',
      searchCategoryUrl: 'http://localhost:8080/adminback/api/searchCategory' + '?name=' + this.name
    }
  },
  methods: {
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
    searchChildren (row) {
      this.$router.push({ path: '/layout/children', query: { categoryCode: row.categoryCode } }).then(res =>{
        this.$store.commit('setParentCode', row.categoryCode)
      })
    },
    send () {
      let params = {
        'pageNumber':this.currentPage,
        'pageSize':this.pageSize
      }
      const _this = this
      categoryTopSend(params).then(res => {
        _this.tableData = res.data.content
        _this.totalPage = res.data.count
        console.log(this.tableData)
        console.log(res)
      })
    },
    // 每页几条数据变化
    handleSizeChange (val) {
      this.pageSize = val
      let params = {
        'pageNumber':this.currentPage,
        'pageSize':this.pageSize
      }
      categoryTopHandleSizeChange(params).then(res => {
        this.tableData = res.data.content
        console.log(res)
        console.log(this.tableData)
      })
    },
    // 当前页变化
    handleCurrentChange (val) {
      this.currentPage = val
      let params = {
        'pageNumber':this.currentPage,
        'pageSize':this.pageSize
      }
      categoryTopHandleCurrentChange(params).then(res => {
        this.tableData = res.data.content
        console.log(res)
        console.log(this.tableData)
      })
    },
    // 修改分类名
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
      this.$axios.post(this.searchCategoryUrl).then(res => {
        if (res.data.code === 10000) {
          this.tableData = res.data.data.list
          this.allCategoryUrl = this.searchCategoryUrl
        } else {
          this.$message.error('未知错误')
        }
      })
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
            this.$message.success('修改成功')
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
    handleAddSuccess () {
      let productCategoryAO = {
        'categoryCode': this.formAddCategory.categoryCode,
        'name': this.formAddCategory.name,
        'parentCode': 0,
        'status': this.formAddCategory.status
      }
      categoryTopAdd(productCategoryAO).then(res => {
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
