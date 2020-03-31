import {postAndGet} from '../utils/api'
const _axios = postAndGet();

// 后台改变用户状态
export const changeStatus = (params) => {
  return _axios.post('/user/changeStatus', params).then(res => res.data)
}
// 后台管理查看用户信息列表
export const searchUserList = (params) => {
  return _axios.post('/user/userList', params
  ).then(res => res.data)
}
// 后台模糊查询用户信息
export const searchUser = (params) => { //
  return _axios.post('/user/searchUser', params).then(res => res.data)
}
// TODO
// 后台查看用户登陆流水
export const getAllLogin = (params) => { //
  return _axios.post('/user/getAllLogin', params).then(res => res.data)
}
// 后台查看用户等级规则
export const userLevelList = (params) => {
  return _axios.post('/user/userLevelList', params).then(res => res.data)
}
// 得到注册的年份列表
export const getAllRegisterYears = () => {
  return _axios.get('/user/getAllRegisterYears').then(res => res.data)
}
// 统计某年每个月用户注册量
export const countRegisterUserByMount = (params) => {
  return _axios.post('/user/countRegisterUserByMount', params).then(res => res.data)
}
// 得到登录的年份列表
export const getAllLoginYears = () => {
  return _axios.get('/user/getAllLoginYears').then(res => res.data)
}
// 统计某年某月每日的用户登录量
export const countLoginUserByDay = (params) => {
  return _axios.post('/user/countLoginUserByDay', params).then(res => res.data)
}
// 后台根据用户名查看其积分记录
export const searchPoints = (params) => {
  return _axios.post('/user/points/searchPoints', params).then(res => res.data)
}
// 后台查看积分记录
export const pointsRecord = (params) => {
  return _axios.post('/user/points/pointsRecord', params).then(res => res.data)
}
// 后台查看积分规则
export const pointsType = (params) => {
  return _axios.post('/user/points/pointsType', params).then(res => res.data)
}
