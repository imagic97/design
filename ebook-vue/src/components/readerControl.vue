<template>
  <div class="readerControls">
    <div v-on:click="totleContentShow()" class="readerControls_item readerControls_item1">
      <span class="title">目录</span>
    </div>
    <div href="javascript:" title="目录" class="readerControls_item readerControls_item2">
      <span class="title">主题</span>
    </div>
    <div href="javascript:" title="目录" class="readerControls_item readerControls_item3">
      <span class="title">字体</span>
    </div>
  </div>
</template>
<script>
import { ebookMixin } from "@/util/mixin";
export default {
  mixins: [ebookMixin],
  methods: {
    totleContentShow() {
      this.setContentShow(!this.contentShow);
    }
  }
};
</script>
<style scoped>
.readerControls {
  width: 100px;
  user-select: none;
  position: fixed;
  right: 15%;
  bottom: 56px;
  width: 100px;
}

.readerControls_item:hover {
  background: linear-gradient(90deg, rgba(0, 0, 0, 0.05), transparent);
}
.readerControls_item {
  margin: 10px 0;
  text-decoration: none;
  background: #fff;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0.03);
  border-radius: 27px;
  line-height: 48px;
  text-align: center;
  box-shadow: 0 6px 12px rgba(0, 25, 104, 0.1);
}
</style>