<template>
  <div>
    <router-view v-if="isRouterAlive"></router-view>
  </div>
</template>

<script>
import menuRouterList from './router/menuRouterList'
export default {
  name: 'app',
  provide(){
    return{
      reload:this.reload
    }
  },
  data () {
    return {
      constRouter: [],
      isRouterAlive:true
    }
  },
  created () {
    // axios基本配置
    // 页面加载时读取vuex数据
    if (localStorage.getItem('AuthNoList') != null) {
      this.$store.commit('setAuthNoList', localStorage.getItem('AuthNoList'))
      let AuthNoList = this.$store.state.AuthNoList
      menuRouterList.map((item) => {
        if (AuthNoList.indexOf(item.meta.authNo) >= 0) {
          this.constRouter.push(item)
        }
      })
      this.$router.addRoutes(this.constRouter)
    }

    // 页面刷新时保存vuex数据
    window.addEventListener('beforeunload', () => {
      localStorage.setItem('AuthNoList', this.$store.state.AuthNoList)
    })
    // TODO 配置全局路由守卫

    // TODO 请求拦截器

    // TODO 响应拦截器
  },
  methods: {
    reload(){
      this.isRouterAlive=false,
        this.$nextTick(function () {
          this.isRouterAlive=true;
        })
    }
  }
}
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }
  html, body {
    margin: 0;
    padding: 0;
    border: 0;
    background: #f3f4f6;
  }

  a {
    text-decoration: none;
  }

  hr {
    margin: 0;
    padding: 0;
  }
</style>
