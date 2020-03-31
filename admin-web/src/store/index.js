import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    token: '',
    AuthNoList: [],
    ParentCode:'',
    CategoryCode:'',
  },
  mutations: {
    setParentCode (state,ParentCode) {
      state.ParentCode = ParentCode
    },
    setCategoryCode (state,CategoryCode) {
      state.CategoryCode = CategoryCode
    },
    setAuthNoList (state, AuthNoList) {
      state.AuthNoList = AuthNoList
    },
    setToken (state, token) {
      state.token = token
    }
  },
  actions: {
  },
  modules: {
  }
})
export default store
