<template>
  <div
    id="text"
    v-html="responseHtml"
    :style="{ fontSize: fontSize + 'px' }"
    v-lazy-container="{ selector: 'img' }"
  ></div>
</template>

<script>
export default {
  props: {
    responseHtml: String,
    fontSize: String
  }
};
</script>
<style scoped>
@import "../../assets/reset.css";
#text {
  padding: 0 16px;
}
</style>
