<template>
  <div id="app">
    <reading />
  </div>
</template>

<script>
import reading from "./views/book/reading.vue";
export default {
  name: "app",
  components: {
    reading
  }
};
</script>

<style>
@import url(assets/reset.css);
/* @import url(assets/dark.css); */
html,
body {
  position: relative;
  height: 100%;
  min-height: 100%;
  background-color: #f6f7f9;
}

#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
</style>
