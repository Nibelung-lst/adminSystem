<template>
<main>
  <!--导航栏-->
  <el-row :gutter="12">
    <el-col :span="24">
      <el-card shadow="always">
        <el-row :gutter="12">
          <el-col :span="7">
            <el-breadcrumb separator-class="el-icon-arrow-right">
              <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
              <el-breadcrumb-item :to="{path:'/product/category'}">分类管理</el-breadcrumb-item>
              <el-breadcrumb-item :to="{path:'/layout/children'}">子分类管理</el-breadcrumb-item>
              <el-breadcrumb-item>商品spu管理</el-breadcrumb-item>
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
            title="添加商品"
            :visible.sync="dialogAddCategory"
            width="30%">
            <el-form :model="formAddSpu">
              <el-form-item :label-width="'100px'" label="spu商品编号">
                <el-input autocomplete="off" v-model="formAddSpu.spuCode" @blur="check"></el-input>
              </el-form-item>
              <el-form-item :label-width="'100px'" label="商品名">
                <el-input autocomplete="off" v-model="formAddSpu.spuName" ></el-input>
              </el-form-item>
              <el-form-item :label-width="'100px'" label="商品描述">
                <el-input autocomplete="off" v-model="formAddSpu.spuDescribe" ></el-input>
              </el-form-item>
              <el-form-item :label-width="'100px'" label="商品品牌">
                <el-select v-model="formAddSpu.brandCode" filterable placeholder="请选择">
                  <el-option
                    autocomplete="off"
                    v-for="item in options"
                    :key="item.code"
                    :label="item.name"
                    :value="item.code">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="handleAddClose">取 消</el-button>
                <el-button type="primary" @click="handleAddSuccess">确 定</el-button>
              </span>
          </el-dialog>
          <!--修改Spu-->
          <el-dialog
            title="修改spu"
            :visible.sync="dialogUpdateCategory"
            width="30%">
            <el-form :model="formUpdateSpu">
                <el-input autocomplete="off" v-model="formUpdateSpu.spuCode" type="hidden"></el-input>
              <el-form-item :label-width="'100px'" label="商品名">
                <el-input autocomplete="off" v-model="formUpdateSpu.spuName" ></el-input>
              </el-form-item>
              <el-form-item :label-width="'100px'" label="商品描述">
                <el-input autocomplete="off" v-model="formUpdateSpu.spuDescribe" ></el-input>
              </el-form-item>
              <el-form-item :label-width="'100px'" label="默认SKU">
                <el-select v-model="formUpdateSpu.defaultSkuCode" filterable placeholder="请选择">
                  <el-option
                    v-for="item in options"
                    :key="item.skuCode"
                    :label="item.skuCode"
                    :value="item.skuCode">
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item>
                <el-switch
                  style="display: block"
                  v-model="formUpdateSpu.status"
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
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item label="商品描述">
                <span>{{ props.row.spuDescribe }}</span>
              </el-form-item>
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
          align="center"
          label="商品编号">
          <template slot-scope="scope">{{ scope.row.spuCode }}</template>
        </el-table-column>
        <el-table-column
          align="center"
          label="品牌名">
          <template slot-scope="scope">{{ scope.row.brandName }}</template>
        </el-table-column>
        <el-table-column
          align="center"
          label="商品名">
          <template slot-scope="scope">{{ scope.row.spuName }}</template>
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
            <el-button type="primary" plain size="small" @click="imageManage(scope.row)">图片管理</el-button>
            <el-button type="primary" plain size="small" @click="handleUpdate(scope.row)">编辑</el-button>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="其他">
          <template slot-scope="scope">
            <el-button type="primary" plain size="small" @click="handleSearchSku(scope.row)">查看SKU</el-button>
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
    getBrandByCategoryCode, getSkuCodeListBySpuCode,
    querySpu,
    querySpuByPage,
    querySpuByPageNumber,
    querySpuByPageSize, spuAdd,
    spuCodeCheck,
    spuUpdate, spuUpdateStatue
  } from "../../api/product";

