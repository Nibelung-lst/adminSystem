import {postAndGet} from '../utils/api'
const _axios = postAndGet();


// TODO 获取所有库存
// 动态获取库存列表
export const getDynamicStockList = (params) => { //
  return _axios.post('/getStockList',params).then(res => res.data)// 已经封装了一层，得到response的数据
}

// 更新库存信息
export const updateStock = (params) => { //
  return _axios.post( '/backUpdateStock',params).then(res => res.data)// 已经封装了一层，得到response的数据
}

// 获取库存流水
export const getFlowList = (params) => { //
  return _axios.post( '/getStockFlow',params).then(res => res.data)// 已经封装了一层，得到response的数据
}


// 方案列表
export const getAllPlanList = (params) => { //
  return _axios.post( '/getPlanList',params).then(res => res.data)// 已经封装了一层，得到response的数据
}

// 根据方案编码获取方案明细
export const getPlanDetailByPlanCode = (params) => { //
  return _axios.post( '/getPlanbyPlanCode',params).then(res => res.data)// 已经封装了一层，得到response的数据
}


// 明细方案列表
export const getAllPlanDetailList = (params) => { //
  return _axios.post( '/getPlanDetailList',params).then(res => res.data)// 已经封装了一层，得到response的数据
}


// addStockByAdmin
export const addStockByAdmin = (params) => { //
  return _axios.post( '/addStockByAdmin',params).then(res => res.data)// 已经封装了一层，得到response的数据
}

// 根据id更新明细信息
export const updateDetail = (params) => { //
  return _axios.post( '/updateDetailById',params).then(res => res.data)// 已经封装了一层，得到response的数据
}

// 完成补货操作
export const completeDetails = (params) => { //
  return _axios.post( '/completeDetail',params).then(res => res.data)// 已经封装了一层，得到response的数据
}
