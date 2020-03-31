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
              <el-button type="primary" plain size="small" @click="handleAddSystem()">添加员工</el-button>
              <el-button type="primary" plain size="small" @click="handleRole()">查看职位</el-button>
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

    <el-dialog
      title="职位表"
      :visible.sync="dialogRole"
      :before-close="handleClose">
      <el-card shadow="always">
        <el-row :gutter="20">
          <el-col :span="6">
            <el-input
              placeholder="输入职位名"
              v-model="input2">
            </el-input>
          </el-col>
          <el-col :span="6">
            <el-button type="primary" plain size="small" @click="handleRoleSearch()">搜索</el-button>
          </el-col>

          <el-col :span="6":offset="6">
            <el-button type="primary" plain size="small" @click="handleAddRole()">新增职位</el-button>
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
            <el-button type="primary" plain size="small" @click="authority(scope.row)" >修改</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>

    <el-dialog
      title="职位表"
      :visible.sync="dialogChangeRole"
      :before-close="handleClose">
      <el-card shadow="always">
        <el-row :gutter="20">
          <el-col :span="6">
            <el-input
              placeholder="输入职位名"
              v-model="input2">
            </el-input>
          </el-col>
          <el-col :span="6">
            <el-button type="primary" plain size="small" @click="handleRoleSearch()">搜索</el-button>
          </el-col>
          <el-col :span="6">
            <span>当前职位：{{this.employee.role}}</span>
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
            <el-button type="primary" plain size="small" @click="changeRole(scope.row)" >修改</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>


    <el-row :gutter="12" style="margin-top: 20px">
      <el-col :span="8" v-for="(employee,i) in employeeList" :key="employee">
        <el-card :body-style="{ padding: '0px'} ">
          <el-avatar :size="150" :src="employee.userFace"></el-avatar>
          <div style="padding: 14px;">
            <span>{{employee.name}}</span></div>
          <div>
            <span style="font-size: 12px;color: #909399">{{employee.role}}</span>
          </div>
          <el-divider></el-divider>
            <el-button @click="getEmployDetail(employee)">联系方式</el-button>
            <el-button @click="updateRole(employee)">修改职位</el-button>
            <div class="bottom clearfix">

            </div>
        </el-card>
      </el-col>
    </el-row>

    <el-dialog
      title="添加职位"
      :visible.sync="dialogSystemRole"
      :before-close="handleClose">
      <el-form :model="roleInformation"
               :rules="roleRule"
               ref="employeeInformation"
               label-width="100px"
               size="small">
        <el-form-item label="英文名：" prop="name">
          <el-input  v-model="roleInformation.name" class="input-width" ></el-input>
        </el-form-item>
        <el-form-item label="中文名：" prop="nameZh">
          <el-input  v-model="roleInformation.nameZh" class="input-width"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary"  @click="handleRoleSubmit()">提交</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-dialog
      title="添加员工"
      :visible.sync="dialogSystem"
      :before-close="handleClose">
      <el-form :model="employeeInformation"
               :rules="rule"
               ref="employeeInformation"
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
    </el-dialog>

    <el-dialog
      title="联系方式"
      :visible.sync="dialogVisible"
      :before-close="handleClose">
      <el-form ref="form" :model="employee" label-width="80px" size="mini">
        <el-form-item label="手机号:">
          <span>{{employee.phone}}</span>
        </el-form-item>
        <el-form-item label="电话:">
          <span>{{employee.telephone}}</span>
        </el-form-item>
        <el-form-item label="地址:">
          <span>{{employee.address}}</span>
        </el-form-item>
        <el-form-item label="邮件:">
          <span>{{employee.email}}</span>
        </el-form-item>
        <el-form-item label="备注:">
          <span>{{employee.remark}}</span>
        </el-form-item>

      </el-form>

    </el-dialog>

  </main>

</template>

<script>

  import {
    addAdmin,
    addAuthority, addMenu, addRole, check,
    checkSystemName,
    checkSystemUrl, getAllAdmin, givePosition,
    listMenu,
    listRole,
    listRoleByMid,
    searchRoleByName
  } from "../../api/admin";

  export default {

    mounted () {
      this.send()
    },
    name: 'employee',
    inject:['reload'],
    data () {
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

        dialogSystemRole:false,
        roleInformation:{
          name:'',
          nameZh: '',
        },
        roleRule:{

          name:[
            {required: true, message: '请输入职位英文名', trigger: 'blur'},
            {pattern:/^[A-Za-z]+$/, message: '请输入正确的职位英文名', trigger: 'blur'}
          ],
          nameZh:[
            {required: true, message: '请输入职位中文名', trigger: 'blur'},
          ],
        },
        rule: {
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
        dialogChangeRole:false,
        employeeInformation: {
          name:'',
          email:'',
          phone:'',
          address:''
        },
        employeeList:[{
          id:'',
          name:'',
          phone:'',
          telephone:'',
          userFace:'',
          username:'',
          remark:'',
          address:'',
          role:'',
        }],
        employee:{
          id:'',
          email:'',
          phone:'',
          address:'',
          telephone:'',
          username:'',
          remark:'',
          role:''
        },
        sizeForm: {
          name: '超级管理员',
          url: '',
          component: '',
          parentId:0
        },
        input2:'',
        dialogRole:false,
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
      handleRoleSubmit(){
        let params = {
          'name':'ROLE_'+this.roleInformation.name,
          'nameZh':this.roleInformation.nameZh
        }
        addRole(params).then(res=>{
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
      handleAddRole(){
        this.dialogSystemRole = true
      },
      changeRole(scope){
        let params = {
          'roleId':scope.id,
          'adminId':this.employee.id
        }
        givePosition(params).then(res=>{
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
      updateRole(employee1){
        this.employee.id= employee1.id
        this.employee.role = employee1.role
        this.dialogChangeRole = true;

      },
      handleRole(){
        this.dialogRole = true;
      },
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
      getEmployDetail(employee1){
        console.log(employee1)
        this.employee.email = employee1.username
        this.employee.phone = employee1.phone
        this.employee.remark = employee1.remark
        this.employee.address = employee1.address
        this.employee.telephone = employee1.telephone
        this.dialogVisible = true;

      },
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
        getAllAdmin().then(res => {
          _this.employeeList = res.data
          console.log(this.tableData)
          console.log(res)
        })
      },
    }
  }
</script>

<style scoped>

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }
  .image{
    width: 20%;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }


  .clearfix:after {
    clear: both
  }
</style>
