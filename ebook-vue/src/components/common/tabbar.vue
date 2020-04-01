<template>
  <div class="tabbarContainer">
    <div class="tabbar_item">
      <router-link to="/search" tag="div" class="tab-items">
        <span class="title">搜索</span>
      </router-link>
    </div>

    <div class="tabbar_item">
      <router-link to="/category" tag="div" class="tab-items">
        <span class="title">书城</span>
      </router-link>
    </div>

    <div class="tabbar_item">
      <router-link to="/self" tag="div" class="tab-items">
        <span class="title">书架</span>
      </router-link>
    </div>

    <div class="tabbar_item">
      <router-link to="/mine" tag="div" class="tab-items">
        <span class="title">我</span>
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
.tabbarContainer {
  position: absolute;
  bottom: 0;
  width: 100%;
  height: 64px;
  z-index: 999;
  box-shadow: 0 -1px 3px rgba(0, 25, 104, 0.3);
  text-align: center;
  display: flex;
  background-color: #f1f1f1;
}

.tabbar_item:hover {
  background: rgba(0, 0, 0, 0.05);
}
.tabbar_item {
  margin: 0 auto;
  flex: 1;
  line-height: 64px;
}

span {
  margin: 0 auto;
}
</style>
