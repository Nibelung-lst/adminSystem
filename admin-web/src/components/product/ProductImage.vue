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
                <el-breadcrumb-item>商品spu图片管理</el-breadcrumb-item>
              </el-breadcrumb>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="12" style="margin-top: 20px">
      <!--商品单个图片-->
      <el-col :span="10" :offset="1">
        <el-upload
          class="upload-demo"
          drag
          name="image"
          action="http://adminweb.luckinshop.com:8080/adminback/api/addSpuImage"
          :data="imageData"
          :before-upload="beforeUpload"
          :on-success="handleSuccess">
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">将文件拖到此处上传商品主图，或<em>点击上传</em></div>
        </el-upload>
        <el-table
          :data="tableDataTypeOne"
          border stripe
          style="width: 100%">
          <el-table-column
            prop="id"
            label="id"
            width="75"
            align="center">
          </el-table-column>
          <el-table-column
            prop="url"
            label="图片"
            align="center">
            <template slot-scope="scope">
              <el-image
                :src="scope.row"
                :fit="'fit'"
                style="width: 100px">
                <div class="image-slot" slot="error">
                  <el-image :fit="'fit'"
                            :src="scope.row"
                            style="width: 100px">
                  </el-image>
                </div>
              </el-image>
            </template>
          </el-table-column>
          <el-table-column
            label="操作"
            width="100"
            align="center">
            <template slot-scope="scope">
              <el-button @click="handleDelete(scope)" type="danger" size="small">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
      <!--商品详情图片-->
      <el-col :span="10" :offset="2">
        <el-upload
          class="upload-demo"
          drag
          name="image"
          action="http://adminweb.luckinshop.com:8080/adminback/api/addSpuImage"
          :data="imageDetail"
          :before-upload="beforeUpload"
          :on-success="handleSuccess">
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">将文件拖到此处上传商品详情图，或<em>点击上传</em></div>
        </el-upload>
        <el-table
          :data="tableDataTypeTwo"
          border stripe
          style="width: 100%">
          <el-table-column
            prop="id"
            label="id"
            width="75"
            align="center">
          </el-table-column>
          <el-table-column
            prop="url"
            label="图片"
            align="center">
            <template slot-scope="scope">
              <el-image
                :src="scope.row"
                :fit="'fit'"
                style="width: 100px">
                <div class="image-slot" slot="error">
                  <el-image :fit="'fit'"
                            :src="scope.row"
                            style="width: 100px">
                  </el-image>
                </div>
              </el-image>
            </template>
          </el-table-column>
          <el-table-column
            label="操作"
            width="100"
            align="center">
            <template slot-scope="scope">
              <el-button @click="handleDelete(scope)" type="danger" size="small">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
  </main>
</template>

<script>
  import {deleteSpuImage, getSpuDetail, getSpuImage} from "../../api/product";

export default {
  mounted () {
    this.send()
  },
  name: 'ProductImage',
  data () {
    return {
      // 商品单个图片
      tableDataTypeOne: [],
      // 商品详情图片
      tableDataTypeTwo: [],
      imageData: {
        spuCode: this.$route.query.spuCode,
        type: true
      },
      imageDetail: {
        spuCode: this.$route.query.spuCode,
        type: false
      }
    }
  },
  methods: {
    send() {
      let val = this.$route.query.spuCode
      getSpuImage(val).then(res =>{
        console.log(res.data)
        this.tableDataTypeOne = res.data
      })
      getSpuDetail(val).then(res =>{
        this.tableDataTypeTwo = res.data
      })
    },
    handleDelete (row) {
      deleteSpuImage(row.row).then(res => {
        this.send()
      })
    },
    handleSuccess (res) {
      this.send()
      console.log(res)
    },
    beforeUpload (file) {
      console.log(file.uid)
      return true
    }
  }
}
</script>

<style scoped>

</style>
