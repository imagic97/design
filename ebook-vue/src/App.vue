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
  width: 670px;
  height: 100%;
  background: beige;
  margin: 0 auto;
  user-select: none;
}

@media screen and (max-width: 500px) {
  #app {
    width: 100%;
  }
}
</style>