export default {
  mounted () {
    this.send()
  },
  name: 'ProductCategory',
  data () {
    return {
      categoryCode: '',
      name: '',
      dialogUpdateCategory: false,
      dialogAddCategory: false,
      formUpdateSpu: {
        spuCode: '',
        spuName: '',
        spuDescribe: '',
        status: '',
        defaultSkuCode: ''
      },
      formAddSpu: {
        spuCode: '',
        spuName: '',
        spuDescribe: '',
        brandCode: '',
        status: false
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
      options: [],
      // 数据表格
      tableData: [],
      loading: true,
      // 每页几条数据
      pageSize: 5,
      // 总页数
      totalPage: 0,
      // 当前页
      currentPage: 1,
    }
  },
  methods: {
    handleSearchSku (row) {
      this.$router.push({ path: '/layout/Sku', query: { spuCode: row.spuCode,spuName: row.spuName } })
    },
    check () {
      let check = this.formAddSpu.spuCode
      spuCodeCheck(check).then(res => {
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
    imageManage(row) {
      this.$router.push({ path: '/layout/image', query: { spuCode: row.spuCode } })
    },
    send () {
      if (this.$route.query.categoryCode == null){
        this.categoryCode = this.$store.state.CategoryCode

      }
      else {
        this.categoryCode = this.$route.query.categoryCode
      }
      let params = {
        'categoryCode':this.categoryCode,
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
    // 每页几条数据变化
    handleSizeChange (val) {
      this.pageSize = val
      let params = {
        'categoryCode':this.categoryCode,
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
        'categoryCode':this.categoryCode,
        'pageNumber':val,
        'pageSize':this.pageSize
      }
      querySpuByPage(params).then(res => {
        this.tableData = res.data.content
        console.log(res)
        console.log(this.tableData)
      })
    },
    // 修改SPU
    handleUpdateSuccess () {
      let productSpuAO = {
        'spuCode': this.formUpdateSpu.spuCode,
        'spuName': this.formUpdateSpu.spuName,
        'spuDescribe': this.formUpdateSpu.spuDescribe,
        'status': this.formUpdateSpu.status,
        'defaultSkuCode': this.formUpdateSpu.defaultSkuCode
      }
      spuUpdate(productSpuAO).then(res => {
        if (res.code === 1) {
          this.$message.success('修改成功')
          this.send()
          this.dialogUpdateCategory = false
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
      getSkuCodeListBySpuCode(row.spuCode).then(res => {
        this.options = res.data
      })
      this.formUpdateSpu.spuCode = row.spuCode
      this.formUpdateSpu.spuDescribe = row.spuDescribe
      this.formUpdateSpu.spuName = row.spuName
      this.formUpdateSpu.status = row.status
      this.dialogUpdateCategory = true
    },
    handleUpdateStatus (row) {
      this.$confirm('此操作将修改商品状态，是否继续?', '警告', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'error'
      }).then(() => {
        row.status = row.status === false
        let productSpuAO = {
          'spuCode': row.spuCode,
          'status': row.status
        }
        spuUpdateStatue(productSpuAO).then(res => {
          if (res.code === 1) {
            this.$message.success('修改成功')
          }
        })
      })
    },
    // 添加spu
    handleAdd () {
      getBrandByCategoryCode(this.categoryCode).then(res => {
        this.options = res.data
        console.log(this.categoryCode)
      })
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
      let productSpuAO = {
        'spuCode': this.formAddSpu.spuCode,
        'spuName': this.formAddSpu.spuName,
        'spuDescribe': this.formAddSpu.spuDescribe,
        'status': this.formAddSpu.status,
        'categoryCode': this.categoryCode,
        'brandCode': this.formAddSpu.brandCode
      }
      spuAdd(productSpuAO).then(res => {
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
