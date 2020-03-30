<template>
  <div class="readerControls" v-show="menuVisible">
    <div
      class="readerControls_item readerControls_item1"
      :class="{'selected': menuShow === 3}"
      @click="showSetting(3)"
    >
      <span class="title">书签</span>
    </div>

    <div
      class="readerControls_item readerControls_item2"
      :class="{'selected': menuShow === 2}"
      @click="showSetting(2)"
    >
      <span class="title">主题</span>
    </div>

    <div
      class="readerControls_item readerControls_item3"
      :class="{'selected': menuShow === 1}"
      @click="showSetting(1)"
    >
      <span class="title">目录</span>
    </div>
    <div
      class="readerControls_item readerControls_item4"
      :class="{'selected': menuShow === 0}"
      @click="showSetting(0)"
    >
      <router-link to="/self" tag="span">
        <span class="title">回到书架</span>
      </router-link>
    </div>
  </div>
</template>
<script>
import { ebookMixin } from "@/util/mixin";
export default {
  mixins: [ebookMixin],
  methods: {
    showSetting(value) {
      this.setMenuShow(value);
    }
  }
};
</script>
<style scoped>
.readerControls {
  position: absolute;
  bottom: 0;
  width: 100%;
  height: 64px;
  z-index: 999;
  box-shadow: 0 -8px 21px rgba(0, 25, 104, 0.3);
  text-align: center;
  display: flex;
}

.readerControls_item:hover {
  background: linear-gradient(90deg, rgba(0, 0, 0, 0.05), transparent);
}
.readerControls_item {
  margin: 0 auto;
  line-height: 64px;
  flex: 1;
}

span {
  margin: 0 auto;
}
</style>
