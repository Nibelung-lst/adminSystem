<template xmlns:el-col="http://www.w3.org/1999/html">
  <main>
    <el-row :gutter="12">
      <el-col :span="24">
        <el-card shadow="always">
          <el-row :gutter="12">
            <el-col :span="4">
              <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item>系统列表</el-breadcrumb-item>
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
          <el-row :gutter="20">
            <el-col :span="6">
              <el-button type="primary" plain size="small" @click="handleAddSystem()">新建菜单</el-button>
            </el-col>
            <el-col :span="6":offset="6">
                <el-input
                  placeholder="输入菜单名搜索"
                  suffix-icon="el-icon-date"
                  v-model="input1">
                </el-input>
            </el-col>
            <el-col :span="6">
              <el-button type="primary" plain size="small" @click="handleDelet()">搜索</el-button>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="12" style="margin-top: 20px">
      <el-col :span="24">
    <el-table
      :data="tableData"
      style="width: 100%;margin-bottom: 150px;"
      row-key="id"
      border
      default-expand-all
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
      <el-table-column
        prop="name"
        label="菜单名"
        sortable
        width="300"
      align="center">
      </el-table-column>
      <el-table-column
        prop="url"
        label="路径"
        sortable
        width="400"
        align="center">
      </el-table-column>
      <el-table-column
        prop="component"
        label="描述"
        sortable
        width="200"
        align="center">
      </el-table-column>
      <el-table-column
        label="操作"
        align="center">
        <template slot-scope="scope">
          <el-button type="primary" plain size="small" @click="handleUpdate(scope.row)">修改</el-button>
          <el-button type="primary" plain size="small" @click="handleDelet(scope.row)">删除</el-button>
          <el-button type="primary" plain size="small" @click="handleNewAdd(scope.row)">新建</el-button>
          <el-button type="primary" plain size="small" @click="distribution(scope.row)" >分配权限</el-button>
        </template>
      </el-table-column>
    </el-table>
      </el-col>
    </el-row>

    <el-dialog
      title="新建系统菜单"
      :visible.sync="dialogSystem"
      :before-close="handleClose">
      <el-form ref="sizeForm"
               :model="sizeForm"
               :rules="rules"
               label-width="80px" size="mini">
        <el-form-item label="菜单名" prop="name">
          <el-input v-model="sizeForm.name"></el-input>
        </el-form-item>
        <el-form-item label="菜单路径" prop="url">
          <el-input v-model="sizeForm.url"></el-input>
        </el-form-item>
        <el-form-item label="菜单描述" prop="component">
          <el-input v-model="sizeForm.component"></el-input>
        </el-form-item>
        <el-form-item size="large">
          <el-button type="primary" @click="onSubmit">立即创建</el-button>
          <el-button>取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-dialog
      title="分配菜单给相应角色"
      :visible.sync="dialogVisible"
      :before-close="handleClose">
          <el-card shadow="always">
            <el-row :gutter="20">
              <el-col :span="6":offset="6">
                <el-input
                  placeholder="输入角色名"
                  suffix-icon="el-icon-date"
                  v-model="input2">
                </el-input>
              </el-col>
              <el-col :span="6">
                <el-button type="primary" plain size="small" @click="handleRoleSearch()">搜索</el-button>
              </el-col>
            </el-row>
          </el-card>
      <el-table
        :data="roleDate"
        border
        style="width: 100%">
        <el-table-column
          prop="name"
          label="英文名"
          width="250">
        </el-table-column>
        <el-table-column
          prop="nameZh"
          label="中文名"
          width="250">
        </el-table-column>
        <el-table-column
          prop="address"
          label="操作">
          <template slot-scope="scope">
          <el-button type="primary" plain size="small" @click="authority(scope.row)" :disabled="buttonStatue" >分配权限</el-button>
          </template>
        </el-table-column>
      </el-table>

    </el-dialog>

  </main>

</template>

<script>

  import {
    addAuthority, addMenu,
    checkSystemName,
    checkSystemUrl,
    listMenu,
    listRole,
    listRoleByMid,
    searchRoleByName
  } from "../../api/admin";

  export default {
    mounted () {
      this.send()
    },
    name: 'role',
    inject:['reload'],
    data () {
      let checkName = (rule, value, callback) =>{
        checkSystemName(value).then(res=>{
          if ( res && res.data === true){
            console.log("true")
            callback("该系统名已存在")
          }else if(res && res.data === false){
            callback()
          }
        })
      }
      let checkUrl = (rule, value, callback) =>{
        checkSystemUrl(value).then(res=>{
          if ( res && res.data === true){
            console.log("true")
            callback("该系统路由已存在")
          }else if(res && res.data === false){
            callback()
          }
        })
      }
      return {
        sizeForm: {
          name: '',
          url: '',
          component: '',
          parentId:0
        },
        rules: {
          name: [ { required: true, message: "请输入系统名", trigger: "blur" },
            {validator:checkName, trigger: "blur"}
          ],
          url:[ { required: true, message: "请输入系统路径", trigger: "blur" },
            {validator:checkUrl, trigger: "blur"}
          ],
        },

        input2:'',
        dialogSystem:false,
        buttonStatue:true,
        dialogVisible: false,
        mid:'',
        roleDate:[{
          id:'',
          name:'',
          nameZh:''
        }],
        tableData:[{
          id:'',
          name:'',
          url:'',
          component:''
        }],

      }
    },
    methods: {
      handleNewAdd(scope){
        this.dialogSystem = true
        this.sizeForm.parentId = scope.id
      },
      onSubmit(){
        let params = {
          'name':this.sizeForm.name,
          'url':this.sizeForm.url,
          'component':this.sizeForm.component,
          'parentId':this.sizeForm.parentId
        }
        addMenu(params).then(res =>{
          if (res.data === true){
            this.$message({
              message: '添加成功',
              type: 'success'
            })
            this.send()
          }else {
            this.$message({
              message: '添加失败',
              type: 'warning'
            })
          }
        })
      },
      handleAddSystem(){
        this.dialogSystem = true
        this.sizeForm.parentId = 0
      },
      authority(scope){
        const _this = this
        let param = {
          'menuId': this.mid,
          'roleId': scope.id
        }
        addAuthority(param).then(res=>{
          if (res.data === true){
            this.$message({
              message: '分配成功',
              type: 'success'
            })
            listRoleByMid(_this.mid).then(res =>{
              _this.roleDate = res.data
            })
          }else {
            this.$message({
              message: '分配失败',
              type: 'error'
            })
          }
        })
      },
      handleRoleSearch(){
        const _this = this
        this.buttonStatue = false
        searchRoleByName(_this.input2).then(res =>{
          _this.roleDate = res.data
        })
      },
      distribution(scope){
        const _this = this
        this.dialogVisible = true
        listRoleByMid(scope.id).then(res =>{
          _this.mid=scope.id
          _this.roleDate = res.data
        })
      },
      send () {
        const _this = this
        listMenu().then(res => {
          _this.tableData = res.data
          console.log(this.tableData)
          console.log(res)
        })
      },
    }
  }
</script>

<style scoped>

</style>
