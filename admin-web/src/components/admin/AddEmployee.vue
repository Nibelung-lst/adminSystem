<template>
  <el-card class="form-container" shadow="never" >
    <el-form :model="employeeInformation"
             :rules="rules"
             ref="couponFrom"
             label-width="100px"
             size="small">
      <el-form-item label="员工姓名：" prop="name">
        <el-input  v-model="employeeInformation.name" class="input-width" ></el-input>
      </el-form-item>
      <el-form-item label="邮箱：" prop="email">
        <el-input  v-model="employeeInformation.email" class="input-width"></el-input>
      </el-form-item>
      <el-form-item label="手机：" prop="phone">
        <el-input v-model="employeeInformation.phone" placeholder="" class="input-width"></el-input>
      </el-form-item>
      <el-form-item label="地址：" prop="address">
        <el-input v-model="employeeInformation.address" placeholder="" class="input-width">
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary"  @click="handleSubmit()">提交</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
  import {addAdmin, check} from "../../api/admin";

  export default {
    name: "AddEmployee",
    data() {
      let checkEmail = (rule, value, callback) =>{
        check(value).then(res=>{
          if ( res && res.data === true){
            console.log("true")
            callback("该邮箱已注册")
          }else if(res && res.data === false){
            callback()
          }
        })
      }

      return {
        employeeInformation: {
          name:'',
          email:'',
          phone:'',
          address:''
        },
        rules: {
          name: [
            {required: true, message: '请输入员工名称', trigger: 'blur'},
            {min: 2, max: 140, message: '长度在 2 到 140 个字符', trigger: 'blur'}
          ],
          email: [ { required: true, message: "请输入邮箱", trigger: "blur" },
            { pattern:/^([0-9A-Za-z\-_\.]+)@([0-9a-z]+\.[a-z]{2,3}(\.[a-z]{2})?)$/g, message: "请输入正确的邮箱", trigger: "blur"},
            {validator:checkEmail, trigger: "blur"}
          ],
          phone: [ { required: true, message: "请输入手机号", trigger: "blur" },
            { pattern:11 && /^((13|14|15|17|18)[0-9]{1}\d{8})$/, message: "请输入正确的手机号", trigger: "blur"}
          ],
        },
      }
    },
    methods: {


      handleSubmit (){
        let params = {
          'name':this.employeeInformation.name,
          'email':this.employeeInformation.email,
          'phone':this.employeeInformation.phone,
          'address':this.employeeInformation.address
        }
        const _this = this
        addAdmin(params).then(res=>{
          if (res.data === true){
            this.$message({
              message: '添加成功',
              type: 'success'
            })
          }else {
            this.$message({
              message: '添加失败',
              type: 'warning'
            })
          }
        })
      },
    }
  }
</script>

<style scoped>

</style>
