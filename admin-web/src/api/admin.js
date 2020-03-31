import {postAndGet} from '../utils/api'
const _axios = postAndGet();

// TODO  管理员登录请求
export const login = (params) => {
  return _axios.post('/login', params).then(res => res.data)
}

// TODO  管理员退出
export const logout = (params) => {
  return _axios.post('/logout', params).then(res => res.data)
}
// TODO  检查用户邮件
export const check = (params) => {
  return _axios.get('/admin/checkAdmin',{
    params:{
      'email':params
    }
  }).then(res => res.data)
}

// TODO  测试
export const test = () => {
  return _axios.get('/demo/test').then(res => res.data)
}

// TODO  获取所有管理员信息
export const listAdmin = () => {
  return _axios.get('/admin/listAdmin').then(res => res.data)
}
// TODO 添加用户
export const addAdmin = (params) => {
  return _axios.post('/admin/addAdmin', params).then(res => res.data)
}

// TODO 获取所有菜单
export const listMenu = () => {
  return _axios.post('/admin/getAllMenu').then(res => res.data)
}
// TODO 获取所有菜单
export const listRole = () => {
  return _axios.post('/admin/getAllRole').then(res => res.data)
}

// TODO  检查用户邮件
export const listRoleByMid = (params) => {
  return _axios.get('/admin/getRoleByMid',{
    params:{
      'menuId':params
    }
  }).then(res => res.data)
}
// TODO  检查用户邮件
export const searchRoleByName = (params) => {
  return _axios.get('/admin/searchRole',{
    params:{
      'roleName':params
    }
  }).then(res => res.data)
}

export const addAuthority = (params) => {
  return _axios.get('/admin/addAuthority',{
    params:{
      'menuId':params.menuId,
      'roleId':params.roleId
    }
  }).then(res => res.data)
}

export const checkSystemName = (params) => {
  return _axios.get('/admin/isExistedSystemName',{
    params:{
      'name':params
    }
  }).then(res => res.data)
}

export const checkSystemUrl = (params) => {
  return _axios.get('/admin/isExistedSystemUrl',{
    params:{
      'url':params
    }
  }).then(res => res.data)
}

export const addMenu = (params) => {
  return _axios.post('/admin/addMenu',params).then(res => res.data)
}

export const getAllAdmin = () => {
  return _axios.post('/admin/getAllAdmin',).then(res => res.data)
}

export const givePosition = (params) => {
  return _axios.get('/admin/givePosition',{
    params:{
      'adminId':params.adminId,
      'roleId':params.roleId
    }
  }).then(res => res.data)
}

export const addRole = (params) => {
  return _axios.get('/admin/addRole',{
    params:{
      'name':params.name,
      'nameZh':params.nameZh
    }
  }).then(res => res.data)
}

