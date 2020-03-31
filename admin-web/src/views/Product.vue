<template>
  <div>
    <!--导航栏-->
    <el-row :gutter="12">
      <el-col :span="24">
        <el-card shadow="always">
          <el-row :gutter="12">
            <el-col :span="4">
              <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item>商品列表</el-breadcrumb-item>
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
              <el-input
                clearable
                placeholder="请输入商品名"
                prefix-icon="el-icon-search"
                v-model="name">
              </el-input>
            </el-col>
            <el-col :offset='15' :span="1">
              <el-button @click="handleSearch" type="primary">查询</el-button>
            </el-col>
            <el-col :offset='1' :span="1">
              <el-button @click="handleAdd" type="primary">添加</el-button>
            </el-col>
            <el-dialog
              title="添加商品"
              :visible.sync="dialogAddProduct"
              width="30%">
              <el-form :model="formAddProductSpu">
                <el-form-item :label-width="'100px'" label="名称">
                  <el-input autocomplete="off" v-model="formAddProductSpu.name"></el-input>
                </el-form-item>
                <el-form-item :label-width="'100px'" label="分类">
                  <el-select placeholder="请选择分类" v-model="formAddProductSpu.categoryCode" :value="1">
                    <el-option
                      :key="category.code"
                      :label="category.name"
                      :value="category.code"
                      v-for="category in categoryList">
                    </el-option>
                  </el-select>
                </el-form-item>
                <el-form-item :label-width="'100px'" label="品牌">
                  <el-select placeholder="请选择品牌" v-model="formAddProductSpu.brandCode" :value="1">
                    <el-option
                      :key="brand.code"
                      :label="brand.name"
                      :value="brand.code"
                      v-for="brand in brandList">
                    </el-option>
                  </el-select>
                </el-form-item>
                <el-form-item :label-width="'100px'" label="描述">
                  <el-input :rows="2" autocomplete="off" placeholder="请输入商品描述"
                            type="textarea" v-model="formAddProductSpu.describe"></el-input>
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
            label="商品编号">
            <template slot-scope="scope">{{ scope.row.spuCode }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="商品名">
            <template slot-scope="scope">{{ scope.row.name }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="分类">
            <template slot-scope="scope">{{ scope.row.categoryName }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="品牌">
            <template slot-scope="scope">{{ scope.row.brandName }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="当前状态"
            prop="status">
            <template slot-scope="scope">
              <el-button :type="scope.row.status===1?'primary':'danger'"
                         plain size="small"
                         @click="handleUpdateStatus(scope.row)">
                {{ scope.row.status === 1 ? '正常' : '已下架' }}
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
          <el-table-column
            align="center"
            label="删除">
            <template slot-scope="">
              <el-button type="danger" plain size="small">删除</el-button>
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
  </div>
</template>
<script>
export default {
  created () {
    this.handleGetAllBrand()
    this.handleGetAllCategory()
    this.handleGetAllProduct()
  },
  mounted () {

  },
  data () {
    return {
      // 搜索栏
      name: '',
      // 数据
      tableData: [
        {
          spuCode: '',
          name: 'redmi K20Pro',
          categoryName: '手机',
          brandName: '小米',
          status: 1
        }
      ],
      formAddProductSpu: {
        categoryCode: 1,
        brandCode: 1,
        brandName: '品牌',
        name: 'spu名',
        describe: 'spu描述',
        url: ''
      },
      categoryList: [
        {
          code: 1,
          name: '分类A'
        },
        {
          code: 2,
          name: '分类B'
        }
      ],
      brandList: [
        {
          code: 0,
          name: '品牌A'
        },
        {
          code: 1,
          name: '品牌B'
        }
      ],
      // 控制窗口显示
      dialogAddProduct: false,
      // 每页几条数据
      pageSize: 5,
      // 总页数
      totalPage: 0,
      // 当前页
      currentPage: 1
    }
  },
  methods: {
    // 每页几条数据变化
    handleSizeChange (val) {
      this.pageSize = val
    },
    // 当前页变化
    handleCurrentChange (val) {
      this.currentPage = val
    },
    // 查询按钮
    handleSearch () {

    },
    // 修改商品状态
    handleUpdateStatus (row) {
      row.status = row.status === 1 ? 0 : 1
    },
    // 添加商品
    handleAdd () {
      this.dialogAddProduct = true
    },
    handleAddClose () {
      this.$confirm('是否确认取消?', '警告', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$message.success('open')
        this.dialogAddProduct = false
      })
        .catch(() => {
        })
    },
    handleAddSuccess () {
      this.$message.success('添加商品成功!')
      this.dialogAddProduct = false
    },
    // 获取分类
    handleGetAllCategory () {

    },
    // 获取品牌
    handleGetAllBrand () {

    },
    // 获取商品
    handleGetAllProduct () {

    }
  }
}
</script>

<style scoped>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }

  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }

  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }

  .el-upload--picture-card {
    background-color: #fbfdff;
    border: 1px dashed #c0ccda;
    border-radius: 6px;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    width: 178px;
    height: 178px;
    line-height: 176px;
    vertical-align: top;
  }
</style>
