<template>
  <div class="bookSelfView">
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
.bookSelfView {
  width: 100%;
  overflow: auto;
  height: 100%;
}
</style>
