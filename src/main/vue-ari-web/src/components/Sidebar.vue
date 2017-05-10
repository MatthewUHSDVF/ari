<template>
  <div class="panel panel-default">
    <div class="panel-heading">
      <p class="text-center">已有时长</p>
    </div>

    <div class="panel-body">
      <p class="text-center">{{ time }} 小时</p>
    </div>
  </div>
</template>

<script>
  export default {
      computed: {
          time() {
              return this.$store.state.totalTime
          }
      }
  }
</script>
<style>
  .panel-heading {
    font-size: large;
    font-weight: bold;
  }
  .panel-body {
    font-size: large;
  }
</style>
