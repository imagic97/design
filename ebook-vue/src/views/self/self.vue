<template>
  <div class="bookSelfView">
    <self-header></self-header>
    <book-self />
  </div>
</template>
<script>
import bookSelf from "@/components/self/bookSelf.vue";
import selfHeader from "@/components/self/selfHeader.vue";
import { ebookMixin } from "@/util/mixin";
export default {
  mixins: [ebookMixin],
  components: {
    bookSelf,
    selfHeader
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
