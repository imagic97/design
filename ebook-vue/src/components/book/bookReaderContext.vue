<template>
  <div
    id="text"
    v-html="responseText"
    :style="{'fontSize':fontSize+'px'}"
    v-lazy-container="{ selector: 'img' }"
  ></div>
</template>

<script>
export default {
  props: {
    responseText: String,
    fontSize: Number
  }
};
</script>
<style scoped>
#text {
  padding: 0 14px;
}
</style>