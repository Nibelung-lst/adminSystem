<template>
  <el-container style="height: 100vh; border: 1px solid #eee">
    <el-header style="text-align: right;font-size: 14px;background-color:#409EFF">
      <span style="color: #FFFFFF;float: left;font-size: 20px;font-weight: bold;">后台管理系统</span>
      <el-dropdown @command="handleCommand">
        <i class="el-icon-setting" style="margin-right: 10px;color: #FFFFFF"></i>
        <el-dropdown-menu slot="dropdown" >
          <el-dropdown-item  command="logout">退出</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <span style="color: #FFFFFF">{{adminName}}</span>
    </el-header>
    <el-container>
      <el-aside style="background-color:#f4f5f7" width="210px">
        <el-radio-group v-model="isCollapse" style="margin-bottom: 1px;">
          <el-radio-button :label="false">展开</el-radio-button>
          <el-radio-button :label="true">收起</el-radio-button>
        </el-radio-group>
        <el-menu default-active="1-4-1" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" :collapse="isCollapse" router style="background-color:#f4f5f7">
          <el-submenu :index='index+""' :key="index" v-for="(item, index) in constRouter">
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span slot="title">{{item.meta.title}}</span>
            </template>
            <el-menu-item-group>
              <el-menu-item :index="item.path+'/'+children.path" :key="children.name" v-for="children in item.children">
                {{children.meta.title}}
              </el-menu-item>
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-container>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
    <el-footer style="background-color:#f4f5f7"></el-footer>
  </el-container>
</template>

<script>
import menuRouterList from '../router/menuRouterList'
import { logout} from "../api/admin";

export default {
  data () {
    return {
      constRouter: [],
      isCollapse: true,
      adminName:localStorage["username"]
    }
  },
  created () {
    console.log('log')
    this.$store.commit('setAuthNoList', localStorage.getItem('AuthNoList'))
    let AuthNoList = this.$store.state.AuthNoList
    menuRouterList.map((item) => {
      if (item.meta.authNo === '' || AuthNoList.indexOf(item.meta.authNo) >= 0) {
        this.constRouter.push(item)
        console.log(item)
      }
    })
    this.$router.addRoutes(this.constRouter)
  },
  methods:{
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    handleCommand(command){
      if(command ==='logout'){
        logout().then(res=>{
          if (res.status === 200) {
            localStorage.clear();
            this.$message.success(res.msg);
            this.$router.push('/');
          }
          else {
            this.$message.error(res.msg);
          }
        })
      }
    }
  }
}
</script>

<style lang="scss" scoped>
  .el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }

  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
  }
  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
  }
  .el-main {
    color: #333;
    text-align: center;
  }

  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }

  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }
</style>
