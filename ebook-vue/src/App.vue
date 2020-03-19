<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default {
  name: "app"
};
</script>

<style>
body {
  background: #7d8188;
}
#app {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 0;
  width: 870px;
  height: 100%;
  background-color: #f1f1f1;
  margin: 0 auto;
  user-select: none;
}

@media screen and (max-width: 750px) {
  #app {
    width: 100%;
  }
}
</style>
