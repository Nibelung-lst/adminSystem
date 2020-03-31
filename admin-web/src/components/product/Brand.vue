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
                <el-breadcrumb-item>品牌管理</el-breadcrumb-item>
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
            <el-col :span="5">
            </el-col>
            <el-col :offset='22' :span="1">
              <el-button @click="handleAdd" type="primary">添加</el-button>
            </el-col>
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
                  <el-input type="textarea" autocomplete="off" v-model="formAddBrand.describe"></el-input>
                </el-form-item>
                <el-form-item :label-width="'100px'" label="分类">
                  <el-select placeholder="请选择分类" v-model="formAddBrand.categoryCode" :value= "0" >
                    <el-option
                      :key="category.code"
                      :label="category.name"
                      :value="category.code"
                      v-for="category in categoryList">
                    </el-option>
                  </el-select>
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
          tooltip-effect="dark">
          <el-table-column
            align="center"
            label="品牌编号">
            <template slot-scope="scope">{{ scope.row.code }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="品牌名">
            <template slot-scope="scope">{{ scope.row.name }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="品牌描述">
            <template slot-scope="scope">{{ scope.row.describe }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="创建人">
            <template slot-scope="scope">{{ scope.row.creatorName }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="最后修改人">
            <template slot-scope="scope">{{ scope.row.lastModifyName }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="当前状态"
            prop="status">
            <template slot-scope="scope">
              <el-button :type="scope.row.status === false ?'primary':'danger'"
                         plain size="small"
                         @click="handleUpdateStatus(scope.row)">
                {{scope.row.status === false ? '正常' : '不合作' }}
              </el-button>
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            label="操作">
            <template slot-scope="">
              <el-button type="primary" plain size="small">编辑</el-button>
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            label="其他">
            <template slot-scope="">
              <el-button type="primary" plain size="small">查看详情</el-button>
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
    brandHandleCurrentChange,
    brandHandleSizeChange,
    brandSend,
    handleAddSuccess,
    handleUpdateStatus
  } from "../../api/product";

export default {
  mounted () {
    this.Send()
  },
  name: 'ProductCategory',
  data () {
    return {
      name: '',
      dialogAddBrand: false,
      formAddBrand: {
        name: '',
        describe: '',
        categoryCode: 0
      },
      categoryList: [
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
    Send () {
      let params = {
        'pageNumber':this.currentPage,
        'pageSize':this.pageSize
      }
        brandSend(params).then(res => {
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
        'pageNumber':this.currentPage,
        'pageSize':this.pageSize
      }
      brandHandleSizeChange(params).then(res => {
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
      brandHandleCurrentChange(params).then(res => {
        this.tableData = res.data.content
        console.log(res)
        console.log(this.tableData)
      })
    },
    handleSearch () {

    },
    // 修改商品状态
    handleUpdateStatus (row) {
      this.$confirm('此操作将修改与友商的合作关系，是否继续?', '警告', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'error'
      }).then(() => {
        row.status = row.status === false
        let productBrandAO = {
          'code': row.code,
          'status': row.status
        }
        handleUpdateStatus(productBrandAO).then(res => {
          if (res.code === 1) {
            this.$message.success('状态修改成功')
          }
        })
      })
    },
    // 添加分类
    handleAdd () {
      this.dialogAddBrand = true
    },
    handleAddClose () {
      this.$confirm('是否确认取消?', '警告', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$message.success('open')
        this.dialogAddBrand = false
      })
        .catch(() => {})
    },
    handleAddSuccess () {
      let categoryAO = {
        'categoryCode': this.formAddCategory.categoryCode,
        'name': this.formAddCategory.name,
        'parentCode': 0,
        'status': this.formAddCategory.status
      }
      handleAddSuccess(categoryAO).then(res => {
        if (res.code === 1) {
          this.$message.success('添加品牌成功!')
          this.send()
          this.dialogAddBrand = false
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
