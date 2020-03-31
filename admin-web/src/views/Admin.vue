<template>
  <main>
    <el-row :gutter="12">
      <el-col :span="24">
        <el-card shadow="always">
          <el-row :gutter="12">
            <el-col :span="4">
              <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item>管理员列表</el-breadcrumb-item>
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
                placeholder="请输入管理员名称"
                prefix-icon="el-icon-search"
                v-model="name">
              </el-input>
            </el-col>
            <el-col :offset='15' :span="1">
              <el-button  v-if="arrTemp.includes('ADMIN_SELECT_ADMIN')" @click="handleSearch" type="primary">查询</el-button>
            </el-col>
            <el-col :offset='1' :span="1">
              <el-button v-if="arrTemp.includes('ADMIN_ADD_ADMIN')" @click="handleAdd" type="primary">添加</el-button>
            </el-col>
            <el-dialog
              title="添加管理员"
              :visible.sync="dialogAddAdmin"
              width="35%">
              <el-form :model="formAddAdmin">
                <el-form-item :label-width="'100px'" label="名称">
                  <el-input autocomplete="off" v-model="formAddAdmin.name"  @blur="handleAddSearch"></el-input>
                </el-form-item>
                <el-form-item :label-width="'100px'" label="手机号">
                  <el-input autocomplete="off" v-model="formAddAdmin.phone"></el-input>
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
            label="名称">
            <template slot-scope="scope">{{ scope.row.name }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="手机号">
            <template slot-scope="scope">{{ scope.row.phone }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="创建时间">
            <template slot-scope="scope">{{ scope.row.createTime }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="更新时间">
            <template slot-scope="scope">{{ scope.row.updateTime }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="当前状态"
            prop="status">
            <template slot-scope="scope" v-if="arrTemp.includes('ADMIN_UPDATE_STATUS')">
              <el-button :type="scope.row.status===0?'primary':'danger'"
                         plain size="small"
                         @click="handleUpdateStatus(scope.row,scope.row.status=scope.row.status===1?0:1)">
                {{ scope.row.status === 0 ? '正常' : '已禁用' }}
              </el-button>
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            prop="id"
            label="操作">
            <template slot-scope="scope">
              <el-button v-if="arrTemp.includes('ADMIN_UPDATE_INFO')"  type="primary" plain size="small" @click="handleAmend(scope.row,scope.$index)">编辑</el-button>
              <el-button v-if="arrTemp.includes('ADMIN_DELETE_ADMIN')" type="danger" plain size="small" @click="handleDeleteStatus(scope.row,-1)">删除</el-button>
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            label="权限">
            <template slot-scope="scope">
              <el-button v-if="arrTemp.includes('ADMIN_GRANT')" type="primary" plain size="small" @click="showSystem(scope.row)">分配权限</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
    <!--修改用户信息的弹窗-->
    <el-dialog
      title="修改管理员信息"
      :visible.sync="dialogAmendAdmin"
      width="35%">
      <el-form :model="formAmendAdmin">
        <el-form-item :label-width="'100px'" label="修改名称">
          <el-input autocomplete="off" v-model="formAmendAdmin.name" ></el-input>
        </el-form-item>
        <el-form-item :label-width="'100px'" label="修改手机号">
          <el-input autocomplete="off" v-model="formAmendAdmin.phone"></el-input>
        </el-form-item>
        <el-form-item :label-width="'100px'" label="修改密码">
          <el-input type="password" autocomplete="off" v-model="formAmendAdmin.password"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
                <el-button @click="handleAmendClose">取 消</el-button>
                <el-button type="primary" @click="handleAmendSuccess">确 定</el-button>
              </span>
    </el-dialog>
    <!--查看用户可以操作权限的弹窗-->
    <el-dialog v-model="resultPermission"
               title="分配权限"
               :visible.sync="dialogSystem"
               width="35%">
      <el-tree
        ref="tree"
        :data="systemTotal"
        show-checkbox
        node-key="id"
        :props="defaultProps">
      </el-tree>
      <!--<span slot="footer" class="dialog-footer">-->
      <el-button @click="handleSystemClose">取 消</el-button>
      <el-button type="primary" @click="handleSystemSuccess">确 定</el-button>
      <!--</span>-->
    </el-dialog>
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
    addAdmin,
    amendAdminInfo,
    amendAdminStatus, amendAdminUseSystem,
    findByName, isExistedAdminName,
    listAdmin,
    listSystem, listSystemAndPermission, viewAdminUseSystem
  } from '../api/admin'

  export default {
  name: 'adminList',
    inject:['reload'],
  data () {
    return {
      name: '',
      dialogAddAdmin: false,
      dialogAmendAdmin:false,
      dialogSystem:false,
      arrTemp:[],
      formAddAdmin: {
        name: '',
        phone: '',
        systemList: []
      },
      formAmendAdmin:{
        name: '',
        phone: '',
        password:''
      },
      amendAmendId:'',
      systemList: [],
      // 数据表格
      tableData: [],
      //临时变量
      tempData:[],
      systemData:[],
      // 每页几条数据
      pageSize: 5,
      // 总页数
      totalPage: 0,
      // 当前页
      currentPage: 1,
      //全部系统和列表
      systemTotal: [],
      defaultProps: {
        children: 'permission',
        label: 'name'
      },
      //默认应该选中的系统和权限
      resultPermission:[]
    }
  },
    created() {
      this.handleGetAllAdmin()
      this.handleGetAllSystemAndPermission()
      this.handleShowButton()
    },
  methods: {
    handleShowButton(){
      this.arrTemp = localStorage.getItem("ButtonList");
      console.log(this.arrTemp);
    },
    //根据用户id展示用户拥有的权限
    showSystem(num){
      this.amendAmendId=num.id
      let AmendAdminStatusAO={
        adminId: num.id
      }
      this.dialogSystem=true
      viewAdminUseSystem(AmendAdminStatusAO).then(res=>{
        this.permissionListTest(res.data)
        console.log(this.resultPermission)
        this.$refs.tree.setCheckedKeys(this.resultPermission);
    })
    },
    //取消修改操作系统和权限
    handleSystemClose(){
      this.dialogSystem=false
    },
    //确认修改系统和权限
    handleSystemSuccess(){
      let SystemPermissonAO={
        adminId:this.amendAmendId,
        systemPermissionList:this.$refs.tree.getCheckedKeys().concat(this.$refs.tree.getHalfCheckedKeys())
      }
      console.log(SystemPermissonAO)
      amendAdminUseSystem(SystemPermissonAO).then(res=>{
        if (res.code ===0){
          this.$message.success("修改成功")
        }else {
          this.$message.error("修改失败")
        }
      })
      this.dialogSystem=false
    },
    permissionListTest(result) {
      var str = ''
      result.forEach(function (e) {
        str += e.id + ','
      })
      str = str.substring(0, str.length - 1)
      this.resultPermission = str.split(',')
    },
    //添加用户的时候先判断该用户是否存在
    handleAddSearch: function () {
      let adminAO = {
        name: this.formAddAdmin.name
      }
      console.log(adminAO)
      isExistedAdminName(adminAO).then(res => {
        if (res.code === 9999) {
          this.formAddAdmin.name = ''
          this.$message.error('该用户名已经存在')
        }
      })
    },
    // 每页几条数据变化
    handleSizeChange (val) {
      this.pageSize = val
    },
    // 当前页变化
    handleCurrentChange (val) {
      this.currentPage = val
    },
    // 根据用户名查找用户
    handleSearch () {
      let adminAO = {
        name: this.name
      }
      findByName(adminAO).then(res => {
        if (res.code === 0) {
          this.tableData = res.data
        } else {
          this.$message.error('查询为空')
        }
      })
    },
    // 添加用户
    handleAdd () {
      this.dialogAddAdmin = true
    },
    //修改信息
    handleAmend(num){
      this.dialogAmendAdmin = true
      this.tempData=num
      this.amendAmendId=num.id
      this.formAmendAdmin=JSON.parse(JSON.stringify(this.tempData))
    },
    //取消修改
    handleAmendClose () {
      this.dialogAmendAdmin=false
      this.formAmendAdmin=JSON.parse(JSON.stringify(this.tempData))
    },
    //确认修改
    handleAmendSuccess() {
      this.dialogAmendAdmin = false
      let amendAdminAO = {
        adminId:this.amendAmendId,
        name: this.formAmendAdmin.name,
        phone: this.formAmendAdmin.phone,
        password: this.formAmendAdmin.password
      }
      console.log(amendAdminAO)
      amendAdminInfo(amendAdminAO).then(res=>{
        if(res.code===0){
          this.$message.success('修改成功!')
        }else {
          this.$message.error(res.message)
        }
      })
      this.reload()
    },
    //取消添加
    handleAddClose () {
      this.dialogAddAdmin = false
    },
    //确认添加
    handleAddSuccess () {
      let addAdminAO = {
        name: this.formAddAdmin.name,
        phone: this.formAddAdmin.phone,
      }
      console.log(addAdminAO)
      addAdmin(addAdminAO).then(res=>{
        if (res.code === 0){
          this.$message.success('添加成功!')
          this.formAddAdmin.name=''
          this.formAddAdmin.phone=''
        } else{
          this.$message.error(res.message)
          this.formAddAdmin.name=''
          this.formAddAdmin.phone=''
        }
      })
      this.dialogAddAdmin = false
    },
    //删除用户时弹出弹窗 是否删除
    handleDeleteStatus(row, status) {
      let amendAdminStatusAO = {
        adminId: row.id,
        status: status
      }
      this.$confirm('是否删除该用户？','提示',{
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
        }).then(()=>{
          amendAdminStatus(amendAdminStatusAO).then(res => {
            if (res.code === 0) {
              this.$message.success('删除成功!')
              this.reload()
            } else {
              this.$message.error(res.message)
            }
          })
        }).catch(()=>{
          this.$message.info('已取消修改')
        })
    },
    //更新用户状态
    handleUpdateStatus(row, status) {
      let amendAdminStatusAO = {
        adminId: row.id,
        status: status
      }
      console.log(amendAdminStatusAO)
      amendAdminStatus(amendAdminStatusAO).then(res => {
        if (res.code === 0) {
          this.$message.success('修改成功!')
        } else {
          this.$message.error(res.message)
        }
      })
    },
    //获取所有的系统和权限
    handleGetAllSystemAndPermission(){
      listSystemAndPermission().then(res=>{
        this.systemTotal=res.data
      })
    },
    // TODO 获取所有管理员
    handleGetAllAdmin() {
      listAdmin().then(res => {
        console.log(res.data)
        this.tableData = res.data
        this.totalPage = this.tableData.length
      })
    }
  }
}
</script>

<style scoped>

</style>
