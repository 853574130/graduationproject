import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

/**
 * 这个也不知道是干嘛的  但估计和主题相关
 */
export const THEME_TYPE = 'THEME_TYPE'; //主题类型

const state = {
    themeType: '',
    githubToken: ['37c846bbaceee76ca17506', '82100f5b21ab5f7310'],
    // 0f6560744b42121c3180d50684ff2aad37cc36fd

}

const mutations = {
    [THEME_TYPE](state, payload) {
        state.themeType = payload.themeType;
    }
}

const actions = {
    changeThemeType({ commit }, data) {
        commit(
            THEME_TYPE, {
                themeType: data
            });
    }
}


export default new Vuex.Store({
    state,
    mutations,
    actions
})