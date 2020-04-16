import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

const state = {
  adminName: "",
  token: "",
};

const mutations = {
  SET_ADMINNAME(state, adminName) {
    state.adminName = adminName;
  },
  SET_TOKEN(state, token) {
    state.token = token;
  },
};

const actions = {
  setAdminName({ commit }, adminName) {
    return commit("SET_ADMINNAME", adminName);
  },
  setToken: ({ commit }, token) => {
    return commit("SET_TOKEN", token);
  },
};
const getters = {
  adminName: (state) => state.adminName,
  token: (state) => state.token,
};

export default new Vuex.Store({
  state,
  actions,
  getters,
  mutations,
});
