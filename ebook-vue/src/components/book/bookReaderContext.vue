<template>
  <div
    id="text"
    v-html="responseHtml"
    :style="{'fontSize':fontSize+'px'}"
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
#text {
  padding: 0 14px;
}
</style>