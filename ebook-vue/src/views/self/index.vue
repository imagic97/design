<template>
  <div class="self_view">
    <book-self />
  </div>
</template>
<script>
import bookSelf from "@/components/self/bookSelf.vue";
import { ebookMixin } from "@/util/mixin";
export default {
  mixins: [ebookMixin],
  components: {
    bookSelf
  }
};
</script>
<style scoped>
.self_view {
  width: 100%;
  overflow: auto;
  height: 100%;
}
</style>
