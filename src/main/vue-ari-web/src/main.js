// src/main.js
import Vue from 'vue'
import VueRouter from 'vue-router'
import VueResource from 'vue-resource'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-default/index.css'

import App from './App'
import Home from './components/Home'
import store from './store'
import TimeEntries from './components/TimeEntries.vue'

import 'bootstrap/dist/css/bootstrap.css'

Vue.use(VueRouter)
Vue.use(VueResource)
Vue.use(ElementUI)
Vue.config.silent = true
const routes = [{
  path : '/',
  component : Home
},{
  path : '/home',
  component : Home
},{
  path : '/time-entries',
  component : TimeEntries,
  children : [{
    path : 'log-time',
    //懒加载
    component : resolve => require(['./components/LogTime.vue'],resolve)
  }]
}];

const router = new VueRouter({
  routes
});

/* eslint-disable no-new */
// 实例化我们的Vue
var app = new Vue({
  el: '#app',
  router,
  store,
  ...App,
});
