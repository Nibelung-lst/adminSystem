<template>
  <el-card class="form-container" shadow="never">
    <el-form :model="addAdvertisementForm"
             :rules="rules"
             ref="couponFrom"
             label-width="150px"
             size="small">
      <el-form-item label="投放平台：">
        <el-select v-model="addAdvertisementForm.platform" collapse-tags>
          <el-option
            v-for="type in platformOptions"
            :key="type.value"
            :label="type.label"
            :value="type.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="广告位置：">
        <el-select v-model="addAdvertisementForm.location" collapse-tags>
          <el-option
            v-for="type in locationOptions"
            :key="type.value"
            :label="type.label"
            :value="type.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="名称：" prop="name">
        <el-input v-model="addAdvertisementForm.name" class="input-width"></el-input>
      </el-form-item>
      <el-form-item label="有效期：">
        <el-date-picker type="datetime" placeholder="选择日期" v-model="addAdvertisementForm.startTime" style="width: 150px"></el-date-picker>
        <span style="margin-left: 20px;margin-right: 20px">至</span>
        <el-date-picker type="datetime" placeholder="选择日期" v-model="addAdvertisementForm.endTime" style="width: 150px"></el-date-picker>
      </el-form-item>
      <el-form-item label="广告图片：">
        <el-upload
          class="upload-demo"
          ref="upload"
          action="http://adminweb.luckinshop.com:8080/adminback/uploadImage"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          :on-success="handleSuccess"
          :file-list="fileList"
          :auto-upload="false"
        v-model="addAdvertisementForm.image">
          <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
          <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
        </el-upload>
      </el-form-item>
      <el-form-item label="排序：" prop = "sort">
        <el-input v-model="addAdvertisementForm.sort" class="input-width"></el-input>
      </el-form-item>
      <el-form-item label="广告链接：" prop="url">
        <el-input v-model="addAdvertisementForm.skuCode" class="input-width"></el-input>
      </el-form-item>
      <el-form-item label="是否上线" >
        <el-radio-group v-model="addAdvertisementForm.status">
          <el-radio label="1">是</el-radio>
          <el-radio label="0">否</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="备注：">
        <el-input
          class="input-width"
          type="textarea"
          :rows="5"
          placeholder="请输入内容"
          v-model="addAdvertisementForm.note">
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" v-if="arr.includes('MARKETING_CREATE_ADVERTISEMENT')" @click="handleSubmit()">提交</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
import { createAdvertisement } from '../../api/marketing'
export default {
  name: 'AddAdvertisement',
  data () {
    return {
        arr:[],
        rules: {
            name: [
                {required: true, message: '请输入广告名称', trigger: 'blur'},
                {min: 2, max: 140, message: '长度在 2 到 140 个字符', trigger: 'blur'}
            ],
            sort: [
                {type: 'number',required: true,message: '只能输入正整数',trigger: 'blur'}
            ]
        },
        locationOptions: [
            {
                label: '首页轮播',
                value: 1
            },
            {
                label: '左侧',
                value: 2
            },
            {
                label: '右侧',
                value: 3
            },
            {
                label: '底部',
                value: 4
            }
        ],
        platformOptions: [
            {
                label: '小程序',
                value: 1
            },
            {
                label: 'PC',
                value: 2
            },
            {
                label: 'APP',
                value: 3
            }
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
      addAdvertisementForm: {},
        fileList:[]
    }
  },
  mounted(){
      this.handleShowButton()

  },
  methods: {
      handleShowButton(){
          this.arr =  localStorage.getItem("ButtonList");
      },
    handleSubmit () {
      let params = this.addAdvertisementForm
      console.log('kkan')
      console.log(params)
        createAdvertisement(params).then(res => {
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
      submitUpload() {
          this.$refs.upload.submit();

      },
      handleRemove(file, fileList) {
          console.log(file, fileList);
      },
      handlePreview(file) {
          console.log(file);
      },
      handleSuccess(response){
          this.addAdvertisementForm.image=response.data;
          console.log(this.addAdvertisementForm.image)
      },
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
