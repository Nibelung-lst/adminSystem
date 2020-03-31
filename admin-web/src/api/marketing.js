import {postAndGet} from '../utils/api'
const _axios = postAndGet();

// 获取所有优惠券
export const getAllCoupon = (params) => {
  return _axios.post('/coupon/getCouponList', params).then(res => res.data)
}
// 根据id获取该张优惠券信息
export const getCouponByCode = (params) => {
  return _axios.get('/coupon/getCouponByCode?'+params).then(res => res.data)
}
// 添加优惠券
export const addCoupon = (params) => {
  return _axios.post('/coupon/createCoupon', params).then(res => res.data)
}
// 删除优惠券
export const handleChangeStatus = (params) => {
  return _axios.get('/coupon/changeCouponStatus?' + params).then(res => res.data)
}
// 设置优惠券发布时间
export const setReleaseTime = (params) => {
  return _axios.post('/coupon/setReleaseTime?', params).then(res => res.data)
}
// 查看优惠券历史
export const getUserCouponList = (params) => {
  return _axios.post('/coupon/getUserCouponList?', params).then(res => res.data)
}
// 赠送优惠券
export const sendUserCoupon = (params) => {
  return _axios.get('/coupon/sendCouponToUser?'+params).then(res => res.data)
}
// 获取广告列表
export const getAdvertisementList = (params) => {
  return _axios.post('/advertisement/getAdvertisementByPage', params).then(res => res.data)
}
// 改变广告状态
export const changeAdvertisementStatus = (params) => {
  return _axios.post('/advertisement/changeStatus', params).then(res => res.data)
}
// 新增广告
export const createAdvertisement = (params) => {
  return _axios.post('/advertisement/createAdvertisement', params).then(res => res.data)
}
// 编辑广告
export const editAdvertisement = (params) => {
  return _axios.post('/advertisement/editAdvertisement', params).then(res => res.data)
}

