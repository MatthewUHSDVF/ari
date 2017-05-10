<template>
  <div id="wrapper">
    <nav class="navbar navbar-default">
      <div class="container">
        <el-col :span="6">
        <a class="navbar-brand" href="#">
          <i class="glyphicon glyphicon-time"></i>
          计划板
        </a></el-col>
        <el-col :span="6">
        <ul class="nav navbar-nav">
          <li><router-link to="/home">首页</router-link></li>
          <li><router-link to="/time-entries">计划列表</router-link></li>
        </ul>
        </el-col>
      </div>
    </nav>
      <div>
        <el-row :gutter="5">
          <el-col :span="6"><sidebar></sidebar></el-col>
          <el-col :span="18"><router-view></router-view></el-col>
        </el-row>
      </div>
  </div>
</template>

<script>
  import Sidebar from './components/Sidebar.vue'

  export default {
      components: {
        'sidebar': Sidebar},
  }
</script>


