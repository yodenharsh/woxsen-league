import { createStore } from "vuex";

export default createStore({
  state: {
    token: "",
  },
  getters: {
    getToken(state) {
      return state.token;
    },
  },
  mutations: {
    setToken(state, payload) {
      state.token = payload.newToken;
    },
  },
  actions: {
    setToken({ commit }, payload) {
      commit("setToken", payload);
    },
  },
  modules: {},
});
