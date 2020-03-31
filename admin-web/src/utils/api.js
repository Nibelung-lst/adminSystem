import ax from 'axios'
import {Message} from 'element-ui';

const _axios =ax.create({
  baseURL:  'http://localhost:8080',
  timeout: 10000,
})
_axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8'
_axios.defaults.baseURL = 'http://localhost:8080/'
_axios.defaults.withCredentials = false
_axios.defaults.withCredentials = false

_axios.interceptors.request.use(
  config => {
    //将token保存到了localStorage，将其添加到请求头
    if (localStorage.getItem("token")) {
      config.headers.Authorization = localStorage.getItem("token")
    }
    return config
  }
)
_axios.interceptors.response.use(function (success) {
  return success;
},function (error){
  if (error && error.response) {
    switch (error.response.status) {
      case 400:
        error.message = '错误请求';
        break;
      case 401:
        error.message = '未授权，请重新登录';
        break;
      case 403:
        error.message = '权限不足拒绝访问';
        break;
      case 404:
        error.message = '请求错误,未找到该资源';
        break;
      case 405:
        error.message = '请求方法未允许';
        break;
      case 408:
        error.message = '请求超时';
        break;
      case 500:
        error.message = '服务器端出错';
        break;
      case 501:
        error.message = '网络未实现';
        break;
      case 502:
        error.message = '网络错误';
        break;
      case 503:
        error.message = '服务不可用';
        break;
      case 504:
        error.message = '网络超时';
        break;
      case 505:
        error.message = 'http版本不支持该请求';
        break;
      default:
        error.message = `连接错误${error.response.status}`;
    }
    let errorData = {
      status: error.response.status,
      message: error.message,
      config: error.response.config
    };
    Message.error(errorData.message)
    console.log('统一错误处理: ', errorData)
  }
  return Promise.reject(error);
})

export const postAndGet = () =>{
  return _axios;
}



