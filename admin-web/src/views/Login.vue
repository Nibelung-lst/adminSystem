<template>
  <el-form :model="formLogin" :rules="rules" class="login-container" label-position="left"
           label-width="0px" ref="formLogin">
    <h3 class="login_title">管理员登录</h3>
    <el-form-item prop="name">
      <el-input auto-complete="off" placeholder="用户名"
                type="text" v-model="formLogin.name"></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input auto-complete="off" placeholder="密码"
                type="password" v-model="formLogin.password"></el-input>
    </el-form-item>
    <el-checkbox class="login_remember"
                 label-position="left">记住密码
    </el-checkbox>
    <el-form-item style="width: 100%">
      <el-button @click="submitClick('formLogin')" style="width: 100%" type="primary">登录</el-button>
    </el-form-item>
  </el-form>
</template>

<script>

import {login} from "../api/admin";
import jwtDecode from 'jwt-decode'

export default {
  data () {
    return {
      rules: {
        name: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
      },
      formLogin: {
        name: '',
        password: ''
      },
      AuthNoListresult: [],
      loading: false
    }
  },
  methods: {

    submitClick (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let adminAO = {
            username: this.formLogin.name,
            password: this.formLogin.password
          }
          login(adminAO).then(res => {
            if (res.status === 200){
              localStorage.token = res.msg;

              const decode =  jwtDecode(res.msg);
              let {authentication:{principal:{name}}} = decode
              console.log(name)
              localStorage.username = name;
              this.$store.commit('setToken', res.msg)
              this.$message.success("登录成功")
              this.$router.push('/layout')
            }else {
              this.$message.error(res.msg)
            }
          })
        } else {
          this.$message.error('请输入用户名和密码！')
        }
      })
    },
  }
}
</script>

<style scoped>
  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 80px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }

</style>
